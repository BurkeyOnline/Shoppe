package com.cjburkey.plugin.shoppe.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.cjburkey.plugin.shoppe.Util;
import com.cjburkey.plugin.shoppe.io.Load;
import com.cjburkey.plugin.shoppe.tabs.ShopTab;
import net.milkbowl.vault.economy.Economy;

public class ShopGUI {
	
	public static final void open(Player p, Economy econ) {
		Inventory inv = Bukkit.createInventory(p, 36, Util.getCFString("Inv Name"));
		
		for(ShopTab t : Load.getTabs()) {
			ItemStack i = t.icon.clone();
			ItemMeta meta = i.getItemMeta();
			meta.setDisplayName(Util.color(t.name));
			i.setItemMeta(meta);
			inv.addItem(i);
		}
		
		p.openInventory(inv);
	}
	
}