package com.gmail.lucario77777777.TBP.commands.KJV.Leviticus;

import com.gmail.lucario77777777.TBP.commands.KJV.bible.Leviticus.KJVBibleLeviticusCh21;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.gmail.lucario77777777.TBP.Main;

public class KJVLeviticusCh21 extends KJVLeviticus {
	public KJVLeviticusCh21(Main plugin) {
		super(plugin);
	}
	
	public static boolean Run(CommandSender sender, String[] args)
	{
		if(args.length <= 3){
			String v1 = KJVBibleLeviticusCh21.getV1();
			sender.sendMessage(ChatColor.GREEN + v1);
			return true;
		}else if(args.length ==4){
			if(args[3].equals("1")){
				String v1 = KJVBibleLeviticusCh21.getV1();
				sender.sendMessage(ChatColor.GREEN + v1);
				return true;
			}else if(args[3].equals("2")){
				String v2 = KJVBibleLeviticusCh21.getV2();
				sender.sendMessage(ChatColor.GREEN + v2);
				return true;
			}else if(args[3].equals("3")){
				String v3 = KJVBibleLeviticusCh21.getV3();
				sender.sendMessage(ChatColor.GREEN + v3);
				return true;
			}else if(args[3].equals("4")){
				String v4 = KJVBibleLeviticusCh21.getV4();
				sender.sendMessage(ChatColor.GREEN + v4);
				return true;
			}else if(args[3].equals("5")){
				String v5 = KJVBibleLeviticusCh21.getV5();
				sender.sendMessage(ChatColor.GREEN + v5);
				return true;
			}else if(args[3].equals("6")){
				String v6 = KJVBibleLeviticusCh21.getV6();
				sender.sendMessage(ChatColor.GREEN + v6);
				return true;
			}else if(args[3].equals("7")){
				String v7 = KJVBibleLeviticusCh21.getV7();
				sender.sendMessage(ChatColor.GREEN + v7);
				return true;
			}else if(args[3].equals("8")){
				String v8 = KJVBibleLeviticusCh21.getV8();
				sender.sendMessage(ChatColor.GREEN + v8);
				return true;
			}else if(args[3].equals("9")){
				String v9 = KJVBibleLeviticusCh21.getV9();
				sender.sendMessage(ChatColor.GREEN + v9);
				return true;
			}else if(args[3].equals("10")){
				String v10 = KJVBibleLeviticusCh21.getV10();
				sender.sendMessage(ChatColor.GREEN + v10);
				return true;
			}else if(args[3].equals("11")){
				String v11 = KJVBibleLeviticusCh21.getV11();
				sender.sendMessage(ChatColor.GREEN + v11);
				return true;
			}else if(args[3].equals("12")){
				String v12 = KJVBibleLeviticusCh21.getV12();
				sender.sendMessage(ChatColor.GREEN + v12);
				return true;
			}else if(args[3].equals("13")){
				String v13 = KJVBibleLeviticusCh21.getV13();
				sender.sendMessage(ChatColor.GREEN + v13);
				return true;
			}else if(args[3].equals("14")){
				String v14 = KJVBibleLeviticusCh21.getV14();
				sender.sendMessage(ChatColor.GREEN + v14);
				return true;
			}else if(args[3].equals("15")){
				String v15 = KJVBibleLeviticusCh21.getV15();
				sender.sendMessage(ChatColor.GREEN + v15);
				return true;
			}else if(args[3].equals("16")){
				String v16 = KJVBibleLeviticusCh21.getV16();
				sender.sendMessage(ChatColor.GREEN + v16);
				return true;
			}else if(args[3].equals("17")){
				String v17 = KJVBibleLeviticusCh21.getV17();
				sender.sendMessage(ChatColor.GREEN + v17);
				return true;
			}else if(args[3].equals("18")){
				String v18 = KJVBibleLeviticusCh21.getV18();
				sender.sendMessage(ChatColor.GREEN + v18);
				return true;
			}else if(args[3].equals("19")){
				String v19 = KJVBibleLeviticusCh21.getV19();
				sender.sendMessage(ChatColor.GREEN + v19);
				return true;
			}else if(args[3].equals("20")){
				String v20 = KJVBibleLeviticusCh21.getV20();
				sender.sendMessage(ChatColor.GREEN + v20);
				return true;
			}else if(args[3].equals("21")){
				String v21 = KJVBibleLeviticusCh21.getV21();
				sender.sendMessage(ChatColor.GREEN + v21);
				return true;
			}else if(args[3].equals("22")){
				String v22 = KJVBibleLeviticusCh21.getV22();
				sender.sendMessage(ChatColor.GREEN + v22);
				return true;
			}else if(args[3].equals("23")){
				String v23 = KJVBibleLeviticusCh21.getV23();
				sender.sendMessage(ChatColor.GREEN + v23);
				return true;
			}else if(args[3].equals("24")){
				String v24 = KJVBibleLeviticusCh21.getV24();
				sender.sendMessage(ChatColor.GREEN + v24);
				return true;
			}else if(args[3].equals("?") || args[3].equals("#")){
				String info = KJVBibleLeviticusCh21.getInfo();
				sender.sendMessage(ChatColor.GREEN + info);
				return true;
			}else{
				String info = KJVBibleLeviticusCh21.getInfo();
				sender.sendMessage(ChatColor.RED + "Sorry, " + info);
				return true;
			}
		}else{
			sender.sendMessage(ChatColor.RED + "Too many arguments!");
			return true;
		}
	}
}