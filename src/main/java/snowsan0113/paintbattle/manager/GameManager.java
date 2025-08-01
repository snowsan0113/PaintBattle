package snowsan0113.paintbattle.manager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import snowsan0113.paintbattle.Main;
import snowsan0113.paintbattle.util.ChatUtil;

public class GameManager {

    private static final GameManager instance = new GameManager();

    private BukkitTask task;
    private int game_time;
    private int count_time;
    private GameStatus status;
    private World world;

    private GameManager() {
        this.count_time = 10;
        this.game_time = 60*15;
        this.status = GameStatus.WAIITNG;
        this.world = Bukkit.getWorlds().get(0);
    }

    public static GameManager getInstance() {
        return instance;
    }

    public int startGame() {
        if (task == null) {
            this.task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (status == GameStatus.WAIITNG || status == GameStatus.CONNTING) {
                        status = GameStatus.CONNTING;
                        if (count_time == 0) {
                            ChatUtil.sendGlobalMessage("ゲーム開始!");
                            status = GameStatus.RUNNING;

                            for (Player online : Bukkit.getOnlinePlayers()) {
                                ScoreboardManager.getInstance(online.getUniqueId()).setScoreboard();
                            }
                        }
                        else {
                            String format = String.format("ゲーム開始まであと%d秒", count_time);
                            ChatUtil.sendGlobalMessage(format);
                            count_time--;
                        }
                    }
                    else if (status == GameStatus.RUNNING) {

                        if (game_time == 0) {
                            this.cancel();
                        }

                        game_time--;
                    }
                }
            }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);

            return 0;
        }
        else {
            return -1;
        }
    }

    public void resetGame() {

    }

    public int getTime() {
        return game_time;
    }

    public GameStatus getStatus() {
        return status;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public enum GameStatus {
        WAIITNG(0, "待機中"),
        CONNTING(1, "カウント中"),
        RUNNING(2, "実行中"),
        ENDING(3, "終了");

        private final int status;
        private final String string_status;

        GameStatus(int status, String string_status) {
            this.status = status;
            this.string_status = string_status;
        }

        public int getStatus() {
            return status;
        }

        public String getStringStatus() {
            return string_status;
        }
    }

}
