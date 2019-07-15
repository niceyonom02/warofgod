package listener;

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

public class ChannelMakerEventHandler implements Listener {
    private ChannelMaker channelMaker;

    public ChannelMakerEventHandler(ChannelMaker channelMaker) {
        this.channelMaker = channelMaker;
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }

        if (e.getInventory() == channelMaker.getInventory()) {
            Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }

        if (e.getInventory() == channelMaker.getInventory()) {
            HandlerList.unregisterAll(this);
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

        }

    }
}
