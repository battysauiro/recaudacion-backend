/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.ContribucionDTO;
import com.recaudacionMunicipio.DTO.ImpuestoDTO;
import com.recaudacionMunicipio.DTO.entidades.ContribucionCompletaDTO;
import com.recaudacionMunicipio.DTO.entidades.ContribucionesDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IaprovechamientoMultaDao;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IderechoGeneralDao;
import com.recaudacionMunicipio.dao.IderechosLicenciasDao;
import com.recaudacionMunicipio.dao.IimpuestoDao;
import com.recaudacionMunicipio.dao.ImultaEbriedadDao;
import com.recaudacionMunicipio.dao.ImultaVehicularDao;
import com.recaudacionMunicipio.dao.IotrosProductosDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.Aprovechamientomulta;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Derechogeneral;
import com.recaudacionMunicipio.modelo.Derechoslicencias;
import com.recaudacionMunicipio.modelo.Impuesto;
import com.recaudacionMunicipio.modelo.Multaebriedad;
import com.recaudacionMunicipio.modelo.Multavehicular;
import com.recaudacionMunicipio.modelo.Otrosproductos;
import com.recaudacionMunicipio.modelo.TipoPago;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public class ContribucionServicioImpl implements Servicios<ContribucionDTO>{

    
    
    @Autowired
    private IcontribucionDao contribucionDao;
    
    @Autowired 
    private IimpuestoDao impuestoDao;
    
    @Autowired
    private IderechosLicenciasDao derechosLicenciasDao;
    
    @Autowired
    private IderechoGeneralDao derechoGeneralDao;
    
    @Autowired
    private IaprovechamientoMultaDao aprovechamientoMultaDao;
    
    @Autowired
    private ImultaVehicularDao multaVehicularDao;
    
    @Autowired
    private ImultaEbriedadDao multaEbriedadDao;
            
    @Autowired
    private IotrosProductosDao otrosProductosDao;
    
    @Autowired 
    private ItipoPagoDao tipoPagoDao;
    
    @Autowired 
    private IcatalogoDescripcionDao catalogoDescripcionDao;
    @Override
    public ContribucionDTO findById(String id) {
        Contribucion contribucion=contribucionDao.findById(id).orElse(null);
        ContribucionDTO contribucionDTO= new ContribucionDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getIdTipoPago(),contribucion.getIdTipoPago().getNombrePago(), contribucion.getIdDescripcion().getIdDescripcion(),contribucion.getIdDescripcion().getDescripcion(),contribucion.getNivelContribucion(),obtenerTipoContribucion(contribucion.getNivelContribucion()));
        return contribucionDTO;
    }
    
    public entidadRespuesta<ContribucionDTO> findByTermino(int numeroDePagina, int MedidaDePagina,String term){
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina); 
        Page<Contribucion> contribucionesP=contribucionDao.findByCodigoContribucionStartingWithIgnoreCaseOrConceptoContribucionContainingIgnoreCase(pageable,term, term);
        List<Contribucion> listaContribuciones =contribucionesP.getContent();
        List<ContribucionDTO> lista= new ArrayList<>();
        for(Contribucion contribucion:listaContribuciones){
            lista.add(new ContribucionDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getIdTipoPago(),contribucion.getIdTipoPago().getNombrePago(), contribucion.getIdDescripcion().getIdDescripcion(),contribucion.getIdDescripcion().getDescripcion(),contribucion.getNivelContribucion(),obtenerTipoContribucion(contribucion.getNivelContribucion())));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(contribucionesP.getNumber());
        entidadrespuesta.setMedidaPagina(contribucionesP.getSize());
        entidadrespuesta.setTotalElementos(contribucionesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribucionesP.getTotalPages());
        entidadrespuesta.setUltima(contribucionesP.isLast());
        entidadrespuesta.setPrimera(contribucionesP.isFirst());
        
        return entidadrespuesta; 
    }
    
    public entidadRespuesta<ImpuestoDTO> findByTerminoImpuestos(int numeroDePagina, int MedidaDePagina,String term){
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);  
        Page<Contribucion> contribucionesP=null;//contribucionDao.findByCodigoContribucionStartingWithIgnoreCaseOrConceptoContribucionContainingIgnoreCaseAndNivelContribucionIs(pageable,term,term,1);
        List<Contribucion> listaContribuciones =contribucionesP.getContent(); 
        List<ImpuestoDTO> lista= new ArrayList<>();  
        for(Contribucion contribucion:listaContribuciones){
            lista.add(new ImpuestoDTO(contribucion.getImpuesto().getIdContribucionImpuesto(), contribucion.getImpuesto().getIdTipoImpuesto().getIdTipoImpuesto(), contribucion.getImpuesto().getIdTipoImpuesto().getDescripcion(), contribucion.getImpuesto().getCantidad(), contribucion.getImpuesto().getContribucion().getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getIdTipoPago(), contribucion.getIdTipoPago().getNombrePago(), contribucion.getIdDescripcion().getIdDescripcion(),
                contribucion.getIdDescripcion().getDescripcion(),contribucion.getNivelContribucion(),
                obtenerTipoContribucion(contribucion.getNivelContribucion())));
        }//contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getIdTipoPago(),contribucion.getIdTipoPago().getNombrePago(), contribucion.getIdDescripcion().getIdDescripcion(),contribucion.getIdDescripcion().getDescripcion(),contribucion.getNivelContribucion(),obtenerTipoContribucion(contribucion.getNivelContribucion()
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(contribucionesP.getNumber());
        entidadrespuesta.setMedidaPagina(contribucionesP.getSize());
        entidadrespuesta.setTotalElementos(contribucionesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribucionesP.getTotalPages());
        entidadrespuesta.setUltima(contribucionesP.isLast());
        entidadrespuesta.setPrimera(contribucionesP.isFirst());
        
        return entidadrespuesta; 
    }
    
    public ContribucionCompletaDTO findByIdCompleto(String id) {
        Contribucion contribucion=contribucionDao.findById(id).orElse(null);
        ContribucionCompletaDTO contribucionCompletaDTO = new ContribucionCompletaDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getNombrePago(),contribucion.getIdDescripcion().getDescripcion());
        return contribucionCompletaDTO;
    }

    @Override
    public ContribucionDTO save(ContribucionDTO contribucionDTO) {
        Contribucion contribucion= mapearEntidad(contribucionDTO);
        Contribucion newContribucion=contribucionDao.save(contribucion);
        ContribucionDTO contribucionRespuesta= mapearDTO(newContribucion);
  
        return contribucionRespuesta;
    }

    @Override
    public entidadRespuesta<ContribucionDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Contribucion> contribucionesP =contribucionDao.findAll(pageable);
        List<Contribucion> listaContribuciones =contribucionesP.getContent();
        List<ContribucionDTO> lista= new ArrayList<>();
        for(Contribucion contribucion:listaContribuciones){
            lista.add(new ContribucionDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getIdTipoPago(),contribucion.getIdTipoPago().getNombrePago(), contribucion.getIdDescripcion().getIdDescripcion(),contribucion.getIdDescripcion().getDescripcion(),contribucion.getNivelContribucion(),obtenerTipoContribucion(contribucion.getNivelContribucion())));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(contribucionesP.getNumber());
        entidadrespuesta.setMedidaPagina(contribucionesP.getSize());
        entidadrespuesta.setTotalElementos(contribucionesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribucionesP.getTotalPages());
        entidadrespuesta.setUltima(contribucionesP.isLast());
        entidadrespuesta.setPrimera(contribucionesP.isFirst());
        
        return entidadrespuesta;
        //return lista;
        //return listaContribuciones.stream().map(contribucion -> mapearDTO(contribucion)).collect(Collectors.toList());
    }
    //regresa el nombre del tipo de contribucion
    public String obtenerTipoContribucion(int number){
        if(number==1)
            return "impuestos" ;
        if(number==2)
            return "Derechos Generales";
        if(number==3)
            return "Derechos Licencias";
        if(number==4)
            return "Multas";
        if(number==5)
            return "Multa Ebriedad";
        if(number==6)
            return "Multa Vehicular";
        else
            return "Otros Productos";
    }
    
    public entidadRespuesta<ContribucionCompletaDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Contribucion> contribucionesP =contribucionDao.findAll(pageable);
        List<Contribucion> listaContribuciones =contribucionesP.getContent();
        List<ContribucionCompletaDTO> lista= new ArrayList<>();
        for(Contribucion contribucion:listaContribuciones){
            lista.add(new ContribucionCompletaDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getNombrePago(),contribucion.getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(contribucionesP.getNumber());
        entidadrespuesta.setMedidaPagina(contribucionesP.getSize());
        entidadrespuesta.setTotalElementos(contribucionesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribucionesP.getTotalPages());
        entidadrespuesta.setUltima(contribucionesP.isLast());
        entidadrespuesta.setPrimera(contribucionesP.isFirst());
        
        return entidadrespuesta;
        //return lista;
        //return listaContribuciones.stream().map(contribucion -> mapearDTO(contribucion)).collect(Collectors.toList());
    }
    //regresa un arreglo con toda la informacion de una contribucion 
    public entidadRespuesta<ContribucionesDTO> findAllContribuciones(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Contribucion> contribucionesP =contribucionDao.findAll(pageable);
        List<Impuesto> listaContribucionesImpuesto =impuestoDao.findAll();
        List<Derechogeneral> listaContribucionesDerechoG =derechoGeneralDao.findAll();
        List<Derechoslicencias> listaContribucionesDerechosLicencias =derechosLicenciasDao.findAll();
        List<Aprovechamientomulta> listaContribucionesAprovechamientoMultas =aprovechamientoMultaDao.findAll();
        List<Multavehicular> listaContribucionesMultaVehicular =multaVehicularDao.findAll();
        List<Multaebriedad> listaContribucionesMultaEbriedad =multaEbriedadDao.findAll();
        List<Otrosproductos> listaContribucionesOtrosProductos =otrosProductosDao.findAll();
        List<Contribucion> listaContribuciones =contribucionDao.findAll();

        List<ContribucionesDTO> lista= new ArrayList<>();
        List<List<ContribucionesDTO>> listas= new ArrayList<>();
        
        for(int i=0;i<listaContribucionesImpuesto.size(); i++){
            listaContribucionesImpuesto.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesImpuesto.get(i).getIdContribucionImpuesto(), "IMPUESTOS", listaContribucionesImpuesto.get(i).getContribucion().getCodigoContribucion(), listaContribucionesImpuesto.get(i).getContribucion().getConceptoContribucion(), listaContribucionesImpuesto.get(i).getIdTipoImpuesto().getDescripcion(), listaContribucionesImpuesto.get(i).getContribucion().getIdDescripcion().getDescripcion()));
        }
        
        for(int i=0;i<listaContribucionesDerechoG.size(); i++){
            listaContribucionesDerechoG.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesDerechoG.get(i).getIdContribucionDerechosLicencias(), "DERECHOS", listaContribucionesDerechoG.get(i).getDerechos().getContribucion().getCodigoContribucion(), listaContribucionesDerechoG.get(i).getDerechos().getContribucion().getConceptoContribucion(), listaContribucionesDerechoG.get(i).getDerechos().getIdTipoDerecho().getDescripcion(), listaContribucionesDerechoG.get(i).getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
           
        for(int i=0;i<listaContribucionesDerechosLicencias.size(); i++){
            listaContribucionesDerechosLicencias.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesDerechosLicencias.get(i).getIdContribucionDerechosLicencias(), "DERECHOS", listaContribucionesDerechosLicencias.get(i).getDerechos().getContribucion().getCodigoContribucion(), listaContribucionesDerechosLicencias.get(i).getDerechos().getContribucion().getConceptoContribucion(), listaContribucionesDerechosLicencias.get(i).getDerechos().getIdTipoDerecho().getDescripcion(), listaContribucionesDerechosLicencias.get(i).getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        
        for(int i=0;i<listaContribucionesAprovechamientoMultas.size(); i++){
            listaContribucionesAprovechamientoMultas.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesAprovechamientoMultas.get(i).getIdContribucionMulta(), "APROVECHAMIENTO", listaContribucionesAprovechamientoMultas.get(i).getAprovechamiento().getContribucion().getCodigoContribucion(), listaContribucionesAprovechamientoMultas.get(i).getAprovechamiento().getContribucion().getConceptoContribucion(), listaContribucionesAprovechamientoMultas.get(i).getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), listaContribucionesAprovechamientoMultas.get(i).getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        
        for(int i=0;i<listaContribucionesMultaVehicular.size(); i++){
            listaContribucionesMultaVehicular.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesMultaVehicular.get(i).getIdContribucionMultaVehicular(), "APROVECHAMIENTO", listaContribucionesMultaVehicular.get(i).getAprovechamiento().getContribucion().getCodigoContribucion(), listaContribucionesMultaVehicular.get(i).getAprovechamiento().getContribucion().getConceptoContribucion(), listaContribucionesMultaVehicular.get(i).getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), listaContribucionesMultaVehicular.get(i).getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        
        for(int i=0;i<listaContribucionesMultaEbriedad.size(); i++){
            listaContribucionesMultaEbriedad.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesMultaEbriedad.get(i).getIdContribucionMultaEbriedad(), "APROVECHAMIENTO", listaContribucionesMultaEbriedad.get(i).getAprovechamiento().getContribucion().getCodigoContribucion(), listaContribucionesMultaEbriedad.get(i).getAprovechamiento().getContribucion().getConceptoContribucion(), listaContribucionesMultaEbriedad.get(i).getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), listaContribucionesMultaEbriedad.get(i).getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        
        for(int i=0;i<listaContribucionesOtrosProductos.size(); i++){
            listaContribucionesOtrosProductos.get(i);
            lista.add(new ContribucionesDTO(listaContribucionesOtrosProductos.get(i).getIdContribucionProductos(), "OTROS PRODUCTOS", listaContribucionesOtrosProductos.get(i).getContribucion().getCodigoContribucion(), listaContribucionesOtrosProductos.get(i).getContribucion().getConceptoContribucion(), listaContribucionesOtrosProductos.get(i).getIdOtrosProductos().getDescripcion(), listaContribucionesOtrosProductos.get(i).getContribucion().getIdDescripcion().getDescripcion()));
        }
        
        /**
        listaContribucionesImpuesto.forEach(impuesto -> {
            lista.add(new ContribucionesDTO(impuesto.getIdContribucionImpuesto(), "IMPUESTOS", impuesto.getContribucion().getCodigoContribucion(), impuesto.getContribucion().getConceptoContribucion(), impuesto.getIdTipoImpuesto().getDescripcion(), impuesto.getContribucion().getIdDescripcion().getDescripcion()));
        });
        
        listaContribucionesDerechoG.forEach(derechoG -> {
            lista.add(new ContribucionesDTO(derechoG.getIdcontribucionderechosLicencias(), "DERECHOS", derechoG.getDerechos().getContribucion().getCodigoContribucion(), derechoG.getDerechos().getContribucion().getConceptoContribucion(), derechoG.getDerechos().getIdTipoDerecho().getDescripcion(), derechoG.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        });
        
        listaContribucionesDerechosLicencias.forEach(derechosL -> {
            lista.add(new ContribucionesDTO(derechosL.getIdcontribucionderechosLicencias(), "DERECHOS", derechosL.getDerechos().getContribucion().getCodigoContribucion(), derechosL.getDerechos().getContribucion().getConceptoContribucion(), derechosL.getDerechos().getIdTipoDerecho().getDescripcion(), derechosL.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        });
        
        listaContribucionesAprovechamientoMultas.forEach(aprovechamientoM -> {
            lista.add(new ContribucionesDTO(aprovechamientoM.getIdContribucionMulta(), "APROVECHAMIENTO", aprovechamientoM.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoM.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoM.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoM.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        });
        
        listaContribucionesMultaVehicular.forEach(multaV -> {
            lista.add(new ContribucionesDTO(multaV.getIdcontribucionmultaVehicular(), "APROVECHAMIENTO", multaV.getAprovechamiento().getContribucion().getCodigoContribucion(), multaV.getAprovechamiento().getContribucion().getConceptoContribucion(), multaV.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), multaV.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        });
        
        listaContribucionesMultaEbriedad.forEach(multaE -> {
            lista.add(new ContribucionesDTO(multaE.getIdcontribucionmultaEbriedad(), "APROVECHAMIENTO", multaE.getAprovechamiento().getContribucion().getCodigoContribucion(), multaE.getAprovechamiento().getContribucion().getConceptoContribucion(), multaE.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), multaE.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        });
        
        listaContribucionesOtrosProductos.forEach(multaOtros -> {
            lista.add(new ContribucionesDTO(multaOtros.getIdContribucionProductos(), "OTROS PRODUCTOS", multaOtros.getContribucion().getCodigoContribucion(), multaOtros.getContribucion().getConceptoContribucion(), multaOtros.getIdotrosProductos().getDescripcion(), multaOtros.getContribucion().getIdDescripcion().getDescripcion()));
        });
        * */
        
        int parte=(listaContribuciones.size())/MedidaDePagina;
        int inicio=0;
        int medida=0;    
          
        for(int i=0; i<=parte;i++){
            inicio=i*MedidaDePagina;
            medida=(i+1)*MedidaDePagina;
            //System.out.println("indice "+i+"inicio ="+inicio+ " medida= "+medida+"tamanio "+listaContribuciones.size());
            if(i==(listaContribuciones.size())/MedidaDePagina){
                listas.add(lista.subList(inicio, listaContribuciones.size()));  
            }
            else{
                listas.add(lista.subList(inicio, medida));  
            }
            
        } 
        
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(listas.get(numeroDePagina));  
        entidadrespuesta.setNumeroPagina(contribucionesP.getNumber());
        entidadrespuesta.setMedidaPagina(contribucionesP.getSize());
        entidadrespuesta.setTotalElementos(contribucionesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribucionesP.getTotalPages());
        entidadrespuesta.setUltima(contribucionesP.isLast());
        entidadrespuesta.setPrimera(contribucionesP.isFirst());
        
        return entidadrespuesta;
        //return lista;
        //return listaContribuciones.stream().map(contribucion -> mapearDTO(contribucion)).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Contribucion contribucion=contribucionDao 
                .findById(id).orElse(null);
        
        contribucionDao.delete(contribucion);
    }

    @Override
    public ContribucionDTO update(ContribucionDTO contribucionDTO, String id) {
        Contribucion contribucion=contribucionDao 
                .findById(id).orElse(null);

        contribucion.setCodigoContribucion(contribucionDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(contribucionDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(contribucionDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(contribucionDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        
        Contribucion contribucionActualizado=contribucionDao.save(contribucion);
        return mapearDTO(contribucionActualizado);
    }
    
    private ContribucionDTO mapearDTO(Contribucion contribucion){
        ContribucionDTO contribucionDTO = new ContribucionDTO();
        contribucionDTO.setCodigo_contribucion(contribucion.getCodigoContribucion());
        contribucionDTO.setConcepto_contribucion(contribucion.getConceptoContribucion());
        contribucionDTO.setId_tipo_pago(contribucion.getIdTipoPago().getIdTipoPago());
        contribucionDTO.setId_descripcion(contribucion.getIdDescripcion().getIdDescripcion());
        return  contribucionDTO;
    }
    
    private Contribucion mapearEntidad(ContribucionDTO contribucionDTO){
        Contribucion contribucion = new Contribucion();
        contribucion.setCodigoContribucion(contribucionDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(contribucionDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(contribucionDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(contribucionDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        
        return contribucion;
    }

    @Override
    public Page<ContribucionDTO> findAll(Pageable pageable) {
        Page<Contribucion> listaContribuciones =contribucionDao.findAll(pageable);
        List<ContribucionDTO> lista= new ArrayList<>();
        for(Contribucion contribucion:listaContribuciones){
            lista.add(new ContribucionDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getIdTipoPago(),contribucion.getIdTipoPago().getNombrePago(), contribucion.getIdDescripcion().getIdDescripcion(),contribucion.getIdDescripcion().getDescripcion(),contribucion.getNivelContribucion(),obtenerTipoContribucion(contribucion.getNivelContribucion())));
}
        return (Page<ContribucionDTO>) lista;
    }
    
    public Page<ContribucionCompletaDTO> findAllC(Pageable pageable) {
        Page<Contribucion> listaContribuciones =contribucionDao.findAll(pageable);
        List<ContribucionCompletaDTO> lista= new ArrayList<>();
        for(Contribucion contribucion:listaContribuciones){
            lista.add(new ContribucionCompletaDTO(contribucion.getCodigoContribucion(), contribucion.getConceptoContribucion(), contribucion.getIdTipoPago().getNombrePago(),contribucion.getIdDescripcion().getDescripcion()));
        }
        return (Page<ContribucionCompletaDTO>) lista;
        //return listaContribuciones.stream().map(contribucion -> mapearDTO(contribucion)).collect(Collectors.toList());
    }
}
    
    
