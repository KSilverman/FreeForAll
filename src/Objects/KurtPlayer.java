package Objects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import me.BajanAmerican.FFA.FFA;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KurtPlayer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	ItemStack[] items;
	ItemStack[] armor;
	Location location;
	
	public KurtPlayer()
	{
		
	}
	
	public static KurtPlayer getKurtPlayer(String name)
    {
        return FFA.getInstance().getKurtPlayers().get(name);
    }
      
    
    public static KurtPlayer getKurtPlayer(Player player)
    {
        return FFA.getInstance().getKurtPlayers().get(player.getName());
    }
	
	public String getName()
	{
		return name;
	}
	
	public ItemStack[] getItems()
	{
		return items;
	}
	
	public ItemStack[] getArmor()
	{
		return armor;
	}
	
	public Location getKurtPlayerLocation()
	{
		return location;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setItems(ItemStack[] itemStacks)
	{
		this.items = itemStacks;
	}
	
	public void setArmor(ItemStack[] armor)
	{
		this.armor = armor;
	}
	
	public void setKurtPlayerLocation(Location loc)
	{
		this.location = loc;
	}
	
	@SuppressWarnings("deprecation")
	public void save()
	{
		try
        {
            File f = new File(FFA.getInstance().getDataFolder() + "/KurtPlayers/" + Bukkit.getPlayer(name).getUniqueId().toString() + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved player item data for: " + "(" + name + ") " + Bukkit.getPlayer(name).getUniqueId().toString());
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Error saving item DAT player: " + "(" + name + ") " + Bukkit.getPlayer(name).getUniqueId().toString() + "!");
        }
	}
}
