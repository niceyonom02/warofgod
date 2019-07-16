package gui;

import channel.Channel;
import listener.TeamMakerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TeamMaker {
    public static String makerName = "TeamMaker";
    Inventory inventory;
    private Channel channel;
    private TeamMakerHandler evHandler;

    private TeamMaker(Channel channel) {
        inventory = Bukkit.createInventory(null, InventoryType.HOPPER, makerName);
        this.channel = channel;

        setItem(null, null);
        evHandler = new TeamMakerHandler(this);
    }

    public static TeamMaker getMaker(Channel channel) {
        return new TeamMaker(channel);
    }

    public void setItem(String teamName, String prefix) {
        ArrayList<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.SIGN_POST);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("TeamName");
        if (!(teamName == null)) {
            lore.add(teamName);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        inventory.setItem(0, item);

        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName("Prefix");
        if (!(prefix == null)) {
            lore.clear();
            lore.add(prefix);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        inventory.setItem(1, item);

        item = new ItemStack(Material.ANVIL);
        meta = item.getItemMeta();

        meta.setDisplayName("Confirm");
        item.setItemMeta(meta);
        inventory.setItem(2, item);
    }

    public Channel getChannel() {
        return channel;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
