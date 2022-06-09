/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoMultaCompletaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ICatalogoAprovechamientoDao;
import com.recaudacionMunicipio.dao.IaprovechamientoDao;
import com.recaudacionMunicipio.dao.IaprovechamientoMultaDao;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.Aprovechamiento;
import com.recaudacionMunicipio.modelo.Aprovechamientomulta;
import com.recaudacionMunicipio.modelo.CatalogoAprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.Contribucion;
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
public class AprovechamientoMultaServicioImpl implements Servicios<AprovechamientoMultaDTO>{

    @Autowired
    private IcontribucionDao contribucionDao;
    
    @Autowired
    private ItipoPagoDao tipoPagoDao;
    
    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;
    
    @Autowired
    private IaprovechamientoDao aprovechamientoDao;
    
    @Autowired
    private ICatalogoAprovechamientoDao catalogoAprovechamientoDao;
    
    @Autowired
    private IaprovechamientoMultaDao aprovechamientoMultaDao;
    
    
    
    @Override
    public AprovechamientoMultaDTO findById(String id) {
        //Contribucion contribucion=contribucionDao.findById(id).orElse(null);
        Aprovechamientomulta aprovechamientoMulta= aprovechamientoMultaDao.findById(id).orElse(null);
        //contribucion.getAprovechamiento().getAprovechamientomulta().getCantidad() 
        AprovechamientoMultaDTO aprovechamientoMultaDTO = new AprovechamientoMultaDTO(aprovechamientoMulta.getIdContribucionMulta(),aprovechamientoMulta.getCantidad() , aprovechamientoMulta.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago() ,aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion());
        return aprovechamientoMultaDTO;
    }

    public List<AprovechamientoMultaDTO> findByIdContribucion(String idContribucionMulta) {
        System.out.println("esto es el codigoooooooooooooooooooooooooooooooooooo   "+idContribucionMulta);
         List<Aprovechamientomulta> listaAprovechamientoMulta =aprovechamientoMultaDao.findByIdContribucionMultaStartingWith(idContribucionMulta);
        List<AprovechamientoMultaDTO> lista= new ArrayList<>(); 
        for(Aprovechamientomulta aprovechamientoMulta:listaAprovechamientoMulta){
            lista.add(new AprovechamientoMultaDTO(aprovechamientoMulta.getIdContribucionMulta(),aprovechamientoMulta.getCantidad() , aprovechamientoMulta.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago() ,aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        } 
        return lista;
    }

    public AprovechamientoMultaCompletaDTO findByIdCompleto(String id) {
        Aprovechamientomulta aprovechamientoMulta=aprovechamientoMultaDao.findById(id).orElse(null);
        AprovechamientoMultaCompletaDTO aprovechamientoMultaCompletaDTO = new AprovechamientoMultaCompletaDTO(aprovechamientoMulta.getCantidad(), aprovechamientoMulta.getIdContribucionMulta(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion());
        return aprovechamientoMultaCompletaDTO;
    }
    
    @Override
    public AprovechamientoMultaDTO save(AprovechamientoMultaDTO aprovechamientoMultaDTO) {
        Contribucion contribucion= new Contribucion();
        contribucion.setCodigoContribucion(aprovechamientoMultaDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(aprovechamientoMultaDTO.getConcepto_contribucion());
        TipoPago tipoPago=tipoPagoDao.findById(aprovechamientoMultaDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion= catalogoDescripcionDao.findById(aprovechamientoMultaDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        //Contribucion newContribucion=contribucionDao.findById(aprovechamientoMultaDTO.getCodigo_contribucion()).orElse(null);
        Aprovechamiento aprovechamiento= new Aprovechamiento();
        aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoMultaDTO.getCodigo_contribucion());
        CatalogoAprovechamiento catalogo= catalogoAprovechamientoDao.findById(aprovechamientoMultaDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        aprovechamiento.setContribucion(contribucion);
        
         
        Aprovechamiento newAprovechamiento=aprovechamientoDao.save(aprovechamiento);
        Aprovechamientomulta aprovechamientoMulta= mapearEntidad(aprovechamientoMultaDTO);
        aprovechamientoMulta.setAprovechamiento(newAprovechamiento);
        Aprovechamientomulta newAprovechamientoMulta=aprovechamientoMultaDao.save(aprovechamientoMulta);
        AprovechamientoMultaDTO aprovechamientoMultaRespuesta= mapearDTO(newAprovechamientoMulta);
  
        return aprovechamientoMultaRespuesta;
    }
    
    public Object crear(AprovechamientoMultaDTO aprovechamientoMultServicioDTO) {
        System.out.println(aprovechamientoMultaDao.existsById(aprovechamientoMultServicioDTO.getCodigo_contribucion())+"existeeeeeeeeeeeeee ");
        if((aprovechamientoMultaDao.existsById(aprovechamientoMultServicioDTO.getCodigo_contribucion())))
            return 0;// significa que usuario ya existe
        if((!aprovechamientoMultaDao.existsById(aprovechamientoMultServicioDTO.getCodigo_contribucion()))){
            Aprovechamientomulta aprovechamientoMultaServicio= mapearEntidad(aprovechamientoMultServicioDTO);
        
            Aprovechamientomulta aprovechamientoMultaEbriedadNueva=aprovechamientoMultaDao.save(aprovechamientoMultaServicio);
            AprovechamientoMultaDTO aprovechamientoRespuesta= mapearDTO(aprovechamientoMultaEbriedadNueva);
            return aprovechamientoRespuesta;
        }
        return null;
      
    }

    @Override
    public entidadRespuesta<AprovechamientoMultaDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Aprovechamientomulta> aprovechamientoMultaP=aprovechamientoMultaDao.findAll(pageable);
        List<Aprovechamientomulta> listaContribuciones =aprovechamientoMultaP.getContent();
        List<AprovechamientoMultaDTO> lista= new ArrayList<>();
        for(Aprovechamientomulta aprovechamientoMulta:listaContribuciones ){
          lista.add(new AprovechamientoMultaDTO(aprovechamientoMulta.getIdContribucionMulta(),aprovechamientoMulta.getCantidad() , aprovechamientoMulta.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago() ,aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(aprovechamientoMultaP.getNumber());
        entidadrespuesta.setMedidaPagina(aprovechamientoMultaP.getSize());
        entidadrespuesta.setTotalElementos(aprovechamientoMultaP.getTotalElements());
        entidadrespuesta.setTotalPaginas(aprovechamientoMultaP.getTotalPages());
        entidadrespuesta.setUltima(aprovechamientoMultaP.isLast());
        entidadrespuesta.setPrimera(aprovechamientoMultaP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<AprovechamientoMultaCompletaDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Aprovechamientomulta> aprovechamientoMultaP=aprovechamientoMultaDao.findAll(pageable);
        List<Aprovechamientomulta> listaAprovechamientoMulta =aprovechamientoMultaP.getContent();
        List<AprovechamientoMultaCompletaDTO> lista= new ArrayList<>();
        for(Aprovechamientomulta aprovechamientoMulta:listaAprovechamientoMulta){
            lista.add(new AprovechamientoMultaCompletaDTO(aprovechamientoMulta.getCantidad(), aprovechamientoMulta.getIdContribucionMulta(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(aprovechamientoMultaP.getNumber());
        entidadrespuesta.setMedidaPagina(aprovechamientoMultaP.getSize());
        entidadrespuesta.setTotalElementos(aprovechamientoMultaP.getTotalElements());
        entidadrespuesta.setTotalPaginas(aprovechamientoMultaP.getTotalPages());
        entidadrespuesta.setUltima(aprovechamientoMultaP.isLast());
        entidadrespuesta.setPrimera(aprovechamientoMultaP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }
    
    
    @Override
    public void delete(String id) {
        Aprovechamientomulta aprovechamientoMulta=aprovechamientoMultaDao 
                .findById(id).orElse(null);
        
        aprovechamientoMultaDao.delete(aprovechamientoMulta);
    }

    @Override
    public AprovechamientoMultaDTO update(AprovechamientoMultaDTO aprovechamientoMultaDTO, String id) {
        Contribucion contribucion = contribucionDao.findById(id).orElse(null);
        contribucion.setConceptoContribucion(aprovechamientoMultaDTO.getConcepto_contribucion());
        TipoPago tipoPago=tipoPagoDao.findById(aprovechamientoMultaDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion= catalogoDescripcionDao.findById(aprovechamientoMultaDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Aprovechamiento aprovechamiento= aprovechamientoDao.findById(id).orElse(null);
        CatalogoAprovechamiento catalogo= catalogoAprovechamientoDao.findById(aprovechamientoMultaDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        aprovechamientoDao.save(aprovechamiento);
        
        
        Aprovechamientomulta aprovechamientoMulta=aprovechamientoMultaDao 
                .findById(id).orElse(null);

        aprovechamientoMulta.setCantidad(aprovechamientoMultaDTO.getCantidad());
        //aprovechamientoMulta.setAprovechamiento(aprovechamiento);
        
        Aprovechamientomulta aprovechamientoMultaActualizado=aprovechamientoMultaDao.save(aprovechamientoMulta);
        
        return mapearDTO(aprovechamientoMultaActualizado);
    }
    
    private AprovechamientoMultaDTO mapearDTO(Aprovechamientomulta aprovechamientoMulta){
        AprovechamientoMultaDTO aprovechamientoMultaDTO = new AprovechamientoMultaDTO();
        aprovechamientoMultaDTO.setCodigo_contribucion(aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion());
        aprovechamientoMultaDTO.setId_aprovechamiento_multa(aprovechamientoMulta.getIdContribucionMulta());
        aprovechamientoMultaDTO.setCantidad(aprovechamientoMulta.getCantidad());
        
        return  aprovechamientoMultaDTO;
    }
    
    private Aprovechamientomulta mapearEntidad(AprovechamientoMultaDTO aprovechamientoMultaDTO){
        Aprovechamientomulta aprovechamientoMulta = new Aprovechamientomulta();
        //Aprovechamiento aprovechamiento =aprovechamientoDao.findById(aprovechamientoMultaDTO.getId_aprovechamiento_multa()).orElse(null);
        aprovechamientoMulta.setIdContribucionMulta(aprovechamientoMultaDTO.getCodigo_contribucion());
        aprovechamientoMulta.setCantidad(aprovechamientoMultaDTO.getCantidad());
        //aprovechamientoMulta.setAprovechamiento(aprovechamiento);

        return aprovechamientoMulta;
    }

    @Override
    public Page<AprovechamientoMultaDTO> findAll(Pageable pageable) {
        Page<Aprovechamientomulta> listaContribuciones =aprovechamientoMultaDao.findAll(pageable);
        List<AprovechamientoMultaDTO> lista= new ArrayList<>();
        for(Aprovechamientomulta aprovechamientoMulta:listaContribuciones ){
          lista.add(new AprovechamientoMultaDTO(aprovechamientoMulta.getIdContribucionMulta(),aprovechamientoMulta.getCantidad() , aprovechamientoMulta.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(),aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago() ,aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(),aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
}
        return (Page<AprovechamientoMultaDTO>) lista;
    }
    
    public Page<AprovechamientoMultaCompletaDTO> findAllC(Pageable pageable) {
        Page<Aprovechamientomulta> listaAprovechamientoMulta =aprovechamientoMultaDao.findAll(pageable);
        List<AprovechamientoMultaCompletaDTO> lista= new ArrayList<>();
        for(Aprovechamientomulta aprovechamientoMulta:listaAprovechamientoMulta){
            lista.add(new AprovechamientoMultaCompletaDTO(aprovechamientoMulta.getCantidad(), aprovechamientoMulta.getIdContribucionMulta(), aprovechamientoMulta.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMulta.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<AprovechamientoMultaCompletaDTO>) lista;
    }
}
