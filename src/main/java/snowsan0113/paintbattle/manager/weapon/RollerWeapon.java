package snowsan0113.paintbattle.manager.weapon;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import snowsan0113.paintbattle.Main;

public class RollerWeapon implements Weapon {

    private final Player player;
    private final BukkitTask task;
    private float health;

    public RollerWeapon(Player player) {
        this.player = player;
        this.health = WeaponType.ROLLER.getHealth();
        this.task = new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getItemInHand().getType() == WeaponType.ROLLER.getBlock()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("残りの武器体力：" + health));
                    if (canUseWeapon()) {
                        Block block = player.getLocation().clone().subtract(0, 1, 0).getBlock();
                        if (block.getType() == Material.WHITE_WOOL) {
                            block.setType(Material.RED_WOOL);
                            health--;
                        }
                    }
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
        return (health != 0);
    }

    public BukkitTask getTask() {
        return task;
    }
}
