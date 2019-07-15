package datacontrol;

import channel.Channel;
import team.Team;

import java.util.ArrayList;

public class InformationManager {
    public static ArrayList<String> getChannelLore(Channel channel) {
        ArrayList<String> lore = new ArrayList<>();

        lore.add("maxMember: " + channel.getGameManager().getDataContainer().getMaxMember());
        lore.add("minMember: " + channel.getGameManager().getDataContainer().getMinMember());
        return lore;
    }

    public static ArrayList<String> getTeamLore(Channel channel, Team team) {
        ArrayList<String> lore = new ArrayList<>();

        for (Team t : channel.getGameManager().getDataContainer().getTeamList()) {
            if (t.getTeamName().equalsIgnoreCase(team.getTeamName())) {
                lore.add("Prefix: " + t.getPrefix());
                break;
            }
        }
        return lore;
    }
}
