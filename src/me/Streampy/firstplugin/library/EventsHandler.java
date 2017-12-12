package me.Streampy.firstplugin.library;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
		
		player.sendMessage(ChatColor.GOLD + "Heb veel speel plezier op onze server.");
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(ChatColor.GOLD + "De awesome speler " + ChatColor.RED + player.getName() + ChatColor.GOLD + " heeft de game verlaten. :(");
	}
	
}
