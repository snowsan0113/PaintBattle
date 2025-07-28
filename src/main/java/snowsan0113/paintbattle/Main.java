package snowsan0113.paintbattle;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import snowsan0113.paintbattle.command.GameStartCommand;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //command
        getCommand("paintbattle_start").setExecutor(new GameStartCommand());

        //listener
        PluginManager plm = getServer().getPluginManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
