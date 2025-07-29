package snowsan0113.paintbattle.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    private final ItemStack itemStack;

    public ItemUtil(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemUtil(String skull_owner) {
        this.itemStack = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) this.itemStack.getItemMeta();
        meta.setOwner(skull_owner);
        this.itemStack.setItemMeta(meta);
    }

    public ItemUtil setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemUtil setDisplayLore(String name, List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            if (name != null) {
                meta.setDisplayName(name);
            }
            if (lore != null) {
                List<String> get_lore = meta.getLore() == null ? new ArrayList<>() : new ArrayList<>(meta.getLore());
                get_lore.addAll(lore);
                meta.setLore(get_lore);
            }

            itemStack.setItemMeta(meta);
        }

        return this;
    }

    public ItemStack build() {
        return itemStack;
    }

}
