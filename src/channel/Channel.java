package channel;

import datacontrol.AccessManager;
import datacontrol.DataContainer;
import godofwar.GameManager;
import gui.ChannelMenu;
import listener.InGameEventHandler;

public class Channel {
    private String channelName;
    private AccessManager accessManager;
    private GameManager gameManager;
    private InGameEventHandler inGameEventManager;
    private ChannelMenu channelMenu;

    public Channel(String chName, DataContainer dataContainer) {
        channelName = chName;

        gameManager = new GameManager(channelName, dataContainer);
        accessManager = new AccessManager(dataContainer);
        inGameEventManager = new InGameEventHandler(dataContainer, gameManager);
        channelMenu = new ChannelMenu(this);
    }

    public String getChannelName() {
        return channelName;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public AccessManager getAccessManager() {
        return accessManager;
    }

    public ChannelMenu getChannelMenu() {
        return channelMenu;
    }
}
