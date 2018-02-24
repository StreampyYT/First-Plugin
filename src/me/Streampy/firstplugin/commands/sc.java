package me.Streampy.firstplugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import me.Streampy.firstplugin.Main;
import me.Streampy.firstplugin.library.Records;
import me.Streampy.firstplugin.library.Records.userRec;
import net.md_5.bungee.api.ChatColor;

public class sc implements CommandExecutor {

	public sc(Main main) {
		// TODO Auto-generated constructor stub
	}

	static ArrayList<Records.userRec> usersList = Records.usersList;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			for (userRec userRecord : usersList) {
				if (player.getUniqueId().toString().equals(userRecord.uuid)) {
					if (userRecord.scoreboard == true) {
						userRecord.scoreboard = false;
						player.sendMessage(ChatColor.RED + "Jouw scoreboard staat uit!");
						Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
						player.setScoreboard(sc);
					}else {
						userRecord.scoreboard = true;
						player.sendMessage(ChatColor.GREEN + "Jouw scoreboard staat aan!");
					}
					break;
				}
			}
		}
		return false;
	}

}
