package dev.spozap.evfishingcore.managers;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.models.FishingRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Map;
import java.util.Optional;

public class FishingRegionManager {

    private EvFishingCore plugin;
    private RegionContainer regionContainer;
    private Map<String, FishingRegion> fishingRegions;

    public FishingRegionManager(EvFishingCore plugin) {
        this.plugin = plugin;
        this.regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        this.fishingRegions = plugin.getConfigManager().loadRegions();
    }

    public Optional<FishingRegion> getFishingRegion(Location location) {

        com.sk89q.worldedit.util.Location adaptedLoc = BukkitAdapter.adapt(location);
        ApplicableRegionSet regionSet = regionContainer.createQuery().getApplicableRegions(adaptedLoc);

        for (ProtectedRegion region : regionSet) {
            if (fishingRegions.containsKey(region.getId())) {
                return Optional.of(fishingRegions.get(region.getId()));
            }
        }
        return Optional.empty();
    }



}
