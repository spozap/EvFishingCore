package dev.spozap.evfishingcore.data;

import com.google.common.base.Enums;
import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.loot.Fish;
import dev.spozap.evfishingcore.loot.LootItem;
import dev.spozap.evfishingcore.loot.LootTier;
import dev.spozap.evfishingcore.regions.FishingRegion;
import dev.spozap.evfishingcore.regions.RegionLootTable;
import org.bukkit.Material;
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

    public Map<String, LootItem> loadFishes() {
        Map<String, LootItem> fishes = new HashMap<>();
        FileConfiguration config = fishesConfigFile.get();

        if (config.getConfigurationSection("fishes") != null) {

            ConfigurationSection fishesSection = config.getConfigurationSection("fishes");
            Set<String> fishKeys = fishesSection.getKeys(false);

            for(String key : fishKeys) {

                ConfigurationSection fishSection = fishesSection.getConfigurationSection(key);
                Material material = Material.STONE;

                Fish fish = new Fish();

                if (fishSection.contains("display-name")) {
                    fish.setName(fishSection.getString("display-name"));
                }

                if (fishSection.contains("max-weight")) {
                    fish.setMaxWeight(fishSection.getDouble("max-weight"));
                }

                if (fishSection.contains("max-length")) {
                    fish.setMaxLength(fishSection.getDouble("max-length"));
                }

                if  (fishSection.contains("material")) {
                    Material confMaterial = Material.matchMaterial(fishSection.getString("material"));

                    if (confMaterial != null) {
                        material = confMaterial;
                    }

                }

                fish.setMaterial(material);

                if (fishSection.contains("tier")) {
                    Optional<LootTier> tierConfig = Enums.getIfPresent(LootTier.class, fishSection.getString("tier")).toJavaUtil();

                    LootTier tier = tierConfig.orElse(LootTier.COMMON);
                    fish.setTier(tier);

                }

                fishes.put(key, fish);

            }

        }

        return fishes;
    }

    public Map<String, FishingRegion> loadRegions() {

        FileConfiguration config = regionsConfigFile.get();

        Map<String, FishingRegion> regions = new HashMap<>();
        Map<String, LootItem> fishes = loadFishes();

        if (config.getConfigurationSection("regions") != null) {

            ConfigurationSection regionsSection = config.getConfigurationSection("regions");
            Set<String> regionKeys = regionsSection.getKeys(false);

            for(String key : regionKeys) {

                FishingRegion region = new FishingRegion();
                RegionLootTable lootTable = new RegionLootTable();
                region.setId(key);

                ConfigurationSection regionSection = regionsSection.getConfigurationSection(key);


                for (String fishInRegion : regionSection.getStringList("fishes")) {

                    LootItem fish = (Fish) fishes.get(fishInRegion);
                    lootTable.addLoot(fish);

                }

                region.setLootTable(lootTable);
                regions.put(key, region);

            }

        }

        return regions;
    }
}

