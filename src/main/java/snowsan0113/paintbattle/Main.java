package snowsan0113.paintbattle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import snowsan0113.paintbattle.command.GameStartCommand;
import snowsan0113.paintbattle.command.TeamCommand;
import snowsan0113.paintbattle.manager.ScoreboardManager;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //command
        getCommand("paintbattle_start").setExecutor(new GameStartCommand());
        getCommand("paintbattle_team").setExecutor(new TeamCommand());

        //listener
        PluginManager plm = getServer().getPluginManager();

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
