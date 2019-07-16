package gui;

import channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChannelMenu {
    private Inventory inventory;
    private Channel channel;
    private String title;
    private TeamList teamList;

    public ChannelMenu(Channel channel) {
        this.channel = channel;
        title = channel.getChannelName() + " Channel menu";
        inventory = Bukkit.createInventory(null, 27, title);
        teamList = new TeamList(channel);

        setItem();
    }

    public void setItem() {
        ItemStack item = new ItemStack(Material.SIGN_POST);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("TeamList / Remove team");
        item.setItemMeta(meta);
        inventory.setItem(11, item);

        item = new ItemStack(Material.NETHER_STAR);
        meta = item.getItemMeta();
        meta.setDisplayName("generate new teaml");
        item.setItemMeta(meta);
        inventory.setItem(13, item);

        /**item = new ItemStack(Material.ARROW);
         meta = item.getItemMeta();
         meta.setDisplayName("channel misc");
         item.setItemMeta(meta);
         inventory.setItem(15, item); */
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }

    public TeamList getTeamList() {
        return teamList;
    }
}
