package me.Streampy.firstplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.Streampy.firstplugin.commands.broadcast;
import me.Streampy.firstplugin.commands.fly;
import me.Streampy.firstplugin.commands.hallo;
import me.Streampy.firstplugin.commands.jail;
import me.Streampy.firstplugin.commands.msg;
import me.Streampy.firstplugin.commands.tpa;
import me.Streampy.firstplugin.commands.tpaccept;
import me.Streampy.firstplugin.commands.tpdeny;
import me.Streampy.firstplugin.commands.tptoggle;
import me.Streampy.firstplugin.library.EventsHandler;

public class Main extends JavaPlugin {

	public void onEnable() {
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
		
		new EventsHandler(this);
	}
	
	public void Debug(String message) {
		Bukkit.getServer().getLogger().info("[FirstPlugin][Debug] " + message);
	}
	
	public void Info(String message) {
		Bukkit.getServer().getLogger().info("[FirstPlugin][Info] " + message);
	}
	
	public void Error(String message) {
		Bukkit.getServer().getLogger().info("[FirstPlugin][Error] " + message);
	}

}
