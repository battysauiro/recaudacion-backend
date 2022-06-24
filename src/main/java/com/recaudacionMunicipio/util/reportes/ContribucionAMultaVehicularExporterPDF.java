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
import com.recaudacionMunicipio.DTO.AprovechamientoMultaEbriedadDTO;
import com.recaudacionMunicipio.DTO.AprovechamientoMultaVehicularDTO;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class ContribucionAMultaVehicularExporterPDF {
    
    private List<AprovechamientoMultaVehicularDTO> listaContribucionesVehicular;

    public ContribucionAMultaVehicularExporterPDF(List<AprovechamientoMultaVehicularDTO> listaContribucionesVehicular) {
        this.listaContribucionesVehicular = listaContribucionesVehicular;
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
       
       celda.setPhrase(new Phrase("Tipo Aprovechamiento",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Articulo",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Tipo vehiculo",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Uma Min",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Uma Max",fuente));
       tabla.addCell(celda);
       
       
   }
   
   private void escribirDatosDeLaTabla(PdfPTable tabla){
       
       for(AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO:listaContribucionesVehicular){
           tabla.addCell(aprovechamientoMultaVehicularDTO.getCodigo_contribucion());
           tabla.addCell(aprovechamientoMultaVehicularDTO.getConcepto_contribucion());
           tabla.addCell(aprovechamientoMultaVehicularDTO.getDescripcion());
           tabla.addCell(aprovechamientoMultaVehicularDTO.getScatalogo());
           tabla.addCell(aprovechamientoMultaVehicularDTO.getDescripcion_articulo());
           tabla.addCell(aprovechamientoMultaVehicularDTO.getSvehiculo());
           tabla.addCell(String.valueOf(aprovechamientoMultaVehicularDTO.getUma_min()));
           tabla.addCell(String.valueOf(aprovechamientoMultaVehicularDTO.getUma_max()));    
           
       }
       
   }
   
   public void exportar(HttpServletResponse response) throws IOException{
       
       Document documento = new Document(PageSize.LETTER.rotate());
       PdfWriter.getInstance(documento, response.getOutputStream());
       
       documento.open();
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fuente.setColor(Color.BLUE);
       fuente.setSize(18);
       
       Paragraph titulo = new Paragraph("Lista Contribuciones Aprovechamiento Vehicular",fuente);
       titulo.setAlignment(Paragraph.ALIGN_CENTER);
       
       documento.add(titulo);
       
       PdfPTable tabla = new PdfPTable(8);
       tabla.setWidthPercentage(100);
       tabla.setSpacingBefore(15);
       tabla.setWidths(new float[] {1.4f,5f,2f,3f,1f,1f,1f,1f});
       tabla.setWidthPercentage(105);
       
       escribirCabeceraDeLaTabla(tabla);
       escribirDatosDeLaTabla(tabla);
       
       documento.add(tabla);
       documento.close();
       
   }
}
