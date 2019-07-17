package team;

import org.bukkit.Location;

public class Team {
    Location teamSpawn;
    Location teamCore;
    String prefix;
    String teamName;

    public Team(Location ts, Location tc, String prefix, String name){
        teamSpawn = ts;
        teamCore = tc;
        this.prefix = prefix;
        teamName = name;
    }

    public Location getTeamSpawn() {
        return teamSpawn;
    }

    public Location getTeamCore() {
        return teamCore;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamSpawn(Location loc) {
        teamSpawn = loc;
    }

    public void setTeamCore(Location loc) {
        teamCore = loc;
    }
}
