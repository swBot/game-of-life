package de.cnc.gol.run;

import de.cnc.gol.container.MapInfinit;
import de.cnc.gol.gui.DrawingEngineJPanel;
import de.cnc.gol.materials.DrawingEngine;
import de.cnc.gol.materials.MapMaintained;
import de.cnc.gol.materials.Settings;

public class Game {
    public static void main(String[] args) {
        final Settings settings = new SettingsDefault();
        settings.askCustomer();

        final MapMaintained map = new MapInfinit(settings);
        map.initializeDataStructure();
        map.randomizeActiveFields();

        final DrawingEngine drawingEngine = new DrawingEngineJPanel(map, settings);
        drawingEngine.start();
    }
}

