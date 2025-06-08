package org.vanillacraft.endermite.endermite;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.vanillacraft.endermite.warp.EndermiteWarp;

import java.io.IOException;
import java.nio.file.Files;

public final class Endermite extends JavaPlugin {
    public static Endermite inst;

    @Override
    public void onEnable() {
        try {
            Files.createDirectory(getDataPath());
        } catch (IOException e) {
            // that's fine, just don't create the folder
        }
        Endermite.inst = this;
        EndermiteWarp warp = new EndermiteWarp(this);
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            warp.register(commands);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
