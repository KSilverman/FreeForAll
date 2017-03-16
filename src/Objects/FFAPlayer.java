package Objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import me.BajanAmerican.FFA.FFA;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FFAPlayer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String name;
	int points;
	double kills;
	double deaths;
	int logins;
	
	public FFAPlayer()
	{
		
	}
	
	public static FFAPlayer getFFAPlayer(String name)
    {
        return FFA.getInstance().getPlayers().get(name);
    }
    
    
    
    public static FFAPlayer getFFAPlayer(Player player)
    {
        return FFA.getInstance().getPlayers().get(player.getName());
    }
	
	public String getName()
	{
		return name;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public double getKills()
	{
		return kills;
	}
	
	public double getDeaths()
	{
		return deaths;
	}
	
	public int getLogins()
	{
		return logins;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public void setKills(double kills)
	{
		this.kills = kills;
	}
	
	public void setDeaths(double deaths)
	{
		this.deaths = deaths;
	}
	
	public void setLogins(int logins)
	{
		this.logins = logins;
	}
	
    @SuppressWarnings("deprecation")
	public void save()
    {
        try
        {
            File f = new File(FFA.getInstance().getDataFolder() + "/players/" + Bukkit.getPlayer(name).getUniqueId().toString() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved player data for: " + "(" + name + ") " + Bukkit.getPlayer(name).getUniqueId().toString());
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Error saving DAT player: " + "(" + name + ") " + Bukkit.getPlayer(name).getUniqueId().toString() + "!");
        }
    }
}
