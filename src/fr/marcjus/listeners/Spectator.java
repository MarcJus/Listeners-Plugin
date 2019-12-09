package fr.marcjus.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class Spectator implements Listener {
	
	@EventHandler
	public void onSpectator(PlayerGameModeChangeEvent e){
		Player player = e.getPlayer();
		GameMode gm = e.getNewGameMode();
		if(gm.equals(GameMode.SPECTATOR)){
			if(player.getSpectatorTarget() != null){
				Entity ent = player.getSpectatorTarget();
				if(ent instanceof Player){
					Player target = (Player) player.getSpectatorTarget();
					if(!target.getName().equals("PaulJus"))
						target.sendMessage("§eLe joueur §c"+player.getName()+"§e est en vous !");
				}
			}
		}
	}

}
