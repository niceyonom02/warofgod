package gui;

import listener.ChannelMakerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChannelMaker {
    private String makerName = "ChannelMaker";
    Inventory inventory;
    private ChannelMakerHandler evHandler;

    public ChannelMaker() {
        inventory = Bukkit.createInventory(null, InventoryType.HOPPER, makerName);
        setItem(null, 0, 0);

        evHandler = new ChannelMakerHandler(this);
    }

    public static ChannelMaker getMaker() {
        return new ChannelMaker();
    }

    public void setItem(String chName, int max, int min) {
        ItemStack item = new ItemStack(Material.SIGN_POST);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("ChannelName");

        if (!(chName == null)) {

        }
        item.setItemMeta(meta);
        inventory.setItem(0, item);

        item = new ItemStack(Material.STAINED_GLASS);
        item.setDurability((short) 5);
        meta = item.getItemMeta();

        meta.setDisplayName("Max-Member");
        item.setItemMeta(meta);
        inventory.setItem(1, item);

        item = new ItemStack(Material.STAINED_GLASS);
        item.setDurability((short) 6);
        meta = item.getItemMeta();

        meta.setDisplayName("Min-Member");
        item.setItemMeta(meta);
        inventory.setItem(2, item);

        item = new ItemStack(Material.ANVIL);
        meta = item.getItemMeta();

        meta.setDisplayName("Confirm");
        item.setItemMeta(meta);
        inventory.setItem(3, item);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
