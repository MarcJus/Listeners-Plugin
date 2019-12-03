package fr.marcjus.listeners;

import java.util.ArrayList;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPluginListeners extends JavaPlugin{
	
	private ArrayList<String> badwords = new ArrayList<>();
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		for(String word : getConfig().getStringList("badwords")){
			badwords.add(word);
		}
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Villagers(), this);
		pm.registerEvents(new Connecting(), this);
		pm.registerEvents(new Death(), this);
		pm.registerEvents(new Chat(this), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public ArrayList<String> getBadwords(){
		return badwords;
	}

}
