package dev.spozap.evfishingcore.managers;

import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.models.LootTier;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LootManager {

    private Map<LootTier, Double> lootProbabilities;
    private Random random;

    public LootManager(EvFishingCore plugin) {
        this.random = new Random();
        lootProbabilities = new HashMap<>();

        lootProbabilities.put(LootTier.LEGENDARY, 0.05);
        lootProbabilities.put(LootTier.EPIC, 0.15);
        lootProbabilities.put(LootTier.COMMON, 0.8);

    }

    public LootTier getRandomLootTier() {
        double randomLoot = random.nextDouble();
        LootTier tier = LootTier.COMMON;

        for(Map.Entry<LootTier, Double> entry : lootProbabilities.entrySet() ) {
            if (randomLoot <= entry.getValue()) {
                tier = entry.getKey();
                break;
            }
        }

        return tier;

    }

}
