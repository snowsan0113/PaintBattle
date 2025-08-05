package snowsan0113.paintbattle.manager;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import snowsan0113.paintbattle.Main;

import java.util.HashMap;
import java.util.Map;

public class LocationManager {

    private static final FileConfiguration config;

    static {
        config = Main.getPlugin(Main.class).getConfig();
    }

    public static Location getLocation(LocationPathType type, World world) {
        return type.getLocation(world);
    }

    public enum LocationPathType {
        LOBBY("location.lobby"),
        RED_SPAWN("location.red_spawn"),
        BLUE_SPAWN("location.blue_spawn");

        private final String path;

        LocationPathType(String path) {
            this.path = path;
        }

        public Location getLocation(World world) {
            int x = config.getInt(path + ".x");
            int y = config.getInt(path + ".y");
            int z = config.getInt(path + ".z");
            return new Location(world, x, y, z);
        }
    }
}
