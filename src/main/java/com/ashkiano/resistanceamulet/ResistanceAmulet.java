package com.ashkiano.resistanceamulet;

import org.bukkit.plugin.java.JavaPlugin;

public class ResistanceAmulet extends JavaPlugin {

    private static ResistanceAmulet instance;

    public void onEnable() {
        // Saves the default config if one does not exist
        saveDefaultConfig();

        String commandPermission = getConfig().getString("command-permission");
        int resistanceLevel = getConfig().getInt("resistance-level");
        String amuletName = getConfig().getString("amulet-name");

        instance = this;
        this.getCommand("resistanceamulet").setExecutor(new ResistanceAmuletCommand(commandPermission, resistanceLevel, amuletName));

        getServer().getPluginManager().registerEvents(new InventoryChangeListener(resistanceLevel), this);

        Metrics metrics = new Metrics(this, 19189);

        this.getLogger().info("Thank you for using the ResistanceAmulet plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    public void onDisable() {
        instance = null;
    }

    public static ResistanceAmulet getPlugin() {
        return instance;
    }
}