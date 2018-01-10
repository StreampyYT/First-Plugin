package me.Streampy.firstplugin.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class setspawn implements CommandExecutor {

	File configFile = new File("plugins/firstplugin/config.yml");
	FileConfiguration config = new YamlConfiguration();
	
	public setspawn(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (!configFile.exists()) {
				player.sendMessage(ChatColor.RED + "Er is geen config gevonden! Reload de server!");
				return false;
			}
			
			String world = player.getLocation().getWorld().getName();
			double x = player.getLocation().getX();
			double y = player.getLocation().getY();
			double z = player.getLocation().getZ();
			float yaw = player.getLocation().getYaw();
			float pitch = player.getLocation().getPitch();
			
			try {
				config.load(configFile);
				config.set("spawn.world", world);
				config.set("spawn.x", x);
				config.set("spawn.y", y);
				config.set("spawn.z", z);
				config.set("spawn.yaw", yaw);
				config.set("spawn.pitch", pitch);
				config.save(configFile);
				player.sendMessage(ChatColor.GOLD + "De spawn is gezet op jouw locatie!");
				
			}catch(Exception ex) {
				player.sendMessage(ChatColor.RED + "Oeps daar ging iets fout");
				Main.Error("Error op regel 53 van try op 42 in Class=setspawn");
				ex.printStackTrace();
			}
			
		}
		
		return false;
	}

}
