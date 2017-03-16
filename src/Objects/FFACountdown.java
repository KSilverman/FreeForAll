package Objects;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class FFACountdown {
	
	int time;
    int id;
    int[] intervals = new int[] { 5, 4, 3, 2, 1 };
    
    
    
    public void startCountdown(final int seconds, final Player p)
    {
        this.time = seconds;
        this.id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(FFA.getInstance(), new Runnable()
        {
            
            
            @Override
            public void run()
            {
            		if (time > 0)
            		{
                		for (int interval : intervals)
                		{
                			if (time == interval)
                			{
                				p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 2.0F);             
                			}
                		}
                		time--;
                	}
                	else
                	{
                		 Methods.giveThePlayerHisOrHerCrapWhichIsJustIronShit(p);
						 Locations.respawnPlayers();
						 p.teleport(Locations.getRandomLocation());
						 FFA.getInstance().loadFFAPlayerData(p);
						 if(!(FFA.getInstance().getPlayers().containsKey(p.getName())))
							{
								FFAPlayer FFAp = new FFAPlayer();
								FFAp.setName(p.getName());
								FFAp.setPoints(100);
								FFAp.setDeaths(0);
								FFAp.setKills(0);
								FFAp.setLogins(0);
								FFAp.save();
								FFA.getInstance().getPlayers().put(p.getName(), FFAp);
							}
						 FFA.getInstance().getPlayerKillStreaks().put(p, 0);
						 FFAPlayer.getFFAPlayer(p).setLogins(FFAPlayer.getFFAPlayer(p).getLogins() + 1);
						 System.out.println(p.getName() + " has logged in " + FFAPlayer.getFFAPlayer(p).getLogins() + " time(s)!");
						 if(p.isOp())
							 Methods.setListName(p, ChatColor.DARK_RED + p.getName());
						 else
							 Methods.setListName(p, ChatColor.RED + p.getName());
						 Methods.giveScoreBoard(p);
						 for(Player player : Bukkit.getOnlinePlayers())
						 {	
							 	if(Methods.inGame(player))
							 		player.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + ">> " + ChatColor.GREEN + p.getName() + " has joined FFA!");
						 }
						 cancelCountdown(id);
                	}
                }
        }, 20L, 20L);
    }
    
    
    
    public void cancelCountdown(int taskID)
    {
        Bukkit.getServer().getScheduler().cancelTask(taskID);
    }
    
    public int getTaskID()
    {
    	return id;
    }
    
    public void setTime(int x)
    {
    	this.time = x;
    }
    
    public int getTime()
    {
        return time;
    }

}
