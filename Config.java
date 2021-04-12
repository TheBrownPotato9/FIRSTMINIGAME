package com.rishi.newminigame;

import org.bukkit.Bukkit;
import org.bukkit.Location;



public class Config {

    private static Main main;
    public Config(Main main){
        Config.main = main;

        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }

    public static int getRequiredPlayers(){
        return main.getConfig().getInt("required-players");
    }

    public static int getCountdownSeconds() {
        return main.getConfig().getInt("countdown-seconds");
    }
    public static Location getLobbySpawn(){
        return new Location(
                Bukkit.getWorld(main.getConfig().getString("lobby-spawn.world")),
                main.getConfig().getDouble("lobby-spawn.x"),
                main.getConfig().getDouble("lobby-spawn.y"),
                main.getConfig().getDouble("lobby-spawn.z"),
                main.getConfig().getInt("yaw"),
                main.getConfig().getInt("pitch"));

    }
    public static Location getArenaSpawn(int id){
        return new Location(
                Bukkit.getWorld(main.getConfig().getString("arenas."+ id + ".world")),
                main.getConfig().getDouble("arenas."+ id + ".x"),
                main.getConfig().getDouble("arenas."+ id + ".y"),
                main.getConfig().getDouble("arenas."+ id + ".z"),
                main.getConfig().getInt("arenas."+ id + ".yaw"),
                main.getConfig().getInt("arenas."+ id + ".pitch"));

    }

    public static int getArenaAmount(){
        return main.getConfig().getConfigurationSection("arenas.").getKeys(false).size();

    }





}
