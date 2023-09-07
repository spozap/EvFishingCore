package dev.spozap.evfishingcore.listener;

import dev.spozap.evfishingcore.fishing.FishingConstants;
import dev.spozap.evfishingcore.fishing.rod.RodHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerRodListener implements Listener {

    private RodHandler rodHandler;

    public PlayerRodListener() {
        this.rodHandler = new RodHandler();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();

        if (inventory.getItem(FishingConstants.FISHING_ROD_SLOT) != null) return;

        ItemStack defaultRod = rodHandler.createFishingRod();
        inventory.setItem(FishingConstants.FISHING_ROD_SLOT, defaultRod);
    }

}
