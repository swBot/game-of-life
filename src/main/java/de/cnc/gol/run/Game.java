package de.cnc.gol.run;

import de.cnc.gol.container.Map;
import de.cnc.gol.gui.DrawingEngine;

public class Game {
    public static void main(String[] args) {
        final Settings settings = new Settings();
        settings.askCustomer();

        final Map map = new Map(settings);
        map.initializeDataStructure();
        map.randomizeActiveFields();

        final DrawingEngine drawingEngine = new DrawingEngine(map, settings);
        drawingEngine.start();
    }
}

