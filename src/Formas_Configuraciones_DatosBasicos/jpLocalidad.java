/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_DatosBasicos;

import Idioma.Propiedades;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Carlos Valdez
 */
public class jpLocalidad extends javax.swing.JPanel {

    /**
     * Creates new form jpLocalidad
     */
    metodosDatosBasicos mdb;
    DefaultTableModel modelo;
    Connection cn;
    jdLocalidad jdL;
    Propiedades idioma;
    String Idioma;

    public jpLocalidad(Connection c, String Idioma) {
        initComponents();

        cn = c;
        this.Idioma = Idioma;
        mdb = new metodosDatosBasicos(cn);
        modelo = (DefaultTableModel) tablaLocalidad.getModel();

        tablaLocalidad.setRowSorter(new TableRowSorter(modelo));

        idioma = new Propiedades(Idioma);
        jButton5.setText(idioma.getProperty("Cerrar"));
        jButton2.setText(idioma.getProperty("Nuevo"));
        jButton3.setText(idioma.getProperty("Editar"));
        jButton4.setText(idioma.getProperty("Desactivar"));
        jLabel10.setText(idioma.getProperty("Situacion"));
        jLabel6.setText(idioma.getProperty("Localidad"));
        jLabel7.setText(idioma.getProperty("Municipio"));
        jLabel8.setText(idioma.getProperty("Estado"));
        jLabel9.setText(idioma.getProperty("Pais"));

        tablaLocalidad.getColumnModel().getColumn(0).setHeaderValue(idioma.getProperty("Localidad"));
        tablaLocalidad.getColumnModel().getColumn(1).setHeaderValue(idioma.getProperty("Municipio"));
        tablaLocalidad.getColumnModel().getColumn(2).setHeaderValue(idioma.getProperty("Estado"));
        tablaLocalidad.getColumnModel().getColumn(3).setHeaderValue(idioma.getProperty("Pais"));

        comboSituacion.addItem(idioma.getProperty("Activos"));
        comboSituacion.addItem(idioma.getProperty("Inactivos"));
        comboSituacion.addItem(idioma.getProperty("Todos"));

        llenaTablaLocalidad();

    }

    public void llenaTablaLocalidad() {
        limpiar(tablaLocalidad);
        mdb = new metodosDatosBasicos(cn);
        mdb.cargarInformacion2(modelo, 4, "select l.Descripcion, m.Descripcion,e.descripcion, p.descripcion \n"
                + "from localidad l \n"
                + "inner join municipio m on (l.ID_Municipio=m.ID) \n"
                + "inner join estado e on (m.ID_Estado=e.ID) \n"
                + "inner join pais p on (e.ID_Pais = p.ID) \n"
                + "where l.ID_Situacion=1");
    }

    public void busquedaLocalidad() {
        String tipoP = "";
        String tipoOIC = "";
        String tipoUE = "";
        String tipoISO = "";
        String situacion = "";
        situacion = comboSituacion.getSelectedIndex() + "";

        if (situacion.equals("1")) {
            situacion = "2";
        } else if (situacion.equals("0")) {
            situacion = "1";
        } else {
            situacion = "3";
        }

        if (txtBusquedaL.getText().length() > 0) {
            tipoOIC = "AND l.descripcion like '" + txtBusquedaL.getText() + "%'";
        }
        if (txtBusquedaM.getText().length() > 0) {
            tipoUE = "AND m.descripcion like '" + txtBusquedaM.getText() + "%'";
        }
        if (txtBusquedaE.getText().length() > 0) {
            tipoISO = "AND e.descripcion like '" + txtBusquedaE.getText() + "%'";
        }
        if (txtBusquedaP.getText().length() > 0) {
            tipoISO = "AND p.descripcion like '" + txtBusquedaP.getText() + "%'";
        }

        String sql;
        if (situacion.equals("3")) {
            sql = "SELECT l.descripcion, m.descripcion, e.descripcion, p.descripcion from localidad l "
                    + "inner join municipio m on (l.ID_Municipio=m.ID) \n"
                    + "inner join estado e on (m.ID_Estado=e.ID) \n"
                    + "inner join pais p on (e.ID_Pais = p.ID) \n"
                    + "where l.ID_Situacion <> 3 " + tipoP + " " + tipoOIC + " " + tipoUE + " " + tipoISO;
        } else {
            sql = "SELECT l.descripcion, m.descripcion, e.descripcion, p.descripcion from localidad l "
                    + "inner join municipio m on (l.ID_Municipio=m.ID) \n"
                    + "inner join estado e on (m.ID_Estado=e.ID) \n"
                    + "inner join pais p on (e.ID_Pais = p.ID) \n"
                    + "where l.ID_Situacion=" + situacion + " " + tipoP + " " + tipoOIC + " " + tipoUE + " " + tipoISO;
        }
        System.out.println(sql);
        limpiar(tablaLocalidad);
        mdb.cargarInformacion2(modelo, 4, sql);

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
        txtBusquedaL = new javax.swing.JTextField();
        txtBusquedaM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtBusquedaE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBusquedaP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaLocalidad = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboSituacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel6.setText("Localidad");

        txtBusquedaL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaLKeyReleased(evt);
            }
        });

        txtBusquedaM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaMKeyReleased(evt);
            }
        });

        jLabel7.setText("Municipio");

        txtBusquedaE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaEKeyReleased(evt);
            }
        });

        jLabel8.setText("Estado");

        txtBusquedaP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaPKeyReleased(evt);
            }
        });

        jLabel9.setText("Pais");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtBusquedaL, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtBusquedaM, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtBusquedaE, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtBusquedaP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(txtBusquedaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusquedaL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaLocalidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Localidad", "Municipio", "Estado", "Pais"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaLocalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLocalidadMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaLocalidad);
        if (tablaLocalidad.getColumnModel().getColumnCount() > 0) {
            tablaLocalidad.getColumnModel().getColumn(0).setResizable(false);
            tablaLocalidad.getColumnModel().getColumn(1).setResizable(false);
            tablaLocalidad.getColumnModel().getColumn(2).setResizable(false);
            tablaLocalidad.getColumnModel().getColumn(3).setResizable(false);
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jdL = new jdLocalidad(null, true, "1", "", municipio, estado, pais, Idioma, cn);
        jdL.jpL = this;
        jdL.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    String localidad = "", municipio = "", pais = "", estado = "";
    private void tablaLocalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLocalidadMouseClicked
        // TODO add your handling code here:
        localidad = modelo.getValueAt(tablaLocalidad.getSelectedRow(), 0) + "";  //Estado String
        municipio = modelo.getValueAt(tablaLocalidad.getSelectedRow(), 1) + "";
        estado = modelo.getValueAt(tablaLocalidad.getSelectedRow(), 2) + "";
        pais = modelo.getValueAt(tablaLocalidad.getSelectedRow(), 3) + "";

        if (evt.getClickCount() == 1) {
            System.out.println("1 Clic");
        }
        if (evt.getClickCount() == 2) {
            jdL = new jdLocalidad(null, true, "2", localidad, municipio, estado, pais, Idioma, cn);
            jdL.jpL = this;
            jdL.setVisible(true);
        }
    }//GEN-LAST:event_tablaLocalidadMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (municipio.equals("")) {
            JOptionPane.showMessageDialog(null, idioma.getProperty("SeleccionRegistro"));
        } else {
            jdL = new jdLocalidad(null, true, "2", localidad, municipio, estado, pais, Idioma, cn);
            jdL.jpL = this;
            jdL.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtBusquedaLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaLKeyReleased
        // TODO add your handling code here:
        busquedaLocalidad();
    }//GEN-LAST:event_txtBusquedaLKeyReleased

    private void txtBusquedaMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaMKeyReleased
        // TODO add your handling code here:
        busquedaLocalidad();
    }//GEN-LAST:event_txtBusquedaMKeyReleased

    private void txtBusquedaEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaEKeyReleased
        // TODO add your handling code here:
        busquedaLocalidad();
    }//GEN-LAST:event_txtBusquedaEKeyReleased

    private void txtBusquedaPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaPKeyReleased
        // TODO add your handling code here:
        busquedaLocalidad();
    }//GEN-LAST:event_txtBusquedaPKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (comboSituacion.getSelectedIndex() == 0) {
            mdb.actualizarBasicos("UPDATE localidad SET ID_Situacion=2 where descripcion='" + localidad + "'");
        } else if (comboSituacion.getSelectedIndex() == 1) {
            mdb.actualizarBasicos("UPDATE localidad SET ID_Situacion=1 where descripcion='" + localidad + "'");
        }
        busquedaLocalidad();
    }//GEN-LAST:event_jButton4ActionPerformed

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
        busquedaLocalidad();
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
    private javax.swing.JTable tablaLocalidad;
    private javax.swing.JTextField txtBusquedaE;
    private javax.swing.JTextField txtBusquedaL;
    private javax.swing.JTextField txtBusquedaM;
    private javax.swing.JTextField txtBusquedaP;
    // End of variables declaration//GEN-END:variables
}
