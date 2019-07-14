package channel;

import datacontrol.AccessManager;
import datacontrol.DataContainer;
import godofwar.GameManager;
import listener.InGameEventManager;

public class Channel {
    private String channelName;
    private AccessManager accessManager;
    private GameManager gameManager;
    private InGameEventManager inGameEventManager;

    public Channel(String chName, DataContainer dataContainer) {
        channelName = chName;

        gameManager = new GameManager(channelName, dataContainer);
        accessManager = new AccessManager(dataContainer);
        inGameEventManager = new InGameEventManager(dataContainer, gameManager);
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
