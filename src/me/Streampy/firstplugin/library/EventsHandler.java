package me.Streampy.firstplugin.library;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
		Records.userRec userRecords = Records.getUsersRecord(player);
		if (userRecords != null) {
			isfound = true;
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
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		Inventory inv = event.getClickedInventory();
		
		if (inv.getName().equals("Gamemode")) {
			if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) {
				player.closeInventory();
				return;
			}
			player.setGameMode(GameMode.valueOf((ChatColor.stripColor(item.getItemMeta().getDisplayName())).toUpperCase()));
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			Player player = (Player) event.getEntity().getKiller();
			Records.userRec userRecords = Records.getUsersRecord(player);
			if (userRecords != null) {
				int entitykills = userRecords.entitykills + 1;
				userRecords.entitykills = entitykills;
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Records.userRec userRecords = Records.getUsersRecord(player);
		if (userRecords != null) {
			Records.setDeath(player, Records.getDeath(player) + 1);
		}
	}
	
	@EventHandler
	public void onChatRender(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		Records.userRec userRecords = Records.getUsersRecord(player);
		if (userRecords != null) {
			String prefix = ChatColor.translateAlternateColorCodes('&',userRecords.prefix);
			String suffix = ChatColor.translateAlternateColorCodes('&',userRecords.suffix);
			String message = event.getMessage();
			if (player.hasPermission("chatcolor")) {
				message = ChatColor.translateAlternateColorCodes('&', event.getMessage());
			}
			String format = prefix + player.getName() + suffix + ChatColor.RESET + " " + message;
			
			event.setFormat(format);
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
