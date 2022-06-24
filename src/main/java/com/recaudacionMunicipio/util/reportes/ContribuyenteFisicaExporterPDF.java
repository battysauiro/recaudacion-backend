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
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.recaudacionMunicipio.DTO.ContribuyenteFisicaDTO;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class ContribuyenteFisicaExporterPDF {
    
    private List<ContribuyenteFisicaDTO> listaContribuyentesFisicas;

    public ContribuyenteFisicaExporterPDF(List<ContribuyenteFisicaDTO> listaContribuyentesFisicas) {
        this.listaContribuyentesFisicas = listaContribuyentesFisicas;
    }
    
    
   private void escribirCabeceraDeLaTabla(PdfPTable tabla){
       PdfPCell celda = new PdfPCell();
       
       celda.setBackgroundColor(Color.BLUE);
       celda.setPadding(5);
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
       fuente.setColor(Color.WHITE);
       
       celda.setPhrase(new Phrase("RMC",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("CURP",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Direccion",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Nombre",fuente));
       tabla.addCell(celda);
       
       celda.setPhrase(new Phrase("Fecha",fuente));
       tabla.addCell(celda);
   }
   
   private void escribirDatosDeLaTabla(PdfPTable tabla){
       
       for(ContribuyenteFisicaDTO contribuyenteFisicaDTO:listaContribuyentesFisicas){
           tabla.addCell(contribuyenteFisicaDTO.getRfc_contribuyente());
           tabla.addCell(contribuyenteFisicaDTO.getCurp());
           String numeroCasa="";
           if(contribuyenteFisicaDTO.getNumero()==null)
               numeroCasa=" #S/N";
           else
               numeroCasa=" #"+contribuyenteFisicaDTO.getNumero();
           tabla.addCell("Calle: "+contribuyenteFisicaDTO.getCalle()+numeroCasa+" "+contribuyenteFisicaDTO.getColonia()+
                   " C.P:"+contribuyenteFisicaDTO.getCodigo_postal());
           tabla.addCell(contribuyenteFisicaDTO.getNombre()+" "+contribuyenteFisicaDTO.getApellido_p()+" "+contribuyenteFisicaDTO.getApellido_m());
           tabla.addCell(contribuyenteFisicaDTO.getFecha().toString());
       }
       
   }
   
   public void exportar(HttpServletResponse response) throws IOException{
       
       Document documento = new Document(PageSize.LETTER.rotate());
       PdfWriter.getInstance(documento, response.getOutputStream());
       
       documento.open();
       
       Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       fuente.setColor(Color.BLUE);
       fuente.setSize(18);
       
       Paragraph titulo = new Paragraph("Lista de Personas Fisicas",fuente);
       titulo.setAlignment(Paragraph.ALIGN_CENTER);
       
       documento.add(titulo);
       
       PdfPTable tabla = new PdfPTable(5);
       tabla.setWidthPercentage(100);
       tabla.setSpacingBefore(15);
       tabla.setWidths(new float[] {2.2f,3f,3.3f,5f,1.5f});
       tabla.setWidthPercentage(105);
       
       escribirCabeceraDeLaTabla(tabla);
       escribirDatosDeLaTabla(tabla);
       
       documento.add(tabla);
       documento.close();
       
   }    
}
