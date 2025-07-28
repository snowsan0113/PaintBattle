package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

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

    public Map<OfflinePlayer, Weapon> getMap() {
        return weapon_map;
    }

}
