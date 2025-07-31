package snowsan0113.paintbattle.manager.weapon;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import snowsan0113.paintbattle.Main;
import snowsan0113.paintbattle.util.ChatUtil;

public class RollerWeapon implements Weapon {

    private final Player player;
    private final BukkitTask task;
    private float health;
    private boolean canUse;

    public RollerWeapon(Player player) {
        this.player = player;
        this.health = WeaponType.ROLLER.getHealth();
        this.canUse = true;
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getItemInHand().getType() == WeaponType.ROLLER.getItem().getType()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("残りの武器体力：" + health));
                    if (canUseWeapon()) {
                        Block block = player.getLocation().clone().subtract(0, 1, 0).getBlock();
                        if (block.getType() == Material.WHITE_WOOL) {
                            block.setType(Material.RED_WOOL);
                            health--;
                        }
                    }

                    //flag
                    canUse = (health > 0);
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 2L);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public WeaponType getType() {
        return WeaponType.ROLLER;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public boolean canUseWeapon() {
        return canUse;
    }

    public BukkitTask getTask() {
        return task;
    }

    @Override
    public void runClickAction() {
        Location p_loc = player.getLocation();
        Vector p_direction = p_loc.getDirection().clone();
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVelocity(p_direction.normalize().multiply(2));
    }

    @Override
    public void paintWool(Location location) {

    }

    @Override
    public void reset() {
        ChatUtil.sendMessage(player, "武器をリロードしています");
        new BukkitRunnable() {
            @Override
            public void run() {
                health = WeaponType.ROLLER.getHealth();
                ChatUtil.sendMessage(player, "武器をリロードしました!");
            }
        }.runTaskLater(Main.getPlugin(Main.class), 20L);
    }
}
