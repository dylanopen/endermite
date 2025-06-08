package org.vanillacraft.endermite.warp;

import org.bukkit.Location;
import org.vanillacraft.endermite.endermite.EndermiteLocation;

import java.io.*;
import java.util.HashMap;

public class Warp implements Serializable {
    public static HashMap<String, Warp> warps = new HashMap<>();

    public String name;
    public EndermiteLocation location;
    public String creator;

    public Warp(String name, EndermiteLocation location, String creator) {
        this.name = name;
        this.location = location;
        this.creator = creator;
    }

    public Warp(String name, Location location, String creator) {
        this(name, new EndermiteLocation(location), creator);
    }

    public static void saveWarpsToFile(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(warps);
        oos.close();
    }

    public static void loadWarpsFromFile(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        warps = (HashMap<String, Warp>) ois.readObject();
        ois.close();
    }
}
