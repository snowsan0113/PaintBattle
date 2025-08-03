package snowsan0113.paintbattle.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import snowsan0113.paintbattle.manager.GameManager;
import snowsan0113.paintbattle.manager.ItemManager;
import snowsan0113.paintbattle.manager.TeamManager;

import java.util.Arrays;
import java.util.List;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GameManager game = GameManager.getInstance();
        TeamManager team = TeamManager.getInstance();
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();

        if (game.getStatus() == GameManager.GameStatus.WAIITNG || game.getStatus() == GameManager.GameStatus.CONNTING) {
            inv.addItem(ItemManager.getItem(ItemManager.PluginItemType.WEAPON_SELECT_STAR));
        }
        else if (game.getStatus() == GameManager.GameStatus.RUNNING) {
            if (team.getJoinGameTeam(player) == null) {
                inv.addItem(ItemManager.getItem(ItemManager.PluginItemType.WEAPON_SELECT_STAR));
            }
        }
    }

}
