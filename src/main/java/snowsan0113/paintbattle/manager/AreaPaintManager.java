package snowsan0113.paintbattle.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import snowsan0113.paintbattle.Main;

import java.util.*;
import java.util.stream.Collectors;

public class AreaPaintManager {

    private static final AreaPaintManager instance = new AreaPaintManager();
    private final GameManager gameManager;
    private final FileConfiguration config;

    //util
    public static final Map<TeamManager.GameTeam, Material> paint_wool_map = Map.of(
            TeamManager.GameTeam.RED, Material.RED_WOOL,
            TeamManager.GameTeam.BLUE, Material.BLUE_WOOL,
            TeamManager.GameTeam.GREEN, Material.GREEN_WOOL,
            TeamManager.GameTeam.YELLOW, Material.YELLOW_WOOL
    );

    private AreaPaintManager() {
        this.gameManager = GameManager.getInstance();
        this.config = Main.getPlugin(Main.class).getConfig();
    }

    public static AreaPaintManager getInstance() {
        return instance;
    }

    public Location getAreaStart() {
        int x = config.getInt("area.start.x");
        int y = config.getInt("area.start.y");
        int z = config.getInt("area.start.z");

        return new Location(gameManager.getWorld(), x, y, z);
    }

    public Location getAreaEnd() {
        int x = config.getInt("area.end.x");
        int y = config.getInt("area.end.y");
        int z = config.getInt("area.end.z");

        return new Location(gameManager.getWorld(), x, y, z);
    }

    public double getPaintPercent(TeamManager.GameTeam team) {
        List<Location> loc_list = new MapManager().getLocationList();
        List<Location> white_wool_list = loc_list.stream().filter(location -> location.getBlock().getType() == Material.WHITE_WOOL).collect(Collectors.toList());
        List<Location> red_wool_list = loc_list.stream().filter(location -> location.getBlock().getType() == Material.RED_WOOL).collect(Collectors.toList());
        List<Location> blue_wool_list = loc_list.stream().filter(location -> location.getBlock().getType() == Material.BLUE_WOOL).collect(Collectors.toList());

        int all_size = (red_wool_list.size() + blue_wool_list.size() + white_wool_list.size());

        if (team == TeamManager.GameTeam.RED) {
            return (double) red_wool_list.size() / all_size;
        }
        else if (team == TeamManager.GameTeam.BLUE) {
            return (double) blue_wool_list.size() / all_size;
        }
        else {
            return 0;
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
