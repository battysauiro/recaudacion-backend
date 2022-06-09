/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.DerechosDTO;
import com.recaudacionMunicipio.DTO.entidades.DerechosCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDerechoDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IderechosDao;
import com.recaudacionMunicipio.modelo.CatalogoDerecho;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Derechos;
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
public class DerechosServicioImpl implements Servicios<DerechosDTO>{

    @Autowired
    private IderechosDao derechosDAO;
    
    @Autowired
    private IcatalogoDerechoDao catalogoDerechosDAO;
    
    @Autowired
    private IcontribucionDao contribucionDAO;
    
    @Override
    public DerechosDTO findById(String id) {
        Derechos derechos=derechosDAO.findById(id).orElse(null);
        DerechosDTO derechosDTO = new DerechosDTO(derechos.getIdContribucionDerechos(), derechos.getIdTipoDerecho().getIdTipoDerecho(), derechos.getIdTipoDerecho().getDescripcion(),derechos.getContribucion().getCodigoContribucion(), derechos.getContribucion().getConceptoContribucion(), derechos.getContribucion().getIdTipoPago().getIdTipoPago(), derechos.getContribucion().getIdTipoPago().getNombrePago(),derechos.getContribucion().getIdDescripcion().getIdDescripcion(),derechos.getContribucion().getIdDescripcion().getDescripcion());
        return derechosDTO;
    }

    public DerechosCompletoDTO findByIdCompleto(String id) {
        Derechos derechos=derechosDAO.findById(id).orElse(null);
        DerechosCompletoDTO derechosCompletoDTO = new DerechosCompletoDTO(derechos.getIdContribucionDerechos(), derechos.getIdTipoDerecho().getDescripcion(), derechos.getContribucion().getCodigoContribucion(), derechos.getContribucion().getConceptoContribucion(), derechos.getContribucion().getIdTipoPago().getNombrePago(), derechos.getContribucion().getIdDescripcion().getDescripcion());
        return derechosCompletoDTO;
    }
    
    @Override
    public DerechosDTO save(DerechosDTO derechosDTO) {
        Derechos derechos= mapearEntidad(derechosDTO);
        Derechos newDerecho=derechosDAO.save(derechos);
        DerechosDTO derechoRespuesta= mapearDTO(newDerecho);
  
        return derechoRespuesta;
    }

    @Override
    public entidadRespuesta<DerechosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Derechos> derechosP =derechosDAO.findAll(pageable);
        List<Derechos> listaDerechos =derechosP.getContent();
        List<DerechosDTO> lista= new ArrayList<>();
        for(Derechos derechos:listaDerechos){
            lista.add(new DerechosDTO(derechos.getIdContribucionDerechos(), derechos.getIdTipoDerecho().getIdTipoDerecho(), derechos.getIdTipoDerecho().getDescripcion(),derechos.getContribucion().getCodigoContribucion(), derechos.getContribucion().getConceptoContribucion(), derechos.getContribucion().getIdTipoPago().getIdTipoPago(), derechos.getContribucion().getIdTipoPago().getNombrePago(),derechos.getContribucion().getIdDescripcion().getIdDescripcion(),derechos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(derechosP.getNumber());
        entidadrespuesta.setMedidaPagina(derechosP.getSize());
        entidadrespuesta.setTotalElementos(derechosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(derechosP.getTotalPages());
        entidadrespuesta.setUltima(derechosP.isLast());
        entidadrespuesta.setPrimera(derechosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<DerechosCompletoDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Derechos> derechosP =derechosDAO.findAll(pageable);
        List<Derechos> listaDerechos =derechosP.getContent();
        List<DerechosCompletoDTO> lista= new ArrayList<>();
        for(Derechos derechos:listaDerechos){
            lista.add(new DerechosCompletoDTO(derechos.getIdContribucionDerechos(), derechos.getIdTipoDerecho().getDescripcion(), derechos.getContribucion().getCodigoContribucion(), derechos.getContribucion().getConceptoContribucion(), derechos.getContribucion().getIdTipoPago().getNombrePago(), derechos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(derechosP.getNumber());
        entidadrespuesta.setMedidaPagina(derechosP.getSize());
        entidadrespuesta.setTotalElementos(derechosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(derechosP.getTotalPages());
        entidadrespuesta.setUltima(derechosP.isLast());
        entidadrespuesta.setPrimera(derechosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }
    
    @Override
    public void delete(String id) {
        Derechos derechos=derechosDAO 
                .findById(id).orElse(null);
        
        derechosDAO.delete(derechos);
    }

    @Override
    public DerechosDTO update(DerechosDTO derechosDTO, String id) {
        Derechos derechos=derechosDAO 
                .findById(id).orElse(null);

        CatalogoDerecho catalogoDerecho =catalogoDerechosDAO.findById(derechosDTO.getCatalogo_derechos()).orElse(null);
        //derechos.setIdContribucionDerechos(derechosDTO.getId_derecho());
        derechos.setIdTipoDerecho(catalogoDerecho);
        Contribucion contribucion = contribucionDAO.findById(derechosDTO.getId_derecho()).orElse(null);
        derechos.setContribucion(contribucion);
        
        Derechos derechoActualizado=derechosDAO.save(derechos);
        
        return mapearDTO(derechoActualizado);
    }
    
    private DerechosDTO mapearDTO(Derechos derechos){
        DerechosDTO derechosDTO = new DerechosDTO();
        derechosDTO.setId_derecho(derechos.getIdContribucionDerechos());
        derechosDTO.setCatalogo_derechos(derechos.getIdTipoDerecho().getIdTipoDerecho());
        
        return  derechosDTO;
    }
    
    private Derechos mapearEntidad(DerechosDTO derechosDTO){
        Derechos derechos = new Derechos();
        CatalogoDerecho catalogoDerecho =catalogoDerechosDAO.findById(derechosDTO.getCatalogo_derechos()).orElse(null);
        derechos.setIdContribucionDerechos(derechosDTO.getId_derecho());
        derechos.setIdTipoDerecho(catalogoDerecho);
        Contribucion contribucion = contribucionDAO.findById(derechosDTO.getId_derecho()).orElse(null);
        derechos.setContribucion(contribucion);

        return derechos;
    }

    @Override
    public Page<DerechosDTO> findAll(Pageable pageable) {
        Page<Derechos> listaDerechos =derechosDAO.findAll(pageable);
        List<DerechosDTO> lista= new ArrayList<>();
        for(Derechos derechos:listaDerechos){
            lista.add(new DerechosDTO(derechos.getIdContribucionDerechos(), derechos.getIdTipoDerecho().getIdTipoDerecho(), derechos.getIdTipoDerecho().getDescripcion(),derechos.getContribucion().getCodigoContribucion(), derechos.getContribucion().getConceptoContribucion(), derechos.getContribucion().getIdTipoPago().getIdTipoPago(), derechos.getContribucion().getIdTipoPago().getNombrePago(),derechos.getContribucion().getIdDescripcion().getIdDescripcion(),derechos.getContribucion().getIdDescripcion().getDescripcion()));
}
        return (Page<DerechosDTO>) lista;
    }
    
    public Page<DerechosCompletoDTO> findAllC(Pageable pageable) {
        Page<Derechos> listaDerechos =derechosDAO.findAll(pageable);
        List<DerechosCompletoDTO> lista= new ArrayList<>();
        for(Derechos derechos:listaDerechos){
            lista.add(new DerechosCompletoDTO(derechos.getIdContribucionDerechos(), derechos.getIdTipoDerecho().getDescripcion(), derechos.getContribucion().getCodigoContribucion(), derechos.getContribucion().getConceptoContribucion(), derechos.getContribucion().getIdTipoPago().getNombrePago(), derechos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<DerechosCompletoDTO>) lista;
    }
}
