/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.CatalogosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoOtrosProductosDao;
import com.recaudacionMunicipio.modelo.CatalogoOtrosProductos;
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
public class CatalogoOtrosProductosServicioImpl implements CrudServicio<CatalogosDTO>{

    @Autowired
    private IcatalogoOtrosProductosDao catalogoOtrosProductosDao;
    
    @Override
    public CatalogosDTO findById(Integer id) {
        CatalogoOtrosProductos catalogoOtrosProductos =catalogoOtrosProductosDao.findById(id).orElse(null);
        CatalogosDTO catalogoOtros= new CatalogosDTO(catalogoOtrosProductos.getIdOtrosProductos(),catalogoOtrosProductos.getDescripcion());
        return catalogoOtros;
    }

    @Override
    public CatalogosDTO save(CatalogosDTO catalogoDTO) {
        CatalogoOtrosProductos catalogoOtrosProductos= mapearEntidad(catalogoDTO);
        CatalogoOtrosProductos newCatalogo=catalogoOtrosProductosDao.save(catalogoOtrosProductos);
        CatalogosDTO catalogoRespuesta= mapearDTO(newCatalogo);
  
        return catalogoRespuesta;
    }

    @Override
    public entidadRespuesta<CatalogosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<CatalogoOtrosProductos> catalogoOtrosProductosP= catalogoOtrosProductosDao.findAll(pageable);
        List<CatalogoOtrosProductos> listaCatalogoOtrosProductos= catalogoOtrosProductosP.getContent();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoOtrosProductos catalogo:listaCatalogoOtrosProductos ){
            lista.add(new CatalogosDTO(catalogo.getIdOtrosProductos(),catalogo.getDescripcion()) );
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(catalogoOtrosProductosP.getNumber());
        entidadrespuesta.setMedidaPagina(catalogoOtrosProductosP.getSize());
        entidadrespuesta.setTotalElementos(catalogoOtrosProductosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(catalogoOtrosProductosP.getTotalPages());
        entidadrespuesta.setUltima(catalogoOtrosProductosP.isLast());
        entidadrespuesta.setPrimera(catalogoOtrosProductosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        CatalogoOtrosProductos catalogoOtrosProductos=catalogoOtrosProductosDao 
                .findById(id).orElse(null);
        
        catalogoOtrosProductosDao.delete(catalogoOtrosProductos);
    }

    @Override
    public CatalogosDTO update(CatalogosDTO catalogoDTO, int id) {
        CatalogoOtrosProductos catalogo=catalogoOtrosProductosDao 
                .findById(id).orElse(null);

        catalogo.setDescripcion(catalogoDTO.getDescripcion());
        
        CatalogoOtrosProductos catalogoActualizado=catalogoOtrosProductosDao.save(catalogo);
        return mapearDTO(catalogoActualizado);
    }
    
    private CatalogosDTO mapearDTO(CatalogoOtrosProductos catalogoOtrosProductos){
        CatalogosDTO catalogoDTO = new CatalogosDTO();
        catalogoDTO.setId_catalogo(catalogoOtrosProductos.getIdOtrosProductos());
        catalogoDTO.setDescripcion(catalogoOtrosProductos.getDescripcion());
        return  catalogoDTO;
    }
    
    private CatalogoOtrosProductos mapearEntidad(CatalogosDTO catalogoDTO){
        CatalogoOtrosProductos catalogoOtrosProductos = new CatalogoOtrosProductos();
        //catalogoAprovechamiento.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogoOtrosProductos.setDescripcion(catalogoDTO.getDescripcion());
       
        return catalogoOtrosProductos;
    }

    @Override
    public Page<CatalogosDTO> findAll(Pageable pageable) {
        Page<CatalogoOtrosProductos> listaCatalogoOtrosProductos= catalogoOtrosProductosDao.findAll(pageable);
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoOtrosProductos catalogo:listaCatalogoOtrosProductos ){
            lista.add(new CatalogosDTO(catalogo.getIdOtrosProductos(),catalogo.getDescripcion()) );
}
        return (Page<CatalogosDTO>) lista;
    }
    
    public List<CatalogosDTO> findAllCatalogo() {
        List<CatalogoOtrosProductos> listaCatalogoOtrosProductos= catalogoOtrosProductosDao.findAll();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoOtrosProductos catalogo:listaCatalogoOtrosProductos ){
            lista.add(new CatalogosDTO(catalogo.getIdOtrosProductos(),catalogo.getDescripcion()) );
        }
        return lista;
    }
}
