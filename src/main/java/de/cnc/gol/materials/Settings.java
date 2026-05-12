package de.cnc.gol.materials;

import java.awt.*;

/** Definition of the settings for the entire game. */
public interface Settings {
    /** Prompts the user to configure the game parameters. */
    void askCustomer();

    /** Returns the requested width of the game board. */
    int getWidth();

    /** Returns the requested height of the game board. */
    int getHeight();

    /** Returns the number of cells that should be alive at the start. */
    int getInitialActive();

    /** Returns the rule engine implementation to use. */
    RuleEngine getRuleEngine();

    /** Returns the horizontal resolution of the output. */
    int getResolutionHorizontal();

    /** Returns the vertical resolution of the output. */
    int getResolutionVertical();

    /** Returns the delay (in ms) between computing and drawing each new round. */
    int getDelay();

    /** Returns the colour used for dead cells. */
    Color getColorDefault();

    /** Returns the colour used for living cells. */
    Color getColorDark();
}
