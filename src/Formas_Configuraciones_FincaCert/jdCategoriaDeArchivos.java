/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_FincaCert;

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
public class jdCategoriaDeArchivos extends javax.swing.JDialog {

    /**
     * Creates new form jdPais
     */
    String tipo, TxTvarC;
    String TxTvar;
    boolean Productor, Parcela;
    Connection cn;
    jpCategoriaDeArchivos jp;
    validaConfi valiConf;

    metodosDatosBasicos mdb;

    public jdCategoriaDeArchivos(java.awt.Frame parent, boolean modal, String tipo, String TxTvarC, String ProductorC, String ParcelaC, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
valiConf = new validaConfi();
        this.tipo = tipo;
        this.TxTvarC = TxTvarC;
        cn = c;
        if (tipo.equals("1")) {
            setTitle("nueva Categoria De Archivos");
        } else {
            setTitle("editar");
            //txtproductor.setText(TxTvarC);
            txtCategoria.setText(TxTvarC);
            // txtParcela.setText(ParcelaC);
            if (ProductorC.equals("true")) {
                checkprod.setSelected(true);
            } else {
                checkprod.setSelected(false);
            }
              if (ParcelaC.equals("true")) {
                checkparc.setSelected(true);
            } else {
                checkparc.setSelected(false);
            }
        }

    }

    String idPais;

    public void tipoProceso() {
        try {
            String sql = "";

            mdb = new metodosDatosBasicos(cn);
            TxTvar = txtCategoria.getText();

            if (checkprod.isSelected()) {
                Productor = true;
            } else {
                Productor = false;
            }
            if (checkparc.isSelected()) {
                Parcela = true;
            } else {
                Parcela = false;
            }

            if (tipo.equals("1")) {
                //nuevoPais();
                sql = "INSERT INTO CategoriaDeArchivos VALUES(null,'" + TxTvar + "'," + Productor + "," + Parcela + ", 1, 1,current_date()"
                        + ", current_time(),1,1,1,1)";
                mdb.insertarBasicos(sql);
                jp.llenaTabla();
                this.dispose();
            } else {
                //editarPais();
                sql = "UPDATE CategoriaDeArchivos SET  Productores=" + Productor + ", Descripcion='" + TxTvar + "',Parcelas=" + Parcela + " where  Descripcion='" + TxTvarC+"'";
               System.out.println(sql);
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
        txtCategoria = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        checkprod = new javax.swing.JCheckBox();
        checkparc = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Categoria De Archivo");

        txtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCategoriaKeyReleased(evt);
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

        jLabel2.setText("Obligatorio Para Productor");

        jLabel3.setText("Obligatorio Para Parcela");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCategoria)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkparc))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(checkprod)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(checkprod))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(checkparc))
                .addGap(66, 66, 66)
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

    private void txtCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaKeyReleased
       if (txtCategoria.getText().length() != 0) {
            txtCategoria.setText(valiConf.primerLetraMayuscula(txtCategoria.getText()).replace("S/n", "S/N"));
            txtCategoria.setText(valiConf.primerLetraMayuscula(txtCategoria.getText()).replace("S/d", "S/D"));
            txtCategoria.setText(valiConf.primerLetraMayuscula(txtCategoria.getText()).replace("S/o", "S/O"));
        }
 // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoriaKeyReleased

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
    private javax.swing.JCheckBox checkparc;
    private javax.swing.JCheckBox checkprod;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCategoria;
    // End of variables declaration//GEN-END:variables
}