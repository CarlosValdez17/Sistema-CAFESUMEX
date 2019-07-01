/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import Formas_FincaCert.jdFormularioProductor;
import Formas_Configuraciones_Recepcion.*;
import Idioma.Propiedades;
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
public class jdAsignacionesPersonas2 extends javax.swing.JDialog {

    /**
     * Creates new form jdFormaProceso
     */
    Connection cn;
    String tipoPersona, nombrePersona, idPersona, tipoOperacion, Idioma;
    metodosDatosBasicos mdb;
    jpEvaluaciones jpE;
    jdSociedadesPersonas formSoc;
    jdFormularioProductor jdFP;
    Propiedades idioma;

    public jdAsignacionesPersonas2(java.awt.Frame parent, boolean modal, String tipoOperacion, String tipoPersona, String nombrePersona, String idPersona, String Idioma, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cn = c;
        this.idPersona = idPersona;
        this.Idioma = Idioma;
        //tipoPersona es el tipo de persona
        this.tipoPersona = tipoPersona;
        this.tipoOperacion = tipoOperacion;
        this.nombrePersona = nombrePersona;
        mdb = new metodosDatosBasicos(cn);
        idioma = new Propiedades(Idioma);
        labelPersona.setText(nombrePersona);
        setTitle(idioma.getProperty("Asignaciones"));

        checkSocio.setText(idioma.getProperty("Socio"));
        checkProd.setText(idioma.getProperty("Productor"));
        checkCapturistaR.setText(idioma.getProperty("CapturistaRecepcion"));
        checkCapturistaB.setText(idioma.getProperty("CapturistaBH"));
        checkEncargadoR.setText(idioma.getProperty("EncargadoRecepcion"));
        checkEncargadoB.setText(idioma.getProperty("EncargadoBH"));
        checkTransportista.setText(idioma.getProperty("Transportista"));
        checkEnlace.setText(idioma.getProperty("EnlaceComercial"));
        jLabel3.setText(idioma.getProperty("Asignaciones"));
        jButton4.setText(idioma.getProperty("Cerrar"));

        marcarChecks();
    }

    public void marcarChecks() {
        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Enlace Comercial' ").equals("")) {
            checkEnlace.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Socio' ").equals("")) {
            checkSocio.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Productor' ").equals("")) {
            checkProd.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Transportista' ").equals("")) {
            checkTransportista.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Encargado Beneficio' ").equals("")) {
            checkEncargadoB.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Encargado Recepcion' ").equals("")) {
            checkEncargadoR.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Capturista Beneficio' ").equals("")) {
            checkCapturistaB.setSelected(true);
        }

        if (!mdb.devuelveUnDato("select id from asignacionespersona "
                + "where id_persona=" + idPersona + " and codigo='Capturista Recepcion' ").equals("")) {
            checkCapturistaR.setSelected(true);
        }
    }

    /* public void abrirFormulario(String formulario) {
        //JOptionPane.showMessageDialog(null, "Entre al case");
        switch (formulario) {
            case "Productor":
                if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "' ") == null
                        || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'").equals("")) {
                    jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "NO", cn);
                    jdFPr.setVisible(true);
                } else {
                    jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "SI", cn);
                    jdFPr.setVisible(true);
                }
                break;
            case "Socio":
                formSoc = new jdSociedadesPersonas(null, true, tipoOperacion, tipoPersona, nombrePersona, idPersona,Idioma, cn);
                formSoc.setVisible(true);
                break;
            case "Capturista Beneficio Humedo":
                jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Beneficio", cn);
                jdAB.setVisible(true);
                break;
        }
    }

    public void editarDetalles(String formulario, String item) {

        switch (formulario) {
            case "Productor":
                switch (item) {
                    case "Detalles Asignacion":

                        if (valorTB.equals("true")) {

                            if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "' ") == null
                                    || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'").equals("")) {
                                jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "NO", cn);
                                jdFPr.setVisible(true);
                            } else {
                                jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "SI", cn);
                                jdFPr.setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Puesto No Asignado");
                        }

                        break;

                    case "Parcelas":

                        if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'") == null
                                || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'").equals("")) {
                            jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "NO", cn);
                            jdFPr.setVisible(true);
                        } else {
                            jdFormularioParcelas jdFP = new jdFormularioParcelas(null, true, idPersona, tipoPersona, "", Idioma, cn);
                            jdFP.setVisible(true);
                        }

                        break;
                }
                break;

            case "Transportista":
                switch (item) {
                    case "Detalles Asignacion":
                        JOptionPane.showMessageDialog(null, "Detalle Transportista");
                    case "Vehiculos":
                        JOptionPane.showMessageDialog(null, "Vehichulos Transportista");
                        break;
                }
                break;

            case "Capturista Beneficio Humedo":

                switch (item) {
                    case "Detalles Asignacion":
                        if (valorTB.equals("true")) {
                            jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Beneficio", cn);
                            jdAB.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Puesto No Asignado");
                        }
                        break;
                }

                break;

            case "Capturista Recepcion":

                switch (item) {
                    case "Detalles Asignacion":
                        if (valorTB.equals("true")) {
                            jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Recepcion", cn);
                            jdAB.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Puesto No Asignado");
                        }
                        break;
                }

                break;

            case "Socio":
                switch (item) {
                    case "Detalles Asignacion":
                        formSoc = new jdSociedadesPersonas(null, true, tipoOperacion, tipoPersona, nombrePersona, idPersona,Idioma, cn);
                        formSoc.setVisible(true);
                        break;
                }

                break;
        }

    }*/
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

        menuProductor = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuCapturistaR = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuCapturistaB = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuSocio = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuEncargadoR = new javax.swing.JPopupMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        menuEncargadoB = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labelPersona = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        checkProd = new javax.swing.JCheckBox();
        checkSocio = new javax.swing.JCheckBox();
        checkCapturistaR = new javax.swing.JCheckBox();
        checkEncargadoR = new javax.swing.JCheckBox();
        checkTransportista = new javax.swing.JCheckBox();
        checkEncargadoB = new javax.swing.JCheckBox();
        checkCapturistaB = new javax.swing.JCheckBox();
        checkEnlace = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();

        menuProductor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuProductorMouseClicked(evt);
            }
        });

        jMenuItem1.setText("Codigo Productor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuProductor.add(jMenuItem1);

        jMenuItem2.setText("Parcelas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuProductor.add(jMenuItem2);

        jMenuItem3.setText("Asignar Recepcion");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuCapturistaR.add(jMenuItem3);

        jMenuItem4.setText("Asignar Beneficio");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuCapturistaB.add(jMenuItem4);

        jMenuItem5.setText("Sociedades");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuSocio.add(jMenuItem5);

        jMenuItem6.setText("Asignar Recepcion");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuEncargadoR.add(jMenuItem6);

        jMenuItem7.setText("Asignar Beneficio");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuEncargadoB.add(jMenuItem7);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Asignaciones de:");

        labelPersona.setText("-");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        checkProd.setText("Productor");
        checkProd.setComponentPopupMenu(menuProductor);
        checkProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkProdActionPerformed(evt);
            }
        });

        checkSocio.setText("Socio");
        checkSocio.setComponentPopupMenu(menuSocio);
        checkSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSocioActionPerformed(evt);
            }
        });

        checkCapturistaR.setText("Capturista Recepción");
        checkCapturistaR.setComponentPopupMenu(menuCapturistaR);
        checkCapturistaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCapturistaRActionPerformed(evt);
            }
        });

        checkEncargadoR.setText("Encargado Recepción");
        checkEncargadoR.setComponentPopupMenu(menuEncargadoR);
        checkEncargadoR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEncargadoRActionPerformed(evt);
            }
        });

        checkTransportista.setText("Transportista");
        checkTransportista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTransportistaActionPerformed(evt);
            }
        });

        checkEncargadoB.setText("Encargado Beneficio");
        checkEncargadoB.setComponentPopupMenu(menuEncargadoB);
        checkEncargadoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEncargadoBActionPerformed(evt);
            }
        });

        checkCapturistaB.setText("Capturista Beneficio");
        checkCapturistaB.setComponentPopupMenu(menuCapturistaB);
        checkCapturistaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCapturistaBActionPerformed(evt);
            }
        });

        checkEnlace.setText("Enlace Comercial");
        checkEnlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEnlaceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkCapturistaR)
                    .addComponent(checkCapturistaB)
                    .addComponent(checkSocio)
                    .addComponent(checkTransportista))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkEnlace)
                    .addComponent(checkProd)
                    .addComponent(checkEncargadoB)
                    .addComponent(checkEncargadoR))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkSocio)
                    .addComponent(checkProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkCapturistaR)
                    .addComponent(checkEncargadoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkCapturistaB)
                    .addComponent(checkEncargadoB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkTransportista)
                    .addComponent(checkEnlace))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(labelPersona))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPersona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    String valorTB = "", asignacion = "", item2;
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // Abrir codigo de productor
        if (!checkProd.isSelected()) {
            JOptionPane.showMessageDialog(null, "Asignacion No Registrada");
        } else {
            if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "' ") == null
                    || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'").equals("")) {
                jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "NO", Idioma, cn);
                jdFPr.setVisible(true);
            } else {
                jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "SI", Idioma, cn);
                jdFPr.setVisible(true);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // Abrir formulario de parcelas
        if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'") == null
                || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'").equals("")) {
            jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "NO", Idioma, cn);
            jdFPr.setVisible(true);
        } else {
            jdFormularioParcelas jdFP = new jdFormularioParcelas(null, true, idPersona, tipoPersona, "", Idioma, cn);
            jdFP.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuProductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuProductorMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_menuProductorMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void checkSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSocioActionPerformed
        // TODO add your handling code here:
        if (checkSocio.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Socio', '" + checkSocio.getText() + "', " + tipoPersona + " )");

            formSoc = new jdSociedadesPersonas(null, true, tipoOperacion, tipoPersona, nombrePersona, idPersona, Idioma, cn);
            formSoc.setVisible(true);
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Socio' ");
        }
    }//GEN-LAST:event_checkSocioActionPerformed

    private void checkProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkProdActionPerformed
        // TODO add your handling code here:
        if (!checkSocio.isSelected()) {
            JOptionPane.showMessageDialog(null, "Asigna Sociedad");
            checkProd.setSelected(false);
        } else {
            if (checkProd.isSelected()) {
                mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Productor', '" + checkProd.getText() + "', " + tipoPersona + " )");

                if (mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "' ") == null
                        || mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona + " and tipoPersona='" + tipoPersona + "'").equals("")) {
                    jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "NO", Idioma, cn);
                    jdFPr.setVisible(true);
                } else {
                    jdFormularioProductor jdFPr = new jdFormularioProductor(null, true, idPersona, nombrePersona, tipoPersona, "SI", Idioma, cn);
                    jdFPr.setVisible(true);
                }

            } else {
                mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Productor' ");
            }
        }
    }//GEN-LAST:event_checkProdActionPerformed

    private void checkEnlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEnlaceActionPerformed
        // TODO add your handling code here:
        if (checkEnlace.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Enlace Comercial','" + checkEnlace.getText() + "', " + tipoPersona + " )");
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Enlace Comercial' ");
        }
    }//GEN-LAST:event_checkEnlaceActionPerformed

    private void checkCapturistaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCapturistaRActionPerformed
        // TODO add your handling code here:
        if (checkCapturistaR.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Capturista Recepcion','" + checkCapturistaR.getText() + "', " + tipoPersona + " )");
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Capturista Recepcion' ");
        }
    }//GEN-LAST:event_checkCapturistaRActionPerformed

    private void checkEncargadoRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEncargadoRActionPerformed
        // TODO add your handling code here:
        if (checkEncargadoR.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Encargado Recepcion','" + checkEncargadoR.getText() + "', " + tipoPersona + " )");
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Encargado Recepcion' ");
        }
    }//GEN-LAST:event_checkEncargadoRActionPerformed

    private void checkCapturistaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCapturistaBActionPerformed
        // TODO add your handling code here:
        if (checkCapturistaB.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Capturista Beneficio','" + checkCapturistaB.getText() + "', " + tipoPersona + " )");
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Capturista Beneficio' ");
        }
    }//GEN-LAST:event_checkCapturistaBActionPerformed

    private void checkEncargadoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEncargadoBActionPerformed
        // TODO add your handling code here:
        if (checkEncargadoB.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Encargado Beneficio','" + checkEncargadoB.getText() + "', " + tipoPersona + " )");
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Encargado Beneficio' ");
        }
    }//GEN-LAST:event_checkEncargadoBActionPerformed

    private void checkTransportistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTransportistaActionPerformed
        // TODO add your handling code here:
        if (checkTransportista.isSelected()) {
            mdb.insertarBasicos("insert into asignacionespersona values(null," + idPersona + ", 'Transportista','" + checkTransportista.getText() + "', " + tipoPersona + " )");
        } else {
            mdb.actualizarBasicos("delete from asignacionespersona where id_persona=" + idPersona + " and codigo='Transportista' ");
        }
    }//GEN-LAST:event_checkTransportistaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (checkCapturistaR.isSelected()) {
            jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Recepcion", "Capturista", Idioma, cn);
            jdAB.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Sin asignar");
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if (checkCapturistaB.isSelected()) {
            jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Beneficio", "Capturista", Idioma, cn);
            jdAB.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Sin asignar");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        formSoc = new jdSociedadesPersonas(null, true, tipoOperacion, tipoPersona, nombrePersona, idPersona, Idioma, cn);
        formSoc.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        if (checkEncargadoB.isSelected()) {
            jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Beneficio", "Encargado", Idioma, cn);
            jdAB.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Sin asignar");
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        if (checkEncargadoR.isSelected()) {
            jdAsignarBeneficio jdAB = new jdAsignarBeneficio(null, true, idPersona, nombrePersona, "Recepcion", "Encargado", Idioma, cn);
            jdAB.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Sin asignar");
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdAsignacionesPersonas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkCapturistaB;
    private javax.swing.JCheckBox checkCapturistaR;
    private javax.swing.JCheckBox checkEncargadoB;
    private javax.swing.JCheckBox checkEncargadoR;
    private javax.swing.JCheckBox checkEnlace;
    private javax.swing.JCheckBox checkProd;
    private javax.swing.JCheckBox checkSocio;
    private javax.swing.JCheckBox checkTransportista;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelPersona;
    private javax.swing.JPopupMenu menuCapturistaB;
    private javax.swing.JPopupMenu menuCapturistaR;
    private javax.swing.JPopupMenu menuEncargadoB;
    private javax.swing.JPopupMenu menuEncargadoR;
    private javax.swing.JPopupMenu menuProductor;
    private javax.swing.JPopupMenu menuSocio;
    // End of variables declaration//GEN-END:variables
}
