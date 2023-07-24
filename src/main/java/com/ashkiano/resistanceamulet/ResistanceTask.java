package com.ashkiano.resistanceamulet;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ResistanceTask {

    public static void checkResistance(HumanEntity entity, Integer resistanceLevel) {
        if (!(entity instanceof Player)) return;

        Player player = (Player) entity;

        for (int i = 0; i < 9; i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item == null) continue;

            ItemMeta meta = item.getItemMeta();
            if (meta == null) continue;

            List<String> lore = meta.getLore();
            if (lore == null) continue;

            String amuletLore = ChatColor.GRAY + "This amulet provides resistance when held in the action bar.";
            if (lore.contains(amuletLore)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, resistanceLevel-1, false, false, false));
                return;
            }
        }

        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    }
}
