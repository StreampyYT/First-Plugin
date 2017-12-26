package me.Streampy.firstplugin.library;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Streampy.firstplugin.Main;

public class EventsHandler implements Listener {

	public EventsHandler(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		event.setJoinMessage(ChatColor.GOLD + "De awesome speler " + ChatColor.GREEN + player.getName() + ChatColor.GOLD + " heeft de game gejoined.");
		
		
		player.sendMessage(ChatColor.GOLD + Strings.join_message);
		
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
