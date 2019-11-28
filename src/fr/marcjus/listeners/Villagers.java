package fr.marcjus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Villagers implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getEntityType() == EntityType.VILLAGER){
			Villager v = (Villager) e.getEntity();
			Entity damager = e.getDamager();
			if(damager.getType() != EntityType.PLAYER){
				e.setCancelled(true);
			}else{
				if(e.getDamage() >= v.getHealth()){
					Player player = (Player) damager;
					player.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
					for(Player pl : Bukkit.getOnlinePlayers()){
						if(pl.isOp()){
							Bukkit.broadcastMessage(ChatColor.DARK_RED+damager.getName()+" a tué un villageois ! ");
						}
					}
				}else {
					Player player = (Player) damager;
					player.sendMessage("§cCe n'est pas bien de taper un villageois ! ");
				}
			}
				
		}
	}
	
}
