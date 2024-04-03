package com.xssssss.xskeepfoodsaturation.listeners;

import com.xssssss.xskeepfoodsaturation.XsKeepFoodSaturation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeath implements Listener {
    private final XsKeepFoodSaturation main;
    public onDeath(XsKeepFoodSaturation main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        String uuid = e.getEntity().getUniqueId().toString();
        int foodLevel = e.getEntity().getFoodLevel();
        String world = e.getEntity().getWorld().getName();
        main.data.set("playerUUID."+uuid+".foodLevel",foodLevel);
        main.data.set("playerUUID."+uuid+".world",world);
        main.saveData();
    }
}
