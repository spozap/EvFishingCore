package dev.spozap.evfishingcore.fishing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishListener implements Listener {

    private FishingManager manager;

    public PlayerFishListener(FishingManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {

        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            manager.onFish(event);
        }

    }

}