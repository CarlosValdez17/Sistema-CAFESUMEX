/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosGenerales;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Resaltador extends DefaultTableCellRenderer {

    private final int columna_patron, col2;

    public Resaltador(int Colpatron, int col2) {
        this.columna_patron = Colpatron;
        this.col2 = col2;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
        /*  switch (table.getValueAt(row, columna_patron).toString()) {
       
            case "E1":              
                setForeground(Color.RED);
                setFont(font);
                break;
            case " ":
                setForeground(Color.BLUE);
                setFont(font);
                break;
            default:
                break;
        }*/

        if (table.getValueAt(row, columna_patron).toString().contains("E")) {
            setBackground(Color.RED);
        }

        if (table.getValueAt(row, col2).toString().contains("S")) {
            setBackground(new Color(202, 245, 235));
        }

        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
