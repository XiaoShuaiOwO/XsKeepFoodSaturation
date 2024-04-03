package com.xssssss.xskeepfoodsaturation.listeners;

import com.xssssss.xskeepfoodsaturation.XsKeepFoodSaturation;
import com.xssssss.xskeepfoodsaturation.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public class onRespawn implements Listener {
    private final Logger logger = Logger.getLogger("onRespawn");
    private final XsKeepFoodSaturation main;

    public onRespawn(XsKeepFoodSaturation main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {

        Player player = e.getPlayer();
        boolean enableMessage = (boolean)main.config.get("message.enableMessage");
        if (player.hasPermission("XsKeepFoodSaturation.bypass")) {
            player.setFoodLevel(20);
            if (enableMessage){
                player.sendMessage(main.config.get("message.bypassMessage").toString().replace("&","§"));
            }
        }else {
            String uuid = player.getUniqueId().toString();
            Object worldObj = main.data.get("playerUUID."+uuid+".foodLevel");
            if (worldObj != null) {
                String world = worldObj.toString();
                String mode = main.config.get("enableWorlds.mode").toString();
                utils u = new utils(main);
                if (u.isEnableWorld(mode,world)){
                    Object foodLevelObj = main.data.get("playerUUID."+uuid+".foodLevel");
                    Object delayObj = main.config.get("setting.delay");
                    int delay = 5;
                    if (delayObj != null) {
                        delay = Integer.parseInt(main.config.get("setting.delay").toString());
                    }else{
                        logger.warning("[XsKeepFoodSaturation] 无法获取到\"config.yml\"中\"setting.delay\"节点的数据，已采用默认值\"5\"！");
                    }
                    if (foodLevelObj != null) {
                        int foodLevel = Integer.parseInt(foodLevelObj.toString());
                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                        scheduler.runTaskLater(main, new Runnable() {
                            @Override
                            public void run() {
                                player.setFoodLevel(foodLevel);
                                if (enableMessage) {
                                    player.sendMessage(main.config.get("message.defaultMessage").toString().replace("&", "§"));
                                }
                            }
                        }, delay);
                    }else {
                        logger.warning("[XsKeepFoodSaturation] 因未知原因，无法在\"data.yml\"中找到玩家 "+player.getName()+"("+player.getUniqueId().toString()+")"+" 的\"foodLevel\"数据。因此不作处理！");
                    }
                }
            }else {
                logger.warning("[XsKeepFoodSaturation] 因未知原因，无法在\"data.yml\"中找到玩家 "+player.getName()+" 的\"world\"数据。因此不作处理！");
            }
        }
    }

}
