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
import com.recaudacionMunicipio.DTO.DerechosGeneralDTO;
import com.recaudacionMunicipio.DTO.ImpuestoDTO;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class ContribucionDerechosGeneralExporterPDF {
    
    private List<DerechosGeneralDTO> listaContribucionesDGenerales;

    public ContribucionDerechosGeneralExporterPDF(List<DerechosGeneralDTO> listaContribucionesDGenerales) {
        this.listaContribucionesDGenerales = listaContribucionesDGenerales;
    }
    
    
   private void escribirCabeceraDeLaTabla(PdfPTable tabla){
       PdfPCell celda = new PdfPCell();
       
       celda.setBackgroundColor(Color.BLUE);
       celda.setPadding(5);
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
       fuente.setColor(Color.WHITE);
       
       celda.setPhrase(new Phrase("Codigo",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Descripción",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Concepto",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Tipo Derecho",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Periodicidad",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Tipo pago",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("precio",fuente));
       tabla.addCell(celda);
       
   }
   
   private void escribirDatosDeLaTabla(PdfPTable tabla){
       
       for(DerechosGeneralDTO derechosGeneralDTO:listaContribucionesDGenerales){
           tabla.addCell(derechosGeneralDTO.getCodigo_contribucion());
           tabla.addCell(derechosGeneralDTO.getConcepto_contribucion());
           tabla.addCell(derechosGeneralDTO.getDescripcion());
           tabla.addCell(derechosGeneralDTO.getScatalogo_derechos());
           tabla.addCell(derechosGeneralDTO.getPeriodicidad());
           tabla.addCell(derechosGeneralDTO.getTipo_pago());
           tabla.addCell(String.valueOf(derechosGeneralDTO.getCantidad()));
           
           
       }
       
   }
   
   public void exportar(HttpServletResponse response) throws IOException{
       
       Document documento = new Document(PageSize.LETTER.rotate());
       PdfWriter.getInstance(documento, response.getOutputStream());
       
       documento.open();
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fuente.setColor(Color.BLUE);
       fuente.setSize(18);
       
       Paragraph titulo = new Paragraph("Lista Contribuciones Derechos Generales",fuente);
       titulo.setAlignment(Paragraph.ALIGN_CENTER);
       
       documento.add(titulo);
       
       PdfPTable tabla = new PdfPTable(7);
       tabla.setWidthPercentage(100);
       tabla.setSpacingBefore(15);
       tabla.setWidths(new float[] {1.4f,5f,2f,5f,1.5f,1f,1f});
       tabla.setWidthPercentage(105);
       
       escribirCabeceraDeLaTabla(tabla);
       escribirDatosDeLaTabla(tabla);
       
       documento.add(tabla);
       documento.close();
       
   }    
}
