package fr.marcjus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Villagers implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		Entity ent = e.getEntity();
		if(e.getEntityType() == EntityType.VILLAGER && !isCustomNPC(ent)){
			Villager v = (Villager) e.getEntity();
			Entity damager = e.getDamager();
			if(damager.getType() != EntityType.PLAYER || damager.getType().equals(EntityType.SNOWBALL) || damager.getType().equals(EntityType.ARROW)){
				Player player = (Player) damager;
				e.setCancelled(true);
			}else if(damager.getType().equals(EntityType.SNOWBALL)){
				Snowball s = (Snowball) damager;
				if(s.getShooter() instanceof Player){
					Player player = (Player) s.getShooter();
					if(e.getDamage() >= v.getHealth()){
						player.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
						Bukkit.broadcastMessage(ChatColor.DARK_RED+damager.getName()+" a tué un villageois ! ");
					}
				}
			}else if(damager.getType().equals(EntityType.ARROW)){
				Arrow a = (Arrow) damager;
				if(a.getShooter() instanceof Player){
					Player shooter = (Player) a.getShooter();
					if(e.getDamage() >= v.getHealth()){
						shooter.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
						Bukkit.broadcastMessage(ChatColor.DARK_RED+damager.getName()+" a tué un villageois ! ");
					
					}else {
						shooter.sendMessage("§cCe n'est pas bien de taper un villageois ! ");
					}
				}
			}else{
				if(e.getDamage() >= v.getHealth()){
					Player player = (Player) damager;
					player.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
					Bukkit.broadcastMessage(ChatColor.DARK_RED+damager.getName()+" a tué un villageois ! ");
				
				}else {
					Player player = (Player) damager;
					player.sendMessage("§cCe n'est pas bien de taper un villageois ! ");
				}
			 
		     }	
		}
	}
	
	private boolean isCustomNPC(Entity ent) {
		
		if(ent instanceof Villager) {
			Villager npc = (Villager) ent;
			
			if(npc.isCustomNameVisible() && npc.getCustomName() != null && npc.getCustomName().equalsIgnoreCase("§eVillageois du chateau")) {
				return true;
				
			}
		}
		
		return false;
	}
	
}
