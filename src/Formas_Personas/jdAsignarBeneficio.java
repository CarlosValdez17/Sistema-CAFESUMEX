/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import Idioma.Propiedades;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class jdAsignarBeneficio extends javax.swing.JDialog {

    /**
     * Creates new form jdAsignarBeneficio
     */
    String idPersona, nombrePersona, tipo, puesto, Idioma;
    metodosDatosBasicos mdb;
    Connection cn;
    Propiedades idioma;

    public jdAsignarBeneficio(java.awt.Frame parent, boolean modal, String idPersona, String nombrePersona, String tipo, String puesto, String Idioma, Connection cn) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.cn = cn;
        this.tipo = tipo;
        this.puesto = puesto;
        this.Idioma = Idioma;
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;

        idioma = new Propiedades(Idioma);
        mdb = new metodosDatosBasicos(cn);

        txtPersona.setText(nombrePersona);

        if (tipo.equals("Beneficio")) {
            jLabel2.setText(idioma.getProperty("BeneficiosHumedos"));
        } else if (tipo.equals("Recepcion")) {
            jLabel2.setText(idioma.getProperty("Recepciones"));
        }

        llenarCombo();
    }

    public void traductor() {
        jLabel1.setText(idioma.getProperty("Capturista"));
        jLabel6.setText(idioma.getProperty("Sociedad"));
        jLabel7.setText(idioma.getProperty("Responsable"));
        jLabel8.setText(idioma.getProperty("Capturista"));
        jLabel3.setText(idioma.getProperty("Domicilio"));
        jLabel4.setText(idioma.getProperty("Telefono"));
        jLabel5.setText(idioma.getProperty("Descripcion"));

        jButton1.setText(idioma.getProperty("Guardar"));
        jButton2.setText(idioma.getProperty("Cancelar"));
    }

    public void llenarCombo() {
        if (tipo.equals("Beneficio")) {
            String[] datos = mdb.cargarCombos("select nombre from beneficioshumedos").split("¬");
            jComboBox1.setModel(new DefaultComboBoxModel((Object[]) datos));
        } else if (tipo.equals("Recepcion")) {
            String[] datos = mdb.cargarCombos("select idRecepcion from recepciones").split("¬");
            jComboBox1.setModel(new DefaultComboBoxModel((Object[]) datos));
        }

    }

    public void datos() {
        switch (tipo) {
            case "Beneficio":
                if (!jComboBox1.getSelectedItem().equals("Seleccione..")) {
                    String[] datos = mdb.devolverLineaDatos("select b.domicilio, b.telefono, b.descripcion, p.razonsocial "
                            + "from beneficioshumedos b "
                            + "inner join personam p on(p.id=b.idSociedad) "
                            + "where nombre='" + jComboBox1.getSelectedItem() + "'", 4).split("¬");
                    txtDomicilio.setText(datos[0]);
                    txtTelefono.setText(datos[1]);
                    txtDescripcion.setText(datos[2]);
                    txtSociedad.setText(datos[3]);

                    txtCapturista.setText(mdb.devuelveUnDato("select concat(pf.Nombre,' ',pf.ApellidoPaterno,' ',pf.ApellidoMaterno) "
                            + "from beneficioshumedos b \n"
                            + "inner join personaf pf on(pf.ID=b.idPersonaCapturista)\n"
                            + "where b.nombre='" + jComboBox1.getSelectedItem() + "'"));

                    txtResponsable.setText(mdb.devuelveUnDato("select concat(pf.Nombre,' ',pf.ApellidoPaterno,' ',pf.ApellidoMaterno) "
                            + "from beneficioshumedos b \n"
                            + "inner join personaf pf on(pf.ID=b.idPersonaEncargada)\n"
                            + "where b.nombre='" + jComboBox1.getSelectedItem() + "'"));

                    if (puesto.equals("Capturista")) {
                        if (txtCapturista.getText().equals(txtPersona.getText())) {
                            JOptionPane.showMessageDialog(null, "Ya Perteneces A Este Beneficio");
                        }
                    } else if (puesto.equals("Encargado")) {
                        if (txtResponsable.getText().equals(txtPersona.getText())) {
                            JOptionPane.showMessageDialog(null, "Ya Perteneces A Este Beneficio");
                        }
                    }

                } else {
                    txtDomicilio.setText("");
                    txtTelefono.setText("");
                    txtDescripcion.setText("");
                    txtSociedad.setText("");
                }
                break;

            case "Recepcion":
                if (!jComboBox1.getSelectedItem().equals("Seleccione..")) {
                    String[] datos = mdb.devolverLineaDatos("select r.domicilio, r.telefono, r.descripcion, p.razonsocial "
                            + "from recepciones r "
                            + "inner join personam p on(p.id=r.idSociedad) "
                            + "where idRecepcion='" + jComboBox1.getSelectedItem() + "'", 4).split("¬");
                    txtDomicilio.setText(datos[0]);
                    txtTelefono.setText(datos[1]);
                    txtDescripcion.setText(datos[2]);
                    txtSociedad.setText(datos[3]);

                    txtCapturista.setText(mdb.devuelveUnDato("select concat(pf.Nombre,' ',pf.ApellidoPaterno,' ',pf.ApellidoMaterno) "
                            + "from recepciones r \n"
                            + "inner join personaf pf on(pf.ID=r.idCapturista)\n "
                            + "where idRecepcion='" + jComboBox1.getSelectedItem() + "'"));

                    txtResponsable.setText(mdb.devuelveUnDato("select concat(pf.Nombre,' ',pf.ApellidoPaterno,' ',pf.ApellidoMaterno) "
                            + "from recepciones r \n"
                            + "inner join personaf pf on(pf.ID=r.idResponsable)\n "
                            + "where idRecepcion='" + jComboBox1.getSelectedItem() + "'"));

                    if (puesto.equals("Capturista")) {
                        if (txtCapturista.getText().equals(txtPersona.getText())) {
                            JOptionPane.showMessageDialog(null, "Ya Perteneces A Este Beneficio");
                        }
                    } else if (puesto.equals("Encargado")) {
                        if (txtResponsable.getText().equals(txtPersona.getText())) {
                            JOptionPane.showMessageDialog(null, "Ya Perteneces A Este Beneficio");
                        }
                    }
                } else {
                    txtDomicilio.setText("");
                    txtTelefono.setText("");
                    txtDescripcion.setText("");
                    txtSociedad.setText("");
                }
                break;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPersona = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtSociedad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        txtCapturista = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Capturista");

        txtPersona.setEditable(false);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Beneficios Humedos");

        jLabel3.setText("Domicilio");

        jLabel4.setText("Telefono");

        txtDomicilio.setEnabled(false);

        txtTelefono.setEnabled(false);

        jLabel5.setText("Descripcion");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtSociedad.setEnabled(false);

        jLabel6.setText("Sociedad");

        jLabel7.setText("Responsable");

        txtResponsable.setEnabled(false);

        txtCapturista.setEnabled(false);

        jLabel8.setText("Capturista");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTelefono)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPersona)
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtCapturista)))
                    .addComponent(txtSociedad)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCapturista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        datos();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (tipo.equals("Beneficio") && puesto.equals("Capturista")) {
            mdb.actualizarBasicos("update beneficioshumedos set idPersonaCapturista=" + idPersona + " where nombre='" + jComboBox1.getSelectedItem() + "'");
        } else if (tipo.equals("Beneficio") && puesto.equals("Encargado")) {
            mdb.actualizarBasicos("update beneficioshumedos set idPersonaEncargada=" + idPersona + " where nombre='" + jComboBox1.getSelectedItem() + "'");
        } else if (tipo.equals("Recepcion") && puesto.equals("Capturista")) {
            mdb.actualizarBasicos("update recepciones set idCapturista=" + idPersona + " where idRecepcion='" + jComboBox1.getSelectedItem() + "'");
        } else if (tipo.equals("Recepcion") && puesto.equals("Encargado")) {
            mdb.actualizarBasicos("update recepciones set idResponsable=" + idPersona + " where idRecepcion='" + jComboBox1.getSelectedItem() + "'");
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(jdAsignarBeneficio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdAsignarBeneficio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdAsignarBeneficio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdAsignarBeneficio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCapturista;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtPersona;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextField txtSociedad;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
