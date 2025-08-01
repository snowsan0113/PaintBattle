package snowsan0113.paintbattle.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import snowsan0113.paintbattle.manager.GameManager;
import snowsan0113.paintbattle.manager.TeamManager;
import snowsan0113.paintbattle.util.ChatUtil;

import java.util.Set;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        GameManager game_manager = GameManager.getInstance();
        TeamManager team_manager = TeamManager.getInstance();
        Player chat_player = event.getPlayer();
        TeamManager.GameTeam chat_team = team_manager.getJoinGameTeam(chat_player);
        Set<Player> recipient_list = event.getRecipients();
        recipient_list.removeIf(recipient_player -> {
            TeamManager.GameTeam recipient_team = team_manager.getJoinGameTeam(recipient_player);
            return chat_team != recipient_team;
        });

        //チームチャット
        if (game_manager.getStatus() == GameManager.GameStatus.RUNNING) {
            //表示形式の例：[チームチャット][赤チーム]<snowsan0113>: aaa
            String format = String.format("%s[チームチャット] %s[%s]%s<%s>:%s",
                    ChatColor.GRAY, chat_team.getColor(), chat_team.getTeamString(), ChatColor.RESET, chat_player.getName(), event.getMessage());
            event.setFormat(format);
        }

    }
}
