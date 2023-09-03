package dev.spozap.evfishingcore.managers;

import dev.jorel.commandapi.CommandAPICommand;
import dev.spozap.evfishingcore.EvFishingCore;
import dev.spozap.evfishingcore.commands.RegionsSubCmd;

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
