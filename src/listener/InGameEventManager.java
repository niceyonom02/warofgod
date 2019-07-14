package listener;

import ability.Ability;
import ability.None;
import datacontrol.CooldownManager;
import datacontrol.DataContainer;
import godofwar.GameManager;
import godofwar.GodOfWar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import util.PlayerWrapper;

import java.util.ArrayList;
import java.util.UUID;

public class InGameEventManager implements Listener {
    private DataContainer dataContainer;
    private GameManager gameManager;
    private CooldownManager cooldownManager;

    public InGameEventManager(DataContainer dataContainer, GameManager gameManager){
        this.dataContainer = dataContainer;
        this.gameManager = gameManager;
        cooldownManager = new CooldownManager();

        Bukkit.getPluginManager().registerEvents(this, GodOfWar.getInstance());
    }

    @EventHandler
    public void preProcess(PlayerCommandPreprocessEvent e){
        if(e.getMessage().equalsIgnoreCase("//copy")){
            GodOfWar.getInstance().setLastCopyLocation(e.getPlayer().getLocation());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        dataContainer.getAccessManager().leavePlayer(player);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity();

        if(dataContainer.getWrapperManager().isPlayerInGame(player)){
            if(dataContainer.getStatusManager().isGameStarted()){
                dataContainer.getTpManager().tpTeam(player);
            } else{
                dataContainer.getTpManager().tpWait(player);
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player player = e.getPlayer();

        if(dataContainer.getWrapperManager().isPlayerInGame(player)){
            PlayerWrapper wrapper = dataContainer.getWrapperManager().getWrapperInGame(player);
            Ability ability = wrapper.getAbility();

            if(ability == null || ability instanceof None){
                return;
            }

            if(player.getInventory().getItemInMainHand() != null){
                if(player.getInventory().getItemInMainHand().isSimilar(new ItemStack(Material.BLAZE_ROD))){
                    if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                        if(!cooldownManager.isInCooldown(wrapper.getUuid(), "left")){
                            if(ability.validateLeftClick(wrapper.uuidToPlayer())){
                                ability.onLeftClick(wrapper.uuidToPlayer());
                                cooldownManager.put(wrapper.getUuid(), "left", ability.getLeftCooldown());
                            } else{
                                wrapper.uuidToPlayer().sendMessage("need more stone!");
                            }
                        } else{
                            player.sendMessage(cooldownManager.getLeftTime(wrapper.getUuid(), "left") + "sec left to use ability again");
                        }
                    } else if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                        if(!cooldownManager.isInCooldown(wrapper.getUuid(), "right")){
                            if(ability.validateRightClick(wrapper.uuidToPlayer())){
                                ability.onRightClick(wrapper.uuidToPlayer());
                                cooldownManager.put(wrapper.getUuid(), "right", ability.getRightCooldown());
                            } else{
                                wrapper.uuidToPlayer().sendMessage("need more stone!");
                            }
                        } else{
                            player.sendMessage(cooldownManager.getLeftTime(wrapper.getUuid(), "right") + "sec left to use ability again");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if(dataContainer.getWrapperManager().isPlayerInGame(player)){
            PlayerWrapper playerWrapper = dataContainer.getWrapperManager().getWrapperInGame(player);

            if(playerWrapper.getTeam() != null){
                if(block.getLocation().equals(playerWrapper.getTeam().getTeamCore())){
                    e.setCancelled(true);
                    player.sendMessage("Your team's core");
                } else{
                    String teamName = dataContainer.getTeamManager().searchWhichTeamCore(block.getLocation());

                    if(teamName != null){
                        dataContainer.getTeamManager().eliminateTeam(teamName);
                        if(dataContainer.getStatusManager().isLastStanding()){
                            gameManager.gameSet(playerWrapper.getTeam().getTeamName());
                        }
                    }
                }
            }
        }
    }


}
