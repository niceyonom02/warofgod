package godofwar;

import channel.Channel;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import datacontrol.ChannelLoader;
import datacontrol.ChannelSaver;
import gui.ChannelList;
import gui.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class GodOfWar extends JavaPlugin {
    private static GodOfWar instance;
    private ArrayList<Channel> channelList;
    private WorldEditPlugin worldEdit;
    private Location copyLOC;
    private InGameCommand inGameCommand = new InGameCommand();
    private LocationCommand locationCommand = new LocationCommand();

    @Override
    public void onEnable(){
        instance = this;
        setupWorldEdit();

        if(!instance.getDataFolder().exists()){
            instance.getDataFolder().mkdir();
            instance.saveResource("config.yml", false);
        }

        if(worldEdit != null){
            getCommand("gow").setExecutor(inGameCommand);
            getCommand("t").setExecutor(inGameCommand);
            getCommand("gow").setExecutor(locationCommand);
            getCommand("t").setExecutor(locationCommand);


            channelList = ChannelLoader.load();
        }

        new MainMenu();
        new ChannelList();
    }

    private void setupWorldEdit() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldEdit");

        if (plugin == null || !(plugin instanceof WorldEditPlugin)) {
            Bukkit.getLogger().severe("WorldEdit 6 is not found - Plugin won't be started");
            return;
        }

        worldEdit = (WorldEditPlugin) plugin;
    }

    @Override
    public void onDisable(){
        ChannelSaver.save(channelList);
    }

    public static GodOfWar getInstance(){
        return instance;
    }

    public Channel getChannel(String name){
        for(Channel ch : channelList){
            if(ch.getChannelName().equalsIgnoreCase(name)){
                return ch;
            }
        }
        return null;
    }

    public void unregisterChannel(Channel channel) {
        channelList.remove(channel);
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    public Location getLastCopyLocation(){
        return copyLOC;
    }

    public void setLastCopyLocation(Location loc){
        this.copyLOC = loc;
    }

    public WorldEditPlugin getWorldEdit(){
        return worldEdit;
    }

    public void registerChannel(Channel channel) {
        GodOfWar.getInstance().getChannelList().add(channel);
    }
}
