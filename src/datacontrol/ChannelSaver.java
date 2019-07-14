package datacontrol;

import channel.Channel;
import godofwar.*;
import team.Team;

import java.util.ArrayList;

public class ChannelSaver {

    public static void save(ArrayList<Channel> channelList) {
        for (String key : GodOfWar.getInstance().getConfig().getKeys(false)) {
            GodOfWar.getInstance().getConfig().set(key, null);
        }

        for (Channel channel : channelList) {
            GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".maxMember", channel.getGameManager().getDataContainer().getMaxMember());
            GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".minMember", channel.getGameManager().getDataContainer().getMinMember());
            GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".lobbyLOC", channel.getGameManager().getDataContainer().getLobbyLOC());
            GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".waitLOC", channel.getGameManager().getDataContainer().getWaitLOC());
            GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".pasteLOC", channel.getGameManager().getDataContainer().getPasteLOC());

            for (Team team : channel.getGameManager().getDataContainer().getTeamList()) {
                GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".team." + team.getTeamName() + ".prefix", team.getPrefix());
                GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".team." + team.getTeamName() + ".coreLOC", team.getTeamCore());
                GodOfWar.getInstance().getConfig().set(channel.getChannelName() + ".team." + team.getTeamName() + ".spawnLOC", team.getTeamSpawn());
            }
        }

        GodOfWar.getInstance().saveConfig();
        GodOfWar.getInstance().reloadConfig();
    }
}
