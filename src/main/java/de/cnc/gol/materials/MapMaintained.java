package de.cnc.gol.materials;

import de.cnc.gol.container.Cell;

/** This interface extends the base map definition with maintenance operations that concern the board itself. */
public interface MapMaintained extends Map {
    /** Initialises (or re-initialises) the internal data structure. */
    void initializeDataStructure();

    /** Recomputes the neighbours for a given cell. */
    void computeNeighbours(final Cell cell);

    /** Randomly (re-)assigns which cells are initially alive. */
    void randomizeActiveFields();
}
