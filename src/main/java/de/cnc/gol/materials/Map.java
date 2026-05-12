package de.cnc.gol.materials;

import java.util.Collection;

import de.cnc.gol.container.Cell;

/** Definition eines Spielfeldes. */
public interface Map {

    /** Diese Funktion gibt eine Zelle anhand seiner kartesischen Koordinaten zurück. */
    Cell getCell(int x, int y);

    /** Diese Funktion gibt alle Zellen ungeordnet zurück. */
    Collection<Cell> getCells();

    /** Diese Funktion gibt die Breite des Spielfeldes zurück. */
    int getWidth();

    /** Diese Funktion gibt die Höhe des Spielfeldes zurück. */
    int getHeight();

    /** Über diese Methode wird die nächste Runde berechnet und eingeleitet. */
    void initiateNextRound();
}
