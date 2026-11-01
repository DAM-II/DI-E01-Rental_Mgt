package com.samuel.entrega_01.models;

import java.awt.*;

public enum RentalStatus {
    ACTIVE(null),
    EARLY_RETURN(new Color(212, 237, 218)), // Soft Pastel Green
    OVERDUE(new Color(248, 215, 218)), // Soft pastel red
    LATE_RETURN(new Color(255, 243, 205)); // Soft pastel yellow

    private final Color rowColour;

    RentalStatus(Color rowColour){
        this.rowColour = rowColour;
    }

    public Color getRowColour() {
        return rowColour;
    }
}
