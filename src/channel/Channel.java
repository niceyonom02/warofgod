package channel;

import datacontrol.AccessManager;
import datacontrol.DataContainer;
import godofwar.GameManager;
import listener.InGameEventHandler;

public class Channel {
    private String channelName;
    private AccessManager accessManager;
    private GameManager gameManager;
    private InGameEventHandler inGameEventManager;

    public Channel(String chName, DataContainer dataContainer) {
        channelName = chName;

        gameManager = new GameManager(channelName, dataContainer);
        accessManager = new AccessManager(dataContainer);
        inGameEventManager = new InGameEventHandler(dataContainer, gameManager);
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


}
