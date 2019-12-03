package fr.marcjus.listeners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPluginListeners extends JavaPlugin{
	
	public static MainPluginListeners instance;
	
	public static MainPluginListeners getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Villagers(), this);
		pm.registerEvents(new Connecting(), this);
		pm.registerEvents(new Death(), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
