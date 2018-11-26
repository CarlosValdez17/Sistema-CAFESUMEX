/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import MetodosGenerales.JComboCheckBox;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Carlos Valdez
 */
public class jpPersonas1 extends javax.swing.JPanel {

    /**
     * Creates new form jpPersonas2
     */
    Connection cn;
    metodosDatosBasicos mdb;
    DefaultTableModel modelo, modelo2;
    jdDetallePersona1 jdDP;
    ArrayList<String> array = new ArrayList<String>();

    public jpPersonas1(Connection c) {
        initComponents();
        //tablaPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //new JScrollPane(tablaPersonas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cn = c;
        mdb = new metodosDatosBasicos(cn);
        modelo = (DefaultTableModel) tablaPersonas.getModel();
        //combo();
        //  AutoCompleteDecorator.decorate(comboPersona);

        llenarTabla();

    }

    /*    public void combo() {
        String[] datos = mdb.cargarCombos("SELECT descripcion from puestos").split("#");
        comboPuestos.setModel(new DefaultComboBoxModel((Object[]) datos));
    }*/
    //✘ ✓
    public void llenarTabla() {
        array.clear();
        String tipo = comboPersona.getSelectedItem() + "";
        if (tipo.equals("Moral")) {
            comboGenero.setEnabled(false);
            //comboPuestos.setEnabled(false);
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 15, "select nombre, ApellidoPaterno, ApellidoMaterno, RazonSocial, socio, productor, Direccion, CodigoPostal, Telefono, telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from persona x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID)"
                    + "where x.ID_TipoPersona=2", array);
            System.out.println(array);

        } else if (tipo.equals("Fisica")) {
            comboGenero.setEnabled(true);
            //comboPuestos.setEnabled(true);
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 15, "select nombre, ApellidoPaterno, ApellidoMaterno, RazonSocial, socio, productor, Direccion, CodigoPostal, Telefono, telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from persona x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID)"
                    + "where x.ID_TipoPersona=1", array);

        } else {
            comboGenero.setEnabled(true);
            //comboPuestos.setEnabled(true);
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 15, "select nombre, ApellidoPaterno, ApellidoMaterno, RazonSocial, socio, productor, Direccion, CodigoPostal, Telefono,telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from persona x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID)", array);
            //+ "where p.ID_TipoPersona=1", array);

        }

        for (int i = 0; i < modelo.getRowCount(); i++) {
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
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboSituacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();
        panelRadios = new javax.swing.JPanel();

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel1.setText("Persona");

        comboPersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Fisica", "Moral" }));
        comboPersona.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonaItemStateChanged(evt);
            }
        });
        comboPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPersonaActionPerformed(evt);
            }
        });

        jLabel2.setText("Genero");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Masculino", "Femenino" }));
        comboGenero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGeneroItemStateChanged(evt);
            }
        });

        jLabel3.setText("Busqueda");

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
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
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

        tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Razon Social", "Socio", "Productor", "Direccion", "Codigo Postal", "Telefono Fijo", "Telefono Movil", "Pais", "Estado", "Municipio", "Localidad", "Colonia", "Sociedades"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPersonasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPersonas);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(23, 23, 23)
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
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelRadios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRadios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    String id, persona = "";
    private void comboSituacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSituacionItemStateChanged
        // TODO add your handling code here:
        // busqueda();
    }//GEN-LAST:event_comboSituacionItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jdDP = new jdDetallePersona1(null, true, "2", cn);
        jdDP.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /*      if (actividad.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        } else {
            jdABH = new jdActividadesBH(null, true, "2", actividad,desc, cn);
            jdABH.jpABH = this;
            jdABH.setVisible(true);
        }*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        /*   String sql = "UPDATE actividadesbh SET ID_Situacion=2 where actividad='" + actividad + "'";
        mdb.actualizarBasicos(sql);
        llenaTabla();*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPersonaActionPerformed

    private void comboPersonaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonaItemStateChanged
        // TODO add your handling code here:
        if (comboPersona.getSelectedItem().equals("Moral")) {
            JTableHeader tableHeader = tablaPersonas.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Razon Social");
            tableHeader.repaint();
        } else {
            JTableHeader tableHeader = tablaPersonas.getTableHeader();
            TableColumnModel tableColumnModel = tableHeader.getColumnModel();
            TableColumn tableColumn = tableColumnModel.getColumn(0);
            tableColumn.setHeaderValue("Nombre");
            tableHeader.repaint();
        }
        llenarTabla();
    }//GEN-LAST:event_comboPersonaItemStateChanged

    private void comboGeneroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGeneroItemStateChanged
        // TODO add your handling code here:

        String tipo = comboGenero.getSelectedItem() + "";
        if (tipo.equals("Masculino")) {
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 4, "select  CONCAT(p.Nombre,\" \",p.ApellidoPaterno,\" \",p.ApellidoMaterno) AS Nombre, p.productor, p.socio, p.id\n"
                    + "from persona p\n "
                    + "inner join genero g on (p.ID_Genero=g.ID)\n "
                    + "where g.descripcion='Masculino' and p.id_tipopersona=1 ", array);
        } else if (tipo.equals("Femenino")) {
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 4, "select  CONCAT(p.Nombre,\" \",p.ApellidoPaterno,\" \",p.ApellidoMaterno) AS Nombre, p.productor, p.socio, p.id\n"
                    + "from persona p\n "
                    + "inner join genero g on (p.ID_Genero=g.ID)\n "
                    + "where g.descripcion='Femenino' and p.id_tipopersona=1", array);
        } else {
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 4, "select  CONCAT(p.Nombre,\" \",p.ApellidoPaterno,\" \",p.ApellidoMaterno) AS Nombre, p.productor, p.socio, p.id\n"
                    + "from persona p\n "
                    + "inner join genero g on (p.ID_Genero=g.ID)\n "
                    + "where p.ID_TipoPersona=1", array);

        }

        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 1).equals("1")) {
                modelo.setValueAt("✓", i, 1);
            } else {
                modelo.setValueAt("✘", i, 1);
            }

            if (modelo.getValueAt(i, 2).equals("1")) {
                modelo.setValueAt("✓", i, 2);
            } else {
                modelo.setValueAt("✘", i, 2);
            }
        }


    }//GEN-LAST:event_comboGeneroItemStateChanged

    private void tablaPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonasMouseClicked
        // TODO add your handling code here:
        //actividad = modelo.getValueAt(tablaActividades.getSelectedRow(), 0) + "";
        //desc = modelo.getValueAt(tablaActividades.getSelectedRow(), 1) + "";

        if (evt.getClickCount() == 1) {
            // System.out.println("1 Clic");
        }
        if (evt.getClickCount() == 2) {
            jdDP = new jdDetallePersona1(null, true, "1", cn);
            //jdDP.jpABH = this;
            jdDP.setVisible(true);
        }
    }//GEN-LAST:event_tablaPersonasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelRadios;
    private javax.swing.JTable tablaPersonas;
    // End of variables declaration//GEN-END:variables
}
