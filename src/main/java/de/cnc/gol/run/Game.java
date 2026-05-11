package de.cnc.gol.run;

import javax.swing.JOptionPane;

import de.cnc.gol.container.Map;
import de.cnc.gol.gui.DrawEngine;

public class Game {
    public static void main(String[] args) {
        int width = promptForPositiveInt("Breite eingeben", 256);
        int height = promptForPositiveInt("Höhe eingeben", 160);
        int active = promptForPositiveInt("Initiales Leben eingeben", (int) (width * height * 0.08));

        final Map map = new Map(width, height, new RuleEngineDefault());
        map.initialize(Math.min(active, width * height));

        final DrawEngine drawEngine = new DrawEngine(map);
        drawEngine.start();
    }

    private static int promptForPositiveInt(final String message, final int defaultValue) {
        while (true) {
            final String input = JOptionPane.showInputDialog(null, message, defaultValue);
            if (input == null || input.isBlank()) {
                return defaultValue;
            }

            try {
                final int value = Integer.parseInt(input.trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Ungültige Eingabe wird über den Hinweisdialog behandelt.
            }

            JOptionPane.showMessageDialog(
                null,
                "Bitte eine ganze Zahl größer als 0 eingeben.",
                "Ungültige Eingabe",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }
}

