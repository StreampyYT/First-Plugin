package me.Streampy.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class tpdeny implements CommandExecutor {

	public tpdeny(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			int num = 0;
			
			for (int a = 0; a <  tpa.tp.size(); a++) {
				
				String[] users = tpa.tp.get(a).split(">");
				
				Player stuur = Bukkit.getPlayer(users[0]);
				Player krijgt = Bukkit.getPlayer(users[1]);
				
				if (krijgt == player) {
					num = 1;
					tpa.tp.remove(a);
					stuur.sendMessage(ChatColor.RED + "Jouw teleport naar" + ChatColor.GREEN + krijgt.getName() + ChatColor.RED + " is gestopt");
				}
				
			}
			
			if (num == 1) {
				player.sendMessage(ChatColor.GOLD + "Al jouw request zijn gestopt!");
				return false;
			}
			player.sendMessage("Er zijn geen request gevonden!");
			
		}
		return false;
	}

}
