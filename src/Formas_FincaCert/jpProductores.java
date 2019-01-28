/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_FincaCert;

import Formas_Personas.jdDetallePersona1;
import Formas_Personas.jdFormularioParcelas;
import Formas_Personas.jdFormularioProductor;
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
    jdDetallePersona1 jdDP;
    ArrayList<String> array = new ArrayList<String>();

    public jpProductores(Connection c) {
        initComponents();
        //tablaPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //new JScrollPane(tablaPersonas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cn = c;
        mdb = new metodosDatosBasicos(cn);
        modelo = (DefaultTableModel) tablaProductores.getModel();
        //  AutoCompleteDecorator.decorate(comboPersona);

        llenarTabla();
        tablaProductores.setRowSorter(new TableRowSorter(modelo));
        txtBusqueda.setEnabled(false);
        comboGenero.setEnabled(true);
        
        JOptionPane.showMessageDialog(null, "Prueba para push maquina nueva");
    }

    //✘ ✓
    public void llenarTabla() {
        limpiar(tablaProductores);
        String situacion = "";
        String tipoPer = comboPersona.getSelectedItem() + "";
        String tipoGen = comboGenero.getSelectedItem() + "";
        String tipoBusqueda = "";
        String Busqueda = "";
        String where = "";
        situacion = comboSituacion.getSelectedItem() + "";

        if (situacion.equals("Inactivo")) {
            situacion = "2";
        } else if (situacion.equals("Activo")) {
            situacion = "1";
        }
        if (tipoGen.equals("Todos")) {
            tipoGen = "";
        }
        if (tipoGen.equals("Masculino")) {
            tipoGen = " And genero='masculino'";
        } else if (tipoGen.equals("Femenino")) {
            tipoGen = " And genero='femenino'";
        }
        if (tipoPer.equals("Todos")) {
            tipoPer = "";
        }
        if (tipoPer.equals("Fisica")) {
            tipoPer = " and tipopersona=\"fisica\"";
        } else if (tipoPer.equals("Moral")) {
            tipoPer = " and tipopersona=\"moral\"";

        }
        if (txtBusqueda.getText().equals("")) {

        } else if (comboBusqueda.getSelectedItem().equals("Seleccione..")) {
            tipoBusqueda = "";
        } else {
            String p = comboBusqueda.getSelectedItem() + "";
            switch (p) {
                case "Nombre":
                    tipoBusqueda = " and nombre like ";
                    break;
                case "Apellido Paterno":
                    tipoBusqueda = " and apellidop like ";
                    break;
                case "Apellido Materno":
                    tipoBusqueda = " and apellidom like ";
                    break;
                case "Clave Productor":
                    tipoBusqueda = " and claveP like ";
                    break;
                case "Numero De Parcelas":
                    tipoBusqueda = " and numparcelas like ";
                    break;
            }
            if (txtBusqueda.getText().length() > 0) {
                Busqueda = "'" + txtBusqueda.getText() + "%'";
            }
        }

        String sql;
        if (situacion.equals("Todos")) {
            sql = "select '' as rz, pf.Nombre, pf.ApellidoPaterno, pf.ApellidoMaterno, pr.clave_productor\n"
                    + "from productor pr\n"
                    + "inner join personaf pf on(pr.id_persona=pf.ID) where tipoPersona=1" + tipoPer + tipoGen + tipoBusqueda + Busqueda;
            mdb.cargarInformacion2(modelo, 5, sql);

            sql = "select pm.razonsocial, '' as nombre, '' as appat, '' as apmat, pr.clave_productor\n"
                    + "from productor pr\n"
                    + "inner join personam pm on(pr.id_persona=pm.ID) where tipoPersona=2" + tipoPer + tipoGen + tipoBusqueda + Busqueda;
            mdb.cargarInformacion2(modelo, 5, sql);
        } else {
            //  sql = "select pf.Nombre, pf.ApellidoPaterno, pf.ApellidoMaterno, pr.clave_productor\n"
            //          + "from productor pr\n"
            //          + "inner join personaf pf on(pr.id_persona=pf.ID)";// + situacion + tipoPer + tipoGen + tipoBusqueda + Busqueda;

            sql = "select '' as rz, pf.Nombre, pf.ApellidoPaterno, pf.ApellidoMaterno, pr.clave_productor\n"
                    + "from productor pr\n"
                    + "inner join personaf pf on(pr.id_persona=pf.ID)where tipoPersona=1" + tipoPer + tipoGen + tipoBusqueda + Busqueda;
            mdb.cargarInformacion2(modelo, 5, sql);

            sql = "select pm.razonsocial, '' as nombre, '' as apellido, '' as apmat, pr.clave_productor\n"
                    + "from productor pr\n"
                    + "inner join personam pm on(pr.id_persona=pm.ID) where tipoPersona=2" + tipoPer + tipoGen + tipoBusqueda + Busqueda;
            mdb.cargarInformacion2(modelo, 5, sql);
        }
        System.out.println(sql);
        //limpiar(tablaProductores);
        // mdb.cargarInformacion2(modelo, 4, sql);
    }

    /* for (int i = 0;
                i < modelo.getRowCount();
                i++) {
            if (modelo.getValueAt(i, 4).equals("1")) {
                modelo.setValueAt("✓", i, 4);
            } else {
                modelo.setValueAt("✘", i, 4);
            }

            if (modelo.getValueAt(i, 5).equals("1")) {
                modelo.setValueAt("✓", i, 5);
            } else {
                modelo.setValueAt("✘", i, 5);
            }
        }*/
    private void activarB() {
        if (comboBusqueda.getSelectedItem().equals("Seleccione..")) {
            txtBusqueda.setEnabled(false);
        } else {
            txtBusqueda.setEnabled(true);
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
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductores = new javax.swing.JTable();

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel1.setText("Persona");

        comboPersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Fisica", "Moral" }));
        comboPersona.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonaItemStateChanged(evt);
            }
        });

        jLabel2.setText("Genero");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Masculino", "Femenino" }));
        comboGenero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGeneroItemStateChanged(evt);
            }
        });
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
        comboBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBusquedaItemStateChanged(evt);
            }
        });
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

        comboSituacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo", "Todos" }));
        comboSituacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSituacionItemStateChanged(evt);
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

        jButton5.setText("Ver Parcelas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Ver Parcelas 2");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(110, 110, 110)
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
                    .addComponent(jButton1)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
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
    private void comboSituacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSituacionItemStateChanged
        if (comboSituacion.getSelectedItem().equals("Inactivo")) {
            estatus = "1";
            jButton4.setText("Activar");
        } else {
            jButton4.setText("Desactivar");
            estatus = "2";
        }  // TODO add your handling code here:
        // TODO add your handling code here:
        // busqueda();
        llenarTabla();
    }//GEN-LAST:event_comboSituacionItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //TODO add your handling code here:
        jdDetallePersona1 jpP = new jdDetallePersona1(null, true, "1", "", "", cn);
        jpP.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /*  if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        } else {
            jdDP = new jdDetallePersona1(null, true, "2", id, "", cn);
            //jdDP.jpABH = this;
            jdDP.setVisible(true);
        }**/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String sql = "";
        /*
        if (estatus.equals("2")) {
             sql = "UPDATE retenciones SET ID_Situacion=2 where descripcion='" + Retenciones + "'";
       
        }else if(estatus.equals("1")){
             sql = "UPDATE retenciones SET ID_Situacion=1 where descripcion='" + Retenciones + "'";
        }
        
     mdb.actualizarBasicos(sql);
            llenarTabla();*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboPersonaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonaItemStateChanged
        llenarTabla();
    }//GEN-LAST:event_comboPersonaItemStateChanged

    private void comboGeneroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGeneroItemStateChanged
        llenarTabla();
    }//GEN-LAST:event_comboGeneroItemStateChanged
    String nom = "", app = "", apm = "", id="", rsocial = "";
    String tipoGenero = "", tipoBusqueda = "", tipoAsignacion = "", tipoCPersona = "";
    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        llenarTabla();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void comboBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBusquedaItemStateChanged
        activarB();
    }//GEN-LAST:event_comboBusquedaItemStateChanged

    private void comboGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGeneroActionPerformed

    private void comboBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBusquedaActionPerformed
    String tipoPersona;
    private void tablaProductoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoresMouseClicked
        // TODO add your handling code here:
        nom = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 1) + "";
        app = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 2) + "";
        apm = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 3) + "";
        rsocial = tablaProductores.getValueAt(tablaProductores.getSelectedRow(), 0) + "";

        if (evt.getClickCount() == 2) {
            if (!nom.equals("")) {
                //Para abrir detalle de persona fisica = 1   /      Persona Fisica = 1
                id = mdb.comprobarExistencia("select id from personaf "
                        + "where (nombre='" + nom + "' and apellidoPaterno='" + app + "' and apellidoMaterno='" + apm + "' ) ");
                tipoPersona = "Fisica";
                jdParcelas jd = new jdParcelas(null, true, cn, id, tipoPersona);
                jd.setVisible(true);

            } else if (nom.equals("") && !rsocial.equals("")) {
                //Abrir detalle para persona moral = 2    /    Persona Moral = 2

                id = mdb.comprobarExistencia("select id from personam "
                        + "where razonsocial='" + rsocial + "'");
                tipoPersona = "Moral";
                jdParcelas jd = new jdParcelas(null, true, cn, id, tipoPersona);
                jd.setVisible(true);
            }
        } else {
            if (!nom.equals("")) {
                //Abrir detalle para persona fisica = 1
                id = mdb.comprobarExistencia("select id from personaf "
                        + "where (nombre='" + nom + "' and apellidoPaterno='" + app + "' and apellidoMaterno='" + apm + "' ) ");
                tipoPersona = "Fisica";
            } else if (nom.equals("") && !rsocial.equals("")) {
                //Abrir detalle para persona moral = 2
                id = mdb.comprobarExistencia("select id from personam "
                        + "where razonsocial='" + rsocial + "'");
                tipoPersona = "Moral";
            }
        }
    }//GEN-LAST:event_tablaProductoresMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (nom.equals("") && rsocial.equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona productor");
        } else {

            if (tipoPersona.equals("1")) {
                tipoPersona = "Fisica";
            } else if (tipoPersona.equals("2")) {
                tipoPersona = "Moral";
            }
            jdParcelas jd = new jdParcelas(null, true, cn, id, tipoPersona);
            jd.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (id.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione Productor");
        } else {

            if (tipoPersona.equals("Fisica")) {
                tipoPersona = "1";
            } else if (tipoPersona.equals("Moral")) {
                tipoPersona = "2";
            }
            
            jdFormularioParcelas jdF = new jdFormularioParcelas(null, true, id, tipoPersona, "", cn);
            jdF.setVisible(true);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBusqueda;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JComboBox<String> comboPersona;
    private javax.swing.JComboBox<String> comboSituacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
