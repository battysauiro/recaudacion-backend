/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.util.reportes;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.recaudacionMunicipio.DTO.ContribuyenteFisicaDTO;
import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.DTO.FacturasNoPagadasDTO;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class lineasCapturasNoPagadasExporterPDF {
    
    private List<FacturasNoPagadasDTO> listaFacturasNoPagadas;
    private String titulo;

    public lineasCapturasNoPagadasExporterPDF(List<FacturasNoPagadasDTO> listaFacturasNoPagadas,String titulo) {
        this.listaFacturasNoPagadas = listaFacturasNoPagadas;
        this.titulo=titulo;
    }
    
    
   private void escribirCabeceraDeLaTabla(PdfPTable tabla){
       PdfPCell celda = new PdfPCell();
       
       celda.setBackgroundColor(Color.BLUE);
       celda.setPadding(5);
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
       fuente.setColor(Color.WHITE);
       
       celda.setPhrase(new Phrase("Folio",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Cajero",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Contribuyente",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Contribucion",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Fecha solicitud",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Descuento",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Total",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Estado pago",fuente));
       tabla.addCell(celda);
   }
   
   private void escribirDatosDeLaTabla(PdfPTable tabla){
       
       for(FacturasNoPagadasDTO cacturasNoPagadasDTO:listaFacturasNoPagadas){
           tabla.addCell(String.valueOf(cacturasNoPagadasDTO.getFolio()));
           tabla.addCell(cacturasNoPagadasDTO.getCajero());
           tabla.addCell(cacturasNoPagadasDTO.getContribuyente());
           tabla.addCell(cacturasNoPagadasDTO.getContribucion());
           tabla.addCell(cacturasNoPagadasDTO.getFecha_solicitud().toString());
           tabla.addCell(String.valueOf(cacturasNoPagadasDTO.getDescuento()));
           tabla.addCell(String.valueOf(cacturasNoPagadasDTO.getTotal()));
           tabla.addCell(cacturasNoPagadasDTO.getEstado_pago());
       }
       
   }
   
   public void exportar(HttpServletResponse response) throws IOException{
       
       Document documento = new Document(PageSize.LETTER.rotate());
       PdfWriter.getInstance(documento, response.getOutputStream());
       
       documento.open();
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fuente.setColor(Color.BLUE);
       fuente.setSize(18);
       
       Paragraph titulo = new Paragraph(this.titulo,fuente);
       titulo.setAlignment(Paragraph.ALIGN_CENTER);
       
       documento.add(titulo);
       
       PdfPTable tabla = new PdfPTable(8);
       tabla.setWidthPercentage(100);
       tabla.setSpacingBefore(15);
       tabla.setWidths(new float[] {0.8f,3f,3.3f,2f,1.5f,1.5f,1.5f,1.5f});
       tabla.setWidthPercentage(105);
       
       escribirCabeceraDeLaTabla(tabla);
       escribirDatosDeLaTabla(tabla);
       
       documento.add(tabla);
       documento.close();
       
   }    
}
