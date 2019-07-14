package datacontrol;

import ability.None;
import org.bukkit.entity.Player;
import util.PlayerWrapper;

public class WrapperManager {
    private DataContainer dataContainer;

    public WrapperManager(DataContainer dataContainer){
        this.dataContainer = dataContainer;
    }

    public boolean isPlayerInGame(Player player){
        return getWrapperInGame(player) != null;
    }

    public boolean isPlayerRan(Player player){
        return getWrapperRanout(player) != null;
    }

    /**getWrapper*/
    public PlayerWrapper getWrapperRanout(Player player){
        for(PlayerWrapper playerWrapper : dataContainer.getRanoutList()){
            if(playerWrapper.getUuid().equals(player.getUniqueId())){
                return playerWrapper;
            }
        }
        return null;
    }

    public PlayerWrapper getWrapperInGame(Player player){
        for(PlayerWrapper playerWrapper : dataContainer.getInGamePlayerList()){
            if(playerWrapper.getUuid().equals(player.getUniqueId())){
                return playerWrapper;
            }
        }
        return null;
    }

    /**add / remove wrapper*/
    public void addWrapperInGame(PlayerWrapper wrapper){
        dataContainer.getInGamePlayerList().add(wrapper);
    }

    public void removeWrapperInGame(PlayerWrapper wrapper){
        dataContainer.getInGamePlayerList().remove(wrapper);
    }

    public void addWrapperRanout(PlayerWrapper wrapper){
        dataContainer.getRanoutList().add(wrapper);
    }

    public void removeWrapperRanout(PlayerWrapper wrapper){
        dataContainer.getRanoutList().remove(wrapper);
    }

    /**get empty ability wrapper*/
    public PlayerWrapper getEmptyAbilityWrapper(PlayerWrapper wrapper){
        PlayerWrapper newWrapper = new PlayerWrapper(wrapper.getTeam(), wrapper.getUuid(), new None());
        return newWrapper;
    }
}
