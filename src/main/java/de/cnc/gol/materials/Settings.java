package de.cnc.gol.materials;

import java.awt.*;

/** Definition der Einstellungen zum gesamten Spielablauf. */
public interface Settings {
    /** Über diese Funktion wird der Aufrufende nach den Rahmenbedingungen des Spielfeldes gefragt. */
    void askCustomer();

    /** Legt die geforderte Breite des Spielfeldes fest. */
    int getWidth();

    /** Legt die geforderte Höhe des Spielfeldes fest. */
    int getHeight();

    /** Legt die geforderte Menge an Zellen fest, die zu Beginn am Leben sein sollen. */
    int getInitialActive();

    /** Legt die Implementierung der Spielregeln fest. */
    RuleEngine getRuleEngine();

    /** Legt die horizontale Auflösung der Ausgabe fest. */
    int getResolutionHorizontal();

    /** Legt die vertikale Auflösung der Ausgabe fest. */
    int getResolutionVertical();

    /** Legt die Verzögerung fest, mit der das Spiel neue Runden berechnet und zeichnet. */
    int getDelay();

    /** Legt die Farbe der Zellen fest, die nicht am Leben sind. */
    Color getColorDefault();

    /** Legt die Farbe der Zellen fest, die am Leben sind. */
    Color getColorDark();
}
