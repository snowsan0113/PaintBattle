package snowsan0113.paintbattle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import snowsan0113.paintbattle.command.GameStartCommand;
import snowsan0113.paintbattle.command.TeamCommand;
import snowsan0113.paintbattle.command.WeaponCommand;
import snowsan0113.paintbattle.listener.InventoryClickListener;
import snowsan0113.paintbattle.listener.PlayerClickListener;
import snowsan0113.paintbattle.manager.ScoreboardManager;
import snowsan0113.paintbattle.manager.weapon.RollerWeapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //command
        getCommand("paintbattle_start").setExecutor(new GameStartCommand());
        getCommand("paintbattle_team").setExecutor(new TeamCommand());
        getCommand("paintbattle_weapon").setExecutor(new WeaponCommand());

        //listener
        PluginManager plm = getServer().getPluginManager();
        plm.registerEvents(new PlayerClickListener(), this);
        plm.registerEvents(new InventoryClickListener(), this);

        //other
        for (Player online : Bukkit.getOnlinePlayers()) {
            ScoreboardManager.getInstance(online.getUniqueId()).setScoreboard();
        }

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
