package de.cnc.gol.run;

import java.awt.*;
import javax.swing.*;

import de.cnc.gol.materials.RuleEngine;
import de.cnc.gol.materials.Settings;

public class SettingsDefault implements Settings {
    private static final double RATIO_INITIAL_ACTIVE = 0.1;
    private static final double RATIO_RESOLUTION = 9.0 / 16.0;
    private static final int RESOLUTION_HORIZONTAL = 1664;
    private static final int RESOLUTION_VERTICAL = (int) (RESOLUTION_HORIZONTAL * RATIO_RESOLUTION);
    private static final int DELAY = 100;
    private static final Color COLOR_DARK = new Color(125, 82, 53);
    private static final Color COLOR_DEFAULT = new Color(238, 238, 210);

    private final RuleEngine ruleEngine = new RuleEngineDefault();
    private int width = 128;
    private int height = (int) (width * RATIO_RESOLUTION);
    private int initialActive = (int) (width * height * RATIO_INITIAL_ACTIVE);

    @Override
    public void askCustomer() {
        this.width = promptForPositiveInt("Breite eingeben", this.width);
        this.height = promptForPositiveInt("Höhe eingeben", (int) (this.width * RATIO_RESOLUTION));
        this.initialActive = promptForPositiveInt("Initiales Leben eingeben", (int) (width * height * RATIO_INITIAL_ACTIVE));
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getInitialActive() {
        return initialActive;
    }

    @Override
    public RuleEngine getRuleEngine() {
        return ruleEngine;
    }

    @Override
    public int getResolutionHorizontal() {
        return RESOLUTION_HORIZONTAL;
    }

    @Override
    public int getResolutionVertical() {
        return RESOLUTION_VERTICAL;
    }

    @Override
    public int getDelay() {
        return DELAY;
    }

    @Override
    public Color getColorDefault() {
        return COLOR_DEFAULT;
    }

    @Override
    public Color getColorDark() {
        return COLOR_DARK;
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


    @Override
    public String toString() {
        return "SettingsDefault{" +
            " width=" + width +
            ",  height=" + height +
            ",  initialActive=" + initialActive +
            '}';
    }
}
