package com.ashkiano.resistanceamulet;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ResistanceAmuletCommand implements CommandExecutor {

    private final String commandPermission;
    private final Integer resistanceLevel;

    public ResistanceAmuletCommand(String commandPermission, Integer resistanceLevel) {
        this.commandPermission = commandPermission;
        this.resistanceLevel = resistanceLevel;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(commandPermission)) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        ItemStack amulet = new ItemStack(Material.EMERALD);

        ItemMeta meta = amulet.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Amulet of Resistance");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "This amulet provides resistance when held in the action bar.");
        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        amulet.setItemMeta(meta);

        amulet.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        player.getInventory().addItem(amulet);
        ResistanceTask.checkResistance(player, resistanceLevel);
        player.sendMessage(ChatColor.GREEN + "You have been given the Amulet of Resistance!");

        return true;
    }
}
