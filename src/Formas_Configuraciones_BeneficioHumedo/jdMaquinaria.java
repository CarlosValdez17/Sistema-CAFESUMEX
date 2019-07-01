/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Configuraciones_BeneficioHumedo;

import Formas_Configuraciones_Recepcion.*;
import Formas_Configuraciones_DatosBasicos.*;
import MetodosGenerales.MiRender;
import Metodos_Configuraciones.metodosDatosBasicos;
import Metodos_Configuraciones.validaConfi;
import java.sql.Connection;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Carlos Valdez
 */
public class jdMaquinaria extends javax.swing.JDialog {

    /**
     * Creates new form jdEstado
     */
    jpMaquinaria jpR;
    String clave, tipo, idMaquinaria, nombre, desc;
    int entradas = 0, salidas = 0;
    DefaultTableModel modelo, modelo2, modelo3;
    metodosDatosBasicos mdb;
    validaConfi valiConf;
    Connection cn;

    public jdMaquinaria(java.awt.Frame parent, boolean modal, String tipo, String dato1, String dato2, String dato3, String dato4, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cn = c;
        this.tipo = tipo;
        clave = dato1;
        idMaquinaria = dato2;
        nombre = dato3;
        desc = dato4;

        mdb = new metodosDatosBasicos(cn);
        valiConf = new validaConfi();

        jCheckBox2.setVisible(false);

        modelo = (DefaultTableModel) tablaEntradas.getModel();
        modelo2 = (DefaultTableModel) tablaSalidas.getModel();
        modelo3 = (DefaultTableModel) tablaMaquinaria.getModel();

        /* botonAñadirSalida.setVisible(false);
        botonEliminarSalida.setVisible(false);
        botonAñadirEntrada.setVisible(false);
        botonEliminarEntrada.setVisible(false);*/
        cargarCombos();
        if (tipo.equals("2")) {
            cargarDatos();
        }

        tablaEntradas.setDefaultRenderer(Object.class, new MiRender());
        tablaSalidas.setDefaultRenderer(Object.class, new MiRender());
    }

    public void cargarTablas() {
        limpiar(tablaSalidas);
        limpiar(tablaMaquinaria);
        limpiar(tablaEntradas);
        //Tabla Entradas
        mdb.cargarInformacion2(modelo, 5, "select clave, formacafe, procesocafe, estadocafe, datoEvaluar\n"
                + "from entradasmaquinaria "
                + "where idMaquinaria=" + idMaquinaria + "");

        //Tabla Salidas
        mdb.cargarInformacion2(modelo2, 5, "select clave, formacafe, procesocafe, estadocafe, datoEvaluar\n"
                + "from salidasmaquinaria "
                + "where idMaquinaria=" + idMaquinaria + "");

        //Tabla Enlaces
        mdb.cargarInformacion2(modelo3, 3, "SELECT r.claveSalida, a.Actividad, m.nombre\n"
                + "from relacionsalidamaquinarias r\n"
                + "left join actividadesbh a on(r.idActividad=a.ID)\n"
                + "left join maquinariabh m on(r.idMaquinariaRelacion=m.id) "
                + "where r.idMaquinaria=" + idMaquinaria + "");

        for (int i = 0; i < modelo2.getRowCount(); i++) {
            comboSalidas.addItem(modelo2.getValueAt(i, 0) + "");
        }

    }

    public void cargarDatos() {
        String[] datos = mdb.devolverLineaDatos("SELECT\n"
                + "    a.Actividad,\n"
                + "    m.nombre,\n"
                + "    m.clave,\n"
                + "    m.marca,\n"
                + "    m.modelo,\n"
                + "    m.serie,\n"
                + "    m.capacidad,\n"
                + "    m.tiempoevaluacion,\n"
                + "    s.Nombre,\n"
                + "    m.descripcion,\n"
                + "    m.generarLote,\n"
                + "    m.restarInmediato\n"
                + "FROM\n"
                + "    maquinariabh m\n"
                + "LEFT JOIN actividadesbh a ON\n"
                + "    (m.idActividad = a.ID)\n"
                + "LEFT JOIN sobrante s ON\n"
                + "    (m.sobrante = a.ID) "
                + "WHERE m.id=" + idMaquinaria + " ", 12).split("¬");

        comboActividades.setSelectedItem(datos[0]);
        txtNombre.setText(datos[1]);
        txtClave.setText(datos[2]);
        txtMarca.setText(datos[3]);
        txtModelo.setText(datos[4]);
        txtSerie.setText(datos[5]);
        txtCapacidad.setText(datos[6]);
        txtEvaluacion.setText(datos[7]);
        comboSobrantes.setSelectedItem(datos[8]);
        txtDescripcion.setText(datos[9]);

        if (datos[10].equals("1")) {
            jCheckBox1.setSelected(true);
            jCheckBox2.setVisible(true);
        } else {
            jCheckBox1.setSelected(false);
        }

        if (datos[11].equals("1")) {
            jCheckBox2.setSelected(true);
        } else {
            jCheckBox2.setSelected(false);
        }

        //Tabla Entradas
        mdb.cargarInformacion2(modelo, 5, "select clave, formacafe, procesocafe, estadocafe, datoEvaluar\n"
                + "from entradasmaquinaria "
                + "where idMaquinaria=" + idMaquinaria + "");

        //Tabla Salidas
        mdb.cargarInformacion2(modelo2, 5, "select clave, formacafe, procesocafe, estadocafe, datoEvaluar\n"
                + "from salidasmaquinaria "
                + "where idMaquinaria=" + idMaquinaria + "");

        //Tabla Enlaces
        mdb.cargarInformacion2(modelo3, 3, "SELECT r.claveSalida, a.Actividad, m.nombre\n"
                + "from relacionsalidamaquinarias r\n"
                + "left join actividadesbh a on(r.idActividad=a.ID)\n"
                + "left join maquinariabh m on(r.idMaquinariaRelacion=m.id) "
                + "where r.idMaquinaria=" + idMaquinaria + "");

        for (int i = 0; i < modelo2.getRowCount(); i++) {
            comboSalidas.addItem(modelo2.getValueAt(i, 0) + "");
        }

    }

    public void cargarCombos() {
        String[] datos = mdb.cargarCombos("select actividad from actividadesbh").split("¬");
        comboActividades.setModel(new DefaultComboBoxModel((Object[]) datos));

        datos = mdb.cargarCombos("select actividad from actividadesbh").split("¬");
        comboActividad.setModel(new DefaultComboBoxModel((Object[]) datos));
    }

    public void setBox(JTable tabla, TableColumn columna) {
        String[] datos = mdb.cargarCombos("select descripcion from formacafe").split("¬");
        JComboBox c = new JComboBox((Object[]) datos);

        columna.setCellEditor(new DefaultCellEditor(c));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        columna.setCellRenderer(renderer);
    }

    public void setBox2(JTable tabla, TableColumn columna) {
        String[] datos = mdb.cargarCombos("select descripcion from procesocafe").split("¬");
        JComboBox c = new JComboBox((Object[]) datos);

        columna.setCellEditor(new DefaultCellEditor(c));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        columna.setCellRenderer(renderer);
    }

    public void setBox3(JTable tabla, TableColumn columna) {
        String[] datos = mdb.cargarCombos("select descripcion from estadocafe").split("¬");
        JComboBox c = new JComboBox((Object[]) datos);

        columna.setCellEditor(new DefaultCellEditor(c));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        columna.setCellRenderer(renderer);
    }

    public void modificarEntradas(String tipo) {
        switch (tipo) {
            case "Entradas":
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    int count = i + 1;
                    tablaEntradas.setValueAt("E" + count, i, 0);
                }
                break;

            case "Salidas":
                for (int i = 0; i < modelo2.getRowCount(); i++) {
                    int count = i + 1;
                    tablaSalidas.setValueAt("S" + count, i, 0);
                }
                break;
        }

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

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboActividades = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEvaluacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboSobrantes = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaEntradas = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        botonAñadirEntrada = new javax.swing.JButton();
        botonEliminarEntrada = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaSalidas = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaMaquinaria = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        comboActividad = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        comboMaquinaria = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        comboSalidas = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        botonAñadirSalida = new javax.swing.JButton();
        botonEliminarSalida = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setText("Actividad");

        comboActividades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Clave");

        jLabel3.setText("Marca");

        jLabel4.setText("Modelo");

        jLabel5.setText("Capacidad (Ton, metr/seg)");

        jLabel6.setText("Serie");

        jLabel7.setText("Evaluar (Dias)");

        jLabel8.setText("Sobrante");

        comboSobrantes.setEnabled(false);

        jCheckBox1.setText("Generar Lote");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("¿Restar Inmediatamente?");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel9.setText("Descripcion");

        jLabel13.setText("Nombre");

        jButton10.setText("Guardar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCheckBox1)
                            .addGap(18, 18, 18)
                            .addComponent(jCheckBox2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel3))
                                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel4))
                                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel5))
                                        .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel7))
                                        .addComponent(txtEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(comboSobrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel9))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSobrantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Información General", jPanel1);

        tablaEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Forma Café", "Proceso Café", "Estado Café", "Dato a Evaluar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaEntradas);
        if (tablaEntradas.getColumnModel().getColumnCount() > 0) {
            tablaEntradas.getColumnModel().getColumn(0).setResizable(false);
            tablaEntradas.getColumnModel().getColumn(1).setResizable(false);
            tablaEntradas.getColumnModel().getColumn(1).setHeaderValue("Forma Café");
            tablaEntradas.getColumnModel().getColumn(2).setResizable(false);
            tablaEntradas.getColumnModel().getColumn(2).setHeaderValue("Proceso Café");
            tablaEntradas.getColumnModel().getColumn(3).setResizable(false);
            tablaEntradas.getColumnModel().getColumn(3).setHeaderValue("Estado Café");
            tablaEntradas.getColumnModel().getColumn(4).setResizable(false);
            tablaEntradas.getColumnModel().getColumn(4).setHeaderValue("Dato a Evaluar");
        }

        jButton4.setText("Guardar Entradas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Configuración de Entradas de Maquinaria.");

        botonAñadirEntrada.setText("Añadir Entrada");
        botonAñadirEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirEntradaActionPerformed(evt);
            }
        });

        botonEliminarEntrada.setText("Eliminar Entrada");
        botonEliminarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(botonAñadirEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminarEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonAñadirEntrada)
                        .addComponent(botonEliminarEntrada)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Entradas", jPanel4);

        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        tablaSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Forma Café", "Proceso Café", "Estado Café", "Dato a Evaluar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaSalidas);
        if (tablaSalidas.getColumnModel().getColumnCount() > 0) {
            tablaSalidas.getColumnModel().getColumn(0).setResizable(false);
            tablaSalidas.getColumnModel().getColumn(1).setResizable(false);
            tablaSalidas.getColumnModel().getColumn(1).setHeaderValue("Forma Café");
            tablaSalidas.getColumnModel().getColumn(2).setResizable(false);
            tablaSalidas.getColumnModel().getColumn(2).setHeaderValue("Proceso Café");
            tablaSalidas.getColumnModel().getColumn(3).setResizable(false);
            tablaSalidas.getColumnModel().getColumn(3).setHeaderValue("Estado Café");
            tablaSalidas.getColumnModel().getColumn(4).setResizable(false);
            tablaSalidas.getColumnModel().getColumn(4).setHeaderValue("Dato a Evaluar");
        }

        jLabel11.setText("Configuración de Salidas de Maquinaria.");

        jButton8.setText("Guardar Salidas");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        tablaMaquinaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Salida", "Actividad", "Maquinaria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablaMaquinaria);
        if (tablaMaquinaria.getColumnModel().getColumnCount() > 0) {
            tablaMaquinaria.getColumnModel().getColumn(0).setResizable(false);
            tablaMaquinaria.getColumnModel().getColumn(1).setResizable(false);
            tablaMaquinaria.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel12.setText("Relacion Actividad");

        comboActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActividadActionPerformed(evt);
            }
        });

        jButton9.setText("Añadir");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel14.setText("Maquinaria");

        jLabel15.setText("Salidas");

        jButton3.setText("Guardar Relaciones");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        botonAñadirSalida.setText("Añadir Salida");
        botonAñadirSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAñadirSalidaActionPerformed(evt);
            }
        });

        botonEliminarSalida.setText("Eliminar Salida");
        botonEliminarSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarSalidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(comboSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(comboMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 73, Short.MAX_VALUE)
                        .addComponent(jButton9))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAñadirSalida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminarSalida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jButton8)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonEliminarSalida)
                        .addComponent(botonAñadirSalida)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMaquinaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Salidas", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jButton11.setText("Cerrar");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAñadirEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirEntradaActionPerformed
        // TODO add your handling code here:
        int count;
        count = tablaEntradas.getRowCount() + 1;

        modelo.addRow(new Object[]{"E" + count, "Seleccione..", "Seleccione..", "Seleccione..", ""});

        setBox(tablaEntradas, tablaEntradas.getColumnModel().getColumn(1));
        setBox2(tablaEntradas, tablaEntradas.getColumnModel().getColumn(2));
        setBox3(tablaEntradas, tablaEntradas.getColumnModel().getColumn(3));
    }//GEN-LAST:event_botonAñadirEntradaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (tipo.equals("1")) {
            for (int i = 0; i < tablaEntradas.getRowCount(); i++) {
                mdb.insertarEnCiclo("insert into entradasmaquinaria values(null," + idMaquinaria + ",  '" + tablaEntradas.getValueAt(i, 0) + "', "
                        + "'" + tablaEntradas.getValueAt(i, 1) + "', '" + tablaEntradas.getValueAt(i, 2) + "',"
                        + "'" + tablaEntradas.getValueAt(i, 3) + "','" + tablaEntradas.getValueAt(i, 4) + "')");
            }
        } else {
            mdb.actualizarBasicos("delete from entradasmaquinaria where idMaquinaria=" + idMaquinaria + "");

            for (int i = 0; i < tablaEntradas.getRowCount(); i++) {
                mdb.insertarEnCiclo("insert into entradasmaquinaria values(null," + idMaquinaria + ",  '" + tablaEntradas.getValueAt(i, 0) + "', "
                        + "'" + tablaEntradas.getValueAt(i, 1) + "', '" + tablaEntradas.getValueAt(i, 2) + "',"
                        + "'" + tablaEntradas.getValueAt(i, 3) + "','" + tablaEntradas.getValueAt(i, 4) + "')");
            }
        }

        cargarTablas();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void botonEliminarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarEntradaActionPerformed
        // TODO add your handling code here:
        modelo.removeRow(tablaEntradas.getSelectedRow());
        modificarEntradas("Entradas");
    }//GEN-LAST:event_botonEliminarEntradaActionPerformed

    private void botonAñadirSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAñadirSalidaActionPerformed
        // TODO add your handling code here:
        int count;
        count = tablaSalidas.getRowCount() + 1;

        String claveSalida = "S" + count;

        modelo2.addRow(new Object[]{claveSalida, "Seleccione..", "Seleccione..", "Seleccione..", ""});

        setBox(tablaSalidas, tablaSalidas.getColumnModel().getColumn(1));
        setBox2(tablaSalidas, tablaSalidas.getColumnModel().getColumn(2));
        setBox3(tablaSalidas, tablaSalidas.getColumnModel().getColumn(3));

        comboSalidas.addItem(claveSalida);
    }//GEN-LAST:event_botonAñadirSalidaActionPerformed

    private void botonEliminarSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarSalidaActionPerformed
        // TODO add your handling code here:
        try {
            modelo2.removeRow(tablaSalidas.getSelectedRow());
            modificarEntradas("Salidas");

            comboSalidas.removeAllItems();
            for (int i = 0; i < modelo2.getRowCount(); i++) {
                comboSalidas.addItem(modelo2.getValueAt(i, 0) + "");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione Una Salida");
        }
    }//GEN-LAST:event_botonEliminarSalidaActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            comboSobrantes.setEnabled(true);
            String[] datos = mdb.cargarCombos("select nombre from sobrante").split("¬");
            comboSobrantes.setModel(new DefaultComboBoxModel((Object[]) datos));
            jCheckBox2.setVisible(true);
        } else {
            comboSobrantes.setEnabled(false);
            jCheckBox2.setVisible(false);
            comboSobrantes.removeAllItems();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    String generarLote = "0", disminucionInm = "0";
    private void comboActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActividadActionPerformed
        // TODO add your handling code here:
        String[] datos = mdb.cargarCombos("select nombre from maquinariabh m \n"
                + "inner join actividadesbh a on (m.idActividad=a.ID) "
                + "where a.actividad= '" + comboActividad.getSelectedItem() + "'").split("¬");
        comboMaquinaria.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboActividadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String idActividad = mdb.devuelveUnDato("select id from actividadesbh where actividad='" + comboActividades.getSelectedItem() + "'");
        String idSobrante = mdb.devuelveUnDato("select id from sobrante where nombre='" + comboSobrantes.getSelectedItem() + "'");

        if (idSobrante.equals("")) {
            idSobrante = "0";
        }

        entradas = tablaEntradas.getRowCount();
        salidas = tablaSalidas.getRowCount();

        if (jCheckBox1.isSelected()) {
            generarLote = "1";
        }

        if (jCheckBox2.isSelected()) {
            disminucionInm = "1";
        }

        mdb.insertarBasicos("insert into maquinariabh values (null, " + idActividad + ", '" + txtClave.getText() + "', '" + txtNombre.getText() + "', "
                + "'" + txtMarca.getText() + "', '" + txtModelo.getText() + "', '" + txtSerie.getText() + "', '" + txtCapacidad.getText() + "', "
                + "" + txtEvaluacion.getText() + ", " + entradas + ", " + salidas + ", " + idSobrante + ", '" + txtDescripcion.getText() + "', "
                + "" + generarLote + "," + disminucionInm + " )");

        idMaquinaria = mdb.devuelveUnDato("select id from maquinariabh where clave='" + txtClave.getText() + "'");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
/*
        switch (jTabbedPane1.getSelectedIndex()) {
            case 0:
                botonAñadirSalida.setVisible(false);
                botonEliminarSalida.setVisible(false);
                botonAñadirEntrada.setVisible(false);
                botonEliminarEntrada.setVisible(false);
                break;

            case 1:
                botonAñadirSalida.setVisible(false);
                botonEliminarSalida.setVisible(false);
                botonAñadirEntrada.setVisible(true);
                botonEliminarEntrada.setVisible(true);
                break;

            case 2:
                botonAñadirSalida.setVisible(true);
                botonEliminarSalida.setVisible(true);
                botonAñadirEntrada.setVisible(false);
                botonEliminarEntrada.setVisible(false);
                break;
        }
         */
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel6MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if (tipo.equals("1")) {
            for (int i = 0; i < tablaSalidas.getRowCount(); i++) {
                mdb.insertarEnCiclo("insert into salidasmaquinaria values(null," + idMaquinaria + ",  '" + tablaSalidas.getValueAt(i, 0) + "', "
                        + "'" + tablaSalidas.getValueAt(i, 1) + "', '" + tablaSalidas.getValueAt(i, 2) + "',"
                        + "'" + tablaSalidas.getValueAt(i, 3) + "','" + tablaSalidas.getValueAt(i, 4) + "')");
            }
        } else {
            mdb.actualizarBasicos("delete from salidasmaquinaria where idMaquinaria=" + idMaquinaria + "");

            for (int i = 0; i < tablaSalidas.getRowCount(); i++) {
                mdb.insertarEnCiclo("insert into salidasmaquinaria values(null," + idMaquinaria + ",  '" + tablaSalidas.getValueAt(i, 0) + "', "
                        + "'" + tablaSalidas.getValueAt(i, 1) + "', '" + tablaSalidas.getValueAt(i, 2) + "',"
                        + "'" + tablaSalidas.getValueAt(i, 3) + "','" + tablaSalidas.getValueAt(i, 4) + "')");
            }

        }

        cargarTablas();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        modelo3.addRow(new Object[]{comboSalidas.getSelectedItem(), comboActividad.getSelectedItem(), comboMaquinaria.getSelectedItem() + ""});
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (tipo.equals("1")) {
            for (int i = 0; i < tablaMaquinaria.getRowCount(); i++) {
                mdb.insertarEnCiclo("insert into relacionsalidamaquinarias values(null," + idMaquinaria + ",  "
                        + "'" + tablaMaquinaria.getValueAt(i, 0) + "', "
                        + "" + mdb.devuelveUnDato("select id from actividadesbh where actividad='" + tablaMaquinaria.getValueAt(i, 1) + "'") + ", "
                        + "" + mdb.devuelveUnDato("select id from maquinariabh where nombre= '" + tablaMaquinaria.getValueAt(i, 2) + "'") + " )");
            }
        } else {
            mdb.actualizarBasicos("delete from relacionsalidamaquinarias where idMaquinaria=" + idMaquinaria + "");

            for (int i = 0; i < tablaMaquinaria.getRowCount(); i++) {
                mdb.insertarEnCiclo("insert into relacionsalidamaquinarias values(null," + idMaquinaria + ",  "
                        + "'" + tablaMaquinaria.getValueAt(i, 0) + "', "
                        + "" + mdb.devuelveUnDato("select id from actividadesbh where actividad='" + tablaMaquinaria.getValueAt(i, 1) + "'") + ", "
                        + "" + mdb.devuelveUnDato("select id from maquinariabh where nombre= '" + tablaMaquinaria.getValueAt(i, 2) + "'") + " )");
            }
        }

        cargarTablas();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" factor=" Look and feel setting code (optional) ">
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
            java.util.logging.Logger.getLogger(jdMaquinaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdMaquinaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdMaquinaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdMaquinaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAñadirEntrada;
    private javax.swing.JButton botonAñadirSalida;
    private javax.swing.JButton botonEliminarEntrada;
    private javax.swing.JButton botonEliminarSalida;
    private javax.swing.JComboBox<String> comboActividad;
    private javax.swing.JComboBox<String> comboActividades;
    private javax.swing.JComboBox<String> comboMaquinaria;
    private javax.swing.JComboBox<String> comboSalidas;
    private javax.swing.JComboBox<String> comboSobrantes;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaEntradas;
    private javax.swing.JTable tablaMaquinaria;
    private javax.swing.JTable tablaSalidas;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEvaluacion;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
