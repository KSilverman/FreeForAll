package me.BajanAmerican.FFA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.FFACommand;
import Commands.FFALeave;
import Commands.FFAList;
import Commands.FFAStop;
import Commands.SimpleCommand;
import Events.EVT_Block;
import Events.EVT_Chat;
import Events.EVT_Damage;
import Events.EVT_Death;
import Events.EVT_Kick;
import Events.EVT_Quit;
import Events.EVT_Respawn;
import Events.EVT_Tag;
import Objects.FFAPlayer;
import Objects.KurtPlayer;

public class FFA extends JavaPlugin{
	
	private static FFA instance;
	
	File rootDir;
    File playersDir;
    File playerDir;
    
    Map<String, FFAPlayer> ffaplayers;
    Map<String, KurtPlayer> kurtplayers;
    Map<Player, Integer> playerkillstreaks;
	
	@Override
	public void onEnable()
	{
		instance = this;
		kurtplayers = new HashMap<String, KurtPlayer>();
		ffaplayers = new HashMap<String, FFAPlayer>();
		playerkillstreaks = new HashMap<Player, Integer>();
		init_files();
		init_events();
		init_commands();
		Methods.clearEverything();
		System.out.println("ENABLED!");
	}
	
	@Override
	public void onDisable()
	{
		Methods.saveAllPlayerStats();
		Methods.clearEverything();
		System.out.println("DISABLED!");
	}
	
	private void init_files()
	{
		rootDir = new File(getDataFolder() + "");
        if (!rootDir.exists())
            rootDir.mkdir();
        
        playersDir = new File(getDataFolder() + "/players/");
        if (!playersDir.exists())
            playersDir.mkdir();
        
        playerDir = new File(getDataFolder() + "/KurtPlayers/");
        if (!playerDir.exists())
            playerDir.mkdir();
        
        File[] playerFiles = playerDir.listFiles();
        for (int i = 0; i < playerFiles.length; i++)
        {
            File f = playerFiles[i];
            	try
            	{
            		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            		KurtPlayer t = (KurtPlayer) ois.readObject();
            		kurtplayers.put(t.getName(), t);
            		ois.close();
            		System.out.println("Loaded player item data for " + "(" + t.getName() + ")");
            	}
            	catch (IOException | ClassNotFoundException e)
            	{
            		e.printStackTrace();
            	}
        }
	}
	
	private void init_events()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EVT_Block(), this);
		pm.registerEvents(new EVT_Respawn(), this);
		pm.registerEvents(new EVT_Chat(), this);
		pm.registerEvents(new EVT_Death(), this);
		pm.registerEvents(new EVT_Tag(), this);
		pm.registerEvents(new EVT_Quit(), this);
		pm.registerEvents(new EVT_Kick(), this);
		pm.registerEvents(new EVT_Damage(), this);
	}
	
	private void init_commands()
	{
		SimpleCommand.registerCommands(this, new FFACommand());
		SimpleCommand.registerCommands(this, new FFALeave());
		SimpleCommand.registerCommands(this, new FFAList());
		SimpleCommand.registerCommands(this, new FFAStop());
	}
	
	public void loadFFAPlayerData(Player p)
	{
        File[] playerFiles = playersDir.listFiles();
        for (int i = 0; i < playerFiles.length; i++)
        {
            File f = playerFiles[i];
            if(f.getName().contains(p.getUniqueId().toString()))
            {
            	try
            	{
            		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            		FFAPlayer t = (FFAPlayer) ois.readObject();
            		ffaplayers.put(t.getName(), t);
            		ois.close();
            		System.out.println("Loaded player data for " + "(" + t.getName() + ") " + p.getUniqueId().toString());
            	}
            	catch (IOException | ClassNotFoundException e)
            	{
            		e.printStackTrace();
            	}
            }
        }
	}

	public static FFA getInstance()
	{
		return instance;
	}
	
	public String getStarter()
	{
		return ChatColor.RED + "" + ChatColor.ITALIC + "[FFA] ";
	}
	
	public Map<String, FFAPlayer> getPlayers()
	{
		return ffaplayers;
	}
	
	public Map<Player, Integer> getPlayerKillStreaks()
	{
		return playerkillstreaks;
	}
	
	public Map<String, KurtPlayer> getKurtPlayers()
	{
		return kurtplayers;
	}
	
}
