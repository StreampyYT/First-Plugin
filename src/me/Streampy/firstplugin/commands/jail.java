package me.Streampy.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Streampy.firstplugin.Main;

public class jail implements CommandExecutor {

	public jail(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 0) {
			sender.sendMessage("Gebruik /jail <speler>");
		}else if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			
			if (target != null) {
				World world = target.getWorld();
				int x = target.getLocation().getBlockX();
				int y = target.getLocation().getBlockY();
				int z = target.getLocation().getBlockZ();
				//Onder layer
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y - 1, z)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y - 1, z)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y - 1, z)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y - 1, z + 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y - 1, z - 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y - 1, z + 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y - 1, z - 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y - 1, z - 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y - 1, z + 1)).setType(Material.BEDROCK);
				
				//bars layer
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 0, z)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 0, z)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 0, z + 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 0, z - 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 0, z + 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 0, z - 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 0, z - 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 0, z + 1)).setType(Material.IRON_FENCE);
				
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 1, z)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 1, z)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 1, z + 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 1, z - 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 1, z + 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 1, z - 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 1, z - 1)).setType(Material.IRON_FENCE);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 1, z + 1)).setType(Material.IRON_FENCE);
				
				
				//Boven layer
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 2, z)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 2, z)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 2, z)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 2, z + 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x, y + 2, z - 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 2, z + 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 2, z - 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x + 1, y + 2, z - 1)).setType(Material.BEDROCK);
				Bukkit.getWorld(target.getWorld().getName()).getBlockAt(new Location(world, x - 1, y + 2, z + 1)).setType(Material.BEDROCK);
				
				sender.sendMessage("Jij hebt " + target.getName() + " gejailed!");
			}else {
				sender.sendMessage("Speler is niet online!");
			}
		}
		return false;
	}

}
