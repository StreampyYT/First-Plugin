package me.Streampy.firstplugin.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class tptoggle implements CommandExecutor {

	public tptoggle(Main main) {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<String> tptogglelist = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			for (int a = 0; a < tptogglelist.size(); a++) {
				if (tptogglelist.get(a).equals(player.getName())) {
					tptogglelist.remove(a);
					player.sendMessage(ChatColor.GREEN + "Mensen kunnen weer naar je teleporteren!");
					return false;
				}
			}
			tptogglelist.add(player.getName());
			player.sendMessage(ChatColor.RED + "Mensen kunnen niet meer naar je teleporteren!");
			
		}
		return false;
	}

}
