package me.Streampy.firstplugin.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class spawn implements CommandExecutor {

	public spawn(Main main) {
		// TODO Auto-generated constructor stub
	}
	
	File configFile = new File("plugins/firstplugin/config.yml");
	FileConfiguration config = new YamlConfiguration();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (!configFile.exists()) {
				player.sendMessage(ChatColor.RED + "Er is geen config gevonden! Reload de server!");
				return false;
			}
			
			try {
				config.load(configFile);
				World world = Bukkit.getWorld(config.getString("spawn.world"));
				double x = config.getDouble("spawn.x");
				double y = config.getDouble("spawn.y");
				double z = config.getDouble("spawn.z");
				double yaw = config.getDouble("spawn.yaw");
				double pitch = config.getDouble("spawn.pitch");
				Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
				
				player.sendMessage(ChatColor.GOLD + "Je bent naar spawn geteleporteerd!");
				player.teleport(loc);
				
				
			}catch(Exception ex) {
				player.sendMessage(ChatColor.RED + "Oeps daar ging iets fout!");
				Main.Error("Error op regel 39 van try op 35 in Class=spawn");
				ex.printStackTrace();
			}
			
			
		}
		
		return false;
	}

}
