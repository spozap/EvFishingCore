package dev.spozap.evfishingcore.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.spozap.evfishingcore.EvFishingCore;

public class CommandManager {

    private EvFishingCore plugin;
    public CommandManager(EvFishingCore plugin) {
        this.plugin = plugin;
        loadCommands();
    }

    public void loadCommands() {

        new CommandAPICommand("fishingcore")
                .withAliases("fc", "fcore")
                .withSubcommands(
                        new RegionsSubCmd().register()
                )
                .register();

    }

}
