package datacontrol;

import ability.Ability;
import godofwar.Status;
import org.bukkit.Location;
import team.Team;
import util.PlayerWrapper;

import java.util.ArrayList;

import static godofwar.Status.*;

public class DataContainer {
    private ArrayList<PlayerWrapper> inGamePlayerList = new ArrayList<>();
    private ArrayList<PlayerWrapper> ranoutList = new ArrayList<>(); //탈주할 시 능력 없애기
    private ArrayList<Ability> abilityList;
    private ArrayList<Team> teamList;
    private Status status = WAIT;
    private int maxMember;
    private int minMember;

    private TpManager tpManager;
    private AccessManager accessManager;
    private WrapperManager wrapperManager;
    private StatusManager statusManager;
    private TeamManager teamManager;
    private AllocateManager allocateManager;

    private Location lobbyLOC;
    private Location waitLOC;
    private Location pasteLOC;

    public DataContainer(int maxMember, int minMember, ArrayList<Team> teamList, ArrayList<Ability> ablityList, Location lobbyLOC, Location waitLOC, Location pastLOC) {
        this.maxMember = maxMember;
        this.minMember = minMember;
        this.abilityList = ablityList;
        this.teamList = teamList;
        this.lobbyLOC = lobbyLOC;
        this.waitLOC = waitLOC;
        this.pasteLOC = pastLOC;

        tpManager = new TpManager(this);
        accessManager = new AccessManager(this);
        wrapperManager = new WrapperManager(this);
        statusManager = new StatusManager(this);
        teamManager = new TeamManager(this);
        allocateManager = new AllocateManager(this);
    }

    protected Location getLobbyLOC() {
        return lobbyLOC;
    }

    protected Location getWaitLOC() {
        return waitLOC;
    }

    protected ArrayList<PlayerWrapper> getInGamePlayerList() {
        return inGamePlayerList;
    }

    protected ArrayList<PlayerWrapper> getRanoutList() {
        return ranoutList;
    }

    protected ArrayList<Ability> getAbilityList() {
        return abilityList;
    }

    protected ArrayList<Team> getTeamList() {
        return teamList;
    }

    protected void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    protected Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    public void setPasteLOC(Location pasteLOC) {
        this.pasteLOC = pasteLOC;
    }

    protected int getMaxMember() {
        return maxMember;
    }

    protected int getMinMember() {
        return minMember;
    }

    protected Location getPasteLOC() {
        return pasteLOC;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public TpManager getTpManager() {
        return tpManager;
    }

    public AccessManager getAccessManager() {
        return accessManager;
    }

    public WrapperManager getWrapperManager() {
        return wrapperManager;
    }

    public StatusManager getStatusManager() {
        return statusManager;
    }

    public AllocateManager getAllocateManager() {
        return allocateManager;
    }

    /**
     * misc
     */
    public void sendAllMessage(String msg) {
        for (PlayerWrapper playerWrapper : inGamePlayerList) {
            if (playerWrapper.uuidToPlayer() != null) {
                playerWrapper.uuidToPlayer().sendMessage(msg);
            }
        }
    }
}
