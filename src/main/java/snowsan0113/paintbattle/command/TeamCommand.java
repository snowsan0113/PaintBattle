package snowsan0113.paintbattle.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Team;
import snowsan0113.paintbattle.manager.TeamManager;
import snowsan0113.paintbattle.util.ChatUtil;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("paintbattle_team")) {
            TeamManager team = TeamManager.getInstance();
            Team red = team.getConvertBoardTeam(TeamManager.GameTeam.RED);
            Team blue = team.getConvertBoardTeam(TeamManager.GameTeam.BLUE);
            if (args[0].equalsIgnoreCase("list")) {
                StringBuilder builder = new StringBuilder();

                builder.append("---赤チーム--- \n");
                red.getEntries().stream().map(red_player -> red_player + ",").forEach(builder::append);
                builder.append("\n");
                builder.append("---青チーム--- \n");
                blue.getEntries().stream().map(blue_player -> blue_player + ",").forEach(builder::append);

                ChatUtil.sendMessage(sender,
                        "==============" + "\n" +
                                builder.toString() + "\n" +
                                "==============");
            }
            else if (args[0].equalsIgnoreCase("join")) {
                if (args[1].equalsIgnoreCase("red")) {
                    team.joinTeam(TeamManager.GameTeam.RED, Bukkit.getPlayer(args[2]));
                    ChatUtil.sendMessage(sender, "赤に参加させました。");
                }
                else if (args[1].equalsIgnoreCase("blue")) {
                    team.joinTeam(TeamManager.GameTeam.BLUE, Bukkit.getPlayer(args[2]));
                    ChatUtil.sendMessage(sender, "青に参加させました。");
                }
            }
            else if (args[0].equalsIgnoreCase("assign")) {
                team.assignOnlinePlayer(null);
                ChatUtil.sendMessage(sender, "チームを割り当てました。");
            }
        }
        return false;
    }

}
