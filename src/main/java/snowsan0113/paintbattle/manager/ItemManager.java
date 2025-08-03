package snowsan0113.paintbattle.manager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import snowsan0113.paintbattle.util.ItemUtil;

import java.util.Arrays;

public class ItemManager {

    public static ItemStack getItem(PluginItemType type) {
        return type.itemStack;
    }

    /**
     * PluginItemTypeのアイテムと、引数のアイテムが一緒かを返す
     * @param itemStack 比較先のアイテム
     * @return 一緒だったらtrue、一緒じゃないならはfalse
     */
    public static boolean containItem(ItemStack itemStack) {
        return Arrays.stream(PluginItemType.values())
                .findFirst()
                .filter(type -> equalItem(type, itemStack))
                .isPresent();
    }

    /**
     * PluginItemTypeのアイテムと、引数のアイテムが一緒かを返す
     * @param type 比較元のPluginItemTypeのアイテム
     * @param itemStack 比較先のアイテム
     * @return 一緒だったらtrue、一緒じゃないならはfalse
     */
    public static boolean equalItem(PluginItemType type, ItemStack itemStack) {
        ItemStack item = type.getItemStack();
        return itemStack.isSimilar(item);
    }

    public enum PluginItemType {
        WEAPON_SELECT_STAR(
                new ItemUtil(Material.NETHER_STAR)
                        .setDisplayLore("右クリックで武器を選択", Arrays.asList("右クリックで武器を選択"))
                        .build()
        );

        private final ItemStack itemStack;

        PluginItemType(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        public ItemStack getItemStack() {
            return itemStack.clone();
        }
    }
}
