package com.gmail.lucario77777777.TBP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.lucario77777777.TBP.TB;
import com.gmail.lucario77777777.TBP.Enums.EnumBooks;
import com.gmail.lucario77777777.TBP.Enums.EnumCmds;
import com.gmail.lucario77777777.TBP.Enums.EnumTrans;
import com.gmail.lucario77777777.TBP.cmdhandling.Args;
import com.gmail.lucario77777777.TBP.cmdhandling.Book;
import com.gmail.lucario77777777.TBP.cmdhandling.Checks;

public class Sendbook {
	public static void run(TB plugin, String playerType, CommandSender sender, String[] args, boolean permsOn){
		if(Args.argsLengthCheck(sender, args, 4, 8, "/bible sendbook <player> <book> <part> [translation] " +
				"or /bible sendbook <player> <book> <part> \"anonymous\" [translation]")){
			return;
		}
		@SuppressWarnings("deprecation")
		Player player = sender.getServer().getPlayer(args[1]);
		String pName = player.getName();
		EnumBooks book = EnumBooks.GENESIS;
		EnumCmds cmds = EnumCmds.SENDBOOK;
		EnumTrans etran = EnumTrans.KJV;
		etran = etran.getDefault();
		String bookName = book.getBook();
		String part = null;
		String tran = etran.getTran();
		int i = 3;
		if(Args.isBook(book, cmds, args, 2) != null){
			book = Args.isBook(book, cmds, args, 2);
			bookName = book.getBook();
			i = Args.getCurrentArg(book, cmds, args, 2);
		}
		if(args.length >= i + 1){
			part = args[i];
			i++;
		}else{
			sender.sendMessage(ChatColor.RED + "Not enough args!");
			sender.sendMessage(ChatColor.RED + "/bible sendbook <player> <book> <part> [translation]");
			return;
		}
		boolean anonymous = false;
		if(args.length >= i + 1){
			if(args[i].equalsIgnoreCase("anonymous")){
				if(!Checks.permCheck(playerType, sender, "anonymous.book", permsOn)){
					return;
				}else{
					anonymous = true;
					i++;
				}
			}
		}
		if(args.length == i + 1 && Args.tranCheck(sender, args[4]) != null){
			tran = Args.tranCheck(sender, args[i]);
		}
		if(!book.isAvailable(tran)){
			return;
		}
		if(!player.hasPermission("TadukooBible.book.receive")){
			sender.sendMessage(ChatColor.RED + player.getName() + " does not have permission to receive " +
					"books!");
			sender.sendMessage(ChatColor.RED + "TadukooBible.book.receive");
			return;
		}
		Book.Run(plugin, sender, tran, bookName, part, "send", pName, anonymous);
	}
}
