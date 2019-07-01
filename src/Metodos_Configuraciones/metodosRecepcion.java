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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Valdez
 */
public class metodosRecepcion {

    Connection cn;

    public metodosRecepcion(Connection c) {
        cn = c;
    }

    public Boolean insertarRecibo(String sql) {
        try {
            System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            JOptionPane.showMessageDialog(null, "Recibo Almacenado");
            return true;
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Error, recibo fallido.\n" + ex);
            return false;
        }
    }

    public void insertarCorte(String sql) {
        try {
            //System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            JOptionPane.showMessageDialog(null, "Corte Almacenado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, corte fallido.\n" + ex);
        }
    }

    public Boolean insertarEnCiclo(String sql) {
        try {
            //System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Insertar en Ciclo\n" + ex);
            return false;
        }
    }

    public void actualizarEnCiclo(String sql) {
        try {
            // System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar \n" + ex);
        }
    }

    public void actualizarCorte(String sql) {
        try {
            //System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            JOptionPane.showMessageDialog(null, "Corte Actualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, Actualizar Corte \n" + ex);
        }
    }

    public void cargarInformacion2(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            //System.out.println(sql);
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

    public String comprobarExistencia(String sql) {
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                Object[] datos = new Object[1];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                String a = datos[0] + "";
                return a;
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Comprobar Existencia \n" + ex);
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
            JOptionPane.showMessageDialog(null, "Error - Cargar Combos \n" + ex);
        }
        return null;
    }

    public String generadorStrings(String sql) {
        try {
            //System.out.println(sql + "\n");
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
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Cargar Combos \n" + ex);
        }
        return null;
    }

    public String devolverLineaDatos(String sql, int tamaño) {
        try {
            // System.out.println(sql + "\n");
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
                //System.out.println(a);
            }
            cmd.close();
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Devolver 1 Dato - Metodo \n" + ex);
        }
        return null;
    }

    public String devolverCalificacion(float valor) {

        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall("select clave, descripcion, minima, maxima from calidadcereza");
            ResultSet rs = cmd.executeQuery();
            
            //JOptionPane.showMessageDialog(null, "Valor final : " + valor);
            while (rs.next()) {
                Object[] datos = new Object[4];
                datos[0] = rs.getString("clave");
                datos[1] = rs.getString("descripcion");
                datos[2] = rs.getFloat("minima");
                datos[3] = rs.getFloat("maxima");

                float minima = (float) datos[2];
                float maxima = (float) datos[3];
                String clave = (String) datos[0];
                String desc = (String) datos[1];
                //JOptionPane.showMessageDialog(null,"Minima: "+minima+"\nMaxima: "+maxima );
                if (valor >= minima && valor <= maxima) {
                    return clave + " " + desc;
                }
            }
            cmd.close();
        } catch (Exception ex) {
        }
        return "3 Rosa";
    }

}
