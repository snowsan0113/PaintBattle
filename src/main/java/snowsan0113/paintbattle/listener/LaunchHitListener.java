package snowsan0113.paintbattle.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import snowsan0113.paintbattle.Main;
import snowsan0113.paintbattle.util.ChatUtil;

public class LaunchHitListener implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        Block hit_block = event.getHitBlock();

        if (hit_block == null) return;
        if (entity.getType() == EntityType.SNOWBALL) {
            if (hit_block.getType() == Material.WHITE_WOOL) {
                hit_block.setBlockData(Bukkit.createBlockData(Material.RED_WOOL));
            }
        }
    }
}
