package snowsan0113.paintbattle.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.nullness.qual.Nullable;
import snowsan0113.paintbattle.Main;
import snowsan0113.paintbattle.manager.AreaPaintManager;
import snowsan0113.paintbattle.manager.TeamManager;
import snowsan0113.paintbattle.manager.weapon.Weapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;
import snowsan0113.paintbattle.util.ChatUtil;

import java.util.UUID;

public class LaunchHitListener implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        Block hit_block = event.getHitBlock();

        if (hit_block == null) return;
        if (entity.getType() == EntityType.SNOWBALL) {
            if (hit_block.getType() == Material.WHITE_WOOL || AreaPaintManager.paint_wool_map.containsValue(hit_block.getType())) {
                MetadataValue uuid_meta = entity.getMetadata("player_uuid").get(0); //MetadataからUUIDを取得する
                UUID player_uuid = UUID.fromString(uuid_meta.asString()); //発射したプレイヤーのUUID
                OfflinePlayer player = Bukkit.getOfflinePlayer(player_uuid); //発射したプレイヤー
                TeamManager.GameTeam team = TeamManager.getInstance().getJoinGameTeam(player); //プレイヤーが所属しているチーム
                @Nullable Weapon weapon = WeaponManager.getInstance().getWeapon(player); //発射したプレイヤーの武器

                if (AreaPaintManager.paint_wool_map.get(team) == hit_block.getType()) return; //当たった場所とプレイヤーの羊毛が一緒だった場合、終了する
                weapon.paintWool(hit_block.getLocation()); //当たった場所に塗る
            }
        }
    }
}
