package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Weapon {

    Player getPlayer();
    WeaponType getType();
    float getHealth();
    boolean canUseWeapon();
    void runClickAction();
    void paintWool(Location location);
    void reset();

}
