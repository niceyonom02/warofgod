package listener;

import channel.Channel;
import datacontrol.DataContainer;
import godofwar.GodOfWar;
import gui.ChannelMaker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import util.AbilityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ChannelMakerHandler implements Listener {
    int maxMember;

    private ChannelMaker channelMaker;
    int minMember;
    String channelName = null;
    boolean closedByPlayer = true;
    private HashMap<UUID, String> behavior = new HashMap<>();


    public ChannelMakerHandler(ChannelMaker channelMaker) {
        this.channelMaker = channelMaker;

        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }

        if (e.getInventory() == channelMaker.getInventory()) {
            if (!closedByPlayer) {
                closedByPlayer = true;
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }

        if (e.getInventory() == channelMaker.getInventory()) {
            if (closedByPlayer) {
                HandlerList.unregisterAll(this);
                behavior.clear();
            }
        }
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getInventory() == channelMaker.getInventory()) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            int slot = e.getSlot();

            if (slot == 0) {
                closedByPlayer = false;
                player.closeInventory();
                behavior.put(player.getUniqueId(), "name");
            } else if (slot == 1) {
                closedByPlayer = false;
                player.closeInventory();
                behavior.put(player.getUniqueId(), "max");
            } else if (slot == 2) {
                closedByPlayer = false;
                player.closeInventory();
                behavior.put(player.getUniqueId(), "min");
            } else if (slot == 3) {
                if (validate()) {
                    DataContainer dataContainer = new DataContainer(maxMember, minMember, new ArrayList<>(), AbilityManager.getAbilityList(), null, null, null);
                    Channel channel = new Channel(channelName, dataContainer);
                    behavior.clear();
                    GodOfWar.getInstance().registerChannel(channel);
                    player.closeInventory();
                } else {
                    /*통과 X*/
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (behavior.containsKey(player.getUniqueId())) {
            e.setCancelled(true);
            String value = behavior.get(player.getUniqueId());

            switch (value) {
                case "name":
                    channelName = e.getMessage();
                    channelMaker.setItem(channelName, maxMember, minMember);
                    behavior.put(player.getUniqueId(), "wait");
                    player.openInventory(channelMaker.getInventory());
                    break;
                case "max":
                    try {
                        if (Integer.valueOf(e.getMessage()) > 0) {
                            maxMember = Integer.parseInt(e.getMessage());
                            channelMaker.setItem(channelName, maxMember, minMember);
                            behavior.put(player.getUniqueId(), "wait");
                            player.openInventory(channelMaker.getInventory());
                        } else {

                        }
                    } catch (ArithmeticException ex) {
                        //정수만 써주세요
                    }
                    break;
                case "min":
                    try {
                        if (Integer.valueOf(e.getMessage()) > 1) {
                            minMember = Integer.parseInt(e.getMessage());
                            channelMaker.setItem(channelName, maxMember, minMember);
                            behavior.put(player.getUniqueId(), "wait");
                            player.openInventory(channelMaker.getInventory());
                        } else {

                        }
                    } catch (ArithmeticException ex) {
                        //정수만 써주세요
                    }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(behavior.containsKey(e.getPlayer().getUniqueId())){
            behavior.clear();
            HandlerList.unregisterAll(this);
        }
    }

    private boolean validate() {
        return !(channelName == null) && maxMember > minMember && minMember > 1;
    }
}
