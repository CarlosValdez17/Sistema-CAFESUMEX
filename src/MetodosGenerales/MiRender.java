/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosGenerales;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author USUARIO
 */
public class MiRender extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {

        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       /* if (value instanceof Long) {
            long valor = (long) value;
            if (column == 3) {
                if (valor < 3) {
                    cell.setBackground(Color.yellow);
                }
            }
        }*/      
        if(value instanceof String){     
            String Valor = (String) value;
            cell.setBackground(new Color(202, 245, 235));
            cell.setForeground(Color.black);    
        }
        
        return cell;
    }

}
