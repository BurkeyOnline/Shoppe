package com.cjburkey.plugin.shoppe;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import net.md_5.bungee.api.ChatColor;

public class Util {
	
	public static final String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static final void log(String msg) {
		ConsoleCommandSender sender = Shoppe.getPlugin().getServer().getConsoleSender();
		sender.sendMessage("[" + Shoppe.getPlugin().getName() + "] " + color(msg));
	}
	
	public static final void send(CommandSender s, String msg) {
		s.sendMessage(color(msg));
	}
	
	public static final String getCFString(String path) {
		return color(Shoppe.getPlugin().getConfig().getString(path));
	}
	
	public static final ItemStack stringToItem(String item, int data) {
		ItemStack s = new ItemStack(Material.getMaterial(item));
		s.setDurability((short) data);
		return s;
	}
	
}