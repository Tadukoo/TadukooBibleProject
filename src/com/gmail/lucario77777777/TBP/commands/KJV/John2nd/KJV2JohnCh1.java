package com.gmail.lucario77777777.TBP.commands.KJV.John2nd;

import com.gmail.lucario77777777.TBP.commands.KJV.bible.John2nd.KJVBible2JohnCh1;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.gmail.lucario77777777.TBP.Main;

public class KJV2JohnCh1 extends KJV2John {
	public KJV2JohnCh1(Main plugin) {
		super(plugin);
	}
	
	public static boolean Run(CommandSender sender, String[] args)
	{
		if(args.length <= 4){
			String v1 = KJVBible2JohnCh1.getV1();
			sender.sendMessage(ChatColor.GREEN + v1);
			return true;
		}else if(args.length ==5){
			if(args[4].equals("1")){
				String v1 = KJVBible2JohnCh1.getV1();
				sender.sendMessage(ChatColor.GREEN + v1);
				return true;
			}else if(args[4].equals("2")){
				String v2 = KJVBible2JohnCh1.getV2();
				sender.sendMessage(ChatColor.GREEN + v2);
				return true;
			}else if(args[4].equals("3")){
				String v3 = KJVBible2JohnCh1.getV3();
				sender.sendMessage(ChatColor.GREEN + v3);
				return true;
			}else if(args[4].equals("4")){
				String v4 = KJVBible2JohnCh1.getV4();
				sender.sendMessage(ChatColor.GREEN + v4);
				return true;
			}else if(args[4].equals("5")){
				String v5 = KJVBible2JohnCh1.getV5();
				sender.sendMessage(ChatColor.GREEN + v5);
				return true;
			}else if(args[4].equals("6")){
				String v6 = KJVBible2JohnCh1.getV6();
				sender.sendMessage(ChatColor.GREEN + v6);
				return true;
			}else if(args[4].equals("7")){
				String v7 = KJVBible2JohnCh1.getV7();
				sender.sendMessage(ChatColor.GREEN + v7);
				return true;
			}else if(args[4].equals("8")){
				String v8 = KJVBible2JohnCh1.getV8();
				sender.sendMessage(ChatColor.GREEN + v8);
				return true;
			}else if(args[4].equals("9")){
				String v9 = KJVBible2JohnCh1.getV9();
				sender.sendMessage(ChatColor.GREEN + v9);
				return true;
			}else if(args[4].equals("10")){
				String v10 = KJVBible2JohnCh1.getV10();
				sender.sendMessage(ChatColor.GREEN + v10);
				return true;
			}else if(args[4].equals("11")){
				String v11 = KJVBible2JohnCh1.getV11();
				sender.sendMessage(ChatColor.GREEN + v11);
				return true;
			}else if(args[4].equals("12")){
				String v12 = KJVBible2JohnCh1.getV12();
				sender.sendMessage(ChatColor.GREEN + v12);
				return true;
			}else if(args[4].equals("13")){
				String v13 = KJVBible2JohnCh1.getV13();
				sender.sendMessage(ChatColor.GREEN + v13);
				return true;
			}else if(args[4].equals("?") || args[4].equals("#")){
				String info = KJVBible2JohnCh1.getInfo();
				sender.sendMessage(ChatColor.GREEN + info);
				return true;
			}else{
				String info = KJVBible2JohnCh1.getInfo();
				sender.sendMessage(ChatColor.RED + "Sorry, " + info);
				return true;
			}
		}else{
			sender.sendMessage(ChatColor.RED + "Too many arguments!");
			return true;
		}
	}
}