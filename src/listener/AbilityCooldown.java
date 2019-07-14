package listener;

import datacontrol.CooldownManager;
import godofwar.GodOfWar;
import org.bukkit.Bukkit;

import java.util.UUID;

public class AbilityCooldown {
    private int taskID;
    private int time;
    private String code;
    private UUID uuid;
    private CooldownManager cooldownManager;
    private AbilityCooldown abilityCooldown;

    public AbilityCooldown(UUID uuid, String code, int time, CooldownManager cooldownManager){
        this.uuid = uuid;
        this.code = code;
        this.time = time;
        this.cooldownManager = cooldownManager;
        this.abilityCooldown = this;

        scheduler();
    }

    public void scheduler(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(GodOfWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(time > 1){
                    time--;
                } else if(time == 1){
                    Bukkit.getScheduler().cancelTask(taskID);
                    cooldownManager.remove(abilityCooldown);
                }
            }
        }, 0, 20);
    }

    public int getTime() {
        return time;
    }

    public String getCode() {
        return code;
    }

    public UUID getUuid() {
        return uuid;
    }
}
