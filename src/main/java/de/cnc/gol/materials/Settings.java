package de.cnc.gol.materials;

import java.awt.*;

public interface Settings {
    void askCustomer();

    int getWidth();

    int getHeight();

    int getInitialActive();

    RuleEngine getRuleEngine();

    int getResolutionHorizontal();

    int getResolutionVertical();

    int getDelay();

    Color getColorDefault();

    Color getColorDark();
}
