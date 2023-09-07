package dev.spozap.evfishingcore;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.spozap.evfishingcore.command.CommandManager;
import dev.spozap.evfishingcore.data.ConfigManager;
import dev.spozap.evfishingcore.fishing.FishingManager;
import dev.spozap.evfishingcore.listener.PlayerFishListener;
import dev.spozap.evfishingcore.listener.PlayerRodListener;
import dev.spozap.evfishingcore.loot.LootManager;
import dev.spozap.evfishingcore.regions.FishingRegionManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EvFishingCore extends JavaPlugin {

    private static EvFishingCore instance;
    private FishingManager fishingManager;
    private ConfigManager configManager;
    private FishingRegionManager fishingRegionManager;
    private LootManager lootManager;
    private CommandManager commandManager;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        CommandAPI.onEnable();

        saveDefaultConfig();
        loadManagers();

        this.getServer().getPluginManager().registerEvents(new PlayerFishListener(fishingManager), this);
        this.getServer().getPluginManager().registerEvents(new PlayerRodListener(), this);

    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    public static EvFishingCore getInstance() {
        return instance;
    }

    public void loadManagers() {
        configManager = new ConfigManager(instance);
        commandManager = new CommandManager(instance);
        fishingRegionManager = new FishingRegionManager(instance);
        lootManager = new LootManager(instance);
        fishingManager = new FishingManager(instance);
    }

    public FishingManager getFishingManager() {
        return fishingManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
    public FishingRegionManager getFishingRegionManager() {
        return fishingRegionManager;
    }
    public LootManager getLootManager() {
        return lootManager;
    }
}
