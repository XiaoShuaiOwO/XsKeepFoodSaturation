package com.xssssss.xskeepfoodsaturation;

import com.xssssss.xskeepfoodsaturation.commands.commands;
import com.xssssss.xskeepfoodsaturation.listeners.onDeath;
import com.xssssss.xskeepfoodsaturation.listeners.onRespawn;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class XsKeepFoodSaturation extends JavaPlugin {
    private File configFile;
    public static FileConfiguration config;
    private File dataFile;
    public static FileConfiguration data;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[XsKeepFoodSaturation] XsKeepFoodSaturation已加载！");
        getLogger().info("[XsKeepFoodSaturation] 作者：XiaoShuaiOwO");
        getLogger().info("[XsKeepFoodSaturation] 版本：1.0.0");

        getCommand("XSKeepFoodSaturation").setExecutor(new commands());
        getServer().getPluginManager().registerEvents(new onDeath(), this);
        getServer().getPluginManager().registerEvents(new onRespawn(), this);

        loadConfig();
        loadData();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[XsKeepFoodSaturation] 已卸载");
    }

    public void loadConfig() {
        configFile = new File(getDataFolder(), "config.yml");
        if(!configFile.exists()){
            saveResource("config.yml",false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public void loadData() {
        dataFile = new File(getDataFolder(), "data.yml");
        if(!dataFile.exists()){
            saveResource("data.yml",false);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }
    public void saveData() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            getLogger().severe("[XsKeepFoodSaturation] 无法保存data.yml文件: " + e.getMessage());
        }
    }
    public void saveData(String path,Object value){
        data.set(path,value);
        saveData();
    }
}
