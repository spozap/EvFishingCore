package dev.spozap.evfishingcore.fishing.rod;

import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.fishing.FishingConstants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class RodHandler {
    private final EvFishingCore plugin;

    public RodHandler() {
        this.plugin = EvFishingCore.getInstance();
    }

    public ItemStack createFishingRod() {
        ItemStack fishingRod = new ItemStack(Material.FISHING_ROD);
        ItemMeta fishingRodMeta = fishingRod.getItemMeta();
        PersistentDataContainer fishingRodPdc = fishingRodMeta.getPersistentDataContainer();

        Component name = Component.text("Caña de pescar vieja")
                .decorate(TextDecoration.BOLD)
                .color(NamedTextColor.GOLD);

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("ᴇꜱᴛᴀᴅɪꜱᴛɪᴄᴀꜱ").color(NamedTextColor.GOLD));
        lore.add(createFishesCaughtComponent(0));

        fishingRodMeta.displayName(name);
        fishingRodMeta.lore(lore);

        setCustomData(fishingRodPdc, "default_rod");
        setFishesCaught(fishingRodPdc, 0);

        fishingRod.setItemMeta(fishingRodMeta);

        return fishingRod;
    }

    public ItemMeta updateRodStats(ItemMeta meta) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        int fishesCaught = getFishesCaught(pdc);
        fishesCaught++;
        setFishesCaught(pdc, fishesCaught);

        List<Component> updatedLore = meta.lore();
        updatedLore.set(updatedLore.size() - 1, createFishesCaughtComponent(fishesCaught));
        meta.lore(updatedLore);

        return meta;
    }

    private Component createFishesCaughtComponent(int fishesCaught) {
        return Component.text("| ᴘᴇᴄᴇѕ ᴘᴇѕᴄᴀᴅᴏѕ: " + fishesCaught).color(NamedTextColor.GRAY);
    }

    private void setCustomData(PersistentDataContainer pdc, String value) {
        pdc.set(new NamespacedKey(plugin, FishingConstants.ROD_CUSTOM_KEY), PersistentDataType.STRING, value);
    }

    private void setFishesCaught(PersistentDataContainer pdc, int value) {
        pdc.set(new NamespacedKey(plugin, FishingConstants.FISHES_CAUGHT_KEY), PersistentDataType.INTEGER, value);
    }

    private int getFishesCaught(PersistentDataContainer pdc) {
        return pdc.getOrDefault(new NamespacedKey(plugin, FishingConstants.FISHES_CAUGHT_KEY), PersistentDataType.INTEGER, 0);
    }
}
