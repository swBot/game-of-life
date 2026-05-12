package de.cnc.gol.container;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/** Objekte dieser Klasse repräsentieren Zellen in einem Koordinatensystem. */
public class Cell {
    /** Koordinate in der euklidischen Ebene. */
    private final int x;

    /** Koordinate in der euklidischen Ebene. */
    private final int y;

    /** Indikator, ob die Zelle in der aktuellen Runde am Leben ist. */
    private boolean alive;

    /** Indikator, ob die Zelle in der nächsten Runde am Leben ist. */
    private boolean aliveNextRound;

    /** Aufzählung aller Nachbarn der Zelle. */
    private final Collection<Cell> neighbours = new HashSet<>();

    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void addNeighbour(final Cell cell) {
        if (!neighbours.contains(cell)) {
            this.neighbours.add(cell);
        }
        cell.getNeighbours().add(this);
    }

    public void setNextRound() {
        this.alive = this.aliveNextRound;
        this.aliveNextRound = false;
    }

    public Collection<Cell> getNeighbours() {
        return neighbours;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    public void setAliveNextRound(final boolean aliveNextRound) {
        this.aliveNextRound = aliveNextRound;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public String toString() {
        return "\nCell{" +
            " alive=" + alive +
            ",  x=" + x +
            ",  y=" + y +
            ",  neighbours=" + neighbours.size() +
            '}';
    }
}
