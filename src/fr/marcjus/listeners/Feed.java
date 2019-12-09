package fr.marcjus.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Feed implements Listener {
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e){
		Entity ent = e.getEntity();
		if(ent instanceof Player){
			e.setCancelled(true);
		}
	}

}
