package fr.marcjus.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GodSword implements Listener {
	
	@EventHandler
	public void onDamageWithGodSword(EntityDamageByEntityEvent e){
		Entity damager = e.getDamager();
		if(damager instanceof Player){
			Player player = (Player) damager;
			if(!player.isOp()){
				e.setCancelled(true);
				player.sendMessage(ChatColor.RED+"Vous n'etes pas digne d'utiliser cette épée");
			}
		}
	}

}
