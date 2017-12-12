package me.Streampy.firstplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;

public class hallo implements CommandExecutor {

	public hallo(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			player.sendMessage("Hallo speler " + player.getName());
			player.chat("Hallo Command");
			
		}else {
			
			sender.sendMessage("Hallo ander object als speler");
			
		}
		return false;
	}

}
