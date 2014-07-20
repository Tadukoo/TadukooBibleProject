package com.gmail.lucario77777777.TBP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.gmail.lucario77777777.TBP.TB;

public class Information {
	public static void info(TB plugin, CommandSender sender, String[] args){
		if(args.length < 2){
			sender.sendMessage(ChatColor.RED + "Not enough arguments!");
			sender.sendMessage(ChatColor.RED + "/bible about|info|abt|information " +
					"<translation>");
			return;
		}
		String tran = args[1].toUpperCase();
		String bookName = tran;
		String ref = "info";
		if(MainCommandExecutor.tranCheck(plugin, sender, tran) == false){
			return;
		}
		Send.player(plugin, sender, bookName, tran, ref);
	}
}