package org.vanillacraft.endermite.warp;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

public class CmdDelwarp {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx) {
        String warpName = ctx.getArgument("warpName", String.class);
        if (!Warp.warps.containsKey(warpName)) {
            ctx.getSource().getExecutor().sendMessage("§cSorry, but there is no warp called §l§b" + warpName + "§r§c.");
            return 5;
        }
        Warp.warps.remove(warpName);
        ctx.getSource().getExecutor().sendMessage("§dSuccessfully removed warp §l§b" + warpName + "§r§d. Create it in a new place with §b/createwarp " + warpName + "§r§d.");
        EndermiteWarp.saveAll();
        return 0;
    }
}
