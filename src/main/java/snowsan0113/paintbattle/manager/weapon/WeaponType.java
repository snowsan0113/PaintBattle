package snowsan0113.paintbattle.manager.weapon;

import org.bukkit.Material;

public enum WeaponType {
    SHOTTER("シューター", Material.STONE, 300.0F),
    ROLLER("ローラー", Material.GRASS_BLOCK, 300.0F);

    private final String weapon_string;
    private final Material block;
    private final float health;

    WeaponType(String weapon_string, Material block, float health) {
        this.weapon_string = weapon_string;
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
}
