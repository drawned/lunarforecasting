package net.minesky.celestials;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minesky.global.GlobalCelestial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class CelestialDisplay {

    private final TextDisplay display;
    private GlobalCelestial.MoonPhases moonPhase;

    public Transformation getTransformation() {
        Quaternionf f = new Quaternionf(0f,0f,0,1f);
        return new Transformation(new Vector3f(0f, 0f, 0f), f, new Vector3f(5.5f, 5.5f, 5.5f), f);
    }

    public static int getOpacity(int ticks) {
        if (ticks >= 10000 && ticks <= 12000) {

            double v = (ticks / 100d) * 2 / 255;
            double p = v * 255;

            return (int)p;

        }
        else if (ticks >= 22000 && ticks <= 24000) {

            double v = (ticks / 100d) * 2 / 255;
            double p = v * 255;

            return (int)p;

        }
        else if(ticks >= 12000)
            return 255;

        return 1;
    }

    public CelestialDisplay(Location origin) {
        final Component finalSpaceComponent = getComponent(origin.getWorld());
        this.display = origin.getWorld().spawn(origin, TextDisplay.class, id -> {
            id.setTransformation(getTransformation());
            id.setViewRange(400);
            id.text(finalSpaceComponent);
            //id.setTextOpacity((byte)0);
            id.setBrightness(new Display.Brightness(15, 15));
        });

    }

    public static Component getComponent(World w) {
        //Component spaceComponent = Component.text("༖༖\uF802\uF836");
        Component spaceComponent = Component.text("");

        Component celestialComponent = Component.text(GlobalCelestial.getMoonPhase(w).getFont())
                .font(Key.key("celestials"));
        //.color(TextColor.color(0x13f832))

        spaceComponent = spaceComponent.append(celestialComponent);

        return spaceComponent;
    }

    public GlobalCelestial.MoonPhases getSetMoonPhase() {
        return this.moonPhase;
    }

    public void updateMoonPhase(World w) {
        GlobalCelestial.MoonPhases moonPhases = GlobalCelestial.getMoonPhase(getTextDisplay().getWorld());

        getTextDisplay().text(getComponent(w));

        this.moonPhase = moonPhases;
    }

    public TextDisplay getTextDisplay() {
        return display;
    }
}
