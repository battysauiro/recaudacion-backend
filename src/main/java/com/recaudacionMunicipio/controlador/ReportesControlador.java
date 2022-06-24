/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaDTO;
import com.recaudacionMunicipio.DTO.AprovechamientoMultaEbriedadDTO;
import com.recaudacionMunicipio.DTO.AprovechamientoMultaVehicularDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteFisicaDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteMoralDTO;
import com.recaudacionMunicipio.DTO.DerechosGeneralDTO;
import com.recaudacionMunicipio.DTO.DerechosLicenciaDTO;
import com.recaudacionMunicipio.DTO.ImpuestoDTO;
import com.recaudacionMunicipio.DTO.OtrosProductosDTO;
import com.recaudacionMunicipio.dao.IcontribuyenteMoralDao;
import com.recaudacionMunicipio.servicios.AprovechamientoMultaEbriedadServicioImpl;
import com.recaudacionMunicipio.servicios.AprovechamientoMultaServicioImpl;
import com.recaudacionMunicipio.servicios.AprovechamientoMultaVehicularImpl;
import com.recaudacionMunicipio.servicios.ContribuyenteFisicaServicioImpl;
import com.recaudacionMunicipio.servicios.ContribuyenteMoralServicioImpl;
import com.recaudacionMunicipio.servicios.DerechoGeneralServicioImpl;
import com.recaudacionMunicipio.servicios.DerechosLicenciasServicioImpl;
import com.recaudacionMunicipio.servicios.ImpuestoServicioImpl;
import com.recaudacionMunicipio.servicios.OtrosProductosServicioImpl;
import com.recaudacionMunicipio.util.reportes.ContribucionAMultaEbriedadExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribucionAMultaVehicularExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribucionAMultasExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribucionDerechosGeneralExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribucionDerechosLicencialExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribucionImpuestoExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribucionOtrosProductosExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribuyenteFisicaExporterPDF;
import com.recaudacionMunicipio.util.reportes.ContribuyenteMoralExporterPDF;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController 
@RequestMapping("/api/reportes")
public class ReportesControlador {
    
    @Autowired
    private ContribuyenteFisicaServicioImpl contribuyenteFisicaImplSer;
    
    @Autowired
    private ContribuyenteMoralServicioImpl contribuyenteMoralImplSer;
    
    @Autowired
    private ImpuestoServicioImpl impuestoSer;
    
    @Autowired
    private DerechoGeneralServicioImpl DerechoGeneralImplSer;
    
    @Autowired
    private DerechosLicenciasServicioImpl DerechoLicenciasImplSer;
    
    @Autowired
    private AprovechamientoMultaServicioImpl aprovechamientoMultaSer;
    
    @Autowired
    private AprovechamientoMultaEbriedadServicioImpl aprovechamientoMultaEbriedadSer;
    
    @Autowired
    private AprovechamientoMultaVehicularImpl aprovechamientoMultaVehicularSer;
    
    @Autowired
    private OtrosProductosServicioImpl otrosProductosImplSer;
    
    @GetMapping("/listaContribuyenteFisica/exportarPDF")
    public void exportarListadoContribuyentesFisicasPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribuyentesFisicas_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<ContribuyenteFisicaDTO> contribuyenteFisicaDTO =contribuyenteFisicaImplSer.listarContribuentesFisicas();
        
        ContribuyenteFisicaExporterPDF export = new ContribuyenteFisicaExporterPDF(contribuyenteFisicaDTO);
        export.exportar(response);
    }
    
    @GetMapping("/listaContribuyenteMoral/exportarPDF")
    public void exportarListadoContribuyentesMoralesPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribuyentesMorales_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<ContribuyenteMoralDTO> contribuyenteMoralDTO =contribuyenteMoralImplSer.listarContribuentesMorales();
        
        ContribuyenteMoralExporterPDF export = new ContribuyenteMoralExporterPDF(contribuyenteMoralDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion Impuesto
    @GetMapping("/listaContribucionImpuesto/exportarPDF")
    public void exportarListadoContribucionesImpuestoPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesImpuestos_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<ImpuestoDTO> impuestoDTO =impuestoSer.listarImpuestos();
        
        ContribucionImpuestoExporterPDF export = new ContribucionImpuestoExporterPDF(impuestoDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion derecho general
    @GetMapping("/listaContribucionDerechosG/exportarPDF")
    public void exportarListadoContribucionesDGeneralPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesDGeneral_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<DerechosGeneralDTO> derechosGeneralDTO =DerechoGeneralImplSer.listaCDerechosGeneral();
        
        ContribucionDerechosGeneralExporterPDF export = new ContribucionDerechosGeneralExporterPDF(derechosGeneralDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion derecho licencia
    @GetMapping("/listaContribucionDerechosL/exportarPDF")
    public void exportarListadoContribucionesDLicenciasPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesDLicencias_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<DerechosLicenciaDTO> derechosLicenciaDTO =DerechoLicenciasImplSer.listarContribucionesDLicencias();
        
        ContribucionDerechosLicencialExporterPDF export = new ContribucionDerechosLicencialExporterPDF(derechosLicenciaDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion Aprovechamiento Multa
    @GetMapping("/listaContribucionAmultas/exportarPDF")
    public void exportarListadoContribucionesAMultasPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesAmultas_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<AprovechamientoMultaDTO> aprovechamientoMultaDTO =aprovechamientoMultaSer.listaAprovechamientoMultas();
        
        ContribucionAMultasExporterPDF export = new ContribucionAMultasExporterPDF(aprovechamientoMultaDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion Aprovechamiento multa Ebriedad
    @GetMapping("/listaContribucionAmultasEbriedad/exportarPDF")
    public void exportarListadoContribucionesAMultasEbriedadPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesAEbriedad_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<AprovechamientoMultaEbriedadDTO> aprovechamientoMultaEbriedadDTO =aprovechamientoMultaEbriedadSer.listaAEbriedad();
        
        ContribucionAMultaEbriedadExporterPDF export = new ContribucionAMultaEbriedadExporterPDF(aprovechamientoMultaEbriedadDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion Aprovechamiento multa vehicular
    @GetMapping("/listaContribucionOtrosProductos/exportarPDF")
    public void exportarListadoContribucionesAMultasVehicularPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesOtrosProductos_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<OtrosProductosDTO> aprovechamientoMultaVehicularDTO =otrosProductosImplSer.listarOtrosProductos();
        
        ContribucionOtrosProductosExporterPDF export = new ContribucionOtrosProductosExporterPDF(aprovechamientoMultaVehicularDTO);
        export.exportar(response);
    }
    
    
    
}
