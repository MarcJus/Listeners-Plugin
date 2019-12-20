package fr.marcjus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Death implements Listener {

	@EventHandler
	public void onDeath(EntityDamageEvent e) {
		Entity ent = e.getEntity();
		if (ent instanceof Player) {
			Player player = (Player) ent;
			if (e.getDamage() >= player.getHealth()) {
				Bukkit.broadcastMessage("§e" + player.getName() + "§c est mort !");
			}
		}
	}

	@EventHandler
	public void onDeathByEntity(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		Entity damager = e.getDamager();
		if (ent instanceof Player) {
			Player player = (Player) ent;
			if (e.getDamage() >= player.getHealth()) {
				if (damager instanceof Player) {
					Player d = (Player) damager;
					Bukkit.broadcastMessage("§e" + d.getName() + "§c a tue §e" + player.getName());
				} else if (damager instanceof Projectile) {
					Projectile projectile = (Projectile) damager;
					Entity shooter = (Entity) projectile.getShooter();
					if (shooter instanceof Player) {
						Player d = (Player) damager;
						Bukkit.broadcastMessage("§e" + d.getName() + "§c a tue §e" + player.getName());
					} else if (shooter instanceof Skeleton) {
						Bukkit.broadcastMessage("§e" + player.getName() + " §c a ete tue par un squelette !");
					} else if (shooter instanceof Witch) {
						Bukkit.broadcastMessage("§e" + player.getName() + " §c a ete tue par une sorciere !");
					} else {
						Bukkit.broadcastMessage("§e" + player.getName() + " §c a ete tue par un projectile !");
					}
				}
			}

		}
	}

}
