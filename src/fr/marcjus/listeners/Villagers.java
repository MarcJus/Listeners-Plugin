package fr.marcjus.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Villagers implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		if (e.getEntityType() == EntityType.VILLAGER) {
			if (!isCustomNPC(ent)) {
				Villager v = (Villager) e.getEntity();
				Entity damager = e.getDamager();
				if (damager.getType() != EntityType.PLAYER || damager.getType() == EntityType.SNOWBALL
						|| damager.getType().equals(EntityType.ARROW)) {
					e.setCancelled(true);
				} else if (damager.getType().equals(EntityType.SNOWBALL)) {
					Snowball s = (Snowball) damager;
					if (s.getShooter() instanceof Player) {
						Player player = (Player) s.getShooter();
						if (e.getDamage() >= v.getHealth()) {
							player.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
							Bukkit.broadcastMessage(ChatColor.DARK_RED + damager.getName() + " a tué un villageois ! ");
						}
					}
				} else if (damager.getType().equals(EntityType.ARROW)) {
					Arrow a = (Arrow) damager;
					if (a.getShooter() instanceof Player) {
						Player shooter = (Player) a.getShooter();
						if (e.getDamage() >= v.getHealth()) {
							shooter.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
							Bukkit.broadcastMessage(ChatColor.DARK_RED + damager.getName() + " a tué un villageois ! ");

						} else {
							shooter.sendMessage("§cCe n'est pas bien de taper un villageois ! ");
						}
					}
				} else {
					if (e.getDamage() >= v.getHealth()) {
						Player player = (Player) damager;
						player.sendMessage("§4Ce n'est pas bien de tuer un villageois ! ");
						Bukkit.broadcastMessage(ChatColor.DARK_RED + damager.getName() + " a tué un villageois ! ");

					} else {
						Player player = (Player) damager;
						player.sendMessage("§cCe n'est pas bien de taper un villageois ! ");
					}

				}
			} else {
				if (e.getDamager().getType() != EntityType.PLAYER) {
					Entity damager = e.getDamager();
					damager.remove();
				}
			}

		}
	}

	@EventHandler
	public void onEmeraldClickChest(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(e.getItem() != null && e.getClickedBlock() != null){
				if(e.getItem().getType().equals(Material.EMERALD) && e.getClickedBlock().getType().equals(Material.CHEST)){
					e.setCancelled(true);
					Block b = e.getClickedBlock();
					ItemStack it = e.getItem();
					ItemMeta im = it.getItemMeta();
					im.setDisplayName("§aCoffre");
					im.addEnchant(Enchantment.KNOCKBACK, 2, true);
					ArrayList<String> lore = new ArrayList<>();
					lore.add(b.getX()+";"+b.getY()+";"+b.getZ());
					im.setLore(lore);
					it.setItemMeta(im);
				}
			}
		}
	}
	
	@EventHandler
	public void onOpenChest(PlayerInteractEvent e){
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
			if(e.getItem() != null){
				if(e.getItem().getType().equals(Material.EMERALD)){
					if(e.getItem().hasItemMeta() && e.getItem().getItemMeta().getLore() != null){
						List<String> lore = e.getItem().getItemMeta().getLore();
						String[] locationXYZ = lore.get(0).split(";");
						int x = Integer.parseInt(locationXYZ[0]);
						int y = Integer.parseInt(locationXYZ[1]);
						int z = Integer.parseInt(locationXYZ[2]);
						BlockState bs = e.getPlayer().getWorld().getBlockAt(x, y, z).getState();
						Chest c = (Chest) bs;
						c.setCustomName("nom");
						e.getPlayer().openInventory(c.getInventory());
					}
				}
			}
		}
	}

	private boolean isCustomNPC(Entity ent) {

		if (ent instanceof Villager) {
			Villager npc = (Villager) ent;

			if (npc.isCustomNameVisible() && npc.getCustomName() != null) {
				if (npc.getCustomName().equals("§eVillageois du chateau"))
					return true;

			} else if (!npc.isCustomNameVisible() && npc.getCustomName() != null) {
				if (npc.getCustomName().equals("§eNpc perdu"))
					return true;
			} else if (npc.isCustomNameVisible() && npc.getCustomName() != null) {
				if (npc.getCustomName().equals("§2Villageois de la terrasse")) {
					return true;
				}
			}
		}

		return false;
	}

}
