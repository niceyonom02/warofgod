package listener;

import godofwar.GodOfWar;
import gui.TeamMaker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import team.Team;

import java.util.HashMap;
import java.util.UUID;

public class TeamMakerHandler implements Listener {
    int maxMember;
    String prefix = null;
    String teamName = null;
    boolean closedByPlayer = true;
    private TeamMaker teamMaker;
    private HashMap<UUID, String> behavior = new HashMap<>();


    public TeamMakerHandler(TeamMaker teamMaker) {
        this.teamMaker = teamMaker;
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }

        if (e.getInventory() == teamMaker.getInventory()) {
            if (!closedByPlayer) {
                closedByPlayer = true;
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) {
            return;
        }

        if (e.getInventory() == teamMaker.getInventory()) {
            if (closedByPlayer) {
                HandlerList.unregisterAll(this);
                behavior.clear();
            }
        }
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getInventory() == teamMaker.getInventory()) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            int slot = e.getSlot();

            switch (slot) {
                case 0:
                    closedByPlayer = false;
                    player.closeInventory();
                    behavior.put(player.getUniqueId(), "name");
                    break;
                case 1:
                    closedByPlayer = false;
                    player.closeInventory();
                    behavior.put(player.getUniqueId(), "prefix");
                    break;
                case 2:
                    if (validate()) {
                        Team team = new Team(null, null, prefix, teamName);
                        behavior.clear();
                        teamMaker.getChannel().getAccessManager().registerTeam(team);
                        player.closeInventory();
                    } else {
                        /*통과 X*/
                    }
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (behavior.containsKey(player.getUniqueId())) {
            String value = behavior.get(player.getUniqueId());

            switch (value) {
                case "name":
                    teamName = e.getMessage();
                    break;
                case "prefx":
                    prefix = e.getMessage();
            }
            teamMaker.setItem(teamName, prefix);
            behavior.put(player.getUniqueId(), "wait");
            player.openInventory(teamMaker.getInventory());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(behavior.containsKey(e.getPlayer().getUniqueId())){
            behavior.clear();
            HandlerList.unregisterAll(this);
        }
    }

    private boolean validate() {
        return !(teamName == null) && !(prefix == null);
    }
}
