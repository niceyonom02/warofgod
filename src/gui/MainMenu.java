package gui;

import listener.GeneralGUIHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainMenu {
    public static String title = "MainMenu";
    private static MainMenu mainMenu;
    Inventory inventory;

    public MainMenu() {
        mainMenu = this;
        inventory = Bukkit.createInventory(null, 27, title);
        new GeneralGUIHandler();

        setItem();
    }

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setItem() {
        ItemStack item = new ItemStack(Material.SIGN);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("ChannelList / Remove channel");
        item.setItemMeta(meta);
        inventory.setItem(11, item);

        item = new ItemStack(Material.NETHER_STAR);
        meta = item.getItemMeta();
        meta.setDisplayName("Generate new channel");
        item.setItemMeta(meta);
        inventory.setItem(13, item);

        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName("Current channel status");
        item.setItemMeta(meta);
        inventory.setItem(15, item);
    }

    public Inventory getInventory() {
        return inventory;
    }


}
