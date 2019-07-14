package godofwar;

import datacontrol.DataContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import util.PlayerWrapper;
import util.Schematic;

import static godofwar.Status.*;

public class GameManager {
    int waitNO;
    int readyNO;
    int overwatchNO;
    private String chanName;
    private DataContainer dataContainer;

    public GameManager(String chName, DataContainer dataContainer) {
        chanName = chName;
        this.dataContainer = dataContainer;

        initialize();
    }

    public void initialize() {
        //dataContainer.getWrapperManager().unregisterAbility();
        dataContainer.getTpManager().tpAllLobby();
        dataContainer.getStatusManager().clear();
        dataContainer.getStatusManager().setStatus(WAIT);
        dataContainer.getTeamManager().restoreTeamList(chanName);

        waitScheduler();
    }

    public void waitScheduler() {
        waitNO = Bukkit.getScheduler().scheduleSyncRepeatingTask(GodOfWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (dataContainer.getStatusManager().isRoomFull()) {
                    dataContainer.getStatusManager().setStatus(READY);
                    Bukkit.getScheduler().cancelTask(waitNO);
                    readyScheduler();
                }
            }
        }, 0, 1);
    }

    public void readyScheduler() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(chanName + "Channel - Game will start!!");
            p.sendMessage("Restoring map!");
        }
        Schematic.paste(chanName, dataContainer.getStatusManager().getPasteLOC());

        readyNO = Bukkit.getScheduler().scheduleSyncRepeatingTask(GodOfWar.getInstance(), new Runnable() {
            int i = 400;

            @Override
            public void run() {
                i--;
                if (dataContainer.getStatusManager().isRoomLess()) {
                    Bukkit.getScheduler().cancelTask(readyNO);
                    dataContainer.sendAllMessage("Turn to waiting mode due to lack of participants..");
                    waitScheduler();
                } else if(i == 200){
                    dataContainer.sendAllMessage("Game start 10 sec left!");
                } else if(i == 0){
                    dataContainer.sendAllMessage("Game start!");
                    Bukkit.getScheduler().cancelTask(readyNO);
                    startGame();
                }

            }
        }, 0, 1);
    }

    public void gameSet(String teamName){
        dataContainer.getStatusManager().setStatus(END);
        initialize();

        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("[ " + chanName + " ] " + teamName + "팀의 승리입니다!");
        }
    }

    public void startGame(){
        dataContainer.getStatusManager().setStatus(PROGRESS);
        dataContainer.getAllocateManager().allocateAbility();
        //dataContainer.getWrapperManager().registerAbility();
        dataContainer.getAllocateManager().allocateTeam();
        dataContainer.getTpManager().tpAllTeam();

        overwatch();
    }

    public void overwatch(){
        overwatchNO = Bukkit.getScheduler().scheduleSyncRepeatingTask(GodOfWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(dataContainer.getStatusManager().isRoomLess()){
                    Bukkit.getScheduler().cancelTask(overwatchNO);
                    forceStop();
                }
            }
        }, 1200, 1200);
    }

    public void forceStop(){
        initialize();
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(chanName + "채널 강제종료");
        }
    }

    public DataContainer getDataContainer() {
        return dataContainer;
    }
}
