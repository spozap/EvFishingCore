package dev.spozap.evfishingcore.managers;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.models.FishingRegion;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FishingManager {

    private final EvFishingCore plugin;
    private RegionContainer regionContainer;
    private Map<String, FishingRegion> fishingRegions;

    public FishingManager(EvFishingCore plugin) {
        this.plugin = plugin;
        this.regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        this.fishingRegions = new HashMap<>();

        fishingRegions.put("fishtest", new FishingRegion());
    }

    public void onFish(PlayerFishEvent event) {

        Player p = event.getPlayer();
        org.bukkit.Location hookLocation = event.getHook().getLocation();

        Location location = BukkitAdapter.adapt(hookLocation);

        ApplicableRegionSet regionSet = regionContainer.createQuery().getApplicableRegions(location);

        Optional<FishingRegion> fishingRegion = getFishingRegion(regionSet);

        if (fishingRegion.isPresent()) {
            FishingRegion currentRegion = fishingRegion.get();
            Item caughtItem = (Item) event.getCaught();

            if (!currentRegion.getFishes().isEmpty()) {
                ItemStack fishItem = new ItemStack(Material.PUFFERFISH);
                ItemMeta itemMeta = fishItem.getItemMeta();
                itemMeta.setDisplayName(currentRegion.getFishes().get(0).getName());
                fishItem.setItemMeta(itemMeta);

                caughtItem.setItemStack(fishItem);
            }

        } else {
            p.sendMessage("No estás en una región de pesca.");
        }


    }

    private Optional<FishingRegion> getFishingRegion(ApplicableRegionSet regionSet) {
        for (ProtectedRegion region : regionSet) {
            if (fishingRegions.containsKey(region.getId())) {
                return Optional.of(fishingRegions.get(region.getId()));
            }
        }
        return Optional.empty();
    }
}

