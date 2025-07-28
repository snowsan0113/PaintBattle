package snowsan0113.paintbattle.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import snowsan0113.paintbattle.manager.GameManager;
import snowsan0113.paintbattle.util.ChatUtil;

public class GameStartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("paintbattle_start")) {
            ChatUtil.sendMessage(send, "ゲームを開始しています...");
            GameManager.getInstance().startGame();
        }
        return false;
    }

}
