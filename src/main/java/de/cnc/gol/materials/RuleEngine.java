package de.cnc.gol.materials;

import de.cnc.gol.container.Cell;

/** Diese Definition legt die Spielregeln fest. */
public interface RuleEngine {
    /** Über diese Funktion kann für eine gegebene Zelle berechnet werden, ob sie in der nächsten Runde am Leben ist. */
    boolean isAliveNextRound(Cell cell);
}
