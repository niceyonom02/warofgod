package datacontrol;

import godofwar.Status;
import org.bukkit.Location;

import static godofwar.Status.PROGRESS;

public class StatusManager {
    private DataContainer dataContainer;

    public StatusManager(DataContainer dataContainer){
        this.dataContainer = dataContainer;
    }

    public boolean isGameStarted(){
        return dataContainer.getStatus() == PROGRESS;
    }

    public boolean isRoomFull(){
        return dataContainer.getInGamePlayerList().size() >= dataContainer.getMaxMember();
    }

    public boolean isRoomLess(){
        return dataContainer.getInGamePlayerList().size() < dataContainer.getMinMember();
    }

    public boolean isLastStanding(){
        return dataContainer.getTeamList().size() == 1;
    }


    public void clear(){
        dataContainer.getInGamePlayerList().clear();
        dataContainer.getRanoutList().clear();
    }

    public void setStatus(Status status){
        dataContainer.setStatus(status);
    }

    public Location getPasteLOC(){
        return dataContainer.getPasteLOC();
    }

    public void setPasteLOC(Location loc){
        dataContainer.setPasteLOC(loc);
    }
}
