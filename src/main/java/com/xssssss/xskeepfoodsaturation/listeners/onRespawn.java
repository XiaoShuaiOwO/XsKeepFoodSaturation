package com.xssssss.xskeepfoodsaturation.listeners;

import com.xssssss.xskeepfoodsaturation.XsKeepFoodSaturation;
import com.xssssss.xskeepfoodsaturation.utils.utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.logging.Logger;

public class onRespawn implements Listener {
    private final Logger logger = Logger.getLogger("onRespawn");
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        XsKeepFoodSaturation main = new XsKeepFoodSaturation();
        Player player = e.getPlayer();
        boolean enableMessage = (boolean)main.config.get("message.enableMessage");
        if (player.hasPermission("XsKeepFoodSaturation.bypass")) {
            player.setFoodLevel(20);
            if (enableMessage){
                player.sendMessage(main.config.get("message.bypassMessage").toString());
            }
        }else {
            String uuid = player.getUniqueId().toString();
            String world = main.data.get("playerUUID."+uuid+".world").toString();
            String mode = main.config.get("enableWorlds.mode").toString();
            utils u = new utils();
            if (u.isEnableWorld(mode,world)){
                if (main.data.get("playerUUID."+uuid+".foodLevel" ) != null) {
                    int foodLevel = Integer.getInteger(main.data.get("playerUUID."+uuid+".foodLevel").toString());
                    player.setFoodLevel(foodLevel);
                    if (enableMessage){
                        player.sendMessage(main.config.get("message.defaultMessage").toString());
                    }
                }else {
                    logger.warning("因未知原因，无法在\"data.yml\"中找到玩家 "+player.getName()+" 的\"foodLevel\"数据。因此不作处理！");
                }
            }
        }
    }

}
