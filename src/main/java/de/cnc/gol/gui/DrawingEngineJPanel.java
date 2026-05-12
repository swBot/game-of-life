package de.cnc.gol.gui;

import java.awt.*;
import javax.swing.*;

import de.cnc.gol.materials.DrawingEngine;
import de.cnc.gol.materials.Map;
import de.cnc.gol.materials.Settings;

/** Implementation of the graphical output using JPanel. */
public class DrawingEngineJPanel extends JPanel implements DrawingEngine {
    /** Horizontal size of a rendered cell in pixels. */
    private final int cellSizeHorizontal;

    /** Vertical size of a rendered cell in pixels. */
    private final int cellSizeVertical;

    /** The map to be rendered. */
    private final Map map;

    /** Delay in milliseconds between computing and drawing each round. */
    private final int delay;

    /** Colour of living cells. */
    private final Color colorDark;

    /** Colour of dead cells. */
    private final Color colorDefault;

    /** Total size of the rendered game area. */
    private final Dimension dimension;

    public DrawingEngineJPanel(final Map map, final Settings settings) {
        this.map = map;
        this.delay = settings.getDelay();
        this.colorDark = settings.getColorDark();
        this.colorDefault = settings.getColorDefault();
        this.cellSizeHorizontal = settings.getResolutionHorizontal() / map.getWidth();
        this.cellSizeVertical = settings.getResolutionVertical() / map.getHeight();
        this.dimension = new Dimension(settings.getResolutionHorizontal(), settings.getResolutionVertical());

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Spiel des Lebens");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(this);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    @Override
    public void start() {
        final Timer timer = new Timer(delay, event -> {
            map.initiateNextRound();
            this.repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        map.getCells().forEach(cell -> {
            graphics.setColor(cell.isAlive() ? colorDark : colorDefault);
            graphics.fillRect(cell.getX() * cellSizeHorizontal, cell.getY() * cellSizeVertical, cellSizeHorizontal, cellSizeVertical);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }
}
