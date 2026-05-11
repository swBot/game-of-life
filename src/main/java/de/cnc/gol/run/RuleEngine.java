package de.cnc.gol.run;

import de.cnc.gol.container.Cell;

public interface RuleEngine {
    boolean isAliveNextRound(Cell cell);
}
