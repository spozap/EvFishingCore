package dev.spozap.evfishingcore.loot;

import dev.spozap.evfishingcore.loot.LootItem;
import dev.spozap.evfishingcore.loot.LootTier;

public class Fish extends LootItem {

    private int maxHeight, maxLength;
    private LootTier tier;

    public Fish() {
        super();
    }

    public int getMaxHeight() {
        return maxHeight;
    }
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }
    public int getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}