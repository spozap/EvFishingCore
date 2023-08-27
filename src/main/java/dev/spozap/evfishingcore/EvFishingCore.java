package dev.spozap.evfishingcore;

import dev.spozap.evfishingcore.listeners.PlayerFishListener;
import dev.spozap.evfishingcore.managers.ConfigManager;
import dev.spozap.evfishingcore.managers.FishingManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EvFishingCore extends JavaPlugin {

    private EvFishingCore plugin;
    private FishingManager fishingManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        saveDefaultConfig();
        loadManagers();

        this.getServer().getPluginManager().registerEvents(new PlayerFishListener(fishingManager), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadManagers() {
        configManager = new ConfigManager(plugin);
        fishingManager = new FishingManager(plugin);
    }

    public FishingManager getFishingManager() {
        return fishingManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
