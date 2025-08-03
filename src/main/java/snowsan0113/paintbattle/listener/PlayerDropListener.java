package snowsan0113.paintbattle.listener;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import snowsan0113.paintbattle.manager.ItemManager;

public class PlayerDropListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Item item_entity = event.getItemDrop();
        ItemStack itemstack = item_entity.getItemStack();
        if (ItemManager.containItem(itemstack)) {
            event.setCancelled(true);
        }
    }

}
