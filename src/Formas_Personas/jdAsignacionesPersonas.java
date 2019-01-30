/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import Formas_Configuraciones_Recepcion.*;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Valdez
 */
public class jdAsignacionesPersonas extends javax.swing.JDialog {

    /**
     * Creates new form jdFormaProceso
     */
    Connection cn;
    String tipoPersona, nombrePersona, idPersona;
    metodosDatosBasicos mdb;
    jpEvaluaciones jpE;
    jdSociedadesPersonas formSoc;
    jdFormularioProductor jdFP;

    public jdAsignacionesPersonas(java.awt.Frame parent, boolean modal, String tipoPersona, String nombrePersona, String idPersona, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cn = c;
        this.idPersona = idPersona;
        //tipoPersona es el tipo de persona
        this.tipoPersona = tipoPersona;
        this.nombrePersona = nombrePersona;
        mdb = new metodosDatosBasicos(cn);

        llenarTabla();
        //llenarCombo();
        labelPersona.setText(nombrePersona);
        setTitle("Asignaciones");

        rellenar();
        /*if (tipo.equals("2")) {
            rellenar();
            comboFormas.setEnabled(false);
            comboFormas.addItem(valor);
            comboFormas.setSelectedItem(valor);
            setTitle("Editar Forma - Evaluacion");
        }*/
    }

    public void llenarTabla() {
        limpiar(tablaAsignaciones);
        DefaultTableModel modelo = (DefaultTableModel) tablaAsignaciones.getModel();
        mdb.cargarInformacion2(modelo, 1, "select descripcion from puestos order by descripcion asc");
    }

    public void rellenar() {

        DefaultTableModel modelo = (DefaultTableModel) tablaAsignaciones.getModel();
        String[] datos = mdb.generadorStrings("select p.descripcion \n"
                + "from asignacionespersona a\n "
                + "inner join puestos p on (a.ID_puesto=p.ID)\n"
                + "where a.id_persona= " + idPersona + " order by p.descripcion asc").split("#");
        /*System.out.println(mdb.cargarCombos("select p.descripcion \n"
                + "from asignacionespersona a\n "
                + "inner join puestos p on (a.ID_puesto=p.ID)\n"
                + "where a.id_persona= " + idPersona ));*/
        int e = 0;
        System.out.println("Tamaño datos= " + datos.length);
        for (int i = 0; i < modelo.getRowCount(); i++) {
            //System.out.println("i ="+i+" --- "+" e ="+e);
            if (e == datos.length) {
                break;
            } else {
                if (modelo.getValueAt(i, 0).equals(datos[e])) {
                    //System.out.println("Vuelta # "+e+ "-- Dato BD= "+datos[e]+ " = "+modelo.getValueAt(i,0));
                    modelo.setValueAt(true, i, 1);
                    e = e + 1;
                }
            }
        }
    }

   /* public void abrirFormulario(String formulario) {
        //JOptionPane.showMessageDialog(null, "Entre al case");
        switch (formulario) {
            case "Productor":
                jdFP = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, cn);
                jdFP.setVisible(true);
                break;
            case "Socio":
                formSoc = new jdSociedadesPersonas(null, true, "1", tipoPersona, nombrePersona, idPersona, cn);
                formSoc.setVisible(true);
                break;
        }
    }*/

    public void editarDetalles(String formulario, String item) {
        switch (formulario) {
            case "Productor":
                switch (item) {
                    case "Detalles Asignacion":

                        if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='"+tipoPersona+"' ") == null
                                || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='"+tipoPersona+"'").equals("")) {
                            jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona,"NO", cn);
                            jdFPr.setVisible(true);
                        } else {
                            jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "SI", cn);
                            jdFPr.setVisible(true);
                        }

                        break;

                    case "Parcelas":

                        if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='"+tipoPersona+"'") == null
                                || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='"+tipoPersona+"'").equals("")) {
                            jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona,"NO", cn);
                            jdFPr.setVisible(true);
                        } else {
                            jdFormularioParcelas jdFP = new jdFormularioParcelas(null, true, idPersona, tipoPersona, "", cn);
                            jdFP.setVisible(true);
                        }

                        break;
                }
                /*if (tipoPersona.equals("1")) {
                    if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona+ " and tipoPersona=1") == null
                            || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona+ " and tipoPersona=1").equals("")) {
                        jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, cn);
                        jdFPr.setVisible(true);
                    } else {
                        jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, cn);
                        jdFPr.setVisible(true);
                        //jdFormularioParcelas jdFP = new jdFormularioParcelas(null, true, idPersona, tipoPersona,"", cn);
                        //jdFP.setVisible(true);
                    }
                }else{
                
                    if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona+ " and tipoPersona=2") == null
                            || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona+ " and tipoPersona=2").equals("")) {
                        jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, cn);
                        jdFPr.setVisible(true);
                    } else {
                        jdFormularioParcelas jdFP = new jdFormularioParcelas(null, true, idPersona, tipoPersona,"", cn);
                        jdFP.setVisible(true);
                    }
                }*/
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAsignaciones = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelPersona = new javax.swing.JLabel();

        jMenuItem1.setText("Detalles Asignacion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Parcelas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Asignacion", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAsignaciones.setComponentPopupMenu(jPopupMenu1);
        tablaAsignaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsignacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaAsignacionesMouseEntered(evt);
            }
        });
        tablaAsignaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaAsignacionesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAsignaciones);
        if (tablaAsignaciones.getColumnModel().getColumnCount() > 0) {
            tablaAsignaciones.getColumnModel().getColumn(0).setResizable(false);
            tablaAsignaciones.getColumnModel().getColumn(1).setMinWidth(60);
            tablaAsignaciones.getColumnModel().getColumn(1).setPreferredWidth(60);
            tablaAsignaciones.getColumnModel().getColumn(1).setMaxWidth(60);
        }

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Asignaciones de:");

        labelPersona.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(labelPersona))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
/*        DefaultTableModel modelo = (DefaultTableModel) tablaAsignaciones.getModel();
        String idForma = mdb.devuelveId("select id from formacafe where descripcion='" + comboFormas.getSelectedItem() + "" + "'");

        if (tipo.equals("1")) {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String desc = modelo.getValueAt(i, 0) + "";
                String valor = modelo.getValueAt(i, 1) + "";

                if (valor.equals("true")) {
                    String idEvaluacion = mdb.devuelveId("select id from tipoevaluacion where descripcion='" + desc + "'");
                    String sql = "INSERT INTO formaevaluacion VALUES(null,'" + idForma + "','" + idEvaluacion + "', 1, 1,current_date()"
                            + ", current_time(),1,1,1,1)";
                    System.out.println(sql);
                    mdb.insertarEnCiclo(sql);
                }
            }
            JOptionPane.showMessageDialog(null, "Inserción Exitosa");
        } else if (tipo.equals("2")) {

            mdb.actualizarBasicos("delete from formaevaluacion where ID_Forma=" + idForma);

            for (int i = 0; i < modelo.getRowCount(); i++) {
                String desc = modelo.getValueAt(i, 0) + "";
                String valor = modelo.getValueAt(i, 1) + "";

                if (valor.equals("true")) {
                    String idEvaluacion = mdb.devuelveId("select id from tipoevaluacion where descripcion='" + desc + "'");
                    String sql = "INSERT INTO formaevaluacion VALUES(null,'" + idForma + "','" + idEvaluacion + "', 1, 1,current_date()"
                            + ", current_time(),1,1,1,1)";
                    //System.out.println(sql);
                    mdb.insertarEnCiclo(sql);
                    jpE.llenarCombo();
                    this.dispose();
                }
            }
        }*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaAsignacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaAsignacionesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaAsignacionesKeyReleased
    String valorTB = "", asignacion = "";
    private void tablaAsignacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignacionesMouseClicked
        // TODO add your handling code here:
        asignacion = tablaAsignaciones.getValueAt(tablaAsignaciones.getSelectedRow(), 0) + "";
        valorTB = tablaAsignaciones.getValueAt(tablaAsignaciones.getSelectedRow(), 1) + "";

        if (valorTB.equals("true")) {

            if (mdb.comprobarExistencia("select id from asignacionespersona where id_persona=" + idPersona + " and id_puesto =" + mdb.devuelveId("select id from puestos where descripcion='" + asignacion + "'") + " ") == null) {
                mdb.insertarBasicos("insert into asignacionespersona "
                        + "values (null," + idPersona + ", " + mdb.devuelveId("select id from puestos where descripcion='" + asignacion + "'") + ")");

                int result = JOptionPane.showConfirmDialog(null, "¿Deseas añadir la información de '"+asignacion+"' ?",
                        null, JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
//                    abrirFormulario(asignacion);
                } else {
                    JOptionPane.showMessageDialog(null, "Información Pendiente");
                }
            }
        }
    }//GEN-LAST:event_tablaAsignacionesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        editarDetalles(asignacion, "Detalles Asignacion");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        editarDetalles(asignacion, "Parcelas");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tablaAsignacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignacionesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaAsignacionesMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelPersona;
    private javax.swing.JTable tablaAsignaciones;
    // End of variables declaration//GEN-END:variables
}
