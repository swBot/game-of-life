package de.cnc.gol.materials;

import de.cnc.gol.container.Cell;

/** Dieses Interface erweitert die normale Definition des Spielfeldes um Steuerfunktion, die das Spielfeld selbst betreffen. */
public interface MapMaintained extends Map {
    /** Über diese Funktion wird die Datenstruktur (neu) initialisiert. */
    void initializeDataStructure();

    /** Über diese Funktion werden die Nachbarn für jede Zelle neu berechnet. */
    void computeNeighbours(final Cell cell);

    /** Über diese Funktion werden die aktiven Felder neu festgelegt. */
    void randomizeActiveFields();
}
