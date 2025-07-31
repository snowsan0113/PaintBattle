package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import snowsan0113.paintbattle.util.ItemUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum WeaponType {
    SHOTTER("シューター",
            "右クリックすることで塗ることができる",
            new ItemUtil(Material.STONE)
                    .setDisplayLore(ChatColor.GOLD + "シューター", Arrays.asList(ChatColor.GOLD +  "左クリックで、リロード", ChatColor.GOLD + "右クリックで、インクを発射することができる。"))
                    .build(),
            300.0F,
            ShooterWeapon.class),
    ROLLER("ローラー",
            "コロコロ転がすことで塗ることができる",
            new ItemUtil(Material.GRASS_BLOCK)
                    .setDisplayLore(ChatColor.GOLD + "ローラー", Arrays.asList(ChatColor.GOLD + "左クリックで、リロード", ChatColor.GOLD + "動くことで、インクを塗ることができる。"))
                    .build(),
            300.0F,
            RollerWeapon.class),
    TEST("テスト用",
            "テスト用の武器",
            new ItemUtil(Material.BARRIER)
                    .setDisplayLore( ChatColor.GOLD + "テスト武器", Arrays.asList("テスト用"))
                    .build(),
            10000F,
            TestWeapon.class),
    UNKNOWN("",
            "",
            new ItemUtil(Material.AIR).build(),
            0F,
            null);

    private final String weapon_string;
    private final String description;
    private final ItemStack weapon_item;
    private final float health;
    private final Class<?extends Weapon> weapon_class;

    WeaponType(String weapon_string, String description, ItemStack weapon_item, float health, Class<?extends Weapon> weapon_class) {
        this.weapon_string = weapon_string;
        this.description = description;
        this.weapon_item = weapon_item;
        this.health = health;
        this.weapon_class = weapon_class;
    }

    public String getWeaponString() {
        return weapon_string;
    }

    public ItemStack getItem() {
        return weapon_item;
    }

    public float getHealth() {
        return health;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends Weapon> getWeaponClass() {
        return weapon_class;
    }
}
