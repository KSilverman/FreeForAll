package Commands;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Commands.SimpleCommand.CommandHandler;

public class FFAList {
	
	@CommandHandler(name = "list")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(Methods.inGame(player))
			{
				String result = "";
				int counter = 0;
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(Methods.inGame(p))
					{
						if(p.isOp())
							result += ChatColor.DARK_RED + p.getName() + ChatColor.GRAY + ", ";
						else
							result += ChatColor.RED + p.getName() + ChatColor.GRAY + ", ";
						counter++;
					}
				}
				player.sendMessage(ChatColor.RED + "FFA" + ChatColor.GRAY + "§l(" + ChatColor.GREEN + String.valueOf(counter) + ChatColor.GRAY + "§l)" + ChatColor.BLACK + ": " + result);
			}
			else
			{
				player.sendMessage(FFA.getInstance().getStarter() + ChatColor.DARK_RED + "You must be in FFA to use this command!");
				return;
			}
		}
		else
		{
			String result = "";
			int counter = 0;
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(Methods.inGame(p))
				{
					if(p.isOp())
						result += ChatColor.DARK_RED + p.getName() + ChatColor.GRAY + ", ";
					else
						result += ChatColor.RED + p.getName() + ChatColor.GRAY + ", ";
					counter++;
				}
			}
			sender.sendMessage(ChatColor.RED + "FFA" + ChatColor.GRAY + "§l(" + ChatColor.GREEN + String.valueOf(counter) + ChatColor.GRAY + "§l)" + ChatColor.BLACK + ": " + result);
		}
    }

}
