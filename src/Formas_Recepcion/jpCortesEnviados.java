/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Recepcion;

import Idioma.Propiedades;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Valdez
 */
public class jpCortesEnviados extends javax.swing.JPanel {

    /**
     * Creates new form jpRecibos
     */
    Connection cn;
    metodosDatosBasicos mdb;
    DefaultTableModel modelo, modelo2;
    jdRecibos jdR;
    String recepcion, idSociedad;
    String idRecibo = "", idLote = "", idRecepcion, Idioma;
    Propiedades idioma;

    public jpCortesEnviados(Connection cn, String Idioma, String recepcion) throws ParseException {
        initComponents();

        this.cn = cn;
        this.Idioma = Idioma;
        mdb = new metodosDatosBasicos(cn);
        idioma = new Propiedades(Idioma);
        modelo = (DefaultTableModel) tablaBoletas.getModel();

        this.recepcion = recepcion;
        idRecepcion = mdb.devuelveUnDato("select id from recepciones where idRecepcion='" + recepcion + "'");
        idSociedad = mdb.devuelveUnDato("select idSociedad from recepciones where idRecepcion='" + recepcion + "' ");

        traductor();
        llenarTabla();
        // sumarColumnas();
    }

    public void traductor() {
        jLabel1.setText(idioma.getProperty("IdCorte"));
        jLabel2.setText(idioma.getProperty("TituloBoleta"));
        jLabel11.setText(idioma.getProperty("Desde"));
        jLabel12.setText(idioma.getProperty("Hasta"));
        jButton6.setText(idioma.getProperty("Buscar"));
        jLabel4.setText(idioma.getProperty("CabeceraBoleta"));
        jLabel3.setText(idioma.getProperty("CortesDeLaBoleta"));
        jLabel7.setText(idioma.getProperty("Situacion"));
        jLabel8.setText(idioma.getProperty("TotalCortes"));
        jLabel9.setText(idioma.getProperty("TotalSacos"));
        jLabel10.setText(idioma.getProperty("TotalKilos"));
        jButton1.setText(idioma.getProperty("Exportar"));
        jButton4.setText(idioma.getProperty("Cerrar"));

        tablaBoletas.getColumnModel().getColumn(0).setHeaderValue(idioma.getProperty("TituloBoleta"));
        tablaBoletas.getColumnModel().getColumn(1).setHeaderValue(idioma.getProperty("Fecha"));
        tablaBoletas.getColumnModel().getColumn(2).setHeaderValue(idioma.getProperty("Origen"));
        tablaBoletas.getColumnModel().getColumn(3).setHeaderValue(idioma.getProperty("Destino"));
        tablaBoletas.getColumnModel().getColumn(4).setHeaderValue(idioma.getProperty("FormaDeCafe"));
        tablaBoletas.getColumnModel().getColumn(5).setHeaderValue(idioma.getProperty("TotalSacos"));
        tablaBoletas.getColumnModel().getColumn(6).setHeaderValue(idioma.getProperty("TotalKilos"));
        tablaBoletas.getColumnModel().getColumn(7).setHeaderValue(idioma.getProperty("CostoAcumulado"));
        tablaBoletas.getColumnModel().getColumn(8).setHeaderValue(idioma.getProperty("BoletaManual"));
        tablaBoletas.getColumnModel().getColumn(9).setHeaderValue(idioma.getProperty("FechaDeBoletaManual"));
        tablaBoletas.getColumnModel().getColumn(10).setHeaderValue(idioma.getProperty("Dividido"));

        tablaCortes.getColumnModel().getColumn(0).setHeaderValue(idioma.getProperty("IdCorte"));
        tablaCortes.getColumnModel().getColumn(1).setHeaderValue(idioma.getProperty("Fecha"));
        tablaCortes.getColumnModel().getColumn(2).setHeaderValue(idioma.getProperty("FormaDeCafe"));
        tablaCortes.getColumnModel().getColumn(3).setHeaderValue(idioma.getProperty("Sacos"));
        tablaCortes.getColumnModel().getColumn(4).setHeaderValue(idioma.getProperty("KgNetos"));
        tablaCortes.getColumnModel().getColumn(5).setHeaderValue(idioma.getProperty("CostoAcumulado"));
        tablaCortes.getColumnModel().getColumn(6).setHeaderValue(idioma.getProperty("SacosEnviados"));
        tablaCortes.getColumnModel().getColumn(7).setHeaderValue(idioma.getProperty("KgEnviados"));
    }

    public void llenarTabla() throws ParseException {
        limpiar(tablaBoletas);

        mdb.cargarInformacion2(modelo, 11,
                "SELECT\n"
                + "    b.idBoleta,\n"
                + "    b.fecha,\n"
                + "    re.idRecepcion,\n"
                + "    bh.nombre,\n"
                + "    c.formaCafe,\n"
                + "    b.totalSacos,\n"
                + "    b.totalKg,\n"
                + "    c.costoAcumulado,\n"
                + "    b.idBoletaManual,\n"
                + "    b.fechaBoletaManual,\n"
                + "    FALSE\n"
                + "FROM\n"
                + "    boletasalidareceptor b\n"
                + "LEFT JOIN cortesdeldia c ON\n"
                + "    (c.idLote = b.idLote)\n"
                + "LEFT JOIN recepciones re ON\n"
                + "    (b.origen = re.id)\n"
                + "LEFT JOIN beneficioshumedos bh ON\n"
                + "    (bh.id = b.destino)\n"
                + "WHERE\n"
                + "    b.idRecepcion = " + idRecepcion + "\n"
                + "GROUP BY\n"
                + "    b.idBoleta\n"
                + "ORDER BY\n"
                + "    b.id");
        cambiarMesLetra(tablaBoletas);
    }

    public void cambiarMesLetra(JTable tabla) {
        try {
            for (int i = 0; i < tabla.getRowCount(); i++) {

                String fecha = tabla.getValueAt(i, 1) + "";
                String fechaBoleta = tabla.getValueAt(i, 10) + "";

                String[] fecha2 = fecha.split("-");
                String[] fecha2B = fechaBoleta.split("-");

                String año = fecha2[0];
                String mes = fecha2[1];
                String dia = fecha2[2];

                String añoB = fecha2B[0];
                String mesB = fecha2B[1];
                String diaB = fecha2B[2];

                switch (mes) {
                    case "01":
                        mes = "Ene";
                        break;
                    case "02":
                        mes = "Feb";
                        break;
                    case "03":
                        mes = "Mar";
                        break;
                    case "04":
                        mes = "Abr";
                        break;
                    case "05":
                        mes = "May";
                        break;
                    case "06":
                        mes = "Jun";
                        break;
                    case "07":
                        mes = "Jul";
                        break;
                    case "08":
                        mes = "Ago";
                        break;
                    case "09":
                        mes = "Sep";
                        break;
                    case "10":
                        mes = "Oct";
                        break;
                    case "11":
                        mes = "Nov";
                        break;
                    case "12":
                        mes = "Dic";
                        break;
                    default:
                        mes = "Invalid month";
                        break;
                }

                switch (mesB) {
                    case "01":
                        mesB = "Ene";
                        break;
                    case "02":
                        mesB = "Feb";
                        break;
                    case "03":
                        mesB = "Mar";
                        break;
                    case "04":
                        mesB = "Abr";
                        break;
                    case "05":
                        mesB = "May";
                        break;
                    case "06":
                        mesB = "Jun";
                        break;
                    case "07":
                        mesB = "Jul";
                        break;
                    case "08":
                        mesB = "Ago";
                        break;
                    case "09":
                        mesB = "Sep";
                        break;
                    case "10":
                        mesB = "Oct";
                        break;
                    case "11":
                        mesB = "Nov";
                        break;
                    case "12":
                        mesB = "Dic";
                        break;
                    default:
                        mesB = "Invalid month";
                        break;
                }

                fechaBoleta = añoB + "-" + mesB + "-" + diaB;
                tabla.setValueAt(fechaBoleta, i, 10);

                fecha = año + "-" + mes + "-" + dia;
                tabla.setValueAt(fecha, i, 1);
            }
        } catch (Exception e) {

        }
    }

    public void sumarColumnas() {

        float contadorKg = 0, contadorSacos = 0;
        contadorProductores.setText(tablaCortes.getRowCount() + "");

        for (int i = 0; i < tablaCortes.getRowCount(); i++) {
            contadorSacos = contadorSacos + Float.parseFloat(tablaCortes.getValueAt(i, 3) + "");
            contadorKg = contadorKg + Float.parseFloat(tablaCortes.getValueAt(i, 4) + "");
        }
        this.contadorKg.setText(contadorKg + "");
        this.contadorSacos.setText(contadorSacos + "");
    }

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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        reciboItem = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        boletaItem = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBoletas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdCorte = new javax.swing.JTextField();
        txtBoleta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jdFecha1 = new com.toedter.calendar.JDateChooser();
        jdFecha2 = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        contadorProductores = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        contadorSacos = new javax.swing.JLabel();
        contadorKg = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCortes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        reciboItem.setText("Recibos");
        reciboItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reciboItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(reciboItem);

        boletaItem.setText("Abrir Boleta");
        boletaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boletaItemActionPerformed(evt);
            }
        });
        jPopupMenu2.add(boletaItem);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaBoletas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Boleta", "Fecha", "Origen", "Destino", "Forma de Café", "Total Sacos", "Total Kg", "Costo Acumulado", "Boleta Manual", "Fecha Boleta Manual", "Dividido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBoletas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaBoletas.setComponentPopupMenu(jPopupMenu2);
        tablaBoletas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaBoletasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBoletas);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel1.setText("Id Corte");

        txtIdCorte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdCorteKeyReleased(evt);
            }
        });

        txtBoleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBoletaKeyReleased(evt);
            }
        });

        jLabel2.setText("Boleta");

        jLabel11.setText("Desde");

        jLabel12.setText("Hasta");

        jButton6.setText("Consultar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jdFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jdFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jdFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(26, 26, 26))
            .addComponent(jdFecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel7.setText("Situacion");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos", "Inactivos", "Todos" }));

        jButton1.setText("Exportar");

        jButton4.setText("Cerrar");

        contadorProductores.setText("-");

        jLabel8.setText("Total Cortes");

        jLabel9.setText("Total Sacos");

        jLabel10.setText("Total Kg");

        contadorSacos.setText("-");

        contadorKg.setText("-");

        tablaCortes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Corte", "Fecha", "Forma de Café", "Sacos", "Kg Neto", "Costo Acumulado", "Sacos Enviados", "Kilos Enviados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCortes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaCortes.setComponentPopupMenu(jPopupMenu1);
        tablaCortes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCortesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCortes);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cortes de la Boleta");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Boletas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(contadorProductores))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(contadorSacos))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(contadorKg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(contadorSacos)
                                        .addComponent(contadorKg))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(contadorProductores)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton4))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
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
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdCorteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdCorteKeyReleased
        // TODO add your handling code here:
        limpiar(tablaBoletas);
        mdb.cargarInformacion2(modelo, 15,
                "SELECT\n"
                + "    b.idBoleta,\n"
                + "    b.fecha,\n"
                + "    re.idRecepcion,\n"
                + "    bh.nombre,\n"
                + "    c.formaCafe,\n"
                + "    b.totalSacos,\n"
                + "    b.totalKg,\n"
                + "    c.costoAcumulado,\n"
                + "    b.idBoletaManual,\n"
                + "    b.fechaBoletaManual,\n"
                + "    FALSE\n"
                + "FROM\n"
                + "    boletasalidareceptor b\n"
                + "LEFT JOIN cortesdeldia c ON\n"
                + "    (c.idLote = b.idLote)\n"
                + "LEFT JOIN recepciones re ON\n"
                + "    (b.origen = re.id)\n"
                + "LEFT JOIN beneficioshumedos bh ON\n"
                + "    (bh.id = b.destino)\n"
                + "WHERE\n"
                + "    b.idRecepcion = " + idRecepcion + " and b.idLote like '" + txtIdCorte.getText() + "%'\n"
                + "GROUP BY\n"
                + "    b.idBoleta\n"
                + "ORDER BY\n"
                + "    b.id");
        cambiarMesLetra(tablaBoletas);
    }//GEN-LAST:event_txtIdCorteKeyReleased

    private void txtBoletaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBoletaKeyReleased
        // TODO add your handling code here:
        limpiar(tablaBoletas);
        mdb.cargarInformacion2(modelo, 11,"SELECT\n"
                + "    b.idBoleta,\n"
                + "    b.fecha,\n"
                + "    re.idRecepcion,\n"
                + "    bh.nombre,\n"
                + "    c.formaCafe,\n"
                + "    b.totalSacos,\n"
                + "    b.totalKg,\n"
                + "    c.costoAcumulado,\n"
                + "    b.idBoletaManual,\n"
                + "    b.fechaBoletaManual,\n"
                + "    FALSE\n"
                + "FROM\n"
                + "    boletasalidareceptor b\n"
                + "LEFT JOIN cortesdeldia c ON\n"
                + "    (c.idLote = b.idLote)\n"
                + "LEFT JOIN recepciones re ON\n"
                + "    (b.origen = re.id)\n"
                + "LEFT JOIN beneficioshumedos bh ON\n"
                + "    (bh.id = b.destino)\n"
                + "WHERE\n"
                + "    b.idRecepcion = " + idRecepcion + " and b.idBoleta like '" + txtBoleta.getText() + "%'\n"
                + "GROUP BY\n"
                + "    b.idBoleta\n"
                + "ORDER BY\n"
                + "    b.id" );
        cambiarMesLetra(tablaBoletas);
    }//GEN-LAST:event_txtBoletaKeyReleased
    String idBoleta;
    private void tablaBoletasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaBoletasMouseClicked
        // TODO add your handling code here:
        limpiar(tablaCortes);
        modelo2 = (DefaultTableModel) tablaCortes.getModel();
        //idLote = idCorte
        //idLote = tablaCortes.getValueAt(tablaCortes.getSelectedRow(), 0) + "";
        idBoleta = tablaBoletas.getValueAt(tablaBoletas.getSelectedRow(), 0) + "";
        mdb.cargarInformacion2(modelo2, 8, "SELECT c.idLote, c.fechaCreacion, c.formaCafe, c.sacos, c.kg, c.costoAcumulado, b.sacosEnviados, b.kilosEnviados\n"
                + "from cortesdeldia c\n"
                + "inner join boletasalidareceptor b on(c.idLote=b.idLote)\n"
                + "where b.idBoleta='" + idBoleta + "' ");

        if (evt.getClickCount() == 2) {
            jdBoletaSalidaReceptorVisor jdBSV;
            try {
                jdBSV = new jdBoletaSalidaReceptorVisor(null, true, idBoleta, Idioma, cn);
                jdBSV.setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(jpCortesEnviados.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sumarColumnas();
        idRecibo = "";
    }//GEN-LAST:event_tablaBoletasMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(jdFecha1.getDate());
        String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(jdFecha2.getDate());
        limpiar(tablaBoletas);

        mdb.cargarInformacion2(modelo, 15,
                "SELECT b.idLote, b.fecha, b.origen, b.destino, c.formaCafe, c.sacos, c.kg, c.costoAcumulado, b.idBoleta, b.idBoletaManual, b.fechaBoletaManual, sacosEnviados, kilosEnviados, b.estatus, false\n"
                + "FROM boletasalidareceptor b \n"
                + "INNER JOIN cortesdeldia c on (c.idLote=b.idLote) "
                + "WHERE idSociedad=" + idSociedad + " and b.fecha between '" + fecha1 + "' and '" + fecha2 + "' order by b.id");
        cambiarMesLetra(tablaBoletas);
    }//GEN-LAST:event_jButton6ActionPerformed
    String idCorte = "";
    private void tablaCortesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCortesMouseClicked
        // TODO add your handling code here:
        idCorte = tablaCortes.getValueAt(tablaCortes.getSelectedRow(), 0) + "";

        if (evt.getClickCount() == 2) {
            jdRecibosDelCorte jdRDC = new jdRecibosDelCorte(null, true, idCorte,Idioma, cn);
            jdRDC.setVisible(true);
        }
    }//GEN-LAST:event_tablaCortesMouseClicked

    private void reciboItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reciboItemActionPerformed
        // TODO add your handling code here:
        jdRecibosDelCorte jdRDC = new jdRecibosDelCorte(null, true, idCorte,Idioma, cn);
        jdRDC.setVisible(true);
    }//GEN-LAST:event_reciboItemActionPerformed

    private void boletaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boletaItemActionPerformed
        // TODO add your handling code here:
        jdBoletaSalidaReceptorVisor jdBSV;
        try {
            jdBSV = new jdBoletaSalidaReceptorVisor(null, true, idBoleta, Idioma, cn);
            jdBSV.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(jpCortesEnviados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_boletaItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem boletaItem;
    private javax.swing.JLabel contadorKg;
    private javax.swing.JLabel contadorProductores;
    private javax.swing.JLabel contadorSacos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdFecha1;
    private com.toedter.calendar.JDateChooser jdFecha2;
    private javax.swing.JMenuItem reciboItem;
    private javax.swing.JTable tablaBoletas;
    private javax.swing.JTable tablaCortes;
    private javax.swing.JTextField txtBoleta;
    private javax.swing.JTextField txtIdCorte;
    // End of variables declaration//GEN-END:variables
}
