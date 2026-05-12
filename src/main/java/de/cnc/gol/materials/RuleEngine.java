package de.cnc.gol.materials;

import de.cnc.gol.container.Cell;

public interface RuleEngine {
    boolean isAliveNextRound(Cell cell);
}
