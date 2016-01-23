package com.cjburkey.plugin.shoppe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.plugin.shoppe.gui.ShopGUI;

public class Shop implements CommandExecutor {

	public boolean onCommand(CommandSender player, Command cmd, String lbl, String[] args) {
		if(player instanceof Player) {
			Player p = (Player) player;
			ShopGUI.open(p);
		} else Util.send(player, "&4You must be a player to use &o/shop&r&4!");
		return true;
	}

}