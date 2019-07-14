package godofwar;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import util.Schematic;

public class InGameCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("gow") || label.equalsIgnoreCase("t")) {
            if (arg.length > 0) {
                if (player.isOp()) {
                    if (arg[0].equalsIgnoreCase("setting")) {
                        //GodOfWar.getInstance().openManageGui();
                        return true;
                    } else if(arg[0].equalsIgnoreCase("help")){
                        //showHelp(player);
                        return true;
                    } else if(arg[0].equalsIgnoreCase("scsave")){
                        if(arg.length > 1){
                            if(GodOfWar.getInstance().getChannel(arg[1]) != null){
                                GodOfWar.getInstance().getChannel(arg[1]).getGameManager().getDataContainer().getStatusManager().setPasteLOC(GodOfWar.getInstance().getLastCopyLocation());
                                Schematic.save(player, arg[1]);
                                player.sendMessage("saved " + arg[1] + " chnnel's schematic");
                            } else{
                                player.sendMessage("Such a channel doesn't exist!");
                            }
                        }
                    }
                } else {
                    if (arg[0].equalsIgnoreCase("join")) {
                        if (arg.length > 1) {
                            if(GodOfWar.getInstance().getChannel(arg[1]) != null){
                                GodOfWar.getInstance().getChannel(arg[1]).getAccessManager().join(player);
                                return true;
                            } else{
                                player.sendMessage("Such a channel doesn't exist!");
                                return false;
                            }
                        }
                    } else if(arg[0].equalsIgnoreCase("help")){

                    }
                }
            }
        }

        return false;
    }
}
