/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.DerechosGeneralDTO;
import com.recaudacionMunicipio.DTO.entidades.DerechosGeneralCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDerechoDao;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IderechoGeneralDao;
import com.recaudacionMunicipio.dao.IderechosDao;
import com.recaudacionMunicipio.dao.IperiodicidadDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.Aprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoAprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoDerecho;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Derechogeneral;
import com.recaudacionMunicipio.modelo.Derechos;
import com.recaudacionMunicipio.modelo.Periodicidad;
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
public class DerechoGeneralServicioImpl implements Servicios<DerechosGeneralDTO>{

    @Autowired
    private IcontribucionDao contribucionDao;
    
    @Autowired
    private ItipoPagoDao tipoPagoDao;
    
    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;
    
    @Autowired
    private IderechoGeneralDao derechoGeneralDAO;
    
    @Autowired
    private IperiodicidadDao periodicidadDAO;
    
    @Autowired
    private IcatalogoDerechoDao catalogoDao;
    
    @Autowired
    private IderechosDao derechosDAO;
    
    @Override
    public DerechosGeneralDTO findById(String id) {
        Derechogeneral derechoGeneral=derechoGeneralDAO.findById(id).orElse(null);
        DerechosGeneralDTO derechoGeneralDTO = new DerechosGeneralDTO(derechoGeneral.getIdContribucionDerechosLicencias(), derechoGeneral.getCantidad(), derechoGeneral.getIdPeriodicidad().getIdPeriodicidad(),derechoGeneral.getIdPeriodicidad().getNombrePeriodicidad(), derechoGeneral.getDerechos().getIdContribucionDerechos(), derechoGeneral.getDerechos().getIdTipoDerecho().getIdTipoDerecho(),derechoGeneral.getDerechos().getIdTipoDerecho().getDescripcion(), derechoGeneral.getDerechos().getContribucion().getCodigoContribucion(), derechoGeneral.getDerechos().getContribucion().getConceptoContribucion(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getIdTipoPago(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getIdDescripcion(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getDescripcion());
        return derechoGeneralDTO;
    }
    
    public DerechosGeneralCompletoDTO findByIdCompleto(String id) {
        Derechogeneral derechoGeneral=derechoGeneralDAO.findById(id).orElse(null);
        DerechosGeneralCompletoDTO derechoGeneralCompletoDTO = new DerechosGeneralCompletoDTO(derechoGeneral.getCantidad(), derechoGeneral.getIdPeriodicidad().getNombrePeriodicidad(), derechoGeneral.getIdContribucionDerechosLicencias(), derechoGeneral.getDerechos().getIdTipoDerecho().getDescripcion(), derechoGeneral.getDerechos().getContribucion().getCodigoContribucion(), derechoGeneral.getDerechos().getContribucion().getConceptoContribucion(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getDescripcion());
        return derechoGeneralCompletoDTO;
    }

    @Override
    public DerechosGeneralDTO save(DerechosGeneralDTO derechosGeneralDTO) {
        
        Contribucion contribucion= new Contribucion();
        contribucion.setCodigoContribucion(derechosGeneralDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(derechosGeneralDTO.getConcepto_contribucion());
        TipoPago tipoPago=tipoPagoDao.findById(derechosGeneralDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion= catalogoDescripcionDao.findById(derechosGeneralDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Derechos derecho= new Derechos();
        derecho.setIdContribucionDerechos(derechosGeneralDTO.getCodigo_contribucion());
        CatalogoDerecho catalogo= catalogoDao.findById(derechosGeneralDTO.getCatalogo_derechos()).orElse(null);
        derecho.setIdTipoDerecho(catalogo);
        derecho.setContribucion(contribucion);
        derechosDAO.save(derecho);
        
        Derechogeneral derechoGeneral= mapearEntidad(derechosGeneralDTO);
        derechoGeneral.setDerechos(derecho);
        Derechogeneral newDerechoGeneral=derechoGeneralDAO.save(derechoGeneral);
        DerechosGeneralDTO derechoGeneralRespuesta= mapearDTO(newDerechoGeneral);
  
        return derechoGeneralRespuesta;
    }
    
    public Object crear(DerechosGeneralDTO derechosGeneralDTO) {
        System.out.println(derechoGeneralDAO.existsById(derechosGeneralDTO.getCodigo_contribucion())+"existeeeeeeeeeeeeee ");
        if((derechoGeneralDAO.existsById(derechosGeneralDTO.getCodigo_contribucion())))
            return 0;// significa que usuario ya existe
        if((!derechoGeneralDAO.existsById(derechosGeneralDTO.getCodigo_contribucion()))){
            Derechogeneral aprovechamientoDerechoGeneral= mapearEntidad(derechosGeneralDTO);
        
            Derechogeneral aprovechamientoDerechoGeneralNueva=derechoGeneralDAO.save(aprovechamientoDerechoGeneral);
            DerechosGeneralDTO aprovechamientoRespuesta= mapearDTO(aprovechamientoDerechoGeneralNueva);
            return aprovechamientoRespuesta;
        }
        return null;
      
    }

    @Override
    public entidadRespuesta<DerechosGeneralDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Derechogeneral> derechoGeneralP =derechoGeneralDAO.findAll(pageable);
        List<Derechogeneral> listaDerechoGeneral =derechoGeneralP.getContent();
        List<DerechosGeneralDTO> lista= new ArrayList<>();
        for(Derechogeneral derechoGeneral:listaDerechoGeneral){
            lista.add(new DerechosGeneralDTO(derechoGeneral.getIdContribucionDerechosLicencias(), derechoGeneral.getCantidad(), derechoGeneral.getIdPeriodicidad().getIdPeriodicidad(),derechoGeneral.getIdPeriodicidad().getNombrePeriodicidad(), derechoGeneral.getDerechos().getIdContribucionDerechos(), derechoGeneral.getDerechos().getIdTipoDerecho().getIdTipoDerecho(),derechoGeneral.getDerechos().getIdTipoDerecho().getDescripcion(), derechoGeneral.getDerechos().getContribucion().getCodigoContribucion(), derechoGeneral.getDerechos().getContribucion().getConceptoContribucion(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getIdTipoPago(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getIdDescripcion(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(derechoGeneralP.getNumber());
        entidadrespuesta.setMedidaPagina(derechoGeneralP.getSize());
        entidadrespuesta.setTotalElementos(derechoGeneralP.getTotalElements());
        entidadrespuesta.setTotalPaginas(derechoGeneralP.getTotalPages());
        entidadrespuesta.setUltima(derechoGeneralP.isLast());
        entidadrespuesta.setPrimera(derechoGeneralP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<DerechosGeneralCompletoDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Derechogeneral> derechoGeneralP =derechoGeneralDAO.findAll(pageable);
        List<Derechogeneral> listaDerechoGeneral =derechoGeneralP.getContent();
        List<DerechosGeneralCompletoDTO> lista= new ArrayList<>();
        for(Derechogeneral derechoGeneral:listaDerechoGeneral){
            lista.add(new DerechosGeneralCompletoDTO(derechoGeneral.getCantidad(), derechoGeneral.getIdPeriodicidad().getNombrePeriodicidad(), derechoGeneral.getIdContribucionDerechosLicencias(), derechoGeneral.getDerechos().getIdTipoDerecho().getDescripcion(), derechoGeneral.getDerechos().getContribucion().getCodigoContribucion(), derechoGeneral.getDerechos().getContribucion().getConceptoContribucion(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(derechoGeneralP.getNumber());
        entidadrespuesta.setMedidaPagina(derechoGeneralP.getSize());
        entidadrespuesta.setTotalElementos(derechoGeneralP.getTotalElements());
        entidadrespuesta.setTotalPaginas(derechoGeneralP.getTotalPages());
        entidadrespuesta.setUltima(derechoGeneralP.isLast());
        entidadrespuesta.setPrimera(derechoGeneralP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }
    
    @Override
    public void delete(String id) {
        Derechogeneral derechosGeneral=derechoGeneralDAO 
                .findById(id).orElse(null);
        
        derechoGeneralDAO.delete(derechosGeneral);
    }

    @Override
    public DerechosGeneralDTO update(DerechosGeneralDTO derechosGeneralDTO, String id) {
        
        Contribucion contribucion = contribucionDao.findById(id).orElse(null);
        contribucion.setConceptoContribucion(derechosGeneralDTO.getConcepto_contribucion());
        TipoPago tipoPago=tipoPagoDao.findById(derechosGeneralDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion= catalogoDescripcionDao.findById(derechosGeneralDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Derechos derecho= derechosDAO.findById(id).orElse(null);
        CatalogoDerecho catalogo= catalogoDao.findById(derechosGeneralDTO.getCatalogo_derechos()).orElse(null);
        derecho.setIdTipoDerecho(catalogo);
        derechosDAO.save(derecho);
        
        Derechogeneral derechos=derechoGeneralDAO 
                .findById(id).orElse(null);

        //derechos.setIdcontribucionderechosLicencias(derechosGeneralDTO.getId_derecho_general());
        derechos.setCantidad(derechosGeneralDTO.getCantidad());
        Periodicidad periodicidad = periodicidadDAO.findById(derechosGeneralDTO.getId_periodicidad()).orElse(null);
        derechos.setIdPeriodicidad(periodicidad);
        //Derechos derechosC = derechosDAO.findById(derechosGeneralDTO.getId_derecho_general()).orElse(null);
        //derechos.setDerechos(derechosC);
        
        Derechogeneral derechoGeneralActualizado=derechoGeneralDAO.save(derechos);
        
        return mapearDTO(derechoGeneralActualizado);
    }
    
    private DerechosGeneralDTO mapearDTO(Derechogeneral derechos){
        DerechosGeneralDTO derechosGeneralDTO = new DerechosGeneralDTO();
        derechosGeneralDTO.setCodigo_contribucion(derechos.getDerechos().getContribucion().getCodigoContribucion());
        derechosGeneralDTO.setId_derecho_general(derechos.getIdContribucionDerechosLicencias());
        derechosGeneralDTO.setCantidad(derechos.getCantidad());
        derechosGeneralDTO.setId_periodicidad(derechos.getIdPeriodicidad().getIdPeriodicidad());
        
        return  derechosGeneralDTO;
    }
    
    private Derechogeneral mapearEntidad(DerechosGeneralDTO derechosGeneralDTO){
        Derechogeneral derechos = new Derechogeneral();
       
        derechos.setIdContribucionDerechosLicencias(derechosGeneralDTO.getCodigo_contribucion());
        derechos.setCantidad(derechosGeneralDTO.getCantidad());
        Periodicidad periodicidad = periodicidadDAO.findById(derechosGeneralDTO.getId_periodicidad()).orElse(null);
        derechos.setIdPeriodicidad(periodicidad);
        //Derechos derechosC = derechosDAO.findById(derechosGeneralDTO.getId_derecho_general()).orElse(null);
        //derechos.setDerechos(derechosC);

        return derechos;
    }

    @Override
    public Page<DerechosGeneralDTO> findAll(Pageable pageable) {
        Page<Derechogeneral> listaDerechoGeneral =derechoGeneralDAO.findAll(pageable);
        List<DerechosGeneralDTO> lista= new ArrayList<>();
        for(Derechogeneral derechoGeneral:listaDerechoGeneral){
            lista.add(new DerechosGeneralDTO(derechoGeneral.getIdContribucionDerechosLicencias(), derechoGeneral.getCantidad(), derechoGeneral.getIdPeriodicidad().getIdPeriodicidad(),derechoGeneral.getIdPeriodicidad().getNombrePeriodicidad(), derechoGeneral.getDerechos().getIdContribucionDerechos(), derechoGeneral.getDerechos().getIdTipoDerecho().getIdTipoDerecho(),derechoGeneral.getDerechos().getIdTipoDerecho().getDescripcion(), derechoGeneral.getDerechos().getContribucion().getCodigoContribucion(), derechoGeneral.getDerechos().getContribucion().getConceptoContribucion(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getIdTipoPago(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getIdDescripcion(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
}
        return (Page<DerechosGeneralDTO>) lista;
    }
    
    public Page<DerechosGeneralCompletoDTO> findAllC(Pageable pageable) {
        Page<Derechogeneral> listaDerechoGeneral =derechoGeneralDAO.findAll(pageable);
        List<DerechosGeneralCompletoDTO> lista= new ArrayList<>();
        for(Derechogeneral derechoGeneral:listaDerechoGeneral){
            lista.add(new DerechosGeneralCompletoDTO(derechoGeneral.getCantidad(), derechoGeneral.getIdPeriodicidad().getNombrePeriodicidad(), derechoGeneral.getIdContribucionDerechosLicencias(), derechoGeneral.getDerechos().getIdTipoDerecho().getDescripcion(), derechoGeneral.getDerechos().getContribucion().getCodigoContribucion(), derechoGeneral.getDerechos().getContribucion().getConceptoContribucion(), derechoGeneral.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechoGeneral.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<DerechosGeneralCompletoDTO>) lista;
    }
}
