package datacontrol;

import org.bukkit.entity.Player;
import util.PlayerWrapper;

public class TpManager {
    private DataContainer dataContainer;

    public TpManager(DataContainer dataContainer){
        this.dataContainer = dataContainer;
    }

    public void tpLobby(Player player){
        player.teleport(dataContainer.getLobbyLOC());
    }

    public void tpTeam(Player player){
        if(dataContainer.getWrapperManager().isPlayerInGame(player)){
            PlayerWrapper playerWrapper = dataContainer.getWrapperManager().getWrapperInGame(player);

            if(playerWrapper.getTeam() != null){
                player.teleport(playerWrapper.getTeam().getTeamSpawn());
            }
        }
    }

    public void tpWait(Player player){
        player.teleport(dataContainer.getWaitLOC());
    }

    public void tpAllLobby(){
        for(PlayerWrapper playerWrapper : dataContainer.getInGamePlayerList()){
            if(playerWrapper.uuidToPlayer() != null){
                playerWrapper.uuidToPlayer().teleport(dataContainer.getLobbyLOC());
            }
        }
    }

    public void tpAllTeam(){
        for(PlayerWrapper playerWrapper : dataContainer.getInGamePlayerList()){
            if(playerWrapper.uuidToPlayer() != null){
                if(playerWrapper.getTeam() != null){
                    playerWrapper.uuidToPlayer().teleport(playerWrapper.getTeam().getTeamSpawn());
                }
            }
        }
    }
}
