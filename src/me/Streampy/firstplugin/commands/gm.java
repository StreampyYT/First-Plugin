package me.Streampy.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Streampy.firstplugin.Main;

public class gm implements CommandExecutor {

	public gm(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			Inventory inv = Bukkit.createInventory(null, 9, "Gamemode");
			
			ItemStack item = new ItemStack(Material.APPLE);
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(ChatColor.GREEN + "Survival");
			item.setItemMeta(meta);
			inv.setItem(3, item);
			
			item = new ItemStack(Material.BEDROCK);
			meta.setDisplayName(ChatColor.GOLD + "Creative");
			item.setItemMeta(meta);
			inv.setItem(5, item);
			
			player.openInventory(inv);
			
		}
		return false;
	}

}
