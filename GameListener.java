package com.rishi.newminigame;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        Player player = e.getPlayer();

        if (Manager.isPlaying(player) && Manager.getArena(player).getState().equals(GameState.LIVE)){
            Manager.getArena(player).getGame().addPoints(player);
        }


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent q){

        Player player = q.getPlayer();
        if(Manager.isPlaying(player)){
            Manager.getArena(player).removePlayer(player);
        }

    }
}
