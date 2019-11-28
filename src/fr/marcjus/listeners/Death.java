package fr.marcjus.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Death implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		
		Entity ent = e.getEntity();
		
		if(ent instanceof Player){
			Player player = (Player)ent;
			if(e.getDamage() >= player.getHealth() || player.getHealth() <= 0){
				player.sendMessage(ChatColor.DARK_RED+"Vous avez été tué !");
				e.setDamage(0);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
				World world = Bukkit.getWorld("world");
				player.teleport(new Location(world, -369, 69, 389, 0f, 0f));
				player.setHealth(20);
				player.setFoodLevel(20);
				ItemStack gun = new ItemStack(Material.DIAMOND_HOE, 1);
				ItemMeta meta = gun.getItemMeta();
				meta.setDisplayName("§4Pistolet");
				meta.setLore(Arrays.asList("Appuyez pour tirer", "Ne marche pas sur en cliquant sur les blocs", "Tapez l'entité pour la faire reculer loin(très)"));
				meta.addEnchant(Enchantment.KNOCKBACK, 5, true);
				gun.setItemMeta(meta);
				world.dropItem(player.getLocation(), gun);
			}
		}
		
	}
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e){
		
		Entity ent = e.getEntity();
		Entity damager = e.getDamager();
		World world = Bukkit.getWorld("world");
		if(ent instanceof Player){
			Player player = (Player) ent;
			if(e.getDamage() > player.getHealth() || player.getHealth() <= 0){
				e.setDamage(0);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
				if(damager instanceof Player){
					Player d = (Player) damager;
					d.sendMessage("Vous avez tué "+ChatColor.GREEN+player.getName());
					player.sendMessage("Vous avez été tué par "+d.getName());
				}else if(damager instanceof Snowball){
					Snowball s = (Snowball) damager;
					if(s.getShooter() instanceof Player){
						Player shotter = (Player) s.getShooter();
						shotter.sendMessage("Vous avez tué "+ChatColor.GREEN+player.getName());
					}
				}else if(damager instanceof Arrow){
					Arrow a = (Arrow) damager;
					if(a.getShooter() instanceof Player){
						Player shotter = (Player) a.getShooter();
						shotter.sendMessage("Vous avez tué "+ChatColor.GREEN+player.getName());
					}
					player.setGameMode(GameMode.SPECTATOR);
					player.teleport(new Location(world, -369, 69, 389, 0f, 0f));
				}
				
			}
			
		}

	}
}
