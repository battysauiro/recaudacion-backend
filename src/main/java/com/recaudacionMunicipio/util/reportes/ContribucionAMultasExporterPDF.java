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
import com.recaudacionMunicipio.DTO.AprovechamientoMultaDTO;
import com.recaudacionMunicipio.DTO.DerechosLicenciaDTO;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class ContribucionAMultasExporterPDF {
    
    private List<AprovechamientoMultaDTO> listaContribucionesAMultas;

    public ContribucionAMultasExporterPDF(List<AprovechamientoMultaDTO> listaContribucionesAMultas) {
        this.listaContribucionesAMultas = listaContribucionesAMultas;
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
       
       celda.setPhrase(new Phrase("Tipo Impuesto",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Tipo pago",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("precio",fuente));
       tabla.addCell(celda);
       
   }
   
   private void escribirDatosDeLaTabla(PdfPTable tabla){
       
       for(AprovechamientoMultaDTO aprovechamientoMultaDTO:listaContribucionesAMultas){
           tabla.addCell(aprovechamientoMultaDTO.getCodigo_contribucion());
           tabla.addCell(aprovechamientoMultaDTO.getConcepto_contribucion());
           tabla.addCell(aprovechamientoMultaDTO.getDescripcion());
           tabla.addCell(aprovechamientoMultaDTO.getScatalogo());
           tabla.addCell(aprovechamientoMultaDTO.getTipo_pago());
           tabla.addCell(String.valueOf(aprovechamientoMultaDTO.getCantidad()));
           
           
       }
       
   }
   
   public void exportar(HttpServletResponse response) throws IOException{
       
       Document documento = new Document(PageSize.LETTER.rotate());
       PdfWriter.getInstance(documento, response.getOutputStream());
       
       documento.open();
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fuente.setColor(Color.BLUE);
       fuente.setSize(18);
       
       Paragraph titulo = new Paragraph("Lista Contribuciones Aprovechamiento Multas",fuente);
       titulo.setAlignment(Paragraph.ALIGN_CENTER);
       
       documento.add(titulo);
       
       PdfPTable tabla = new PdfPTable(6);
       tabla.setWidthPercentage(100);
       tabla.setSpacingBefore(15);
       tabla.setWidths(new float[] {1.4f,5f,2f,5f,1.5f,1f});
       tabla.setWidthPercentage(105);
       
       escribirCabeceraDeLaTabla(tabla);
       escribirDatosDeLaTabla(tabla);
       
       documento.add(tabla);
       documento.close();
       
   }
    
}
