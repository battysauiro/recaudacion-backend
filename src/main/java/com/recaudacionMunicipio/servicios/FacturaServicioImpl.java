/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.ContribucionFacturaDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteDTO;
import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.DTO.FacturasNoPagadasDTO;
import com.recaudacionMunicipio.dao.IContribucionFacturaDao;
import com.recaudacionMunicipio.dao.IFacturaDao;
import com.recaudacionMunicipio.dao.IcontribuyenteDao;
import com.recaudacionMunicipio.dao.IcontribuyenteFisicaDao;
import com.recaudacionMunicipio.modelo.Contribucionfactura;
import com.recaudacionMunicipio.modelo.Contribuyente;
import com.recaudacionMunicipio.modelo.ContribuyenteFisica;
import com.recaudacionMunicipio.modelo.Factura;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public class FacturaServicioImpl {

    @Autowired
    private IFacturaDao facturaDao;

    @Autowired
    private IContribucionFacturaDao contribucionFacturaDao;
    
    @Autowired
    private IcontribuyenteDao contribuyenteDao;

    public List<FacturasNoPagadasDTO> facturasNoPagadas(boolean estado) {

        List<Factura> f = facturaDao.findByEstadoPago(estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
            if (factura.getContribuyenteId().getContribuyenteFisica() == null) {
                facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral(), factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
            } else {
                String nombre = factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
                facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        nombre, factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
            }
        }
        return facturasNoPagadasDTO;
    }
    
    public List<FacturasNoPagadasDTO> facturasNoPagadasPorTipoContribucionPersonasFisicas(boolean estado,int tipo) {
        List<Factura> f = facturaDao.findByEstadoPago(estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
              if(factura.getContribuyenteId().getContribuyenteFisica()!=null){
                  if(factura.getContribucionfacturaList().get(0).getContribucionId().getNivelContribucion()==tipo){
                        String nombre = factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
                        facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        nombre, factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
                }
              }
        }
        return facturasNoPagadasDTO;
    }
    
    public List<FacturasNoPagadasDTO> facturasNoPagadasPorTipoContribucionPersonasMorales(boolean estado,int tipo) {
        List<Factura> f = facturaDao.findByEstadoPago(estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
              if(factura.getContribuyenteId().getContribuyenteMoral()!=null){
                  if(factura.getContribucionfacturaList().get(0).getContribucionId().getNivelContribucion()==tipo){
                        facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral(), factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
                }
              }
        }
        return facturasNoPagadasDTO;
    }
    
    public List<FacturasNoPagadasDTO> facturasNoPagadasPorConceptoContribucionPersonasFisicas(boolean estado,String concepto) {
        List<Factura> f = facturaDao.findByEstadoPago(estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
              if(factura.getContribuyenteId().getContribuyenteFisica()!=null){
                  if(factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion().equalsIgnoreCase(concepto)){
                        String nombre = factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
                        facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        nombre, factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
                }
              }
        }
        return facturasNoPagadasDTO;
    }
    
    public List<FacturasNoPagadasDTO> facturasNoPagadasPorConceptoContribucionPersonasMorales(boolean estado,String concepto) {
        List<Factura> f = facturaDao.findByEstadoPago(estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
              if(factura.getContribuyenteId().getContribuyenteMoral()!=null){
                  if(factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion().equalsIgnoreCase(concepto)){
                        facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral(), factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
                }
              }
        }
        return facturasNoPagadasDTO;
    }
    
    public List<FacturasNoPagadasDTO> facturasNoPagadasPorContribuyenteFisica(String rfc,boolean estado) {
        Contribuyente contribuyente=contribuyenteDao.findById(rfc).orElse(null);
        List<Factura> f = facturaDao.findByContribuyenteIdAndEstadoPago(contribuyente,estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
              if(factura.getContribuyenteId().getContribuyenteFisica()!=null){
                  
                        String nombre = factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica() + " " + factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
                        facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        nombre, factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
                }
              
        }
        return facturasNoPagadasDTO;
    }
    
    public List<FacturasNoPagadasDTO> facturasNoPagadasPorPersonasMorales(String rfc,boolean estado) {
        Contribuyente contribuyente=contribuyenteDao.findById(rfc).orElse(null);
        List<Factura> f = facturaDao.findByContribuyenteIdAndEstadoPago(contribuyente,estado);
        List<FacturasNoPagadasDTO> facturasNoPagadasDTO = new ArrayList<>();
        for (Factura factura : f) {
            
            String estadoPago = "";
            if (factura.getEstadoPago()) {
                estadoPago = "Pagado";
            } else {
                estadoPago = "Pendiente";
            }
              if(factura.getContribuyenteId().getContribuyenteMoral()!=null){
                  
                        facturasNoPagadasDTO.add(new FacturasNoPagadasDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoP() + " " + factura.getUsuarioId().getIdEmpleado().getApellidoM(),
                        factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral(), factura.getContribucionfacturaList().get(0).getContribucionId().getCodigoContribucion() + " " + factura.getContribucionfacturaList().get(0).getContribucionId().getConceptoContribucion(), factura.getFecha(), factura.getDescuento(),
                        factura.getTotal(), estadoPago));
                
              }
        }
        return facturasNoPagadasDTO;
    }

}
