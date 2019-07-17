package datacontrol;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import team.Team;

public class LocationManager {
    private String channelName;
    private DataContainer dataContainer;

    public LocationManager(DataContainer dataContainer, String channelName) {
        this.dataContainer = dataContainer;
        this.channelName = channelName;
    }

    public void setTeamSpawn(Team team, Location loc) {
        team.setTeamSpawn(loc);
    }

    public void setTeamCore(Team team, Location loc) {
        team.setTeamCore(loc);
    }

    public void setChannelLobby(Location loc) {
        dataContainer.setLobbyLocation(loc);
    }

    public void setChannelWait(Location loc) {
        dataContainer.setWaitLocation(loc);
    }

    public boolean integrityCheck() {
        if (dataContainer.getWaitLOC() == null) {
            Bukkit.getLogger().info("[ " + channelName + " ] : wait Location is not set!");
        }

        if (dataContainer.getLobbyLOC() == null) {
            Bukkit.getLogger().info("[ " + channelName + " ] : lobby Location is not set!");
        }

        for (Team team : dataContainer.getTeamList()) {
            if (team.getTeamCore() == null) {
                Bukkit.getLogger().info("[ " + channelName + " - " + team.getTeamName() + " ] : team core location is not set!");
            }

            if (team.getTeamSpawn() == null) {
                Bukkit.getLogger().info("[ " + channelName + " - " + team.getTeamSpawn() + " ] : team spawn location is not set!");
            }
        }
        return true;
    }
}
