package com.rishi.newminigame;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (args.length == 1 && args[0].equalsIgnoreCase("list")){
                player.sendMessage(ChatColor.BLACK + "These are the arenas");
                for(Arena arena : Manager.getArenas() ){
                    player.sendMessage(ChatColor.BLACK + "-" + arena.getId());
                }
            }else if (args.length == 1 && args[0].equalsIgnoreCase("leave")){
                if (Manager.isPlaying(player)){
                    Manager.getArena((player)).removePlayer(player);
                    player.sendMessage(ChatColor.GREEN + "Arena Left Successfully");
                }else{
                    player.sendMessage(ChatColor.RED + "You are not currently in an arena");

                }
            } else if(args.length == 2 && args[0].equalsIgnoreCase("join")){
                try{
                    int id = Integer.parseInt(args[1]);
                    if (id >= 0 && id <= (Config.getArenaAmount() - 1)){
                        if (Manager.isRecruiting(id)){
                            Manager.getArena(id).addPlayer(player);

                            player.sendMessage(ChatColor.GREEN + "You are now playing in arena" + id + "!");
                        }
                    }else{
                        player.sendMessage(ChatColor.RED + "You cannot join this game right now");

                    }
                } catch(NumberFormatException x) {
                    player.sendMessage(ChatColor.RED + "Invalid Arena, see /arena list for available arenas" );
                }
            } else{
                player.sendMessage(ChatColor.RED + "Invalid Usage - these are the options");
                player.sendMessage(ChatColor.GREEN + "/arena list");
                player.sendMessage(ChatColor.GREEN + "/arena leave");
                player.sendMessage(ChatColor.GREEN + "/arena join [id]");
            }
        }else{
            System.out.println("You cannot use this from the console");
        }


        return false;
    }

}
