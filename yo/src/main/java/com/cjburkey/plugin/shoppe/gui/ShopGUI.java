package com.cjburkey.plugin.shoppe.gui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.cjburkey.plugin.shoppe.Util;
import com.cjburkey.plugin.shoppe.io.Load;
import com.cjburkey.plugin.shoppe.tabs.ShopItem;
import com.cjburkey.plugin.shoppe.tabs.ShopTab;
import net.milkbowl.vault.economy.Economy;

public class ShopGUI {
	
	public static final void open(Player p) {
		Inventory inv = Bukkit.createInventory(p, 36, Util.getCFString("Inv Name"));
		
		for(ShopTab t : Load.getTabs()) {
			ItemStack i = t.icon.clone();
			ItemMeta meta = i.getItemMeta();
			meta.setDisplayName(t.name);
			i.setItemMeta(meta);
			inv.addItem(i);
		}
		
		p.openInventory(inv);
	}
	
	public static final void tabGUI(Player p, Economy econ, ShopTab tab, int page) {
		Inventory inv = Bukkit.createInventory(p, 54, Util.getCFString("Inv Name") + " - " + tab.name);
		
		List<ShopItem> list = Load.getItemsForTab(tab.id);
		
		int start = 45 * (page - 1);
		double pages = Math.ceil((double) list.size() / 45d);
		
		for(int j = start; j < (45 * (page - 1)) + 45; j ++) {
			if(j < list.size()) {
				ShopItem i = list.get(j);
				ItemStack item = i.item.clone();
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(Util.color("&2Buy: " + econ.format(i.buy)));
				lore.add(Util.color("&4Sell: " + econ.format(Util.buyToSell(i.buy))));
				meta.setLore(lore);
				item.setItemMeta(meta);
				inv.addItem(item);
			}
		}
		
		ItemStack back = Util.nameStack(Util.stringToItem("SIGN", 0), "&2Back");
		ItemStack forw = Util.nameStack(Util.stringToItem("SIGN", 0), "&2Next");
		ItemStack exit = Util.nameStack(Util.stringToItem("BARRIER", 0), "&4Home");
		
		back = Util.loreStack(back, "" + (page - 1), tab.name, "Move");
		forw = Util.loreStack(forw, "" + (page + 1), tab.name, "Move");
		
		if(page > 1) inv.setItem(inv.getSize() - 2, back);
		if(page < pages) inv.setItem(inv.getSize() - 1, forw);
		inv.setItem(inv.getSize() - 9, exit);
		
		p.openInventory(inv);
	}
	
}