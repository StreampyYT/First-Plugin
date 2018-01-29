package me.Streampy.firstplugin.library;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Streampy.firstplugin.Main;

public class EventsHandler implements Listener {

	static ArrayList<Records.userRec> usersList = Records.usersList;
	
	public EventsHandler(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		event.setJoinMessage(ChatColor.GOLD + "De awesome speler " + ChatColor.GREEN + player.getName() + ChatColor.GOLD + " heeft de game gejoined.");
		player.sendMessage(ChatColor.GOLD + Strings.join_message);
		boolean isfound = false;
		for (int a = 0; a < usersList.size(); a++) {
			Records.userRec userRecord = usersList.get(a);
			if (userRecord.uuid.equals(player.getUniqueId().toString())) {
				isfound = true;
			}
		}
		
		if (isfound == false) {
			Records.userRec newUserRecord = new Records.userRec();
			
			usersList.add(newUserRecord);
			newUserRecord.uuid = player.getUniqueId().toString();
			newUserRecord.death = 0;
			newUserRecord.entitykills = 0;
		}
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			for (int a = 0; a < usersList.size(); a++) {
				Player player = (Player) event.getEntity().getKiller();
				Records.userRec userRecord = usersList.get(a);
				if (userRecord.uuid.equals(player.getUniqueId().toString())) {
					int entitykills = userRecord.entitykills + 1;
					userRecord.entitykills = entitykills;
				}
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		for (int a = 0; a < usersList.size(); a++) {
			Player player = event.getEntity();
			Records.userRec userRecord = usersList.get(a);
			if (userRecord.uuid.equals(player.getUniqueId().toString())) {
				int death = userRecord.death + 1;
				userRecord.death = death;
			}
		}
	}
	
	@EventHandler
	public void onChatRender(AsyncPlayerChatEvent event) {
		for (int a = 0; a < usersList.size(); a++) {
			Player player = event.getPlayer();
			Records.userRec userRecord = usersList.get(a);
			if (userRecord.uuid.equals(player.getUniqueId().toString())) {
				String prefix = ChatColor.translateAlternateColorCodes('&',userRecord.prefix);
				String suffix = ChatColor.translateAlternateColorCodes('&',userRecord.suffix);
				String message = event.getMessage();
				if (player.hasPermission("chatcolor")) {
					message = ChatColor.translateAlternateColorCodes('&', event.getMessage());
				}
				String format = prefix + player.getName() + suffix + ChatColor.RESET + " " + message;
				
				event.setFormat(format);
				return;
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(ChatColor.GOLD + "De awesome speler " + ChatColor.RED + player.getName() + ChatColor.GOLD + " heeft de game verlaten. :(");
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		player.sendMessage(ChatColor.GOLD + "Je hebt " + ChatColor.GREEN + block.getType() + ChatColor.GOLD + " geplaatst!");
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		player.sendMessage(ChatColor.GOLD + "Je hebt " + ChatColor.RED + block.getType() + ChatColor.GOLD + " gesloopt!");
	}
	
	@EventHandler
	public void onPlayerEnterBed(PlayerBedEnterEvent event) {
		Block block = event.getBed();
		
		Bukkit.getServer().getWorld(block.getWorld().getName()).createExplosion(block.getLocation(), 10);
	}
	
	@EventHandler
	public void onBreed(EntityBreedEvent event) {
		event.setExperience(100);
		event.setCancelled(true);
		
	}
	
}
