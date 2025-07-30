package snowsan0113.paintbattle.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snowsan0113.paintbattle.manager.MapManager;
import snowsan0113.paintbattle.util.ChatUtil;

public class MapCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("paintbattle_map")) {
            new MapManager().reset();
            ChatUtil.sendMessage((Player) send, "ブロックをリセットしました");
        }
        return false;
    }

}
