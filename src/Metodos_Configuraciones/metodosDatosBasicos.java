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
public class metodosDatosBasicos {

    Connection cn;

    public metodosDatosBasicos(Connection c) {
        cn = c;
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

    public void insertarEnCiclo(String sql) {
        try {
            System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Insertar en Ciclo\n" + ex);
        }
    }

    public void actualizarBasicos(String sql) {
        try {
            System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            JOptionPane.showMessageDialog(null, "Dato Actualizado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Actualizar \n" + ex);
        }
    }

    public void cargarInformacion(DefaultTableModel modelo) {

        try {
            String sql = "SELECT descripcion, OIC, UE, ISO FROM pais where ID_Situacion=1";
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

    public void cargarInformacionPruebaArray(DefaultTableModel modelo, int tamaño, String sql, ArrayList<String> array) {
        try {
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[tamaño];
                for (int i = 0; i < tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                array.add(datos[tamaño - 1] + "");
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Prueba Array" + ex);
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

    public String cargarDatosFormularioPersonas(String sql, int tamaño) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[tamaño];
                for (int i = 0; i < tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                    if(datos[i].equals("")){
                        datos[i]=" ";
                    }
                    a += datos[i] + "¬";
                }
                //String a = datos[0] + "," + datos[1] + "," + datos[2] + "," + datos[3] + "," + datos[4]+ "," + datos[5] + "," + datos[6] + "," + datos[7] + "," + datos[8]+ "," + datos[9] + "," + datos[10] + "," + datos[11] + "," + datos[12]+ "," + datos[13] + "," + datos[14] + "," + datos[15] + "," + datos[16]+","+datos[17];
                System.out.println(a);
                return a;
                
            }
            cmd.close();
        } catch (Exception ex) {

        }
        return null;
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
            //System.out.println(sql + "\n");
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[2];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a +=datos[0] + "#";
            }
            cmd.close();
            return "Seleccione..#"+a;
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
                a +=datos[0] + "#";
            }
            cmd.close();
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Cargar Combos \n" + ex);
        }
        return null;
    }

    
    public String cargarFormaProceso(String sql) {
        try {
            // System.out.println(sql + "\n");
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[2];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a += "• " + datos[0] + "\n\n";
            }
            cmd.close();
            return a;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error - Cargar Forma/Procesos \n" + ex);
        }
        return null;
    }

    public String devuelveId(String sql) {
        try {
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String id="";
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
           // System.out.println(sql);
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
            JOptionPane.showMessageDialog(null, "Error - Devolver 1 Dato - Metodo \n" + ex);
        }
        return null;
    }

    public void busquedaBasicos(DefaultTableModel modelo, String sql, int tamaño) {
        try {
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                Object[] datos = new Object[tamaño];
                for (int i = 0; i <= tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en Busqueda Basicos - " + ex);
        }
    }

    public void cargarInformacion3(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[10];
                for (int i = 0; i < tamaño; i++) {

                    datos[i] = rs.getString(i + 1);
                    if (i == 1) {

                        if (datos[1].equals("1")) {
                            datos[1] = true;
                        } else {
                            datos[1] = false;
                        }
                        //System.out.println(datos[i]);
                    } else if (i == 2) {
                        if (datos[2].equals("1")) {
                            datos[2] = true;
                        } else {
                            datos[2] = false;
                        }
                    }
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info 2" + ex);
        }

    }

    public void cargarInformacionFormaEvaluaciones(DefaultTableModel modelo, String sql) {

        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            //System.out.println("antes de while");
            while (rs.next()) {
                //System.out.println("while");
                Object[] datos = new Object[2];
                datos[0] = rs.getString("estad");
                datos[1] = rs.getString("idventa");
                datos[2] = rs.getString("total");
                datos[3] = 0;

                modelo.addRow(datos);

            }

            //System.out.println("desps de while");

            cmd.close();
            //    cn.close();

        } catch (Exception ex) {
        }
    }

    public void cargarInformacionEvaluaciones(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[10];
                for (int i = 0; i < tamaño; i++) {

                    datos[i] = rs.getString(i + 1);
                    if (i == 1) {

                        if (datos[1].equals("1")) {
                            datos[1] = true;
                        } else {
                            datos[1] = false;
                        }
                        //System.out.println(datos[i]);
                    } else if (i == 2) {
                        if (datos[2].equals("1")) {
                            datos[2] = true;
                        } else {
                            datos[2] = false;
                        }
                    }
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info 2" + ex);
        }

    }

}
