package com.cjburkey.plugin.shoppe;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import com.cjburkey.plugin.shoppe.io.Load;
import com.cjburkey.plugin.shoppe.tabs.ShopTab;
import net.milkbowl.vault.economy.Economy;

public class Shoppe extends JavaPlugin {
	
	private static Shoppe plugin;
	private static Economy econ;
	
	public void onEnable() {
		plugin = this;
		
		if(!setupEcon()) {
			getServer().getPluginManager().disablePlugin(this);
			Util.log("&4Vault not found! Disabling.");
		} else Util.log("&2Vault found! Should be working.");
		
		getCommand("shop").setExecutor(new Shop());
		
		for(ShopTab t : Load.getTabs()) {
			Util.log(t.name + " - " + t.id + " - " + t.icon.getItemMeta().getDisplayName());
		}
	}
	
	public void onDisable() {  }
	
	private boolean setupEcon() {
		if(getServer().getPluginManager().getPlugin("Vault") == null) return false;
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if(rsp == null) return false;
		econ = rsp.getProvider();
		return econ != null;
	}
	
	public static final Shoppe getPlugin() { return plugin; }
	public static final Economy getEcon() { return econ; }
	
}