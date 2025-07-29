package snowsan0113.paintbattle.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import snowsan0113.paintbattle.manager.weapon.Weapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;

public class PlayerClickListener implements Listener {
    
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        WeaponManager weapon_manager = WeaponManager.getInstance();
        Weapon player_weapon = weapon_manager.getWeapon(player);
        if (player_weapon != null) {
            player_weapon.runClickAction();
        }
    } 
    
}
