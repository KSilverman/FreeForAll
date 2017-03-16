package Events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class EVT_Tag implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerTagReciece(AsyncPlayerReceiveNameTagEvent event) 
	{
		if(event.getNamedPlayer().isOp())
			event.setTag(ChatColor.DARK_RED + event.getNamedPlayer().getName());
		else
			event.setTag(ChatColor.RED + event.getNamedPlayer().getName());
	}

}
