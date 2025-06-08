package org.vanillacraft.endermite.home;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

public class CmdHomes {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().getExecutor().sendMessage("§l§e=== Your homes ===§r");
        String playerName = ctx.getSource().getSender().getName();
        for (Home home : Home.homes) {
            if (!home.creator.equalsIgnoreCase(playerName)) { continue; }
            ctx.getSource().getExecutor().sendMessage("§d/home §b" + home.name);
        }
        return 0;
    }
}
