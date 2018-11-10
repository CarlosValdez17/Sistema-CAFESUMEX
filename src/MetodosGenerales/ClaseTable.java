/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosGenerales;

import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Carlos Valdez
 */
public class ClaseTable {

    public static void cargarDatos(JTable jtable, ResultSet rs) {
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        modelo.setRowCount(0);
        try {
            ResultSetMetaData metaDatos = rs.getMetaData();
            if (rs.next()) {
                do {
                    Object[] datos = new Object[metaDatos.getColumnCount()];
                    for (int i = 0; i < modelo.getColumnCount(); i++) {
                        try {
                            datos[i] = rs.getString(i + 1);
                        } catch (Exception ex) {
                        }
                    }
                    modelo.addRow(datos);
                } while (rs.next());
                jtable.setModel(modelo);
            }
        } catch (SQLException ex) {
            //MensajeSistema.setSQLException(ex);
        }
    }

    /**
     * Metodo que borra el registro seleccionado de la grilla
     *
     * @param jTable
     */
    public static void removeRowjTable(JTable jTable) {
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        try {
            dtm.removeRow(jTable.getSelectedRow());
        } catch (Exception e) {
//            MensajeSistema.setException("La grilla esta vacia o necesita seleccionar el registro para remover", e);
        }
    }

    /**
     * Metodo que borra el registro que se envia como parametro
     *
     * @param jTable
     * @param row
     */
    public static void removeRowjTable(JTable jTable, int row) {
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        try {
            dtm.removeRow(row);
        } catch (Exception e) {
           // MensajeSistema.setException("La grilla esta vacia o No se encontro el registro a eliminar", e);
        }
    }

    /**
     * Vacia un jTable
     *
     * @param jTable
     */
    public static void vaciarJtable(JTable jTable) {
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        dtm.setRowCount(0);
    }

    /**
     * Metodo para darle al jTable la funcionalidad de ordenar ascendente o
     * descentente en cada columna haciendo clic encima.
     *
     * @param jTable
     */
    public static void ordernar(JTable jTable) {
        System.out.println("Estoy en ordenar");
        TableModel dtm = (TableModel) jTable.getModel();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
    }

    /**
     * Metodo para filtrar un registro en el jTable, recibe como parametro el
     * JTable, el valor a buscar y la posicion de la columna en donde va a
     * buscar las coincidencias del valor..
     *
     * @param jTable
     * @param buscar
     * @param posicion
     */
    public static void filtrar(JTable jTable, String buscar, int posicion) {
        TableModel dtm = (TableModel) jTable.getModel();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(buscar.toUpperCase(), posicion));
    }

    /**
     * Recibe valor de una jTable que fue seleccionado y lo retorna como una
     * cadena de texto, donde, el <code>column</code> representa al orden que
     * tiene la columna dentro del jTable. <code>column</code> Empieza los
     * numeros a partir de 1 en adelante <code>jTable</code> Tabla especificada
     * para recibir el string
     *
     * @param jTable
     * @param columna
     * @return
     */
    public static String getValorSeleccionado(JTable jTable, int columna) {
        int fila = jTable.getSelectedRow(); // devuelve el numero de fila del registro seleccionado         
        if (fila == -1) {
            return "";
        } else { // devuelve el valor de la fila seleccionada
            try {
                return jTable.getValueAt(fila, columna).toString();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    /**
     * Coloca a una jtable una cadena de texto segun la <code>column</code> Este
     * metodo solo funciona cuando los registros ya estan insertados, de otro
     * modo, crea error a la hora de insertar, especialmente por uno vacio
     *
     * @param jTable
     * @param texto
     * @param columna
     */
    public static void setValueATjTable(JTable jTable, String texto, int columna) {
        columna -= 1;
        jTable.setValueAt(texto, jTable.getSelectedRow(), columna);
    }

    /**
     * Asi como en foxpro, sube los datos a la memoria de la matriz, tambien
     * procede de forma similar este metodo, a diferencia de que le hay que
     * pasar 3 datos para funcionar, los cuales son: jtextfield (a quien se le
     * da el valor), jTable (quien posee el dato a dar) y column (en que columna
     * se le da el dato)
     *
     * @param texto
     * @param jTable
     * @param columna
     */
    public static void enTextoValorSelect(JTextField texto, JTable jTable, int columna) {
        try {
            if (jTable.getSelectedRow() > 0) {
                texto.setText(jTable.getValueAt(jTable.getSelectedRow(), columna).toString());
            } else {
                texto.setText("");
            }
        } catch (Exception e) {
            texto.setText("");
        }
    }

/*    public static void enTextoValorSelect(txtTexto texto, JTable jTable, int columna) {
        try {
            if (jTable.getSelectedRow() > 0) {
                texto.setText(jTable.getValueAt(jTable.getSelectedRow(), columna).toString());
            } else {
                texto.setText("");
            }
        } catch (Exception e) {
            texto.setText("");
        }
    }
*/
    /**
     * Devuelve verdadero si la jTable se encuentra vacio
     *
     * @param jTable
     * @return
     */
    public static boolean isEmptyJtable(JTable jTable) {
        int count = 0;
        for (int c = 0; c < jTable.getRowCount(); c++) {
            count += 1;
            break;
        }
        return count == 0;
    }

    /**
     * Sirve para identificar el valor maximo de una tabla y devuelve el entero
     * como retorno. Si no encuentra retorna 0
     *
     * @param jTable
     * @param Columna
     * @return
     */
    public static double getMAX(JTable jTable, int Columna) {
        double tmpMax = 0;
        DefaultTableModel djTable = (DefaultTableModel) jTable.getModel();
        try {
            for (int x = 0; x < djTable.getRowCount(); x++) {
                if (Double.parseDouble(djTable.getValueAt(x, Columna).toString()) > tmpMax) {
                    tmpMax = Double.parseDouble(djTable.getValueAt(x, Columna).toString());
                }
            }
        } catch (NumberFormatException e) {
        }
        return tmpMax;
    }

    /**
     * Si encuentra el valor minimo en una tabla retorna ese numero, de otro
     * modo es 0
     *
     * @param jTable
     * @param Columna
     * @return
     */
    public static double getMIN(JTable jTable, int Columna) {
        if (isEmptyJtable(jTable)) {
            return 0;
        }
        double tmpMin = getMAX(jTable, Columna);
        DefaultTableModel djTable = (DefaultTableModel) jTable.getModel();
        try {
            for (int x = 0; x < djTable.getRowCount(); x++) {
                if (Double.parseDouble(djTable.getValueAt(x, Columna).toString()) < tmpMin) {
                    tmpMin = Double.parseDouble(djTable.getValueAt(x, Columna).toString());
                }
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return tmpMin;
    }

    /**
     * Suma una columna especifica de una jTable
     *
     * @param jTable
     * @param Columna
     * @return
     */
    public static double getSUM(JTable jTable, int Columna) {
        double tmpSuma = 0;
        DefaultTableModel dtmsuma = (DefaultTableModel) jTable.getModel();
        try {
            for (int x = 0; x < dtmsuma.getRowCount(); x++) {
                tmpSuma = tmpSuma + Double.parseDouble(dtmsuma.getValueAt(x, Columna).toString());
            }
        } catch (NumberFormatException e) {
        }
        return tmpSuma;
    }

    /**
     * Metodo que retorna el Promedio de una columna
     *
     * @param jtable
     * @param column Es el Numero de la columna que se va usar para hacer el
     * calculo
     * @return
     */
    public static double getAVG(JTable jtable, int column) {
        double valor = 0;
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                valor += Double.parseDouble(modelo.getValueAt(i, column).toString().replaceAll(",", ""));
            } catch (Exception ex) {
            }
        }
        return valor / modelo.getRowCount();
    }

    /**
     * A partir de una columna (dentro de un jtable) lo convierte en un vector
     * de String. Ejemplo: String[]vedat = comJTable.getColumnaToVector(jTable1,
     * 3);
     *
     * @param table
     * @param columna
     * @return
     */
    public static String[] getColumnaToVector(JTable table, int columna) {
        ArrayList lista = new ArrayList();
        for (int i = 0; i < table.getRowCount(); i++) {
            lista.add(table.getValueAt(i, columna));
        }
        String[] veDatos = new String[lista.size()];
        try {
            for (int i = 0; i < lista.size(); i++) {
                veDatos[i] = lista.get(i).toString();
            }
        } catch (Exception e) {
            return null;
        }
        return veDatos;
    }

    /**
     * Permite alinear a una columna en el lado derecho
     *
     * @param jTable
     * @param Columna
     */
    public static void setAlinearDerecha(JTable jTable, int Columna) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable.getColumnModel().getColumn(Columna).setCellRenderer(tcr);
        jTable.repaint();
    }

    /**
     * Permite alinear a una columna en el centro
     *
     * @param jTable
     * @param Columna
     */
    public static void setAlinearCentro(JTable jTable, int Columna) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable.getColumnModel().getColumn(Columna).setCellRenderer(tcr);
        jTable.repaint();
    }

    /**
     * Permite alinear a una columna en el lado izquierdo
     *
     * @param jTable
     * @param Columna
     */
    public static void setAlinearIzquierdo(JTable jTable, int Columna) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.LEFT);
        jTable.getColumnModel().getColumn(Columna).setCellRenderer(tcr);
        jTable.repaint();
    }

    /**
     * Permite colocar a una columna en modo oculto
     *
     * @param tbl
     * @param columna
     */
    public static void setOcultar(JTable tbl, int columna) {
        try {
            tbl.getColumnModel().getColumn(columna).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna).setMinWidth(0);
        } catch (Exception e) {
        }
    }

    /**
     * Coloca a una columna en el modo visible.
     * <p>
     * Su ancho estandar es como minimo 15 y su maximo es 300. El modo normal
     * con que se muestra es de 75 pulgadas.
     *
     * @param tbl
     * @param columna
     */
    public static void setMostrar(JTable tbl, int columna) {
        tbl.getColumnModel().getColumn(columna).setMaxWidth(450);
        tbl.getColumnModel().getColumn(columna).setMinWidth(15);
        tbl.getTableHeader().getColumnModel().getColumn(columna).setMaxWidth(450);
        tbl.getTableHeader().getColumnModel().getColumn(columna).setMinWidth(15);
        tbl.getColumnModel().getColumn(columna).setPreferredWidth(75);
    }

    /**
     * De forma intercalada oculta o muestra una columna.
     *
     * @param tbl
     * @param columna
     */
    public static void setMostrarOcultar(JTable tbl, int[] columna) {
        if (columna.length <= 0) {
            return;
        }
        for (int i = 0; i < columna.length; i++) {
            if (tbl.getColumnModel().getColumn(columna[i]).getWidth() != 0) {
                setOcultar(tbl, columna[i]);
            } else {
                setMostrar(tbl, columna[i]);
            }
        }
    }

    /**
     * Coloca a una columna a un tamaño especificado segun los parametros que le
     * vallemos asignando. Ejemplo:
     * <p>
     * setWidth(tablaLocal, 1, 100);
     *
     * @param tbl
     * @param columna
     * @param tamaño
     */
    public static void setAncho(JTable tbl, int columna, int tamaño) {
        //tbl.getColumnModel().getColumn(columna).setMinWidth(tamaño);
        //tbl.getColumnModel().getColumn(columna).setMaxWidth(tamaño);
        tbl.getColumnModel().getColumn(columna).setPreferredWidth(tamaño);        
    }

    /**
     * Se le coloca a todas las columnas con el mismo tamaño dentro de un jTable
     *
     * @param tbl
     * @param tamaño
     */
    public static void setAnchoTodos(JTable tbl, int tamaño) {
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setPreferredWidth(tamaño);
        }
    }

    public static void setSeleccionarRegistro(JTable jtable, int codigo) {
        int valorCelda;
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            try {
                valorCelda = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                if (valorCelda == codigo) {
                    jtable.getSelectionModel().setSelectionInterval(fila, fila);
                    break;
                }
            } catch (NumberFormatException ex) {
            }
        }
    }

    public static void setSeleccionarRegistro(JTable jtable, String registro) {
        String valorCelda;
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            try {
                valorCelda = modelo.getValueAt(fila, 0).toString();
                if (valorCelda.equals(registro)) {
                    jtable.getSelectionModel().setSelectionInterval(fila, fila);
                    break;
                }
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Metodo que imprime el contenido de la tabla directamente en la impresora
     *
     * @param jtable
     * @param titulo
     */
    public static void print(JTable jtable, String titulo) {
        try {
            // tabla1.print();//envia los datos de la tabla a la impresora
            MessageFormat headerFormat = new MessageFormat(titulo);
            MessageFormat footerFormat = new MessageFormat("- Página {0} -");
            jtable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
        } catch (PrinterException ex) {
            //MensajeSistema.setException("No se ha podido imprimir correctamente, intentalo más tarde.", ex);
        }
    }

    public static void formatearColumna(JTable jtable, int column, int decimal) {
        DefaultTableModel modelo = (DefaultTableModel) jtable.getModel();
        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            try {
                String xnum = modelo.getValueAt(fila, column).toString();
              //  String valor = ClaseNumeros.formatoMiles(xnum, decimal);
                //modelo.setValueAt(valor, fila, column);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
