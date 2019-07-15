package datacontrol;

import org.bukkit.Location;
import team.Team;
import util.PlayerWrapper;

import java.util.ArrayList;

public class TeamManager {
    private DataContainer dataContainer;

    public TeamManager(DataContainer dataContainer){
        this.dataContainer = dataContainer;
    }

    public String searchWhichTeamCore(Location loc){
        for(Team team : dataContainer.getTeamList()){
            if(team.getTeamCore().equals(loc)){
                return team.getTeamName();
            }
        }
        return null;
    }

    public void eliminateTeam(String teamName){
        for(PlayerWrapper playerWrapper : dataContainer.getInGamePlayerList()){
            if(playerWrapper.uuidToPlayer() != null){
                if(playerWrapper.getTeam() != null){
                    if(playerWrapper.getTeam().getTeamName().equalsIgnoreCase(teamName)){
                        dataContainer.getWrapperManager().removeWrapperInGame(playerWrapper);
                        dataContainer.getTpManager().tpLobby(playerWrapper.uuidToPlayer());
                    }
                }
            }
        }

        for(Team team : dataContainer.getTeamList()){
            if(team.getTeamName().equalsIgnoreCase(teamName)){
                dataContainer.getTeamList().remove(team);
            }
        }
    }

    public void restoreTeamList(String key){
        dataContainer.getTeamList().clear();
        dataContainer.setTeamList(ChannelLoader.loadTeams(key));
    }

    public ArrayList<Team> getTeamList() {
        return dataContainer.getTeamList();
    }
}
