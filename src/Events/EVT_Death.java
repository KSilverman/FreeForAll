package Events;

import me.BajanAmerican.FFA.FFA;
import me.BajanAmerican.FFA.Methods;
import net.minecraft.server.v1_7_R3.EnumClientCommand;
import net.minecraft.server.v1_7_R3.PacketPlayInClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import Objects.FFAPlayer;

public class EVT_Death implements Listener{

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDeath(final PlayerDeathEvent event)
	{
		if(!(event.getEntity() instanceof Player))
			return;
		final Player victim = (Player) event.getEntity();
		
		if(!Methods.inGame(victim))
			return;
		
		FFAPlayer victimFFAPlayer = FFAPlayer.getFFAPlayer(victim);
		
		event.getDrops().clear();
		event.setDroppedExp(0);
		
		FFA.getInstance().getPlayerKillStreaks().put(victim, 0);
		victimFFAPlayer.setDeaths(victimFFAPlayer.getDeaths() + 1);
		victimFFAPlayer.setPoints(victimFFAPlayer.getPoints() - 3);
		Methods.giveScoreBoard(victim);
		
		if(victim.getKiller() instanceof Player)
		{
			Player attacker = (Player) victim.getKiller();
			FFAPlayer attackerFFAPlayer = FFAPlayer.getFFAPlayer(attacker);
			event.setDeathMessage(null);
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(Methods.inGame(p))
				{
					if(!(victim.isOp()))
						p.sendMessage(ChatColor.RED + victim.getName() + ChatColor.YELLOW + " was killed by " + ChatColor.GREEN + attacker.getName());
					else
						p.sendMessage(ChatColor.DARK_RED + victim.getName() + ChatColor.YELLOW + " was killed by " + ChatColor.GREEN + attacker.getName());
				}
			}
			attackerFFAPlayer.setKills(attackerFFAPlayer.getKills() + 1);
			attackerFFAPlayer.setPoints(attackerFFAPlayer.getPoints() + 5);
			FFA.getInstance().getPlayerKillStreaks().put(attacker, FFA.getInstance().getPlayerKillStreaks().get(attacker) + 1);
			giveKillStreakItem(attacker);
			Methods.giveScoreBoard(attacker);
		}
		else
		{
			event.setDeathMessage(null);
			for(Player p : Bukkit.getOnlinePlayers())
			{
				if(Methods.inGame(p))
				{
					if(!(victim.isOp()))
						p.sendMessage(ChatColor.RED + victim.getName() + " died");
					else
						p.sendMessage(ChatColor.DARK_RED + victim.getName() + " died");
				}
			}
		}
		
		FFA.getInstance().getServer().getScheduler().runTaskLater(FFA.getInstance(), new Runnable() 
    	{
              public void run() 
              {
          		((CraftPlayer)event.getEntity()).getHandle().playerConnection.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
              }
            }
            , 2L);
	}
	
	private void giveKillStreakItem(Player p)
	{
		int ks = FFA.getInstance().getPlayerKillStreaks().get(p);
		if(ks <= 0)
			return;
		else if(ks == 1)
		{
			p.getInventory().clear();
			p.getInventory().addItem(Methods.setName(new ItemStack(Material.DIAMOND_SWORD), "A Freaking Sword", ChatColor.AQUA));
		}
		else if(ks == 2)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.IRON_HELMET), "A Freaking Helmet", ChatColor.LIGHT_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			p.getInventory().setHelmet(a);
		}
		else if(ks == 3)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.IRON_CHESTPLATE), "A Freaking Chestplate", ChatColor.LIGHT_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			p.getInventory().setChestplate(a);
		}
		else if(ks == 4)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.IRON_LEGGINGS), "Freaking Pants", ChatColor.LIGHT_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			p.getInventory().setLeggings(a);
		}
		else if(ks == 5)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.IRON_BOOTS), "Freaking Boots", ChatColor.LIGHT_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			p.getInventory().setBoots(a);
		}
		else if(ks == 6)
		{
			p.getInventory().clear();
			ItemStack a = Methods.setName(new ItemStack(Material.DIAMOND_SWORD), "A Freaking Sword", ChatColor.BLUE);
			a.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			a.addEnchantment(Enchantment.DURABILITY, 1);
			p.getInventory().addItem(a);
		}
		else if(ks == 7)
		{
			p.getInventory().setHelmet(Methods.setName(new ItemStack(Material.DIAMOND_HELMET), "A Freaking Helmet", ChatColor.RED));
		}
		else if(ks == 8)
		{
			p.getInventory().setChestplate(Methods.setName(new ItemStack(Material.DIAMOND_CHESTPLATE), "A Freaking Chestplate", ChatColor.RED));
		}
		else if(ks == 9)
		{
			p.getInventory().setLeggings(Methods.setName(new ItemStack(Material.DIAMOND_LEGGINGS), "Freaking Pants", ChatColor.RED));
		}
		else if(ks == 10)
		{
			p.getInventory().setBoots(Methods.setName(new ItemStack(Material.DIAMOND_BOOTS), "Freaking Boots", ChatColor.RED));
		}
		else if(ks == 11)
		{
			p.getInventory().clear();
			ItemStack a = Methods.setName(new ItemStack(Material.DIAMOND_SWORD), "A Freaking Sword", ChatColor.DARK_BLUE);
			a.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			a.addEnchantment(Enchantment.DURABILITY, 1);
			a.addEnchantment(Enchantment.FIRE_ASPECT, 1);
			p.getInventory().addItem(a);
		}
		else if(ks == 12)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.DIAMOND_HELMET), "A Freaking Helmet", ChatColor.DARK_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			a.addEnchantment(Enchantment.DURABILITY, 2);
			p.getInventory().setHelmet(a);
		}
		else if(ks == 13)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.DIAMOND_CHESTPLATE), "A Freaking Chestplate", ChatColor.DARK_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			a.addEnchantment(Enchantment.DURABILITY, 2);
			p.getInventory().setChestplate(a);
		}
		else if(ks == 14)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.DIAMOND_LEGGINGS), "Freaking Pants", ChatColor.DARK_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			a.addEnchantment(Enchantment.DURABILITY, 2);
			p.getInventory().setLeggings(a);
		}
		else if(ks == 15)
		{
			ItemStack a = Methods.setName(new ItemStack(Material.DIAMOND_BOOTS), "Freaking Boots", ChatColor.DARK_PURPLE);
			a.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			a.addEnchantment(Enchantment.DURABILITY, 2);
			p.getInventory().setBoots(a);
		}
		else
		{
			p.sendMessage(FFA.getInstance().getStarter() + ChatColor.LIGHT_PURPLE + "You already hit your highest killstreak!");
		}
	}
}
