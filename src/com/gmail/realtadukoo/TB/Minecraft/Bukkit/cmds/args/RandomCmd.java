package com.gmail.realtadukoo.TB.Minecraft.Bukkit.cmds.args;

import org.bukkit.command.CommandSender;

import com.gmail.realtadukoo.TB.API.Constants.EnumBible;
import com.gmail.realtadukoo.TB.API.Constants.EnumTranslation;
import com.gmail.realtadukoo.TB.Minecraft.Bukkit.TB;
import com.gmail.realtadukoo.TB.Minecraft.Bukkit.Enums.EnumCmds;
import com.gmail.realtadukoo.TB.Minecraft.Bukkit.cmds.Randomize;
import com.gmail.realtadukoo.TB.Minecraft.Bukkit.cmds.Verse;
import com.gmail.realtadukoo.TB.Minecraft.Bukkit.cmds.handling.Args;

public class RandomCmd {
	public static void run(TB plugin, CommandSender sender, String playerType, String[] args){
		if(Args.argsLengthCheck(sender, args, 1, 6, "/bible random [book] [chapter] [translation]")){
			return;
		}
		EnumCmds cmds = EnumCmds.RANDOM;
		EnumTranslation etran = EnumTranslation.fromAbbreviation(TB.config.getString("default.translation"));
		String bookName = null, chp = null, v = null;
		String tran = etran.getAbbreviation();
		boolean bookSet = false, chpSet = false, vSet = false, tranSet = false;
		int i = 1;
		while(args.length >= i + 1 && args[i] != null){
			if(!bookSet && Args.isBook(cmds, args, i) != null){
				EnumBible book = Args.isBook(cmds, args, i);
				bookName = book.getBook();
				bookSet = true;
				i = Args.getCurrentArg(cmds, args, i);
			}else if(!tranSet && Args.tranCheck(sender, args[i]) != null){
				tran = Args.tranCheck(sender, args[i]);
				tranSet = true;
				i++;
			}else{
				try{
					if(!chpSet){
						int c = Integer.parseInt(args[i]);
						chp = String.valueOf(c);
						i++;
						chpSet = true;
					}else{
						Args.unknownArg(plugin, sender, args[i]);
						return;
					}
				}catch(NumberFormatException e){
					Args.unknownArg(plugin, sender, args[i]);
					return;
				}
			}
		}
		if(bookSet == false){
			bookName = Randomize.book(tran);
		}
		if(chpSet == false){
			chp = Randomize.chapter(bookName);
		}
		if(vSet == false){
			v = Randomize.verse(bookName, chp);
		}
		Verse.check(plugin, sender, playerType, bookName, chp, v, tran, "get", null, false, false);
	}
}