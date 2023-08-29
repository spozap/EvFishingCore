package dev.spozap.evfishingcore.models;

public abstract class LootItem {

    private String name;
    private LootTier tier;

    public LootItem() {
    }
    public LootItem(String name, LootTier tier) {
        this.name = name;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }
    public LootTier getTier() {
        return tier;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTier(LootTier tier) {
        this.tier = tier;
    }
}
