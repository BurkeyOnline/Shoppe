package com.cjburkey.plugin.shoppe.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import com.cjburkey.plugin.shoppe.Shoppe;
import com.cjburkey.plugin.shoppe.Util;
import com.cjburkey.plugin.shoppe.gui.ShopGUI;
import com.cjburkey.plugin.shoppe.io.Load;
import com.cjburkey.plugin.shoppe.tabs.ShopTab;

public class ClickEvent implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getInventory().getName().equals(Util.getCFString("Inv Name"))) {
			ItemStack i = e.getCurrentItem();
			String name = i.getItemMeta().getDisplayName();
			Util.log(name);
			Player p = (Player) e.getWhoClicked();
			ShopTab t = Load.getTab(name);
			e.setCancelled(true);
			if(t != null) {
				ShopGUI.tabGUI(p, Shoppe.getEcon(), t);
			} else {
				p.closeInventory();
				Util.log("&4Error!");
			}
		} else if(e.getInventory().getName().contains(Util.getCFString("Inv Name"))) {
			Player p = (Player) e.getWhoClicked();
			
			ItemStack stack = e.getCurrentItem();

			e.setCancelled(true);
			
			String lore1 = stack.getItemMeta().getLore().get(0);
			String lore2 = stack.getItemMeta().getLore().get(1);
			double buy = Double.parseDouble(lore1.split(" ")[1].replaceAll("[^\\.0123456789]",""));
			double sell = Double.parseDouble(lore2.split(" ")[1].replaceAll("[^\\.0123456789]",""));
			
			if(e.isLeftClick()) {
				if(Util.take(p, buy)) {
					ItemStack s = new ItemStack(stack.getType(), 1);
					p.getInventory().addItem(s);
				}
			} else if(e.isShiftClick()) {
				if(Util.give(p, sell * 16)) {
					ItemStack s = new ItemStack(stack.getType(), 16);
					p.getInventory().addItem(s);
				}
			} else if(e.isRightClick()) {
				if(Util.give(p, sell)) {
					ItemStack s = new ItemStack(stack.getType(), 1);
					p.getInventory().addItem(s);
				}
			}
		}
	}
	
}