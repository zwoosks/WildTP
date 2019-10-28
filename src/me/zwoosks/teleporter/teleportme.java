package me.zwoosks.teleporter;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class teleportme extends JavaPlugin {
	
	public Permission playerPermission = new Permission("wild");
	

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(playerPermission);
	}
	
	
	@Override
	public void onDisable() {
		
	}
	
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("wild") && sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(player.hasPermission("wild.use")) {
				
				
                
                Location originalLocation = player.getLocation();
               
                Random random = new Random();
               
                Location teleportLocation = null;
               
                int x = random.nextInt(100) + 1;
                int y = 150;
                int z = random.nextInt(100) + 1;
               
                boolean isOnLand = false;
               
                while (isOnLand == false) {

                        teleportLocation = new Location(player.getWorld(), x, y, z);
                       
                        if (teleportLocation.getBlock().getType() != Material.AIR) {
                                isOnLand = true;
                        } else y--;
               
                }
               
                player.teleport(new Location(player.getWorld(), teleportLocation.getX(), teleportLocation.getY() + 1, teleportLocation.getZ()));
               
                player.sendMessage(ChatColor.GREEN + "You have been teleported " + (int)teleportLocation.distance(originalLocation) + " blocks away!");
				
				
			} else {
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command!");
			}
			
			return true;
		}
		
		
		return false;
		
	}
	
}
