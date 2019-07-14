package ability;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import util.ItemFinder;

public class IronMaker implements Ability{
    private int leftCooldown = 10;
    private int rightCooldown = 15;

    private int leftStone = 5;
    private int rightStone = 45;

    @Override
    public void onLeftClick(Player player) {
        ItemFinder.takeStone(player, leftStone);

        ItemStack itemStack = new ItemStack(Material.IRON_INGOT, 5);
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void onRightClick(Player player) {
        ItemFinder.takeStone(player, rightStone);

        ItemStack itemStack = new ItemStack(Material.IRON_BLOCK, 2);
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void passive(Player player){

    }

    @Override
    public void registerPassive() {

    }

    @Override
    public void unregisterPassive() {

    }

    @Override
    public boolean validateLeftClick(Player player) {
       return ItemFinder.hasStone(player, leftStone);
    }

    @Override
    public boolean validateRightClick(Player player) {
        return ItemFinder.hasStone(player, rightStone);
    }

    @Override
    public int getLeftCooldown() {
        return leftCooldown;
    }

    @Override
    public int getRightCooldown() {
        return rightCooldown;
    }
}
