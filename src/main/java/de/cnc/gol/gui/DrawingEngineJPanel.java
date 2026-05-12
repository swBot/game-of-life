package de.cnc.gol.gui;

import java.awt.*;
import javax.swing.*;

import de.cnc.gol.container.Map;
import de.cnc.gol.materials.DrawingEngine;
import de.cnc.gol.materials.Settings;


public class DrawingEngineJPanel extends JPanel implements DrawingEngine {
    private final int cellSizeHorizontal;
    private final int cellSizeVertical;
    private final Map map;
    private final int delay;
    private final Color colorDark;
    private final Color colorDefault;

    public DrawingEngineJPanel(final Map map, final Settings settings) {
        this.map = map;
        this.cellSizeHorizontal = settings.getResolutionHorizontal() / map.getWidth();
        this.cellSizeVertical = settings.getResolutionVertical() / map.getHeight();
        this.delay = settings.getDelay();
        this.colorDark = settings.getColorDark();
        this.colorDefault = settings.getColorDefault();

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
            map.computeNextRound();
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
        return new Dimension( map.getWidth() * cellSizeHorizontal, map.getHeight() * cellSizeVertical);
    }
}
