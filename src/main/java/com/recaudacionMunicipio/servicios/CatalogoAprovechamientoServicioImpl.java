/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.CatalogosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ICatalogoAprovechamientoDao;
import com.recaudacionMunicipio.modelo.CatalogoAprovechamiento;
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
public class CatalogoAprovechamientoServicioImpl implements CrudServicio<CatalogosDTO>{

    @Autowired
    private ICatalogoAprovechamientoDao catalogoAprovechamientoDao;
    @Override
    public CatalogosDTO findById(Integer id) {
        CatalogoAprovechamiento catalogoAprovechamiento= catalogoAprovechamientoDao.findById(id).orElse(null);
        CatalogosDTO catalogoAprovechamientos= new CatalogosDTO(catalogoAprovechamiento.getIdTipoAprovechamiento(),catalogoAprovechamiento.getDescripcion());
        return catalogoAprovechamientos;
    }   

    @Override
    public CatalogosDTO save(CatalogosDTO catalogoDTO) {
        CatalogoAprovechamiento catalogoAprovechamiento= mapearEntidad(catalogoDTO);
        CatalogoAprovechamiento newCatalogo=catalogoAprovechamientoDao.save(catalogoAprovechamiento);
        CatalogosDTO catalogoRespuesta= mapearDTO(newCatalogo);
  
        return catalogoRespuesta;
    }

    @Override
    public entidadRespuesta<CatalogosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<CatalogoAprovechamiento> catalogoAprovechamientoP= catalogoAprovechamientoDao.findAll(pageable);
        List<CatalogoAprovechamiento> listaCatalogoAprovechamiento= catalogoAprovechamientoP.getContent();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoAprovechamiento catalogo:listaCatalogoAprovechamiento ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoAprovechamiento(),catalogo.getDescripcion()) );
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(catalogoAprovechamientoP.getNumber());
        entidadrespuesta.setMedidaPagina(catalogoAprovechamientoP.getSize());
        entidadrespuesta.setTotalElementos(catalogoAprovechamientoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(catalogoAprovechamientoP.getTotalPages());
        entidadrespuesta.setUltima(catalogoAprovechamientoP.isLast());
        entidadrespuesta.setPrimera(catalogoAprovechamientoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        CatalogoAprovechamiento catalogoAprovechamiento=catalogoAprovechamientoDao 
                .findById(id).orElse(null);
        
        catalogoAprovechamientoDao.delete(catalogoAprovechamiento);
    }

    @Override
    public CatalogosDTO update(CatalogosDTO catalogoDTO, int id) {
        CatalogoAprovechamiento catalogo=catalogoAprovechamientoDao 
                .findById(id).orElse(null);

        //catalogo.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogo.setDescripcion(catalogoDTO.getDescripcion());
        
        CatalogoAprovechamiento catalogoActualizado=catalogoAprovechamientoDao.save(catalogo);
        return mapearDTO(catalogoActualizado);
    }
    
    private CatalogosDTO mapearDTO(CatalogoAprovechamiento catalogoAprovechamiento){
        CatalogosDTO catalogoDTO = new CatalogosDTO();
        catalogoDTO.setId_catalogo(catalogoAprovechamiento.getIdTipoAprovechamiento());
        catalogoDTO.setDescripcion(catalogoAprovechamiento.getDescripcion());
        return  catalogoDTO;
    }
    
    private CatalogoAprovechamiento mapearEntidad(CatalogosDTO catalogoDTO){
        CatalogoAprovechamiento catalogoAprovechamiento = new CatalogoAprovechamiento();
        //catalogoAprovechamiento.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogoAprovechamiento.setDescripcion(catalogoDTO.getDescripcion());
       
        return catalogoAprovechamiento;
    }

    @Override
    public Page<CatalogosDTO> findAll(Pageable pageable) {
        Page<CatalogoAprovechamiento> listaCatalogoAprovechamiento= catalogoAprovechamientoDao.findAll(pageable);
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoAprovechamiento catalogo:listaCatalogoAprovechamiento ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoAprovechamiento(),catalogo.getDescripcion()) );
}
        return (Page<CatalogosDTO>) lista;
    }
    
    public List<CatalogosDTO> findAllCatalogo() {
        List<CatalogoAprovechamiento> listaCatalogoAprovechamiento= catalogoAprovechamientoDao.findAll();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoAprovechamiento catalogo:listaCatalogoAprovechamiento ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoAprovechamiento(),catalogo.getDescripcion()) );
        }
        return lista;
    }
}
