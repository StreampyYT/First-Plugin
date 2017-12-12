package me.Streampy.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class broadcast implements CommandExecutor {

	public broadcast(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
			
			if (args.length == 0) {
				sender.sendMessage("Type: /" + cmd.getName() + " <bericht>");
			}else {
				String bericht = "";
				for (int a = 0; a < args.length; a++) {
					bericht += args[a] + " ";
				}
				
				for(Player playerall : Bukkit.getOnlinePlayers()) {
					playerall.sendMessage("[" + ChatColor.DARK_RED + "Broadcast" + ChatColor.RESET + "] " + ChatColor.RED + bericht);
				}
				
			}
			
			
		
		return false;
	}

}
