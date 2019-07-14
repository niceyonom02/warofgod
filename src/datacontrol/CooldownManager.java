package datacontrol;

import listener.AbilityCooldown;
import util.PlayerWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {
    private ArrayList<AbilityCooldown> inCooldown = new ArrayList<>();

    public void put(UUID uuid, String code, int time){
        AbilityCooldown abilityCooldown = new AbilityCooldown(uuid, code, time, this);
        inCooldown.add(abilityCooldown);
    }

    public synchronized void remove(AbilityCooldown abilityCooldown){
        inCooldown.remove(abilityCooldown);
    }

    public boolean isInCooldown(UUID uuid, String code){
        return getAbilityCooldown(uuid, code) != null;
    }

    private AbilityCooldown getAbilityCooldown(UUID uuid, String code){
        for(AbilityCooldown abilityCooldown : inCooldown){
            if(abilityCooldown.getUuid().equals(uuid) && abilityCooldown.getCode().equalsIgnoreCase(code)){
                return abilityCooldown;
            }
        }
        return null;
    }

    public int getLeftTime(UUID uuid, String code){
        AbilityCooldown abilityCooldown = getAbilityCooldown(uuid, code);

        return abilityCooldown.getTime();
    }
}
