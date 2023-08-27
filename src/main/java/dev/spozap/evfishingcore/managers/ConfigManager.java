package dev.spozap.evfishingcore.managers;

import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.config.ConfigurationFile;
import dev.spozap.evfishingcore.models.Fish;
import dev.spozap.evfishingcore.models.FishingRegion;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

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

    public Map<String, Fish> loadFishes() {
        Map<String, Fish> fishes = new HashMap<>();
        FileConfiguration config = fishesConfigFile.get();

        if (config.getConfigurationSection("fishes") != null) {

            ConfigurationSection fishesSection = config.getConfigurationSection("fishes");
            Set<String> fishKeys = fishesSection.getKeys(false);

            for(String key : fishKeys) {

                ConfigurationSection fishSection = fishesSection.getConfigurationSection(key);

                Fish fish = new Fish();

                if (fishSection.contains("display-name")) {
                    fish.setName(fishSection.getString("display-name"));
                }

                fishes.put(key, fish);

            }

        }

        return fishes;
    }

    public Map<String, FishingRegion> loadRegions() {

        FileConfiguration config = regionsConfigFile.get();

        Map<String, FishingRegion> regions = new HashMap<>();
        Map<String, Fish> fishes = loadFishes();

        if (config.getConfigurationSection("regions") != null) {

            ConfigurationSection regionsSection = config.getConfigurationSection("regions");
            Set<String> regionKeys = regionsSection.getKeys(false);

            for(String key : regionKeys) {

                FishingRegion region = new FishingRegion();
                region.setId(key);

                ConfigurationSection regionSection = regionsSection.getConfigurationSection(key);

                List<Fish> fishesInRegion = new ArrayList<>();

                for (String fishInRegion : regionSection.getStringList("fishes")) {

                    Fish fish = fishes.get(fishInRegion);
                    if (fish != null) {
                        fishesInRegion.add(fishes.get(fishInRegion));
                    }

                }

                region.setFishes(fishesInRegion);
                regions.put(key, region); // TODO: Change this to put the region name

            }

        }

        return regions;
    }
}

