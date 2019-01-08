/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormasInternas;

import Conexion.Conexion;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import sun.misc.IOUtils;

/**
 *
 * @author Carlos Valdez
 */
public class pruebas extends javax.swing.JFrame {

    /**
     * Creates new form pruebas
     */
    Connection cn;
    metodosDatosBasicos mdb;

    public pruebas() {
        initComponents();
        cn = (new Conexion()).conectar();
        mdb = new metodosDatosBasicos(cn);
    }

    //con este obtienes el documento y retorna un file
    public File cargarDoc() {
        JFileChooser archivo = new JFileChooser();
        FileNameExtensionFilter filtro
                = new FileNameExtensionFilter("Formatos de Archivo Documentos", "docx", "pdf", "jpg", "jpeg", "png");
        archivo.addChoosableFileFilter(filtro);
        archivo.setFileFilter(filtro);
        int ventana = archivo.showOpenDialog(null);
        File file = null;
        if (ventana == JFileChooser.APPROVE_OPTION) {

            file = archivo.getSelectedFile();

        } else {
            return null;
        }
        return file;
    }

    public void code(String filePath) throws FileNotFoundException, IOException, SQLException {
        String newFileName = "prueba.pdf";

        //Codificar
        byte[] input_file = Files.readAllBytes(Paths.get(filePath));
        byte[] encodedBytes = Base64.getEncoder().encode(input_file);
        String encodedString = new String(encodedBytes);

        System.out.println(encodedString);
        PreparedStatement cmd = cn.prepareStatement("insert into fotosprueba values (null,'" + encodedString + "')");

        cmd.execute();
        cmd.close();
        //Devolver
        /*byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());

        FileOutputStream fos = new FileOutputStream("C:\\prueba\\" + newFileName);
        fos.write(decodedBytes);
        fos.flush();
        fos.close();*/

    }

    public void decode(String encodedString) throws IOException {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());

        FileOutputStream fos = new FileOutputStream("C:\\prueba\\pruebaDecode.pdf");
        fos.write(decodedBytes);
        fos.flush();
        fos.close();

    }

//aqui recibe un file y los transforma y no inserta    
    //File pdfCV;
    public void InsertArchivo(File pdfCV) {
        try {
            //metodosDatosBasicos mdb = new metodosDatosBasicos(cn);
            FileInputStream doc = new FileInputStream(pdfCV);

            // mdb.insertarBasicos("insert into fotosprueba values (null, '"+doc+"')");
            PreparedStatement cmd = cn.prepareStatement("insert into fotosprueba values (null,?)");

            cmd.setBlob(1, doc);

            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }

    }

    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        // initialize string buffer to hold contents of file
        StringBuffer fileContentStr = new StringBuffer("");
        BufferedReader reader = null;
        try {
            // initialize buffered reader  
            reader = new BufferedReader(new FileReader(filePath));
            String line = null;
            // read lines of file
            while ((line = reader.readLine()) != null) {
                //append line to string buffer
                fileContentStr.append(line).append("\n");
            }
            // convert string to byte array
            fileContent = fileContentStr.toString().trim().getBytes();
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return fileContent;
    }

    private void Pdf(Blob archivo) throws FileNotFoundException {

        try {
            String pathname = "C:\\prueba\\pruebaPDF2.pdf";
            File file = new File(pathname);
            FileOutputStream output = new FileOutputStream(file);

            InputStream inStream = archivo.getBinaryStream();
            int length = -1;
            int size = (int) archivo.length();
            byte[] buffer = new byte[size];
            while ((length = inStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar: " + e);
        }
    }

    public File[] recuperar() {

        File[] file1 = new File[1];

        try {
            CallableStatement cmd = cn.prepareCall("select * from fotosprueba where id=6");
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Pdf(rs.getBlob(1));
            }
            cmd.close();
            return file1;

        } catch (Exception e) {
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        ruta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Normal", "Bolean"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Convertir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        area.setColumns(20);
        area.setRows(5);
        jScrollPane2.setViewportView(area);

        jButton3.setText("Abrir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Taco");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Decode");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ruta)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(40, 40, 40)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addGap(28, 28, 28)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.addRow(new Object[]{"Prueba 1", true});
    }//GEN-LAST:event_jButton1ActionPerformed
    File fichero = null;
    private JPanel contentPane;

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.pdf", "pdf");
        file.setFileFilter(filtro);

        int seleccion = file.showOpenDialog(contentPane);
        //Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            fichero = file.getSelectedFile();
            //Ecribe la ruta del fichero seleccionado en el campo de texto
            ruta.setText(fichero.getAbsolutePath());
            try {
                code(fichero.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        metodosDatosBasicos mdb = new metodosDatosBasicos(cn);

        String cadena = null;
        try {
            cadena = new String(convertFileContentToBlob(ruta.getText()));
        } catch (IOException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }

        area.setText(cadena);

        mdb.insertarBasicos("insert into fotosprueba values (null, '" + cadena + "')");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        InsertArchivo(cargarDoc());
        /*       Statement statement = null;
        //       ResultSet rs = null;
        try {

//            statement = conectar.createStatement();
//            rs = statement.executeQuery("SELECT fileName,blobFile FROM tblBlobFiles");
            String sql = "select foto from fotosprueba where id=1";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            Blob blob = null;
            if (rs.next()) {

                String filename = "PruebaArchivoFoto";
                blob = rs.getBlob(1);
                InputStream is = blob.getBinaryStream();
                FileOutputStream fos = new FileOutputStream("C:\\prueba" + "\\" + filename);

                int b = 0;
                while ((b = is.read()) != -1) {
                    fos.write(b);
                }
            }

            area.setText(blob.toString());
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println(e);
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println(e);
        }
         */
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cargarDoc();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        recuperar();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // TODO add your handling code here:
            decode(mdb.devuelveUnDato("select foto from fotosprueba where id=7"));
        } catch (IOException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(pruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pruebas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pruebas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField ruta;
    // End of variables declaration//GEN-END:variables
}
