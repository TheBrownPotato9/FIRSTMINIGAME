package com.rishi.newminigame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena {

    private int id;
    private ArrayList<UUID> players;
    private Location spawn;
    private GameState state;
    private Countdown countdown;
    private Game game;




    public Arena(int id){
    this.id = id;
    players = new ArrayList<>();
    spawn = Config.getArenaSpawn(id);
    state = GameState.RECRUITING;
    countdown = new Countdown(this);
    game = new Game(this);
    }

    public void start(){
        game.start();
    }

   public void reset(){
        for (UUID uuid : players){
            Bukkit.getPlayer(uuid).teleport(Config.getLobbySpawn());
        }
        state = GameState.RECRUITING;
        players.clear();
        countdown = new Countdown(this);
        game = new Game(this);


    }

    public void sendMessage(String message){
        for (UUID uuid : players){
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
}
   public void addPlayer(Player player){
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if(players.size() >= Config.getRequiredPlayers()){
            countdown.begin();
        }

   }

public void removePlayer(Player player){
players.remove(player.getUniqueId());
player.teleport(Config.getLobbySpawn());

    if (players.size() <= Config.getRequiredPlayers()){
        if (state.equals(GameState.COUNTDOWN)){
            reset();
        }
    }

    if (players.size() == 0 && state.equals(GameState.LIVE)){
        reset();
    }

    }





public int getId() {
    return id;
}
public List<UUID> getPlayers() {
    return players;
}
public GameState getState(){
        return state;
}

public Game getGame(){return game;}
public void setState(GameState state){this.state = state;}























}
