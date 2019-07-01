/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos_Configuraciones;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class metodosParcelas {

    Connection cn;

    public metodosParcelas(Connection cn) {
        this.cn = cn;
    }

    public void insertarBasicos(String sql) {
        try {
            System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            JOptionPane.showMessageDialog(null, "Inserción Exitosa");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Insertar\n" + ex);
        }
    }

    public void actualizarBasicos(String sql) {
        try {
            System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar \n" + ex);
        }
    }

    public String devuelveUnDato(String sql) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[2];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a = datos[0] + "";
                //System.out.println(a);
            }
            cmd.close();
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Devolver 1 Dato - Metodo \n" + ex);
        }
        return null;
    }

    public void cargarInformacion2(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[tamaño];
                for (int i = 0; i < tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info 2" + ex);
        }

    }

    public String cargarDatosFormularioPersonas(String sql, int tamaño) {
        try {
            //System.out.println("--------------------------------------\n" + sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[tamaño];
                for (int i = 0; i < tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                    if (datos[i].equals("")) {
                        datos[i] = " ";
                    }
                    a += datos[i] + "¬";
                }
                //String a = datos[0] + "," + datos[1] + "," + datos[2] + "," + datos[3] + "," + datos[4]+ "," + datos[5] + "," + datos[6] + "," + datos[7] + "," + datos[8]+ "," + datos[9] + "," + datos[10] + "," + datos[11] + "," + datos[12]+ "," + datos[13] + "," + datos[14] + "," + datos[15] + "," + datos[16]+","+datos[17];
                //System.out.println(a);
                return a;

            }
            cmd.close();
        } catch (Exception ex) {

        }
        return null;
    }

    public String cargarCombos(String sql) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[2];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a += datos[0] + "¬";
            }
            cmd.close();
            return "Seleccione...¬" + a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Cargar Combos \n" + ex);
        }
        return null;
    }

    public String devuelveId(String sql) {
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String id = "";
            if (rs.next()) {
                id = rs.getString("ID");
                return id;
            }
            cmd.close();
            return id;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Devolver Id - Metodo \n" + ex);
        }
        return null;
    }

    public Boolean insertarEnCiclo(String sql) {
        try {
            System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Insertar en Ciclo\n" + ex);
            return false;
        }
    }

}
