package streamInfoTwitch;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;
	
	public void onEnable() {
		
		plugin = this;
		
		File config = new File(this.getDataFolder() + File.separator + "config.yml");
		
		if(!config.exists()) {
			
			try {
				
				getDataFolder().mkdirs();
				config.createNewFile();
				
			} catch (IOException e) {}
			
		}
		FileConfiguration configFile = (FileConfiguration)YamlConfiguration.loadConfiguration(config);
		if(configFile.contains("oauth"))
			Twitch.oauth = configFile.getString("oauth");
		else
			getLogger().warning("ERROR: Укажите oauth в конфиге");
		
		if(configFile.contains("nick"))
			Twitch.nick = configFile.getString("nick");
		else
			getLogger().warning("ERROR: Укажите nick в конфиге");
		
		this.getLogger().info("Started!");
		
	}
	
}
