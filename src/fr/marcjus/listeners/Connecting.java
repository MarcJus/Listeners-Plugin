package fr.marcjus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Connecting implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		//player.setInvulnerable(false);

		player.removePotionEffect(PotionEffectType.SPEED);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
		e.setJoinMessage("§eBienvenue §2" + player.getName() + " §esur le serveur ! ");
		player.sendMessage("§4Attention nous vous rappelons qu'il est interdit de tuer un villageois !");
		
	}
	
}
