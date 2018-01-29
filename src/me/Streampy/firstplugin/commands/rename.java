package me.Streampy.firstplugin.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class rename implements CommandExecutor {

	public rename(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if (args.length == 0) {
				player.sendMessage(ChatColor.GOLD + "gebruik /rename <naam van je item>");
			}else {
				if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
					player.sendMessage(ChatColor.RED + "Hou een item in je hand als je deze command gebruik!");
					return false;
				}
				
				String fullname = args[0];
				for (int a = 1; a < args.length; a++) {
					fullname = fullname + " " + args[a];
				}
				
				ItemStack item = player.getInventory().getItemInMainHand();
				ItemMeta itemMeta = item.getItemMeta();
				
				ArrayList<String> lore = new ArrayList<String>();
				
				lore.add(ChatColor.GRAY + "Dit item is gerenamed door firstplugin");
				lore.add(ChatColor.GRAY + "firstplugin is gemaakt door Streampy");
				
				itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', fullname));
				itemMeta.setLore(lore);
	
				player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
				
				player.sendMessage(ChatColor.GREEN + "Jouw item is gerenamed!");
			}
		}

		return false;
	}

}
