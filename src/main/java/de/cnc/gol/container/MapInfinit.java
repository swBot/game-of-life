package de.cnc.gol.container;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.cnc.gol.materials.Settings;

/** Zwei-dimensionales Koordinatensystem, welches an den Rändern NICHT beschränkt ist. */
public class MapInfinit extends MapDefault {

    public MapInfinit(final Settings settings) {
        super(settings);
    }

    @Override
    public void initializeDataStructure() {
        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                final Cell cell = new Cell(x, y);
                super.addCell(cell);
            }
        }
        super.getCells().forEach(this::computeNeighbours);

        Logger.getLogger(MapDefault.class.getName()).log(Level.INFO, "infinity Map initialized: " + this);
    }

    @Override
    public void computeNeighbours(final Cell cell) {
        final int x = cell.getX();
        final int y = cell.getY();

        cell.addNeighbour(
            this.getCell(
                x,
                this.fold(y - 1, this.getHeight())
            )
        );
        cell.addNeighbour(
            this.getCell(
                this.fold(x - 1, this.getWidth()),
                y
            )
        );
        cell.addNeighbour(
            this.getCell(
                this.fold(x - 1, this.getWidth()),
                this.fold(y - 1, this.getHeight())
            )
        );
        cell.addNeighbour(
            this.getCell(
                this.fold(x + 1, this.getWidth()),
                this.fold(y - 1, this.getHeight())
            )
        );
    }

    private int fold(final int i, final int max) {
        if (i >= 0 && i < max) {
            return i;
        } else if (i < 0) {
            return max - 1;
        }
        return 0;
    }
}
