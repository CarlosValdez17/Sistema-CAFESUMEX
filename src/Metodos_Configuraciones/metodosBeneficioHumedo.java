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
public class metodosBeneficioHumedo {

    Connection cn;

    public metodosBeneficioHumedo(Connection cn) {
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
     
    public void cargarInformacionColumna1B(DefaultTableModel modelo, int tamaño, String sql) {
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
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info Col1B" + ex);
        }
    }

    public String devolverLineaDatos(String sql, int tamaño) {
        try {
            System.out.println(sql + "\n");
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[tamaño];
                for (int i = 0; i < tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                    a += datos[i] + "¬";
                    System.out.println(a);
                }
            }
            cmd.close();
            //System.out.println(a);
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Devolver Linea BH \n" + ex);
        }
        return null;
    }
    
    
    public String cargarCombos(String sql) {
        try {
            //System.out.println(sql + "\n AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
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
            return "Seleccione..¬" + a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Cargar Combos BH \n" + ex);
        }
        return null;
    }
    
    
     public String devuelveUnDato(String sql) {
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[2];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a = datos[0] + "";
              
            }
            cmd.close();
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Devolver 1 Dato - BH \n" + ex);
        }
        return null;
    }

}
