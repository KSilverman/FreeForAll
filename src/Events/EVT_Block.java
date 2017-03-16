package Events;

import me.BajanAmerican.FFA.Methods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class EVT_Block implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerHungerDicipate(FoodLevelChangeEvent event)
	{
		if(Methods.inGame((Player)event.getEntity()))
			event.setCancelled(true);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onWeatherChange(WeatherChangeEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDrop(PlayerDropItemEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerPickup(PlayerPickupItemEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
			event.setCancelled(true);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInvClick(InventoryClickEvent event)
	{
		if(Methods.inGame((Player)event.getWhoClicked()))
			event.setCancelled(true);
	}
}
