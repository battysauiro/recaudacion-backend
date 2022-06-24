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
import com.recaudacionMunicipio.DTO.ContribuyenteMoralDTO;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class ContribuyenteMoralExporterPDF {
    
    private List<ContribuyenteMoralDTO> listaContribuyentesMoral;

    public ContribuyenteMoralExporterPDF(List<ContribuyenteMoralDTO> listaContribuyentesMoral) {
        this.listaContribuyentesMoral = listaContribuyentesMoral;
    }
    
    
   private void escribirCabeceraDeLaTabla(PdfPTable tabla){
       PdfPCell celda = new PdfPCell();
       
       celda.setBackgroundColor(Color.BLUE);
       celda.setPadding(5);
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
       fuente.setColor(Color.WHITE);
       
       celda.setPhrase(new Phrase("RMC",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Direccion",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Nombre",fuente));
       tabla.addCell(celda);
       
   }
   
   private void escribirDatosDeLaTabla(PdfPTable tabla){
       
       for(ContribuyenteMoralDTO contribuyenteMoralDTO:listaContribuyentesMoral){
           tabla.addCell(contribuyenteMoralDTO.getRfc_contribuyente());
           String numeroCasa="";
           if(contribuyenteMoralDTO.getNumero()==null)
               numeroCasa=" #S/N";
           else
               numeroCasa=" #"+contribuyenteMoralDTO.getNumero();
           tabla.addCell("Calle: "+contribuyenteMoralDTO.getCalle()+numeroCasa+" "+contribuyenteMoralDTO.getColonia()+
                   " C.P:"+contribuyenteMoralDTO.getCodigo_postal());
           tabla.addCell(contribuyenteMoralDTO.getRazon_social());
       }
       
   }
   
   public void exportar(HttpServletResponse response) throws IOException{
       
       Document documento = new Document(PageSize.LETTER.rotate());
       PdfWriter.getInstance(documento, response.getOutputStream());
       
       documento.open();
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fuente.setColor(Color.BLUE);
       fuente.setSize(18);
       
       Paragraph titulo = new Paragraph("Lista de Personas Morales",fuente);
       titulo.setAlignment(Paragraph.ALIGN_CENTER);
       
       documento.add(titulo);
       
       PdfPTable tabla = new PdfPTable(3);
       tabla.setWidthPercentage(100);
       tabla.setSpacingBefore(15);
       tabla.setWidths(new float[] {2.2f,6f,4f});
       tabla.setWidthPercentage(80);
       
       escribirCabeceraDeLaTabla(tabla);
       escribirDatosDeLaTabla(tabla);
       
       documento.add(tabla);
       documento.close();
       
   }    
}
