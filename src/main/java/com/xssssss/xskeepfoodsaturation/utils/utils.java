package com.xssssss.xskeepfoodsaturation.utils;

import com.xssssss.xskeepfoodsaturation.XsKeepFoodSaturation;

import java.util.List;
import java.util.logging.Logger;

public class utils {
    private final Logger logger = Logger.getLogger("utils");
    private final XsKeepFoodSaturation main;
    public utils(XsKeepFoodSaturation main) {
        this.main = main;
    }
    public boolean isEnableWorld(String mode, String world){
        List<String> worldlist = (List<String>)main.config.get("enableWorlds.list");
        if (!(mode.equalsIgnoreCase("ALL") || mode.equalsIgnoreCase("WHITELIST") || mode.equalsIgnoreCase("BLACKLIST"))) {
            logger.warning("[XsKeepFoodSaturation] config.yml中的\"enableWorlds.mode\"只能为\"ALL/WHITELIST/BLACKLIST\"，已启用默认模式\"ALL\"！");
            mode = "ALL";
        }
        switch (mode){
            case "ALL":
                return true;
            case "WHITELIST":
                if (worldlist.contains(world)) {
                    return true;
                }else { return false; }
            case "BLACKLIST":
                if (worldlist.contains(world)) {
                    return false;
                }else { return true; }
        }
        return true;
    }
}
