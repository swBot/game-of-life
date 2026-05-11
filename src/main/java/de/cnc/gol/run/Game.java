package de.cnc.gol.run;

import javax.swing.Timer;

import de.cnc.gol.container.Map;
import de.cnc.gol.gui.DrawEngine;

public class Game {


    public static void main(String[] args) {
        int repaintDelay = 300;
        int width = 128;
        int height = 64;
        int active = 1000;

        final Map map = new Map(width, height, new RuleEngineDefault());
        map.initialize(active);

        final DrawEngine drawEngine = new DrawEngine(map);

        final Timer timer = new Timer(repaintDelay, event -> {
            map.computeNextRound();
            drawEngine.repaint();
        });
        timer.start();
    }
}

