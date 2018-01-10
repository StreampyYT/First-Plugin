package me.Streampy.firstplugin;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.Streampy.firstplugin.commands.broadcast;
import me.Streampy.firstplugin.commands.fly;
import me.Streampy.firstplugin.commands.hallo;
import me.Streampy.firstplugin.commands.home;
import me.Streampy.firstplugin.commands.jail;
import me.Streampy.firstplugin.commands.msg;
import me.Streampy.firstplugin.commands.sethome;
import me.Streampy.firstplugin.commands.setspawn;
import me.Streampy.firstplugin.commands.spawn;
import me.Streampy.firstplugin.commands.tpa;
import me.Streampy.firstplugin.commands.tpaccept;
import me.Streampy.firstplugin.commands.tpdeny;
import me.Streampy.firstplugin.commands.tptoggle;
import me.Streampy.firstplugin.library.EventsHandler;
import me.Streampy.firstplugin.library.Strings;

public class Main extends JavaPlugin {
	
	File firstplugin = new File("plugins/firstplugin");
	
	File configFile = new File("plugins/firstplugin/config.yml");
	FileConfiguration config = new YamlConfiguration();
	
	public void onEnable() {
		
		if (!firstplugin.exists()) {
			firstplugin.mkdir();
		}
		
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				config.load(configFile);
				config.set("join_message", "Heb veel speel plezier op onze server.");
				config.save(configFile);
			}catch(Exception ex) {
				Error("Error op regel 43 van try op 37 in Class=Main");
				ex.printStackTrace();
			}
		}
		
		try {
			config.load(configFile);
			if (config.get("join_message") == null || config.get("join_message").equals("")) {
				config.set("join_message", "Heb veel speel plezier op onze server.");
				config.save(configFile);
			}
			config.load(configFile);
			Strings.join_message = config.getString("join_message");
		}catch(Exception ex) {
			Error("Error op regel 57 van try op 48 in Class=Main");
			ex.printStackTrace();
		}
		
		Info("FirstPlugin is aan");
		Info("Eigenaar: Streampy");
		Info("Veel succes!");
		
		getCommand("hallo").setExecutor(new hallo(this));
		getCommand("fly").setExecutor(new fly(this));
		getCommand("msg").setExecutor(new msg(this));
		getCommand("broadcast").setExecutor(new broadcast(this));
		getCommand("bc").setExecutor(new broadcast(this));
		getCommand("tpa").setExecutor(new tpa (this));
		getCommand("tpaccept").setExecutor(new tpaccept (this));
		getCommand("tpdeny").setExecutor(new tpdeny(this));
		getCommand("tptoggle").setExecutor(new tptoggle(this));
		getCommand("jail").setExecutor(new jail(this));
		getCommand("setspawn").setExecutor(new setspawn(this));
		getCommand("spawn").setExecutor(new spawn(this));
		getCommand("sethome").setExecutor(new sethome (this));
		getCommand("home").setExecutor(new home (this));
		
		new EventsHandler(this);
	}
	
	public static void Debug(String message) {
		Bukkit.getServer().getLogger().info("[FirstPlugin][Debug] " + message);
	}
	
	public static void Info(String message) {
		Bukkit.getServer().getLogger().info("[FirstPlugin][Info] " + message);
	}
	
	public static void Error(String message) {
		Bukkit.getServer().getLogger().info("[FirstPlugin][Error] " + message);
	}

}
