package com.cjburkey.plugin.shoppe.io;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.plugin.shoppe.Shoppe;
import com.cjburkey.plugin.shoppe.Util;
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
			tabs.add(new ShopTab(id, name, Util.stringToItem(items[0], Integer.parseInt(items[1]))));
		}
		return tabs;
	}
	
}