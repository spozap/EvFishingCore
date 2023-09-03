package dev.spozap.evfishingcore.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.managers.FishingManager;
import dev.spozap.evfishingcore.models.FishingRegion;

import java.util.Map;

public class RegionsSubCmd {

    public CommandAPICommand register() {
        return new CommandAPICommand("regions")
                .withSubcommands(
                        get()
                );
    }

    protected CommandAPICommand get() {
        return new CommandAPICommand("get")
                .withArguments(new StringArgument("region"))
                .executesPlayer((player, args) -> {
                   String region = (String) args.get("region");
                   player.sendMessage("La region es : " + region);
                });
    }

}
