package Events;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import Objects.FFAPlayer;
import Objects.KurtPlayer;

public class EVT_Quit implements Listener{
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if(Methods.inGame(event.getPlayer()))
		{
			event.setQuitMessage(null);
			FFAPlayer ffaplayer = FFAPlayer.getFFAPlayer(event.getPlayer());
			ffaplayer.save();
			FFA.getInstance().getPlayers().remove(event.getPlayer().getName());
			FFA.getInstance().getPlayerKillStreaks().remove(event.getPlayer());
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(Methods.inGame(p))
					p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + ">> " + ChatColor.RED + event.getPlayer().getName() + " has left FFA!");
			}
		}
		else
		{
			KurtPlayer kp = KurtPlayer.getKurtPlayer(event.getPlayer());
			kp.setArmor(event.getPlayer().getInventory().getArmorContents());
			kp.setItems(event.getPlayer().getInventory().getContents());
			kp.setKurtPlayerLocation(event.getPlayer().getLocation());
			kp.save();
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerjOin(PlayerJoinEvent event)
	{
		if(event.getPlayer().isOp())
			event.setJoinMessage(ChatColor.DARK_RED + event.getPlayer().getName() + ChatColor.AQUA + " has joined the freaking server!");
		else
			event.setJoinMessage(ChatColor.RED + event.getPlayer().getName() + ChatColor.AQUA + " has joined the freaking server!");
		if(!(FFA.getInstance().getKurtPlayers().containsKey(event.getPlayer().getName())))
		{
			KurtPlayer FFAp = new KurtPlayer();
			FFAp.setName(event.getPlayer().getName());
			FFAp.setItems(event.getPlayer().getInventory().getContents());
			FFAp.setArmor(event.getPlayer().getInventory().getArmorContents());
			FFAp.setKurtPlayerLocation(event.getPlayer().getLocation());
			FFAp.save();
			FFA.getInstance().getKurtPlayers().put(event.getPlayer().getName(), FFAp);
		}
		if(event.getPlayer().getWorld().getName().equalsIgnoreCase("Highrise"))
			event.getPlayer().teleport(KurtPlayer.getKurtPlayer(event.getPlayer()).getKurtPlayerLocation());
		event.getPlayer().getInventory().setArmorContents(KurtPlayer.getKurtPlayer(event.getPlayer()).getArmor());
		event.getPlayer().getInventory().setContents(KurtPlayer.getKurtPlayer(event.getPlayer()).getItems());
	}

}
