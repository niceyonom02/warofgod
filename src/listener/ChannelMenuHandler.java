package listener;

import channel.Channel;
import godofwar.GodOfWar;
import gui.TeamMaker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChannelMenuHandler implements Listener {
    private Channel channel;

    public ChannelMenuHandler(Channel channel) {
        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
        this.channel = channel;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getInventory().getName().equalsIgnoreCase(channel.getChannelMenu().getTitle())) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            int slot = e.getSlot();

            if (slot == 11) {
                player.closeInventory();
                player.openInventory(channel.getChannelMenu().getTeamList().getInventory());
            } else if (slot == 13) {
                TeamMaker teamMaker = TeamMaker.getMaker(channel);
                player.closeInventory();
                player.openInventory(teamMaker.getInventory());
            }
        }
    }
}
