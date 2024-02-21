package net.minesky.global;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.HashMap;

public class GlobalCelestial {

    //
    public static HashMap<String, MoonPhases> worldPhase = new HashMap<>();

    public enum MoonPhases {
        _1FULL() {
            @Override public int getNumber() { return 1; }
            @Override
            public String getFont() {
                return "A";
            }
        },
        _2() {
            @Override public int getNumber() { return 2; }
            @Override
            public String getFont() {
                return "B";
            }
        },
        _3() {
            @Override public int getNumber() { return 3; }
            @Override
            public String getFont() {
                return "C";
            }
        },
        _4() {
            @Override public int getNumber() { return 4; }
            @Override
            public String getFont() {
                return "D";
            }
        },
        _5EMPTY() {
            @Override public int getNumber() { return 5; }
            @Override
            public String getFont() {
                return "E";
            }
        },
        _6() {
            @Override public int getNumber() { return 6; }
            @Override
            public String getFont() {
                return "F";
            }
        },
        _7() {
            @Override public int getNumber() { return 7; }
            @Override
            public String getFont() {
                return "G";
            }
        },
        _8() {
            @Override public int getNumber() { return 8; }
            @Override
            public String getFont() {
                return "H";
            }
        };

        public abstract String getFont();
        public abstract int getNumber();
    }

    public static MoonPhases calculatePhase(World w) {
        long i = w.getFullTime() / 24000;
        int phase = (int) ((i % 8) == 0 ? 8 : (i % 8));

        //Bukkit.broadcastMessage(phase + " | "+i);

        for(MoonPhases p : MoonPhases.values()) {
            if(p.getNumber() == phase)
                return p;
        }

        return MoonPhases._1FULL;
    }

    public static MoonPhases getMoonPhase(World w) {
        return calculatePhase(w);
    }

}
