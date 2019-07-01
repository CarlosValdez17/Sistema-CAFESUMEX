/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_FincaCert;

import Formas_Personas.jdFormularioPersonas;
import Formas_Personas.jdFormularioParcelas;
import Idioma.Propiedades;
//import Metodos_Configuraciones.JComboCheckBox;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.awt.Checkbox;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.control.CheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class jpProductores extends javax.swing.JPanel {

    /**
     * Creates new form jpPersonas2
     */
    Connection cn;
    metodosDatosBasicos mdb;
    DefaultTableModel modelo, modelo2;
    jdFormularioProductor jdFP;
    jdFormularioPersonas jdDP;
    String Idioma;
    ArrayList<String> array = new ArrayList<String>();
    Propiedades idioma;

    public jpProductores(Connection c, String Idioma) {
        initComponents();
        //tablaPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //new JScrollPane(tablaPersonas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cn = c;
        this.Idioma = Idioma;
        mdb = new metodosDatosBasicos(cn);
        idioma = new Propiedades(Idioma);
        modelo = (DefaultTableModel) tablaProductores.getModel();
        traductor();
        //  AutoCompleteDecorator.decorate(comboPersona);

        // llenarTabla();
        tablaProductores.setRowSorter(new TableRowSorter(modelo));
        comboGenero.setEnabled(false);
        comboBusqueda.setEnabled(false);
        txtBusqueda.setEnabled(false);

        //JOptionPane.showMessageDialog(null, "Prueba para push maquina nueva");
    }

    public void traductor() {
        jLabel1.setText(idioma.getProperty("Persona"));
        jLabel2.setText(idioma.getProperty("Genero"));
        jLabel4.setText(idioma.getProperty("BuscarPor"));
        jLabel3.setText(idioma.getProperty("Busqueda"));

        comboSituacion.addItem(idioma.getProperty("Activos"));
        comboSituacion.addItem(idioma.getProperty("Inactivos"));
        comboSituacion.addItem(idioma.getProperty("Todos"));

        comboGenero.addItem(idioma.getProperty("Todos"));
        comboGenero.addItem(idioma.getProperty("Masculino"));
        comboGenero.addItem(idioma.getProperty("Femenino"));

        comboPersona.addItem(idioma.getProperty("Todos"));
        comboPersona.addItem(idioma.getProperty("PersonaFisica"));
        comboPersona.addItem(idioma.getProperty("PersonaMoral"));

        tablaProductores.getColumnModel().getColumn(0).setHeaderValue(idioma.getProperty("RazonSocial"));
        tablaProductores.getColumnModel().getColumn(1).setHeaderValue(idioma.getProperty("Nombre"));
        tablaProductores.getColumnModel().getColumn(2).setHeaderValue(idioma.getProperty("ApellidoPaterno"));
        tablaProductores.getColumnModel().getColumn(3).setHeaderValue(idioma.getProperty("ApellidoMaterno"));
        tablaProductores.getColumnModel().getColumn(4).setHeaderValue(idioma.getProperty("ClaveProductor"));

        jButton1.setText(idioma.getProperty("Cerrar"));
        jButton2.setText(idioma.getProperty("Nuevo"));
        jButton3.setText(idioma.getProperty("Editar"));
        jButton4.setText(idioma.getProperty("Desactivar"));
    }

    public void llenarTabla() {

        limpiar(tablaProductores);
        String where = "", situacion = "";

        switch (comboSituacion.getSelectedIndex()) {
            case 0:
                situacion = "=1";
                break;

            case 1:
                situacion = "=2";
                break;

            case 2:
                situacion = "<>3";
                break;
        }
        switch (comboPersona.getSelectedIndex()) {
            //Todos 
            case 0:
                mdb.cargarInformacion2(modelo, 5,
                        "select '' as rz, pf.Nombre, pf.ApellidoPaterno, pf.ApellidoMaterno, pr.clave_productor\n"
                        + "from productor pr\n"
                        + "inner join personaf pf on(pr.id_persona=pf.ID) where pr.id_situacion" + situacion + " and tipoPersona=1");
                mdb.cargarInformacion2(modelo, 5, "select pm.razonsocial, '' as nombre, '' as appat, '' as apmat, pr.clave_productor\n"
                        + "from productor pr\n"
                        + "inner join personam pm on(pr.id_persona=pm.ID) where pr.id_situacion" + situacion + " and tipoPersona=2");
                break;

            //Fisica
            case 1:

                switch (comboGenero.getSelectedIndex()) {
                    //Todos masculino y femenino
                    case 0:
                        where += " and pf.id_genero<>3";
                        break;

                    //Masculino
                    case 1:
                        where += " and pf.id_genero=1";
                        break;

                    //Femenino
                    case 2:
                        where += " and pf.id_genero=2";
                        break;

                }

                switch (comboBusqueda.getSelectedIndex()) {
                    //Select
                    case 0:

                        break;

                    //Nombre
                    case 1:
                        where += " and pf.nombre like '" + txtBusqueda.getText() + "%' ";
                        break;

                    //Ap Pat
                    case 2:
                        where += " and pf.apellidopaterno like '" + txtBusqueda.getText() + "%' ";
                        break;

                    //Ap Mat
                    case 3:
                        where += " and pf.apellidomaterno like '" + txtBusqueda.getText() + "%' ";
                        break;

                    //Clave Prod
                    case 4:
                        where += " and pr.clave_productor like '" + txtBusqueda.getText() + "%' ";
                        break;
                }

                mdb.cargarInformacion2(modelo, 5,
                        "select '' as rz, pf.Nombre, pf.ApellidoPaterno, pf.ApellidoMaterno, pr.clave_productor\n"
                        + "from productor pr\n"
                        + "inner join personaf pf on(pr.id_persona=pf.ID) where pr.id_situacion" + situacion + " and tipoPersona=1" + where);

                break;

            //Moral
            case 2:

                switch (comboBusqueda.getSelectedIndex()) {
                    //Select
                    case 0:

                        break;

                    //Razon Social
                    case 1:
                        where += " and pm.razonsocial like '" + txtBusqueda.getText() + "%' ";
                        break;

                    //Clave Prod
                    case 2:
                        where += " and pr.clave_productor like '" + txtBusqueda.getText() + "%' ";
                        break;
                }

                mdb.cargarInformacion2(modelo, 5, "select pm.razonsocial, '' as nombre, '' as appat, '' as apmat, pr.clave_productor\n"
                        + "from productor pr\n"
                        + "inner join personam pm on(pr.id_persona=pm.ID) where pr.id_situacion" + situacion + " and tipoPersona=2" + where);
                break;

        }

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
        jLabel1 = new javax.swing.JLabel();
        comboPersona = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboBusqueda = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboSituacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductores = new javax.swing.JTable();

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel1.setText("Persona");

        comboPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPersonaActionPerformed(evt);
            }
        });

        jLabel2.setText("Genero");

        comboGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGeneroActionPerformed(evt);
            }
        });

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel3.setText("Busqueda");

        comboBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", "Nombre", "Apellido Paterno", "Apellido Materno", "Clave Productor", "Numero De Parcelas" }));
        comboBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBusquedaActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar por..");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(136, 136, 136)
                        .addComponent(jLabel4)
                        .addGap(105, 105, 105)
                        .addComponent(jLabel3)))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        tablaProductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Razon Social", "Nombre", "Apellido Paterno", "Apellido Materno", "Clave Productor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductores);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
String estatus = "2";
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //TODO add your handling code here:
        jdFormularioPersonas jpP = new jdFormularioPersonas(null, true, "1", "", "", Idioma, cn);
        jpP.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!nom.equals("")) {
            jdFP = new jdFormularioProductor(null, true, id, nom + " " + app + " " + apm, "1", "SI", Idioma, cn);
            jdFP.jpP = this;
            jdFP.setVisible(true);
        } else if (nom.equals("") && !rsocial.equals("")) {
            jdFP = new jdFormularioProductor(null, true, id, rsocial, "2", "SI",Idioma, cn);
            jdFP.setVisible(true);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (comboSituacion.getSelectedIndex() == 0) {
            mdb.actualizarBasicos("update productor set id_situacion=2 where id_persona=" + id + " and tipoPersona=" + tipoPersona + " ");

        } else if (comboSituacion.getSelectedIndex() == 1) {
            mdb.actualizarBasicos("update productor set id_situacion=1 where id_persona=" + id + " and tipoPersona=" + tipoPersona + " ");
        }
        llenarTabla();
    }//GEN-LAST:event_jButton4ActionPerformed
    String nom = "", app = "", apm = "", id = "", rsocial = "";

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        llenarTabla();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void comboGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGeneroActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_comboGeneroActionPerformed

    private void comboBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBusquedaActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_comboBusquedaActionPerformed
    String tipoPersona = "";
    private void tablaProductoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoresMouseClicked
        // TODO add your handling code here:
        nom = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 1) + "";
        app = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 2) + "";
        apm = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 3) + "";
        rsocial = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 0) + "";

        if (!nom.equals("")) {
            //Abrir detalle para persona fisica = 1
            id = mdb.comprobarExistencia("select id from personaf "
                    + "where (nombre='" + nom + "' and apellidoPaterno='" + app + "' and apellidoMaterno='" + apm + "' ) ");
            tipoPersona = "1";

        } else if (nom.equals("") && !rsocial.equals("")) {
            //Abrir detalle para persona moral = 2
            id = mdb.comprobarExistencia("select id from personam "
                    + "where razonsocial='" + rsocial + "'");
            tipoPersona = "2";

        }

        if (evt.getClickCount() == 2) {
            if (id.equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione Productor");
            } else {
                jdFormularioParcelas jdF = new jdFormularioParcelas(null, true, id, tipoPersona, "", Idioma, cn);
                jdF.setVisible(true);
            }
        }

    }//GEN-LAST:event_tablaProductoresMouseClicked

    private void comboPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPersonaActionPerformed
        // TODO add your handling code here:
        switch (comboPersona.getSelectedIndex()) {
            case 1:
                comboGenero.setEnabled(true);
                comboBusqueda.setEnabled(true);
                txtBusqueda.setEnabled(true);
                comboBusqueda.removeAllItems();
                comboBusqueda.addItem(idioma.getProperty("Seleccione"));
                comboBusqueda.addItem(idioma.getProperty("Nombre"));
                comboBusqueda.addItem(idioma.getProperty("ApellidoPaterno"));
                comboBusqueda.addItem(idioma.getProperty("ApellidoMaterno"));
                comboBusqueda.addItem(idioma.getProperty("ClaveProductor"));
                break;
            case 2:
                comboGenero.setEnabled(false);
                comboBusqueda.removeAllItems();
                comboBusqueda.addItem("Seleccione..");
                comboBusqueda.addItem("Razon Social");
                comboBusqueda.addItem("Clave Productor");
                break;
            default:
                comboGenero.setEnabled(false);
                comboBusqueda.setEnabled(false);
                txtBusqueda.setEnabled(false);
                break;
        }
        llenarTabla();
    }//GEN-LAST:event_comboPersonaActionPerformed

    private void comboSituacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSituacionActionPerformed
        // TODO add your handling code here:
        switch (comboSituacion.getSelectedIndex()) {
            case 0:
                jButton4.setText(idioma.getProperty("Desactivar"));
                jButton4.setEnabled(true);
                break;
            case 1:
                jButton4.setText(idioma.getProperty("Activar"));
                jButton4.setEnabled(true);
                break;
            default:
                jButton4.setEnabled(false);
                break;
        }

        llenarTabla();
    }//GEN-LAST:event_comboSituacionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBusqueda;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JComboBox<String> comboPersona;
    private javax.swing.JComboBox<String> comboSituacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductores;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
