package Events;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import Objects.FFAPlayer;

public class EVT_Kick implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
		{
			event.setLeaveMessage(null);
			FFAPlayer ffaplayer = FFAPlayer.getFFAPlayer(event.getPlayer());
			ffaplayer.save();
			FFA.getInstance().getPlayers().remove(event.getPlayer().getName());
			FFA.getInstance().getPlayerKillStreaks().remove(event.getPlayer());
			for(Player p : Bukkit.getOnlinePlayers())
				p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + ">> " + ChatColor.RED + event.getPlayer().getName() + " has left FFA!");
		}
	}

}
