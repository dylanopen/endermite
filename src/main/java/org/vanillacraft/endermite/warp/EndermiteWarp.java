package org.vanillacraft.endermite.warp;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import org.vanillacraft.endermite.endermite.Endermite;

import java.io.IOException;
import java.util.HashMap;

public class EndermiteWarp {
    LiteralArgumentBuilder<CommandSourceStack> cmdWarp;
    LiteralArgumentBuilder<CommandSourceStack> cmdXwarp;
    LiteralArgumentBuilder<CommandSourceStack> cmdCreatewarp;
    LiteralArgumentBuilder<CommandSourceStack> cmdSetwarp;
    LiteralArgumentBuilder<CommandSourceStack> cmdDelwarp;
    LiteralArgumentBuilder<CommandSourceStack> cmdWarps;

    static Endermite plugin;

    public EndermiteWarp(Endermite plugin) {
        try {
            Warp.loadWarpsFromFile(plugin.getDataFolder() + "/warps.endermite");
        } catch (IOException e) {
            Warp.warps = new HashMap<>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cmdWarps = Commands.literal("warps")
                .executes(ctx -> CmdWarps.run(plugin, ctx));

        cmdWarp = Commands.literal("warp")
                .then(Commands.argument("warpName", StringArgumentType.word())
                        .executes(ctx -> CmdWarp.run(plugin, ctx, false)));
        cmdXwarp = Commands.literal("xwarp")
                .then(Commands.argument("warpName", StringArgumentType.word())
                .executes(ctx -> CmdWarp.run(plugin, ctx, true)));

        cmdCreatewarp = Commands.literal("createwarp")
                .then(Commands.argument("warpName", StringArgumentType.word())
                        .executes(ctx -> CmdCreatewarp.run(plugin, ctx)));

        cmdSetwarp = Commands.literal("setwarp")
                .then(Commands.argument("warpName", StringArgumentType.word())
                        .executes(ctx -> CmdCreatewarp.run(plugin, ctx)));

        cmdDelwarp = Commands.literal("delwarp")
                .then(Commands.argument("warpName", StringArgumentType.word())
                        .executes(ctx -> CmdDelwarp.run(plugin, ctx)));
    }

    public void register(ReloadableRegistrarEvent<Commands> commands) {
        commands.registrar().register(cmdWarp.build());
        commands.registrar().register(cmdXwarp.build());
        commands.registrar().register(cmdCreatewarp.build());
        commands.registrar().register(cmdSetwarp.build());
        commands.registrar().register(cmdDelwarp.build());
        commands.registrar().register(cmdWarps.build());
    }

    public static void saveAll() {
        try {
            Warp.saveWarpsToFile(Endermite.inst.getDataFolder() + "/warps.endermite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
