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
    
    public void insertarRecibo(String sql) {
        try {
           // System.out.println(sql);
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
            JOptionPane.showMessageDialog(null, "Recibo Almacenado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, recibo fallido.\n" + ex);
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
    
    public void actualizarEnCiclo(String sql) {
        try {
            System.out.println(sql);
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
            //System.out.println(sql);
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
            System.out.println(sql + "\n AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String a = "";
            while (rs.next()) {
                Object[] datos = new Object[2];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                }
                a += datos[0] + "#";
            }
            cmd.close();
            return "Seleccione..#" + a;
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
                a += datos[0] + "#";
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
            System.out.println(sql);
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
            
            while (rs.next()) {
                Object[] datos = new Object[2];
                datos[0] = rs.getString("estad");
                datos[1] = rs.getString("idventa");
                datos[2] = rs.getString("total");
                datos[3] = 0;
                
                modelo.addRow(datos);
                
            }
            
            cmd.close();
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
    
    public void cargarInformacion4(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            
            while (rs.next()) {
                String[] datos = new String[10];
                
                for (int i = 0; i < tamaño; i++) {
                    datos[i] = rs.getString(i + 1);
                    if (i == 0) {
                        int b, b2;
                        String[] c1 = datos[0].split(",");
                        
                        b = Integer.parseInt(c1[0]);
                        b2 = Integer.parseInt(c1[1]);
                        char digit1 = (char) b;
                        char digit2 = (char) b2;
                        datos[0] = digit1 + "" + digit2;
                        
                    }
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info 4" + ex);
        }
        
    }
    
    public void tablaDetallesLocalidad(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            
            while (rs.next()) {
                Object[] datos = new Object[10];
                for (int i = 0; i < tamaño; i++) {
                    
                    datos[i] = rs.getString(i + 1);
                    
                    //JOptionPane.showMessageDialog(null, "Dato " + i + " =" + datos[i]);
                    
                    if (i == 8) {
                        
                       // JOptionPane.showMessageDialog(null, "Datos 8 = " + datos[8]);
                        if (datos[8].equals("1")) {
                            datos[8] = true;
                        } else if (datos[8].equals("0")) {
                            datos[8] = false;
                        }
                        System.out.println(datos[8]);
                        
                    }
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info 2" + ex);
        }
        
    }
    
    
    public void tablaAlmacenes(DefaultTableModel modelo, int tamaño, String sql) {
        try {
            System.out.println(sql);
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            
            while (rs.next()) {
                Object[] datos = new Object[10];
                for (int i = 0; i < tamaño; i++) {             
                    datos[i] = rs.getString(i + 1);         
                    //JOptionPane.showMessageDialog(null, "Dato " + i + " =" + datos[i]);           
                    if (i == 8) {   
                        //JOptionPane.showMessageDialog(null, "Datos 8 = " + datos[8]);
                        if (datos[8].equals("1")) {
                            datos[8] = true;
                        } else if (datos[8].equals("0")) {
                            datos[8] = false;
                        }
                       // System.out.println(datos[8]);   
                    }
                }
                modelo.addRow(datos);
            }
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Excepcion - Error Cargar Info 2" + ex);
        }
        
    }
    
    public String devolverCalificacion(float valor) {
        
        try {
            //System.out.println(sql);
            CallableStatement cmd = cn.prepareCall("select clave, descripcion, minima, maxima from calidadcereza");
            ResultSet rs = cmd.executeQuery();
            
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
                if (valor >= minima && valor <= maxima) {
                    return clave + " " + desc;
                }
            }
            
            cmd.close();
        } catch (Exception ex) {
        }
        return "0";
    }
    
}