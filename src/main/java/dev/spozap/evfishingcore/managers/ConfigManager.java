package dev.spozap.evfishingcore.managers;

import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.config.ConfigurationFile;

public class ConfigManager {

    private EvFishingCore plugin;
    private ConfigurationFile fishesConfigFile, regionsConfigFile;

    public ConfigManager(EvFishingCore plugin) {

        this.plugin = plugin;

        fishesConfigFile = new ConfigurationFile("items");
        fishesConfigFile.setup();

        regionsConfigFile = new ConfigurationFile("regions");
        regionsConfigFile.setup();
    }

}

