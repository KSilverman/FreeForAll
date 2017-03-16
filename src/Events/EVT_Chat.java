package Events;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scoreboard.DisplaySlot;

import Objects.FFAPlayer;
import Objects.KurtPlayer;

public class EVT_Chat implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
		{
			if(event.getPlayer().isOp())
			event.setFormat(ChatColor.GRAY + "§l[" + ChatColor.DARK_PURPLE + String.valueOf(FFAPlayer.getFFAPlayer(event.getPlayer()).getPoints()) + ChatColor.GRAY + "§l]" + ChatColor.DARK_RED + event.getPlayer().getName()
					+ ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + event.getMessage());
			else
				event.setFormat(ChatColor.GRAY + "§l[" + ChatColor.DARK_PURPLE + String.valueOf(FFAPlayer.getFFAPlayer(event.getPlayer()).getPoints()) + ChatColor.GRAY + "§l]" + ChatColor.RED + event.getPlayer().getName()
						+ ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + event.getMessage());
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(!(Methods.inGame(p)))
					event.getRecipients().remove(p);
			}
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event)
	{
		if(event.getMessage().contains("stop"))
		{
			if(event.getPlayer().isOp())
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(Methods.inGame(p))
					{
						FFAPlayer ffaplayer = FFAPlayer.getFFAPlayer(p);
						KurtPlayer kurtplayer = KurtPlayer.getKurtPlayer(p);
						ffaplayer.save();
						FFA.getInstance().getPlayers().remove(p.getName());
						FFA.getInstance().getPlayerKillStreaks().remove(p);
						p.teleport(kurtplayer.getKurtPlayerLocation());
						p.getInventory().setContents(kurtplayer.getItems());;
						p.getInventory().setArmorContents(kurtplayer.getArmor());
						p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					}
					p.kickPlayer(ChatColor.RED + "Server is restarting! Please rejoin in a few seconds!");
				}
				FFA.getInstance().getServer().shutdown();
			}
			else
			{
				event.getPlayer().sendMessage(FFA.getInstance().getStarter() + ChatColor.DARK_RED + "You do not have permission to use this command!");
			}
		}
	}

}
