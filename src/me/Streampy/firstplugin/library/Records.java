package me.Streampy.firstplugin.library;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Records {

	public static class userRec {
		public String uuid;
		public int death;
		public int entitykills;
		public String prefix;
		public String suffix;
		public boolean scoreboard;
	}
	
	public static ArrayList<userRec> usersList = new ArrayList<userRec>();	
	
	public static userRec getUsersRecord(Player player) {
		for (userRec userRecord : usersList) {
			if (userRecord.uuid.equals(player.getUniqueId().toString())) {
				return userRecord;
			}
		}
		return null;
	}
	
	public static int getDeath(Player player) {
		userRec userRecords = getUsersRecord(player);
		if (userRecords != null) {
			return userRecords.death;
		}
		return 0;
	}
	
	public static void setDeath(Player player, int newdeaths) {
		userRec userRecords = getUsersRecord(player);
		if (userRecords != null) {
			userRecords.death = newdeaths;
		}
	}
}
