package ability;

import channel.Channel;
import godofwar.GodOfWar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FIreResister implements Ability, Listener {
    @Override
    public void onLeftClick(Player player) {

    }

    @Override
    public void onRightClick(Player player) {

    }

    @Override
    public void registerPassive() {
        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
    }

    @EventHandler
    public void passive(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getEntity();

        if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) ||
                e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
            for (Channel channel : GodOfWar.getInstance().getChannelList()) {
                if (channel.getGameManager().getDataContainer().getWrapperManager().isPlayerInGame(player)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @Override
    public boolean validateLeftClick(Player player) {
        return false;
    }

    @Override
    public boolean validateRightClick(Player player) {
        return false;
    }

    @Override
    public int getLeftCooldown() {
        return 0;
    }

    @Override
    public int getRightCooldown() {
        return 0;
    }
}
