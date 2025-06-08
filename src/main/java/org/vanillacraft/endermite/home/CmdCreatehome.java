package org.vanillacraft.endermite.home;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

public class CmdCreatehome {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx) {
        String playerName = ctx.getSource().getExecutor().getName();
        String homeName = ctx.getArgument("homeName", String.class);
        for (Home home : Home.homes) {
            if (home.name.equalsIgnoreCase(homeName) && home.creator.equalsIgnoreCase(playerName)) {
                ctx.getSource().getExecutor().sendMessage("§cSorry, but you already have a home called §l§b" + homeName + "§r§c. To replace it, first run §b/delhome " + homeName + "§c.§r");
                return 6;
            }
        }
        String creator = ctx.getSource().getExecutor().getName();
        Home home = new Home(homeName, ctx.getSource().getLocation(), creator);
        Home.homes.add(home);
        ctx.getSource().getExecutor().sendMessage("§dSuccessfully created home §l§b" + homeName + "§r§d at position [§l§b" + home.location.toString() + "§d in §b" + home.location.world + "§r§d].");
        EndermiteHome.saveAll();
        return 0;
    }
}
