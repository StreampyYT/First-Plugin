package me.Streampy.firstplugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import me.Streampy.firstplugin.library.Records;

public class info implements CommandExecutor {

	public info(Main main) {
		// TODO Auto-generated constructor stub
	}

	static ArrayList<Records.userRec> usersList = Records.usersList;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				for (int a = 0; a < usersList.size(); a++) {
					Records.userRec userRecord = usersList.get(a);
					if (player.getUniqueId().toString().equals(userRecord.uuid)){
						player.sendMessage("death: " + userRecord.death);
						player.sendMessage("entitykills: " + userRecord.entitykills);
						return false;
					}
					
				}
			}else if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				for (int a = 0; a < usersList.size(); a++) {
					Records.userRec userRecord = usersList.get(a);
					if (target.getUniqueId().toString().equals(userRecord.uuid)){
						player.sendMessage("player: " + target.getName());
						player.sendMessage("death: " + userRecord.death);
						player.sendMessage("entitykills: " + userRecord.entitykills);
						return false;
					}
					
				}
				
			}
		}
		
		return false;
	}

}
