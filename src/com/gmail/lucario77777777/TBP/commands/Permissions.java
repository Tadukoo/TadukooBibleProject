package com.gmail.lucario77777777.TBP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Permissions {
	public static boolean check(CommandSender sender, String perm){
		Player player = (Player) sender;
		if(player.hasPermission("TadukooBible." + perm)){
			return true;
		}else{
			sender.sendMessage(ChatColor.RED + "You don't have permission.");
			sender.sendMessage(ChatColor.RED + "You need TadukooBible." + perm);
			return false;
		}
	}
}