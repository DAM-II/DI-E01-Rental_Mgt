package com.samuel.entrega_01.renderer;

import com.samuel.entrega_01.models.Rental;
import com.samuel.entrega_01.models.RentalStatus;
import com.samuel.entrega_01.services.DataStore;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class RentalTableCellRenderer extends DefaultTableCellRenderer {
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        // Let default renderer prepare the standard text & fonts
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Identify the rental for this row because rows are descending
        List<Rental> rentals = dataStore.getRentals();
        int rentalIndex = rentals.size() -1 - row;

        if(rentalIndex >= 0 && rentalIndex < rentals.size()){
            Rental rental = rentals.get(rentalIndex);
            RentalStatus thisRentalStatus = rental.getStatus();
            Color statusColour = thisRentalStatus.getRowColour();

            // Apply colour if not selected by the user
            if(!isSelected && statusColour != null){
                cellComponent.setBackground(statusColour);
            } else if(!isSelected){
                cellComponent.setBackground(Color.WHITE);
            }
        }
        return cellComponent;
    }
}
