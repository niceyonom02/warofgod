package util;


import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.math.transform.Transform;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.DataException;
import com.sk89q.worldedit.world.World;
import godofwar.GodOfWar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Schematic {
    public static void save(Player player, String schematicName) {
        try {
            File schematic = new File(GodOfWar.getInstance().getDataFolder(), File.separator + "schematics" + File.separator + schematicName + ".schematic");
            File dir = new File(GodOfWar.getInstance().getDataFolder(), File.separator + "schematics" + File.separator);
            if (!dir.exists())
                dir.mkdirs();

            WorldEditPlugin wep = GodOfWar.getInstance().getWorldEdit();
            WorldEdit we = wep.getWorldEdit();

            LocalPlayer localPlayer = wep.wrapPlayer(player);
            LocalSession localSession = we.getSession(localPlayer);
            ClipboardHolder selection = localSession.getClipboard();
            EditSession editSession = localSession.createEditSession(localPlayer);

            Vector min = selection.getClipboard().getMinimumPoint();
            Vector max = selection.getClipboard().getMaximumPoint();

            editSession.enableQueue();
            CuboidClipboard clipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
            clipboard.copy(editSession);
            SchematicFormat.MCEDIT.save(clipboard, schematic);
            editSession.flushQueue();

            player.sendMessage("Saved schematic!");
        } catch (IOException | DataException ex) {
            Bukkit.getLogger().info("Can't save schematic!");
        } catch (EmptyClipboardException ex) {
            Bukkit.getLogger().info("Can't save schematic!");
        }
    }

    public static void paste(String schematicName, Location pasteLoc) {
        try {
            File dir = new File(GodOfWar.getInstance().getDataFolder(), File.separator +"schematics" + File.separator + schematicName + ".schematic");

            EditSession editSession = new EditSession(new BukkitWorld(pasteLoc.getWorld()), Integer.MAX_VALUE);
            editSession.enableQueue();

            SchematicFormat schematic = SchematicFormat.getFormat(dir);
            CuboidClipboard clipboard = schematic.load(dir);

            clipboard.paste(editSession, BukkitUtil.toVector(pasteLoc), true);
            editSession.flushQueue();
        } catch (DataException | IOException ex) {
            Bukkit.getLogger().info("Can't paste schematic!");
        } catch (MaxChangedBlocksException ex) {
            Bukkit.getLogger().info("Can't paste schematic!");
        }
    }
}
