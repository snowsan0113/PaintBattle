package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import snowsan0113.paintbattle.util.ItemUtil;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void openSelectMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, ChatColor.BLACK + "武器を選択してください");
        WeaponType[] values = WeaponType.values();
        for (int i = 0; i < values.length; i++) {
            WeaponType type = values[i];
            if (type != WeaponType.UNKNOWN) {
                inv.setItem(i, new ItemUtil(type.getItem().getType()).setDisplayLore(type.getWeaponString(), Arrays.asList(type.getDescription())).build());
            }
        }

        player.openInventory(inv);
    }

}
