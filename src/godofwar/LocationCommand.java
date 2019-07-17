package godofwar;

import channel.Channel;
import gui.MainMenu;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.Team;
import util.Schematic;

public class LocationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (label.equalsIgnoreCase("t") || label.equalsIgnoreCase("gow")) {
            Player player = (Player) sender;

            if (player.isOp()) {
                if (arg.length >= 3) {
                    if (arg[0].equalsIgnoreCase("ts") || arg[0].equalsIgnoreCase("tc")) {
                        Channel channel = GodOfWar.getInstance().getChannel(arg[1]);

                        if (channel != null) {
                            Team team = channel.getAccessManager().searchTeam(arg[2]);

                            if (team != null) {
                                if (arg[0].equalsIgnoreCase("ts")) {
                                    channel.getGameManager().getDataContainer().getLocationManager().setTeamSpawn(team, player.getLocation());
                                    return true;
                                } else if (arg[0].equalsIgnoreCase("tc")) {
                                    channel.getGameManager().getDataContainer().getLocationManager().setTeamCore(team, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                                    return true;
                                }
                            } else {
                                showNoTeamExist(player);
                                return false;
                            }
                        } else {
                            showNoChannelExist(player);
                            return false;
                        }
                    }
                } else if (arg.length >= 2) {
                    if (arg[0].equalsIgnoreCase("cl") || arg[0].equalsIgnoreCase("cw")) {
                        Channel channel = GodOfWar.getInstance().getChannel(arg[1]);

                        if (channel != null) {
                            if (arg[0].equalsIgnoreCase("cl")) {
                                channel.getGameManager().getDataContainer().getLocationManager().setChannelLobby(player.getLocation());
                                return true;
                            } else if (arg[0].equalsIgnoreCase("cw")) {
                                channel.getGameManager().getDataContainer().getLocationManager().setChannelWait(player.getLocation());
                                return true;
                            }
                        } else {
                            showNoChannelExist(player);
                            return false;
                        }
                    } else if (arg[0].equalsIgnoreCase("scsave")) {
                        if (GodOfWar.getInstance().getChannel(arg[1]) != null) {
                            GodOfWar.getInstance().getChannel(arg[1]).getGameManager().getDataContainer().getStatusManager().setPasteLOC(GodOfWar.getInstance().getLastCopyLocation());
                            Schematic.save(player, arg[1]);
                            player.sendMessage("saved " + arg[1] + " chnnel's schematic");
                            return true;
                        } else {
                            showNoChannelExist(player);
                            return false;
                        }
                    }
                } else if (arg.length >= 1) {
                    if (arg[0].equalsIgnoreCase("setting")) {
                        player.openInventory(MainMenu.getMainMenu().getInventory());
                        return true;
                    }
                }
            } else {
                showUnknown(player);
                return false;
            }
        }
        return false;
    }

    public void showNoChannelExist(Player player) {
        player.sendMessage("Such a channel doesn't exist!");
    }

    public void showUnknown(Player player) {
        player.sendMessage("Unknown command. Type \"/help\" for help");
    }


    public void showNoTeamExist(Player player) {
        player.sendMessage("Such a team doesn't exist!");
    }
}
