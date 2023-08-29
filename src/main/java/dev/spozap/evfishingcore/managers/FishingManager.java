package dev.spozap.evfishingcore.managers;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.models.FishingLootTable;
import dev.spozap.evfishingcore.models.FishingRegion;
import dev.spozap.evfishingcore.models.LootItem;
import dev.spozap.evfishingcore.models.LootTier;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class FishingManager {

    private final EvFishingCore plugin;
    private RegionContainer regionContainer;
    private Map<String, FishingRegion> fishingRegions;
    private Map<LootTier, Double> lootProbabilities;

    public FishingManager(EvFishingCore plugin) {
        this.plugin = plugin;
        this.regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        this.fishingRegions = plugin.getConfigManager().loadRegions();

        lootProbabilities = new HashMap<>();
        lootProbabilities.put(LootTier.COMMON, 0.8);
        lootProbabilities.put(LootTier.EPIC, 0.15);
        lootProbabilities.put(LootTier.LEGENDARY, 0.05);

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

            FishingLootTable lootTable = currentRegion.getLootTable();
            LootTier rewardTier = getLootItemTier();

            if (lootTable.hasLoot(rewardTier)) {

                LootItem reward = lootTable.getLootByTier(rewardTier);

                ItemStack fishItem = new ItemStack(Material.PUFFERFISH);
                ItemMeta itemMeta = fishItem.getItemMeta();

                String name = reward.getName();
                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

                fishItem.setItemMeta(itemMeta);
                caughtItem.setItemStack(fishItem);


                p.sendMessage("Has pescado el pez " + ChatColor.translateAlternateColorCodes('&', name + "&f")
                + " en la región de pesca " + currentRegion.getId() + " con tier: "
                + ChatColor.translateAlternateColorCodes('&', reward.getTier().getLabel()));
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

    public LootTier getLootItemTier() {
        double random = new Random().nextDouble();
        LootTier tier = LootTier.COMMON;

        for(Map.Entry<LootTier, Double> entry : lootProbabilities.entrySet() ) {
            if (random <= entry.getValue()) {
                tier = entry.getKey();
                break;
            }
        }

        return tier;

    }
}

