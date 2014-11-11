package com.gmail.realtadukoo.TBP.commands.args;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.gmail.realtadukoo.TBP.TB;
import com.gmail.realtadukoo.TBP.Enums.EnumBooks;
import com.gmail.realtadukoo.TBP.Enums.EnumCmds;
import com.gmail.realtadukoo.TBP.Enums.EnumTrans;
import com.gmail.realtadukoo.TBP.commands.Sending;
import com.gmail.realtadukoo.TBP.commands.handling.Args;
import com.gmail.realtadukoo.TBP.commands.handling.Checks;

public class Announce {
	public static void run(TB plugin, CommandSender sender, String[] args){
		if(Args.argsLengthCheck(sender, args, 3, 7, plugin.getLanguage().getString("help.pages.ann.usage"))){
			return;
		}
		EnumBooks book = EnumBooks.GENESIS;
		EnumTrans etran = EnumTrans.KJV;
		etran = etran.getDefault();
		String bookName = book.getBook();
		String chp = null;
		String v = null;
		String tran = etran.getTran();
		EnumCmds cmds = EnumCmds.ANNOUNCE;
		int i = 2;
		if(Args.isBook(book, cmds, args, 1) != null){
			book = Args.isBook(book, cmds, args, 1);
			bookName = book.getBook();
			i = Args.getCurrentArg(book, cmds, args, 1);
		}else{
			String error = plugin.getLanguage().getString("command.announce.mustbebookerr");
			error = error.replaceAll("\\{args1\\}", args[1]);
			sender.sendMessage(ChatColor.RED + error);
			return;
		}
		if(args.length >= i + 1){
			if(args[i].contains(":")){
				String[] chpV = args[i].split(":");
				chp = chpV[0];
				v = chpV[1];
				i++;
			}else{
				chp = args[i];
				i++;
				if(args.length >= i + 1){
					v = args[i];
					i++;
				}else{
					sender.sendMessage(ChatColor.RED + 
							plugin.getLanguage().getString("command.args.notenougherr"));
					sender.sendMessage(ChatColor.RED + plugin.getLanguage().getString("help.pages.ann.usage"));
					return;
				}
			}
		}else{
			sender.sendMessage(ChatColor.RED + 
					plugin.getLanguage().getString("command.args.notenougherr"));
			sender.sendMessage(ChatColor.RED + plugin.getLanguage().getString("help.pages.ann.usage"));
			return;
		}
		if(args.length == i + 1 && Args.tranCheck(sender, args[i]) != null){
			tran = Args.tranCheck(sender, args[i]);
		}
		if(!book.isAvailable(tran)){
			return;
		}
		if(!Checks.checkForYML(plugin, sender, bookName, tran)){
			return;
		}
		String ref = Checks.bookCheck(plugin, sender, book, bookName, chp, v);
		if(ref == null){
			return;
		}
		Sending.broadcastVerse(plugin, sender, bookName, chp, v, tran, ref);
	}
}
