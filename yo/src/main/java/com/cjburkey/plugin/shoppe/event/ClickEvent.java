package com.cjburkey.plugin.shoppe.event;

import org.bukkit.Material;
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
				ShopGUI.tabGUI(p, Shoppe.getEcon(), t, 1);
			} else {
				p.closeInventory();
				Util.log("&4Error!");
			}
		} else if(e.getInventory().getName().contains(Util.getCFString("Inv Name"))) {
			Player p = (Player) e.getWhoClicked();
			
			ItemStack stack = e.getCurrentItem();

			e.setCancelled(true);
			
			if(stack.getType() != Material.BARRIER && stack.getItemMeta().getLore().size() == 2) {
				String lore1 = stack.getItemMeta().getLore().get(0);
				String lore2 = stack.getItemMeta().getLore().get(1);
				double buy = Double.parseDouble(lore1.split(" ")[1].replaceAll("[^\\.0123456789]",""));
				double sell = Double.parseDouble(lore2.split(" ")[1].replaceAll("[^\\.0123456789]",""));
				
				if(e.isLeftClick()) { // Sell
					if(Util.give(p, sell)) {
						ItemStack s = new ItemStack(stack.getType(), 1);
						for(ItemStack itemStack : p.getInventory()) {
							if(itemStack.getType().equals(s.getType()) && itemStack.getAmount() > 0) {
								itemStack.setAmount(itemStack.getAmount() - 1);
							}
						}
					}
				} else if(e.isShiftClick()) { // Sell 16
					if(Util.give(p, sell * 16)) {
						ItemStack s = new ItemStack(stack.getType(), 16);
						for(ItemStack itemStack : p.getInventory()) {
							if(itemStack.getType().equals(s.getType()) && itemStack.getAmount() > 15) {
								itemStack.setAmount(itemStack.getAmount() - 16);
							}
						}
					}
				} else if(e.isRightClick()) { // Buy
					if(Util.take(p, sell)) {
						ItemStack s = new ItemStack(stack.getType(), 1);
						p.getInventory().addItem(s);
					}
				}
			} else if(stack.getType() != Material.BARRIER && stack.getItemMeta().getLore().size() == 3) {
				int page = Integer.parseInt(stack.getItemMeta().getLore().get(0));
				String tab = stack.getItemMeta().getLore().get(1);
				ShopGUI.tabGUI(p, Shoppe.getEcon(), Load.getTab(tab), page);
			} else if(stack.getType().equals(Material.BARRIER)) {
				ShopGUI.open(p);
			}
		}
	}
	
}