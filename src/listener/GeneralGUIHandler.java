package listener;

import channel.Channel;
import godofwar.GodOfWar;
import gui.ChannelList;
import gui.ChannelMaker;
import gui.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GeneralGUIHandler implements Listener {
    public GeneralGUIHandler() {
        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)) {
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
                ChannelMaker channelMaker = ChannelMaker.getMaker();
                player.openInventory(channelMaker.getInventory());
            } else if (slot == 15) {
                //misc
            }
        }
        if (e.getInventory().getName().equalsIgnoreCase(ChannelList.title)) {
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();

            if (item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();

                if (itemMeta.getDisplayName() != null) {
                    Channel channel = GodOfWar.getInstance().getChannel(itemMeta.getDisplayName());

                    if (channel != null) {
                        if (e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                            GodOfWar.getInstance().unregisterChannel(channel);
                        } else {
                            player.closeInventory();
                            player.openInventory(channel.getChannelMenu().getInventory());
                        }
                    }
                }
            }
        }
    }
}
