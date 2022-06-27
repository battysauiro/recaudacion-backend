/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.ContribucionFacturaDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteDTO;
import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.dao.IContribucionFacturaDao;
import com.recaudacionMunicipio.dao.IFacturaDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IcontribuyenteDao;
import com.recaudacionMunicipio.dao.IusuarioDao;
import com.recaudacionMunicipio.modelo.Contribucionfactura;
import com.recaudacionMunicipio.modelo.Contribuyente;
import com.recaudacionMunicipio.modelo.Factura;
import com.recaudacionMunicipio.modelo.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */ 
@Service
public class ContribuyenteServicioImpl {
    
    @Autowired
    private IcontribuyenteDao contribuyenteDao;
    
    @Autowired
    private IFacturaDao facturaDao;
    
    @Autowired
    private IContribucionFacturaDao contribucionFacturaDao;
    
    @Autowired
    private IusuarioDao usuarioDao;
    
    @Autowired
    private IcontribucionDao contribucionDao;
    
    public ContribuyenteDTO findById(String id){
        Contribuyente c=contribuyenteDao.findById(id).orElse(null);
        List<Factura> f=c.getFacturaList();
        List<FacturaDTO> facturaDTO=new ArrayList<>();
        List<ContribucionFacturaDTO> CfacturaDTO=new ArrayList<>();
        for(Factura factura:f){
            List<Contribucionfactura> contribucionfactura=factura.getContribucionfacturaList();
            for(Contribucionfactura contribucionf:contribucionfactura){
                
                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(),contribucionf.getCantidad()));
            }
            String numero=" S/N ";
                if(factura.getContribuyenteId().getNumero()!=null)
                    numero=" #"+factura.getContribuyenteId().getNumero();
                String domicilio="Calle: "+factura.getContribuyenteId().getCalle()+numero+factura.getContribuyenteId().getColonia()+" C.P: "+factura.getContribuyenteId().getCp();
            facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(),domicilio,"", factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO,factura.getEstadoPago()));
        }
        ContribuyenteDTO contribuyenteDTO= new ContribuyenteDTO(c.getRfcContribuyente(),c.getCalle() , c.getNumero(), c.getColonia(), c.getCp(), facturaDTO);
        return contribuyenteDTO;
         
    }
    
    public boolean findByContribuyenteId(String id,String contribucion){
        
        Contribuyente contribuyente= contribuyenteDao.findById(id).orElse(null);
        List<Factura> facturas = facturaDao.findByContribuyenteId(contribuyente);
        if(facturas!=null){
            for(Factura fact:facturas){
                for(Contribucionfactura cFactura: fact.getContribucionfacturaList()){
                    String codigo =cFactura.getContribucionId().getCodigoContribucion();
                    if(codigo.equalsIgnoreCase(contribucion))
                        return true;
                }
            }

        }
        return false;
    }
    
    public FacturaDTO obtenerFactura(String id,String contribucion){
        String nombreContribuyente="";
        Contribuyente contribuyente= contribuyenteDao.findById(id).orElse(null);
        List<Factura> facturas = facturaDao.findByContribuyenteId(contribuyente);
        if(facturas!=null){
            for(Factura fact:facturas){
                for(Contribucionfactura cFactura: fact.getContribucionfacturaList()){
                    String codigo =cFactura.getContribucionId().getCodigoContribucion();
                    if(codigo.equalsIgnoreCase(contribucion)){
                        if(fact.getContribuyenteId().getContribuyenteFisica()!=null){
                            nombreContribuyente=fact.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica()+" "+fact.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica()+" "+fact.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
                               }
                         else{
                                nombreContribuyente=fact.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral();
                               }
                        List<ContribucionFacturaDTO> CfacturaDTO=new ArrayList<>();
                        List<Contribucionfactura> contribucionfacturas=fact.getContribucionfacturaList();
                        for(Contribucionfactura contribucionf:contribucionfacturas){
                                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(),contribucionf.getCantidad()));
                        }
                        String numero=" S/N ";
                        if(fact.getContribuyenteId().getNumero()!=null)
                            numero=" #"+fact.getContribuyenteId().getNumero();
                        String domicilio="Calle: "+fact.getContribuyenteId().getCalle()+numero+fact.getContribuyenteId().getColonia()+" C.P: "+fact.getContribuyenteId().getCp();
                        FacturaDTO facturasDTO = new FacturaDTO(fact.getFolio(), fact.getUsuarioId().getIdEmpleado().getNombre()+" "+fact.getUsuarioId().getIdEmpleado().getApellidoP()+" "+fact.getUsuarioId().getIdEmpleado().getApellidoM()
                , nombreContribuyente,domicilio,fact.getContribuyenteId().getRfcContribuyente(), fact.getFecha(), fact.getDescuento(), fact.getTotal(), CfacturaDTO,fact.getEstadoPago());
                        
                        return facturasDTO;
                    }
                        
                }
            }

        }
        return null;
    }
    
    //vrific si ya expiro el tiempo para el pago de su linea de captura regresa 
    public boolean lineaCapturaPendiente(String id,String contribucion){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarFechaHo = Calendar.getInstance();
        calendarFechaHo.setTime(date);
        int dayActual=calendarFechaHo.get(Calendar.DAY_OF_MONTH);
        int mesActual=calendarFechaHo.get(Calendar.MONTH);    
        Contribuyente contribuyente= contribuyenteDao.findById(id).orElse(null);
        List<Factura> facturas = facturaDao.findByContribuyenteId(contribuyente);
        if(facturas!=null){
            for(Factura fact:facturas){
                
                
                for(Contribucionfactura cFactura: fact.getContribucionfacturaList()){
                    String codigo =cFactura.getContribucionId().getCodigoContribucion();
                    calendar.setTime(fact.getFecha());
                    int dayCaptura=calendar.get(Calendar.DAY_OF_MONTH); 
                    int mesCaptura=calendar.get(Calendar.MONTH);
                    if(codigo.equalsIgnoreCase(contribucion) && dayCaptura<=dayActual && mesCaptura<=mesActual)
                        return true;
                }    
            }

        }
        return false;
    }
    
    public FacturaDTO findFacturaById(int id){
        Factura factura = facturaDao.findById(id).orElse(null);
        String nombreContribuyente="";
        List<ContribucionFacturaDTO> CfacturaDTO=new ArrayList<>();
        List<Contribucionfactura> contribucionfactura=factura.getContribucionfacturaList();
        for(Contribucionfactura contribucionf:contribucionfactura){
                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getIdDescripcion().getDescripcion()+" "+contribucionf.getContribucionId().getConceptoContribucion(), contribucionf.getFacturaId().getFolio(),contribucionf.getCantidad()));
        }
        if(factura.getContribuyenteId().getContribuyenteFisica()!=null){
                nombreContribuyente=factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica()+" "+factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica()+" "+factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
              }
        else{
                nombreContribuyente=factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral();
            }
        String numero=" S/N ";
                if(factura.getContribuyenteId().getNumero()!=null)
                    numero=" #"+factura.getContribuyenteId().getNumero();
                String domicilio="Calle: "+factura.getContribuyenteId().getCalle()+numero+factura.getContribuyenteId().getColonia()+" C.P: "+factura.getContribuyenteId().getCp();
        FacturaDTO facturaDTO = new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre()+" "+factura.getUsuarioId().getIdEmpleado().getApellidoP()+" "+factura.getUsuarioId().getIdEmpleado().getApellidoM()
                , nombreContribuyente,domicilio,factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO,factura.getEstadoPago());
        return facturaDTO;
         
    }
    
    public FacturaDTO saveFactura(FacturaDTO facturaDTO){
        Factura factura= new Factura();
        String nombreContribuyente="";
        //factura.setFolio(facturaDTO.getFolio());
        Usuario usuario = usuarioDao.findById(facturaDTO.getUsuario_id()).orElse(null);
        factura.setUsuarioId(usuario);
        Contribuyente contribuyente=contribuyenteDao.findById(facturaDTO.getContribuyente_id()).orElse(null);
        factura.setContribuyenteId(contribuyente);
        factura.setFecha(facturaDTO.getFecha());
        factura.setDescuento(facturaDTO.getDescuento());
        factura.setTotal(facturaDTO.getTotal());
        factura.setEstadoPago(false);
        Factura facturaNew = facturaDao.save(factura);
        //Contribucionfactura contribucionfacturas= new Contribucionfactura();
        
        List<Contribucionfactura> contribucionfactura= new ArrayList<>();
        for(ContribucionFacturaDTO cfacturaDTO:facturaDTO.getItems()){
                Contribucionfactura contribucionfacturas= new Contribucionfactura();
                System.out.println("va aqui  "+facturaNew);
                contribucionfacturas.setFacturaId(facturaNew);
                
                contribucionfacturas.setContribucionId(contribucionDao.findById(cfacturaDTO.getIdContribucion()).orElse(null));
                contribucionfacturas.setCantidad(cfacturaDTO.getCantidad());
                Contribucionfactura contribucionFacturanew=contribucionFacturaDao.save(contribucionfacturas);
                contribucionfactura.add(contribucionFacturanew);
                System.out.println("esta es mi contribucion factura: "+contribucionDao.findById(cfacturaDTO.getIdContribucion()).orElse(null));
        }
        facturaNew.setContribucionfacturaList(contribucionfactura);
        
        List<ContribucionFacturaDTO> CfacturaDTO=new ArrayList<>();
        List<Contribucionfactura> contribucionfacturas=facturaNew.getContribucionfacturaList();
        for(Contribucionfactura contribucionf:contribucionfacturas){
                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(),contribucionf.getCantidad()));
        }
        
        if(factura.getContribuyenteId().getContribuyenteFisica()!=null){
             nombreContribuyente=factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica()+" "+factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica()+" "+factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
        }
        else{
             nombreContribuyente=factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral();
        }
        String numero=" S/N ";
                if(factura.getContribuyenteId().getNumero()!=null)
                    numero=" #"+factura.getContribuyenteId().getNumero();
                String domicilio="Calle: "+factura.getContribuyenteId().getCalle()+numero+factura.getContribuyenteId().getColonia()+" C.P: "+factura.getContribuyenteId().getCp();        
        FacturaDTO facturasDTO = new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getIdEmpleado().getNombre()+" "+factura.getUsuarioId().getIdEmpleado().getApellidoP()+" "+factura.getUsuarioId().getIdEmpleado().getApellidoM()
                , nombreContribuyente,domicilio,factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO,factura.getEstadoPago());
        return facturasDTO;
    }
    //metodo para actualizar el estado de pago de pendiente a pagado
    public FacturaDTO actualizarPagoFactura(int id){
        System.out.println("esta en actualizar ");
        Factura factura= facturaDao.findById(id).orElse(null);
        String nombreContribuyente="";
        factura.setFechaPagado(new Date());
        factura.setEstadoPago(true);
        Factura facturaNew=facturaDao.save(factura);
        List<ContribucionFacturaDTO> CfacturaDTO=new ArrayList<>();
        List<Contribucionfactura> contribucionfacturas=facturaNew.getContribucionfacturaList();
        for(Contribucionfactura contribucionf:contribucionfacturas){
                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(),contribucionf.getCantidad()));
        }
        
        if(factura.getContribuyenteId().getContribuyenteFisica()!=null){
             nombreContribuyente=factura.getContribuyenteId().getContribuyenteFisica().getNombreContribuyenteFisica()+" "+factura.getContribuyenteId().getContribuyenteFisica().getApellidoPContribuyenteFisica()+" "+factura.getContribuyenteId().getContribuyenteFisica().getApellidoMContribuyenteFisica();
        }
        else{
             nombreContribuyente=factura.getContribuyenteId().getContribuyenteMoral().getRazonSocialContribuyenteMoral();
        }
        String numero=" S/N ";
                if(factura.getContribuyenteId().getNumero()!=null)
                    numero=" #"+factura.getContribuyenteId().getNumero();
                String domicilio="Calle: "+factura.getContribuyenteId().getCalle()+numero+factura.getContribuyenteId().getColonia()+" C.P: "+factura.getContribuyenteId().getCp();        
        FacturaDTO facturasDTO = new FacturaDTO(facturaNew.getFolio(), facturaNew.getUsuarioId().getIdEmpleado().getNombre()+" "+factura.getUsuarioId().getIdEmpleado().getApellidoP()+" "+facturaNew.getUsuarioId().getIdEmpleado().getApellidoM()
                , nombreContribuyente,domicilio,facturaNew.getContribuyenteId().getRfcContribuyente(), facturaNew.getFecha(), facturaNew.getDescuento(), facturaNew.getTotal(), CfacturaDTO,facturaNew.getEstadoPago());
        return facturasDTO;
        
    }
    
    public void deleteFacturaById(int id){
        facturaDao.deleteById(id);
    }
}
