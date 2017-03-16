package Events;

import me.BajanAmerican.FFA.Methods;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EVT_Damage implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDamage(EntityDamageEvent event)
	{
		if(!(event.getEntity() instanceof Player))
			return;
		Player player = (Player) event.getEntity();
		if(!(Methods.inGame(player)))
			return;
		player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
	}

}
