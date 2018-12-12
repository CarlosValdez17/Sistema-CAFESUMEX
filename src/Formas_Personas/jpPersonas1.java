/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import MetodosGenerales.JComboCheckBox;
import Metodos_Configuraciones.metodosDatosBasicos;
import java.awt.Checkbox;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.control.CheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Carlos Valdez
 */
public class jpPersonas1 extends javax.swing.JPanel {

    /**
     * Creates new form jpPersonas2
     */
    Connection cn;
    metodosDatosBasicos mdb;
    DefaultTableModel modelo, modelo2;
    jdDetallePersona1 jdDP;
    ArrayList<String> array = new ArrayList<String>();

    public jpPersonas1(Connection c) {
        initComponents();
        //tablaPersonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //new JScrollPane(tablaPersonas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cn = c;
        mdb = new metodosDatosBasicos(cn);
        modelo = (DefaultTableModel) tablaPersonas.getModel();
        //  AutoCompleteDecorator.decorate(comboPersona);

        llenarTabla();
        tablaPersonas.setRowSorter(new TableRowSorter(modelo));
        cargarCombo();
        comboGenero.setEnabled(false);
    }

    public void cargarCombo() {
        String[] datos = mdb.cargarCombos("SELECT descripcion from puestos").split("#");
        comboAsignaciones.setModel(new DefaultComboBoxModel((Object[]) datos));
    }

    //✘ ✓
    public void llenarTabla() {
        array.clear();
        String tipoPer = comboPersona.getSelectedItem() + "";
        String tipoGen = comboGenero.getSelectedItem() + "";
        String where = "";

        if (!tipoGen.equals("Todos")) {
            tipoGen = mdb.devuelveId("select id from genero where descripcion='" + tipoGen + "'");
        } else {
            tipoGen = "";
        }

        if (comboBusqueda.getSelectedItem().equals("Seleccione..") && !comboGenero.getSelectedItem().equals("Todos")) {
            where = "where id_genero=" + tipoGen;
        }

        String tipoBusqueda = "";
        if (txtBusqueda.getText().length() > 0) {
            tipoBusqueda = " AND Almacen like '%" + txtBusqueda.getText() + "%'";
        }

        if (tipoPer.equals(
                "Moral")) {
            comboGenero.setEnabled(false);
            //comboPuestos.setEnabled(false);
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 13, "select '' as nombre, '' as ApellidoPaterno, '' as ApellidoMaterno, RazonSocial, Direccion, CodigoPostal, Telefono, '' as telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from personam x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID) " + where, array);
            //+ "where x.ID_TipoPersona=2", array);
            System.out.println(array);

        } else if (tipoPer.equals(
                "Fisica")) {
            comboGenero.setEnabled(true);
            //comboPuestos.setEnabled(true);
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 13, "select nombre, ApellidoPaterno, ApellidoMaterno, '' as razon, Direccion, CodigoPostal, Telefono, telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from personaf x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID)", array);
            //+ "where x.ID_TipoPersona=1", array);

        } else {
            comboGenero.setEnabled(true);
            //comboPuestos.setEnabled(true);
            limpiar(tablaPersonas);
            mdb.cargarInformacionPruebaArray(modelo, 13, "select nombre, ApellidoPaterno, ApellidoMaterno, '' as razon, Direccion, CodigoPostal, Telefono, telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from personaf x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID)", array);
            mdb.cargarInformacionPruebaArray(modelo, 13, "select '' as nombre, '' as ApellidoPaterno, '' as ApellidoMaterno, RazonSocial, Direccion, CodigoPostal, Telefono, '' as telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                    + "from personam x\n"
                    + "inner join pais p on (x.ID_Pais=p.ID)\n"
                    + "inner join estado e on (x.ID_Estado=e.ID)\n"
                    + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                    + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                    + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID)", array);

        }

        /* for (int i = 0;
                i < modelo.getRowCount();
                i++) {
            if (modelo.getValueAt(i, 4).equals("1")) {
                modelo.setValueAt("✓", i, 4);
            } else {
                modelo.setValueAt("✘", i, 4);
            }

            if (modelo.getValueAt(i, 5).equals("1")) {
                modelo.setValueAt("✓", i, 5);
            } else {
                modelo.setValueAt("✘", i, 5);
            }
        }*/
    }

    public void busqueda(String tipoCPersona, String tipoGenero, String tipoAsignacion, String tipoBusqueda, String palabra) {

        String sql = "", sql2 = "", where = "";
        limpiar(tablaPersonas);

        switch (tipoBusqueda) {
            case "Apellido Paterno":
                tipoBusqueda = "apellidoPaterno";
                break;
            case "Apellido Materno":
                tipoBusqueda = "apellidoMaterno";
                break;
            case "Razon Social":
                tipoBusqueda = "razonsocial";
                break;
        }

        switch (tipoCPersona) {

            case "Todos":

                if (!tipoAsignacion.equals("Ninguno")) {
                    where = " where pu.descripcion ='" + tipoAsignacion + "' ";
                }
                if (!tipoBusqueda.equals("Seleccione..")) {
                    where = where + " AND x." + tipoBusqueda + " like '" + palabra + "%'";
                }

                sql = "select nombre, ApellidoPaterno, ApellidoMaterno, '' as razon, Direccion, CodigoPostal, Telefono, telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                        + "from personaf x\n"
                        + "inner join pais p on (x.ID_Pais=p.ID)\n"
                        + "inner join estado e on (x.ID_Estado=e.ID)\n"
                        + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                        + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                        + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID) "
                        + "inner join genero g on (x.ID_Genero=g.ID) "
                        + "inner join asignacionespersona a on (x.ID=a.id_persona)\n"
                        + "inner join puestos pu on (pu.ID=a.id_puesto) \n" + where + " group by x.id";

                sql2 = "select '' as nombre, '' as ApellidoPaterno, '' as ApellidoMaterno, RazonSocial, Direccion, CodigoPostal, Telefono, '' as telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                        + "from personam x\n"
                        + "inner join pais p on (x.ID_Pais=p.ID)\n"
                        + "inner join estado e on (x.ID_Estado=e.ID)\n"
                        + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                        + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                        + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID) "
                        + "inner join asignacionespersona a on (x.ID=a.id_persona)\n"
                        + "inner join puestos pu on (pu.ID=a.id_puesto) \n" + where + " group by x.id";

                if (tipoAsignacion.equals("Ninguno") && tipoBusqueda.equals("Seleccione..")) {
                    mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                    mdb.cargarInformacionPruebaArray(modelo, 13, sql2, array);

                } else if (!tipoAsignacion.equals("Ninguno") && tipoBusqueda.equals("Seleccione..")) {
                    mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                    mdb.cargarInformacionPruebaArray(modelo, 13, sql2, array);
                } else {//if (!tipoAsignacion.equals("Ninguno") && !tipoBusqueda.equals("Seleccione..")) {

                    switch (tipoBusqueda) {
                        case "Nombre":
                            mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                            break;
                        case "apellidoPaterno":
                            mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                            break;
                        case "apellidoMaterno":
                            mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                            break;
                        case "razonsocial":
                            mdb.cargarInformacionPruebaArray(modelo, 13, sql2, array);
                            break;
                        case "Direccion":
                            mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                            mdb.cargarInformacionPruebaArray(modelo, 13, sql2, array);
                            break;
                    }

                }
                break;

            case "Fisica":
//------------------------------- RESTRICCION MASCULINO ------------------------------------------
                switch (tipoGenero) {
                    case "Masculino":
                        where = " WHERE g.descripcion='Masculino'";
                        if (!tipoAsignacion.equals("Ninguno")) {
                            where = where + " AND pu.descripcion ='" + tipoAsignacion + "' ";
                        }
                        if (!tipoBusqueda.equals("Seleccione..")) {
                            where = where + " AND x." + tipoBusqueda + " like '" + palabra + "%'";
                        }
//------------------------------- RESTRICCION FEMENINA ------------------------------------------
                        break;
                    case "Femenino":
                        where = " WHERE g.descripcion='Femenino'";
                        if (!tipoAsignacion.equals("Ninguno")) {
                            where = where + " AND pu.descripcion ='" + tipoAsignacion + "' ";
                        }
                        if (!tipoBusqueda.equals("Seleccione..")) {
                            where = where + " AND x." + tipoBusqueda + " like '" + palabra + "%'";
                        }
//------------------------------- RESTRICCION TODOS ------------------------------------------                 
                        break;
                    default:
                        where = "";
                        if (!tipoAsignacion.equals("Ninguno")) {
                            where = " where pu.descripcion ='" + tipoAsignacion + "' ";
                        }
                        if (!tipoBusqueda.equals("Seleccione..")) {
                            where = where + " AND x." + tipoBusqueda + " like '" + palabra + "%'";
                        }
                        break;
                }

                sql = "select nombre, ApellidoPaterno, ApellidoMaterno, '' as razon, Direccion, CodigoPostal, Telefono, telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                        + "from personaf x\n"
                        + "inner join pais p on (x.ID_Pais=p.ID)\n"
                        + "inner join estado e on (x.ID_Estado=e.ID)\n"
                        + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                        + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                        + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID) "
                        + "inner join genero g on (x.ID_Genero=g.ID) "
                        + "inner join asignacionespersona a on (x.ID=a.id_persona)\n"
                        + "inner join puestos pu on (pu.ID=a.id_puesto) \n" + where + " group by x.id";
                mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
                break;

            case "Moral":

                if (!tipoAsignacion.equals("Ninguno")) {
                    where = " where pu.descripcion ='" + tipoAsignacion + "' ";
                }

                if (!tipoBusqueda.equals("Seleccione..")) {
                    where = where + " AND x." + tipoBusqueda + " like '" + palabra + "%'";
                }

                sql = "select '' as nombre, '' as ApellidoPaterno, '' as ApellidoMaterno, RazonSocial, Direccion, CodigoPostal, Telefono, '' as telefonoMovil, p.descripcion, e.descripcion, m.descripcion, l.descripcion, c.descripcion\n"
                        + "from personam x\n"
                        + "inner join pais p on (x.ID_Pais=p.ID)\n"
                        + "inner join estado e on (x.ID_Estado=e.ID)\n"
                        + "inner join municipio m on (x.ID_Municipio=m.ID)\n"
                        + "inner join localidad l on (x.ID_Localidad=l.ID)\n"
                        + "inner join ejidocolonia c on (x.ID_EjidoColonia=c.ID) "
                        + "inner join asignacionespersona a on (x.ID=a.id_persona)\n"
                        + "inner join puestos pu on (pu.ID=a.id_puesto) \n" + where + " group by x.id";

                mdb.cargarInformacionPruebaArray(modelo, 13, sql, array);
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

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboPersona = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboBusqueda = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboAsignaciones = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboSituacion = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(830, 570));

        jLabel1.setText("Persona");

        comboPersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Fisica", "Moral" }));
        comboPersona.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonaItemStateChanged(evt);
            }
        });

        jLabel2.setText("Genero");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Masculino", "Femenino" }));
        comboGenero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGeneroItemStateChanged(evt);
            }
        });
        comboGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGeneroActionPerformed(evt);
            }
        });

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        jLabel3.setText("Busqueda");

        comboBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", "Nombre", "Razon Social", "Nombre Corto", "Apellido Paterno", "Apellido Materno", "Direccion" }));
        comboBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBusquedaItemStateChanged(evt);
            }
        });

        jLabel4.setText("Buscar por..");

        comboAsignaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar.." }));
        comboAsignaciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboAsignacionesItemStateChanged(evt);
            }
        });

        jLabel5.setText("Asignaciones");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(comboAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(108, 108, 108)
                        .addComponent(jLabel3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(comboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setText("Situacion");

        comboSituacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo", "Todos" }));
        comboSituacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSituacionItemStateChanged(evt);
            }
        });

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Desactivar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Cerrar");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(comboSituacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Razon Social", "Direccion", "Codigo Postal", "Telefono Fijo", "Telefono Movil", "Pais", "Estado", "Municipio", "Localidad", "Colonia", "Sociedades", "Socio", "Productor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPersonas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPersonasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPersonas);
        if (tablaPersonas.getColumnModel().getColumnCount() > 0) {
            tablaPersonas.getColumnModel().getColumn(0).setMinWidth(150);
            tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(150);
            tablaPersonas.getColumnModel().getColumn(0).setMaxWidth(150);
            tablaPersonas.getColumnModel().getColumn(1).setMinWidth(120);
            tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(120);
            tablaPersonas.getColumnModel().getColumn(1).setMaxWidth(120);
            tablaPersonas.getColumnModel().getColumn(2).setMinWidth(120);
            tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(120);
            tablaPersonas.getColumnModel().getColumn(2).setMaxWidth(120);
            tablaPersonas.getColumnModel().getColumn(3).setMinWidth(150);
            tablaPersonas.getColumnModel().getColumn(3).setPreferredWidth(150);
            tablaPersonas.getColumnModel().getColumn(3).setMaxWidth(150);
            tablaPersonas.getColumnModel().getColumn(4).setMinWidth(150);
            tablaPersonas.getColumnModel().getColumn(4).setPreferredWidth(150);
            tablaPersonas.getColumnModel().getColumn(4).setMaxWidth(150);
            tablaPersonas.getColumnModel().getColumn(5).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(5).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(5).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(6).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(6).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(6).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(7).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(7).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(7).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(8).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(8).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(8).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(9).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(9).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(9).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(10).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(10).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(10).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(11).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(11).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(11).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(12).setMinWidth(90);
            tablaPersonas.getColumnModel().getColumn(12).setPreferredWidth(90);
            tablaPersonas.getColumnModel().getColumn(12).setMaxWidth(90);
            tablaPersonas.getColumnModel().getColumn(14).setMinWidth(50);
            tablaPersonas.getColumnModel().getColumn(14).setPreferredWidth(50);
            tablaPersonas.getColumnModel().getColumn(14).setMaxWidth(50);
            tablaPersonas.getColumnModel().getColumn(15).setMinWidth(50);
            tablaPersonas.getColumnModel().getColumn(15).setPreferredWidth(50);
            tablaPersonas.getColumnModel().getColumn(15).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboSituacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSituacionItemStateChanged
        // TODO add your handling code here:
        // busqueda();
    }//GEN-LAST:event_comboSituacionItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jdDP = new jdDetallePersona1(null, true, "1", "", "", cn);
        jdDP.jpDP = this;
        jdDP.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro");
        } else {
            jdDP = new jdDetallePersona1(null, true, "2", id, "", cn);
            //jdDP.jpABH = this;
            jdDP.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jdFormularioParcelas jdfp = new jdFormularioParcelas(null, true, "3", cn);
        jdfp.setVisible(true);
        /*   String sql = "UPDATE actividadesbh SET ID_Situacion=2 where actividad='" + actividad + "'";
        mdb.actualizarBasicos(sql);
        llenaTabla();*/
    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboPersonaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonaItemStateChanged
        // TODO add your handling code here:
        switch (tipoCPersona) {
            case "Fisica":
                comboGenero.setEnabled(true);
                comboBusqueda.removeAllItems();
                comboBusqueda.addItem("Seleccione..");
                comboBusqueda.addItem("Nombre");
                comboBusqueda.addItem("Apellido Paterno");
                comboBusqueda.addItem("Apellido Materno");
                comboBusqueda.addItem("Direccion");
                break;
            case "Moral":
                comboGenero.setEnabled(false);
                comboBusqueda.removeAllItems();
                comboBusqueda.addItem("Seleccione..");
                comboBusqueda.addItem("Razon Social");
                comboBusqueda.addItem("Direccion");
                break;
            case "Todos":
                comboGenero.setEnabled(false);
                comboBusqueda.removeAllItems();
                comboBusqueda.addItem("Seleccione..");
                comboBusqueda.addItem("Nombre");
                comboBusqueda.addItem("Razon Social");
                comboBusqueda.addItem("Apellido Paterno");
                comboBusqueda.addItem("Apellido Materno");
                comboBusqueda.addItem("Direccion");
                break;
            default:
                break;
        }

        tipoCPersona = comboPersona.getSelectedItem() + "";
        tipoGenero = comboGenero.getSelectedItem() + "";
        tipoAsignacion = comboAsignaciones.getSelectedItem() + "";
        tipoBusqueda = comboBusqueda.getSelectedItem() + "";

        busqueda(tipoCPersona, tipoGenero, tipoAsignacion, tipoBusqueda, txtBusqueda.getText());
    }//GEN-LAST:event_comboPersonaItemStateChanged

    private void comboGeneroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGeneroItemStateChanged
        // TODO add your handling code here:*
        tipoCPersona = comboPersona.getSelectedItem() + "";
        tipoGenero = comboGenero.getSelectedItem() + "";
        tipoAsignacion = comboAsignaciones.getSelectedItem() + "";
        tipoBusqueda = comboBusqueda.getSelectedItem() + "";

        busqueda(tipoCPersona, tipoGenero, tipoAsignacion, tipoBusqueda, txtBusqueda.getText());
    }//GEN-LAST:event_comboGeneroItemStateChanged
    String nom = "", app = "", apm = "", id, rsocial = "";
    private void tablaPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonasMouseClicked
        // TODO add your handling code here:
        nom = tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 0) + "";
        app = tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 1) + "";
        apm = tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 2) + "";
        rsocial = tablaPersonas.getValueAt(tablaPersonas.getSelectedRow(), 3) + "";

        if (evt.getClickCount() == 2) {
            if (!nom.equals("")) {
                //Abrir detalle para persona fisica = 1
                id = mdb.comprobarExistencia("select id from personaf "
                        + "where (nombre='" + nom + "' and apellidoPaterno='" + app + "' and apellidoMaterno='" + apm + "' ) ");
                //JOptionPane.showMessageDialog(null,"Persona Fisica #"+id);
                jdDP = new jdDetallePersona1(null, true, "2", id, "1", cn);
                jdDP.jpDP = this;
                jdDP.setVisible(true);

            } else if (nom.equals("") && !rsocial.equals("")) {
                //Abrir detalle para persona moral = 2

                id = mdb.comprobarExistencia("select id from personam "
                        + "where razonsocial='" + rsocial + "'");
                //JOptionPane.showMessageDialog(null,"Persona Moral #"+id);
                jdDP = new jdDetallePersona1(null, true, "2", id, "2", cn);
                jdDP.jpDP = this;
                jdDP.setVisible(true);
            }
        }
    }//GEN-LAST:event_tablaPersonasMouseClicked

    String tipoGenero = "", tipoBusqueda = "", tipoAsignacion = "", tipoCPersona = "";
    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        // TODO add your handling code here:
        tipoCPersona = comboPersona.getSelectedItem() + "";
        tipoGenero = comboGenero.getSelectedItem() + "";
        tipoAsignacion = comboAsignaciones.getSelectedItem() + "";
        tipoBusqueda = comboBusqueda.getSelectedItem() + "";

        busqueda(tipoCPersona, tipoGenero, tipoAsignacion, tipoBusqueda, txtBusqueda.getText());
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void comboBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBusquedaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBusquedaItemStateChanged

    private void comboGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGeneroActionPerformed

    private void comboAsignacionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAsignacionesItemStateChanged
        // TODO add your handling code here:
        tipoCPersona = comboPersona.getSelectedItem() + "";
        tipoGenero = comboGenero.getSelectedItem() + "";
        tipoAsignacion = comboAsignaciones.getSelectedItem() + "";
        tipoBusqueda = comboBusqueda.getSelectedItem() + "";

        busqueda(tipoCPersona, tipoGenero, tipoAsignacion, tipoBusqueda, txtBusqueda.getText());
    }//GEN-LAST:event_comboAsignacionesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboAsignaciones;
    private javax.swing.JComboBox<String> comboBusqueda;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JComboBox<String> comboPersona;
    private javax.swing.JComboBox<String> comboSituacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPersonas;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
