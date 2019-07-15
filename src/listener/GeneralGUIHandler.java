package listener;

import godofwar.GodOfWar;
import gui.ChannelList;
import gui.ChannelMaker;
import gui.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GeneralGUIHandler implements Listener {
    public GeneralGUIHandler() {
        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equalsIgnoreCase(MainMenu.title)) {
            e.setCancelled(true);

            int slot = e.getSlot();

            if (slot == 11) {
                player.closeInventory();
                player.openInventory(ChannelList.getChannelList().getInventory());
            } else if (slot == 13) {
                player.closeInventory();
                ChannelMaker channelMaker = new ChannelMaker();
                player.openInventory(channelMaker.getInventory());
            } else if (slot == 15) {
                //misc
            }

        }
    }
}
