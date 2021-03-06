/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_FincaCert;

import Idioma.Propiedades;
import FormasGenerales.pantallaPrincipal;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Valdez
 */
public class jpCalidadSombra extends javax.swing.JPanel {

    /**
     * Creates new form jpCalidadSombra
     */
    metodosDatosBasicos mdb;
    DefaultTableModel modelo;
    jdCalidadSombra jdCS;
    public pantallaPrincipal pp;
    Connection cn;

    Propiedades idioma;
    String Idioma;

    public jpCalidadSombra(Connection c, String Idioma) {
        initComponents();

        cn = c;
        this.Idioma = Idioma;
        mdb = new metodosDatosBasicos(cn);
        modelo = (DefaultTableModel) tablaCalidadSombra.getModel();

        idioma = new Propiedades(Idioma);
        jButton5.setText(idioma.getProperty("Cerrar"));
        jButton2.setText(idioma.getProperty("Nuevo"));
        jButton3.setText(idioma.getProperty("Editar"));
        jButton4.setText(idioma.getProperty("Desactivar"));
        jLabel10.setText(idioma.getProperty("Situacion"));
        jLabel6.setText(idioma.getProperty("CalidadEstrellas"));
        jLabel7.setText(idioma.getProperty("AlturaMaximaMts."));
        jLabel8.setText(idioma.getProperty("TipoDeSombra"));
        jLabel9.setText(idioma.getProperty("CoberturaDeSombraPorcen"));

        tablaCalidadSombra.getColumnModel().getColumn(0).setHeaderValue(idioma.getProperty("CalidadEstrellas"));
        tablaCalidadSombra.getColumnModel().getColumn(1).setHeaderValue(idioma.getProperty("AlturaMaximaMts."));
        tablaCalidadSombra.getColumnModel().getColumn(2).setHeaderValue(idioma.getProperty("TipoDeSombra"));
        tablaCalidadSombra.getColumnModel().getColumn(3).setHeaderValue(idioma.getProperty("CoberturaDeSombraPorcen"));
        tablaCalidadSombra.getColumnModel().getColumn(4).setHeaderValue(idioma.getProperty("Situacion"));

        comboSituacion.addItem(idioma.getProperty("Activos"));
        comboSituacion.addItem(idioma.getProperty("Inactivos"));
        comboSituacion.addItem(idioma.getProperty("Todos"));

        llenaTablaCalidad();
    }

    ArrayList<String> array = new ArrayList<String>();

    public void llenaTablaCalidad() {
        limpiar(tablaCalidadSombra);
        mdb.cargarInformacionPruebaArray(modelo, 6, "select estrellas, alturasombrametros, t.Descripcion, cobertura,s.descripcion,c.id \n"
                + "from calidadsombra c \n"
                + "inner join tiposombra t on (c.ID_tiposombra=t.ID) \n"
                + "inner join situacion s on (c.id_situacion=s.id) "
                + "where c.ID_Situacion=1", array);
    }

    public void busqueda() {
        String tipoP = "";
        String tipoOIC = "";
        String tipoUE = "";
        String tipoISO = "";
        String situacion = comboSituacion.getSelectedIndex() + "";

        if (situacion.equals("1")) {
            situacion = "2";
        } else if (situacion.equals("0")) {
            situacion = "1";
        } else {
            situacion = "3";
        }

        if (txtBusquedaC.getText().length() > 0) {
            tipoOIC = "AND estrellas like '" + txtBusquedaC.getText() + "%'";
        }
        if (txtBusquedaA.getText().length() > 0) {
            tipoUE = "AND alturasombrametros like '" + txtBusquedaA.getText() + "%'";
        }
        if (txtBusquedaTS.getText().length() > 0) {
            tipoISO = "AND t.descripcion like '" + txtBusquedaTS.getText() + "%'";
        }
        if (txtBusquedaCS.getText().length() > 0) {
            tipoISO = "AND cobertura like '" + txtBusquedaCS.getText() + "%'";
        }

        String sql;
        if (situacion.equals("3")) {
            sql = "select estrellas, alturasombrametros, t.Descripcion, cobertura,s.descripcion,c.id \n"
                    + "from calidadsombra c \n"
                    + "inner join tiposombra t on (c.ID_tiposombra=t.ID) \n"
                    + "inner join situacion s on (c.id_situacion=s.id) "
                    + "where c.ID_Situacion <> 3 " + tipoP + " " + tipoOIC + " " + tipoUE + " " + tipoISO;
        } else {
            sql = "select estrellas, alturasombrametros, t.Descripcion, cobertura,s.descripcion,c.id \n"
                    + "from calidadsombra c \n"
                    + "inner join tiposombra t on (c.ID_tiposombra=t.ID) \n"
                    + "inner join situacion s on (c.id_situacion=s.id) "
                    + "where c.ID_Situacion=" + situacion + " " + tipoP + " " + tipoOIC + " " + tipoUE + " " + tipoISO;
        }
        limpiar(tablaCalidadSombra);
        array.clear();
        mdb.cargarInformacionPruebaArray(modelo, 6, sql, array);
        //System.out.println("ARRAY IMPRESO POSICION \n" + array);

    }

    private void limpiar(JTable tabla) {
        while (tabla.getRowCount() > 0) {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtBusquedaC = new javax.swing.JTextField();
        txtBusquedaA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtBusquedaTS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBusquedaCS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCalidadSombra = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboSituacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel6.setText("Calidad, estrellas");

        txtBusquedaC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaCKeyReleased(evt);
            }
        });

        txtBusquedaA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaAKeyReleased(evt);
            }
        });

        jLabel7.setText("Altura Max, metros");

        txtBusquedaTS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaTSKeyReleased(evt);
            }
        });

        jLabel8.setText("Tipo de sombra");

        txtBusquedaCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaCSKeyReleased(evt);
            }
        });

        jLabel9.setText("Cobertura de Sombra");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtBusquedaC, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtBusquedaA, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtBusquedaTS, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtBusquedaCS, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaCS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaCalidadSombra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Calidad (Estrellas)", "Altura (metros)", "Tipo de Sombra", "Cobertura %", "Situacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCalidadSombra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCalidadSombraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCalidadSombra);
        if (tablaCalidadSombra.getColumnModel().getColumnCount() > 0) {
            tablaCalidadSombra.getColumnModel().getColumn(0).setResizable(false);
            tablaCalidadSombra.getColumnModel().getColumn(1).setResizable(false);
            tablaCalidadSombra.getColumnModel().getColumn(2).setResizable(false);
            tablaCalidadSombra.getColumnModel().getColumn(3).setResizable(false);
            tablaCalidadSombra.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel10.setText("Situacion");

        comboSituacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSituacionActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Desactivar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cerrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(comboSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedaCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCKeyReleased
        // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_txtBusquedaCKeyReleased

    private void txtBusquedaAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaAKeyReleased
        // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_txtBusquedaAKeyReleased

    private void txtBusquedaTSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaTSKeyReleased
        // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_txtBusquedaTSKeyReleased

    private void txtBusquedaCSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCSKeyReleased
        // TODO add your handling code here:
        busqueda();
    }//GEN-LAST:event_txtBusquedaCSKeyReleased
    String id = "", calidad = "", altura = "", tipo = "", cobertura = "", situacion = "";
    private void tablaCalidadSombraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCalidadSombraMouseClicked
        // TODO add your handling code here:
        try {
            calidad = tablaCalidadSombra.getValueAt(tablaCalidadSombra.getSelectedRow(), 0) + "";  //Estado String
            altura = tablaCalidadSombra.getValueAt(tablaCalidadSombra.getSelectedRow(), 1) + "";
            tipo = tablaCalidadSombra.getValueAt(tablaCalidadSombra.getSelectedRow(), 2) + "";
            cobertura = tablaCalidadSombra.getValueAt(tablaCalidadSombra.getSelectedRow(), 3) + "";
            situacion = tablaCalidadSombra.getValueAt(tablaCalidadSombra.getSelectedRow(), 4) + "";
            id = array.get(tablaCalidadSombra.getSelectedRow());

            if (evt.getClickCount() == 2) {
                jdCS = new jdCalidadSombra(null, true, "2", calidad, altura, tipo, cobertura, id, Idioma, cn);
                jdCS.jpCS = this;
                jdCS.setVisible(true);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_tablaCalidadSombraMouseClicked
    String estatus = "";
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jdCS = new jdCalidadSombra(null, true, "1", calidad, altura, tipo, cobertura, id, Idioma, cn);
        jdCS.jpCS = this;
        jdCS.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (calidad.equals("")) {
            //JOptionPane.showMessageDialog(null, "Seleccione un estado");
            JOptionPane.showMessageDialog(null, idioma.getProperty("SeleccionRegistro"));
        } else {
            if (situacion.equals("Activo")) {
                jdCS = new jdCalidadSombra(null, true, "2", calidad, altura, tipo, cobertura, id, Idioma, cn);
                jdCS.jpCS = this;
                jdCS.setVisible(true);
            } else if (situacion.equals("Inactivo")) {
                JOptionPane.showMessageDialog(null, "Dato Inactivo");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (comboSituacion.getSelectedIndex() == 0) {
            mdb.actualizarBasicos("UPDATE calidadsombra SET ID_Situacion=2 where id=" + id);
        } else if (comboSituacion.getSelectedIndex() == 1) {
            mdb.actualizarBasicos("UPDATE calidadsombra SET ID_Situacion=1 where id=" + id);
        }
        busqueda();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            pp.pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(jpCalidadSombra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void comboSituacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSituacionActionPerformed
        // TODO add your handling code here:
        if (comboSituacion.getSelectedItem().equals(idioma.getProperty("Inactivos"))) {
            jButton4.setText(idioma.getProperty("Activar"));
            jButton4.setEnabled(true);
        } else if (comboSituacion.getSelectedItem().equals(idioma.getProperty("Activos"))) {
            jButton4.setText(idioma.getProperty("Desactivar"));
            jButton4.setEnabled(true);
        } else {
            jButton4.setEnabled(false);
        }
        busqueda();
    }//GEN-LAST:event_comboSituacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboSituacion;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCalidadSombra;
    private javax.swing.JTextField txtBusquedaA;
    private javax.swing.JTextField txtBusquedaC;
    private javax.swing.JTextField txtBusquedaCS;
    private javax.swing.JTextField txtBusquedaTS;
    // End of variables declaration//GEN-END:variables
}
