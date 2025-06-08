package org.vanillacraft.endermite.endermite;

import org.vanillacraft.endermite.warp.Warp;

import java.io.Serializable;

public class EndermiteLocation implements Serializable {
    public int x;
    public int y;
    public int z;
    public String world;

    public EndermiteLocation(int x, int y, int z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public EndermiteLocation(org.bukkit.Location bukkitLocation) {
        this.x = bukkitLocation.getBlockX();
        this.y = bukkitLocation.getBlockY();
        this.z = bukkitLocation.getBlockZ();
        this.world = bukkitLocation.getWorld().getName();
    }

    public org.bukkit.Location bukkitLocation() {
        return new org.bukkit.Location(Endermite.inst.getServer().getWorld(world), x, y, z);
    }

    public String toString() {
        return x + ", " +  y + ", " + z;
    }
}
