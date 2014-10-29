package com.gmail.realtadukoo.TBP.commands.handling;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

import com.gmail.realtadukoo.TBP.TB;
import com.gmail.realtadukoo.TBP.Enums.EnumCmds;
import com.gmail.realtadukoo.TBP.commands.apocrypha.args.ApoHelp;
import com.gmail.realtadukoo.TBP.commands.args.*;

public class BibleCommandExec implements CommandExecutor {
	private static TB plugin;
	private static boolean permsOn;
	public BibleCommandExec(TB plugin, boolean permsOn) {
		BibleCommandExec.plugin = plugin;
		BibleCommandExec.permsOn = permsOn;
	}
	
	public static boolean onCommand(CommandSender sender, String cmd, String[] args, String playerType) {
		EnumCmds cmds = EnumCmds.READ;
		
		String unknownSender = plugin.getLanguage().getString("command.error.unknownsender");
		String notAvailable = plugin.getLanguage().getString("command.error.commandnotavailable");
		String doesntExist = plugin.getLanguage().getString("command.error.commanddoesntexist");
		String help = plugin.getLanguage().getString("command.error.help");
		
		if((cmd.equalsIgnoreCase("bible")) && 
				Checks.permCheck(playerType, plugin, sender, "Bible", "use", permsOn)){
			String type = "Bible";
			if(playerType == "block" || playerType == "unknown"){
				//Don't yet know how to handle command block and unknown senders.
				sender.sendMessage(ChatColor.RED + unknownSender);
				return true;
			}else{
				if(args.length >= 1){
					if(Args.isCmd(cmds, args[0]) != null){
						cmds = Args.isCmd(cmds, args[0]);
						if(!cmds.isAvailable(type)){
							notAvailable = notAvailable.replaceAll("\\{cmd\\}", cmds.getCmd());
							notAvailable = notAvailable.replaceAll("\\{type\\}", type);
							sender.sendMessage(ChatColor.RED + notAvailable);
							return true;
						}
					}
				}
				String cmdType = cmds.getCmd();
				String cmdPerm = cmds.getPerm();
				if(Checks.permCheck(playerType, plugin, sender, type, cmdPerm, permsOn)){
					if(cmdType.equalsIgnoreCase("read")){
						Read.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("send")){
						Send.run(plugin, playerType, sender, args, permsOn);
						return true;
					}else if(cmdType.equalsIgnoreCase("previous")){
						Previous.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("next")){
						Next.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("last")){
						Last.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("random")){
						RandomCmd.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("favorite")){
						Favorite.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("getbook") && 
							Checks.consoleCheck(plugin, sender, playerType)){
						Getbook.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("sendbook")){
						Sendbook.run(plugin, playerType, sender, args, permsOn);
						return true;
					}else if(cmdType.equalsIgnoreCase("info")){
						Info.run(plugin, sender, args, playerType, permsOn);
						return true;
					}else if(cmdType.equalsIgnoreCase("help")){
						Help.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("plugin")){
						Plugin.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("books")){
						Books.run(sender, plugin, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("translation")){
						Translation.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("block")){
						Block.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("receive")){
						Receive.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("config")){
						Config.run(plugin, sender, args);
						return true;
					}else if(cmdType.equalsIgnoreCase("announce")){
						Announce.run(plugin, sender, args);
						return true;
					}
				}
			}
		}else if(cmd.equalsIgnoreCase("apocrypha") && 
				Checks.permCheck(playerType, plugin, sender, "Apocrypha", "use", permsOn)){
			String type = "Apocrypha";
			if(playerType == "block" || playerType == "unknown"){
				sender.sendMessage(ChatColor.RED + unknownSender);
				return true;
			}else{
				if(args.length >= 1){
					if(Args.isCmd(cmds, args[0]) != null){
						cmds = Args.isCmd(cmds, args[0]);
						if(!cmds.isAvailable(type)){
							notAvailable = notAvailable.replaceAll("\\{cmd\\}", cmds.getCmd());
							notAvailable = notAvailable.replaceAll("\\{type\\}", type);
							sender.sendMessage(ChatColor.RED + notAvailable);
							return true;
						}
					}else{
						help = help.replaceAll("\\{type\\}", type);
						sender.sendMessage(ChatColor.RED + doesntExist);
						sender.sendMessage(ChatColor.RED + help);
						return true;
					}
				}
				String cmdType = cmds.getCmd();
				String cmdPerm = cmds.getPerm();
				if(Checks.permCheck(playerType, plugin, sender, type, cmdPerm, permsOn)){
					if(cmdType.equalsIgnoreCase("help")){
						ApoHelp.run(plugin, sender, args);
						return true;
					}
				}
			}
		}
		return false;
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
		
		if(cmd.getName().equalsIgnoreCase("bible")){
			onCommand(sender, "bible", args, playerType);
			return true;
		}else if(cmd.getName().equalsIgnoreCase("apocrypha")){
			onCommand(sender, "apocrypha", args, playerType);
			return true;
		}else{
			return false;
		}
	}
}