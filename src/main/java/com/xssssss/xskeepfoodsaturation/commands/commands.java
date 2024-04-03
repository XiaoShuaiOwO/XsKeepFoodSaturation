package com.xssssss.xskeepfoodsaturation.commands;

import com.xssssss.xskeepfoodsaturation.XsKeepFoodSaturation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (args.length == 0) {
            if (sender.isOp()) {
                sender.sendMessage("§7§l=========== §3§lXsKeepFoodSaturation §7§l===========");
                sender.sendMessage("§3§lBy: XiaoShuaiOwO");
                sender.sendMessage(" ");
                sender.sendMessage("§e命令列表:");
                sender.sendMessage("§e/kfs help §f- §7查看帮助");
                sender.sendMessage("§e/kfs set <player> <satiety> §f- §7设置玩家饱食度");
                sender.sendMessage("§e/kfs reload §f- §7重装插件");
                return true;
            }else {
                sender.sendMessage("§7§l=========== §3§lXsKeepFoodSaturation §7§l===========");
                sender.sendMessage("§3§lBy: XiaoShuaiOwO");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("help")){
            if (sender.hasPermission("XsKeepFoodSaturation.help")) {
                sender.sendMessage("§7§l=========== §3§lXsKeepFoodSaturation §7§l===========");
                sender.sendMessage("§3§lBy: XiaoShuaiOwO");
                sender.sendMessage(" ");
                sender.sendMessage("§e命令列表:");
                sender.sendMessage("§e/kfs help §f- §7查看帮助");
                sender.sendMessage("§e/kfs set <player> <satiety> §f- §7设置玩家饱食度");
                sender.sendMessage("§e/kfs reload §f- §7重装插件");
                return true;
            }else {
                sender.sendMessage("§7§l=========== §3§lXsKeepFoodSaturation §7§l===========");
                sender.sendMessage("§3§lBy: XiaoShuaiOwO");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("reload")){
            if (sender.hasPermission("XsKeepFoodSaturation.reload")){
                XsKeepFoodSaturation main = new XsKeepFoodSaturation();
                main.loadConfig();
                main.loadData();
                sender.sendMessage("§3[XsKeepFoodSaturation] §a插件重载成功！");
                return true;
            }else{
                sender.sendMessage("§3[XsKeepFoodSaturation] §c你没有权限执行此命令！");
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("set")){
            if (sender.hasPermission("XsKeepFoodSaturation.setFoodSaturation")){
                Player player = Bukkit.getPlayer(args[1]);
                if (player != null) {
                    try {
                        player.setFoodLevel(Math.min(Math.max(Integer.parseInt(args[2]), 0), 20));
                        return true;
                    } catch (NumberFormatException e){
                        sender.sendMessage("§3[XsKeepFoodSaturation] §c<satiety>需要为一个0-20内的数字!");
                    }
                }else {
                    sender.sendMessage("§3[XsKeepFoodSaturation] §c玩家不在线/不存在！");
                }
            }else{
                sender.sendMessage("§3[XsKeepFoodSaturation] §c你没有权限执行此命令！");
                return true;
            }
        }

        //无法识别命令参数
        if (sender.isOp()) {
            sender.sendMessage("§3[XsKeepFoodSaturation] §c参数有误！");
            sender.sendMessage("§3[XsKeepFoodSaturation] §c/kfs help §f- §c查看帮助");
            return true;
        }else {
            sender.sendMessage("§7§l=========== §3§lXsKeepFoodSaturation §7§l===========");
            sender.sendMessage("§3§lBy: XiaoShuaiOwO");
            return true;
        }

    }
}
