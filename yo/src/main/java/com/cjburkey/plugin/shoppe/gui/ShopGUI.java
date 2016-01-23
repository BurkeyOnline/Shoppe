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
		
		p.openInventory(inv);
	}
	
}