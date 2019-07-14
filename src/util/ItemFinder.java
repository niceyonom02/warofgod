package util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemFinder {
    public static boolean hasStone(Player player, int stoneCount){
        int count = 0;

        ItemStack[] items = player.getInventory().getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() == Material.COBBLESTONE) {
                count += items[i].getAmount();
            }

            if (count >= stoneCount) {
                return true;
            }
        }
        return false;
    }

    public static void takeStone(Player player, int stoneCount) {
        ItemStack[] items = player.getInventory().getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() == Material.COBBLESTONE) {
                if (items[i].getAmount() > stoneCount) {
                    items[i].setAmount(items[i].getAmount() - stoneCount);
                    break;
                } else if (items[i].getAmount() == stoneCount) {
                    items[i] = null;
                    break;
                } else {
                    stoneCount -= items[i].getAmount();
                    items[i] = null;
                }
            }
        }
        player.getInventory().setContents(items);
    }
}
