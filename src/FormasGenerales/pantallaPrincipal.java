/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormasGenerales;

import Conexion.Conexion;
import FormasInternas.internalPrueba;
import FormasInternas.panelPrueba;
import Formas_BeneficioHumedo.jdSeleccionBeneficio;
import Formas_BeneficioHumedo.jpCortesRecibidos;
import Formas_BeneficioHumedo.jpLotesConfirmados;
import Formas_Configuraciones_BeneficioHumedo.jpActividadesBH;
import Formas_Configuraciones_BeneficioHumedo.jpAreaAlmacen;
import Formas_Configuraciones_BeneficioHumedo.jpClima;
import Formas_Configuraciones_BeneficioHumedo.jpMaquinaria;
import Formas_Configuraciones_BeneficioHumedo.jpRutas;
import Formas_Configuraciones_BeneficioHumedo.jpSobrante;
import Formas_Configuraciones_BeneficioHumedo.jpVehiculo;
import Formas_Configuraciones_DatosBasicos.jpColoniaEjido;
import Formas_Configuraciones_DatosBasicos.jpEstado;
import Formas_Configuraciones_DatosBasicos.jpFondo;
import Formas_Configuraciones_DatosBasicos.jpGiroEmpresa;
import Formas_Configuraciones_DatosBasicos.jpLocalidad;
import Formas_Configuraciones_DatosBasicos.jpMunicipio;
import Formas_Configuraciones_DatosBasicos.jpPais;
import Formas_Configuraciones_FincaCert.Certificados.CodComb;
import Formas_Configuraciones_FincaCert.Certificados.jpCertificado;
import Formas_Configuraciones_FincaCert.Certificados.jpCertificador;
import Formas_Configuraciones_FincaCert.Certificados.jpCodigosCertificados;
import Formas_Configuraciones_FincaCert.jpActividadesParcela;
import Formas_Configuraciones_FincaCert.jpCalidadSombra;
import Formas_Configuraciones_FincaCert.jpCultivos;
import Formas_Configuraciones_FincaCert.jpDiseñoPlantacion;
import Formas_Configuraciones_FincaCert.jpEnfermedadPlaga;
import Formas_Configuraciones_FincaCert.jpFauna;
import Formas_Configuraciones_FincaCert.jpFertilizante;
import Formas_Configuraciones_FincaCert.jpFlora;
import Formas_Configuraciones_FincaCert.jpHerbicida;
import Formas_Configuraciones_FincaCert.jpSistemaProduccion;
import Formas_Configuraciones_FincaCert.jpSuelos;
import Formas_Configuraciones_FincaCert.Certificados.jpTipoCertificacion;
import Formas_Configuraciones_FincaCert.jpCategoriaDeArchivos;
import Formas_Configuraciones_FincaCert.jpControlMaleza;
import Formas_Configuraciones_FincaCert.jpMaquinariaHerramienta;
import Formas_Configuraciones_FincaCert.jpMotivoPlantacion;
import Formas_Configuraciones_FincaCert.jpTipoAccion;
import Formas_Configuraciones_FincaCert.jpTipoPoda;
import Formas_Configuraciones_FincaCert.jpTipoSombra;
import Formas_Configuraciones_FincaCert.jpVariedadCafe;
import Formas_Configuraciones_Idiomas.jpIdioma;
import Formas_Configuraciones_Recepcion.jpCalificacionCereza;
import Formas_Configuraciones_Recepcion.jpCalificacionSeco;
import Formas_Configuraciones_Recepcion.jpEvaluaciones;
import Formas_Configuraciones_Recepcion.jpFactorForma;
import Formas_Configuraciones_Recepcion.jpFormaCafe;
import Formas_Configuraciones_Recepcion.jpFormaProceso;
import Formas_Configuraciones_Recepcion.jpMaximoRendimiento;
import Formas_Configuraciones_Recepcion.jpMaximoTamañoPromedio;
import Formas_Configuraciones_Recepcion.jpProcesoCafe;
import Formas_Configuraciones_Recepcion.jpReglasEvaluacion;
import Formas_Configuraciones_Recepcion.jpTipoEvaluacion;
import Formas_Configuraciones_Seguridad.jpPerfiles;
import Formas_Configuraciones_Sociedades.jpPuestos;
import Formas_Configuraciones_Sociedades.jpRetenciones;
import Formas_FincaCert.jpProductores;
import Formas_Personas.jpPersonas;
import Formas_Personas.jpPersonas;
import Formas_Recepcion.jdRecibos;
import Formas_Recepcion.jdSeleccionRecepcion;
import Formas_Recepcion.jpCortesDelDia;
import Formas_Recepcion.jpCortesDelDia;
import Formas_Recepcion.jpCortesEnviados;
import Formas_Recepcion.jpRecibos;
import Formas_Sociedades.jpAlmacenes;
import Formas_Sociedades.jpBeneficiosH;
import Formas_Sociedades.jpCatalogoSociedades;
import Formas_Sociedades.jpLocalidadesSociedades;
import Formas_Sociedades.jpRecepcion;
import Idioma.Propiedades;
import MetodosGenerales.JComboCheckBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author Carlos Valdez
 */
public class pantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form pantallaPrincipal
     */
    Connection cn;
    panelPrueba pP;
    jpTipoSombra jpTS;
    Propiedades idioma;

    public pantallaPrincipal() throws ParseException {
        initComponents();
        setLocationRelativeTo(null);
        cn = (new Conexion()).conectar();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //jButton3.setVisible(false);
        //jButton4.setVisible(false);
        //jButton5.setVisible(false);
        //jButton6.setVisible(false);
        btnLaboratorio.setVisible(false);
        idioma = new Propiedades(comboIdioma.getSelectedItem() + "");

        setTitle(idioma.getProperty("TituloVentanaPrincipal"));
        btnConfigurador.setBackground(new Color(26, 66, 21));

        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        //render.setLeafIcon(new ImageIcon(this.getClass().getResource("../Imagenes/database.png")));
        //render.setOpenIcon(new ImageIcon(this.getClass().getResource("../Imagenes/database.png")));
        //render.setClosedIcon(new ImageIcon(this.getClass().getResource("../Imagenes/database.png")));

    }

    public void cambiarIdioma(String I) {

        idioma = new Propiedades(I);

        btnConfigurador.setText(idioma.getProperty("Configurador"));
        btnPersonas.setText(idioma.getProperty("Personas"));
        btnFincaCert.setText(idioma.getProperty("FincaCert"));
        btnRecepcion.setText(idioma.getProperty("Recepcion"));
        btnBH.setText(idioma.getProperty("BeneficioHumedo"));
        btnLaboratorio.setText(idioma.getProperty("Laboratorio"));
        btnSociedades.setText(idioma.getProperty("Sociedades"));
        jLabel3.setText(idioma.getProperty("Idioma"));
        jLabel1.setText(idioma.getProperty("Bienvenido"));
        setTitle(idioma.getProperty("TituloVentanaPrincipal"));

    }

    public void pintarPanel(String tipo) throws ParseException {
        Dimension size = panelPrincipal.getSize();
        //Tamaño Manual = 830, 570
        //Propiedades idioma = new Propiedades(comboIdioma.getSelectedItem() + "");
        //String tipo2 = idioma.getProperty("Pais");
        //JOptionPane.showMessageDialog(null, "Valor " + tipo2);
        String idiomaActual = comboIdioma.getSelectedItem() + "";

        if (idioma.getProperty("Pais").equals(tipo)) {
            //JOptionPane.showMessageDialog(null, "Entre");
            jpPais jpP = new jpPais(cn, idiomaActual);
            jpP.setSize(size);
            jpP.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpP);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Sociedades").equals(tipo)) {
            jpCatalogoSociedades jpCPre = new jpCatalogoSociedades(cn, idiomaActual);
            jpCPre.setSize(size);
            jpCPre.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCPre);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Estado").equals(tipo)) {
            jpEstado jpE = new jpEstado(cn, idiomaActual);
            jpE.setSize(size);
            jpE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Municipio").equals(tipo)) {
            jpMunicipio jpM = new jpMunicipio(cn, idiomaActual);
            jpM.setSize(size);
            jpM.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpM);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Localidad").equals(tipo)) {
            jpLocalidad jpL = new jpLocalidad(cn, idiomaActual);
            jpL.setSize(size);
            jpL.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpL);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ColoniaEjido").equals(tipo)) {
            jpColoniaEjido jpCE = new jpColoniaEjido(cn, idiomaActual);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("GiroEmpresa").equals(tipo)) {
            jpGiroEmpresa jpCE = new jpGiroEmpresa(cn, idiomaActual);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("TipoDeSombra").equals(tipo)) {
            jpTipoSombra jpCE = new jpTipoSombra(cn, idiomaActual);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CalidadDeSombra").equals(tipo)) {
            jpCalidadSombra jpCE = new jpCalidadSombra(cn, idiomaActual);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("VariedadDeCafe").equals(tipo)) {
            jpVariedadCafe jpCE = new jpVariedadCafe(cn, idiomaActual);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Cultivos").equals(tipo)) {
            jpCultivos jpCE = new jpCultivos(cn, idiomaActual);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("DisenoDePlantacion").equals(tipo)) {
            jpDiseñoPlantacion jpCE = new jpDiseñoPlantacion(cn);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Herbicida").equals(tipo)) {
            jpHerbicida jpCE = new jpHerbicida(cn);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("EnfermedadPlaga").equals(tipo)) {
            jpEnfermedadPlaga jpCE = new jpEnfermedadPlaga(cn);
            jpCE.setSize(size);
            jpCE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Fertilizante").equals(tipo)) {
            jpFertilizante jpFe = new jpFertilizante(cn);
            jpFe.setSize(size);
            jpFe.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpFe);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("SistemaDeProduccion").equals(tipo)) {
            jpSistemaProduccion jpSP = new jpSistemaProduccion(cn, idiomaActual);
            jpSP.setSize(size);
            jpSP.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpSP);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("TiposDeEvaluacion").equals(tipo)) {
            jpTipoEvaluacion jpTE = new jpTipoEvaluacion(cn);
            jpTE.setSize(size);
            jpTE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpTE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ActividadesParcela").equals(tipo)) {
            jpActividadesParcela jpAP = new jpActividadesParcela(cn);
            jpAP.setSize(size);
            jpAP.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpAP);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("FormaDeCafe").equals(tipo)) {
            jpFormaCafe jpFC = new jpFormaCafe(cn);
            jpFC.setSize(size);
            jpFC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpFC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("TipoDeCertificaciones").equals(tipo)) {
            jpTipoCertificacion jpTC = new jpTipoCertificacion(cn, idiomaActual);
            jpTC.setSize(size);
            jpTC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpTC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ActividadesBH").equals(tipo)) {
            jpActividadesBH jpABH = new jpActividadesBH(cn);
            jpABH.setSize(size);
            jpABH.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpABH);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Sobrantes").equals(tipo)) {
            jpSobrante jpS = new jpSobrante(cn);
            jpS.setSize(size);
            jpS.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpS);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ReglasDeEvaluacion").equals(tipo)) {
            jpReglasEvaluacion jpR = new jpReglasEvaluacion(cn);
            jpR.setSize(size);
            jpR.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpR);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CalificacionSeco").equals(tipo)) {
            jpCalificacionSeco jpCalS = new jpCalificacionSeco(cn);
            jpCalS.setSize(size);
            jpCalS.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCalS);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CalificacionCereza").equals(tipo)) {
            jpCalificacionCereza jpCC = new jpCalificacionCereza(cn);
            jpCC.setSize(size);
            jpCC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Evaluaciones").equals(tipo)) {
            jpEvaluaciones jpEva = new jpEvaluaciones(cn);
            jpEva.setSize(size);
            jpEva.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpEva);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Personas").equals(tipo)) {
            jpPersonas jpPer = new jpPersonas(cn, idiomaActual);
            jpPer.setSize(size);
            jpPer.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpPer);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Perfiles").equals(tipo)) {
            jpPerfiles jpPerf = new jpPerfiles(cn, idiomaActual);
            jpPerf.setSize(size);
            jpPerf.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpPerf);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Retenciones").equals(tipo)) {
            jpRetenciones jpRet = new jpRetenciones(cn, idiomaActual);
            jpRet.setSize(size);
            jpRet.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpRet);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Puestos").equals(tipo)) {
            jpPuestos jpPues = new jpPuestos(cn, idiomaActual);
            jpPues.setSize(size);
            jpPues.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpPues);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("TipoDeSuelo").equals(tipo)) {
            jpSuelos jpTSuelo = new jpSuelos(cn, idiomaActual);
            jpTSuelo.setSize(size);
            jpTSuelo.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpTSuelo);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("NativoFauna").equals(tipo)) {
            jpFauna jpNF = new jpFauna(cn, idiomaActual);
            jpNF.setSize(size);
            jpNF.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpNF);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("NativoFlora").equals(tipo)) {
            jpFlora jpNFlora = new jpFlora(cn, idiomaActual);
            jpNFlora.setSize(size);
            jpNFlora.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpNFlora);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Certificado").equals(tipo)) {
            jpCertificado jpCertificado = new jpCertificado(cn, idiomaActual);
            jpCertificado.setSize(size);
            jpCertificado.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCertificado);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Certificador").equals(tipo)) {
            jpCertificador jpCertificador = new jpCertificador(cn, idiomaActual);
            jpCertificador.setSize(size);
            jpCertificador.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCertificador);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("TipoDeAccion").equals(tipo)) {
            jpTipoAccion jpTipoA = new jpTipoAccion(cn);
            jpTipoA.setSize(size);
            jpTipoA.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpTipoA);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("MotivoPlantacion").equals(tipo)) {
            jpMotivoPlantacion jpMPlan = new jpMotivoPlantacion(cn);
            jpMPlan.setSize(size);
            jpMPlan.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpMPlan);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("MaquinariaHerramienta").equals(tipo)) {
            jpMaquinariaHerramienta jpMH = new jpMaquinariaHerramienta(cn);
            jpMH.setSize(size);
            jpMH.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpMH);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ControlMaleza").equals(tipo)) {
            jpControlMaleza jpCMaleza = new jpControlMaleza(cn);
            jpCMaleza.setSize(size);
            jpCMaleza.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCMaleza);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("TipoPoda").equals(tipo)) {
            jpTipoPoda jpTPoda = new jpTipoPoda(cn);
            jpTPoda.setSize(size);
            jpTPoda.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpTPoda);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CategoriaArchivo").equals(tipo)) {
            jpCategoriaDeArchivos jpCArch = new jpCategoriaDeArchivos(cn);
            jpCArch.setSize(size);
            jpCArch.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCArch);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ProcesoDeCafe").equals(tipo)) {
            jpProcesoCafe jpProcC = new jpProcesoCafe(cn);
            jpProcC.setSize(size);
            jpProcC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpProcC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("FactorFormaCafe").equals(tipo)) {
            jpFactorForma jpFFC = new jpFactorForma(cn);
            jpFFC.setSize(size);
            jpFFC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpFFC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("MaximoTamanoPromedio").equals(tipo)) {
            jpMaximoTamañoPromedio jpMTP = new jpMaximoTamañoPromedio(cn);
            jpMTP.setSize(size);
            jpMTP.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpMTP);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Vehiculos").equals(tipo)) {
            jpVehiculo jpVh = new jpVehiculo(cn, idiomaActual);
            jpVh.setSize(size);
            jpVh.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpVh);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("ActividadesBH").equals(tipo)) {
            jpAreaAlmacen jpAAl = new jpAreaAlmacen(cn);
            jpAAl.setSize(size);
            jpAAl.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpAAl);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Clima").equals(tipo)) {
            jpClima jpClima = new jpClima(cn);
            jpClima.setSize(size);
            jpClima.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpClima);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Idioma").equals(tipo)) {
            jpIdioma jpIdi = new jpIdioma(cn);
            jpIdi.setSize(size);
            jpIdi.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpIdi);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Rutas").equals(tipo)) {
            jpRutas jpRut = new jpRutas(cn);
            jpRut.setSize(size);
            jpRut.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpRut);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("MaximoRendimiento").equals(tipo)) {
            jpMaximoRendimiento jpMR = new jpMaximoRendimiento(cn);
            jpMR.setSize(size);
            jpMR.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpMR);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
            /* } else if (idioma.getProperty("FormaProceso").equals(tipo)) {
            jpFormaProceso jpFP = new jpFormaProceso(cn);
            jpFP.setSize(size);
            jpFP.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpFP);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();*/
        } else if (idioma.getProperty("Productores").equals(tipo)) {
            jpProductores jpPro = new jpProductores(cn, idiomaActual);
            jpPro.setSize(size);
            jpPro.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpPro);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Codigos").equals(tipo)) {
            jpCodigosCertificados jpCodC = new jpCodigosCertificados(cn, idiomaActual);
            jpCodC.setSize(size);
            jpCodC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCodC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Recibos").equals(tipo)) {
            jpRecibos jpRec = new jpRecibos(cn, idiomaActual, recepcion);
            jpRec.setSize(size);
            jpRec.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpRec);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CortesDelDia").equals(tipo)) {
            jpCortesDelDia jpLot = new jpCortesDelDia(cn, idiomaActual, recepcion);
            jpLot.setSize(size);
            jpLot.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpLot);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CortesEnviados").equals(tipo)) {
            jpCortesEnviados jpLotE = new jpCortesEnviados(cn, idiomaActual, recepcion);
            jpLotE.setSize(size);
            jpLotE.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpLotE);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Recepciones").equals(tipo)) {
            jpRecepcion jpRece = new jpRecepcion(cn, idiomaActual);
            jpRece.setSize(size);
            jpRece.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpRece);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("BeneficiosHumedos").equals(tipo)) {
            jpBeneficiosH jpBH = new jpBeneficiosH(cn, idiomaActual);
            jpBH.setSize(size);
            jpBH.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpBH);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Almacenes").equals(tipo)) {
            jpAlmacenes jpAlm = new jpAlmacenes(cn, idiomaActual);
            jpAlm.setSize(size);
            jpAlm.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpAlm);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CortesRecibidos").equals(tipo)) {
            jpCortesRecibidos jpCR = new jpCortesRecibidos(beneficio, cn);
            jpCR.setSize(size);
            jpCR.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpCR);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("CortesConfirmados").equals(tipo)) {
            jpLotesConfirmados jpLC = new jpLotesConfirmados(cn);
            jpLC.setSize(size);
            jpLC.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpLC);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else if (idioma.getProperty("Maquinaria").equals(tipo)) {
            jpMaquinaria jpMa = new jpMaquinaria(cn);
            jpMa.setSize(size);
            jpMa.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpMa);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        } else if (idioma.getProperty("Recibos").equals(tipo)) {
            jpRecibos jpRec = new jpRecibos(cn, idiomaActual, recepcion);
            jpRec.setSize(size);
            jpRec.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(jpRec);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } else {
            JLabel lbl = new JLabel();
            lbl.setSize(panelPrincipal.getWidth(), panelPrincipal.getHeight());
            ImageIcon fot = new ImageIcon("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Sistema-CAFESUMEX\\src\\Imagenes\\granos fondo.jpg");
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
            lbl.setIcon(icono);
            //jpFondo jpF = new jpFondo();
            //jpF.setSize(size);
            //.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(lbl);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        }

        /* switch (tipo) {
            case "Sociedades":
                jpCatalogoSociedades jpCPre = new jpCatalogoSociedades(cn);
                jpCPre.setSize(size);
                jpCPre.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCPre);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;

            case "Localidades":
                jpLocalidadesSociedades jpLS = new jpLocalidadesSociedades(cn);
                jpLS.setSize(size);
                jpLS.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpLS);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Pais":
                jpPais jpP = new jpPais(cn);
                jpP.setSize(size);
                jpP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();

                //jpP.fprin=this;
                break;
            case "Estado":
                jpEstado jpE = new jpEstado(cn);
                jpE.setSize(size);
                jpE.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpE);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Municipio":
                jpMunicipio jpM = new jpMunicipio(cn);
                jpM.setSize(size);
                jpM.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpM);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Localidad":
                jpLocalidad jpL = new jpLocalidad(cn);
                jpL.setSize(size);
                jpL.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpL);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Colonia/Ejido":
                jpColoniaEjido jpCE = new jpColoniaEjido(cn);
                jpCE.setSize(size);
                jpCE.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCE);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Giro de Empresa":
                jpGiroEmpresa jpGE = new jpGiroEmpresa(cn);
                jpGE.setSize(size);
                jpGE.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpGE);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Tipo de Sombra":
                jpTipoSombra jpTS = new jpTipoSombra(cn);
                jpTS.setSize(size);
                jpTS.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpTS);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Calidad de Sombra":
                jpCalidadSombra jpCS = new jpCalidadSombra(cn);
                jpCS.pp = this;
                jpCS.setSize(size);
                jpCS.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCS);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Variedad de Café":
                jpVariedadCafe jpV = new jpVariedadCafe(cn);
                jpV.setSize(size);
                jpV.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpV);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Cultivos":
                jpCultivos jpC = new jpCultivos(cn);
                jpC.setSize(size);
                jpC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Diseño Plantación":
                jpDiseñoPlantacion jpDP = new jpDiseñoPlantacion(cn);
                jpDP.setSize(size);
                jpDP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpDP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Herbicida":
                jpHerbicida jpH = new jpHerbicida(cn);
                jpH.setSize(size);
                jpH.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpH);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Enfermedad/Plaga":
                jpEnfermedadPlaga jpEP = new jpEnfermedadPlaga(cn);
                jpEP.setSize(size);
                jpEP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpEP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Fertilizante":
                jpFertilizante jpFe = new jpFertilizante(cn);
                jpFe.setSize(size);
                jpFe.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpFe);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Sistemas de Producción":
                jpSistemaProduccion jpSP = new jpSistemaProduccion(cn);
                jpSP.setSize(size);
                jpSP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpSP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Tipos de Evaluación":
                jpTipoEvaluacion jpTE = new jpTipoEvaluacion(cn);
                jpTE.setSize(size);
                jpTE.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpTE);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Actividades de Parcela":
                jpActividadesParcela jpAP = new jpActividadesParcela(cn);
                jpAP.setSize(size);
                jpAP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpAP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Forma de Café":
                jpFormaCafe jpFC = new jpFormaCafe(cn);
                jpFC.setSize(size);
                jpFC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpFC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Tipo de Certificaciones":
                jpTipoCertificacion jpTC = new jpTipoCertificacion(cn);
                jpTC.setSize(size);
                jpTC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpTC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Actividades de BH":
                jpActividadesBH jpABH = new jpActividadesBH(cn);
                jpABH.setSize(size);
                jpABH.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpABH);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Sobrantes":
                jpSobrante jpS = new jpSobrante(cn);
                jpS.setSize(size);
                jpS.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpS);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Reglas de Evaluación":
                jpReglasEvaluacion jpR = new jpReglasEvaluacion(cn);
                jpR.setSize(size);
                jpR.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpR);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;

            case "Calificación Seco":
                jpCalificacionSeco jpCalS = new jpCalificacionSeco(cn);
                jpCalS.setSize(size);
                jpCalS.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCalS);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Calificación Cereza":
                jpCalificacionCereza jpCC = new jpCalificacionCereza(cn);
                jpCC.setSize(size);
                jpCC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Evaluaciones":
                jpEvaluaciones jpEva = new jpEvaluaciones(cn);
                jpEva.setSize(size);
                jpEva.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpEva);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Propuesta 1":
                jpPersonas jpPer = new jpPersonas(cn);
                jpPer.setSize(size);
                jpPer.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpPer);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Personas":
                jpPersonas jpPer1 = new jpPersonas(cn);
                jpPer1.setSize(size);
                jpPer1.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpPer1);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Perfiles":
                jpPerfiles jpPerf = new jpPerfiles(cn);
                jpPerf.setSize(size);
                jpPerf.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpPerf);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Retenciones":
                jpRetenciones jpRet = new jpRetenciones(cn);
                jpRet.setSize(size);
                jpRet.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpRet);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Puestos":
                jpPuestos jpPues = new jpPuestos(cn);
                jpPues.setSize(size);
                jpPues.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpPues);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Tipos de Suelo":
                jpSuelos jpTSuelo = new jpSuelos(cn);
                jpTSuelo.setSize(size);
                jpTSuelo.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpTSuelo);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Nativo Fauna":
                jpFauna jpNF = new jpFauna(cn);
                jpNF.setSize(size);
                jpNF.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpNF);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Nativo Flora":
                jpFlora jpNFlora = new jpFlora(cn);
                jpNFlora.setSize(size);
                jpNFlora.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpNFlora);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Certificado":
                jpCertificado jpCertificado = new jpCertificado(cn);
                jpCertificado.setSize(size);
                jpCertificado.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCertificado);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Certificador":
                jpCertificador jpCertificador = new jpCertificador(cn);
                jpCertificador.setSize(size);
                jpCertificador.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCertificador);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Tipo de Acción":
                jpTipoAccion jpTipoA = new jpTipoAccion(cn);
                jpTipoA.setSize(size);
                jpTipoA.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpTipoA);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Motivo Plantación":
                jpMotivoPlantacion jpMPlan = new jpMotivoPlantacion(cn);
                jpMPlan.setSize(size);
                jpMPlan.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpMPlan);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Maquinaria/Herramienta":
                jpMaquinariaHerramienta jpMH = new jpMaquinariaHerramienta(cn);
                jpMH.setSize(size);
                jpMH.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpMH);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Control Maleza":
                jpControlMaleza jpCMaleza = new jpControlMaleza(cn);
                jpCMaleza.setSize(size);
                jpCMaleza.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCMaleza);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Tipo de Poda":
                jpTipoPoda jpTPoda = new jpTipoPoda(cn);
                jpTPoda.setSize(size);
                jpTPoda.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpTPoda);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Categoria de Archivo":
                jpCategoriaDeArchivos jpCArch = new jpCategoriaDeArchivos(cn);
                jpCArch.setSize(size);
                jpCArch.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCArch);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Proceso de Café":
                jpProcesoCafe jpProcC = new jpProcesoCafe(cn);
                jpProcC.setSize(size);
                jpProcC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpProcC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Factor por Forma de Café":
                jpFactorForma jpFFC = new jpFactorForma(cn);
                jpFFC.setSize(size);
                jpFFC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpFFC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Máximo Tamaño Promedio":
                jpMaximoTamañoPromedio jpMTP = new jpMaximoTamañoPromedio(cn);
                jpMTP.setSize(size);
                jpMTP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpMTP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Vehículos":
                jpVehiculo jpVh = new jpVehiculo(cn);
                jpVh.setSize(size);
                jpVh.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpVh);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Área de Almacén":
                jpAreaAlmacen jpAAl = new jpAreaAlmacen(cn);
                jpAAl.setSize(size);
                jpAAl.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpAAl);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Climas":
                jpClima jpClima = new jpClima(cn);
                jpClima.setSize(size);
                jpClima.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpClima);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Idiomas":
                jpIdioma jpIdi = new jpIdioma(cn);
                jpIdi.setSize(size);
                jpIdi.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpIdi);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Rutas":
                jpRutas jpRut = new jpRutas(cn);
                jpRut.setSize(size);
                jpRut.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpRut);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Máximo Rendimiento":
                jpMaximoRendimiento jpMR = new jpMaximoRendimiento(cn);
                jpMR.setSize(size);
                jpMR.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpMR);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Forma Procesos":
                jpFormaProceso jpFP = new jpFormaProceso(cn);
                jpFP.setSize(size);
                jpFP.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpFP);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Productores":
                jpProductores jpPro = new jpProductores(cn);
                jpPro.setSize(size);
                jpPro.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpPro);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Codigos":
                jpCodigosCertificados jpCodC = new jpCodigosCertificados(cn);
                jpCodC.setSize(size);
                jpCodC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCodC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Recibos":
                jpRecibos jpRec = new jpRecibos(cn, recepcion);
                jpRec.setSize(size);
                jpRec.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpRec);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Cortes del día":
                jpCortesDelDia jpLot = new jpCortesDelDia(cn, recepcion);
                jpLot.setSize(size);
                jpLot.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpLot);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Cortes Enviados":
                jpCortesEnviados jpLotE = new jpCortesEnviados(cn, recepcion);
                jpLotE.setSize(size);
                jpLotE.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpLotE);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Recepcion":
                jpRecepcion jpRece = new jpRecepcion(cn);
                jpRece.setSize(size);
                jpRece.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpRece);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Beneficios Humedos":
                jpBeneficiosH jpBH = new jpBeneficiosH(cn);
                jpBH.setSize(size);
                jpBH.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpBH);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
            case "Almacenes":
                jpAlmacenes jpAlm = new jpAlmacenes(cn);
                jpAlm.setSize(size);
                jpAlm.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpAlm);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;

            case "Cortes Recibidos":
                jpCortesRecibidos jpCR = new jpCortesRecibidos(beneficio, cn);
                jpCR.setSize(size);
                jpCR.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpCR);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;

            case "Lotes Confirmados":
                jpLotesConfirmados jpLC = new jpLotesConfirmados(cn);
                jpLC.setSize(size);
                jpLC.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpLC);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;

            case "Maquinaria":
                jpMaquinaria jpMa = new jpMaquinaria(cn);
                jpMa.setSize(size);
                jpMa.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(jpMa);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;

            default:
                JLabel lbl = new JLabel();
                lbl.setSize(panelPrincipal.getWidth(), panelPrincipal.getHeight());
                ImageIcon fot = new ImageIcon("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\Sistema-CAFESUMEX\\src\\Imagenes\\granos fondo.jpg");
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
                lbl.setIcon(icono);
                //jpFondo jpF = new jpFondo();
                //jpF.setSize(size);
                //.setLocation(0, 0);
                panelPrincipal.removeAll();
                panelPrincipal.add(lbl);
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
                break;
        }*/
    }

    public void recepcion(String recepcion) {
        this.recepcion = recepcion;

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Recepcion"));
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Recibos"));
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CortesDelDia"));
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CortesEnviados"));
        treeNode1.add(treeNode2);
        treeNode1.add(treeNode3);
        treeNode1.add(treeNode4);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        //CAMBIAR COLORES BOTOTES
        btnRecepcion.setBackground(new Color(26, 66, 21));
        //QUITAR COLOR
        btnSociedades.setBackground(Color.getColor("FFFFFF"));
        btnPersonas.setBackground(Color.getColor("FFFFFF"));
        btnFincaCert.setBackground(Color.getColor("FFFFFF"));
        btnConfigurador.setBackground(Color.getColor("FFFFFF"));
        btnBH.setBackground(Color.getColor("FFFFFF"));
        btnLaboratorio.setBackground(Color.getColor("FFFFFF"));
    }

    public void beneficio(String beneficio) {
        this.beneficio = beneficio;

        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("BeneficioHumedo"));
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CortesRecibidos"));
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CortesConfirmados"));
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CortesProcesoSecado"));
        javax.swing.tree.DefaultMutableTreeNode treeNode5 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("SubLotesEnviados"));
        javax.swing.tree.DefaultMutableTreeNode treeNode6 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ConsumoAgua"));
        javax.swing.tree.DefaultMutableTreeNode treeNode7 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("EntregaCascarilla"));
        javax.swing.tree.DefaultMutableTreeNode treeNode8 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ClimaTemperaturaAmbiente"));
        javax.swing.tree.DefaultMutableTreeNode treeNode9 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("MantenimientoMaquinaria"));
        javax.swing.tree.DefaultMutableTreeNode treeNode10 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("LimpiezaDeMaquinaria"));
        javax.swing.tree.DefaultMutableTreeNode treeNode11 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("EvaluacionMaquinaria"));
        javax.swing.tree.DefaultMutableTreeNode treeNode12 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("SubLotesSecos"));
        treeNode1.add(treeNode2);
        treeNode1.add(treeNode3);
        treeNode1.add(treeNode4);
        treeNode1.add(treeNode5);
        treeNode1.add(treeNode6);
        treeNode1.add(treeNode7);
        treeNode1.add(treeNode8);
        treeNode1.add(treeNode9);
        treeNode1.add(treeNode10);
        treeNode1.add(treeNode11);
        treeNode1.add(treeNode12);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //CAMBIAR COLORES BOTOTES
        btnBH.setBackground(Color.getHSBColor(0.56f, 1.0f, 0.8f));
        //QUITAR COLOR
        btnSociedades.setBackground(Color.getColor("FFFFFF"));
        btnPersonas.setBackground(Color.getColor("FFFFFF"));
        btnFincaCert.setBackground(Color.getColor("FFFFFF"));
        btnRecepcion.setBackground(Color.getColor("FFFFFF"));
        btnConfigurador.setBackground(Color.getColor("FFFFFF"));
        btnLaboratorio.setBackground(Color.getColor("FFFFFF"));
    }

    /*
    public void paint(Graphics grafico) {
        Dimension height = getSize();
//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
        ImageIcon Img = new ImageIcon(getClass().getResource("/Imagenes/FondoPantalla.png"));
//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
        grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
        panelPrincipal.setOpaque(false);
        panelPrincipal.paint(grafico);

    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        btnConfigurador = new javax.swing.JButton();
        btnPersonas = new javax.swing.JButton();
        btnFincaCert = new javax.swing.JButton();
        btnRecepcion = new javax.swing.JButton();
        btnBH = new javax.swing.JButton();
        btnLaboratorio = new javax.swing.JButton();
        btnSociedades = new javax.swing.JButton();
        comboIdioma = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        panelArbol = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FincaLab - Pantalla Principal");
        setFocusTraversalPolicyProvider(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnConfigurador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/database(2).png"))); // NOI18N
        btnConfigurador.setText("Configurador");
        btnConfigurador.setToolTipText("");
        btnConfigurador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfigurador.setFocusPainted(false);
        btnConfigurador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfigurador.setOpaque(false);
        btnConfigurador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfigurador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguradorActionPerformed(evt);
            }
        });

        btnPersonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group(1).png"))); // NOI18N
        btnPersonas.setText("Personas");
        btnPersonas.setToolTipText("Modulo Personas");
        btnPersonas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonas.setFocusPainted(false);
        btnPersonas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonasActionPerformed(evt);
            }
        });

        btnFincaCert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/avatar.png"))); // NOI18N
        btnFincaCert.setText("FincaCert");
        btnFincaCert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFincaCert.setFocusPainted(false);
        btnFincaCert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFincaCert.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFincaCert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFincaCertActionPerformed(evt);
            }
        });

        btnRecepcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cash.png"))); // NOI18N
        btnRecepcion.setText("Recepcion");
        btnRecepcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecepcion.setFocusPainted(false);
        btnRecepcion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecepcion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionActionPerformed(evt);
            }
        });

        btnBH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/machinery.png"))); // NOI18N
        btnBH.setText("B Humedo");
        btnBH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBH.setFocusPainted(false);
        btnBH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBHActionPerformed(evt);
            }
        });

        btnLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/breakfast.png"))); // NOI18N
        btnLaboratorio.setText("Laboratorio");
        btnLaboratorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaboratorio.setFocusPainted(false);
        btnLaboratorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLaboratorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratorioActionPerformed(evt);
            }
        });

        btnSociedades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/network.png"))); // NOI18N
        btnSociedades.setText("Sociedades");
        btnSociedades.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSociedades.setFocusPainted(false);
        btnSociedades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSociedades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSociedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSociedadesActionPerformed(evt);
            }
        });

        comboIdioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Ingles", "Ingles Etiopia", "Oromo" }));
        comboIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIdiomaActionPerformed(evt);
            }
        });

        jLabel3.setText("Idioma");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConfigurador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPersonas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFincaCert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRecepcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLaboratorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSociedades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(comboIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSociedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLaboratorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFincaCert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPersonas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfigurador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Configuracion");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Datos Basicos");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Ubicación Geográfica");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Pais");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Estado");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Municipio");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Localidad");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Colonia/Ejido");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Personas");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Giro de Empresa");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Seguridad");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Perfiles");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Sociedades");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Retenciones");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Puestos");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("FincaCert");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipos de Suelo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Nativo Fauna");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Nativo Flora");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo de Sombra");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Calidad de Sombra");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Certificados");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Certificado");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Certificador");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tipo de Certificaciones");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Codigos");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Variedad de Café");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cultivos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo de Acción");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Diseño Plantación");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Motivo Plantación");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Herbicida");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Maquinaria/Herramienta");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Enfermedad/Plaga");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Control Maleza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fertilizante");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo de Poda");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Categoria de Archivo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Actividades de Parcela");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Sistemas de Producción");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Recepción");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Proceso de Café");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Forma de Café");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipos de Evaluación");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Evaluaciones");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Factor por Forma de Café");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Calificación Seco");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Máximo Rendimiento");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Máximo Tamaño Promedio");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Reglas de Evaluación");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Calificación Cereza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Forma Procesos");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Beneficio Húmedo");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Vehículos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Actividades de BH");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Área de Almacén");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Rutas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Climas");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Sobrantes");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Idioma");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Idiomas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setOpaque(false);
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTree1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelArbolLayout = new javax.swing.GroupLayout(panelArbol);
        panelArbol.setLayout(panelArbolLayout);
        panelArbolLayout.setHorizontalGroup(
            panelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArbolLayout.createSequentialGroup()
                .addGroup(panelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArbolLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelArbolLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelArbolLayout.setVerticalGroup(
            panelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArbolLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelArbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelArbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        // TODO add your handling code here:
        TreePath tp; //jTree1.getPathForLocation(evt.getX(), evt.getY());
        tp = jTree1.getSelectionPath();

        String tipoPanel = jTree1.getLastSelectedPathComponent() + "";

        if (tp != null) {
            System.out.println(jTree1.getLastSelectedPathComponent());//tp.toString()+ "COUNT: "+c);
            // JOptionPane.showMessageDialog(null, tp.getPathCount());

            try {
                pintarPanel(tipoPanel);
            } catch (ParseException ex) {
                Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /* if(compara.equals("Pais")){
          internalPrueba ip = new internalPrueba();
          escritorio.add(ip);
          ip.show();
      }*/
 /*JPanel panelPrueba = new JPanel();
        JFrame f = new JFrame();
        f.getContentPane().add(panelPrueba);
         */
        //  JPanel pP = new JPanel(panelPrueba);
        //  panelPrincipal.add(panelPrueba,BorderLayout.CENTER);

    }//GEN-LAST:event_jTree1MouseClicked

    private void btnPersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonasActionPerformed
        // TODO add your handling code here:
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ModuloPersonas"));
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Personas"));
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CAMBIAR COLORES BOTOTES
        btnPersonas.setBackground(new Color(26, 66, 21));
        //QUITAR COLOR
        btnSociedades.setBackground(Color.getColor("FFFFFF"));
        btnConfigurador.setBackground(Color.getColor("FFFFFF"));
        btnFincaCert.setBackground(Color.getColor("FFFFFF"));
        btnRecepcion.setBackground(Color.getColor("FFFFFF"));
        btnBH.setBackground(Color.getColor("FFFFFF"));
        btnLaboratorio.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_btnPersonasActionPerformed

    private void jTree1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTree1KeyReleased
        // TODO add your handling code here:
        TreePath tp; //jTree1.getPathForLocation(evt.getX(), evt.getY());
        tp = jTree1.getSelectionPath();

        String tipoPanel = jTree1.getLastSelectedPathComponent() + "";

        if (tp != null) {
            char car = (char) evt.getKeyCode();
            if (car == evt.VK_ENTER) {
                try {
                    pintarPanel(tipoPanel);
                    //this.botonIngresarActionPerformed(null);
                } catch (ParseException ex) {
                    Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jTree1KeyReleased

    private void btnConfiguradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguradorActionPerformed
        // TODO add your handling code here:
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        idioma = new Propiedades(comboIdioma.getSelectedItem() + "");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Configuracion"));
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("DatosBasicos"));
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("UbicacionGeografica"));
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Pais"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Estado"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Municipio"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Localidad"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ColoniaEjido"));
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Personas"));
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("GiroEmpresa"));
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Seguridad"));
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Perfiles"));
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Sociedad"));
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Retenciones"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Puestos"));
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("FincaCert"));
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("TipoDeSuelo"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("NativoFauna"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("NativoFlora"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("TipoDeSombra"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CalidadDeSombra"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Certificados"));
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Certificado"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Certificador"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("TipoDeCertificaciones"));
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Codigos"));
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("VariedadDeCafe"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Cultivos"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("TipoDeAccion"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("DisenoDePlantacion"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("MotivoPlantacion"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Herbicida"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("MaquinariaHerramienta"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("EnfermedadPlaga"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ControlMaleza"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Fertilizante"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("TipoPoda"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CategoriaArchivo"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ActividadesParcela"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("SistemaDeProduccion"));
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Recepcion"));
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ProcesoDeCafe"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("FormaDeCafe"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("TiposDeEvaluacion"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Evaluaciones"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("FactorFormaCafe"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CalificacionSeco"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("MaximoRendimiento"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("MaximoTamanoPromedio"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ReglasDeEvaluacion"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("CalificacionCereza"));
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("BeneficioHumedo"));
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Vehiculos"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Maquinaria"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ActividadesBH"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("AreasAlmacen"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Rutas"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Climas"));
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Sobrantes"));
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Idioma"));
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Idiomas"));
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        //CAMBIAR COLORES BOTOTES
        btnConfigurador.setBackground(new Color(26, 66, 21));
        //QUITAR COLOR
        btnSociedades.setBackground(Color.getColor("FFFFFF"));
        btnPersonas.setBackground(Color.getColor("FFFFFF"));
        btnFincaCert.setBackground(Color.getColor("FFFFFF"));
        btnRecepcion.setBackground(Color.getColor("FFFFFF"));
        btnBH.setBackground(Color.getColor("FFFFFF"));
        btnLaboratorio.setBackground(Color.getColor("FFFFFF"));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnConfiguradorActionPerformed

    private void btnFincaCertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFincaCertActionPerformed
        // TODO add your handling code here:
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("FincaCert"));
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Productores"));
        treeNode1.add(treeNode2);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CAMBIAR COLORES BOTOTES
        btnFincaCert.setBackground(new Color(26, 66, 21));
        //QUITAR COLOR
        btnSociedades.setBackground(Color.getColor("FFFFFF"));
        btnPersonas.setBackground(Color.getColor("FFFFFF"));
        btnConfigurador.setBackground(Color.getColor("FFFFFF"));
        btnRecepcion.setBackground(Color.getColor("FFFFFF"));
        btnBH.setBackground(Color.getColor("FFFFFF"));
        btnLaboratorio.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_btnFincaCertActionPerformed
    String recepcion = "";
    private void btnRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionActionPerformed
        // TODO add your handling code here:
        jdSeleccionRecepcion jdSRecepcion = new jdSeleccionRecepcion(null, true, cn);
        jdSRecepcion.pPrin = this;
        jdSRecepcion.setVisible(true);
    }//GEN-LAST:event_btnRecepcionActionPerformed
    String beneficio = "";
    private void btnBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBHActionPerformed
        // TODO add your handling code here:
        jdSeleccionBeneficio jdSBH = new jdSeleccionBeneficio(null, true, cn);
        jdSBH.pPrin = this;
        jdSBH.setVisible(true);


    }//GEN-LAST:event_btnBHActionPerformed

    private void btnLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratorioActionPerformed
        // TODO add your handling code here:
        //CAMBIAR COLORES BOTOTES
        btnLaboratorio.setBackground(Color.getHSBColor(0.56f, 1.0f, 0.8f));
        //QUITAR COLOR
        btnSociedades.setBackground(Color.getColor("FFFFFF"));
        btnPersonas.setBackground(Color.getColor("FFFFFF"));
        btnFincaCert.setBackground(Color.getColor("FFFFFF"));
        btnRecepcion.setBackground(Color.getColor("FFFFFF"));
        btnBH.setBackground(Color.getColor("FFFFFF"));
        btnConfigurador.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_btnLaboratorioActionPerformed

    private void btnSociedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSociedadesActionPerformed
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("ModuloSociedades"));
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Sociedades"));
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Localidades"));
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Recepciones"));
        javax.swing.tree.DefaultMutableTreeNode treeNode5 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("BeneficiosHumedos"));
        javax.swing.tree.DefaultMutableTreeNode treeNode6 = new javax.swing.tree.DefaultMutableTreeNode(idioma.getProperty("Almacenes"));
        treeNode1.add(treeNode2);
        treeNode1.add(treeNode3);
        treeNode1.add(treeNode4);
        treeNode1.add(treeNode5);
        treeNode1.add(treeNode6);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CAMBIAR COLORES BOTOTES
        btnSociedades.setBackground(new Color(26, 66, 21));
        //QUITAR COLOR
        btnRecepcion.setBackground(Color.getColor("FFFFFF"));
        btnPersonas.setBackground(Color.getColor("FFFFFF"));
        btnFincaCert.setBackground(Color.getColor("FFFFFF"));
        btnConfigurador.setBackground(Color.getColor("FFFFFF"));
        btnBH.setBackground(Color.getColor("FFFFFF"));
        btnLaboratorio.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_btnSociedadesActionPerformed

    private void comboIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIdiomaActionPerformed
        // TODO add your handling code here:
        cambiarIdioma(comboIdioma.getSelectedItem() + "");
    }//GEN-LAST:event_comboIdiomaActionPerformed

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
            java.util.logging.Logger.getLogger(pantallaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new pantallaPrincipal().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBH;
    private javax.swing.JButton btnConfigurador;
    private javax.swing.JButton btnFincaCert;
    private javax.swing.JButton btnLaboratorio;
    private javax.swing.JButton btnPersonas;
    private javax.swing.JButton btnRecepcion;
    private javax.swing.JButton btnSociedades;
    private javax.swing.JComboBox<String> comboIdioma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel panelArbol;
    public javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
