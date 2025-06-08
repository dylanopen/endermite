package org.vanillacraft.endermite.home;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

public class CmdDelhome {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx) {
        String homeName = ctx.getArgument("homeName", String.class);
        String playerName = ctx.getSource().getExecutor().getName();

        for (Home home : Home.homes) {
            if (!home.name.equalsIgnoreCase(homeName)) { continue; }
            if (!home.creator.equalsIgnoreCase(playerName)) { continue; }
            Home.homes.remove(home);
            ctx.getSource().getExecutor().sendMessage("§dSuccessfully removed home §l§b" + homeName + "§r§d. Create it in a new place with §b/createhome" + homeName + "§r§d.");
            EndermiteHome.saveAll();
        }
        ctx.getSource().getExecutor().sendMessage("§cSorry, but you don't have a home called §l§b" + homeName + "§r§c.");
        return 0;
    }
}
