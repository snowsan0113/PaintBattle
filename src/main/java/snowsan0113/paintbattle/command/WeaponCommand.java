package snowsan0113.paintbattle.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;

public class WeaponCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("paintbattle_weapon")) {
            if (args[0].equalsIgnoreCase("select_menu")) {
                WeaponManager.getInstance().openSelectMenu((Player) send);
            }
        }
        return false;
    }

}
