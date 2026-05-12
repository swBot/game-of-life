package de.cnc.gol.materials;

import java.util.Collection;

import de.cnc.gol.container.Cell;

/** Definition of a game board. */
public interface Map {

    /** Returns a cell by its Cartesian coordinates. */
    Cell getCell(int x, int y);

    /** Returns all cells in no particular order. */
    Collection<Cell> getCells();

    /** Returns the width of the game board. */
    int getWidth();

    /** Returns the height of the game board. */
    int getHeight();

    /** Computes and initiates the next round. */
    void initiateNextRound();
}
