package ru.dseymo.twitchStream;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main main;
	
	public void onEnable() {
		
		main = this;
		
		getLogger().info("Started!");
		
	}
	
}
