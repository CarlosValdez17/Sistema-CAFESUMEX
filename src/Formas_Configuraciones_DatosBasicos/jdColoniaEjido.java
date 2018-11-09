/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_DatosBasicos;

import Metodos_Configuraciones.metodosDatosBasicos;
import Metodos_Configuraciones.validaConfi;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Valdez
 */
public class jdColoniaEjido extends javax.swing.JDialog {

    /**
     * Creates new form jdColoniaEjido
     */
    String tipo, co, l, m, e, p;
    metodosDatosBasicos mdb;
    validaConfi valiConf;
    jpColoniaEjido jpC;
    Connection cn;

    public jdColoniaEjido(java.awt.Frame parent, boolean modal, String tipo, String co, String l, String m, String e, String p, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cn = c;
        this.tipo = tipo;
        this.co = co;
        this.l = l;
        this.m = m;
        this.e = e;
        this.p = p;

        mdb = new metodosDatosBasicos(cn);
        valiConf = new validaConfi();
        
        rellenarCombos();
        comparaciones();

    }
   
    public void comparaciones() {
        if (tipo.equals("1")) {
            setTitle("Nuevo");

        } else {
            setTitle("Editar Colonia/Ejido");

            comboPais.setEnabled(false);
            comboEstado.setEnabled(false);
            comboMunicipio.setEnabled(false);
            comboLocalidad.setEnabled(false);

            comboMunicipio.addItem(m);
            comboMunicipio.setSelectedItem(m);

            comboEstado.addItem(e);
            comboEstado.setSelectedItem(e);

            comboPais.addItem(p);
            comboPais.setSelectedItem(p);

            comboLocalidad.addItem(l);
            comboLocalidad.setSelectedItem(l);
            
            txtColonia.setText(co);
        }
    }

    String[] datos;

    public void rellenarCombos() {
        String pais, estado, municipio;

        datos = mdb.cargarCombos("SELECT descripcion from pais").split(",");
        comboPais.setModel(new DefaultComboBoxModel((Object[]) datos));

        pais = comboPais.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + pais + "'").split(",");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datos));

        estado = comboEstado.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + estado + "'").split(",");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));

        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT l.descripcion \n"
                + "from localidad l \n"
                + "inner join municipio m on (l.ID_Municipio=m.ID) \n"
                + "where m.Descripcion='" + municipio + "' ").split(",");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));

        //comboMunicipio.setSelectedItem(m);
    }

    public void tipoProceso() {
        String sql = "";

        String localidad = comboLocalidad.getSelectedItem() + "";

        if (tipo.equals("1")) {
            //nuevo
            if (mdb.comprobarExistencia("select descripcion from ejidocolonia where descripcion='" + txtColonia.getText() + "'") == null) {
                sql = "INSERT INTO ejidocolonia VALUES(null,'" + txtColonia.getText() + "', 1, 1,current_date()"
                        + ", current_time(), 1, 1, 1, 1, " + mdb.devuelveIdPais(localidad, "localidad") + " )";
                mdb.insertarBasicos(sql);
                jpC.llenaTablaColonia();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Dato Repetido");
            }
        } else {
            //editar
            sql = "UPDATE ejidocolonia SET descripcion ='" + txtColonia.getText() + "', ID_localidad='" + mdb.devuelveIdPais(localidad, "localidad") + "' where descripcion='" + co + "' ";
            System.out.println(sql);
            mdb.actualizarBasicos(sql);
            jpC.llenaTablaColonia();
            this.dispose();
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
        jLabel2 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboPais = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboMunicipio = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboLocalidad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Colonia/Ejido");

        txtColonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColoniaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColoniaKeyTyped(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        comboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEstadoItemStateChanged(evt);
            }
        });
        comboEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboEstadoMouseClicked(evt);
            }
        });

        jLabel3.setText("Estado");

        comboPais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPaisItemStateChanged(evt);
            }
        });

        jLabel1.setText("Pais");

        jLabel4.setText("Municipio");

        comboMunicipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMunicipioItemStateChanged(evt);
            }
        });
        comboMunicipio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboMunicipioMouseClicked(evt);
            }
        });

        jLabel5.setText("Localidad");

        comboLocalidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboLocalidadItemStateChanged(evt);
            }
        });
        comboLocalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboLocalidadMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3))
                        .addComponent(txtColonia)
                        .addComponent(comboEstado, 0, 280, Short.MAX_VALUE)
                        .addComponent(comboPais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(19, 19, 19))
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
        if (txtColonia.getText().length() == 0 ) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        } else {
        tipoProceso();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged
        // TODO add your handling code here:
        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + comboEstado.getSelectedItem() + "" + "'").split(",");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboEstadoItemStateChanged

    private void comboEstadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboEstadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEstadoMouseClicked

    private void comboPaisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPaisItemStateChanged
        // TODO add your handling code here:
        String p = comboPais.getSelectedItem() + "";
        datos = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + p + "'").split(",");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datos));

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + comboEstado.getSelectedItem() + "" + "'").split(",");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboPaisItemStateChanged

    private void comboMunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMunicipioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMunicipioItemStateChanged

    private void comboMunicipioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMunicipioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMunicipioMouseClicked

    private void comboLocalidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboLocalidadItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLocalidadItemStateChanged

    private void comboLocalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboLocalidadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLocalidadMouseClicked

    private void txtColoniaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColoniaKeyReleased
        // TODO add your handling code here:
        txtColonia.setText(valiConf.primerLetraMayuscula(txtColonia.getText()));
    }//GEN-LAST:event_txtColoniaKeyReleased

    private void txtColoniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColoniaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtColoniaKeyTyped

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
            java.util.logging.Logger.getLogger(jdColoniaEjido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdColoniaEjido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdColoniaEjido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdColoniaEjido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
 /*        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdColoniaEjido dialog = new jdColoniaEjido(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboLocalidad;
    private javax.swing.JComboBox<String> comboMunicipio;
    private javax.swing.JComboBox<String> comboPais;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtColonia;
    // End of variables declaration//GEN-END:variables
}