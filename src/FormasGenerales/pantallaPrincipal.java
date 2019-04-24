/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormasGenerales;

import Conexion.Conexion;
import FormasInternas.internalPrueba;
import FormasInternas.panelPrueba;
import Formas_Configuraciones_BeneficioHumedo.jpActividadesBH;
import Formas_Configuraciones_BeneficioHumedo.jpAreaAlmacen;
import Formas_Configuraciones_BeneficioHumedo.jpClima;
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

    public pantallaPrincipal() throws ParseException {
        initComponents();
        setLocationRelativeTo(null);
        cn = (new Conexion()).conectar();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //jButton3.setVisible(false);
        //jButton4.setVisible(false);
        //jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);

        jButton2.setBackground(new Color(26,66,21));
        
        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        //render.setLeafIcon(new ImageIcon(this.getClass().getResource("../Imagenes/database.png")));
        //render.setOpenIcon(new ImageIcon(this.getClass().getResource("../Imagenes/database.png")));
        //render.setClosedIcon(new ImageIcon(this.getClass().getResource("../Imagenes/database.png")));

    }


    public void pintarPanel(String tipo) throws ParseException {
        Dimension size = panelPrincipal.getSize();
        //Tamaño Manual = 830, 570
        switch (tipo) {
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
        }
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

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Recepcion");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Recibos");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cortes del día");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Cortes Enviados");
        treeNode1.add(treeNode2);
        treeNode1.add(treeNode3);
        treeNode1.add(treeNode4);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        //CAMBIAR COLORES BOTOTES
        jButton5.setBackground(new Color(26,66,21));
        //QUITAR COLOR
        jButton1.setBackground(Color.getColor("FFFFFF"));
        jButton3.setBackground(Color.getColor("FFFFFF"));
        jButton4.setBackground(Color.getColor("FFFFFF"));
        jButton2.setBackground(Color.getColor("FFFFFF"));
        jButton6.setBackground(Color.getColor("FFFFFF"));
        jButton7.setBackground(Color.getColor("FFFFFF"));
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/database(2).png"))); // NOI18N
        jButton2.setText("Configurador");
        jButton2.setToolTipText("");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setOpaque(false);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/group(1).png"))); // NOI18N
        jButton3.setText("Personas");
        jButton3.setToolTipText("Modulo Personas");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/avatar.png"))); // NOI18N
        jButton4.setText("FincaCert");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cash.png"))); // NOI18N
        jButton5.setText("Recepcion");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/machinery.png"))); // NOI18N
        jButton6.setText("B Humedo");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusPainted(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/breakfast.png"))); // NOI18N
        jButton7.setText("Laboratorio");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/network.png"))); // NOI18N
        jButton1.setText("Sociedades");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
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
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel1.setText("¡ BIENVENIDO A FINCALAB !");
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

        jLabel2.setText("jLabel2");

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Modulo Personas");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Personas");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        //CAMBIAR COLORES BOTOTES
        jButton3.setBackground(new Color(26,66,21));
        //QUITAR COLOR
        jButton1.setBackground(Color.getColor("FFFFFF"));
        jButton2.setBackground(Color.getColor("FFFFFF"));
        jButton4.setBackground(Color.getColor("FFFFFF"));
        jButton5.setBackground(Color.getColor("FFFFFF"));
        jButton6.setBackground(Color.getColor("FFFFFF"));
        jButton7.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
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

        //CAMBIAR COLORES BOTOTES
        jButton2.setBackground(new Color(26,66,21));
        //QUITAR COLOR
        jButton1.setBackground(Color.getColor("FFFFFF"));
        jButton3.setBackground(Color.getColor("FFFFFF"));
        jButton4.setBackground(Color.getColor("FFFFFF"));
        jButton5.setBackground(Color.getColor("FFFFFF"));
        jButton6.setBackground(Color.getColor("FFFFFF"));
        jButton7.setBackground(Color.getColor("FFFFFF"));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("FincaCert");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Productores");
        treeNode1.add(treeNode2);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        try {
            pintarPanel("fondo");
        } catch (ParseException ex) {
            Logger.getLogger(pantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CAMBIAR COLORES BOTOTES
        jButton4.setBackground(new Color(26,66,21));
        //QUITAR COLOR
        jButton1.setBackground(Color.getColor("FFFFFF"));
        jButton3.setBackground(Color.getColor("FFFFFF"));
        jButton2.setBackground(Color.getColor("FFFFFF"));
        jButton5.setBackground(Color.getColor("FFFFFF"));
        jButton6.setBackground(Color.getColor("FFFFFF"));
        jButton7.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_jButton4ActionPerformed
    String recepcion = "";
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jdSeleccionRecepcion jdSRecepcion = new jdSeleccionRecepcion(null, true, cn);
        jdSRecepcion.pPrin = this;
        jdSRecepcion.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //CAMBIAR COLORES BOTOTES
        jButton6.setBackground(Color.getHSBColor(0.56f, 1.0f, 0.8f));
        //QUITAR COLOR
        jButton1.setBackground(Color.getColor("FFFFFF"));
        jButton3.setBackground(Color.getColor("FFFFFF"));
        jButton4.setBackground(Color.getColor("FFFFFF"));
        jButton5.setBackground(Color.getColor("FFFFFF"));
        jButton2.setBackground(Color.getColor("FFFFFF"));
        jButton7.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //CAMBIAR COLORES BOTOTES
        jButton7.setBackground(Color.getHSBColor(0.56f, 1.0f, 0.8f));
        //QUITAR COLOR
        jButton1.setBackground(Color.getColor("FFFFFF"));
        jButton3.setBackground(Color.getColor("FFFFFF"));
        jButton4.setBackground(Color.getColor("FFFFFF"));
        jButton5.setBackground(Color.getColor("FFFFFF"));
        jButton6.setBackground(Color.getColor("FFFFFF"));
        jButton2.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        panelArbol.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane2.setOpaque(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Modulo Sociedades");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Sociedades");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Localidades");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Recepcion");
        javax.swing.tree.DefaultMutableTreeNode treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Beneficios Humedos");
        javax.swing.tree.DefaultMutableTreeNode treeNode6 = new javax.swing.tree.DefaultMutableTreeNode("Almacenes");
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
        jButton1.setBackground(new Color(26,66,21));
        //QUITAR COLOR
        jButton5.setBackground(Color.getColor("FFFFFF"));
        jButton3.setBackground(Color.getColor("FFFFFF"));
        jButton4.setBackground(Color.getColor("FFFFFF"));
        jButton2.setBackground(Color.getColor("FFFFFF"));
        jButton6.setBackground(Color.getColor("FFFFFF"));
        jButton7.setBackground(Color.getColor("FFFFFF"));
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel panelArbol;
    public javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
