package com.cjburkey.plugin.shoppe.tabs;

import org.bukkit.inventory.ItemStack;

public class ShopTab {
	
	public int id;
	public String name;
	public ItemStack icon;
	
	public ShopTab(int id, String name, ItemStack icon) {
		this.id = id;
		this.name = name;
		this.icon = icon;
	}
	
}