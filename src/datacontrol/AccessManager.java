package datacontrol;

import ability.None;
import channel.Channel;
import org.bukkit.entity.Player;
import team.Team;
import util.PlayerWrapper;

public class AccessManager {
    private DataContainer dataContainer;



    public AccessManager(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public void join(Player player) {
        if (dataContainer.getWrapperManager().isPlayerRan(player)) {
            player.sendMessage("Reconnect to game..");
            reconnect(player);
        } else if (dataContainer.getStatusManager().isGameStarted()) {
            player.sendMessage("Game already started");
        } else if (dataContainer.getStatusManager().isRoomFull()) {
            player.sendMessage("Room is Full!");
        } else {
            joinPlayer(player);
        }
    }

    private void joinPlayer(Player player) {
        if (!dataContainer.getWrapperManager().isPlayerInGame(player)) {
            PlayerWrapper wrapper = new PlayerWrapper(null, player.getUniqueId(), new None());
            dataContainer.getWrapperManager().addWrapperInGame(wrapper);
            dataContainer.getTpManager().tpWait(player);
        } else if (dataContainer.getWrapperManager().isPlayerRan(player)) {
            reconnect(player);
        } else {
            player.sendMessage("You are already in game");
        }
    }

    public void reconnect(Player player) {
        PlayerWrapper playerWrapper = dataContainer.getWrapperManager().getWrapperRanout(player);
        dataContainer.getWrapperManager().removeWrapperRanout(playerWrapper);
        dataContainer.getWrapperManager().addWrapperInGame(playerWrapper);
        dataContainer.getTpManager().tpTeam(player);
    }

    public void leavePlayer(Player player) {
        if (dataContainer.getWrapperManager().isPlayerInGame(player)) {
            PlayerWrapper playerWrapper = dataContainer.getWrapperManager().getWrapperInGame(player);

            dataContainer.getWrapperManager().removeWrapperInGame(playerWrapper);
            if (dataContainer.getStatusManager().isGameStarted()) {
                //if (GodOfWar.getInstance().isAbilityDelete()) {
                PlayerWrapper wrapper = dataContainer.getWrapperManager().getEmptyAbilityWrapper(playerWrapper);
                dataContainer.getWrapperManager().addWrapperRanout(wrapper);
                //}
            }
        }
    }

    public void registerTeam(Channel channel, Team team) {
        channel.getGameManager().getDataContainer().getTeamList().add(team);
    }


}
