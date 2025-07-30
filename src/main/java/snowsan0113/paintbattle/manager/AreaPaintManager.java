package snowsan0113.paintbattle.manager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import snowsan0113.paintbattle.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        List<Location> location_list = new MapManager().getLocationList();
        long wool_size = location_list.stream().filter(location -> {
            Block block = location.getBlock();
            return block.getType() == paint_wool_map.get(team);
        }).count();

        return ((double) wool_size / location_list.size()) * 100;
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
