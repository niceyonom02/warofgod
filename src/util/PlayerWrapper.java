package util;

import ability.Ability;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import team.Team;

import java.util.UUID;

public class PlayerWrapper {
    Team team;
    UUID uuid;
    Ability ability;

    public PlayerWrapper(Team team, UUID uuid, Ability ability){
        this.team = team;
        this.uuid = uuid;
        this.ability = ability;
    }


    public Player uuidToPlayer(){
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid);
        }
        return null;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Team getTeam(){
        return team;
    }

    public Ability getAbility(){
        return ability;
    }

    public UUID getUuid(){
        return uuid;
    }
}
