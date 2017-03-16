package Events;

import me.BajanAmerican.FFA.Methods;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import Objects.Locations;

public class EVT_Respawn implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
			Locations.respawnPlayers();
			event.setRespawnLocation(Locations.getRandomLocation());
			Methods.giveThePlayerHisOrHerCrapWhichIsJustIronShit(event.getPlayer());
	}

}
