package ability;

import org.bukkit.entity.Player;

public class None implements Ability {
    @Override
    public void registerPassive() {

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

    @Override
    public void onLeftClick(Player player) {

    }

    @Override
    public void onRightClick(Player player) {

    }

    @Override
    public String toString() {
        return "No ability";
    }
}
