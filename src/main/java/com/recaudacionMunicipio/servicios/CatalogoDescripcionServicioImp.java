/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.CatalogosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
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
public class CatalogoDescripcionServicioImp implements CrudServicio<CatalogosDTO>{

    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;
    
    @Override
    public CatalogosDTO findById(Integer id) {
        CatalogoDescripcion catalogoDescripcion =catalogoDescripcionDao.findById(id).orElse(null);
        CatalogosDTO catalogoDescripciones= new CatalogosDTO(catalogoDescripcion.getIdDescripcion(),catalogoDescripcion.getDescripcion());
        return catalogoDescripciones;
    }

    @Override
    public CatalogosDTO save(CatalogosDTO catalogoDTO) {
        CatalogoDescripcion catalogoDescripcion= mapearEntidad(catalogoDTO);
        CatalogoDescripcion newCatalogo=catalogoDescripcionDao.save(catalogoDescripcion);
        CatalogosDTO catalogoRespuesta= mapearDTO(newCatalogo);
  
        return catalogoRespuesta;
    }

    @Override
    public entidadRespuesta<CatalogosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<CatalogoDescripcion> catalogoDescripcionP= catalogoDescripcionDao.findAll(pageable);
        List<CatalogoDescripcion> listaCatalogoDescripcion= catalogoDescripcionP.getContent();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoDescripcion catalogo:listaCatalogoDescripcion ){
            lista.add(new CatalogosDTO(catalogo.getIdDescripcion(),catalogo.getDescripcion()) );
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(catalogoDescripcionP.getNumber());
        entidadrespuesta.setMedidaPagina(catalogoDescripcionP.getSize());
        entidadrespuesta.setTotalElementos(catalogoDescripcionP.getTotalElements());
        entidadrespuesta.setTotalPaginas(catalogoDescripcionP.getTotalPages());
        entidadrespuesta.setUltima(catalogoDescripcionP.isLast());
        entidadrespuesta.setPrimera(catalogoDescripcionP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        CatalogoDescripcion catalogoDescripcion=catalogoDescripcionDao 
                .findById(id).orElse(null);
        
        catalogoDescripcionDao.delete(catalogoDescripcion);
    }

    @Override
    public CatalogosDTO update(CatalogosDTO catalogoDTO, int id) {
        CatalogoDescripcion catalogo=catalogoDescripcionDao 
                .findById(id).orElse(null);

        //catalogo.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogo.setDescripcion(catalogoDTO.getDescripcion());
        
        CatalogoDescripcion catalogoActualizado=catalogoDescripcionDao.save(catalogo);
        return mapearDTO(catalogoActualizado);
    }
    
    private CatalogosDTO mapearDTO(CatalogoDescripcion catalogoDescripcion){
        CatalogosDTO catalogoDTO = new CatalogosDTO();
        catalogoDTO.setId_catalogo(catalogoDescripcion.getIdDescripcion());
        catalogoDTO.setDescripcion(catalogoDescripcion.getDescripcion());
        return  catalogoDTO;
    }
    
    private CatalogoDescripcion mapearEntidad(CatalogosDTO catalogoDTO){
        CatalogoDescripcion catalogoDescripcion = new CatalogoDescripcion();
        //catalogoAprovechamiento.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogoDescripcion.setDescripcion(catalogoDTO.getDescripcion());
       
        return catalogoDescripcion;
    }

    @Override
    public Page<CatalogosDTO> findAll(Pageable pageable) {
        Page<CatalogoDescripcion> listaCatalogoDescripcion= catalogoDescripcionDao.findAll(pageable);
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoDescripcion catalogo:listaCatalogoDescripcion ){
            lista.add(new CatalogosDTO(catalogo.getIdDescripcion(),catalogo.getDescripcion()) );
}
        return (Page<CatalogosDTO>) lista;
    }
    
    public List<CatalogosDTO> findAllCatalogo() {
        List<CatalogoDescripcion> listaCatalogoDescripcion= catalogoDescripcionDao.findAll();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoDescripcion catalogo:listaCatalogoDescripcion ){
            lista.add(new CatalogosDTO(catalogo.getIdDescripcion(),catalogo.getDescripcion()) );
        }
        return lista;
    }
}
