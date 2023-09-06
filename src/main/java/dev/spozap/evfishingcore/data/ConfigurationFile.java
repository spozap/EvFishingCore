package dev.spozap.evfishingcore.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationFile {

    private File file;
    private FileConfiguration fileConfiguration;
    private String fileName;
    public ConfigurationFile(String fileName) {
        this.fileName = fileName;
    }

    public void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("EvFishingCore").getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get() {
        return fileConfiguration;
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {

        }
    }

}

