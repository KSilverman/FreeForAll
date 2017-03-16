package me.BajanAmerican.FFA;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import Objects.FFAPlayer;

public class Methods {
	
	public static void setListName(Player player, String name)
	{
		if (name.length() > 16) 
			name = name.substring(0, 16);
		player.setPlayerListName(name);
	}
	
	public static ItemStack setName(ItemStack is, String name, ChatColor colour)
	{
		    ItemMeta im = is.getItemMeta();
		    im.setDisplayName(colour + name);
		    is.setItemMeta(im);
		    return is;
	}
	
	public static int getRandomNumberInRange(int Max, int Min)
	{
		return Min + (int) (Math.random() * ((Max - Min) + 1));
	}
	
	public static boolean inGame(Player player)
	{
		if(player.getWorld().getName().equalsIgnoreCase("HighRise"))
			return true;
		return false;
	}
	
	public static void clearEverything()
	{
		FFA.getInstance().getPlayerKillStreaks().clear();
		FFA.getInstance().getPlayers().clear();
		FFA.getInstance().getKurtPlayers().clear();
	}
	
	public static void saveAllPlayerStats()
	{
		for(FFAPlayer p : FFA.getInstance().getPlayers().values())
			p.save();
	}
	
	public static void giveThePlayerHisOrHerCrapWhichIsJustIronShit(Player p)
	{
		 p.getInventory().clear();
		 p.getInventory().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
		 p.getInventory().setHelmet(setName(new ItemStack(Material.IRON_HELMET), "A Freaking Helmet", ChatColor.RED));
		 p.getInventory().setChestplate(setName(new ItemStack(Material.IRON_CHESTPLATE), "A Freaking Chestplate", ChatColor.RED));
		 p.getInventory().setLeggings(setName(new ItemStack(Material.IRON_LEGGINGS), "Freaking Pants", ChatColor.RED));
		 p.getInventory().setBoots(setName(new ItemStack(Material.IRON_BOOTS), "Freaking Boots", ChatColor.RED));
		 p.getInventory().addItem(setName(new ItemStack(Material.IRON_SWORD), "A Freaking Sword", ChatColor.RED));
		 p.setHealth(20);
		 p.setFoodLevel(20);
		 p.setGameMode(GameMode.SURVIVAL);
	}
	
	@SuppressWarnings("deprecation")
	public static void giveScoreBoard(Player p)
	{
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective objective = board.registerNewObjective("FFA", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "     §nFFA" + ChatColor.RESET + "     ");
		Score score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "" + ChatColor.BOLD + "Killstreak"));
		Score bpoints = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.BLACK + String.valueOf(FFA.getInstance().getPlayerKillStreaks().get(p))));
		Score blank2 = objective.getScore(Bukkit.getOfflinePlayer(("  ")));
		Score scoree = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Points"));
		Score rpoints = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + String.valueOf(FFAPlayer.getFFAPlayer(p).getPoints())));
		Score blank3 = objective.getScore(Bukkit.getOfflinePlayer(("   ")));
		Score blank = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Total Kills"));
		Score bkills = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + String.valueOf(FFAPlayer.getFFAPlayer(p).getKills())));
		Score blank4 = objective.getScore(Bukkit.getOfflinePlayer(("    ")));
		Score blankk = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Total Deaths"));
		Score rkills = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + String.valueOf(FFAPlayer.getFFAPlayer(p).getDeaths())));
		Score blank5 = objective.getScore(Bukkit.getOfflinePlayer(("     ")));
		Score kit = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "" + ChatColor.BOLD + "Total Logins"));
		Score kitname = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW + String.valueOf(FFAPlayer.getFFAPlayer(p).getLogins())));
		Score blank6 = objective.getScore(Bukkit.getOfflinePlayer(("      ")));
		score.setScore(15);
		bpoints.setScore(14);
		blank2.setScore(13);
		scoree.setScore(12);
		rpoints.setScore(11);
		blank3.setScore(10);
		blank.setScore(9);
		bkills.setScore(8);
		blank4.setScore(7);
		blankk.setScore(6);
		rkills.setScore(5);
		blank5.setScore(4);
		kit.setScore(3);
		kitname.setScore(2);
		blank6.setScore(1);
		p.setScoreboard(board);
	}

}
