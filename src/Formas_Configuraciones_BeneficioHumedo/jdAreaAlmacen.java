/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_BeneficioHumedo;

import Metodos_Configuraciones.metodosDatosBasicos;
import Metodos_Configuraciones.validaConfi;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Valdez
 */
public class jdAreaAlmacen extends javax.swing.JDialog {

    /**
     * Creates new form jdPais
     */
    String tipo, ClaveC;
    String Clave, TxTvar, Almacen;
    Connection cn;
    jpAreaAlmacen jp;
    validaConfi valConf;
    metodosDatosBasicos mdb;

    public jdAreaAlmacen(java.awt.Frame parent, boolean modal, String tipo, String AlmacenC, String ClaveC, String TxTvarC, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        valConf = new validaConfi();

        this.tipo = tipo;
        this.ClaveC = ClaveC;
        cn = c;
        if (tipo.equals("1")) {
            setTitle("Nueva Area Almacen");
        } else {
            setTitle("Editar");
            Area.setText(TxTvarC);
            txtclave.setText(ClaveC);
            txtAlmacen.setText(AlmacenC);
        }

    }

    String idPais;

    public void tipoProceso() {
        try {
            String sql = "";

            mdb = new metodosDatosBasicos(cn);
            TxTvar = Area.getText();
            Clave = txtclave.getText();
            Almacen = txtAlmacen.getText();

            if (tipo.equals("1")) {
                //nuevoPais();
                sql = "INSERT INTO AreaAlmacen VALUES(null,'" + Almacen + "','" + Clave + "','" + TxTvar + "', 1, 1,current_date()"
                        + ", current_time(),1,1,1,1)";
                mdb.insertarBasicos(sql);
                jp.llenaTabla();
                this.dispose();
            } else {
                //editarPais();
                sql = "UPDATE AreaAlmacen SET  Clave='" + Clave + "',Area='" + TxTvar + "',Almacen='" + Almacen + "' where Clave='" + ClaveC + "' ";
                mdb.actualizarBasicos(sql);
                jp.llenaTabla();
                this.dispose();

            }
        } catch (Exception e) {
        }
    }

    /* public void llenaUsuarios() {
        limpiar(tablaUsuarios);
        DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
        u.cargarUsu(modelo);
    }*/
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
        txtclave = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Area = new javax.swing.JTextField();
        txtAlmacen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Clave");

        txtclave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclaveKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtclaveKeyTyped(evt);
            }
        });

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

        jLabel2.setText("Area");

        Area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AreaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AreaKeyTyped(evt);
            }
        });

        txtAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlmacenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlmacenKeyTyped(evt);
            }
        });

        jLabel3.setText("Almacen");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtclave)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addComponent(Area)
                    .addComponent(txtAlmacen)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tipoProceso();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtAlmacenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlmacenKeyReleased
        // TODO add your handling code here:
        if (txtAlmacen.getText().length() != 0) {
            txtAlmacen.setText(valConf.primerLetraMayuscula(txtAlmacen.getText()).replace("S/n", "S/N"));
            txtAlmacen.setText(valConf.primerLetraMayuscula(txtAlmacen.getText()).replace("S/d", "S/D"));
            txtAlmacen.setText(valConf.primerLetraMayuscula(txtAlmacen.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtAlmacenKeyReleased

    private void txtclaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclaveKeyReleased
        // TODO add your handling code here:
        if (txtclave.getText().length() != 0) {
            txtclave.setText(valConf.primerLetraMayuscula(txtclave.getText()).replace("S/n", "S/N"));
            txtclave.setText(valConf.primerLetraMayuscula(txtclave.getText()).replace("S/d", "S/D"));
            txtclave.setText(valConf.primerLetraMayuscula(txtclave.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtclaveKeyReleased

    private void AreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AreaKeyReleased
        // TODO add your handling code here:
        if (Area.getText().length() != 0) {
            Area.setText(valConf.primerLetraMayuscula(Area.getText()).replace("S/n", "S/N"));
            Area.setText(valConf.primerLetraMayuscula(Area.getText()).replace("S/d", "S/D"));
            Area.setText(valConf.primerLetraMayuscula(Area.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_AreaKeyReleased

    private void txtAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlmacenKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtAlmacenKeyTyped

    private void txtclaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclaveKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtclaveKeyTyped

    private void AreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AreaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_AreaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

        /* Create and display the dialog */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdPais dialog = new jdPais(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        //</editor-fold>

        /* Create and display the dialog */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdPais dialog = new jdPais(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Area;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAlmacen;
    private javax.swing.JTextField txtclave;
    // End of variables declaration//GEN-END:variables
}
