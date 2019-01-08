/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formas_Personas;

import Metodos_Configuraciones.metodosDatosBasicos;
import Metodos_Configuraciones.validaConfi;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Carlos Valdez
 */
public class jdDetallePersona1 extends javax.swing.JDialog {

    /**
     * Creates new form jdDetallePersona1
     */
    metodosDatosBasicos mdb;
    jdFormularioProductor formProd;
    jdAsignacionesPersonas jdAP;
    jdSociedadesPersonas formSoc;
    jpPersonas1 jpDP;
    validaConfi valiConf;
    private JPanel contentPane;
    String idPersona, tipo, tipoPersona;
    File fichero = null;
    Connection cn;

    public jdDetallePersona1(java.awt.Frame parent, boolean modal, String tipo, String idPersona, String tipoPersona, Connection c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cn = c;
        mdb = new metodosDatosBasicos(cn);
        valiConf = new validaConfi();
        this.idPersona = idPersona;
        this.tipoPersona = tipoPersona;
        this.tipo = tipo;
        //pFisica.setSelected(true);

        rellenarInformacion();
    }

    public void rellenarInformacion() {
        //    try {
        //EXPLICACION DE VARIABLE "TIPO" = TIPO DE PROCESO
        //Comparación de tipo de proceso (Visualizacion/Modificacion = 2 | Nuevo = 1)
        if (tipo.equals("2")) {
            //Comparacion de tipo de persona para realizar consulta en su tabla especifica.
            if (tipoPersona.equals("1")) {

                String ds = mdb.generadorStrings("select p.nombrecorto \n"
                        + "from sociedadespersona a\n "
                        + "inner join personam p on (a.ID_asociado=p.ID)\n "
                        + "where a.id_persona= " + idPersona + " and a.tipoPersona=1 order by p.razonsocial asc").replace("#", ", ");

                lblSociedades.setText("Sociedad: " + ds);

                String[] datos = mdb.cargarDatosFormularioPersonas("select nombre, apellidopaterno, apellidomaterno, "
                        + "registrodepoblacion, identificacionfiscal, id_genero,telefono, telefonomovil, direccion, codigopostal, "
                        + "id_pais, id_estado, id_municipio, id_localidad, id_ejidocolonia, foto from personaf where id=" + idPersona, 16).split("¬");

                /*   System.out.println("DATOOOOOOOOOOOOOOOOS HERE AQUI VEEELOS PRRO \n" + mdb.cargarDatosFormularioPersonas("select nombre, apellidopaterno, apellidomaterno, "
                        + "registrodepoblacion, identificacionfiscal, id_genero,telefono, telefonomovil, direccion, codigopostal, "
                        + "id_pais, id_estado, id_municipio, id_localidad, id_ejidocolonia, foto from personaf where id=" + idPersona, 16));
                 */
                pFisica.setSelected(true);

                txtNombre.setText(datos[0]);
                txtApPat.setText(datos[1]);
                txtApMat.setText(datos[2]);

                txtCURP.setText(datos[3]);
                txtRFC.setText(datos[4]);

                if (datos[5].equals("1")) {
                    radioM.setSelected(true);
                    radioF.setSelected(false);
                } else {
                    radioM.setSelected(false);
                    radioF.setSelected(true);
                }

                txtTelefono.setText(datos[6]);
                txtTelefono1.setText(datos[7]);
                txtDireccion.setText(datos[8]);
                txtCP.setText(datos[9]);

                txtRuta.setText(datos[15]);

                if (mdb.devuelveId("select a.id from asignacionespersona a\n "
                        + "inner join puestos p on (a.id_puesto=p.ID)\n "
                        + "where a.id_persona=" + idPersona + " and p.Descripcion='Productor' ").equals("")) {
                    tProductor.setSelected(false);
                } else {
                    tProductor.setSelected(true);
                }

                if (mdb.devuelveId("select a.id from asignacionespersona a\n "
                        + "inner join puestos p on (a.id_puesto=p.ID)\n "
                        + "where a.id_persona=" + idPersona + " and p.Descripcion='Socio' ").equals("")) {
                    tSocio.setSelected(false);
                } else {
                    tSocio.setSelected(true);
                    jButton6.setEnabled(true);
                }

                if (!txtRuta.getText().equals("NO") || datos[15].equals("")) {
                    BufferedImage img = null;
                    img = decodeToImage(datos[15]);
                    ImageIcon icon = new ImageIcon(img);
                    Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                    lbFoto.setText(null);
                    lbFoto.setIcon(icono);
                }
                apagarCampos();
            } //Tipo de persona moral = 2
            else if (tipoPersona.equals("2")) {

                String[] datos = mdb.cargarDatosFormularioPersonas("select razonsocial, nombrecorto, clavecorte, "
                        + "identificacionFiscal,telefono, direccion,email,paginaweb, codigopostal, "
                        + "id_pais, id_estado, id_municipio, id_localidad, id_ejidocolonia, foto from personam where id=" + idPersona, 15).split("¬");

                /*                System.out.println(mdb.cargarDatosFormularioPersonas("select razonsocial, nombrecorto, clavecorte, "
                        + "identificacionFiscal,telefono, direccion,email,paginaweb, codigopostal, "
                        + "id_pais, id_estado, id_municipio, id_localidad, id_ejidocolonia, foto from personam where id=" + idPersona, 15));
                 */
                pFisica.setSelected(false);
                pMoral.setSelected(true);

                txtNombre.setText(datos[0]);
                txtApPat.setText(datos[1]);
                txtApMat.setText(datos[2]);
                txtRFC.setText(datos[3]);
                txtTelefono.setText(datos[4]);
                txtDireccion.setText(datos[5]);
                txtTelefono1.setText(datos[6]);
                txtCURP.setText(datos[7]);
                txtCP.setText(datos[8]);

                txtRuta.setText(datos[14]);

                if (!txtRuta.getText().equals("NO") || datos[14].equals("")) {
                    BufferedImage img = null;
                    img = decodeToImage(datos[14]);
                    ImageIcon icon = new ImageIcon(img);
                    Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                    lbFoto.setText(null);
                    lbFoto.setIcon(icono);
                }

                if (mdb.devuelveId("select a.id from asignacionespersona a\n "
                        + "inner join puestos p on (a.id_puesto=p.ID)\n "
                        + "where a.id_persona=" + idPersona + " and p.Descripcion='Productor' ").equals("")) {
                    tProductor.setSelected(false);
                } else {
                    tProductor.setSelected(true);
                    jButton6.setEnabled(true);
                }

                if (mdb.devuelveId("select a.id from asignacionespersona a\n "
                        + "inner join puestos p on (a.id_puesto=p.ID)\n "
                        + "where a.id_persona=" + idPersona + " and p.Descripcion='Socio' ").equals("")) {
                    tSocio.setSelected(false);
                } else {
                    tSocio.setSelected(true);
                }
                apagarCampos();
            }
            /*else {
                pFisica.setSelected(true);
            }
             */
        } else {
            pFisica.setSelected(true);
        }
        rellenarCombos();
        //  } catch (Exception e) {
        //  }
    }

    public void apagarCampos() {
        //Aplicar bloqueo de campos para visualización.
        txtNombre.setEnabled(false);
        txtApPat.setEnabled(false);
        txtApMat.setEnabled(false);
        radioM.setEnabled(false);
        pFisica.setEnabled(false);
        tSocio.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtCP.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtTelefono1.setEnabled(false);
        comboPais.setEnabled(false);
        comboMunicipio.setEnabled(false);
        comboLocalidad.setEnabled(false);
        comboEstado.setEnabled(false);
        comboColonia.setEnabled(false);
        radioF.setEnabled(false);
        tProductor.setEnabled(false);
        pMoral.setEnabled(false);
        txtRuta.setEnabled(false);
        jButton4.setEnabled(false);
        jButton1.setEnabled(false);
        txtRFC.setEnabled(false);
        txtCURP.setEnabled(false);
    }

    public void rellenarCombos() {
        String pais, estado, municipio, localidad, colonia;
        String[] datos;

        datos = mdb.cargarCombos("SELECT descripcion from pais").split("#");
        comboPais.setModel(new DefaultComboBoxModel((Object[]) datos));
        pais = comboPais.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + pais + "'").split("#");
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

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from ejidocolonia m \n"
                + "inner join localidad e on (m.id_localidad=e.id) \n"
                + "where e.Descripcion='" + localidad + "'").split("#");
        comboColonia.setModel(new DefaultComboBoxModel((Object[]) datos));
    }

    void cargar() {
        BufferedImage img = null;
        String sql = "SELECT foto FROM persona where id=4";

        String imagen_string = null;

        try {
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                imagen_string = rs.getString("foto");
            }
            if (imagen_string.equals("")) {
                lbFoto.setIcon(null);
                lbFoto.setText("No existe imagen del producto");
            } else {
                img = decodeToImage(imagen_string);
                ImageIcon icon = new ImageIcon(img);
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
                lbFoto.setText(null);
                lbFoto.setIcon(icono);
            }
        } catch (SQLException ex) {
        }
    }

    public void tipoFormulario() {
        if (tipo.equals("2")) {
            jButton5.setEnabled(true);
            jButton6.setEnabled(true);
        }

        if (pMoral.isSelected()) {
            txtNombre.setEnabled(true);
            txtApPat.setEnabled(true);
            txtApMat.setEnabled(true);
            radioF.setEnabled(false);
            radioM.setEnabled(false);
            txtCURP.setEnabled(true);
            txtRFC.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtCP.setEnabled(true);
            txtTelefono.setEnabled(true);
            txtTelefono1.setEnabled(true);
            pMoral.setEnabled(true);
            pFisica.setEnabled(true);
            tSocio.setEnabled(true);
            tProductor.setEnabled(true);
            comboPais.setEnabled(true);
            comboEstado.setEnabled(true);
            comboMunicipio.setEnabled(true);
            comboLocalidad.setEnabled(true);
            comboColonia.setEnabled(true);
            txtRuta.setEnabled(true);
            jButton4.setEnabled(true);
            jButton1.setEnabled(true);
            jLabel1.setText("Razon Social");
            jLabel2.setText("Nombre Corto");
            jLabel3.setText("Clave Corte");
            jLabel16.setText("Pagina Web");
            jLabel8.setText("Email");
        } else {
            jLabel1.setText("Nombre");
            jLabel2.setText("Apellido Paterno");
            jLabel3.setText("Apellido Materno");
            jLabel16.setText("CURP");
            jLabel8.setText("Telefono Movil");
            txtNombre.setEnabled(true);
            txtApPat.setEnabled(true);
            txtApMat.setEnabled(true);
            radioM.setEnabled(true);
            pFisica.setEnabled(true);
            tSocio.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtCP.setEnabled(true);
            txtTelefono.setEnabled(true);
            txtTelefono1.setEnabled(true);
            txtCURP.setEnabled(true);
            txtRFC.setEnabled(true);
            comboPais.setEnabled(true);
            comboMunicipio.setEnabled(true);
            comboLocalidad.setEnabled(true);
            comboEstado.setEnabled(true);
            comboColonia.setEnabled(true);
            radioF.setEnabled(true);
            tProductor.setEnabled(true);
            pMoral.setEnabled(true);
            txtRuta.setEnabled(true);
            jButton4.setEnabled(true);
            jButton1.setEnabled(true);
        }
    }

    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static String encodeToString(BufferedImage image) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public Boolean validarCampos() {
        Boolean valor = null;

        if (!tSocio.isSelected() && !tProductor.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione Socio o Productor");
            return false;
        } else {
            valor = true;
        }

        if (txtNombre.getText().length() == 0 || txtApPat.getText().length() == 0 || txtApMat.getText().length() == 0
                || txtDireccion.getText().length() == 0 || txtRFC.getText().length() == 0 || txtCURP.getText().length() == 0
                || txtTelefono.getText().length() == 0 || txtTelefono1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Existen Campos Vacios");
            return false;
        } else {
            valor = true;
        }

        if (!radioM.isSelected() && !radioF.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione un Genero");
            return false;
        } else {
            valor = true;
        }

        if (comboPais.getSelectedItem().equals("Seleccione..")
                || comboEstado.getSelectedItem().equals("Seleccione..")
                || comboMunicipio.getSelectedItem().equals("Seleccione..")
                || comboLocalidad.getSelectedItem().equals("Seleccione..")
                || comboColonia.getSelectedItem().equals("Seleccione..")) {
            JOptionPane.showMessageDialog(null, "Verifica Ubicacion Geografica");
            return false;
        } else {
            valor = true;
        }
        return valor;
    }

    public void tipoProceso() {
        try {
            String image_string = "NO", atributoS = "0", atributoP = "0", genero = "", asignacion = "";
            if (!txtRuta.getText().equals("NO")) {
                try {
                    BufferedImage img = ImageIO.read(new File(fichero.toString()));

                    image_string = encodeToString(img);
                } catch (Exception e) {
                }
            }

            String estadoSocio = "0";
            if (tSocio.isSelected() && tProductor.isSelected()) {
                //Preguntar y Añadir Sociedad
                estadoSocio = "1";
            } else if (!tSocio.isSelected() && tProductor.isSelected()) {
                //Preguntar y Añadir Sociedad a quien entregará Cafe
                estadoSocio = "0";
            } else if (tSocio.isSelected() && !tProductor.isSelected()) {
                //Preguntar y añadir Sociedad
                estadoSocio = "1";
            }

            if (pFisica.isSelected()) {
                tipoPersona = "1";
            } else {
                tipoPersona = "2";
            }

            if (tSocio.isSelected()) {
                atributoS = "1";
                asignacion = "Socio";
            }
            if (tProductor.isSelected()) {
                atributoP = "1";
                asignacion = "Productor";
            }

            if (radioM.isSelected()) {
                genero = "1";
            } else {
                genero = "2";
            }

            if (tipo.equals("1")) {
                if (tipoPersona.equals("1")) {
                    mdb.insertarBasicos("INSERT INTO personaf VALUES(null, '" + txtNombre.getText() + "', "
                            + "'" + txtApPat.getText() + "', '" + txtApMat.getText() + "', '" + txtCURP.getText() + "','" + txtRFC.getText() + "', "
                            + "" + genero + ", '" + txtTelefono.getText() + "','" + txtTelefono1.getText() + "', "
                            + "'" + txtDireccion.getText() + "', '" + txtCP.getText() + "', "
                            + "'" + mdb.devuelveId("select id from pais where descripcion='" + comboPais.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from estado where descripcion='" + comboEstado.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from municipio where descripcion='" + comboMunicipio.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from ejidocolonia where descripcion='" + comboColonia.getSelectedItem() + "' ") + "',"
                            + " '" + image_string + "', 1,1,current_date(),current_time(),1,1,1,1," + estadoSocio + ") ");

                    idPersona = mdb.devuelveId("SELECT id FROM personaf ORDER BY id DESC LIMIT 1");

                    if (tSocio.isSelected()) {
                        //atributoS = "1";
                        asignacion = "Socio";
                        mdb.insertarBasicos("insert into asignacionespersona "
                                + "values (null, " + idPersona + ", " + mdb.devuelveId("select id from puestos where descripcion='" + asignacion + "'") + ")");
                    }
                    if (tProductor.isSelected()) {
                        //atributoP = "1";
                        asignacion = "Productor";
                        mdb.insertarBasicos("insert into asignacionespersona "
                                + "values (null, " + idPersona + ", " + mdb.devuelveId("select id from puestos where descripcion='" + asignacion + "'") + ")");
                    }
                    detalleAsignaciones(idPersona, "1");
                    tipo = "2";
                } else {
                    mdb.insertarBasicos("INSERT INTO personam VALUES(null,  "
                            + "'" + txtNombre.getText() + "', '" + txtApPat.getText() + "', '" + txtApMat.getText() + "','" + txtRFC.getText() + "', "
                            + "'" + txtTelefono.getText() + "','" + txtTelefono1.getText() + "','" + txtCURP.getText() + "', "
                            + "'" + txtDireccion.getText() + "', '" + txtCP.getText() + "', "
                            + "'" + mdb.devuelveId("select id from pais where descripcion='" + comboPais.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from estado where descripcion='" + comboEstado.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from municipio where descripcion='" + comboMunicipio.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "' ") + "',"
                            + "'" + mdb.devuelveId("select id from ejidocolonia where descripcion='" + comboColonia.getSelectedItem() + "' ") + "',"
                            + " '" + image_string + "', 1,1,current_date(),current_time(),1,1,1,1," + estadoSocio + ")");

                    idPersona = mdb.devuelveId("SELECT id FROM personam ORDER BY id DESC LIMIT 1");
                    tipo = "2";
                    if (tSocio.isSelected()) {
                        //atributoS = "1";
                        asignacion = "Socio";
                        mdb.insertarBasicos("insert into asignacionespersona "
                                + "values (null," + idPersona + ", " + mdb.devuelveId("select id from puestos where descripcion='" + asignacion + "'") + ")");

                    }
                    if (tProductor.isSelected()) {
                        //atributoP = "1";
                        asignacion = "Productor";
                        mdb.insertarBasicos("insert into asignacionespersona "
                                + "values (null," + idPersona + ", " + mdb.devuelveId("select id from puestos where descripcion='" + asignacion + "'") + ")");
                    }
                    detalleAsignaciones(idPersona, "2");
                }
                /*if (mdb.comprobarExistencia("select id from persona "
                        + "where (nombre='" + txtNombre.getText() + "' and apellidoPaterno='" + txtApPat.getText() + "' and apellidoMaterno='" + txtApMat.getText() + "' ) "
                        + "OR razonsocial='" + txtNombre.getText() + "'") != null) {

                    JOptionPane.showMessageDialog(null, "Asignaciones Desbloqueadas ");
                    jButton5.setEnabled(true);
                }*/
                jButton5.setEnabled(true);
                jButton6.setEnabled(true);
            } else {
                //AQUI METODOS PARA ACTUALIZACION DE DATOS
                if (tipoPersona.equals("1")) {
                    mdb.actualizarBasicos("UPDATE personaf SET nombre='" + txtNombre.getText() + "' , "
                            + "apellidoPaterno='" + txtApPat.getText() + "', apellidoMaterno='" + txtApMat.getText() + "',  "
                            + " id_genero=" + genero + ", telefono='" + txtTelefono.getText() + "', telefonoMovil='" + txtTelefono1.getText() + "',"
                            + " direccion='" + txtDireccion.getText() + "', codigopostal='" + txtCP.getText() + "', "
                            + "identificacionfiscal='" + txtRFC.getText() + "', paginaweb='" + txtCURP.getText() + "', "
                            + " id_pais='" + mdb.devuelveId("select id from pais where descripcion='" + comboPais.getSelectedItem() + "' ") + "',"
                            + " id_estado='" + mdb.devuelveId("select id from estado where descripcion='" + comboEstado.getSelectedItem() + "' ") + "',"
                            + " id_municipio='" + mdb.devuelveId("select id from municipio where descripcion='" + comboMunicipio.getSelectedItem() + "' ") + "',"
                            + " id_localidad='" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "' ") + "',"
                            + " id_ejidocolonia='" + mdb.devuelveId("select id from ejidocolonia where descripcion='" + comboColonia.getSelectedItem() + "' ") + "',"
                            + " foto='" + image_string + "' "
                            + " where id=" + idPersona);
                } else {
                    mdb.actualizarBasicos("UPDATE personam SET razonsocial='" + txtNombre.getText() + "',  "
                            + "nombrecorto='" + txtApPat.getText() + "', clavecorte='" + txtApMat.getText() + "', "
                            + "identificacionfiscal='" + txtRFC.getText() + "', paginaweb='" + txtCURP.getText() + "', "
                            + " telefono='" + txtTelefono.getText() + "', email='" + txtTelefono1.getText() + "',"
                            + " direccion='" + txtDireccion.getText() + "', codigopostal='" + txtCP.getText() + "', "
                            + " id_pais='" + mdb.devuelveId("select id from pais where descripcion='" + comboPais.getSelectedItem() + "' ") + "',"
                            + " id_estado='" + mdb.devuelveId("select id from estado where descripcion='" + comboEstado.getSelectedItem() + "' ") + "',"
                            + " id_municipio='" + mdb.devuelveId("select id from municipio where descripcion='" + comboMunicipio.getSelectedItem() + "' ") + "',"
                            + " id_localidad='" + mdb.devuelveId("select id from localidad where descripcion='" + comboLocalidad.getSelectedItem() + "' ") + "',"
                            + " id_ejidocolonia='" + mdb.devuelveId("select id from ejidocolonia where descripcion='" + comboColonia.getSelectedItem() + "' ") + "',"
                            + " foto='" + image_string + "' "
                            + " where id=" + idPersona);
                }
            }
            jpDP.llenarTabla();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void detalleAsignaciones(String id, String tipoP) {
        String nombre = "";

        if (tSocio.isSelected() && tProductor.isSelected()) {
            //Preguntar y Añadir Sociedad
            formSoc = new jdSociedadesPersonas(null, true, "1", tipoP, "¿A que sociedad perteneces?", id, cn);
            formSoc.setVisible(true);
        } else if (!tSocio.isSelected() && tProductor.isSelected()) {
            //Preguntar y Añadir Sociedad a quien entregará Cafe
            formSoc = new jdSociedadesPersonas(null, true, "1", tipoP, "¿A que sociedad entregaras tu cafe?", id, cn);
            formSoc.setVisible(true);
        } else if (tSocio.isSelected() && !tProductor.isSelected()) {
            //Preguntar y añadir Sociedad
            formSoc = new jdSociedadesPersonas(null, true, "1", tipoP, "¿A que sociedad perteneces?", id, cn);
            formSoc.setVisible(true);
        }
        /*if (tSocio.isSelected()) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas añadir la información de 'Sociedades' ?",
                    null, JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                formSoc = new jdSociedadesPersonas(null, true, "1", tipoP, "NOMBRE", id, cn);
                formSoc.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Información Pendiente");
            }
        }
        if (tProductor.isSelected()) {
            int result = JOptionPane.showConfirmDialog(null, "¿Deseas añadir la información de 'Productor' ?",
                    null, JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {

                if (tipoPersona.equals("1")) {
                    nombre = txtNombre.getText() + " " + txtApPat.getText() + " " + txtApMat.getText();
                } else if (tipoPersona.equals("2")) {
                    nombre = txtNombre.getText();
                }

                formProd = new jdFormularioProductor(null, true, id, nombre, cn);
                formProd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Información Pendiente");
            }
        }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbFoto = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        radioF = new javax.swing.JRadioButton();
        radioM = new javax.swing.JRadioButton();
        txtTelefono1 = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtApPat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApMat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        txtCURP = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        comboPais = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboMunicipio = new javax.swing.JComboBox<>();
        comboLocalidad = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        comboColonia = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        tProductor = new javax.swing.JCheckBox();
        tSocio = new javax.swing.JCheckBox();
        pFisica = new javax.swing.JRadioButton();
        pMoral = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lblSociedades = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Personas");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Fotografia"));

        txtRuta.setEditable(false);
        txtRuta.setMaximumSize(new java.awt.Dimension(6, 20));
        txtRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaActionPerformed(evt);
            }
        });

        jButton4.setText("Abrir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel17.setText("Ruta del Archivo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(12, 12, 12))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Basicos"));

        buttonGroup2.add(radioF);
        radioF.setText("Femenino");

        buttonGroup2.add(radioM);
        radioM.setText("Masculino");

        txtTelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefono1ActionPerformed(evt);
            }
        });
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel7.setText("Telefono Fijo");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel5.setText("Dirección");

        jLabel1.setText("Nombre");

        txtApPat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApPatActionPerformed(evt);
            }
        });
        txtApPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApPatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApPatKeyTyped(evt);
            }
        });

        jLabel3.setText("Apellido Materno");

        txtApMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApMatKeyReleased(evt);
            }
        });

        jLabel6.setText("Codigo Postal");

        txtCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPActionPerformed(evt);
            }
        });

        jLabel2.setText("Apellido Paterno");

        jLabel8.setText("Telefono Movil");

        jLabel4.setText("Genero");

        jLabel15.setText("RFC");

        txtRFC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRFCKeyReleased(evt);
            }
        });

        txtCURP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCURPKeyReleased(evt);
            }
        });

        jLabel16.setText("CURP");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel5)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtApPat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)))
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(radioM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioF))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtApMat)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6)
                                .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioM)
                            .addComponent(radioF))))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicación Geografica"));

        comboPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));
        comboPais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPaisItemStateChanged(evt);
            }
        });

        jLabel9.setText("Pais");

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
        comboLocalidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboLocalidadItemStateChanged(evt);
            }
        });

        jLabel12.setText("Localidad");

        jLabel13.setText("Colonia/Ejido");

        comboColonia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(comboColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Atributos"));

        tProductor.setText("Productor");

        tSocio.setText("Socio");
        tSocio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tSocioItemStateChanged(evt);
            }
        });

        buttonGroup1.add(pFisica);
        pFisica.setText("Persona Fisica");
        pFisica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pFisicaItemStateChanged(evt);
            }
        });

        buttonGroup1.add(pMoral);
        pMoral.setText("Persona Moral");
        pMoral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pMoralItemStateChanged(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pFisica)
                    .addComponent(pMoral))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tSocio)
                    .addComponent(tProductor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(pFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pMoral)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tSocio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tProductor))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(4, 4, 4))
        );

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        jButton5.setText("Asignaciones");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Sociedades");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblSociedades.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSociedades))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSociedades))
        );

        jButton7.setText("Limpiar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton7))
                        .addGap(24, 24, 24))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Abrir catalogo de asignaciones
        String id = "";
        if (tipoPersona.equals("1")) {
            id = mdb.comprobarExistencia("select id from personaf "
                    + "where (nombre='" + txtNombre.getText() + "' and apellidoPaterno='" + txtApPat.getText() + "' and apellidoMaterno='" + txtApMat.getText() + "')");
        } else if (tipoPersona.equals("2")) {
            id = mdb.comprobarExistencia("select id from personam "
                    + "where (razonsocial='" + txtNombre.getText() + "')");
        }
        jdAP = new jdAsignacionesPersonas(null, true, tipoPersona, txtNombre.getText() + " " + txtApPat.getText() + " " + txtApMat.getText(), idPersona, cn);
        jdAP.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tipoFormulario();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validarCampos() == true) {
            tipoProceso();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pMoralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pMoralItemStateChanged
        // TODO add your handling code here:
        tipoFormulario();
    }//GEN-LAST:event_pMoralItemStateChanged

    private void pFisicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pFisicaItemStateChanged
        // TODO add your handling code here:
        tipoFormulario();
    }//GEN-LAST:event_pFisicaItemStateChanged

    private void tSocioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tSocioItemStateChanged

    }//GEN-LAST:event_tSocioItemStateChanged

    private void comboLocalidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboLocalidadItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String localidad;

        localidad = comboLocalidad.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from ejidocolonia m \n"
                + "inner join localidad e on (m.id_localidad=e.id) \n"
                + "where e.Descripcion='" + localidad + "'").split("#");
        comboColonia.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboLocalidadItemStateChanged

    private void comboMunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMunicipioItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String municipio, localidad;

        municipio = comboMunicipio.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from localidad m \n"
                + "inner join municipio e on (m.id_municipio=e.id) \n"
                + "where e.Descripcion='" + municipio + "'").split("#");
        comboLocalidad.setModel(new DefaultComboBoxModel((Object[]) datos));
        localidad = comboLocalidad.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from ejidocolonia m \n"
                + "inner join localidad e on (m.id_localidad=e.id) \n"
                + "where e.Descripcion='" + localidad + "'").split("#");
        comboColonia.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboMunicipioItemStateChanged

    private void comboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEstadoItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String estado, municipio, localidad;

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

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from ejidocolonia m \n"
                + "inner join localidad e on (m.id_localidad=e.id) \n"
                + "where e.Descripcion='" + localidad + "'").split("#");
        comboColonia.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboEstadoItemStateChanged

    private void comboPaisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPaisItemStateChanged
        // TODO add your handling code here:
        String[] datos;
        String pais, estado, municipio, localidad;

        pais = comboPais.getSelectedItem() + "";

        datos = mdb.cargarCombos("SELECT e.descripcion \n"
                + "from estado e \n"
                + "inner join pais p on (e.id_pais=p.id) \n"
                + "where p.Descripcion='" + pais + "'").split("#");
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

        datos = mdb.cargarCombos("SELECT m.descripcion \n"
                + "from ejidocolonia m \n"
                + "inner join localidad e on (m.id_localidad=e.id) \n"
                + "where e.Descripcion='" + localidad + "'").split("#");
        comboColonia.setModel(new DefaultComboBoxModel((Object[]) datos));
    }//GEN-LAST:event_comboPaisItemStateChanged

    private void txtCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPActionPerformed

    private void txtApPatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApPatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApPatActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
            ImageIcon icon = new ImageIcon(fichero.toString());
            //System.out.println(fichero.getName());
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbFoto.getWidth(), lbFoto.getHeight(), Image.SCALE_DEFAULT));
            lbFoto.setText(null);
            lbFoto.setIcon(icono);

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String nombre = "";
        if (tipoPersona.equals("1")) {
            nombre = txtNombre.getText() + " " + txtApPat.getText() + " " + txtApMat.getText();
        } else if (tipoPersona.equals("2")) {
            nombre = txtNombre.getText();
        }

        formSoc = new jdSociedadesPersonas(null, true, tipo, tipoPersona, nombre, idPersona, cn);
        formSoc.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jLabel1.setText("Nombre");
        jLabel2.setText("Apellido Paterno");
        jLabel3.setText("Apellido Materno");
        jLabel16.setText("CURP");
        jLabel8.setText("Telefono Movil");
        txtNombre.setEnabled(true);
        txtApPat.setEnabled(true);
        txtApMat.setEnabled(true);
        radioM.setEnabled(true);
        pFisica.setEnabled(true);
        tSocio.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtCP.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtTelefono1.setEnabled(true);
        txtCURP.setEnabled(true);
        txtRFC.setEnabled(true);
        comboPais.setEnabled(true);
        comboMunicipio.setEnabled(true);
        comboLocalidad.setEnabled(true);
        comboEstado.setEnabled(true);
        comboColonia.setEnabled(true);
        radioF.setEnabled(true);
        tProductor.setEnabled(true);
        pMoral.setEnabled(true);
        txtRuta.setEnabled(true);
        jButton4.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);

        txtNombre.setText("");
        txtApPat.setText("");
        txtApMat.setText("");
        txtDireccion.setText("");
        txtCP.setText("");
        txtTelefono.setText("");
        txtTelefono1.setText("");
        txtCURP.setText("");
        txtRFC.setText("");
        txtRuta.setText("");

        tSocio.setSelected(false);
        tProductor.setSelected(false);

        idPersona = "";
        tipo = "";


    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        if (txtNombre.getText().length() != 0) {
            txtNombre.setText(valiConf.primerLetraMayuscula(txtNombre.getText()).replace("S/n", "S/N"));
            txtNombre.setText(valiConf.primerLetraMayuscula(txtNombre.getText()).replace("S/d", "S/D"));
            txtNombre.setText(valiConf.primerLetraMayuscula(txtNombre.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApPatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApPatKeyReleased
        // TODO add your handling code here:
        if (txtApPat.getText().length() != 0) {
            txtApPat.setText(valiConf.primerLetraMayuscula(txtApPat.getText()).replace("S/n", "S/N"));
            txtApPat.setText(valiConf.primerLetraMayuscula(txtApPat.getText()).replace("S/d", "S/D"));
            txtApPat.setText(valiConf.primerLetraMayuscula(txtApPat.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtApPatKeyReleased

    private void txtApMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApMatKeyReleased
        // TODO add your handling code here:
        if (txtApMat.getText().length() != 0) {
            txtApMat.setText(valiConf.primerLetraMayuscula(txtApMat.getText()).replace("S/n", "S/N"));
            txtApMat.setText(valiConf.primerLetraMayuscula(txtApMat.getText()).replace("S/d", "S/D"));
            txtApMat.setText(valiConf.primerLetraMayuscula(txtApMat.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtApMatKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        // TODO add your handling code here:
        if (txtDireccion.getText().length() != 0) {
            txtDireccion.setText(valiConf.primerLetraMayuscula(txtDireccion.getText()).replace("S/n", "S/N"));
            txtDireccion.setText(valiConf.primerLetraMayuscula(txtDireccion.getText()).replace("S/d", "S/D"));
            txtDireccion.setText(valiConf.primerLetraMayuscula(txtDireccion.getText()).replace("S/o", "S/O"));
        }
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApPatKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtApPatKeyTyped

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        // TODO add your handling code here:
        if (pFisica.isSelected()) {
            char c = evt.getKeyChar();
            if (Character.isLetter(c)) {
                getToolkit().beep();
                evt.consume();
            }
        } else {
            txtTelefono1.setText(txtTelefono1.getText().toLowerCase());
        }
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void txtRFCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRFCKeyReleased
        // TODO add your handling code here:
        txtRFC.setText(txtRFC.getText().toUpperCase());
    }//GEN-LAST:event_txtRFCKeyReleased

    private void txtCURPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCURPKeyReleased
        // TODO add your handling code here:
        if (pFisica.isSelected()) {
            txtCURP.setText(txtCURP.getText().toUpperCase());
        } else {
            txtCURP.setText(txtCURP.getText().toLowerCase());
        }
    }//GEN-LAST:event_txtCURPKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

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
            java.util.logging.Logger.getLogger(jdDetallePersona1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdDetallePersona1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdDetallePersona1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdDetallePersona1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
 /*        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdDetallePersona1 dialog = new jdDetallePersona1(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> comboColonia;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboLocalidad;
    private javax.swing.JComboBox<String> comboMunicipio;
    private javax.swing.JComboBox<String> comboPais;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lblSociedades;
    private javax.swing.JRadioButton pFisica;
    private javax.swing.JRadioButton pMoral;
    private javax.swing.JRadioButton radioF;
    private javax.swing.JRadioButton radioM;
    private javax.swing.JCheckBox tProductor;
    private javax.swing.JCheckBox tSocio;
    private javax.swing.JTextField txtApMat;
    private javax.swing.JTextField txtApPat;
    private javax.swing.JTextField txtCP;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    // End of variables declaration//GEN-END:variables
}
