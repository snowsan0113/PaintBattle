package snowsan0113.paintbattle.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import snowsan0113.paintbattle.manager.ItemManager;
import snowsan0113.paintbattle.manager.weapon.Weapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;

public class PlayerClickListener implements Listener {
    
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        WeaponManager weapon_manager = WeaponManager.getInstance();
        Weapon player_weapon = weapon_manager.getWeapon(player);
        if (item == null) return;
        if (ItemManager.equalItem(ItemManager.PluginItemType.WEAPON_SELECT_STAR, item)) {
            weapon_manager.openSelectMenu(player);
        }
        if (player_weapon != null) {
            if (item.isSimilar(player_weapon.getType().getItem())) {
                if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                    player_weapon.reset();
                }
                else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    player_weapon.runClickAction();
                }
                event.setCancelled(true);
                event.setUseInteractedBlock(Event.Result.DENY);
            }
        }
    } 
    
}
