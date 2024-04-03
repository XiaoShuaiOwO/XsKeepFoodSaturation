package com.xssssss.xskeepfoodsaturation.utils;

import com.xssssss.xskeepfoodsaturation.XsKeepFoodSaturation;

import java.util.List;
import java.util.logging.Logger;

public class utils {
    private final Logger logger = Logger.getLogger("utils");
    XsKeepFoodSaturation main = new XsKeepFoodSaturation();
    public boolean isEnableWorld(String mode, String world){
        List<String> worldlist = (List<String>)main.config.get("enableWorlds.list");
        if (!(mode.equalsIgnoreCase("ALL") || mode.equalsIgnoreCase("WHITELIST") || mode.equalsIgnoreCase("BLACKLIST"))) {
            logger.warning("config.yml中的\"enableWorlds.mode\"只能为\"ALL/WHITELIST/BLACKLIST\"，已启用默认模式\"ALL\"！");
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
