/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Sociedades;

import Idioma.Propiedades;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author USUARIO
 */
public class jdRecepcion extends javax.swing.JDialog {

    /**
     * Creates new form jdRecepcion
     */
    Connection cn;
    metodosDatosBasicos mdb;
    String idRecepcionN = "", idRecepcion, Idioma, tipoOperacion;
    jpRecepcion jpR;
    Propiedades idioma;

    public jdRecepcion(java.awt.Frame parent, boolean modal, String tipoOperacion, String idRecepcion, String Idioma, Connection cn) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.Idioma = Idioma;
        this.tipoOperacion = tipoOperacion;
        this.idRecepcion = idRecepcion;
        idioma = new Propiedades(Idioma);
        mdb = new metodosDatosBasicos(cn);

        traductor();

        if (tipoOperacion.equals("Editar")) {
            llenarDatos();
        } else if (tipoOperacion.equals("Nuevo")) {
            rellenarCombos();
        }
    }

    public void traductor() {
        jLabel1.setText(idioma.getProperty("Sociedad"));
        jLabel2.setText(idioma.getProperty("IdRecepcion"));
        jLabel3.setText(idioma.getProperty("Responsable"));
        jLabel4.setText(idioma.getProperty("Capturista"));
        jLabel5.setText(idioma.getProperty("Direccion"));
        jLabel6.setText(idioma.getProperty("Telefono"));
        jLabel7.setText(idioma.getProperty("Pais"));
        jLabel8.setText(idioma.getProperty("Estado"));
        jLabel10.setText(idioma.getProperty("Municipio"));
        jLabel9.setText(idioma.getProperty("Localidad"));
        jLabel11.setText(idioma.getProperty("Descripcion"));
        jButton1.setText(idioma.getProperty("Aceptar"));
        jButton2.setText(idioma.getProperty("Cancelar"));
    }

    public void llenarDatos() {
        String[] datos = mdb.devolverLineaDatos("select r.idRecepcion, CONCAT(pf.Nombre, ' ', pf.apellidoPaterno, ' ', pf.apellidoMaterno),\n"
                + "CONCAT(pf2.Nombre, ' ', pf2.apellidoPaterno, ' ', pf2.apellidoMaterno), r.domicilio, r.descripcion,r.telefono,p.descripcion, e.Descripcion,m.descripcion,l.Descripcion, pm.nombrecorto\n"
                + "from recepciones r \n"
                + "left join personaf pf on (pf.ID=r.idResponsable)\n"
                + "left join personaf pf2 on (pf2.ID=r.idCapturista)\n"
                + "left join localidad l on (r.idLocalidad=l.ID)\n"
                + "left join municipio m on (l.ID_Municipio=m.ID)\n"
                + "left join estado e on (m.ID_Estado=e.ID)\n"
                + "left join pais p on (e.ID_Pais=p.ID)\n"
                + "left join personam pm on (r.idSociedad=pm.ID) where r.idRecepcion='" + idRecepcion + "'", 11).split("¬");

        String[] sociedades = mdb.cargarCombos("select nombrecorto from personam").split("¬");
        comboSociedades.setModel(new DefaultComboBoxModel((Object[]) sociedades));
        comboSociedades.setSelectedItem(datos[10]);

        txtRecepcion.setText(datos[0]);
        jTextField1.setText(datos[1]);
        jTextField2.setText(datos[2]);
        txtDomicilio.setText(datos[3]);
        txtTelefono.setText(datos[5]);
        txtDescripcion.setText(datos[4]);

        String datosG[];

        datosG = mdb.cargarCombos("SELECT descripcion from pais").split("¬");
        comboPais.setModel(new DefaultComboBoxModel((Object[]) datosG));

        comboPais.setSelectedItem(datos[6]);

        datosG = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + datos[6] + "'").split("¬");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datosG));
        comboEstado.setSelectedItem(datos[7]);

        datosG = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + datos[7] + "'").split("¬");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datosG));
        comboMunicipio.setSelectedItem(datos[8]);

        comboLocalidad.setSelectedItem(datos[9]);
    }

    String[] datos;

    public void rellenarCombos() {
        String[] sociedades = mdb.cargarCombos("select nombrecorto from personam").split("¬");
        comboSociedades.setModel(new DefaultComboBoxModel((Object[]) sociedades));

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

    }

    public void realizarOperacion() {
        String idSociedad = mdb.devuelveId("select id from personam where nombrecorto='" + comboSociedades.getSelectedItem() + "'");

        if (tipoOperacion.equals("Nuevo")) {
            mdb.insertarBasicos("insert into recepciones "
                    + "values(null, " + idSociedad + ", '" + txtRecepcion.getText() + "', '0', "
                    + "'0', '" + txtDomicilio.getText() + "', '" + txtTelefono.getText() + "', "
                    + "" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "'") + ", '" + txtDescripcion.getText() + "',1 ) ");
            this.dispose();
            jpR.llenarTabla();
        } else if (tipoOperacion.equals("Editar")) {
            mdb.actualizarBasicos("update recepciones set domicilio='" + txtDomicilio.getText() + "', "
                    + "telefono='" + txtTelefono.getText() + "', "
                    + "idLocalidad=" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "'") + ","
                    + "descripcion='" + txtDescripcion.getText() + "' where idRecepcion='" + idRecepcion + "' ");
            this.dispose();
            jpR.llenarTabla();
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
        comboSociedades = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtRecepcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        comboPais = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboLocalidad = new javax.swing.JComboBox<>();
        comboMunicipio = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Recepcion");
        setResizable(false);

        jLabel1.setText("Sociedad");

        comboSociedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSociedadesActionPerformed(evt);
            }
        });

        jLabel2.setText("Id Recepcion");

        txtRecepcion.setEnabled(false);

        jLabel3.setText("Responsable");

        jLabel4.setText("Capturista");

        jLabel5.setText("Domicilio");

        jLabel6.setText("Telefono");

        comboPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPaisActionPerformed(evt);
            }
        });

        jLabel7.setText("Pais");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        jLabel8.setText("Estado");

        jLabel9.setText("Localidad");

        comboLocalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMunicipioActionPerformed(evt);
            }
        });

        jLabel10.setText("Municipio");

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

        jLabel11.setText("Descripcion");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jTextField1.setEnabled(false);

        jTextField2.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRecepcion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDomicilio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMunicipio, javax.swing.GroupLayout.Alignment.LEADING, 0, 190, Short.MAX_VALUE)
                            .addComponent(comboPais, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboLocalidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTelefono)
                            .addComponent(jTextField2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(comboSociedades, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSociedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        realizarOperacion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboSociedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSociedadesActionPerformed
        // TODO add your handling code here:
        String idSociedad = mdb.devuelveId("select id from personam where nombrecorto='" + comboSociedades.getSelectedItem() + "'");

        idRecepcionN = "RE-" + mdb.devuelveUnDato("select clavecorte from personam where id=" + idSociedad) + "-" + mdb.devuelveUnDato("select id+1 from recepciones order by id desc limit 1");
        txtRecepcion.setText(idRecepcion);
    }//GEN-LAST:event_comboSociedadesActionPerformed

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
        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + comboMunicipio.getSelectedItem() + "" + "'").split("¬");
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
            java.util.logging.Logger.getLogger(jdRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdRecepcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdRecepcion dialog = new jdRecepcion(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> comboSociedades;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtRecepcion;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
