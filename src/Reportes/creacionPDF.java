/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Metodos_Configuraciones.metodosDatosBasicos;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class creacionPDF {

    metodosDatosBasicos mdb;
    Connection cn;

    public creacionPDF(Connection cn) {
        this.cn = cn;
        mdb = new metodosDatosBasicos(cn);
    }

    public void pdfBoletaSalidaRecepcion(String idBoleta, Object[][] contenido) throws FileNotFoundException, DocumentException, SQLException {

        String[] datos = mdb.devolverLineaDatos("select "
                + "b.idBoletaManual, b.origen, b.destino, b.fecha, b.totalSacos, b.totalKg, b.transporteLimpio, "
                + "b.descripcion, v.Nombre, v.Placas, v.Responsable \n"
                + "from boletasalidareceptor b \n"
                + "inner join vehiculo v on(b.idTransporte=v.ID) \n"
                + "where b.idBoleta = '" + idBoleta + "' group by b.idBoleta", 11).split("¬");

        String boletaManual = datos[0];
        String origen = datos[1];
        String destino = datos[2];
        String fecha = datos[3];
        String totalSacos = datos[4];
        String totalKg = datos[5];
        String transporteLimpio = datos[6];
        String observaciones = datos[7];
        String vehiculo = datos[8];
        String placas = datos[9];
        String responsable = datos[10];

        Document documento = new Document(PageSize.LETTER);

        FileOutputStream ficheroPdf = new FileOutputStream("C:\\Users\\USUARIO\\Desktop\\pruebaBoleta.pdf");
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

        documento.open();

        Font fuente = new Font();
        Font fuente2 = new Font();
        fuente.setSize(11);
        fuente2.setSize(8);

        Paragraph titulo = new Paragraph("Grupo Terruño Nayarita S. de R.L. de C.V.", fuente);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        Paragraph ubicacion = new Paragraph("Nayarit #290 Col. Morelos. Tepic, Nayarit, México", fuente2);
        ubicacion.setAlignment(Element.ALIGN_CENTER);
        documento.add(ubicacion);

        Paragraph telefono = new Paragraph("Tel. 3112103599", fuente2);
        telefono.setAlignment(Element.ALIGN_CENTER);
        documento.add(telefono);

        Paragraph pagina = new Paragraph("www.gtnay.com\n\n", fuente2);
        pagina.setAlignment(Element.ALIGN_CENTER);
        documento.add(pagina);

        Paragraph idBoletaM = new Paragraph("Recepción\nBoleta Manual\n" + boletaManual + "\n\n", fuente2);
        idBoletaM.setAlignment(Element.ALIGN_CENTER);
        documento.add(idBoletaM);

        Paragraph origenDestino = new Paragraph("Origen: " + origen + "             Destino: " + destino, fuente2);
        origenDestino.setAlignment(Element.ALIGN_LEFT);
        documento.add(origenDestino);

        Paragraph horarioBoleta = new Paragraph("Fecha: " + fecha, fuente2);
        horarioBoleta.setAlignment(Element.ALIGN_LEFT);
        documento.add(horarioBoleta);

        documento.add(new Paragraph("\n"));

        documento.add(tablaBoletaSalida(contenido, totalSacos, totalKg));

        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("\n"));

        Paragraph datosVehiculo = new Paragraph("Transporte: " + vehiculo + "                      Placas: " + placas + "\nResponsable: " + responsable, fuente2);
        datosVehiculo.setAlignment(Element.ALIGN_LEFT);
        documento.add(datosVehiculo);

        String leyendalimpio = "";
        if (transporteLimpio.equals("1")) {
            leyendalimpio = "Transporte Limpio Y Seco - SI ✓";
        } else {
            leyendalimpio = "Transporte Limpio Y Seco - NO ✖";
        }

        Paragraph transporteL = new Paragraph(leyendalimpio + "\n\n", fuente2);
        transporteL.setAlignment(Element.ALIGN_LEFT);
        documento.add(transporteL);

        Paragraph observa = new Paragraph("Observaciones:\n" + observaciones, fuente2);
        observa.setAlignment(Element.ALIGN_LEFT);
        documento.add(observa);

        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("\n"));

        Paragraph nombresFirmas = new Paragraph("                    Gerardo Sebastian Amaral Rodriguez                                 " + responsable + "                      Carlos German Valdez Correa", fuente2);
        documento.add(nombresFirmas);

        Paragraph firmas = new Paragraph("              ______________________________              ______________________________              ______________________________              ", fuente2);
        firmas.setAlignment(Element.ALIGN_CENTER);
        documento.add(firmas);

        Paragraph nombres = new Paragraph("               Receptor                                                               Chofer                                                Quien Recibe              ", fuente2);
        nombres.setAlignment(Element.ALIGN_CENTER);
        documento.add(nombres);

        documento.close();
    }

    public PdfPTable tablaBoletaSalida(Object[][] contenido, String sacos, String kg) throws SQLException {
        Font fuente2 = new Font();
        fuente2.setSize(7);

        PdfPTable table = new PdfPTable(5);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        Paragraph columna1 = new Paragraph("Id Corte");
        Paragraph columna2 = new Paragraph("Certificado");
        Paragraph columna3 = new Paragraph("Forma de Café");
        Paragraph columna4 = new Paragraph("Sacos");
        Paragraph columna5 = new Paragraph("Kg. Neto");

        columna1.getFont().setStyle(Font.BOLD);
        columna1.getFont().setSize(7);

        columna2.getFont().setStyle(Font.BOLD);
        columna2.getFont().setSize(7);

        columna3.getFont().setStyle(Font.BOLD);
        columna3.getFont().setSize(7);

        columna4.getFont().setStyle(Font.BOLD);
        columna4.getFont().setSize(7);

        columna5.getFont().setStyle(Font.BOLD);
        columna5.getFont().setSize(7);

        table.addCell(columna1);
        table.addCell(columna2);
        table.addCell(columna3);
        table.addCell(columna4);
        table.addCell(columna5);

        table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(100);

        for (int counter = 0; counter < contenido.length; counter++) {

            //Paragraph col0 = new Paragraph(String.valueOf(counter + 1), fuente2);
            // table.addCell(col0);
            Paragraph col1 = new Paragraph(contenido[counter][0] + "", fuente2);
            //JOptionPane.showMessageDialog(null,"Dato "+contenido[counter][0]);
            table.addCell(col1);

            Paragraph col2 = new Paragraph(contenido[counter][1] + "", fuente2);
            // JOptionPane.showMessageDialog(null,"Dato "+contenido[counter][1]);
            table.addCell(col2);

            Paragraph col3 = new Paragraph(contenido[counter][2] + "", fuente2);
            // JOptionPane.showMessageDialog(null,"Dato "+contenido[counter][2]);
            table.addCell(col3);

            Paragraph col4 = new Paragraph(contenido[counter][3] + "", fuente2);
            // JOptionPane.showMessageDialog(null,"Dato "+contenido[counter][3]);
            table.addCell(col4);

            Paragraph col5 = new Paragraph(contenido[counter][4] + "", fuente2);
            //JOptionPane.showMessageDialog(null,"Dato "+contenido[counter][4]);
            table.addCell(col5);
        }
        Paragraph totales = new Paragraph("Totales", fuente2);
        Paragraph totalSacos = new Paragraph(sacos, fuente2);
        Paragraph totalKg = new Paragraph(kg, fuente2);

        table.addCell("");
        table.addCell("");
        table.addCell(totales);
        table.addCell(totalSacos);
        table.addCell(totalKg);

        return table;
    }

    public void pdfRecibo(String idRecibo)
            throws DocumentException, IOException {

        try {

            String[] datos = mdb.devolverLineaDatos("SELECT\n"
                    + "    r.id,\n"
                    + "    r.folioManual,\n"
                    + "    r.formaCafe,\n"
                    + "    r.sacos,\n"
                    + "    r.kgRecibidos,\n"
                    + "    r.totalBruto,\n"
                    + "    r.retencion,\n"
                    + "    r.total,\n"
                    + "    r.precioNeto,\n"
                    + "    r.verdes,\n"
                    + "    r.inmaduros,\n"
                    + "    r.brocados,\n"
                    + "    r.calificacion,\n"
                    + "    r.personaEntrego,\n"
                    + "    r.observaciones,\n"
                    + "    r.precioBrutoKgSociedad,\n"
                    + "    r.precioNetoKgSociedad,\n"
                    + "    Concat (pf.Nombre,' ',pf.apellidopaterno,' ',apellidomaterno),\n"
                    + "    lPer.Descripcion,\n"
                    + "    prod.clave_productor,\n"
                    + "    pm.RazonSocial,\n"
                    + "    pm.NombreCorto,\n"
                    + "    pm.Direccion,\n"
                    + "    lpm.Descripcion,\n"
                    + "    epm.descripcion,\n"
                    + "    r.fechaCreacion,\n"
                    + "    par.nombre,\n"
                    + "    par.clave_parcela,\n"
                    + "    par.clave_certificacion,\n"
                    + "    lpar.descripcion\n"
                    + "FROM\n"
                    + "    recibos r\n"
                    + "INNER JOIN personaf pf ON\n"
                    + "    (r.idPersona = pf.ID)\n"
                    + "INNER JOIN localidad lPer ON\n"
                    + "    (pf.ID_Localidad = lPer.ID)\n"
                    + "INNER JOIN productor prod ON\n"
                    + "    (prod.id_persona = pf.ID)\n"
                    + "INNER JOIN personam pm ON\n"
                    + "    (r.idSociedad = pm.ID)\n"
                    + "INNER JOIN localidad lpm ON\n"
                    + "    (pm.ID_Localidad = lpm.ID)\n"
                    + "INNER JOIN estado epm ON\n"
                    + "    (pm.ID_Estado = epm.ID)\n"
                    + "INNER JOIN parcelas par ON\n"
                    + "    (r.idParcela = par.id)\n"
                    + "INNER JOIN localidad lpar ON\n"
                    + "    (par.id_localidad = lpar.ID)\n"
                    + "WHERE\n"
                    + "    r.id = " + idRecibo + " AND prod.tipoPersona = 1", 30).split("¬");

            String id = datos[0];
            String folioManual = datos[1];
            String formaCafe = datos[2];
            String sacos = datos[3];
            String kgRecibidos = datos[4];
            String totalBruto = datos[5];
            String retencion = datos[6];
            String total = datos[7];
            String precioNeto = datos[8];
            String verdes = datos[9];
            String inmaduros = datos[10];
            String brocados = datos[11];
            String calificacion = datos[12];
            String pEntrego = datos[13];
            String observaciones = datos[14];
            String precioBrutoKgSociedad = datos[15];
            String precioNetoKgSociedad = datos[16];
            String nombreProductor = datos[17];
            String localidadProd = datos[18];
            String claveProd = datos[19];
            String razonsocial = datos[20];
            String nombrecorto = datos[21];
            String direccionSociedad = datos[22];
            String localidadSociedad = datos[23];
            String estadoSociedad = datos[24];
            String fechaRecibo = datos[25];
            String nombrePar = datos[26];
            String clavePar = datos[27];
            String certPar = datos[28];
            String localidadPar = datos[29];

            Document documento = new Document(PageSize.LETTER);
            //Image img = Image.getInstance("mega200px.png");

            FileOutputStream ficheroPdf = new FileOutputStream("C:\\Users\\USUARIO\\Desktop\\prueba2.pdf");
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

            documento.open();
            //img.setAbsolutePosition(380f, 695f);

            Font fuente = new Font();
            Font fuente2 = new Font();
            Font fuenteNegrita = new Font(Font.FontFamily.UNDEFINED, 7, Font.BOLD);
            fuente.setSize(8);
            fuente2.setSize(7);

            //documento.add(img);
            Paragraph titulo = new Paragraph("RECIBO DE COMPRA DE CAFÉ", fuente);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph infoSociedad = new Paragraph(razonsocial + "      " + direccionSociedad + "      Localidad: " + localidadSociedad + "        Estado: " + estadoSociedad, fuente2);
            infoSociedad.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoSociedad);

            Paragraph infoRecibo = new Paragraph("Fecha: " + fechaRecibo + "                                       Folio Manual: " + folioManual + "                                       Folio: " + id, fuente2);
            infoRecibo.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoRecibo);

            Paragraph bla = new Paragraph("-", fuente2);
            documento.add(bla);

            documento.add(tablaProdParc(nombreProductor, claveProd, localidadProd, nombrecorto, nombrePar, clavePar, localidadPar, certPar));
            /* Paragraph encabezadoProd = new Paragraph("----- Productor ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", fuente);
            encabezadoProd.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezadoProd);

            Paragraph infoProd = new Paragraph("Nombre: " + nombreProductor + "     Clave: " + claveProd + "     Localidad: " + localidadProd + "     Sociedad: " + nombrecorto, fuente2);
            infoProd.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoProd);

            Paragraph encabezadoParc = new Paragraph("----- Parcela -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", fuente);
            encabezadoParc.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezadoParc);

            Paragraph infoParc = new Paragraph("Nombre: " + nombrePar + "     Clave: " + clavePar + "     Localidad: " + localidadPar + "     Certificado: " + certPar, fuente2);
            infoParc.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoParc);
             */

            documento.add(bla);

            documento.add(tablaRecibo(verdes, precioBrutoKgSociedad, inmaduros, precioNetoKgSociedad,
                    brocados, totalBruto, formaCafe, retencion, sacos, total, calificacion, kgRecibidos));

            Paragraph observa = new Paragraph(observaciones, fuente2);
            documento.add(observa);

            documento.add(bla);

            Paragraph nombresFirmas = new Paragraph("                                   Gerardo Sebastian Amaral Rodriguez                  " + pEntrego + "                      " + nombreProductor, fuenteNegrita);
            documento.add(nombresFirmas);

            Paragraph firmas = new Paragraph("              ______________________________              ______________________________              ______________________________              ", fuente2);
            firmas.setAlignment(Element.ALIGN_CENTER);
            documento.add(firmas);

            Paragraph nombres = new Paragraph("               Receptor                                                         Quien Entrega                                                A Quien Se Paga              ", fuente2);
            nombres.setAlignment(Element.ALIGN_CENTER);
            documento.add(nombres);

            Paragraph division = new Paragraph("------------------------------------------------------------------------------------------------------------------", fuente2);
            division.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            documento.add(division);

            // ----------------------------------------------------------------------------------------------------------------------
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            infoSociedad.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoSociedad);

            infoRecibo.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoRecibo);

            documento.add(bla);

            documento.add(tablaProdParc(nombreProductor, claveProd, localidadProd, nombrecorto, nombrePar, clavePar, localidadPar, certPar));

            /*encabezadoProd.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezadoProd);

            infoProd.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoProd);

            encabezadoParc.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezadoParc);

            infoParc.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoParc);*/
            documento.add(bla);

            documento.add(tablaRecibo(verdes, precioBrutoKgSociedad, inmaduros, precioNetoKgSociedad,
                    brocados, totalBruto, formaCafe, retencion, sacos, total, calificacion, kgRecibidos));

            documento.add(observa);

            documento.add(bla);

            documento.add(nombresFirmas);

            firmas.setAlignment(Element.ALIGN_CENTER);
            documento.add(firmas);

            nombres.setAlignment(Element.ALIGN_CENTER);
            documento.add(nombres);

            division.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            documento.add(division);

            // ----------------------------------------------------------------------------------------------------------------------
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            infoSociedad.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoSociedad);

            infoRecibo.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoRecibo);

            documento.add(bla);

            documento.add(tablaProdParc(nombreProductor, claveProd, localidadProd, nombrecorto, nombrePar, clavePar, localidadPar, certPar));

            /*encabezadoProd.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezadoProd);

            infoProd.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoProd);

            encabezadoParc.setAlignment(Element.ALIGN_CENTER);
            documento.add(encabezadoParc);

            infoParc.setAlignment(Element.ALIGN_CENTER);
            documento.add(infoParc);*/
            documento.add(bla);

            documento.add(tablaRecibo(verdes, precioBrutoKgSociedad, inmaduros, precioNetoKgSociedad,
                    brocados, totalBruto, formaCafe, retencion, sacos, total, calificacion, kgRecibidos));

            documento.add(observa);

            documento.add(bla);

            documento.add(nombresFirmas);

            firmas.setAlignment(Element.ALIGN_CENTER);
            documento.add(firmas);

            nombres.setAlignment(Element.ALIGN_CENTER);
            documento.add(nombres);

            documento.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public PdfPTable tablaRecibo(String verdes, String precioBrutoKgSociedad, String inmaduros, String precioNetoKgSociedad,
            String brocados, String totalBruto, String formaCafe, String retencion, String sacos, String total, String calificacion, String kgRecibidos) {

        Font fuente2 = new Font();
        fuente2.setSize(7);

        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        Paragraph columna1 = new Paragraph(formaCafe + "          Calificación " + calificacion);
        Paragraph columna2 = new Paragraph("Totales");

        columna1.getFont().setStyle(Font.BOLD);
        columna1.getFont().setSize(7);

        columna2.getFont().setStyle(Font.BOLD);
        columna2.getFont().setSize(7);

        Font fuente3 = new Font();
        fuente3.setSize(7);

        table.addCell(columna1);
        table.addCell(columna2);

        table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(100);

        Paragraph dVerdes = new Paragraph("Verdes:      " + verdes, fuente3);
        Paragraph dPrecioBrutoCereza = new Paragraph("Precio Bruto Cereza Por Kg:       $" + precioBrutoKgSociedad, fuente3);
        Paragraph dInmaduros = new Paragraph("Inmaduros:        " + inmaduros, fuente3);
        Paragraph dPrecioNetoCereza = new Paragraph("Precio Neto Cereza Por Kg:         $" + precioNetoKgSociedad, fuente3);
        Paragraph dBrocados = new Paragraph("Brocados:      " + brocados, fuente3);
        Paragraph dtotalBruto = new Paragraph("Precio Bruto Cereza:         $" + totalBruto, fuente3);
        Paragraph dFormaCafe = new Paragraph("Forma de Café:        " + formaCafe, fuente3);
        Paragraph dRetenciones = new Paragraph("Retenciones:        $" + retencion, fuente3);
        Paragraph dSacos = new Paragraph("Sacos:        " + sacos, fuente3);
        Paragraph dPago = new Paragraph("Pago al Productor:         $" + total, fuente3);
        Paragraph dCalifica = new Paragraph("Calificacion:        " + calificacion, fuente3);
        Paragraph dKg = new Paragraph("Kg Recibidos:        " + kgRecibidos, fuente3);

        /*table.addCell(dVerdes);
        table.addCell(dFormaCafe);
        table.addCell(dInmaduros);
        table.addCell(dPrecioBrutoCereza);
        table.addCell(dBrocados);
        table.addCell(dPrecioNetoCereza);
        table.addCell(dSacos);
        table.addCell(dtotalBruto);
        table.addCell(dCalifica);
        table.addCell(dRetenciones);
        table.addCell(dKg);
        table.addCell(dPago);*/
        table.addCell(dVerdes);
        table.addCell(dPrecioBrutoCereza);
        table.addCell(dInmaduros);
        table.addCell(dPrecioNetoCereza);
        table.addCell(dBrocados);
        table.addCell(dtotalBruto);
        table.addCell(dSacos);
        table.addCell(dRetenciones);
        table.addCell(dKg);
        table.addCell(dPago);

        return table;

    }

    public PdfPTable tablaProdParc(String prod, String claveProd, String locaProd, String sociedad, String parce,
            String claveParce, String locaParce, String certParce) {
        Font fuente2 = new Font();
        fuente2.setSize(7);

        PdfPTable table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        Paragraph columna1 = new Paragraph("Productor " + prod + "    -   " + claveProd);
        Paragraph columna2 = new Paragraph("Parcela " + parce + "    -   " + claveParce);

        columna1.getFont().setStyle(Font.BOLD);
        columna1.getFont().setSize(7);

        columna2.getFont().setStyle(Font.BOLD);
        columna2.getFont().setSize(7);

        table.addCell(columna1);
        table.addCell(columna2);

        table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(100);

        Paragraph datosProd = new Paragraph(locaProd + " " + sociedad, fuente2);
        table.addCell(datosProd);

        Paragraph datosParc = new Paragraph(locaParce + " Certificado: " + certParce, fuente2);
        table.addCell(datosParc);

        return table;
    }
}
