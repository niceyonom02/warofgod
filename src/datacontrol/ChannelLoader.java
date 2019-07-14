package datacontrol;

import ability.Ability;
import channel.Channel;
import godofwar.GodOfWar;
import org.bukkit.Location;
import team.Team;
import util.AbilityManager;

import java.util.ArrayList;

public class ChannelLoader {
    public static ArrayList<Channel> load(){
        ArrayList<Channel> list = new ArrayList<>();

        for(String key : GodOfWar.getInstance().getConfig().getKeys(false)){
            ArrayList<Team> teamList = loadTeams(key);
            ArrayList<Ability> ablist = AbilityManager.getAbilityList();

            int maxMember = GodOfWar.getInstance().getConfig().getInt(key + ".maxMember");
            int minMember = GodOfWar.getInstance().getConfig().getInt(key + ".minMember");
            Location lobbyLOC = (Location) GodOfWar.getInstance().getConfig().get(key + ".lobbyLOC");
            Location waitLOC = (Location) GodOfWar.getInstance().getConfig().get(key + ".waitLOC");
            Location pasteLOC = (Location) GodOfWar.getInstance().getConfig().get(key + ".pasteLOC");

            DataContainer dataContainer = new DataContainer(maxMember, minMember, teamList, ablist, lobbyLOC, waitLOC, pasteLOC);

            Channel channel = new Channel(key, dataContainer);
            list.add(channel);
        }
        return list;
    }

    public static ArrayList<Team> loadTeams(String key){
        ArrayList<Team> teamList = new ArrayList<>();

        if(GodOfWar.getInstance().getConfig().getConfigurationSection(key + ".team") != null){
            for(String teamName : GodOfWar.getInstance().getConfig().getConfigurationSection(key + ".team").getKeys(false)){
                String prefix = GodOfWar.getInstance().getConfig().getString(key + ".team." + teamName + ".prefix");
                Location coreLOC = (Location) GodOfWar.getInstance().getConfig().get(key + ".team." + teamName + ".coreLOC");
                Location spawnLOC = (Location) GodOfWar.getInstance().getConfig().get(key + ".team." + teamName + ".spawnLOC");

                Team team = new Team(spawnLOC, coreLOC, prefix, teamName);
                teamList.add(team);
            }
        }
        return teamList;
    }
}
