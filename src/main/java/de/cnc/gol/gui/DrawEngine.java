package de.cnc.gol.gui;

import java.awt.*;
import javax.swing.*;

import de.cnc.gol.container.Map;

public class DrawEngine extends JPanel {
    private static final int DELAY = 100;
    private static final Color COLOR_DARK = new Color(125, 82, 53);
    private static final Color COLOR_DEFAULT = new Color(238, 238, 210);

    private final int cellSizeHorizontal;
    private final int cellSizeVertical;
    private final Map map;

    public DrawEngine(final Map map) {
        this.map = map;
        this.cellSizeHorizontal = 1900 / map.getWidth();
        this.cellSizeVertical = 1024 / map.getHeight();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Spiel des Lebens");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(this);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public void start() {
        final Timer timer = new Timer(DELAY, event -> {
            map.computeNextRound();
            this.repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        map.getCells().forEach(cell -> {
            graphics.setColor(cell.isAlive() ? COLOR_DARK : COLOR_DEFAULT);
            graphics.fillRect(cell.getX() * cellSizeHorizontal, cell.getY() * cellSizeVertical, cellSizeHorizontal, cellSizeVertical);
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension( map.getWidth() * cellSizeHorizontal, map.getHeight() * cellSizeVertical);
    }
}
