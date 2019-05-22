/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Recepcion;

import Metodos_Configuraciones.metodosRecepcion;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class jdRecibosDelCorte extends javax.swing.JDialog {

    /**
     * Creates new form jdRecibosDelCorte
     */
    Connection cn;
    metodosRecepcion mdr;
    DefaultTableModel modelo;
    
    public jdRecibosDelCorte(java.awt.Frame parent, boolean modal, String idCorte, Connection cn) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        this.cn=cn;
    
        mdr = new metodosRecepcion(cn);
        modelo = (DefaultTableModel) tablaRecibos.getModel();
        
        jLabel2.setText(idCorte);
        
        mdr.cargarInformacion2(modelo, 21, "select r.id, r.folioManual,r.idLote, r.fechaRecepcion, pf.Nombre, pf.ApellidoPaterno, pf.ApellidoMaterno,\n"
                + "pm.RazonSocial, p.nombre, r.formaCafe, r.sacos, r.kgRecibidos, r.totalBruto, r.retencion, r.total, r.verdes, r.inmaduros, r.brocados, r.calificacion, r.personaEntrego, r.observaciones\n"
                + "from recibos r\n"
                + "inner join personam pm on ( pm.ID=r.idSociedad)\n"
                + "inner join personaf pf on ( pf.ID=r.idPersona)\n"
                + "inner join parcelas p on ( p.id=r.idParcela) "
                + "where r.idLote='" + idCorte + "'");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRecibos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recibos");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Recibos del Corte");

        tablaRecibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Folio Manual", "Id Lote", "Fecha de Recepcion", "Nombre", "Apellido Paterno", "Apellido Materno", "Sociedad", "Parcela", "Forma Cafe", "Sacos", "Kg Recibidos", "Total Bruto", "Retencion", "Total", "Verdes", "Inmaduros", "Brocados", "Calificacion", "Entregó", "Observaciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRecibos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaRecibos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRecibosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaRecibos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
String idRecibo;
    private void tablaRecibosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRecibosMouseClicked
        // TODO add your handling code here:
        idRecibo = tablaRecibos.getValueAt(tablaRecibos.getSelectedRow(), 0) + "";

        if (evt.getClickCount() == 2) {
            jdRecibos jdR = new jdRecibos(null, true, idRecibo, "2","", "", cn);
            jdR.setVisible(true);
        }
    }//GEN-LAST:event_tablaRecibosMouseClicked

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
            java.util.logging.Logger.getLogger(jdRecibosDelCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdRecibosDelCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdRecibosDelCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdRecibosDelCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
      /*  java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdRecibosDelCorte dialog = new jdRecibosDelCorte(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaRecibos;
    // End of variables declaration//GEN-END:variables
}
