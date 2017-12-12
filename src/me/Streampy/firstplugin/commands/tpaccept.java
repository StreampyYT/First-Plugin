package me.Streampy.firstplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class tpaccept implements CommandExecutor {

	public int times = 0;
	int schedular = 1;
	
	public tpaccept(Main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			for (int a = 0; a <  tpa.tp.size(); a++) {
				final int num = a;
				String[] users = tpa.tp.get(a).split(">");
				
				Player player = Bukkit.getPlayer(users[0]);
				Player target = Bukkit.getPlayer(users[1]);
				
				if (player != null) {
					Location playerloc = player.getLocation();
					int x = playerloc.getBlockX();
					int y = playerloc.getBlockY();
					int z = playerloc.getBlockZ();
					times = 0;
					schedular = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), new Runnable() {
						
						@Override
						public void run() {
							int timer = 5 - times;
							if (timer > 0) {
							player.sendMessage(ChatColor.RED + "Blijf stil staan voor " + timer + "!");
							}
							if (times == 5) {
								player.teleport(target.getLocation());
								player.sendMessage("Je bent geteleporteerd");
								tpa.tp.remove(num);
								Bukkit.getServer().getScheduler().cancelTask(schedular);
							}else {
								int px = player.getLocation().getBlockX();
								int py = player.getLocation().getBlockY();
								int pz = player.getLocation().getBlockZ();
								if (x == px) {
									if (y == py) {
										if (z == pz) {
											times++;
										}else {
											player.sendMessage("tpa is gestopt je hebt bewogen");
											Bukkit.getServer().getScheduler().cancelTask(schedular);
										}
									}else {
										player.sendMessage("tpa is gestopt je hebt bewogen");
										Bukkit.getServer().getScheduler().cancelTask(schedular);
									}
								}else {
									player.sendMessage("tpa is gestopt je hebt bewogen");
									Bukkit.getServer().getScheduler().cancelTask(schedular);
								}
								
							}
							
							
							
						}
						
					}, 0, 20);
					
				}else {
					target.sendMessage("speler is niet online");
				}
			}
		}
	
			
		
		
		
		return false;
	}

}
