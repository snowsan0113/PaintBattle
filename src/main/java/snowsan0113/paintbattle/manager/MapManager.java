package snowsan0113.paintbattle.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MapManager {

    private World map;
    private final List<Location> location_list;

    public MapManager() {
        this.map = GameManager.getInstance().getWorld();;
        this.location_list = new ArrayList<>();
        setLocationList();
    }

    private void setLocationList() {
        location_list.clear();
        AreaPaintManager area = AreaPaintManager.getInstance();
        Location start = area.getAreaStart();
        Location end = area.getAreaEnd();
        int min_x = Math.min(start.getBlockX(), end.getBlockX());
        int max_x = Math.max(start.getBlockX(), end.getBlockX());

        int min_y = Math.min(start.getBlockY(), end.getBlockY());
        int max_y = Math.max(start.getBlockY(), end.getBlockY());

        int min_z = Math.min(start.getBlockZ(), end.getBlockZ());
        int max_z = Math.max(start.getBlockZ(), end.getBlockZ());

        for (int x = min_x; x <= max_x; x++) {
            for (int y = min_y; y <= max_y; y++) {
                for (int z = min_z; z <= max_z; z++) {
                    location_list.add(new Location(start.getWorld(), x, y, z));
                }
            }
        }
    }

    public List<Location> getLocationList() {
        return Collections.unmodifiableList(location_list);
    }

    public void reset() {
        location_list.stream().filter(location -> {
            Block block = location.getBlock();
            return AreaPaintManager.paint_wool_map.values().stream().anyMatch(wool -> block.getType() == wool);
        }).forEach(location -> {
            Block block = location.getBlock();
            block.setType(Material.WHITE_WOOL);
        });

    }
}
