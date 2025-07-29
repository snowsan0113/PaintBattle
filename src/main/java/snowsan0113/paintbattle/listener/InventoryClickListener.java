package snowsan0113.paintbattle.listener;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import snowsan0113.paintbattle.manager.weapon.RollerWeapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;
import snowsan0113.paintbattle.manager.weapon.WeaponType;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack current = event.getCurrentItem();
        InventoryView view = event.getView();
        int slot = event.getSlot();
        HumanEntity player = view.getPlayer();
        WeaponManager weapon = WeaponManager.getInstance();
        if (current == null) return;
        if (view.getTitle().equalsIgnoreCase(ChatColor.BLACK + "武器を選択してください")) {
            for (WeaponType type : WeaponType.values()) {
                if (type != WeaponType.UNKNOWN) {
                    if (current.getType() == WeaponType.ROLLER.getBlock()) {
                        weapon.setWeapon((OfflinePlayer) player, new RollerWeapon((Player) player));
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}
