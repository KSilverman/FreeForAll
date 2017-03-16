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

public class FFALeave {
	
	@CommandHandler(name = "leave")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(Methods.inGame(player))
			{
				FFAPlayer ffaplayer = FFAPlayer.getFFAPlayer(player);
				KurtPlayer kurtplayer = KurtPlayer.getKurtPlayer(player);
				ffaplayer.save();
				FFA.getInstance().getPlayers().remove(player.getName());
				FFA.getInstance().getPlayerKillStreaks().remove(player);
				player.teleport(kurtplayer.getKurtPlayerLocation());
				player.getInventory().setContents(kurtplayer.getItems());;
				player.getInventory().setArmorContents(kurtplayer.getArmor());
				player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(Methods.inGame(p))
						p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + ">> " + ChatColor.RED + player.getName() + " has left FFA!");
				}
			}
			else
			{
				player.sendMessage(FFA.getInstance().getStarter() + ChatColor.DARK_RED + "You must be in FFA to use this command!");
				return;
			}
		}
		else
		{
			sender.sendMessage("You must be a player to use this command!");
			return;
		}
    }

}
