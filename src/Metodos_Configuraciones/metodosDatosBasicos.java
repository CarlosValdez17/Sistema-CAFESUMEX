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
 * @author Carlos Valdez
 */
public class metodosDatosBasicos {

    Connection cn;

    public metodosDatosBasicos(Connection c) {
        cn = c;
    }

    public void insertarBasicos(String sql) {
        try {
            PreparedStatement cmd = cn.prepareCall(sql);
            System.out.println(sql);
            cmd.execute();

            cmd.close();
            JOptionPane.showMessageDialog(null, "Inserción Exitosa");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Insertar");
        }
    }

    public void actualizarBasicos(String sql) {
        try {
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar");
        }
    }

    public void cargarInformacion(DefaultTableModel modelo) {

        try {
            String sql = "SELECT descripcion, OIC, UE, ISO FROM pais";
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[4];
                for (int i = 0; i <= 3; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null,"Excepcion - "+ ex);
        }

    }

    public String cargarDatosEditar(String pais) {
        try {
            String sql = "SELECT id, descripcion,OIC,UE,ISO from pais where descripcion='" + pais + "'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                Object[] datos = new Object[5];
                for (int i = 0; i < 5; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                String a = datos[0] + "," + datos[1] + "," + datos[2] + "," + datos[3] + "," + datos[4];
                return a;
            }
            cmd.close();
        } catch (Exception ex) {

        }
        return null;
    }

    public String cargarCombos(String tipo) {
        try {
            String sql = "SELECT id,descripcion from " + tipo;
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[3];
                for (int i = 0; i < 2; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a += datos[0] + " " + datos[1] + ",";
            }
            cmd.close();
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Cargar Combos \n" + ex);
        }
        return null;
    }

    public void busquedaBasicos(DefaultTableModel modelo, String sql) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                Object[] datos = new Object[4];
                for (int i = 0; i <= 3; i++) {
                    datos[i] = rs.getString(i + 1);
                    System.out.println(i + " " + datos[i]);
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en Busqueda Basicos - " + ex);
        }
    }

}
