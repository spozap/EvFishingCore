package dev.spozap.evfishingcore.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;

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
