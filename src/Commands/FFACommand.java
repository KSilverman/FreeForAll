package Commands;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Commands.SimpleCommand.CommandHandler;
import Objects.FFACountdown;
import Objects.KurtPlayer;

public class FFACommand {
	
	@CommandHandler(name = "ffa")
    public void onCommand(CommandSender sender, String[] args)
    {
		if(sender instanceof Player)
		{
			final Player player = (Player) sender;
			if(!(Methods.inGame(player)))
			{
				KurtPlayer kp = KurtPlayer.getKurtPlayer(player);
				kp.setItems(player.getInventory().getContents());
				kp.setArmor(player.getInventory().getArmorContents());
				kp.setKurtPlayerLocation(player.getLocation());
				kp.save();
				player.sendMessage(FFA.getInstance().getStarter() + ChatColor.GREEN + "You will be teleported in " + ChatColor.BLUE + "" + ChatColor.BOLD + String.valueOf(5) + ChatColor.GREEN + " seconds!");
				FFACountdown ffac = new FFACountdown();
				ffac.startCountdown(5, player);
			}
			else
			{
				player.sendMessage(FFA.getInstance().getStarter() + ChatColor.DARK_RED + "You are already in FFA!");
				return;
			}
		}
		else
		{
			sender.sendMessage("You must be a player to use this command!");
		}
    }

}
