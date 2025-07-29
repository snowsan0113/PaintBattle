package snowsan0113.paintbattle.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import snowsan0113.paintbattle.manager.weapon.Weapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;

public class PlayerClickListener implements Listener {
    
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        WeaponManager weapon_manager = WeaponManager.getInstance();
        Weapon player_weapon = weapon_manager.getWeapon(player);
        if (player_weapon != null) {
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                player_weapon.reset();
            }
            else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                player_weapon.runClickAction();
            }
        }
    } 
    
}
