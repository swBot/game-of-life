package de.cnc.gol.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.cnc.gol.materials.RuleEngine;
import de.cnc.gol.materials.Settings;

public class Map {
    private final int width;
    private final int height;
    private final int initialActiveFields;
    private final RuleEngine ruleEngine;
    private final Cell[][] board;
    private final Collection<Cell> cells;

    public Map(final Settings settings) {
        this.width = settings.getWidth();
        this.height = settings.getHeight();
        this.initialActiveFields = Math.min(settings.getInitialActive(), width * height);
        this.ruleEngine = settings.getRuleEngine();
        this.board = new Cell[width][height];
        this.cells = new ArrayList<>();
    }

    public void initializeDataStructure() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Cell cell = new Cell(x, y);
                board[x][y] = cell;
                cells.add(cell);
                this.computeNeighbours(cell);
            }
        }

        Logger.getLogger(Map.class.getName()).log(Level.INFO, "Map initialized: " + this);
    }

    private void computeNeighbours(final Cell cell) {
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

    public void computeNextRound() {
        for (Cell cell : cells) {
            cell.setAliveNextRound(ruleEngine.isAliveNextRound(cell));
        }
        for (Cell cell : cells) {
            cell.setNextRound();
        }
    }

    public Cell getCell(final int x, final int y) {
        return board[x][y];
    }

    public Collection<Cell> getCells() {
        return cells;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
