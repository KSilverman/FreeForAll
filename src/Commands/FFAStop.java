package Commands;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import Commands.SimpleCommand.CommandHandler;
import Objects.FFAPlayer;
import Objects.KurtPlayer;

public class FFAStop {
	
	@CommandHandler(name = "end")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(player.isOp())
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
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return;
			}
		}
		else
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
    }

}
