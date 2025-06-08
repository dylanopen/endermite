package org.vanillacraft.endermite.warp;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

public class CmdCreatewarp {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx) {
        String warpName = ctx.getArgument("warpName", String.class);
        if (Warp.warps.containsKey(warpName)) {
            ctx.getSource().getExecutor().sendMessage("§cSorry, but the warp §l§b" + warpName + "§r§c already exists. To replace it, first run §b/delwarp " + warpName + "§c.§r");
            return 6;
        }
        String creator = ctx.getSource().getExecutor().getName();
        Warp warp = new Warp(warpName, ctx.getSource().getLocation(), creator);
        Warp.warps.put(warpName, warp);
        ctx.getSource().getExecutor().sendMessage("§dSuccessfully created warp §l§b" + warpName + "§r§d at position [§l§b" + warp.location.toString() + "§d in §b" + warp.location.world + "§r§d].");
        EndermiteWarp.saveAll();
        return 0;
    }
}
