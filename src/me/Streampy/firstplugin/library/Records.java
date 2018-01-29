package me.Streampy.firstplugin.library;

import java.util.ArrayList;

public class Records {

	public static class userRec {
		public String uuid;
		public int death;
		public int entitykills;
		public String prefix;
		public String suffix;
	}
	
	public static ArrayList<userRec> usersList = new ArrayList<userRec>();
	
	
}
