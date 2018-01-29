package me.Streampy.firstplugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import me.Streampy.firstplugin.library.Records;
import net.md_5.bungee.api.ChatColor;

public class setprefix implements CommandExecutor {

	public setprefix(Main main) {
		// TODO Auto-generated constructor stub
	}
	
	static ArrayList<Records.userRec> usersList = Records.usersList;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				
				for (int a = 0; a < usersList.size(); a++) {
					Records.userRec userRecord = usersList.get(a);
					if (userRecord.uuid.equals(player.getUniqueId().toString())) {
						userRecord.prefix = args[0];
						player.sendMessage(ChatColor.GOLD + "Je prefix is nu " + ChatColor.translateAlternateColorCodes('&', args[0]) + ChatColor.GOLD + "!");
						return false;
					}
				}
			}else if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[0]);
				
				for (int a = 0; a < usersList.size(); a++) {
					Records.userRec userRecord = usersList.get(a);
					if (userRecord.uuid.equals(target.getUniqueId().toString())) {
						userRecord.prefix = args[1];
						player.sendMessage(ChatColor.GOLD + "Je hebt " + ChatColor.GREEN +  args[0] + ChatColor.GOLD + " prefix verandert naar " + ChatColor.translateAlternateColorCodes('&', args[1]) + ChatColor.GOLD + "!");
						return false;
					}
				}
			}
		}
		
		
		return false;
	}

}
