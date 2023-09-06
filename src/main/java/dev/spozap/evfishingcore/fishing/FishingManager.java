package dev.spozap.evfishingcore.fishing;

import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.loot.LootItem;
import dev.spozap.evfishingcore.loot.LootManager;
import dev.spozap.evfishingcore.loot.LootTier;
import dev.spozap.evfishingcore.regions.FishingRegion;
import dev.spozap.evfishingcore.regions.FishingRegionManager;
import dev.spozap.evfishingcore.regions.RegionLootTable;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class FishingManager {

    private final EvFishingCore plugin;
    private FishingRegionManager fishingRegionManager;
    private LootManager lootManager;

    public FishingManager(EvFishingCore plugin) {
        this.plugin = plugin;
        this.fishingRegionManager = plugin.getFishingRegionManager();
        this.lootManager = plugin.getLootManager();

    }

    public void onFish(PlayerFishEvent event) {

        Player p = event.getPlayer();
        Location hookLocation = event.getHook().getLocation();

        Optional<FishingRegion> fishingRegion = fishingRegionManager.getFishingRegion(hookLocation);

        if (fishingRegion.isPresent()) {
            FishingRegion currentRegion = fishingRegion.get();
            Item caughtItem = (Item) event.getCaught();

            RegionLootTable lootTable = currentRegion.getLootTable();
            LootTier rewardTier = lootManager.getRandomLootTier();

            if (lootTable.hasLoot(rewardTier)) {

                LootItem reward = lootTable.getLootByTier(rewardTier);

                ItemStack fishItem = new ItemStack(reward.getMaterial());
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

}

