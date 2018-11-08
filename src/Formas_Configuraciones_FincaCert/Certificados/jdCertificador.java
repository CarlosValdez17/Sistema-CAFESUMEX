/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_FincaCert.Certificados;

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
public class jdCertificador extends javax.swing.JDialog {

    /**
     * Creates new form jdPais
     */
    String tipo, ClaveC;
    String Clave,TxTvar;
    Connection cn;
    jpCertificador jp;
   validaConfi valiConf;


    metodosDatosBasicos mdb;

    public jdCertificador(java.awt.Frame parent, boolean modal, String tipo, String ClaveC,String TxTvarC, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
valiConf = new validaConfi();
        this.tipo = tipo;
        this.ClaveC = ClaveC;
        cn = c;
        if (tipo.equals("1")) {
            setTitle("nuevo Certificador");
        }else{
              setTitle("editar");
              Centavos.setText(TxTvarC);
              txtRetenciones.setText(ClaveC);
        }  
 
    }

    String idPais;

 

    public void tipoProceso() {
        try {
            String sql = "";

            mdb = new metodosDatosBasicos(cn);
           TxTvar=Centavos.getText();
         Clave = txtRetenciones.getText();
        

            if (tipo.equals("1")) {
                //nuevoPais();
                sql = "INSERT INTO Certificadora VALUES(null,'" +Clave+ "','"+TxTvar+"', 1, 1,current_date()"
                        + ", current_time(),1,1,1,1)";
                mdb.insertarBasicos(sql);
                jp.llenaTabla();
                this.dispose();
            } else {
                //editarPais();
                sql = "UPDATE Certificadora SET  Clave='" +Clave+ "', Descripcion='"+TxTvar+"' where Clave='" + ClaveC + "' ";
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
        txtRetenciones = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Centavos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Clave");

        txtRetenciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRetencionesKeyTyped(evt);
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

        jLabel2.setText("Certificador");

        Centavos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CentavosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRetenciones)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Centavos))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRetenciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Centavos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
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

    private void txtRetencionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRetencionesKeyTyped
char c = evt.getKeyChar();
        if (Character.isDigit(c)) {//if (Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();}        // TODO add your handling code here:
    }//GEN-LAST:event_txtRetencionesKeyTyped

    private void CentavosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CentavosKeyReleased
if (Centavos.getText().length() != 0) {
            Centavos.setText(valiConf.primerLetraMayuscula(Centavos.getText()).replace("S/n", "S/N"));
            Centavos.setText(valiConf.primerLetraMayuscula(Centavos.getText()).replace("S/d", "S/D"));
            Centavos.setText(valiConf.primerLetraMayuscula(Centavos.getText()).replace("S/o", "S/O"));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CentavosKeyReleased

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
    private javax.swing.JTextField Centavos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtRetenciones;
    // End of variables declaration//GEN-END:variables
}
