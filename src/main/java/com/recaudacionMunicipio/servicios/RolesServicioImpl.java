/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.RolesDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IrolesDao;
import com.recaudacionMunicipio.modelo.Roles;
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
public class RolesServicioImpl implements CrudServicio<RolesDTO>{
    
    @Autowired
    private IrolesDao rolesDao;

    @Override
    public RolesDTO findById(Integer id) {
        Roles roles=rolesDao.findById(id).orElse(null);
        RolesDTO rolesDTO = new RolesDTO(roles.getIdRol(),roles.getDescripcionRol());
        return rolesDTO;
    }

    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        Roles roles= mapearEntidad(rolesDTO);
        Roles newRoles=rolesDao.save(roles);
        RolesDTO rolesRespuesta= mapearDTO(newRoles);
  
        return rolesRespuesta;
    }

    @Override
    public entidadRespuesta<RolesDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Roles> rolesP =rolesDao.findAll(pageable);
        List<Roles> listaRoles =rolesP.getContent();
        List<RolesDTO> lista= new ArrayList<>();
        for(Roles roles:listaRoles){
            lista.add(new RolesDTO(roles.getIdRol(),roles.getDescripcionRol()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(rolesP.getNumber());
        entidadrespuesta.setMedidaPagina(rolesP.getSize());
        entidadrespuesta.setTotalElementos(rolesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(rolesP.getTotalPages());
        entidadrespuesta.setUltima(rolesP.isLast());
        entidadrespuesta.setPrimera(rolesP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        Roles roles=rolesDao 
                .findById(id).orElse(null);
        
        rolesDao.delete(roles);
    }

    @Override
    public RolesDTO update(RolesDTO rolesDTO, int id) {
        Roles roles=rolesDao 
                .findById(id).orElse(null);

        roles.setDescripcionRol(rolesDTO.getDescripcion()); 
        Roles rolesActualizado=rolesDao.save(roles);
        
        return mapearDTO(rolesActualizado);
    }
    
    private RolesDTO mapearDTO(Roles roles){
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setDescripcion(roles.getDescripcionRol());
        
        return  rolesDTO;
    }
    
    private Roles mapearEntidad(RolesDTO rolesDTO){
        Roles roles = new Roles();
        roles.setDescripcionRol(rolesDTO.getDescripcion());
       
        return roles;
    }

    @Override
    public Page<RolesDTO> findAll(Pageable pageable) {
        Page<Roles> listaRoles =rolesDao.findAll(pageable);
        List<RolesDTO> lista= new ArrayList<>();
        for(Roles roles:listaRoles){
            lista.add(new RolesDTO(roles.getIdRol(),roles.getDescripcionRol()));
}
        return (Page<RolesDTO>) lista;
    }
    
    public entidadRespuesta<RolesDTO> findAllRoles(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Roles> rolesP =rolesDao.findAll(pageable);
        List<Roles> listaRoles =rolesP.getContent();
        List<RolesDTO> lista= new ArrayList<>();
        for(Roles rol:listaRoles){
            lista.add(new RolesDTO(rol.getIdRol(),rol.getDescripcionRol()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(rolesP.getNumber());
        entidadrespuesta.setMedidaPagina(rolesP.getSize());
        entidadrespuesta.setTotalElementos(rolesP.getTotalElements());
        entidadrespuesta.setTotalPaginas(rolesP.getTotalPages());
        entidadrespuesta.setUltima(rolesP.isLast());
        entidadrespuesta.setPrimera(rolesP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }
    
    public List<RolesDTO> findAllRoles() {
        List<Roles> listaRoles =rolesDao.findAll();
        List<RolesDTO> lista= new ArrayList<>();
        for(Roles roles:listaRoles){
            lista.add(new RolesDTO(roles.getIdRol(),roles.getDescripcionRol()));
        }
        return lista;
    }
}
