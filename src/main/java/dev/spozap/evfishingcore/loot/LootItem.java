package dev.spozap.evfishingcore.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class LootItem {

    private String name;
    private LootTier tier;
    private Material material;
    public LootItem() {
    }
    public LootItem(String name, LootTier tier, Material material) {
        this.name = name;
        this.tier = tier;
        this.material = material;
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
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public abstract ItemStack toItemStack();
}
