package fr.marcjus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import fr.marcjus.MainPluginListeners;

@SuppressWarnings("deprecation")
public class Chat implements Listener {
	
	private MainPluginListeners main;
	
	public Chat(MainPluginListeners main){
		this.main = main;
	}
	
	@EventHandler
	public void onCHat(PlayerChatEvent e){
		Player player = e.getPlayer();
		String message = e.getMessage();
		if(!player.isOp()){
			for(int i = 0; i<main.getBadwords().size(); i++){
				if(message.contains(main.getBadwords().get(i))){
					e.setCancelled(true);
					player.sendMessage(main.getConfig().getString("messages.deny").replace('&', '§'));
				}
			}
		}
	}

}
