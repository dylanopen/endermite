package org.vanillacraft.endermite.warp;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

public class CmdWarps {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx) {
        ctx.getSource().getExecutor().sendMessage("§l§e=== Server warps ===§r");
        for (String warpName : Warp.warps.keySet()) {
            ctx.getSource().getExecutor().sendMessage("§d/warp §b" + warpName);
        }
        return 0;
    }
}
