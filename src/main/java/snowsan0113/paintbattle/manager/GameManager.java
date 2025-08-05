package snowsan0113.paintbattle.manager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.checkerframework.checker.nullness.qual.Nullable;
import snowsan0113.paintbattle.Main;
import snowsan0113.paintbattle.manager.weapon.Weapon;
import snowsan0113.paintbattle.manager.weapon.WeaponManager;
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
        this.game_time = 10;
        this.status = GameStatus.WAIITNG;
        this.world = Bukkit.getWorlds().get(0);
    }

    public static GameManager getInstance() {
        return instance;
    }

    public int startGame() {
        if (task == null) {
            TeamManager team = TeamManager.getInstance();
            this.task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (status == GameStatus.WAIITNG || status == GameStatus.CONNTING) {
                        status = GameStatus.CONNTING;
                        if (count_time == 0) {
                            ChatUtil.sendGlobalMessage("ゲーム開始!");
                            status = GameStatus.RUNNING;

                            for (Player online : Bukkit.getOnlinePlayers()) {
                                if (team.getJoinGameTeam(online) == TeamManager.GameTeam.RED) {
                                    online.teleport(LocationManager.getLocation(LocationManager.LocationPathType.RED_SPAWN, world));
                                }
                                else if (team.getJoinGameTeam(online) == TeamManager.GameTeam.BLUE) {
                                    online.teleport(LocationManager.getLocation(LocationManager.LocationPathType.BLUE_SPAWN, world));
                                }

                                WeaponManager weaponManager = WeaponManager.getInstance();
                                @Nullable Weapon weapon = weaponManager.getWeapon(online);
                                PlayerInventory inv = online.getInventory();
                                inv.clear();
                                ScoreboardManager.getInstance(online.getUniqueId()).setScoreboard();

                                if (weapon != null) {
                                    inv.addItem(weapon.getType().getItem());
                                }
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
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.setGameMode(GameMode.SPECTATOR);
                            }

                            AreaPaintManager areaPaintManager = AreaPaintManager.getInstance();
                            ChatUtil.sendGlobalMessage("ゲーム終了。結果は..");
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    double red_percent = areaPaintManager.getPaintPercent(TeamManager.GameTeam.RED);
                                    double blue_percent = areaPaintManager.getPaintPercent(TeamManager.GameTeam.BLUE);

                                    StringBuilder builder = new StringBuilder();
                                    if (red_percent > blue_percent) {
                                        builder.append("赤の勝ち");
                                    }
                                    else if (red_percent < blue_percent) {
                                        builder.append("青の勝ち");
                                    }
                                    else if (red_percent == blue_percent){
                                        builder.append("引き分け");
                                    }

                                    String red_string = String.format("%.2f", red_percent * 100);
                                    String blue_string = String.format("%.2f", blue_percent * 100);
                                    ChatUtil.sendGlobalMessage("==========" + "\n" +
                                            "赤：" + red_string + "%　青：" + blue_string + "% \n" +
                                            "で、" + builder.toString() + "です！" + "\n" +
                                            "==========");

                                    for (Player online : Bukkit.getOnlinePlayers()) {
                                        online.setGameMode(GameMode.ADVENTURE);
                                        online.teleport(LocationManager.getLocation(LocationManager.LocationPathType.LOBBY, world));
                                    }
                                }
                            }.runTaskLater(Main.getPlugin(Main.class), 20*2);
                            this.cancel();
                        }
                        else {
                            game_time--;
                        }
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
