/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_FincaCert;

import Metodos_Configuraciones.metodosDatosBasicos;
import Metodos_Configuraciones.validaConfi;
import java.sql.Connection;

/**
 *
 * @author Carlos Valdez
 */
public class jdFertilizante extends javax.swing.JDialog {

    /**
     * Creates new form jdVariedadCafe
     */
    jpFertilizante jpF;
    Connection cn;
    metodosDatosBasicos mdb;
    String tipo, dato;
 validaConfi valiConf;

    public jdFertilizante(java.awt.Frame parent, boolean modal, String tipo, String dato, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
valiConf = new validaConfi();
        cn = c;
        this.dato = dato;
        this.tipo = tipo;

        mdb = new metodosDatosBasicos(cn);
        
        if (tipo.equals("1")) {
            setTitle("Nuevo Fertilizante");
        } else {
            setTitle("Editar Fertilizante");
            txtFertilizante.setText(dato);
        }
    }

    public void tipoProceso() {
        try {
            String sql = "";

            if (tipo.equals("1")) {
                //nuevoPais();
                sql = "INSERT INTO fertilizante VALUES(null,'" + txtFertilizante.getText() + "', 1, 1,current_date()"
                        + ", current_time(), 1, 1, 1, 1 )";
                mdb.insertarBasicos(sql);
                jpF.llenaTabla();
                this.dispose();
            } else {
                //editarPais();
                sql = "UPDATE fertilizante SET  descripcion ='" + txtFertilizante.getText() + "' where descripcion='" + dato + "' ";
                mdb.actualizarBasicos(sql);
                jpF.llenaTabla();
                this.dispose();

            }
        } catch (Exception e) {
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
        txtFertilizante = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Fertilizante");

        txtFertilizante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFertilizanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFertilizanteKeyTyped(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFertilizante)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFertilizante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tipoProceso();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtFertilizanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFertilizanteKeyReleased
if (txtFertilizante.getText().length() != 0) {
            txtFertilizante.setText(valiConf.primerLetraMayuscula(txtFertilizante.getText()).replace("S/n", "S/N"));
            txtFertilizante.setText(valiConf.primerLetraMayuscula(txtFertilizante.getText()).replace("S/d", "S/D"));
            txtFertilizante.setText(valiConf.primerLetraMayuscula(txtFertilizante.getText()).replace("S/o", "S/O"));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFertilizanteKeyReleased

    private void txtFertilizanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFertilizanteKeyTyped
char c = evt.getKeyChar();
        if (Character.isDigit(c)) {//if (Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtFertilizanteKeyTyped

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
            java.util.logging.Logger.getLogger(jdFertilizante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdFertilizante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdFertilizante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdFertilizante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
 /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdVariedadCafe dialog = new jdVariedadCafe(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtFertilizante;
    // End of variables declaration//GEN-END:variables
}
