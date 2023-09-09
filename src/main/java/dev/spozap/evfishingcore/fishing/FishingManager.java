package dev.spozap.evfishingcore.fishing;

import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.fishing.rod.RodHandler;
import dev.spozap.evfishingcore.loot.Fish;
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
    private RodHandler rodHandler;

    public FishingManager(EvFishingCore plugin) {
        this.plugin = plugin;
        this.fishingRegionManager = plugin.getFishingRegionManager();
        this.lootManager = plugin.getLootManager();
        this.rodHandler = new RodHandler();
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

                System.out.println("El tier es: " + reward.getTier().getLabel());

                if (reward instanceof Fish) {
                    ItemStack rod = p.getInventory().getItem(FishingConstants.FISHING_ROD_SLOT);
                    ItemMeta rodMeta = rod.getItemMeta();
                    ItemMeta updatedMeta = rodHandler.updateRodStats(rodMeta);
                    rod.setItemMeta(updatedMeta);
                }

                ItemStack lootItem = reward.toItemStack();
                caughtItem.setItemStack(lootItem);

                p.sendMessage("Has pescado el pez " + ChatColor.translateAlternateColorCodes('&', reward.getName() + "&f")
                + " en la región de pesca " + currentRegion.getId() + " con tier: "
                + ChatColor.translateAlternateColorCodes('&', reward.getTier().getLabel()));
            }

        } else {
            p.sendMessage("No estás en una región de pesca.");
        }


    }

}

