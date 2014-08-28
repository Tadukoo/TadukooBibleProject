package com.gmail.lucario77777777.TBP.commands;

import org.bukkit.command.CommandSender;

import com.gmail.lucario77777777.TBP.TB;
import com.gmail.lucario77777777.TBP.Enums.EnumBooks;
import com.gmail.lucario77777777.TBP.Enums.EnumChps;
import com.gmail.lucario77777777.TBP.Enums.EnumCmds;
import com.gmail.lucario77777777.TBP.Enums.EnumTrans;
import com.gmail.lucario77777777.TBP.cmdhandling.Args;
import com.gmail.lucario77777777.TBP.cmdhandling.Checks;
import com.gmail.lucario77777777.TBP.cmdhandling.Randomize;
import com.gmail.lucario77777777.TBP.cmdhandling.References;
import com.gmail.lucario77777777.TBP.cmdhandling.Sending;

public class RandomCmd {
	public static void run(TB plugin, CommandSender sender, String[] args){
		if(Args.argsLengthCheck(sender, args, 1, 6, "/bible random [book] [chapter] [translation]")){
			return;
		}
		EnumBooks book = EnumBooks.GENESIS;
		EnumChps echp = EnumChps.GENESIS;
		EnumCmds cmds = EnumCmds.RANDOM;
		EnumTrans etran = EnumTrans.KJV;
		etran = etran.getDefault();
		String bookName = null;
		String chp = null;
		String v = null;
		String tran = etran.getTran();
		boolean bookSet = false;
		boolean chpSet = false;
		if(args.length >= 2 && Args.isBook(book, cmds, args, 1) != null){
			book = Args.isBook(book, cmds, args, 1);
			bookName = book.getBook();
			bookSet = true;
			int i = Args.getCurrentArg(book, cmds, args, 1);
			if(args.length >= i + 1){
				chp = args[i];
				chpSet = true;
				i++;
				if(args.length == i + 1 && Args.tranCheck(sender, args[i]) != null){
					tran = Args.tranCheck(sender, args[i]);
				}
			}
		}
		boolean vSet = false;
		String pName = sender.getName();
		if(bookSet == false){
			bookName = Randomize.book(book, tran);
		}
		if(chpSet == false){
			chp = Randomize.chapter(book, bookName);
		}
		if(vSet == false){
			v = Randomize.verse(book, echp, chp);
		}
		if(!Checks.checkForYML(plugin, sender, tran, bookName)){
			return;
		}
		String ref = References.makeRef(book, chp, v);
		if(!References.checkRef(plugin, sender, bookName, tran, ref)){
			return;
		}
		Sending.sendVerseToPlayer(plugin, sender, pName, bookName, chp, v, tran, ref);
	}
}
