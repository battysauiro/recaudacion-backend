/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;


import com.recaudacionMunicipio.DTO.AprovechamientoDTO;
import com.recaudacionMunicipio.DTO.ContribucionDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
//import com.recaudacionMunicipio.DTO.entidades.AprovechamientoDTO;  
import com.recaudacionMunicipio.dao.ICatalogoAprovechamientoDao;
import com.recaudacionMunicipio.dao.IaprovechamientoDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.modelo.Aprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoAprovechamiento;
import com.recaudacionMunicipio.modelo.Contribucion;
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
public class AprovechamientoServicioImpl implements Servicios<AprovechamientoDTO> {

    @Autowired
    private IaprovechamientoDao aprovechamientoDao;
    
    @Autowired
    private ICatalogoAprovechamientoDao catalogoAproDao;
    
    @Autowired
    private IcontribucionDao contribucionDao;
    
    @Override
    public AprovechamientoDTO findById(String id) {
        Aprovechamiento aprovechamiento= aprovechamientoDao.findById(id).orElse(null);
        AprovechamientoDTO aprovechamientoDTO = new AprovechamientoDTO(aprovechamiento.getIdContribucionAprovechamiento(), aprovechamiento.getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamiento.getIdTipoAprovechamiento().getDescripcion(), aprovechamiento.getContribucion().getCodigoContribucion(), aprovechamiento.getContribucion().getConceptoContribucion(), aprovechamiento.getContribucion().getIdTipoPago().getIdTipoPago(),aprovechamiento.getContribucion().getIdTipoPago().getNombrePago(), aprovechamiento.getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamiento.getContribucion().getIdDescripcion().getDescripcion(),aprovechamiento.getContribucion().getNivelContribucion());
        return aprovechamientoDTO;  
    }

    public AprovechamientoCompletoDTO findByIdCompleto(String id) {
        Aprovechamiento aprovechamiento=aprovechamientoDao.findById(id).orElse(null);
        AprovechamientoCompletoDTO aprovechamientoCompletoDTO = new AprovechamientoCompletoDTO(aprovechamiento.getIdContribucionAprovechamiento(), aprovechamiento.getIdTipoAprovechamiento().getDescripcion(), aprovechamiento.getContribucion().getCodigoContribucion(), aprovechamiento.getContribucion().getConceptoContribucion(), aprovechamiento.getContribucion().getIdTipoPago().getNombrePago(), aprovechamiento.getContribucion().getIdDescripcion().getDescripcion());
        return aprovechamientoCompletoDTO;
    }
    
    @Override
    public AprovechamientoDTO save(AprovechamientoDTO aprovechamientoDTO) {
        Aprovechamiento aprovechamiento= mapearEntidad(aprovechamientoDTO);
        Aprovechamiento newAprovechamiento=aprovechamientoDao.save(aprovechamiento);
        AprovechamientoDTO aprovechamientoRespuesta= mapearDTO(newAprovechamiento);
  
        return aprovechamientoRespuesta;
    }

    @Override
    public entidadRespuesta<AprovechamientoDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Aprovechamiento> listaAprovechamientoP =aprovechamientoDao.findAll(pageable);
        List<Aprovechamiento> listaAprovechamiento =listaAprovechamientoP.getContent();
        List<AprovechamientoDTO> lista= new ArrayList<>();
        for(Aprovechamiento aprovechamiento:listaAprovechamiento ){
            lista.add(new AprovechamientoDTO(aprovechamiento.getIdContribucionAprovechamiento(), aprovechamiento.getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamiento.getIdTipoAprovechamiento().getDescripcion(), aprovechamiento.getContribucion().getCodigoContribucion(), aprovechamiento.getContribucion().getConceptoContribucion(), aprovechamiento.getContribucion().getIdTipoPago().getIdTipoPago(),aprovechamiento.getContribucion().getIdTipoPago().getNombrePago(), aprovechamiento.getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamiento.getContribucion().getIdDescripcion().getDescripcion(),aprovechamiento.getContribucion().getNivelContribucion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(listaAprovechamientoP.getNumber());
        entidadrespuesta.setMedidaPagina(listaAprovechamientoP.getSize());
        entidadrespuesta.setTotalElementos(listaAprovechamientoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(listaAprovechamientoP.getTotalPages());
        entidadrespuesta.setUltima(listaAprovechamientoP.isLast());
        entidadrespuesta.setPrimera(listaAprovechamientoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<AprovechamientoCompletoDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Aprovechamiento> listaAprovechamientoP =aprovechamientoDao.findAll(pageable);
        List<Aprovechamiento> listaAprovechamiento =listaAprovechamientoP.getContent();
        List<AprovechamientoCompletoDTO> lista= new ArrayList<>();
        for(Aprovechamiento aprovechamiento:listaAprovechamiento){
            lista.add(new AprovechamientoCompletoDTO(aprovechamiento.getIdContribucionAprovechamiento(), aprovechamiento.getIdTipoAprovechamiento().getDescripcion(), aprovechamiento.getContribucion().getCodigoContribucion(), aprovechamiento.getContribucion().getConceptoContribucion(), aprovechamiento.getContribucion().getIdTipoPago().getNombrePago(), aprovechamiento.getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(listaAprovechamientoP.getNumber());
        entidadrespuesta.setMedidaPagina(listaAprovechamientoP.getSize());
        entidadrespuesta.setTotalElementos(listaAprovechamientoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(listaAprovechamientoP.getTotalPages());
        entidadrespuesta.setUltima(listaAprovechamientoP.isLast());
        entidadrespuesta.setPrimera(listaAprovechamientoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
        //return listaContribuciones.stream().map(contribucion -> mapearDTO(contribucion)).collect(Collectors.toList());
    }
    
    @Override
    public void delete(String id) {
        Aprovechamiento aprovechamiento=aprovechamientoDao 
                .findById(id).orElse(null);
        
        aprovechamientoDao.delete(aprovechamiento);
    }

    @Override
    public AprovechamientoDTO update(AprovechamientoDTO aprovechamientoDTO, String id) {
       Aprovechamiento aprovechamiento=aprovechamientoDao 
                .findById(id).orElse(null);

        //aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoDTO.getId_contribucion());
        Contribucion contribucion =contribucionDao.findById(aprovechamientoDTO.getId_contribucion()).orElse(null);
        aprovechamiento.setContribucion(contribucion);
        CatalogoAprovechamiento catalogo = catalogoAproDao.findById(aprovechamientoDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        
        Aprovechamiento aprovechamientoActualizado=aprovechamientoDao.save(aprovechamiento);
        
        return mapearDTO(aprovechamientoActualizado);
    }

    
    private AprovechamientoDTO mapearDTO(Aprovechamiento aprovechamiento){
        AprovechamientoDTO aprovechamientoDTO = new AprovechamientoDTO();
        aprovechamientoDTO.setId_contribucion(aprovechamiento.getIdContribucionAprovechamiento());
        aprovechamientoDTO.setId_catalogo(aprovechamiento.getIdTipoAprovechamiento().getIdTipoAprovechamiento());
        
        return  aprovechamientoDTO;
    }
    
    private Aprovechamiento mapearEntidad(AprovechamientoDTO aprovechamientoDTO){
        Aprovechamiento aprovechamiento = new Aprovechamiento();
        Contribucion contribucion =contribucionDao.findById(aprovechamientoDTO.getId_contribucion()).orElse(null);
        aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoDTO.getId_contribucion());
        
        aprovechamiento.setContribucion(contribucion);
        CatalogoAprovechamiento catalogo = catalogoAproDao.findById(aprovechamientoDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
    
        return aprovechamiento;
    }
    
    @Override
    public Page<AprovechamientoDTO> findAll(Pageable pageable) {
        Page<Aprovechamiento> listaAprovechamiento =aprovechamientoDao.findAll(pageable);
        List<AprovechamientoDTO> lista= new ArrayList<>();
        for(Aprovechamiento aprovechamiento:listaAprovechamiento ){
            lista.add(new AprovechamientoDTO(aprovechamiento.getIdContribucionAprovechamiento(), aprovechamiento.getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamiento.getIdTipoAprovechamiento().getDescripcion(), aprovechamiento.getContribucion().getCodigoContribucion(), aprovechamiento.getContribucion().getConceptoContribucion(), aprovechamiento.getContribucion().getIdTipoPago().getIdTipoPago(),aprovechamiento.getContribucion().getIdTipoPago().getNombrePago(), aprovechamiento.getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamiento.getContribucion().getIdDescripcion().getDescripcion(),aprovechamiento.getContribucion().getNivelContribucion()));
}
        return (Page<AprovechamientoDTO>) lista;
    }
    
    public Page<AprovechamientoCompletoDTO> findAllC(Pageable pageable) {
        Page<Aprovechamiento> listaAprovechamiento =aprovechamientoDao.findAll(pageable);
        List<AprovechamientoCompletoDTO> lista= new ArrayList<>();
        for(Aprovechamiento aprovechamiento:listaAprovechamiento){
            lista.add(new AprovechamientoCompletoDTO(aprovechamiento.getIdContribucionAprovechamiento(), aprovechamiento.getIdTipoAprovechamiento().getDescripcion(), aprovechamiento.getContribucion().getCodigoContribucion(), aprovechamiento.getContribucion().getConceptoContribucion(), aprovechamiento.getContribucion().getIdTipoPago().getNombrePago(), aprovechamiento.getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<AprovechamientoCompletoDTO>) lista;
    }
    
}
