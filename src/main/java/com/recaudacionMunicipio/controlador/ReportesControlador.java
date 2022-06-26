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
import com.recaudacionMunicipio.DTO.FacturasNoPagadasDTO;
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
import com.recaudacionMunicipio.servicios.FacturaServicioImpl;
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
import com.recaudacionMunicipio.util.reportes.lineasCapturasNoPagadasExporterPDF;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private FacturaServicioImpl facturasImplSer;
    
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
    @GetMapping("/listaContribucionMVehicular/exportarPDF")
    public void exportarListadoContribucionesAMultasVehicularPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesMVehicular_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<AprovechamientoMultaVehicularDTO> aprovechamientoMultaVehicularDTO =aprovechamientoMultaVehicularSer.listarContribucionesMVehicuar();
        
        ContribucionAMultaVehicularExporterPDF export = new ContribucionAMultaVehicularExporterPDF(aprovechamientoMultaVehicularDTO);
        export.exportar(response);
    }
    
    //lista reportes de contribucion otros productos
    @GetMapping("/listaContribucionOtrosProductos/exportarPDF")
    public void exportarListadoContribucionesOtrosProductosPDF(HttpServletResponse response) throws IOException{
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
    
    //lista reportes de contribuciones no pagadas
    @GetMapping("/listaContribucionesNoPagadas/exportarPDF/{estado}")
    public void exportarListadoContribucionesNoPagadasPDF(HttpServletResponse response,@PathVariable Boolean estado) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesNoPagadas_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadas(estado);
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Lineas de Capturas no Pagadas");
        export.exportar(response);
    }
    
    //lista reportes de contribuciones pagadas
    @GetMapping("/listaContribucionesPagadas/exportarPDF/{estado}")
    public void exportarListadoContribucionesPagadasPDF(HttpServletResponse response,@PathVariable Boolean estado) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        String valor ="attachment; filename=contribucionesPagadas_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadas(estado);
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Lineas de Capturas no Pagadas");
        export.exportar(response);
    }
    
    //lista reportes por tipo de contribuciones no pagadas y pagadas
    @GetMapping("/listaContribucionesTipoNoPagadas/exportarPDF/{estado}/{tipo}")
    public void exportarListadoContribucionesTipoNoPagadasPDF(HttpServletResponse response,@PathVariable Boolean estado,@PathVariable int tipo) throws IOException{
        response.setContentType("application/pdf");
        String valor="";
        String tituloTipo="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        if(tipo==1){
            valor ="attachment; filename=contribucionesNoPagadasImpuestos_"+fechaActual+".pdf";
            tituloTipo="Impuestos";
        }
        if(tipo==2){
            valor ="attachment; filename=contribucionesNoPagadasDerechosGeneral_"+fechaActual+".pdf";
            tituloTipo="Derechos Generales";
        }
        if(tipo==3){
            valor ="attachment; filename=contribucionesNoPagadasDerechosLicencia_"+fechaActual+".pdf";
            tituloTipo="Derechos Licencias";
        }
        if(tipo==4){
            valor ="attachment; filename=contribucionesNoPagadasMulta_"+fechaActual+".pdf";
            tituloTipo="Multas Generales";
        }
        if(tipo==5){
            valor ="attachment; filename=contribucionesNoPagadasMultaEbriedad_"+fechaActual+".pdf";
            tituloTipo="Multas Ebriedades";
        }
        if(tipo==6){
            valor ="attachment; filename=contribucionesNoPagadasMultaVehicular_"+fechaActual+".pdf";
            tituloTipo="Multas Vehiculares";
        }
        if(tipo==7){
            valor ="attachment; filename=contribucionesNoPagadasOtrosProductos_"+fechaActual+".pdf";
            tituloTipo="Otros Productos";
        }
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadasPorTipoContribucionPersonasFisicas(estado,tipo);
        String sEstado="";
        if(estado==false)
             sEstado="No";
        else
            sEstado="";
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Contribuciones "+tituloTipo+ " "+sEstado+" Pagadas (Personas Fisicas)");
        export.exportar(response);
    }
    
    //lista reportes por tipo de contribuciones no pagadas y pagadas personas morales
    @GetMapping("/listaContribucionesTipoNoPagadasM/exportarPDF/{estado}/{tipo}")
    public void exportarListadoContribucionesTipoNoPagadasMPDF(HttpServletResponse response,@PathVariable Boolean estado,@PathVariable int tipo) throws IOException{
        response.setContentType("application/pdf");
        String valor="";
        String tituloTipo="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        if(tipo==1){
            valor ="attachment; filename=contribucionesNoPagadasImpuestos_"+fechaActual+".pdf";
            tituloTipo="Impuestos";
        }
        if(tipo==2){
            valor ="attachment; filename=contribucionesNoPagadasDerechosGeneral_"+fechaActual+".pdf";
            tituloTipo="Derechos Generales";
        }
        if(tipo==3){
            valor ="attachment; filename=contribucionesNoPagadasDerechosLicencia_"+fechaActual+".pdf";
            tituloTipo="Derechos Licencias";
        }
        if(tipo==4){
            valor ="attachment; filename=contribucionesNoPagadasMulta_"+fechaActual+".pdf";
            tituloTipo="Multas Generales";
        }
        if(tipo==5){
            valor ="attachment; filename=contribucionesNoPagadasMultaEbriedad_"+fechaActual+".pdf";
            tituloTipo="Multas Ebriedades";
        }
        if(tipo==6){
            valor ="attachment; filename=contribucionesNoPagadasMultaVehicular_"+fechaActual+".pdf";
            tituloTipo="Multas Vehiculares";
        }
        if(tipo==7){
            valor ="attachment; filename=contribucionesNoPagadasOtrosProductos_"+fechaActual+".pdf";
            tituloTipo="Otros Productos";
        }
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadasPorTipoContribucionPersonasMorales(estado,tipo);
        String sEstado="";
        if(estado==false)
             sEstado="No";
        else
            sEstado="";
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Contribuciones "+tituloTipo+ " "+sEstado+" Pagadas (Personas Fisicas)");
        export.exportar(response);
    }
    
    //lista reportes por concepto de contribuciones no pagadas y pagadas fisicas
    @GetMapping("/listaContribucionesConceptoNoPagadas/exportarPDF/{estado}/{concepto}")
    public void exportarListadoContribucionesConceptoNoPagadasPDF(HttpServletResponse response,@PathVariable Boolean estado,@PathVariable String concepto) throws IOException{
        response.setContentType("application/pdf");
        String valor="";
        String tituloTipo="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        valor ="attachment; filename=contribucion"+concepto+"NoPagadas_"+fechaActual+".pdf";
           
        String cabecera="Content-Disposition";
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadasPorConceptoContribucionPersonasFisicas(estado,concepto);
        String sEstado="";
        if(estado==false)
             sEstado="No";
        else
            sEstado="";
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Contribuciones "+concepto+ " "+sEstado+" Pagadas (Personas Fisicas)");
        export.exportar(response);
    }
    
    //lista reportes por concepto de contribuciones no pagadas y pagadas personas morales
    @GetMapping("/listaContribucionesConceptoNoPagadasM/exportarPDF/{estado}/{concepto}")
    public void exportarListadoContribucionesConceptoNoPagadasMPDF(HttpServletResponse response,@PathVariable Boolean estado,@PathVariable String concepto) throws IOException{
        response.setContentType("application/pdf");
        String valor="";
        String tituloTipo="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        
        valor ="attachment; filename=contribucionesNoPagadas"+concepto+"_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadasPorConceptoContribucionPersonasMorales(estado,concepto);
        String sEstado="";
        if(estado==false)
             sEstado="No";
        else
            sEstado="";
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Contribuciones "+concepto+ " "+sEstado+" Pagadas (Personas Fisicas)");
        export.exportar(response);
    }
    
    //lista reportes por concepto de contribuciones no pagadas y pagadas fisicas
    @GetMapping("/listaContribuyentesFisicasNoPagadas/exportarPDF/{rfc}/{estado}")
    public void exportarListadoContribuyenteMoralNoPagadasPDF(HttpServletResponse response,@PathVariable String rfc,@PathVariable Boolean estado) throws IOException{
        response.setContentType("application/pdf");
        String valor="";
        String tituloTipo="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        valor ="attachment; filename=contribucion"+rfc+"NoPagadas_"+fechaActual+".pdf";
           
        String cabecera="Content-Disposition";
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadasPorContribuyenteFisica(rfc,estado);
        String sEstado="";
        if(estado==false)
             sEstado="No";
        else
            sEstado="";
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Contribuciones "+rfc+ " "+sEstado+" Pagadas (Personas Fisicas)");
        export.exportar(response);
    }
    
    //lista reportes por concepto de contribuciones no pagadas y pagadas personas morales
    @GetMapping("/listaContribucionesContribuyenteMNoPagadasM/exportarPDF/{rfc}/{estado}")
    public void exportarListadoContribuyenteMoralNoPagadasMDF(HttpServletResponse response,@PathVariable Boolean estado,@PathVariable String rfc) throws IOException{
        response.setContentType("application/pdf");
        String valor="";
        String tituloTipo="";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera="Content-Disposition";
        
        valor ="attachment; filename=contribucionesNoPagadas"+rfc+"_"+fechaActual+".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO =facturasImplSer.facturasNoPagadasPorPersonasMorales(rfc,estado);
        String sEstado="";
        if(estado==false)
             sEstado="No";
        else
            sEstado="";
        
        lineasCapturasNoPagadasExporterPDF export = new lineasCapturasNoPagadasExporterPDF(facturasNoPagadasDTO,"Contribuciones "+rfc+ " "+sEstado+" Pagadas (Personas Morales)");
        export.exportar(response);
    }
}
