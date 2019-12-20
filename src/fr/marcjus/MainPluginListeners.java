package fr.marcjus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.marcjus.listeners.Chat;
import fr.marcjus.listeners.Connecting;
import fr.marcjus.listeners.DeathRespawn;
import fr.marcjus.listeners.Feed;
import fr.marcjus.listeners.Spectator;
import fr.marcjus.listeners.Villagers;

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
		pm.registerEvents(new Chat(this), this);
		pm.registerEvents(new Feed(), this);
		pm.registerEvents(new DeathRespawn(), this);
		pm.registerEvents(new Spectator(), this);
	}
	
	@Override
	public void onDisable() {
		for(Player player : Bukkit.getOnlinePlayers()){
			player.kickPlayer("§cReload du serveur. Merci d'attendre 10 sec environ avant de se reconnecter.");
		}
	}
	
	public ArrayList<String> getBadwords(){
		return badwords;
	}

}
