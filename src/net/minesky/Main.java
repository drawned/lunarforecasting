package net.minesky;

import net.minesky.celestials.Moon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static Logger l;

    @Override
    public void onDisable() {
        for(Player b : Bukkit.getOnlinePlayers()) {

            if(Moon.playerMoon.containsKey(b)) {

                Moon.playerMoon.get(b).getCelestialDisplay().getTextDisplay().remove();

            }

        }
    }

    @Override
    public void onEnable() {

        l = this.getLogger();

        long penis = (long) ((long)Bukkit.getOnlinePlayers().size() * 0.08);


        new BukkitRunnable() {
            @Override
            public void run() {

                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(Moon.playerMoon.containsKey(p)) {
                        Moon.playerMoon.get(p).updateMoon();
                        continue;
                    }

                    Moon m = new Moon(p);

                    Moon.playerMoon.put(p, m);

                    m.updateMoon();
                    Bukkit.broadcastMessage("ATUALIZANDO LUA PRIMERIA VEZ : )");
                }
            }
        }.runTaskTimer(this, 0, 0);

    }

}