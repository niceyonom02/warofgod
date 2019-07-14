package datacontrol;

import org.bukkit.Bukkit;
import util.PlayerWrapper;

import java.util.Collections;

public class AllocateManager {
    private DataContainer dataContainer;

    public AllocateManager(DataContainer dataContainer){
        this.dataContainer = dataContainer;
    }

    public void allocateAbility(){
        Collections.shuffle(dataContainer.getAbilityList());
        Bukkit.getLogger().info(dataContainer.getAbilityList().size() + "");
        Bukkit.getLogger().info(dataContainer.getTeamList().size() + "");


        for(int i = 0; i < dataContainer.getInGamePlayerList().size(); i++){
            dataContainer.getInGamePlayerList().get(i).setAbility(dataContainer.getAbilityList().get(i));
        }
    }

    public void allocateTeam(){
        Collections.shuffle(dataContainer.getTeamList());

        int teamCount = dataContainer.getTeamList().size();
        int memberEachCount = dataContainer.getInGamePlayerList().size() / teamCount;

        int memberPointer = 0;

        for(int i = 0; i < teamCount; i++){
            for(int k = 0; k < memberEachCount; k++){
                if(memberPointer == dataContainer.getInGamePlayerList().size()){
                    break;
                }

                dataContainer.getInGamePlayerList().get(memberPointer).setTeam(dataContainer.getTeamList().get(i));
                memberPointer++;
            }
        }
    }
}
