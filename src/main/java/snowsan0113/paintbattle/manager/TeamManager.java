package snowsan0113.paintbattle.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeamManager {

    private static final TeamManager instance = new TeamManager();

    private final Scoreboard new_scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private final Map<GameTeam, Team> team_map = new HashMap<>();

    private TeamManager() {
        //赤
        Team red = new_scoreboard.registerNewTeam("赤");
        red.setColor(GameTeam.RED.getColor());
        red.setPrefix(GameTeam.RED.getColor() + "");
        team_map.put(GameTeam.RED, red);

        //青
        Team blue = new_scoreboard.registerNewTeam("青");
        blue.setColor(GameTeam.BLUE.getColor());
        blue.setPrefix(GameTeam.BLUE.getColor() + "");
        team_map.put(GameTeam.BLUE, blue);
    }

    public static TeamManager getInstance() {
        return instance;
    }

    public boolean joinTeam(@NonNull GameTeam team, @NonNull Player player) {
        if (!isJoinTeam(player)) {
            Team board_team = getConvertBoardTeam(team);
            board_team.addEntry(player.getName());
            player.setPlayerListName(team.getColor() + player.getName());
            return true;
        }
        return false;
    }

    public boolean leaveTeam(@NonNull OfflinePlayer player) {
        if (isJoinTeam(player)) {
            Team board_team = getJoinBoardTeam(player);
            board_team.removeEntry(player.getName());
            if (player.isOnline()) {
                player.getPlayer().setPlayerListName(ChatColor.RESET + player.getName());
            }
            return true;
        }
        return false;
    }

    public void clearTeam() {
        for (Team team : team_map.values()) {
            for (String entry : team.getEntries()) {
                team.removeEntry(entry);
            }
        }
    }

    public boolean isJoinTeam(@NonNull OfflinePlayer player) {
        for (Map.Entry<GameTeam, Team> entry : team_map.entrySet()) {
            GameTeam game_team = entry.getKey();
            Team board_team = entry.getValue();
            if (board_team.getEntries().contains(player.getName())) {
                return true;
            }
        }
        return false;
    }

    /**

     */
    public void assignOnlinePlayer(List<Player> exclude_players) {
        List<Player> assign_player = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (exclude_players != null) assign_player.removeAll(exclude_players);

        clearTeam();
        Collections.shuffle(assign_player);

        int half = assign_player.size() / 2;

        List<GameTeam> team_list = Arrays.asList(GameTeam.RED, GameTeam.BLUE);
        Collections.shuffle(team_list);
        for (int n = 0; n < half; n++) {
            Player player = assign_player.get(n);
            joinTeam(team_list.get(0), player);
        }
        for (int n = half; n < assign_player.size(); n++) {
            Player player = assign_player.get(n);
            joinTeam(team_list.get(1), player);
        }
    }

    public void assignPlayer(Player player) {
        Set<String> red_entry = team_map.get(GameTeam.RED).getEntries();
        Set<String> blue_entry = team_map.get(GameTeam.BLUE).getEntries();

        if (red_entry.size() > blue_entry.size()) {
            joinTeam(GameTeam.BLUE, player);
        }
        else if (red_entry.size() < blue_entry.size()) {
            joinTeam(GameTeam.RED, player);
        }
        else {
            joinTeam(GameTeam.RED, player);
        }
    }

    /**
     * ゲームチームから、スコアボードのチームを取得するもの
     * @param team 取得したいチーム
     */
    public Team getConvertBoardTeam(GameTeam team) {
        return team_map.get(team);
    }

    /**
     * スコアボードのチームから、ゲームチームを取得するもの
     * @param team 取得したいチーム
     */
    public GameTeam getConvertGameTeam(Team team) {
        for (Map.Entry<GameTeam, Team> entry : team_map.entrySet()) {
            GameTeam game_team = entry.getKey();
            Team board_team = entry.getValue();
            if (board_team.getName().equalsIgnoreCase(team.getName())) {
                return game_team;
            }
        }
        return null;
    }

    /**
     * プレイヤーがどのチームに所属しているかを返す
     * @return スコアボードのチームを返す。見つからない場合はnull
     */
    public Team getJoinBoardTeam(OfflinePlayer player) {
        for (Map.Entry<GameTeam, Team> entry : team_map.entrySet()) {
            GameTeam game_team = entry.getKey();
            Team board_team = entry.getValue();

            Set<String> team_player_set = board_team.getEntries();
            for (String team_player_name : team_player_set) {
                OfflinePlayer team_player = Bukkit.getOfflinePlayer(team_player_name);
                if (player.equals(team_player)) {
                    return board_team;
                }
            }
        }
        return null;
    }

    /**
     * プレイヤーがどのチームに所属しているかを返す
     * @return ゲームチームを返す。見つからない場合はnull
     */
    public GameTeam getJoinGameTeam(OfflinePlayer player) {
        Team board_team = getJoinBoardTeam(player);
        if (board_team != null) {
            return getConvertGameTeam(board_team);
        }

        return null;
    }

    public Scoreboard getScoreboard() {
        return new_scoreboard;
    }

    public enum GameTeam {
        RED(ChatColor.RED, "赤"),
        BLUE(ChatColor.BLUE, "青"),
        GREEN(ChatColor.GREEN, "緑"),
        YELLOW(ChatColor.YELLOW, "黄");

        private final ChatColor color;
        private final String team_string;

        GameTeam(ChatColor color, String team_string) {
            this.color = color;
            this.team_string = team_string;
        }

        public ChatColor getColor() {
            return color;
        }

        public String getTeamString() {
            return team_string;
        }
    }

}
