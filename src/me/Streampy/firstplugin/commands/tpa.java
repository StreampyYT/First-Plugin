package me.Streampy.firstplugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Streampy.firstplugin.Main;
import net.md_5.bungee.api.ChatColor;


public class tpa implements CommandExecutor {


	
	
	public tpa(Main main) {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<String> tp = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage("gebruik /tpa <speler>");
			}else if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target != null) {
					
					for (int a = 0; a < tptoggle.tptogglelist.size(); a++) {
						if (tptoggle.tptogglelist.get(a).equals(target.getName())) {
							player.sendMessage(ChatColor.RED + "Deze persoon wilt geen teleport hebben op dit moment!");
							return false;
						}
					}
					
					for (int a = 0; a < tp.size(); a++) {
						if (tp.get(a).equals(player.getName() + ">" + target.getName())) {
							player.sendMessage("Er is al een request naar deze persoon gestuurd!");
							return false;
						}
					}
					
					target.sendMessage("Jij hebt een Teleport request van " + player.getName());
					target.sendMessage("Gebruik /tpaccept om te accepteren!");
					player.sendMessage(target.getName() + " heeft 120 seconden om je request te accepteren");
					
					
					tp.add(player.getName() + ">" + target.getName()); //Piet>Klaas
					Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), new Runnable() {

						@Override
						public void run() {
							for (int a = 0; a < tp.size(); a++) {
								if (tp.get(a).equals(player.getName() + ">" + target.getName())) {
									tp.remove(a);
									player.sendMessage("De persoon heeft niet geaccepteerd...");
									return;
								}
							}
						}
						
						
					}, 20 * 120);
					
				}else {
					player.sendMessage("Speler is niet online!");
				}
				
			}else {
				player.sendMessage("gebruik /tpa <speler>");
			}
		}
		return false;
	}

}
