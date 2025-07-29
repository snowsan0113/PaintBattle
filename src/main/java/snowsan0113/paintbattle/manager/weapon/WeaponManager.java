package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;

public class WeaponManager {

    private static final WeaponManager instance = new WeaponManager();
    private Map<OfflinePlayer, Weapon> weapon_map;

    private WeaponManager() {
        this.weapon_map = new HashMap<>();
    }

    public static WeaponManager getInstance() {
        return instance;
    }

    public boolean isSetWeapon(OfflinePlayer player, WeaponType type) {
        for (Map.Entry<OfflinePlayer, Weapon> entry : weapon_map.entrySet()) {
            OfflinePlayer key_player = entry.getKey();
            if (key_player.getUniqueId().equals(player.getUniqueId())) {
                Weapon value_weapon = entry.getValue();
                return value_weapon.getType() == type;
            }
        }
        return false;
    }

    @Nullable
    public Weapon getWeapon(OfflinePlayer player) {
        return weapon_map.get(player);
    }

    public void setWeapon(OfflinePlayer player, Weapon weapon) {
        weapon_map.put(player, weapon);
    }

    public void unsetWeapon(OfflinePlayer player) {
        weapon_map.put(player, null);
    }

}
