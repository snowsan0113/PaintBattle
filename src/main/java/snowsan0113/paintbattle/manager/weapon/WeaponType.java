package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.Material;

public enum WeaponType {
    SHOTTER("シューター", "右クリックすることで塗ることができる", Material.STONE, 300.0F),
    ROLLER("ローラー", "コロコロ転がすことで塗ることができる", Material.GRASS_BLOCK, 300.0F),
    UNKNOWN("", "", Material.AIR, 0F);

    private final String weapon_string;
    private final String description;
    private final Material block;
    private final float health;

    WeaponType(String weapon_string, String description, Material block, float health) {
        this.weapon_string = weapon_string;
        this.description = description;
        this.block = block;
        this.health = health;
    }

    public String getWeaponString() {
        return weapon_string;
    }

    public Material getBlock() {
        return block;
    }

    public float getHealth() {
        return health;
    }

    public String getDescription() {
        return description;
    }
}
