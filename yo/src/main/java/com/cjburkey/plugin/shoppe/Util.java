package com.cjburkey.plugin.shoppe;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.EconomyResponse;

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
	
	public static final double getCFDouble(String path) {
		return Shoppe.getPlugin().getConfig().getDouble(path);
	}
	
	public static final ItemStack stringToItem(String item, int data) {
		ItemStack s = new ItemStack(Material.getMaterial(item));
		s.setDurability((short) data);
		return s;
	}
	
	public static final double buyToSell(double buy) {
		return buy * getCFDouble("Sell Per");
	}
	
	public static final boolean take(Player p, double amt) {
		EconomyResponse r = Shoppe.getEcon().withdrawPlayer(p, amt);
		return r.transactionSuccess();
	}
	
	public static final boolean give(Player p, double amt) {
		EconomyResponse r = Shoppe.getEcon().depositPlayer(p, amt);
		return r.transactionSuccess();
	}
	
}