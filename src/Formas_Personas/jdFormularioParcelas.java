/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import Metodos_Configuraciones.metodosDatosBasicos;
import Metodos_Configuraciones.validaConfi;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author Carlos Valdez
 */
public class jdFormularioParcelas extends javax.swing.JDialog {

    /**
     * Creates new form jdFormularioParcelas
     */
    Connection cn;
    metodosDatosBasicos mdb;
    DefaultTableModel modelo, modeloC, modeloCultivos, modeloV, modeloD;
    String idPersona;
    validaConfi valiConf;

    String fila = null;

    private JPanel contentPane;

    public jdFormularioParcelas(java.awt.Frame parent, boolean modal, String idPersona, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cn = c;
        mdb = new metodosDatosBasicos(cn);
        valiConf = new validaConfi();

        modelo = (DefaultTableModel) tablaParcelas.getModel();
        modeloC = (DefaultTableModel) tablaCertificado.getModel();
        modeloCultivos = (DefaultTableModel) tablaCultivos.getModel();
        modeloV = (DefaultTableModel) tablaVariedades.getModel();
        modeloD = (DefaultTableModel) tablaDocumentos.getModel();

        this.idPersona = idPersona;

        llenarDatos();
        llenarTabla();
        rellenarCombos();
        // datosParcela("17");
    }

    public void validarCampos() {
        if (txtClave.getText().length() == 0) {
            txtClave.setBackground(Color.RED);
        }
        if (txtNombre.getText().length() == 0) {
            txtNombre.setBackground(Color.RED);
        }
        if (txtAltura.getText().length() == 0) {
            txtAltura.setBackground(Color.RED);
        }
        if (txtArea.getText().length() == 0) {
            txtArea.setBackground(Color.RED);
        }
        if (txtCafetos.getText().length() == 0) {
            txtCafetos.setBackground(Color.RED);
        }
        if (txtLatitud.getText().length() == 0) {
            txtLatitud.setBackground(Color.RED);
        }
        if (txtLongitud.getText().length() == 0) {
            txtLongitud.setBackground(Color.RED);
        }
    }

    public void llenarTabla() {
        limpiar(tablaParcelas);
        mdb.cargarInformacion2(modelo, 8, "SELECT s.razonsocial, p.nombre, \n"
                + "    p.clave_parcela,\n"
                + "    GROUP_CONCAT(\n"
                + "        DISTINCT c.Descripcion\n"
                + "    ORDER BY\n"
                + "        c.Descripcion ASC SEPARATOR ' - '\n"
                + "    ) AS \"Certificados\",\n"
                + "    l.descripcion,"
                + "    altura,\n"
                + "    AREA,\n"
                + "    numCafetos\n"
                + "FROM\n"
                + "    parcelas p\n"
                + "INNER JOIN certificadosparcelas cp ON\n"
                + "    (cp.id_parcela = p.id)\n"
                + "INNER JOIN certificado c ON\n"
                + "    (c.ID = cp.id_certificado)\n"
                + "INNER JOIN personam s ON\n"
                + "    (p.id_sociedad = s.id)\n"
                + "INNER JOIN localidad l ON\n"
                + "    (p.id_localidad=l.id)"
                + "WHERE\n"
                + "    id_persona =" + idPersona + "  \n"
                + "GROUP BY clave_parcela ASC");
    }

    public void llenarDatos() {
        String[] datos = mdb.cargarDatosFormularioPersonas("SELECT concat(nombre,' ',apellidopaterno,' ', apellidomaterno) as nombre,"
                + "identificacionfiscal, registrodepoblacion, direccion, telefono, telefonomovil from personaf where id=" + idPersona, 6).split("¬");
        lblNombre.setText(datos[0]);
        lblRFC.setText(datos[1]);
        lblCURP.setText(datos[2]);
        lblDireccion.setText(datos[3]);
        lblTelFijo.setText(datos[4]);
        lblTelMov.setText(datos[5]);

        lblClave.setText(mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona));
        //System.out.println("CLAVE DE PRODUCTOR=" + mdb.devuelveUnDato("select clave_productor from productor where id_persona=" + idPersona));
    }

    public void datosParcela(String idParcela) {
        /*    ----- CONSULTA COMPLETA -----  
        String[] datos = mdb.cargarDatosFormularioPersonas("SELECT clave_parcela, nombre, altura, area, numCafetos, pm.NombreCorto, pa.descripcion,\n"
                + "	e.descripcion, m.descripcion, l.descripcion, ts.descripcion, tsom.descripcion, sp.descripcion, sector, edad, observaciones\n"
                + "from parcelas p\n"
                + "inner join personam pm on (p.id_sociedad=pm.ID)\n"
                + "inner join pais pa on (p.id_pais=pa.ID)\n"
                + "inner join estado e on (p.id_estado=e.ID)\n"
                + "inner join municipio m on (p.id_municipio=m.ID)\n"
                + "inner join localidad l on (p.id_localidad=l.ID)\n"
                + "inner join tiposuelo ts on (p.Id_tipoSuelo=ts.ID)\n"
                + "inner join tiposombra tsom on (p.Id_tipoSombra=tsom.ID)\n"
                + "inner join tipoproduccion sp on (p.Id_sistemaProduccion=sp.ID)\n"
                + "where p.id=" + idParcela, 16).split("¬");
         */

        String[] datos = mdb.cargarDatosFormularioPersonas("SELECT clave_parcela, nombre, altura, area, numCafetos, pm.NombreCorto, pa.descripcion,\n"
                + "	e.descripcion, m.descripcion, l.descripcion, sector, edad, observaciones\n"
                + "from parcelas p\n"
                + "inner join personam pm on (p.id_sociedad=pm.ID)\n"
                + "inner join pais pa on (p.id_pais=pa.ID)\n"
                + "inner join estado e on (p.id_estado=e.ID)\n"
                + "inner join municipio m on (p.id_municipio=m.ID)\n"
                + "inner join localidad l on (p.id_localidad=l.ID)\n"
                + "where p.id=" + idParcela, 13).split("¬");

        //Clave de parcela
        txtClave.setText(datos[0]);
        //Nombre parcela
        txtNombre.setText(datos[1]);
        //Altura parcela
        txtAltura.setText(datos[2]);
        //Area parcela
        txtArea.setText(datos[3]);
        //Numero de Cafetos
        txtCafetos.setText(datos[4]);
        //Nombre Corto de Sociedad
        comboSociedades.setSelectedItem(datos[5]);
        //Pais
        comboPais.setSelectedItem(datos[6]);
        //Estado
        comboEstado.setSelectedItem(datos[7]);
        //Municipio
        comboMunicipio.setSelectedItem(datos[8]);
        //Localidad
        comboLocalidad.setSelectedItem(datos[9]);
        //Sector
        comboSector.setSelectedItem(datos[10]);
        //Edad
        txtEdad.setText(datos[11]);
        //Observaciones
        txtObservaciones.setText(datos[12]);

        //Combos - Consulta Individual
        //Tipo de Suelo
        comboSuelo.setSelectedItem(mdb.devuelveUnDato("select ts.descripcion from parcelas p "
                + "inner join tiposuelo ts on (p.Id_tipoSuelo=ts.ID) "
                + "where p.id=" + idParcela));

        //Tipo de Sombra
        comboSombra.setSelectedItem(mdb.devuelveUnDato("select ts.descripcion from parcelas p "
                + "inner join tiposombra ts on (p.id_tiposombra=ts.id) "
                + "where p.id=" + idParcela));

        //Combo Sistema Produccion
        comboSistemaP.setSelectedItem(mdb.devuelveUnDato("select sp.descripcion from parcelas p "
                + "inner join tipoproduccion sp on (p.Id_sistemaProduccion=sp.ID) "
                + "where p.id=" + idParcela));

        limpiar(tablaCultivos);
        limpiar(tablaVariedades);
        limpiar(tablaCertificado);
        limpiar(tablaDocumentos);

        mdb.cargarInformacion2(modeloCultivos, 2, "select c.descripcion, porcentaje "
                + "from porcentaje_cultivos p "
                + "inner join cultivos c on (p.id_cultivo=c.id) "
                + "where id_parcela=" + idParcela);

        mdb.cargarInformacion2(modeloV, 2, "select c.descripcion, porcentaje "
                + "from porcentaje_variedades p "
                + "inner join variedadcafe c on (p.id_variedad=c.id) "
                + "where id_parcela=" + idParcela);

        mdb.cargarInformacion2(modeloC, 1, "select c.clave "
                + "from certificadosparcelas cp "
                + "inner join certificado c on (cp.id_certificado=c.id) "
                + "where id_parcela=" + idParcela);

        mdb.cargarInformacion2(modeloD, 3, "select nombre, t.descripcion, ruta "
                + "from documentosparcelas d "
                + "inner join categoriadearchivos t on (d.tipoArchivo=t.id) "
                + "where id_parcela=" + idParcela);
    }

    public void limpiarCampos() {
        txtClave.setBackground(Color.WHITE);
        txtNombre.setBackground(Color.WHITE);
        txtAltura.setBackground(Color.WHITE);
        txtArea.setBackground(Color.WHITE);
        txtCafetos.setBackground(Color.WHITE);
        txtLatitud.setBackground(Color.WHITE);
        txtLongitud.setBackground(Color.WHITE);

        txtClave.setText("");
        txtNombre.setText("");
        txtAltura.setText("");
        txtArea.setText("");
        txtCafetos.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        txtEdad.setText("");
        txtObservaciones.setText("");

        limpiar(tablaCultivos);
        limpiar(tablaVariedades);
        limpiar(tablaCertificado);

        comboPais.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        comboMunicipio.setSelectedIndex(0);
        comboLocalidad.setSelectedIndex(0);
        comboSuelo.setSelectedIndex(0);
        comboSombra.setSelectedIndex(0);
        comboSistemaP.setSelectedIndex(0);
        comboSector.setSelectedIndex(0);
        comboSociedades.setSelectedIndex(0);

        jTabbedPane1.setSelectedIndex(0);
    }

    public void rellenarCombos() {
        String pais, estado, municipio;
        String[] datos;

        datos = mdb.cargarCombos("SELECT descripcion from pais").split("#");
        comboPais.setModel(new DefaultComboBoxModel((Object[]) datos));
        pais = comboPais.getSelectedItem() + "";                            

        datos = mdb.cargarCombos("SELECT e.descripcion from estado e "
                + "inner join pais p on(e.id_pais=p.id) "
                + "where p.descripcion='" + pais + "'").split("#");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datos));
        estado = comboEstado.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + estado + "'").split("#");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + municipio + "'").split("#");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
//        localidad = comboLocalidad.getSelectedItem() + "";

        /*      datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from ejidocolonia m \n"
                + "inner join localidad e on (m.id_localidad=e.id) \n"
                + "where e.Descripcion='" + localidad + "'").split("#");
        comboColonia.setModel(new DefaultComboBoxModel((Object[]) datos));
         */
        String[] datosC = mdb.cargarCombos("select clave from certificado").split("#");
        comboCertificado.setModel(new DefaultComboBoxModel((Object[]) datosC));

        String[] datosS = mdb.cargarCombos("select p.nombrecorto from personam p \n"
                + "inner join sociedadespersona s on (p.id=s.id_asociado)\n"
                + "inner join personaf f on (f.ID=s.id_persona)\n"
                + "where f.ID=" + idPersona).split("#");
        comboSociedades.setModel(new DefaultComboBoxModel((Object[]) datosS));

        String[] datosTSuelo = mdb.cargarCombos("select descripcion from tiposuelo").split("#");
        comboSuelo.setModel(new DefaultComboBoxModel((Object[]) datosTSuelo));

        String[] datosTSombra = mdb.cargarCombos("select descripcion from tiposombra").split("#");
        comboSombra.setModel(new DefaultComboBoxModel((Object[]) datosTSombra));

        String[] datosTProd = mdb.cargarCombos("select descripcion from tipoproduccion").split("#");
        comboSistemaP.setModel(new DefaultComboBoxModel((Object[]) datosTProd));

        String[] datosCultivos = mdb.cargarCombos("select descripcion from cultivos").split("#");
        comboCultivos.setModel(new DefaultComboBoxModel((Object[]) datosCultivos));

        String[] datosVariedad = mdb.cargarCombos("select descripcion from variedadcafe").split("#");
        comboVariedad.setModel(new DefaultComboBoxModel((Object[]) datosVariedad));

        String[] datosArchivos = mdb.cargarCombos("select descripcion from categoriadearchivos where parcelas=1").split("#");
        comboArchivos.setModel(new DefaultComboBoxModel((Object[]) datosArchivos));
    }

    public String code(String filePath) throws FileNotFoundException, IOException, SQLException {
        //Codificar
        byte[] input_file = Files.readAllBytes(Paths.get(filePath));
        byte[] encodedBytes = Base64.getEncoder().encode(input_file);
        String encodedString = new String(encodedBytes);

        //System.out.println(encodedString);
        return encodedString;
    }

    public void decode(String encodedString) throws IOException {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());

        FileOutputStream fos = new FileOutputStream("C:\\prueba\\pruebaDocParc.pdf");
        fos.write(decodedBytes);
        fos.flush();
        fos.close();

        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new java.io.File("C:\\prueba\\pruebaDocParc.pdf"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se puede abrir archivo." + ex.getMessage());
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
        jLabel8 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblRFC = new javax.swing.JLabel();
        lblCURP = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelFijo = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTelMov = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaParcelas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtClave = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        txtCafetos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboSociedades = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboMunicipio = new javax.swing.JComboBox<>();
        comboLocalidad = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        comboPais = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        txtLatitud = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtLongitud = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboCertificado = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCertificado = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        comboSuelo = new javax.swing.JComboBox<>();
        comboSombra = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        comboSector = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        comboSistemaP = new javax.swing.JComboBox<>();
        txtEdad = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCultivos = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        comboCultivos = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtPorcentajeCultivos = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaVariedades = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        comboVariedad = new javax.swing.JComboBox<>();
        txtPorcentajeVariedad = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        comboArchivos = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaDocumentos = new javax.swing.JTable();
        txtNombreArchivo = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir Parcelas");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Productor"));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nombre");

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNombre.setText("jLabel14");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Clave de Productor");

        lblClave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblClave.setText("jLabel15");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("RFC");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("CURP");

        lblRFC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRFC.setText("jLabel14");

        lblCURP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCURP.setText("jLabel14");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Dirección");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDireccion.setText("jLabel14");

        lblTelFijo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTelFijo.setText("jLabel14");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Telefono Fijo");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Telefono Movil");

        lblTelMov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTelMov.setText("jLabel14");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(lblClave))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRFC)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lblCURP))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblDireccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(lblTelFijo))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(lblTelMov))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelMov))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel18)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblTelFijo))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel17)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblDireccion))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblCURP))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(lblRFC)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClave))))
        );

        tablaParcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Sociedad", "Nombre", "Clave Parcela", "Certificado", "Localidad", "Altura", "Area (HA)", "Numero de Cafetos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaParcelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaParcelasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaParcelas);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Parcela"));

        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveKeyReleased(evt);
            }
        });

        jLabel1.setText("Clave Parcela");

        jLabel2.setText("Nombre de Parcela");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        jLabel3.setText("Altura");

        txtAltura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlturaKeyTyped(evt);
            }
        });

        jLabel4.setText("Área (HA)");

        txtArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaKeyTyped(evt);
            }
        });

        txtCafetos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCafetosKeyTyped(evt);
            }
        });

        jLabel5.setText("Numero de Cafetos");

        comboSociedades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione.." }));
        comboSociedades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSociedadesItemStateChanged(evt);
            }
        });
        comboSociedades.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboSociedadesFocusGained(evt);
            }
        });

        jLabel7.setText("Sociedad");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(txtCafetos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(comboSociedades, 0, 148, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCafetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSociedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicación Geografica"));

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));
        comboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEstadoItemStateChanged(evt);
            }
        });

        jLabel10.setText("Estado");

        jLabel11.setText("Municipio");

        comboMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));
        comboMunicipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMunicipioItemStateChanged(evt);
            }
        });

        comboLocalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));

        jLabel12.setText("Localidad");

        jLabel26.setText("Pais");

        comboPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));
        comboPais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPaisItemStateChanged(evt);
            }
        });

        jLabel27.setText("Latitud");

        jLabel28.setText("Longitud");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMunicipio, javax.swing.GroupLayout.Alignment.LEADING, 0, 177, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(comboLocalidad, 0, 177, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Certificados"));

        jLabel6.setText("Certificado");

        comboCertificado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCertificadoItemStateChanged(evt);
            }
        });
        comboCertificado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboCertificadoFocusGained(evt);
            }
        });

        tablaCertificado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Certificado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCertificado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCertificadoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaCertificado);
        if (tablaCertificado.getColumnModel().getColumnCount() > 0) {
            tablaCertificado.getColumnModel().getColumn(0).setResizable(false);
        }

        jButton2.setText("Añadir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(comboCertificado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(comboCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Basicos", jPanel4);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Complementarios"));

        jLabel9.setText("Tipo de Suelo");

        comboSuelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboSombra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Tipo de Sombra");

        comboSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..", "Privado", "Publico" }));

        jLabel20.setText("Sector");

        jLabel21.setText("Sistema de Producción");

        comboSistemaP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setText("Edad en Años");

        jLabel25.setText("Observaciones");

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane3.setViewportView(txtObservaciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtEdad)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel20)
                                .addComponent(jLabel24)
                                .addComponent(jLabel9)
                                .addComponent(comboSector, 0, 206, Short.MAX_VALUE)
                                .addComponent(comboSuelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel21)
                                .addComponent(jLabel13)
                                .addComponent(comboSombra, 0, 206, Short.MAX_VALUE)
                                .addComponent(comboSistemaP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSombra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSistemaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addGap(16, 16, 16))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Porcentajes"));

        tablaCultivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cultivos", "Porcentaje %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCultivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCultivosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaCultivos);

        jLabel22.setText("Cultivos");

        comboCultivos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Porcentaje %");

        txtPorcentajeCultivos.setText("0");

        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tablaVariedades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Variedad Cafe", "Porcentaje %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVariedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVariedadesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaVariedades);

        jLabel29.setText("Variedades de Cafe");

        comboVariedad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtPorcentajeVariedad.setText("0");

        jLabel30.setText("Porcentaje %");

        jButton6.setText("Añadir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Eliminar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(comboVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txtPorcentajeVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton7)))
                                .addGap(0, 3, Short.MAX_VALUE)))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(comboCultivos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtPorcentajeCultivos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCultivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPorcentajeCultivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(txtPorcentajeVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos Complementarios", jPanel6);

        jLabel31.setText("Tipo de Archivo");

        comboArchivos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel32.setText("Ruta");

        txtRuta.setEditable(false);

        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        tablaDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre del Archivo", "Tipo de Documento", "Ruta"
            }
        ));
        tablaDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDocumentosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaDocumentos);

        jLabel33.setText("Nombre del Archivo");

        jButton9.setText("Eliminar");

        jButton10.setText("Añadir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setText("Abrir PDF");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton12)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(txtNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Documentos", jPanel9);

        jButton11.setText("Limpiar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setText("Pruebas");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13)
                        .addGap(343, 343, 343)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton11)
                    .addComponent(jButton13))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String estado, municipio;

        estado = comboEstado.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + estado + "'").split("#");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + municipio + "'").split("#");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboEstadoItemStateChanged

    private void comboMunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMunicipioItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String municipio;

        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + municipio + "'").split("#");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboMunicipioItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String tipoSuelo = "", tipoSombra = "", tipoProduccion = "";

        tipoSuelo = mdb.devuelveId("select id from tiposuelo where descripcion='" + comboSuelo.getSelectedItem() + "'");
        tipoSombra = mdb.devuelveId("select id from tiposombra where descripcion='" + comboSombra.getSelectedItem() + "'");
        tipoProduccion = mdb.devuelveId("select id from tipoproduccion where descripcion='" + comboSistemaP.getSelectedItem() + "'");

        if (tipoSuelo == null || tipoSuelo.equals("")) {
            tipoSuelo = "0";
        }

        if (tipoSombra == null || tipoSombra.equals("")) {
            tipoSombra = "0";
        }

        if (tipoProduccion == null || tipoProduccion.equals("")) {
            tipoProduccion = "0";
        }

        if (txtClave.getText().length() == 0 || txtNombre.getText().length() == 0 || txtAltura.getText().length() == 0
                || txtArea.getText().length() == 0 || txtCafetos.getText().length() == 0 || txtLatitud.getText().length() == 0
                || txtLongitud.getText().length() == 0) {
            validarCampos();
            JOptionPane.showMessageDialog(null, "Existen Campos Vacios");
        }//Fin del if
        else {
            if (mdb.devuelveUnDato("select clave_parcela from parcelas where clave_parcela='" + txtClave.getText() + "'").equals(txtClave.getText())) {
                JOptionPane.showMessageDialog(null, "Error: Clave Duplicada");
            } else {
                mdb.insertarBasicos("insert into parcelas values (null, " + idPersona + ", '" + txtClave.getText() + "', '" + txtNombre.getText() + "', "
                        + "'" + txtAltura.getText() + "', '" + txtArea.getText() + "', " + txtCafetos.getText() + ", "
                        + "" + mdb.devuelveId("select id from pais where descripcion='" + comboPais.getSelectedItem() + "'") + ", "
                        + "" + mdb.devuelveId("select id from estado where descripcion='" + comboEstado.getSelectedItem() + "'") + ", "
                        + "" + mdb.devuelveId("select id from municipio where descripcion='" + comboMunicipio.getSelectedItem() + "' ") + ","
                        + "" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "' ") + ", "
                        + "" + mdb.devuelveId("select id from personam where nombrecorto='" + comboSociedades.getSelectedItem() + "'  ") + ", "
                        + "" + tipoSuelo + ", "
                        + "" + tipoSombra + ", "
                        + " '" + comboSector.getSelectedItem() + "', "
                        + "" + tipoProduccion + ","
                        + " '" + txtEdad.getText() + "', '" + txtLongitud.getText() + "', '" + txtLatitud.getText() + "', '" + txtObservaciones.getText() + "',1,1,current_date(),current_time(),1,1,1,1) ");

                if (mdb.devuelveId("select id from parcelas where clave_parcela='" + txtClave.getText() + "'").equals("")) {
                    JOptionPane.showMessageDialog(null, "No existe la parcela");
                } else {
                    for (int i = 0; i < modeloC.getRowCount(); i++) {
                        String certificados = modeloC.getValueAt(i, 0).toString();
                        mdb.insertarEnCiclo("insert into certificadosparcelas values (null, "
                                + " " + mdb.devuelveId("select id from parcelas where clave_parcela='" + txtClave.getText() + "' ") + ", "
                                + " " + mdb.devuelveId("select id from certificado where clave='" + certificados + "' ") + ","
                                + " 1,1,current_date(),current_time(),1,1,1,1) ");
                    }

                    for (int i = 0; i < modeloCultivos.getRowCount(); i++) {
                        String cultivo = modeloCultivos.getValueAt(i, 0).toString();
                        String porcentaje = modeloCultivos.getValueAt(i, 1).toString();

                        cultivo = mdb.devuelveId("select id from cultivos where descripcion='" + cultivo + "'");

                        mdb.insertarEnCiclo("insert into porcentaje_cultivos values (null, "
                                + " " + mdb.devuelveId("select id from parcelas where clave_parcela='" + txtClave.getText() + "' ") + ", "
                                + " " + cultivo + ", " + porcentaje + ", "
                                + " 1,1,current_date(),current_time(),1,1,1,1) ");
                    }

                    for (int i = 0; i < modeloV.getRowCount(); i++) {
                        String variedad = modeloV.getValueAt(i, 0).toString();
                        String porcentaje = modeloV.getValueAt(i, 1).toString();

                        variedad = mdb.devuelveId("select id from variedadcafe where descripcion='" + variedad + "'");

                        mdb.insertarEnCiclo("insert into porcentaje_variedades values (null, "
                                + " " + mdb.devuelveId("select id from parcelas where clave_parcela='" + txtClave.getText() + "' ") + ", "
                                + " " + variedad + ", " + porcentaje + ", "
                                + " 1,1,current_date(),current_time(),1,1,1,1) ");
                    }

                    for (int i = 0; i < modeloD.getRowCount(); i++) {
                        String nombre = modeloD.getValueAt(i, 0).toString();
                        String tipoDoc
                                = mdb.devuelveUnDato("select id from categoriadearchivos where descripcion= '" + modeloD.getValueAt(i, 1).toString() + "'");
                        String ruta = modeloD.getValueAt(i, 2).toString();
                        try {
                            mdb.insertarEnCiclo("insert into documentosparcelas values (null," + mdb.devuelveId("select id from parcelas where clave_parcela='" + txtClave.getText() + "' ") + " , "
                                    + "" + tipoDoc + ", '" + nombre + "','" + ruta + "', '" + code(ruta) + "' )");
                        } catch (IOException ex) {
                            Logger.getLogger(jdFormularioParcelas.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(jdFormularioParcelas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    limpiarCampos();
                }
            }
            llenarTabla();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboCertificadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCertificadoItemStateChanged
        // TODO add your handling code here:
        comboCertificado.setToolTipText(
                mdb.devuelveUnDato("select descripcion from certificado "
                        + "where clave='" + comboCertificado.getSelectedItem() + "' "));
    }//GEN-LAST:event_comboCertificadoItemStateChanged

    private void comboCertificadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboCertificadoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCertificadoFocusGained

    private void comboSociedadesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboSociedadesFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSociedadesFocusGained

    private void comboSociedadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSociedadesItemStateChanged
        // TODO add your handling code here:
        comboSociedades.setToolTipText(
                mdb.devuelveUnDato("select razonsocial from personam "
                        + "where nombrecorto='" + comboSociedades.getSelectedItem() + "' "));
    }//GEN-LAST:event_comboSociedadesItemStateChanged

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if (txtNombre.getText().length() != 0) {
            txtNombre.setText(valiConf.primerLetraMayuscula(txtNombre.getText()).replace("S/n", "S/N"));
            txtNombre.setText(valiConf.primerLetraMayuscula(txtNombre.getText()).replace("S/d", "S/D"));
            txtNombre.setText(valiConf.primerLetraMayuscula(txtNombre.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtAlturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlturaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtAlturaKeyTyped

    private void txtAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtAreaKeyTyped

    private void txtCafetosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCafetosKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCafetosKeyTyped

    private void txtClaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyReleased
        // TODO add your handling code here:
        txtClave.setText(txtClave.getText().toUpperCase());
    }//GEN-LAST:event_txtClaveKeyReleased

    private void comboPaisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPaisItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String pais, estado, municipio, localidad;

        pais = comboPais.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT e.descripcion from estado e "
                + "inner join pais p on(e.id_pais=p.id) "
                + "where p.descripcion='" + pais + "'").split("#");
        comboEstado.setModel(new DefaultComboBoxModel((Object[]) datos));
        estado = comboEstado.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from municipio m \n"
                + "inner join estado e on (m.id_estado=e.id) \n"
                + "where e.Descripcion='" + estado + "'").split("#");
        comboMunicipio.setModel(new DefaultComboBoxModel((Object[]) datos));
        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + municipio + "'").split("#");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
        localidad = comboLocalidad.getSelectedItem() + "";
    }//GEN-LAST:event_comboPaisItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        modeloC.addRow(new Object[]{comboCertificado.getSelectedItem() + ""});
    }//GEN-LAST:event_jButton2ActionPerformed
    int porcentajeMaximo = 0, sumaPorcentajes = 0;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Suma y Comparación de porcentajes para obtener y/o no sobrepasar el 100% entre 
        //los porcentajes de cultivos.
        if (tablaCultivos.getRowCount() > 0) {
            for (int i = 0; i < tablaCultivos.getRowCount(); i++) {
                sumaPorcentajes = sumaPorcentajes + Integer.parseInt(tablaCultivos.getValueAt(i, 1) + "");
            }
            sumaPorcentajes = sumaPorcentajes + Integer.parseInt(txtPorcentajeCultivos.getText());
            //JOptionPane.showMessageDialog(null, "Porcentaje= " + sumaPorcentajes);
        }

        if (sumaPorcentajes <= 100) {
            if (txtPorcentajeCultivos.getText().equals("") || comboCultivos.getSelectedItem().equals("Seleccione..")) {
                JOptionPane.showMessageDialog(null, "Introduce Porcentaje de Cultivo y/o Selecciona Cultivo");
            } else {
                modeloCultivos.addRow(new Object[]{comboCultivos.getSelectedItem() + "", txtPorcentajeCultivos.getText()});
                txtPorcentajeCultivos.setText("");
            }
        } else if (sumaPorcentajes > 100) {
            JOptionPane.showMessageDialog(null, "Excede El Porcentaje Maximo (100%)");
        }
        sumaPorcentajes = 0;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (tablaVariedades.getRowCount() > 0) {
            for (int i = 0; i < tablaVariedades.getRowCount(); i++) {
                sumaPorcentajes = sumaPorcentajes + Integer.parseInt(tablaVariedades.getValueAt(i, 1) + "");
            }
            sumaPorcentajes = sumaPorcentajes + Integer.parseInt(txtPorcentajeVariedad.getText());
            JOptionPane.showMessageDialog(null, "Porcentaje= " + sumaPorcentajes);
        }

        if (sumaPorcentajes <= 100) {

            if (txtPorcentajeVariedad.getText().equals("") || comboVariedad.getSelectedItem().equals("Seleccione..")) {
                JOptionPane.showMessageDialog(null, "Introduce Porcentaje de Variedad y/o Selecciona Variedad");
            } else {
                modeloV.addRow(new Object[]{comboVariedad.getSelectedItem() + "", txtPorcentajeVariedad.getText()});
                txtPorcentajeVariedad.setText("");
            }
        } else if (sumaPorcentajes > 100) {
            JOptionPane.showMessageDialog(null, "Excede El Porcentaje Maximo (100%)");
        }
        sumaPorcentajes = 0;
    }//GEN-LAST:event_jButton6ActionPerformed
    File fichero = null;
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
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
            txtRuta.setText(fichero.getAbsolutePath());
            //System.out.println(fichero.getName());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        modeloD.addRow(new Object[]{txtNombreArchivo.getText(), comboArchivos.getSelectedItem(), txtRuta.getText()});
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (fila == null) {
        } else {
            modeloC.removeRow(Integer.parseInt(fila));
            fila = null;
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tablaCertificadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCertificadoMouseClicked
        // TODO add your handling code here:
        fila = tablaCertificado.getSelectedRow() + "";
    }//GEN-LAST:event_tablaCertificadoMouseClicked

    private void tablaCultivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCultivosMouseClicked
        // TODO add your handling code here:
        fila = tablaCultivos.getSelectedRow() + "";
    }//GEN-LAST:event_tablaCultivosMouseClicked

    private void tablaVariedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVariedadesMouseClicked
        // TODO add your handling code here:
        fila = tablaVariedades.getSelectedRow() + "";
    }//GEN-LAST:event_tablaVariedadesMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (fila == null) {
        } else {
            modeloCultivos.removeRow(Integer.parseInt(fila));
            fila = null;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (fila == null) {
        } else {
            modeloV.removeRow(Integer.parseInt(fila));
            fila = null;
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void tablaParcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaParcelasMouseClicked
        // TODO add your handling code here:
        datosParcela(mdb.devuelveId(
                "select id from parcelas where clave_parcela = '" + tablaParcelas.getValueAt(tablaParcelas.getSelectedRow(), 2) + "'"));
    }//GEN-LAST:event_tablaParcelasMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            // TODO add your handling code here:
            decode(mdb.devuelveUnDato("select archivo from documentosparcelas where ruta='" + rutaPDF + "'"));
        } catch (IOException ex) {
            Logger.getLogger(jdFormularioParcelas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed
    String rutaPDF = "";
    private void tablaDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDocumentosMouseClicked
        // TODO add your handling code here:
        rutaPDF = tablaDocumentos.getValueAt(tablaDocumentos.getSelectedRow(), 2) + "";
    }//GEN-LAST:event_tablaDocumentosMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        String tipoSuelo = "0", tipoSombra = "0", tipoProduccion = "0", dato;

        tipoSuelo = mdb.devuelveId("select id from tiposuelo where descripcion='" + comboSuelo.getSelectedItem() + "'");
        tipoSombra = mdb.devuelveId("select id from tiposombra where descripcion='" + comboSombra.getSelectedItem() + "'");
        tipoProduccion = mdb.devuelveId("select id from tipoproduccion where descripcion='" + comboSistemaP.getSelectedItem() + "'");

        if (tipoSuelo == null || tipoSuelo.equals("")) {
            tipoSuelo = "0";
        }

        if (tipoSombra == null || tipoSombra.equals("")) {
            tipoSombra = "0";
        }

        if (tipoProduccion == null || tipoProduccion.equals("")) {
            tipoProduccion = "0";
        }

        dato = mdb.devuelveUnDato("select t.descripcion from parcelas p "
                + "inner join tiposuelo t on (p.id_tiposuelo = t.id) "
                + "where p.id=21");

        JOptionPane.showMessageDialog(null, "Dato es = " + dato);
    }//GEN-LAST:event_jButton13ActionPerformed

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
            java.util.logging.Logger.getLogger(jdFormularioParcelas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdFormularioParcelas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdFormularioParcelas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdFormularioParcelas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboArchivos;
    private javax.swing.JComboBox<String> comboCertificado;
    private javax.swing.JComboBox<String> comboCultivos;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboLocalidad;
    private javax.swing.JComboBox<String> comboMunicipio;
    private javax.swing.JComboBox<String> comboPais;
    private javax.swing.JComboBox<String> comboSector;
    private javax.swing.JComboBox<String> comboSistemaP;
    private javax.swing.JComboBox<String> comboSociedades;
    private javax.swing.JComboBox<String> comboSombra;
    private javax.swing.JComboBox<String> comboSuelo;
    private javax.swing.JComboBox<String> comboVariedad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCURP;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRFC;
    private javax.swing.JLabel lblTelFijo;
    private javax.swing.JLabel lblTelMov;
    private javax.swing.JTable tablaCertificado;
    private javax.swing.JTable tablaCultivos;
    private javax.swing.JTable tablaDocumentos;
    private javax.swing.JTable tablaParcelas;
    private javax.swing.JTable tablaVariedades;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCafetos;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtLatitud;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreArchivo;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPorcentajeCultivos;
    private javax.swing.JTextField txtPorcentajeVariedad;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
