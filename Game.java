package com.rishi.newminigame;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena){
        this.arena = arena;
        this.points = new HashMap<>();
    }


    public void start(){

        arena.setState(GameState.LIVE);

        arena.sendMessage("Game has started! Break 20 Block to Win!");

        for (UUID uuid : arena.getPlayers()){
            points.put(uuid, 0);
        }

    }

public void addPoints(Player player){
    int p = points.get(player.getUniqueId()) + 1;

    if (p == 20){
       arena.sendMessage(player.getName() + "You WON!");

       arena.reset();
               return;

    }
        points.replace(player.getUniqueId(), p);
    }





}
