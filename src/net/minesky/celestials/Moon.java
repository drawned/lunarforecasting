package net.minesky.celestials;

import net.minesky.Main;
import net.minesky.global.GlobalCelestial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Vector;

import java.awt.print.Paper;
import java.util.HashMap;

public class Moon {

    public static HashMap<Player, Moon> playerMoon = new HashMap<>();
    public static HashMap<Integer, GlobalCelestial.MoonPhases> cachedMoonPhase = new HashMap<>();

    private final CelestialDisplay celestialDisplay;
    private final Player player;

    public Moon(Player player) {

        this.player = player;
        this.celestialDisplay = new CelestialDisplay(player.getLocation()
                .add(0, 100, 0));

        playerMoon.put(player, this);
    }

    public Player getPlayer() {
        return player;
    }

    public CelestialDisplay getCelestialDisplay() {
        return celestialDisplay;
    }

    public void updateMoon() {

        World w = getPlayer().getWorld();

        long t = w.getTime();

        int base = 80;

        double piC = Math.PI*((double) t /12000)+(Math.PI/2);
        double x = base* -Math.sin(piC);
        double y = base* Math.cos(piC);

        // em breve :::: )))))  =3  =3  =3  =3 :3 :3 :3 :3
        double z = 0;

        Location l = player.getLocation().clone();

        TextDisplay c = getCelestialDisplay().getTextDisplay();

        getCelestialDisplay().updateMoonPhase(w);

        /*int opacity = CelestialDisplay.getOpacity((int)l.getWorld().getTime());
        c.setTextOpacity((byte)opacity);
        /Bukkit.broadcastMessage(opacity + "");*/

        l.add(x, y, z);
        //l.setYaw(yaw);
        //l.setPitch(pitch);

        Vector v = player.getLocation().getBlock().getLocation().subtract(c.getLocation()).toVector();

        //Main.l.info("---> "+v);

        l.setDirection(v);

        c.teleport(l);
        //c.setRotation(yaw, pitch);

        //Main.l.info("CUUU");

    }
}
