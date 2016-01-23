package com.cjburkey.plugin.shoppe.tabs;

import org.bukkit.inventory.ItemStack;
import com.cjburkey.plugin.shoppe.io.Load;

public class ShopItem {
	
	public ShopTab tab;
	public ItemStack item;
	public double buy;
	
	public ShopItem(int tab, ItemStack item, double buy) {
		this.tab = Load.getTab(tab);
		this.item = item;
		this.buy = buy;
	}
	
}