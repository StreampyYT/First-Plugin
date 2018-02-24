package me.Streampy.firstplugin;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.Streampy.firstplugin.commands.broadcast;
import me.Streampy.firstplugin.commands.fly;
import me.Streampy.firstplugin.commands.gm;
import me.Streampy.firstplugin.commands.hallo;
import me.Streampy.firstplugin.commands.home;
import me.Streampy.firstplugin.commands.info;
import me.Streampy.firstplugin.commands.jail;
import me.Streampy.firstplugin.commands.msg;
import me.Streampy.firstplugin.commands.rename;
import me.Streampy.firstplugin.commands.sc;
import me.Streampy.firstplugin.commands.sethome;
import me.Streampy.firstplugin.commands.setprefix;
import me.Streampy.firstplugin.commands.setspawn;
import me.Streampy.firstplugin.commands.setsuffix;
import me.Streampy.firstplugin.commands.spawn;
import me.Streampy.firstplugin.commands.tpa;
import me.Streampy.firstplugin.commands.tpaccept;
import me.Streampy.firstplugin.commands.tpdeny;
import me.Streampy.firstplugin.commands.tptoggle;
import me.Streampy.firstplugin.library.EventsHandler;
import me.Streampy.firstplugin.library.Records;
import me.Streampy.firstplugin.library.Records.userRec;
import me.Streampy.firstplugin.library.Strings;


public class Main extends JavaPlugin {
	
	File firstplugin = new File("plugins/firstplugin");
	
	File configFile = new File("plugins/firstplugin/config.yml");
	FileConfiguration config = new YamlConfiguration();
	
	File userFile = new File("plugins/firstplugin/users.yml");
	FileConfiguration user = new YamlConfiguration();
	
	static ArrayList<Records.userRec> usersList = Records.usersList;
	
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
		
		loadusers();
		
		Info("FirstPlugin is aan");
		Info("Eigenaar: Streampy");
		Info("Veel succes!");
		
		getCommand("hallo").setExecutor(new hallo(this));
		getCommand("fly").setExecutor(new fly(this));
		getCommand("msg").setExecutor(new msg(this));
		getCommand("broadcast").setExecutor(new broadcast(this));
		getCommand("bc").setExecutor(new broadcast(this));
		getCommand("tpa").setExecutor(new tpa(this));
		getCommand("tpaccept").setExecutor(new tpaccept(this));
		getCommand("tpdeny").setExecutor(new tpdeny(this));
		getCommand("tptoggle").setExecutor(new tptoggle(this));
		getCommand("jail").setExecutor(new jail(this));
		getCommand("setspawn").setExecutor(new setspawn(this));
		getCommand("spawn").setExecutor(new spawn(this));
		getCommand("sethome").setExecutor(new sethome (this));
		getCommand("home").setExecutor(new home(this));
		getCommand("rename").setExecutor(new rename(this));
		getCommand("info").setExecutor(new info(this));
		getCommand("setprefix").setExecutor(new setprefix(this));
		getCommand("setsuffix").setExecutor(new setsuffix(this));
		getCommand("sc").setExecutor(new sc(this));
		getCommand("gm").setExecutor(new gm(this));

		new EventsHandler(this);
		updateScoreboard();
	}
	
	public void onDisable() {
		saveusers();
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
	
	public void updateScoreboard() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), new Runnable() {

			@Override
			public void run() {
				for (Player playerall : Bukkit.getOnlinePlayers()) {					
					for (userRec userRecord : usersList) {
						if (playerall.getUniqueId().toString().equals(userRecord.uuid)) {
							if (userRecord.scoreboard == true) {
								Scoreboard sc = Bukkit.getScoreboardManager().getNewScoreboard();
								Objective obj = sc.registerNewObjective("scoreboard", "dummy");
								obj.setDisplaySlot(DisplaySlot.SIDEBAR);
								obj.setDisplayName(ChatColor.AQUA + "Jouw Scoreboard");
								Score EntityKills = obj.getScore(ChatColor.GOLD + "EntityKills: " + ChatColor.GREEN + userRecord.entitykills);
								EntityKills.setScore(1);
								Score Deaths = obj.getScore(ChatColor.GOLD + "Deaths: " + ChatColor.GREEN + userRecord.death);
								Deaths.setScore(0);
								playerall.setScoreboard(sc);
								
							}
							break;
						}
					}	
				}
			}
		}, 0, 20);
	}
	
	public void loadusers() {
		if (userFile.exists()) {
			try {
				user.load(userFile);
				for (int a = 0; user.contains("user." + a); a++) {
					Records.userRec userRecord = new Records.userRec();
					
					usersList.add(userRecord);
					userRecord.uuid = user.getString("user." + a + ".id");
					userRecord.death = user.getInt("user." + a + ".death");
					userRecord.entitykills = user.getInt("user." + a + ".entitykills");
					if (user.contains("user." + a + ".prefix")) {
						userRecord.prefix = user.getString("user." + a + ".prefix");
					}else {
						userRecord.prefix = "<";
					}
					if (user.contains("user." + a + ".suffix")) {
						userRecord.suffix = user.getString("user." + a + ".suffix");
					}else {
						userRecord.suffix = ">";
					}
					userRecord.scoreboard = user.getBoolean("user." + a + ".scoreboard");
				}
				
			}catch(Exception ex) {
				Error("Hier ging iets fout bij het laden van de users file");
				ex.printStackTrace();
			}
		}
	}
	
	public void saveusers() {
		if (!userFile.exists()) {
			try {
				userFile.createNewFile();
			}catch(Exception ex) {
				Error("Hier ging iets fout bij het maken van de users file");
				ex.printStackTrace();
			}
		}
		
		try {
			for (int a = 0; a < usersList.size(); a++) {
				Records.userRec userRecord = usersList.get(a);
				user.load(userFile);
				int death = userRecord.death + 0;
				int entitykills = userRecord.entitykills + 0;
				
				user.set("user." + a + ".id", userRecord.uuid);
				user.set("user." + a + ".death", death);
				user.set("user." + a + ".entitykills", entitykills);
				user.set("user." + a + ".prefix", userRecord.prefix);
				user.set("user." + a + ".suffix", userRecord.suffix);
				user.set("user." + a + ".scoreboard", userRecord.scoreboard);
				user.save(userFile);
			}
		}catch(Exception ex) {
			Error("Hier ging iets fout bij het laden van de users file");
			ex.printStackTrace();
		}
		
	}

}
