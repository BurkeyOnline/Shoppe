package com.cjburkey.plugin.shoppe.io;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.plugin.shoppe.Shoppe;
import com.cjburkey.plugin.shoppe.Util;
import com.cjburkey.plugin.shoppe.tabs.ShopItem;
import com.cjburkey.plugin.shoppe.tabs.ShopTab;

public class Load {
	
	public static final List<ShopTab> getTabs() {
		List<ShopTab> tabs = new ArrayList<ShopTab>();
		for(Object obj : Shoppe.getPlugin().getConfig().getList("tabs")) {
			String s = (String) obj;
			String[] split = s.split(",");
			int id = Integer.parseInt(split[0]);
			String name = split[1];
			String[] items = split[2].split(":");
			tabs.add(new ShopTab(id, Util.color(name), Util.stringToItem(items[0], Integer.parseInt(items[1]))));
		}
		return tabs;
	}
	
	public static final ShopTab getTab(String name) {
		for(ShopTab t : getTabs()) {
			if(t.name.trim().equals(name.trim())) return t;
		}
		return null;
	}
	
	public static final ShopTab getTab(int id) {
		for(ShopTab t : getTabs()) {
			if(t.id == id) return t;
		}
		return null;
	}
	
	public static final List<ShopItem> getShopItems() {
		List<ShopItem> items = new ArrayList<ShopItem>();
		for(Object obj : Shoppe.getPlugin().getConfig().getList("items")) {
			String s = (String) obj;
			String[] split = s.split(",");
			int tab = Integer.parseInt(split[0]);
			double buy = Double.parseDouble(split[2]);
			String[] its = split[1].split(":");
			items.add(new ShopItem(tab, Util.stringToItem(its[0], Integer.parseInt(its[1])), buy));
		}
		return items;
	}
	
	public static final List<ShopItem> getItemsForTab(int tab) {
		List<ShopItem> items = new ArrayList<ShopItem>();
		for(ShopItem i : getShopItems()) {
			if(i.tab.id == tab) {
				items.add(i);
			}
		}
		return items;
	}
	
}