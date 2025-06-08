package org.vanillacraft.endermite.home;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import org.vanillacraft.endermite.endermite.Endermite;

import java.io.IOException;
import java.util.ArrayList;

public class EndermiteHome {
    LiteralArgumentBuilder<CommandSourceStack> cmdHome;
    LiteralArgumentBuilder<CommandSourceStack> cmdXhome;
    LiteralArgumentBuilder<CommandSourceStack> cmdCreatehome;
    LiteralArgumentBuilder<CommandSourceStack> cmdSethome;
    LiteralArgumentBuilder<CommandSourceStack> cmdDelhome;
    LiteralArgumentBuilder<CommandSourceStack> cmdHomes;

    static Endermite plugin;

    public EndermiteHome(Endermite plugin) {
        try {
            Home.loadHomesFromFile(plugin.getDataFolder() + "/homes.endermite");
        } catch (IOException e) {
            Home.homes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        cmdHomes = Commands.literal("homes")
                .executes(ctx -> CmdHomes.run(plugin, ctx));

        cmdHome = Commands.literal("home")
                .then(Commands.argument("homeName", StringArgumentType.word())
                        .executes(ctx -> CmdHome.run(plugin, ctx, false)));
        cmdXhome = Commands.literal("xhome")
                .then(Commands.argument("homeName", StringArgumentType.word())
                .executes(ctx -> CmdHome.run(plugin, ctx, true)));

        cmdCreatehome = Commands.literal("createhome")
                .then(Commands.argument("homeName", StringArgumentType.word())
                        .executes(ctx -> CmdCreatehome.run(plugin, ctx)));

        cmdSethome = Commands.literal("sethome")
                .then(Commands.argument("homeName", StringArgumentType.word())
                        .executes(ctx -> CmdCreatehome.run(plugin, ctx)));

        cmdDelhome = Commands.literal("delhome")
                .then(Commands.argument("homeName", StringArgumentType.word())
                        .executes(ctx -> CmdDelhome.run(plugin, ctx)));
    }

    public void register(ReloadableRegistrarEvent<Commands> commands) {
        commands.registrar().register(cmdHome.build());
        commands.registrar().register(cmdXhome.build());
        commands.registrar().register(cmdCreatehome.build());
        commands.registrar().register(cmdSethome.build());
        commands.registrar().register(cmdDelhome.build());
        commands.registrar().register(cmdHomes.build());
    }

    public static void saveAll() {
        try {
            Home.saveHomesToFile(Endermite.inst.getDataFolder() + "/homes.endermite");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
