package Objects;

import me.BajanAmerican.FFA.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Locations {
	
	private static Location location;
	
	public static void respawnPlayers()
	{
		switch(Methods.getRandomNumberInRange(6, 1))
		{
			case 1:
				location = new Location(Bukkit.getWorld("Highrise"), 17.0D, 189.0D, -6.0D);
				break;
			case 2:
				location = new Location(Bukkit.getWorld("Highrise"), -51.0D, 190.0D, 36.0D);
				break;
			case 3:
				location = new Location(Bukkit.getWorld("Highrise"), 15.0D, 185.0D, 5.0D);
				break;
			case 4:
				location = new Location(Bukkit.getWorld("Highrise"), 9.0D, 195.0D, 16.0D);
				break;
			case 5:
				location = new Location(Bukkit.getWorld("Highrise"), 14.0D, 190.0D, 12.0D);
				break;
			case 6:
				location = new Location(Bukkit.getWorld("Highrise"), 18.0D, 194.0D, 35.0D);
				break;
		}
	}
	
	public static Location getRandomLocation()
	{
		return location;
	}

}
