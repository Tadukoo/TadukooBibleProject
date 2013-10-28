package com.gmail.lucario77777777.TBP.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

import com.gmail.lucario77777777.TBP.Main;

public class MainCommandExecutor implements CommandExecutor {
	private Main plugin;
	public MainCommandExecutor(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final String playerType;
		if (sender instanceof Player){
			playerType = "player";
		}else if(sender instanceof ConsoleCommandSender){
			playerType = "console";
		}else if(sender instanceof BlockCommandSender){
			playerType = "block";
		}else{
			playerType = "unknown";
		}
		Boolean permsOn = plugin.perms;
		if(cmd.getName().equalsIgnoreCase("bible")){
			if(playerType == "block" || playerType == "unknown"){
				sender.sendMessage(ChatColor.RED + "Unknown sender!");
				return true;
			}else{
				EnumBooks book = EnumBooks.GENESIS;
				EnumCmds cmds = EnumCmds.HELP;
				String bookName = "Genesis";
				String chp = "1";
				String v = "1";
				String tran = plugin.getConfig().getString("DefaultTranslation").toUpperCase();
				String ref = null;
				String verse = null;
				String type = null;
				String i = "1";
				if(args.length >= 1){
					if(book.fromString(args[0].toUpperCase()) != null){
						book = book.fromString(args[0].toUpperCase());
						type = "book";
					}else if(cmds.fromString(args[0].toUpperCase()) != null){
						cmds = cmds.fromString(args[0].toUpperCase());
						type = "cmd";
					}else{
						sender.sendMessage(ChatColor.RED + "Sorry, that book/command does not exist.");
						return true;
					}
				}
				if(type == "book"){
					if(book.isAvailable() == false){
						sender.sendMessage(ChatColor.RED + "Sorry, " + book.getBook() + 
								" is not available yet.");
						return true;
					}
					if(args.length >= 2){
						chp = args[1];
						if(args.length >= 3){
							v = args[2];
							if(args.length >= 4){
								tran = args[3].toUpperCase();
							}
						}
					}
					bookName = book.getBook();
					if(tranCheck(plugin, sender, tran) == false){
						return true;
					}
				}else if(type == "cmd"){
					if(cmds.isAvailable() == false){
						sender.sendMessage(ChatColor.RED + "Sorry, " + cmds.getCmd() + " is not available yet.");
						return true;
					}
					String cmdType = cmds.getCmd();
					if(cmdType.equalsIgnoreCase("first") || cmdType.equalsIgnoreCase("second") ||
							cmdType.equalsIgnoreCase("third")){
						tran = "all";
						bookName = "BibleConfig";
						ref = cmdType.toLowerCase();
					}else if(cmdType.equalsIgnoreCase("help")){
						if(args.length >= 2){
							i = args[1];
						}
						if(permCheck(permsOn, playerType, sender, "help") == false){
							return true;
						}
						Help.Run(i, sender, plugin);
						return true;
					}else if(cmdType.equalsIgnoreCase("info")){
						if(args.length < 2){
							sender.sendMessage(ChatColor.RED + "Not enough arguments!");
							sender.sendMessage(ChatColor.RED + "/bible about|info|abt|information " +
									"<translation>");
							return true;
						}
						if(permCheck(permsOn, playerType, sender, "info") == false){
							return true;
						}
						tran = args[1].toUpperCase();
						bookName = tran;
						ref = "info";
						if(tranCheck(plugin, sender, tran) == false){
							return true;
						}
					}else if(cmdType.equalsIgnoreCase("books")){
						if(args.length >= 2){
							i = args[1];
						}
						if(permCheck(permsOn, playerType, sender, "books") == false){
							return true;
						}
						BooksList.list(i, sender);
						return true;
					}else if(cmdType.equalsIgnoreCase("translations")){
						if(permCheck(permsOn, playerType, sender, "translations") == false){
							return true;
						}
						Translations.Run(sender, plugin);
						return true;
					}else if(cmdType.equalsIgnoreCase("getBook")){
						if(permCheck(permsOn, playerType, sender, "getbook") == false){
							return true;
						}
						bookName = chp;
						String part = v;
						if(args.length >= 2){
							bookName = args[1];
							if(args.length >= 3){
								part = args[2];
								if(args.length >= 4){
									tran = args[3].toUpperCase();
									if(tranCheck(plugin, sender, tran) == false){
										return true;
									}
								}
							}
						}
						Book.Run(plugin, sender, playerType, tran, bookName, part);
						return true;
					}
				}
				if(plugin.getBook(tran, bookName) == null){
					sender.sendMessage(ChatColor.RED + "Sorry, " + tran + "/" + bookName 
							+ ".yml does not exist.");
					return true;
				}
				if(chp.equalsIgnoreCase("info") || chp.equalsIgnoreCase("?")){
					ref = book.getAlias() + "Info";
				}
				if(chp.equalsIgnoreCase("#")){
					ref = book.getAlias() + "#";
				}
				if(v.equalsIgnoreCase("#") || v.equalsIgnoreCase("?") || v.equalsIgnoreCase("info")){
					v = "info";
				}
				if(ref == null){
					ref = "ch" + chp + "v" + v;
				}
				if(plugin.getBook(tran, bookName).getString(ref) == null){
					sender.sendMessage(ChatColor.RED + "An error occurred. Please make sure you typed in a " +
							"chapter/verse that exists.");
					return true;
				}
				if(plugin.getBook(tran, bookName).getString(ref) == null){
					sender.sendMessage(ChatColor.RED + "An error occurred.");
					return true;
				}
				if(verse == null){
					verse = plugin.getBook(tran, bookName).getString(ref);
				}
				sender.sendMessage(ChatColor.GREEN + verse);
				return true;
			}
		}
		return false;
	}
	
	private static boolean tranCheck(Main plugin, CommandSender sender, String tran) {
		if(plugin.getConfig().getString(tran) == null || plugin.getConfig().getBoolean(tran) == false){
			sender.sendMessage(ChatColor.RED + "Sorry, that translation is not available.");
			return false;
		}else{
			return true;
		}
	}
	
	private static boolean permCheck(boolean permsOn, String playerType, CommandSender sender, String perm){
		if(permsOn == true && playerType == "player"){
			if(Permissions.check(sender, perm) == true){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
