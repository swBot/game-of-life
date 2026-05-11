package de.cnc.gol.gui;

import java.awt.*;
import javax.swing.*;

import de.cnc.gol.container.Map;

public class DrawEngine extends JPanel {

    private static final int CELL_SIZE = 16;
    private final Map map;

    public DrawEngine(final Map map) {
        this.map = map;

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Punktiversum");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(this);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {
                boolean isDark = map.getCell(col, row).isAlive();
                g.setColor(isDark ? new Color(125, 82, 53) : new Color(238, 238, 210));
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(map.getWidth() * CELL_SIZE, map.getHeight() * CELL_SIZE);
    }
}
