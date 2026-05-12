package de.cnc.gol.container;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/** Objects represent cells in a coordinate system. */
public class Cell {
    /** Coordinate in the Euclidean plane. */
    private final int x;

    /** Coordinate in the Euclidean plane. */
    private final int y;

    /** Indicator whether the cell is alive in the current round. */
    private boolean alive;

    /** Indicator whether the cell will be alive in the next round. */
    private boolean aliveNextRound;

    /** Collection of all neighbours of this cell. */
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
