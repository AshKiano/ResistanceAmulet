package com.ashkiano.resistanceamulet;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryChangeListener implements Listener {

    private final int resistanceLevel;

    public InventoryChangeListener(int resistanceLevel) {
        this.resistanceLevel = resistanceLevel;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        runDelayedCheck(event.getWhoClicked());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        runDelayedCheck(event.getPlayer());
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        runDelayedCheck(event.getWhoClicked());
    }

    @EventHandler
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        runDelayedCheck(event.getPlayer());
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        runDelayedCheck(event.getPlayer());
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        runDelayedCheck(event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        runDelayedCheck(event.getPlayer());
    }

    private void runDelayedCheck(HumanEntity entity) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ResistanceTask.checkResistance(entity, resistanceLevel);
            }
        }.runTaskLater(ResistanceAmulet.getPlugin(), 1L);
    }
}

