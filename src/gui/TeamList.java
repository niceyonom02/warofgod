package gui;

import channel.Channel;
import datacontrol.InformationManager;
import godofwar.GodOfWar;
import listener.TeamListHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeamList {
    private Inventory inventory;
    private Channel channel;
    private String title;

    public TeamList(Channel channel) {
        this.channel = channel;
        title = channel.getChannelName() + " Team list";
        inventory = Bukkit.createInventory(null, 9, title);

        new TeamListHandler(channel);

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

    public void updateItem() {
        inventory.clear();

        ItemStack item;
        ItemMeta meta;
        ArrayList<String> lore;

        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName("back to channel menu");
        item.setItemMeta(meta);
        inventory.setItem(8, item);

        for (int i = 0; i < channel.getGameManager().getDataContainer().getTeamManager().getTeamList().size(); i++) {
            lore = InformationManager.getTeamLore(channel, channel.getGameManager().getDataContainer().getTeamManager().getTeamList().get(i));

            item = new ItemStack(Material.EMERALD);
            meta = item.getItemMeta();
            meta.setDisplayName(channel.getGameManager().getDataContainer().getTeamManager().getTeamList().get(i).getTeamName());
            meta.setLore(lore);

            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }
}
