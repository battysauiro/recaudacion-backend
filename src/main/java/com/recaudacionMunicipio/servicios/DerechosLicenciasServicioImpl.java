/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.DerechosLicenciaDTO;
import com.recaudacionMunicipio.DTO.entidades.DerechosLicenciaCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDerechoDao;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IderechosDao;
import com.recaudacionMunicipio.dao.IderechosLicenciasDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.CatalogoDerecho;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Derechos;
import com.recaudacionMunicipio.modelo.Derechoslicencias;
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
public class DerechosLicenciasServicioImpl implements Servicios<DerechosLicenciaDTO>{

    @Autowired
    private IderechosDao derechosDAO;
    
    @Autowired
    private IcatalogoDerechoDao catalogoDerechosDAO;
    
    @Autowired
    private ItipoPagoDao tipoPagoDao;
    
    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;
    
    @Autowired
    private IcontribucionDao contribucionDAO;
    
    @Autowired
    private IderechosLicenciasDao derechosLicenciasDAO;
    
    
    @Override
    public DerechosLicenciaDTO findById(String id) {
        Derechoslicencias derechosLicencias=derechosLicenciasDAO.findById(id).orElse(null);
        DerechosLicenciaDTO derechosLicenciasDTO = new DerechosLicenciaDTO(derechosLicencias.getIdContribucionDerechosLicencias(), derechosLicencias.getExpedicion(), derechosLicencias.getRefrendo(), derechosLicencias.getDerechos().getIdContribucionDerechos(), derechosLicencias.getDerechos().getIdTipoDerecho().getIdTipoDerecho(),derechosLicencias.getDerechos().getIdTipoDerecho().getDescripcion(), derechosLicencias.getDerechos().getContribucion().getCodigoContribucion(), derechosLicencias.getDerechos().getContribucion().getConceptoContribucion(), derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getIdTipoPago(),derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getIdDescripcion(),derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getDescripcion());
        return derechosLicenciasDTO;
    }

    public DerechosLicenciaCompletoDTO findByIdCompleto(String id) {
        Derechoslicencias derechosLicencias=derechosLicenciasDAO.findById(id).orElse(null);
        DerechosLicenciaCompletoDTO derechosLicenciasCompletoDTO = new DerechosLicenciaCompletoDTO(derechosLicencias.getExpedicion(), derechosLicencias.getRefrendo(), derechosLicencias.getIdContribucionDerechosLicencias(), derechosLicencias.getDerechos().getIdTipoDerecho().getDescripcion(), derechosLicencias.getDerechos().getContribucion().getCodigoContribucion(), derechosLicencias.getDerechos().getContribucion().getConceptoContribucion(), derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getDescripcion());
        return derechosLicenciasCompletoDTO;
    }
    
    @Override
    public DerechosLicenciaDTO save(DerechosLicenciaDTO derechosLicenciaDTO) {
        
        Contribucion contribucion= new Contribucion();
        contribucion.setCodigoContribucion(derechosLicenciaDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(derechosLicenciaDTO.getConcepto_contribucion());
        TipoPago tipoPago=tipoPagoDao.findById(derechosLicenciaDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion= catalogoDescripcionDao.findById(derechosLicenciaDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDAO.save(contribucion);
        Derechos derecho= new Derechos();
        derecho.setIdContribucionDerechos(derechosLicenciaDTO.getCodigo_contribucion());
        CatalogoDerecho catalogo= catalogoDerechosDAO.findById(derechosLicenciaDTO.getCatalogo_derechos()).orElse(null);
        derecho.setIdTipoDerecho(catalogo);
        derecho.setContribucion(contribucion);
        derechosDAO.save(derecho);
        
        Derechoslicencias derechoLicencias= mapearEntidad(derechosLicenciaDTO);
        derechoLicencias.setDerechos(derecho);
        Derechoslicencias newDerechoLicencias=derechosLicenciasDAO.save(derechoLicencias);
        DerechosLicenciaDTO derechoLicenciaRespuesta= mapearDTO(newDerechoLicencias);
  
        return derechoLicenciaRespuesta;
    }

    @Override
    public entidadRespuesta<DerechosLicenciaDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Derechoslicencias> derechosLicenciasP =derechosLicenciasDAO.findAll(pageable);
        List<Derechoslicencias> listaDerechosLicencias =derechosLicenciasP.getContent();
        List<DerechosLicenciaDTO> lista= new ArrayList<>();
        for(Derechoslicencias derechosLicencias:listaDerechosLicencias){
            lista.add(new DerechosLicenciaDTO(derechosLicencias.getIdContribucionDerechosLicencias(), derechosLicencias.getExpedicion(), derechosLicencias.getRefrendo(), derechosLicencias.getDerechos().getIdContribucionDerechos(), derechosLicencias.getDerechos().getIdTipoDerecho().getIdTipoDerecho(),derechosLicencias.getDerechos().getIdTipoDerecho().getDescripcion(), derechosLicencias.getDerechos().getContribucion().getCodigoContribucion(), derechosLicencias.getDerechos().getContribucion().getConceptoContribucion(), derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getIdTipoPago(),derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getIdDescripcion(),derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(derechosLicenciasP.getNumber());
        entidadrespuesta.setMedidaPagina(derechosLicenciasP.getSize());
        entidadrespuesta.setTotalElementos(derechosLicenciasP.getTotalElements());
        entidadrespuesta.setTotalPaginas(derechosLicenciasP.getTotalPages());
        entidadrespuesta.setUltima(derechosLicenciasP.isLast());
        entidadrespuesta.setPrimera(derechosLicenciasP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<DerechosLicenciaCompletoDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Derechoslicencias> derechosLicenciasP =derechosLicenciasDAO.findAll(pageable);
        List<Derechoslicencias> listaDerechosLicencias =derechosLicenciasP.getContent();
        List<DerechosLicenciaCompletoDTO> lista= new ArrayList<>();
        for(Derechoslicencias derechosLicencias:listaDerechosLicencias){
            lista.add(new DerechosLicenciaCompletoDTO(derechosLicencias.getExpedicion(), derechosLicencias.getRefrendo(), derechosLicencias.getIdContribucionDerechosLicencias(), derechosLicencias.getDerechos().getIdTipoDerecho().getDescripcion(), derechosLicencias.getDerechos().getContribucion().getCodigoContribucion(), derechosLicencias.getDerechos().getContribucion().getConceptoContribucion(), derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(derechosLicenciasP.getNumber());
        entidadrespuesta.setMedidaPagina(derechosLicenciasP.getSize());
        entidadrespuesta.setTotalElementos(derechosLicenciasP.getTotalElements());
        entidadrespuesta.setTotalPaginas(derechosLicenciasP.getTotalPages());
        entidadrespuesta.setUltima(derechosLicenciasP.isLast());
        entidadrespuesta.setPrimera(derechosLicenciasP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }
    
    @Override
    public void delete(String id) {
        Derechoslicencias derechosLicencias=derechosLicenciasDAO 
                .findById(id).orElse(null);
        
        derechosLicenciasDAO.delete(derechosLicencias);
    }

    @Override
    public DerechosLicenciaDTO update(DerechosLicenciaDTO derechosLicenciaDTO, String id) {
        Contribucion contribucion = contribucionDAO.findById(id).orElse(null);
        contribucion.setConceptoContribucion(derechosLicenciaDTO.getConcepto_contribucion());
        TipoPago tipoPago=tipoPagoDao.findById(derechosLicenciaDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion= catalogoDescripcionDao.findById(derechosLicenciaDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDAO.save(contribucion);
        Derechos derecho= derechosDAO.findById(id).orElse(null);
        CatalogoDerecho catalogo= catalogoDerechosDAO.findById(derechosLicenciaDTO.getCatalogo_derechos()).orElse(null);
        derecho.setIdTipoDerecho(catalogo);
        derechosDAO.save(derecho);
        
        Derechoslicencias derechos=derechosLicenciasDAO 
                .findById(id).orElse(null);

        //derechos.setIdcontribucionderechosLicencias(derechosLicenciaDTO.getId_derecho_licencia());
        derechos.setExpedicion(derechosLicenciaDTO.getExpedicion());
        derechos.setRefrendo(derechosLicenciaDTO.getRefrendo());
        //Derechos derechosC = derechosDAO.findById(derechosLicenciaDTO.getId_derecho_licencia()).orElse(null);
        //derechos.setDerechos(derechosC);
        
        Derechoslicencias derechoLincenciasctualizado=derechosLicenciasDAO.save(derechos);
        
        return mapearDTO(derechoLincenciasctualizado);
    }
    
    
    private DerechosLicenciaDTO mapearDTO(Derechoslicencias derechos){
        DerechosLicenciaDTO derechosLicenciaDTO = new DerechosLicenciaDTO();
        derechosLicenciaDTO.setCodigo_contribucion(derechos.getDerechos().getContribucion().getCodigoContribucion());
        derechosLicenciaDTO.setId_derecho_licencia(derechos.getIdContribucionDerechosLicencias());
        derechosLicenciaDTO.setExpedicion(derechos.getExpedicion());
        derechosLicenciaDTO.setRefrendo(derechos.getRefrendo());
        
        return  derechosLicenciaDTO;
    }
    
    private Derechoslicencias mapearEntidad(DerechosLicenciaDTO derechosLicenciaDTO){
        Derechoslicencias derechos = new Derechoslicencias();
       
        derechos.setIdContribucionDerechosLicencias(derechosLicenciaDTO.getCodigo_contribucion());
        derechos.setExpedicion(derechosLicenciaDTO.getExpedicion());
        derechos.setRefrendo(derechosLicenciaDTO.getRefrendo());
        //Derechos derechosC = derechosDAO.findById(derechosLicenciaDTO.getId_derecho_licencia()).orElse(null);
        //derechos.setDerechos(derechosC);

        return derechos;
    }

    @Override
    public Page<DerechosLicenciaDTO> findAll(Pageable pageable) {
        Page<Derechoslicencias> listaDerechosLicencias =derechosLicenciasDAO.findAll(pageable);
        List<DerechosLicenciaDTO> lista= new ArrayList<>();
        for(Derechoslicencias derechosLicencias:listaDerechosLicencias){
            lista.add(new DerechosLicenciaDTO(derechosLicencias.getIdContribucionDerechosLicencias(), derechosLicencias.getExpedicion(), derechosLicencias.getRefrendo(), derechosLicencias.getDerechos().getIdContribucionDerechos(), derechosLicencias.getDerechos().getIdTipoDerecho().getIdTipoDerecho(),derechosLicencias.getDerechos().getIdTipoDerecho().getDescripcion(), derechosLicencias.getDerechos().getContribucion().getCodigoContribucion(), derechosLicencias.getDerechos().getContribucion().getConceptoContribucion(), derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getIdTipoPago(),derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getIdDescripcion(),derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
}
        return (Page<DerechosLicenciaDTO>) lista;
    }
    
    public Page<DerechosLicenciaCompletoDTO> findAllC(Pageable pageable) {
        Page<Derechoslicencias> listaDerechosLicencias =derechosLicenciasDAO.findAll(pageable);
        List<DerechosLicenciaCompletoDTO> lista= new ArrayList<>();
        for(Derechoslicencias derechosLicencias:listaDerechosLicencias){
            lista.add(new DerechosLicenciaCompletoDTO(derechosLicencias.getExpedicion(), derechosLicencias.getRefrendo(), derechosLicencias.getIdContribucionDerechosLicencias(), derechosLicencias.getDerechos().getIdTipoDerecho().getDescripcion(), derechosLicencias.getDerechos().getContribucion().getCodigoContribucion(), derechosLicencias.getDerechos().getContribucion().getConceptoContribucion(), derechosLicencias.getDerechos().getContribucion().getIdTipoPago().getNombrePago(), derechosLicencias.getDerechos().getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<DerechosLicenciaCompletoDTO>) lista;
    }
}
