package gui;

import channel.Channel;
import datacontrol.InformationManager;
import godofwar.GodOfWar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ChannelList {
    public static String title = "ChannelList";
    private static ChannelList channelList;
    private Inventory inventory;

    public ChannelList() {
        channelList = this;
        inventory = Bukkit.createInventory(null, 9, title);

        synchronize();
    }

    public void synchronize() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(GodOfWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                updateItem();
            }
        }, 0, 1);
    }

    public static ChannelList getChannelList() {
        return channelList;
    }

    private void updateItem() {
        inventory.clear();

        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta;
        ArrayList<String> lore;

        for (int i = 0; i < GodOfWar.getInstance().getChannelList().size(); i++) {
            Channel channel = GodOfWar.getInstance().getChannelList().get(i);
            lore = InformationManager.getChannelLore(channel);

            meta = item.getItemMeta();
            meta.setDisplayName(channel.getChannelName());
            meta.setLore(lore);

            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}