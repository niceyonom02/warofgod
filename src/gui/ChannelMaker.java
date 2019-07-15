package gui;

import listener.ChannelMakerEventHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChannelMaker {
    public static String makerName = "ChannelMaker";
    Inventory inventory;
    private ChannelMakerEventHandler evHandler;

    public ChannelMaker() {
        inventory = Bukkit.createInventory(null, InventoryType.HOPPER, makerName);
        setItem();

        evHandler = new ChannelMakerEventHandler(this);
    }

    public static ChannelMaker getMaker() {
        return new ChannelMaker();
    }

    public static String getMakerName() {
        return makerName;
    }

    public void setItem() {
        ItemStack item = new ItemStack(Material.SIGN_POST);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("ChannelName");
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
