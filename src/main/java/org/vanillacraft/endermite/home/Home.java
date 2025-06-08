package org.vanillacraft.endermite.home;

import org.bukkit.Location;
import org.vanillacraft.endermite.endermite.EndermiteLocation;

import java.io.*;
import java.util.ArrayList;

public class Home implements Serializable {
    public static ArrayList<Home> homes = new ArrayList<>();

    public String name;
    public EndermiteLocation location;
    public String creator;

    public Home(String name, EndermiteLocation location, String creator) {
        this.name = name;
        this.location = location;
        this.creator = creator;
    }

    public Home(String name, Location location, String creator) {
        this(name, new EndermiteLocation(location), creator);
    }

    public static void saveHomesToFile(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(homes);
        oos.close();
    }

    public static void loadHomesFromFile(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        homes = (ArrayList<Home>) ois.readObject();
        ois.close();
    }
}
