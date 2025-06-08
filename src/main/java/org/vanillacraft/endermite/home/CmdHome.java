package org.vanillacraft.endermite.home;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

import java.util.Locale;

public class CmdHome {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx, boolean xaero) {
        String playerName = ctx.getSource().getExecutor().getName();
        String homeName = ctx.getArgument("homeName", String.class);

        for (Home home : Home.homes) {
            if (!home.creator.equalsIgnoreCase(playerName)) { continue; }
            if (!home.name.equalsIgnoreCase(homeName)) { continue; }
            if (xaero) {
                String dimension = "overworld";
                if (home.location.world.contains("nether")) { dimension = "the-nether"; }
                if (home.location.world.contains("end")) { dimension = "the-end"; }
                ctx.getSource().getExecutor().sendMessage("xaero-waypoint:/home " + homeName + ":S:" + home.location.x + ":" + home.location.y + ":" + home.location.z + ":0:false:0:Internal-" + dimension);
                ctx.getSource().getExecutor().sendMessage("§dThis home is in the §b§l" + dimension.toUpperCase(Locale.ROOT) + "§r§d dimension.");
            } else {
                ctx.getSource().getExecutor().sendMessage("§dHome §l§b" + homeName + "§r§d position: [§l§b" + home.location.toString() + ", §l" + home.location.world + "§r§d].");
                ctx.getSource().getExecutor().sendMessage("§7Tip: If you use Xaero's Minimap, type /xhome " + homeName + " to get a waypoint to the home.");
            }
        }
        return 0;
    }
}
