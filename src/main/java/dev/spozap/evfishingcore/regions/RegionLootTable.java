package dev.spozap.evfishingcore.regions;

import dev.spozap.evfishingcore.loot.LootItem;
import dev.spozap.evfishingcore.loot.LootTier;

import java.util.*;

public class RegionLootTable {
    private Map<LootTier, List<LootItem>> lootByTier;
    public RegionLootTable() {
        lootByTier = new EnumMap<>(LootTier.class);

        for (LootTier tier : LootTier.values()) {
            lootByTier.put(tier, new ArrayList<>());
        }
    }

    public void addLoot(LootItem item) {
        List<LootItem> items = lootByTier.get(item.getTier());
        items.add(item);
    }
    public boolean hasLoot(LootTier tier) {
        List<LootItem> loot = lootByTier.get(tier);
        return loot != null && !loot.isEmpty();
    }

    public LootItem getLootByTier(LootTier tier) {
        List<LootItem> items = lootByTier.get(tier);

        if (items == null || items.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomSize = random.nextInt(items.size());
        LootItem item = items.get(randomSize);

        return item;
    }

}
