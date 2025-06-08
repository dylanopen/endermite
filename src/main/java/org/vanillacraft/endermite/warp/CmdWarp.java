package org.vanillacraft.endermite.warp;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.vanillacraft.endermite.endermite.Endermite;

import java.util.Locale;

public class CmdWarp {
    public static int run(Endermite plugin, CommandContext<CommandSourceStack> ctx, boolean xaero) {
        String warpName = ctx.getArgument("warpName", String.class);
        if (!Warp.warps.containsKey(warpName)) {
            ctx.getSource().getExecutor().sendMessage("§cSorry, but there is no warp called §l§b" + warpName + "§r§c. Create it with §b/createwarp " + warpName + "§c!§r");
            return 5;
        } else if (xaero) {
            Warp warp = Warp.warps.get(warpName);
            String dimension = "OVERWORLD";
            if (warp.location.world.contains("nether")) { dimension = "the-nether"; }
            if (warp.location.world.contains("end")) { dimension = "the-end"; }
            ctx.getSource().getExecutor().sendMessage("xaero-waypoint:/warp " + warpName + ":S:" + warp.location.x + ":" + warp.location.y + ":" + warp.location.z + ":0:false:0:Internal-" + dimension);
            ctx.getSource().getExecutor().sendMessage("§dThis warp is in the §b§l" + dimension.toUpperCase(Locale.ROOT) + "§r§d dimension.");
        } else {
            Warp warp = Warp.warps.get(warpName);
            ctx.getSource().getExecutor().sendMessage("§dWarp §l§b" + warpName + "§r§d position: [§l§b" + warp.location.toString() + ", §l" + warp.location.world + "§r§d] (created by §b" + warp.creator + "§d)");
            ctx.getSource().getExecutor().sendMessage("§7Tip: If you use Xaero's Minimap, type /xwarp " + warpName + " to get a waypoint to the warp.");
        }
        return 0;
    }
}
