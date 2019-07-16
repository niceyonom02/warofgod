package listener;

import channel.Channel;
import godofwar.GodOfWar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.Team;

public class TeamListHandler implements Listener {
    private Channel channel;

    public TeamListHandler(Channel channel) {
        this.channel = channel;

        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (!(e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))) {
            return;
        }

        if (e.getInventory().getName().equalsIgnoreCase(channel.getChannelMenu().getTeamList().getTitle())) {
            e.setCancelled(true);

            if (e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCurrentItem();

                if (item.hasItemMeta()) {
                    ItemMeta itemMeta = item.getItemMeta();

                    if (itemMeta.getDisplayName() != null) {
                        Team team = channel.getAccessManager().searchTeam(itemMeta.getDisplayName());

                        if (team != null) {
                            channel.getAccessManager().unregisterTeam(team);
                        }
                    }
                }
            }
        }
    }
}
