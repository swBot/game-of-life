package de.cnc.gol.run;

import de.cnc.gol.container.Cell;
import de.cnc.gol.materials.RuleEngine;

/** Default implementation of the game rules strictly following Conway's Game of Life. */
public class RuleEngineDefault implements RuleEngine {

    @Override
    public boolean isAliveNextRound(final Cell cell) {
        final long aliveNeighbours = cell.getNeighbours().stream().filter(Cell::isAlive).count();
        return (cell.isAlive() && aliveNeighbours == 2) || aliveNeighbours == 3;
    }
}
