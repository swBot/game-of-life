package de.cnc.gol.materials;

import de.cnc.gol.container.Cell;

/** Definition of the game rules. */
public interface RuleEngine {
    /** Determines whether a given cell will be alive in the next round. */
    boolean isAliveNextRound(Cell cell);
}
