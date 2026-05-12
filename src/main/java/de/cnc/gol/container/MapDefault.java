package de.cnc.gol.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.cnc.gol.materials.MapMaintained;
import de.cnc.gol.materials.RuleEngine;
import de.cnc.gol.materials.Settings;

/** Zwei-dimensionales Koordinatensystem, welches an den Rändern beschränkt ist. */
public class MapDefault implements MapMaintained {
    /** Breite des Koordinatensystems. */
    private final int width;

    /** Höhe des Koordinatensystems. */
    private final int height;

    /** Anzahl der Felder die initial am Leben sein sollen. */
    private final int initialActiveFields;

    /** Controller der die Regeln des Spiels durchsetzt. */
    private final RuleEngine ruleEngine;

    /** Elemente des Koordinatensystems angeordnet in einer 2-dimensionalen Matrix. */
    private final Cell[][] board;

    /** Elemente des Koordinatensystems ungeordnet. */
    private final Collection<Cell> cells;

    public MapDefault(final Settings settings) {
        this.width = settings.getWidth();
        this.height = settings.getHeight();
        this.initialActiveFields = Math.min(settings.getInitialActive(), width * height);
        this.ruleEngine = settings.getRuleEngine();
        this.board = new Cell[width][height];
        this.cells = new ArrayList<>();
    }

    @Override
    public void initializeDataStructure() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Cell cell = new Cell(x, y);
                this.addCell(cell);
                this.computeNeighbours(cell);
            }
        }

        Logger.getLogger(MapDefault.class.getName()).log(Level.INFO, "Map initialized: " + this);
    }

    @Override
    public void computeNeighbours(final Cell cell) {
        final int x = cell.getX();
        final int y = cell.getY();

        if (x > 0) {
            cell.addNeighbour(this.getCell(x - 1, y));
            if (y > 0) {
                cell.addNeighbour(this.getCell(x - 1, y - 1));
            }
        }
        if (y > 0) {
            cell.addNeighbour(this.getCell(x, y - 1));
            if (x < width - 1) {
                cell.addNeighbour(this.getCell(x + 1, y - 1));
            }
        }
    }

    @Override
    public void randomizeActiveFields() {
        cells.forEach(cell -> cell.setAlive(false));

        for (int i = 0; i < initialActiveFields; i++) {
            final int x = ThreadLocalRandom.current().nextInt(width);
            final int y = ThreadLocalRandom.current().nextInt(height);
            final Cell cell = this.getCell(x, y);
            if (cell.isAlive()) {
                i--;
            }
            cell.setAlive(true);
        }
    }

    @Override
    public void initiateNextRound() {
        cells.forEach(cell -> cell.setAliveNextRound(ruleEngine.isAliveNextRound(cell)));
        cells.forEach(Cell::setNextRound);
    }

    @Override
    public Cell getCell(final int x, final int y) {
        return board[x][y];
    }

    @Override
    public Collection<Cell> getCells() {
        return cells;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    protected void addCell(final Cell cell) {
        this.board[cell.getX()][cell.getY()] = cell;
        this.cells.add(cell);
    }

    @Override
    public String toString() {
        return "Map{" +
            " width=" + width +
            ",  height=" + height +
            ",  cells=" + cells +
            '}';
    }
}
