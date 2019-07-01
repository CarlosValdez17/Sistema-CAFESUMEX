/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_DatosBasicos;

import Idioma.Propiedades;
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
    Propiedades idioma;

    public jdColoniaEjido(java.awt.Frame parent, boolean modal, String tipo, String co, String l, String m, String e, String p, String Idioma, Connection c) {
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
        idioma = new Propiedades(Idioma);

        jButton2.setText(idioma.getProperty("Aceptar"));
        jButton3.setText(idioma.getProperty("Cancelar"));
        jLabel1.setText(idioma.getProperty("Pais"));
        jLabel3.setText(idioma.getProperty("Estado"));
        jLabel4.setText(idioma.getProperty("Municipio"));
        jLabel5.setText(idioma.getProperty("Localidad"));
        jLabel2.setText(idioma.getProperty("ColoniaEjido"));

        rellenarCombos();
        comparaciones();

    }

    public void comparaciones() {
        if (tipo.equals("1")) {
            //setTitle("Nuevo");
            setTitle(idioma.getProperty("NuevoEjido"));
        } else {
            //setTitle("Editar Colonia/Ejido");
            setTitle(idioma.getProperty("EditarEjido"));

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

        datos = mdb.cargarCombos("SELECT descripcion from pais").split("¬");
        comboPais.setModel(new DefaultComboBoxModel((Object[]) datos));

        pais = comboPais.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + pais + "'").split("¬");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datos));

        estado = comboEstado.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + estado + "'").split("¬");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));

        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT l.descripcion \n"
                + "from localidad l \n"
                + "inner join municipio m on (l.ID_Municipio=m.ID) \n"
                + "where m.Descripcion='" + municipio + "' ").split("¬");
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
                        + ", current_time(), 1, 1, 1, 1, " + mdb.devuelveId("select id from localidad where descripcion='" + localidad + "'") + " )";
                mdb.insertarBasicos(sql);
                jpC.llenaTablaColonia();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Dato Repetido");
            }
        } else {
            //editar
            sql = "UPDATE ejidocolonia SET descripcion ='" + txtColonia.getText() + "', ID_localidad='" + mdb.devuelveId("select id from localidad where descripcion='" + localidad + "'") + "' where descripcion='" + co + "' ";
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

        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Estado");

        comboPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPaisActionPerformed(evt);
            }
        });

        jLabel1.setText("Pais");

        jLabel4.setText("Municipio");

        comboMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMunicipioActionPerformed(evt);
            }
        });

        jLabel5.setText("Localidad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboMunicipio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboLocalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtColonia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 145, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addComponent(comboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboPais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (txtColonia.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos");
        } else {
            tipoProceso();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void comboPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPaisActionPerformed
        // TODO add your handling code here:
        String p = comboPais.getSelectedItem() + "";
        datos = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + p + "'").split("¬");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datos));

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + comboEstado.getSelectedItem() + "" + "'").split("¬");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));

    }//GEN-LAST:event_comboPaisActionPerformed

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        // TODO add your handling code here:
        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + comboEstado.getSelectedItem() + "" + "'").split("¬");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void comboMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMunicipioActionPerformed
        // TODO add your handling code here:
        String municipio = comboMunicipio.getSelectedItem() + "";
        datos = mdb.cargarCombos("SELECT l.descripcion \n"
                + "from localidad l \n"
                + "inner join municipio m on (l.ID_Municipio=m.ID) \n"
                + "where m.Descripcion='" + municipio + "' ").split("¬");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboMunicipioActionPerformed

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
