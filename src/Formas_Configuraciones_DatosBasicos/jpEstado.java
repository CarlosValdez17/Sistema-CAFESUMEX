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
public class jpEstado extends javax.swing.JPanel {

    /**
     * Creates new form jpEstado
     */
    jdEstado jdE;
    Connection cn;
    metodosDatosBasicos mdb;
    DefaultTableModel modelo;

    Propiedades idioma;
    String Idioma;

    public jpEstado(Connection c, String Idioma) {
        initComponents();

        cn = c;
        this.Idioma = Idioma;
        mdb = new metodosDatosBasicos(cn);
        modelo = (DefaultTableModel) tablaEstado.getModel();

        tablaEstado.setRowSorter(new TableRowSorter(modelo));

        idioma = new Propiedades(Idioma);
        jButton1.setText(idioma.getProperty("Cerrar"));
        jButton2.setText(idioma.getProperty("Nuevo"));
        jButton3.setText(idioma.getProperty("Editar"));
        jButton4.setText(idioma.getProperty("Desactivar"));
        jLabel10.setText(idioma.getProperty("Situacion"));
        jLabel6.setText(idioma.getProperty("Estado"));
        jLabel7.setText(idioma.getProperty("Pais"));

        tablaEstado.getColumnModel().getColumn(0).setHeaderValue(idioma.getProperty("Estado"));
        tablaEstado.getColumnModel().getColumn(1).setHeaderValue(idioma.getProperty("Pais"));

        comboSituacion.addItem(idioma.getProperty("Activos"));
        comboSituacion.addItem(idioma.getProperty("Inactivos"));
        comboSituacion.addItem(idioma.getProperty("Todos"));

        llenaTablaEstado();
    }

    public void llenaTablaEstado() {
        limpiar(tablaEstado);
        String sql = "select e.descripcion, p.descripcion from estado e inner join pais p on (e.ID_Pais=p.ID) where e.ID_Situacion=1";
        mdb.cargarInformacion2(modelo, 2, sql);
    }

    public void busquedaEstado() {
        String tipoP = "";
        String tipoE = "";
        String situacion;
        situacion = comboSituacion.getSelectedIndex() + "";

        if (situacion.equals("1")) {
            situacion = "2";
        } else if (situacion.equals("0")) {
            situacion = "1";
        } else {
            situacion = "3";
        }

        if (txtEstado.getText().length() > 0) {
            tipoE = " AND e.descripcion like '" + txtEstado.getText() + "%'";
        }
        if (txtPais.getText().length() > 0) {
            tipoP = " AND p.descripcion like '" + txtPais.getText() + "%'";
        }

        String sql;
        if (situacion.equals("3")) {
            sql = "select e.descripcion, p.descripcion from estado e inner join pais p on (e.ID_Pais=p.ID) where e.ID_Situacion <> 3" + tipoE + tipoP;
        } else {
            sql = "select e.descripcion, p.descripcion from estado e inner join pais p on (e.ID_Pais=p.ID) where e.ID_Situacion=" + situacion + tipoE + tipoP;
        }
        limpiar(tablaEstado);
        mdb.cargarInformacion2(modelo, 2, sql);
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
        txtEstado = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEstado = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboSituacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1010, 700));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel6.setText("Estado");

        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstadoKeyReleased(evt);
            }
        });

        txtPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaisKeyReleased(evt);
            }
        });

        jLabel7.setText("Pais");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estado", "País"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEstadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaEstado);
        if (tablaEstado.getColumnModel().getColumnCount() > 0) {
            tablaEstado.getColumnModel().getColumn(0).setResizable(false);
            tablaEstado.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel10.setText("Situacion");

        comboSituacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSituacionItemStateChanged(evt);
            }
        });
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

        jButton1.setText("Cerrar");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
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
                    .addComponent(jButton1))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
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
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jdE = new jdEstado(null, true, "1", estado, "", Idioma, cn);
        jdE.jpE = this;
        jdE.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    String estado = "", pais = "";
    private void tablaEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEstadoMouseClicked
        // TODO add your handling code here:
        estado = modelo.getValueAt(tablaEstado.getSelectedRow(), 0) + "";  //Estado String
        pais = modelo.getValueAt(tablaEstado.getSelectedRow(), 1) + "";

        if (evt.getClickCount() == 1) {
            // System.out.println("1 Clic");
        }
        if (evt.getClickCount() == 2) {
            jdE = new jdEstado(null, true, "2", estado, pais, Idioma, cn);
            jdE.jpE = this;
            jdE.setVisible(true);
        }
    }//GEN-LAST:event_tablaEstadoMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (estado.equals("")) {
            JOptionPane.showMessageDialog(null, idioma.getProperty("SeleccionRegistro"));
        } else {
            jdE = new jdEstado(null, true, "2", estado, pais, Idioma, cn);
            jdE.jpE = this;
            jdE.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyReleased
        // TODO add your handling code here:
        busquedaEstado();
    }//GEN-LAST:event_txtEstadoKeyReleased

    private void txtPaisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaisKeyReleased
        // TODO add your handling code here:
        busquedaEstado();
    }//GEN-LAST:event_txtPaisKeyReleased

    private void comboSituacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSituacionItemStateChanged
        // TODO add your handling code here:
        busquedaEstado();
    }//GEN-LAST:event_comboSituacionItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (comboSituacion.getSelectedIndex() == 0) {
            mdb.actualizarBasicos("UPDATE estado SET ID_Situacion=2 where descripcion='" + estado + "'");
        } else if (comboSituacion.getSelectedIndex() == 1) {
            mdb.actualizarBasicos("UPDATE estado SET ID_Situacion=1 where descripcion='" + estado + "'");
        }
        busquedaEstado();
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
        busquedaEstado();
    }//GEN-LAST:event_comboSituacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboSituacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEstado;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtPais;
    // End of variables declaration//GEN-END:variables
}
