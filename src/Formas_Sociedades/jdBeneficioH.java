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
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class jdBeneficioH extends javax.swing.JDialog {

    /**
     * Creates new form jdBeneficioH
     */
    Connection cn;
    metodosDatosBasicos mdb;
    String beneficio, tipo, Idioma;
    jpBeneficiosH jpBH;
    Propiedades idioma;

    public jdBeneficioH(java.awt.Frame parent, boolean modal, String beneficio, String tipo, String Idioma, Connection cn) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.cn = cn;
        this.tipo = tipo;
        this.Idioma = Idioma;
        this.beneficio = beneficio;
        idioma = new Propiedades(Idioma);
        mdb = new metodosDatosBasicos(cn);

        if (tipo.equals("Nuevo")) {
            rellenarCombo();
        } else {
            llenarDatos();
        }
        traductor();
    }

    public void traductor() {
        jLabel2.setText(idioma.getProperty("Sociedad"));
        jLabel1.setText(idioma.getProperty("Nombre"));
        jLabel12.setText(idioma.getProperty("Responsable"));
        jLabel13.setText(idioma.getProperty("Capturista"));
        jLabel4.setText(idioma.getProperty("Direccion"));
        jLabel5.setText(idioma.getProperty("Telefono"));
        jLabel6.setText(idioma.getProperty("Localizacion"));
        jLabel7.setText(idioma.getProperty("Pais"));
        jLabel8.setText(idioma.getProperty("Estado"));
        jLabel10.setText(idioma.getProperty("Muncipio"));
        jLabel9.setText(idioma.getProperty("Localidad"));
        jLabel11.setText(idioma.getProperty("Descripcion"));

        jButton1.setText(idioma.getProperty("Aceptar"));
        jButton2.setText(idioma.getProperty("Cancelar"));
    }

    String[] datos;

    public void rellenarCombo() {
        String[] sociedades = mdb.cargarCombos("select nombrecorto from personam").split("¬");
        comboSociedad.setModel(new DefaultComboBoxModel((Object[]) sociedades));

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

    public void llenarDatos() {
        String datos[] = mdb.devolverLineaDatos("select pm.NombreCorto, b.nombre, concat(pf.Nombre,' ',pf.ApellidoPaterno,' ',pf.ApellidoMaterno), concat(pf2.Nombre,' ',pf2.ApellidoPaterno,' ',pf2.ApellidoMaterno),b.domicilio, b.telefono,b.descripcion, l.Descripcion, m.Descripcion, e.Descripcion, p.Descripcion\n"
                + "from beneficioshumedos b\n"
                + "left join personam pm on (b.idSociedad=pm.ID)\n"
                + "left join personaf pf on (b.idPersonaEncargada=pf.ID)\n"
                + "left join personaf pf2 on (b.idPersonaCapturista=pf.ID)\n"
                + "left join localidad l on (b.idLocalidad=l.ID)\n"
                + "left join municipio m on (l.ID_Municipio=m.ID)\n"
                + "left join estado e on (m.ID_Estado=e.ID)\n"
                + "left join pais p on (e.ID_Pais=p.ID)\n"
                + "where b.nombre='" + beneficio + "'", 11).split("¬");

        String[] sociedades = mdb.cargarCombos("select nombrecorto from personam").split("¬");
        comboSociedad.setModel(new DefaultComboBoxModel((Object[]) sociedades));

        comboSociedad.setSelectedItem(datos[0]);
        txtNombre.setText(datos[1]);
        txtResponsable.setText(datos[2]);
        txtCapturista.setText(datos[3]);
        txtDomicilio.setText(datos[4]);
        txtTelefono.setText(datos[5]);
        txtDescripcion.setText(datos[6]);

        //Combos Geograficos
        String datosG[];

        datosG = mdb.cargarCombos("SELECT descripcion from pais").split("¬");
        comboPais.setModel(new DefaultComboBoxModel((Object[]) datosG));

        comboPais.setSelectedItem(datos[10]);

        datosG = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + datos[10] + "'").split("¬");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datosG));
        comboEstado.setSelectedItem(datos[9]);

        datosG = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + datos[9] + "'").split("¬");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datosG));
        comboMunicipio.setSelectedItem(datos[8]);

        comboLocalidad.setSelectedItem(datos[7]);
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
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboSociedad = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtDomicilio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtLocalizacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboPais = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        comboMunicipio = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        comboLocalidad = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCapturista = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Beneficio Humedo");
        setResizable(false);

        jLabel1.setText("Nombre");

        txtNombre.setEnabled(false);

        jLabel2.setText("Sociedad");

        comboSociedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSociedadActionPerformed(evt);
            }
        });

        jLabel4.setText("Domicilio");

        jLabel5.setText("Telefono");

        jLabel6.setText("Localizacion");

        jLabel7.setText("Pais");

        comboPais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPaisItemStateChanged(evt);
            }
        });

        jLabel8.setText("Estado");

        comboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEstadoItemStateChanged(evt);
            }
        });

        comboMunicipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMunicipioItemStateChanged(evt);
            }
        });

        jLabel9.setText("Localidad");

        jLabel10.setText("Municipio");

        jLabel11.setText("Descripcion");

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

        jLabel12.setText("Responsable");

        txtResponsable.setEnabled(false);

        jLabel13.setText("Capturista");

        txtCapturista.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(comboSociedad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtCapturista, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSociedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCapturista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mdb.insertarBasicos("insert into beneficioshumedos values(null,0,0, " + mdb.devuelveId("select id from personam where nombrecorto='" + comboSociedad.getSelectedItem() + "'") + ","
                + "'" + txtNombre.getText() + "', '" + txtDomicilio.getText() + "', '" + txtTelefono.getText() + "', '" + txtLocalizacion.getText() + "',"
                + "'" + txtDescripcion.getText() + "', " + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "' ") + ",1  )");

        jpBH.llenarTabla();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged
        // TODO add your handling code here:
        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + comboEstado.getSelectedItem() + "" + "'").split("¬");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboEstadoItemStateChanged

    private void comboMunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMunicipioItemStateChanged
        // TODO add your handling code here:
        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + comboMunicipio.getSelectedItem() + "" + "'").split("¬");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboMunicipioItemStateChanged

    private void comboPaisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPaisItemStateChanged
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
    }//GEN-LAST:event_comboPaisItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    String idBeneficio = "";
    private void comboSociedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSociedadActionPerformed
        // TODO add your handling code here:
        String idSociedad = mdb.devuelveId("select id from personam where nombrecorto='" + comboSociedad.getSelectedItem() + "'");
        idBeneficio = "BH-" + mdb.devuelveUnDato("select clavecorte from personam where id=" + idSociedad)
                + "-" + mdb.devuelveUnDato("select id+1 from beneficioshumedos order by id desc limit 1");
        txtNombre.setText(idBeneficio);
    }//GEN-LAST:event_comboSociedadActionPerformed

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
            java.util.logging.Logger.getLogger(jdBeneficioH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdBeneficioH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdBeneficioH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdBeneficioH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
 /*   java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdBeneficioH dialog = new jdBeneficioH(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> comboSociedad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCapturista;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtLocalizacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
