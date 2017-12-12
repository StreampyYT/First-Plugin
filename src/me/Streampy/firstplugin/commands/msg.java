package me.Streampy.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;

public class msg implements CommandExecutor {

	public msg(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length <= 1) {
				player.sendMessage("Type: /msg <speler> <bericht>");
			}else {
				if (args[0].equalsIgnoreCase("console")){
					String bericht = "";
					for (int a = 1; a < args.length; a++ ) {
						bericht += args[a] + " ";
					}
					
					player.sendMessage("[mij > CONSOLE] " + bericht);
					Bukkit.getServer().getLogger().info("[" + player.getName() + " > mij] " + bericht);
					
				}else {
					Player target = Bukkit.getPlayer(args[0]);
					
					if (target == null) {
						player.sendMessage("Speler is niet online");
						return false;
					}
					
					String bericht = "";
					for (int a = 1; a < args.length; a++ ) {
						bericht += args[a] + " ";
					}
					
					player.sendMessage("[mij > " + target.getName() + "] " + bericht);
					target.sendMessage("[" + player.getName() + " > mij] " + bericht);
				}
				
				
			}
		}else {
			//Console
			if (args.length <= 1) {
				sender.sendMessage("Type: /msg <speler> <bericht>");
			}else {
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					sender.sendMessage("Speler is niet online");
					return false;
				}
				
				String bericht = "";
				for (int a = 1; a < args.length; a++ ) {
					bericht += args[a] + " ";
				}
				
				sender.sendMessage("[mij > " + target.getName() + "] " + bericht);
				target.sendMessage("[CONSOLE > mij] " + bericht);
				
			}
			
		}
		return false;
	}

}
