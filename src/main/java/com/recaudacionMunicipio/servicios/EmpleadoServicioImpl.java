/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.EmpleadoDTO;
import com.recaudacionMunicipio.DTO.PalacioMunicipalDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IempleadoDao;
import com.recaudacionMunicipio.modelo.Empleado;
import com.recaudacionMunicipio.modelo.Palaciomunicipal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recaudacionMunicipio.dao.IpalacioMunicipalDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Oscar
 */
@Service
public class EmpleadoServicioImpl implements Servicios<EmpleadoDTO>{

    @Autowired
    private IempleadoDao empleadoDao;
    
    @Autowired
    private IpalacioMunicipalDao palacio;
    
    @Override
    public EmpleadoDTO findById(String id) {
        Empleado empleado=empleadoDao.findById(id).orElse(null);
       
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(empleado.getCurp(), empleado.getNombre(), empleado.getApellidoP(), empleado.getApellidoM(),empleado.getIdPalacio().getIdPalacio(),empleado.getIdPalacio().getIdMunicipio().getNombreMunicipio());
        return empleadoDTO;
    }

    @Override
    public EmpleadoDTO save(EmpleadoDTO empleadoDTO) {
        Empleado empleado= mapearEntidad(empleadoDTO);
        Empleado newEmpleado=empleadoDao.save(empleado);
        EmpleadoDTO tipoContribucionRespuesta= mapearDTO(newEmpleado);
  
        return tipoContribucionRespuesta;
    }
    
    public Object crear(EmpleadoDTO empleadoDTO) {
        System.out.println(empleadoDao.existsById(empleadoDTO.getCurp())+"existeeeeeeeeeeeeee ");
        if((empleadoDao.existsById(empleadoDTO.getCurp())))
            return 0;// significa que el empleado ya existe
        if((!empleadoDao.existsById(empleadoDTO.getCurp()))){
            Empleado empleado= mapearEntidad(empleadoDTO);
            Empleado newEmpleado=empleadoDao.save(empleado);
            EmpleadoDTO tipoContribucionRespuesta= mapearDTO(newEmpleado);
  
        return tipoContribucionRespuesta;
        }
        return null;
      
    }

    @Override
    public entidadRespuesta<EmpleadoDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Empleado> empleadosP =empleadoDao.findAll(pageable);
        List<Empleado> listaEmpleados =empleadosP.getContent();
        List<EmpleadoDTO> lista= new ArrayList<>();
        for(Empleado empleado:listaEmpleados){
            
            lista.add(new EmpleadoDTO(empleado.getCurp(), empleado.getNombre(), empleado.getApellidoP(), empleado.getApellidoM(), empleado.getIdPalacio().getIdPalacio(),empleado.getIdPalacio().getIdMunicipio().getNombreMunicipio()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(empleadosP.getNumber());
        entidadrespuesta.setMedidaPagina(empleadosP.getSize());
        entidadrespuesta.setTotalElementos(empleadosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(empleadosP.getTotalPages());
        entidadrespuesta.setUltima(empleadosP.isLast());
        entidadrespuesta.setPrimera(empleadosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        Empleado empleado=empleadoDao 
                .findById(id).orElse(null);
        
        empleadoDao.delete(empleado);
    }
    
    private EmpleadoDTO mapearDTO(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setCurp(empleado.getCurp());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido_p(empleado.getApellidoP());
        empleadoDTO.setApellido_m(empleado.getApellidoM());
        empleadoDTO.setPalacio(empleado.getIdPalacio().getIdPalacio());
        return  empleadoDTO;
    }
    
    private Empleado mapearEntidad(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();
        empleado.setCurp(empleadoDTO.getCurp());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellidoP(empleadoDTO.getApellido_p());
        empleado.setApellidoM(empleadoDTO.getApellido_m());
        Palaciomunicipal palacioM = palacio.getById(empleadoDTO.getPalacio());
        empleado.setIdPalacio(palacioM);
        return empleado;
    }

    @Override
    public EmpleadoDTO update(EmpleadoDTO empleadoDTO, String id) {
        Empleado empleado=empleadoDao 
                .findById(id).orElse(null);
        
        //empleado.setCurp(empleadoDTO.getCurp());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellidoP(empleadoDTO.getApellido_p());
        empleado.setApellidoM(empleadoDTO.getApellido_m());
        Palaciomunicipal palacioM = palacio.findById(empleadoDTO.getPalacio()).orElse(null);
        empleado.setIdPalacio(palacioM);
        
        Empleado empleadoActualizado=empleadoDao.save(empleado);
        return mapearDTO(empleadoActualizado);
    }
    
    @Override
    public Page<EmpleadoDTO> findAll(Pageable pageable) {
        Page<Empleado> listaEmpleados =empleadoDao.findAll(pageable);
        List<EmpleadoDTO> lista= new ArrayList<>();
        for(Empleado empleado:listaEmpleados){
    
            lista.add(new EmpleadoDTO(empleado.getCurp(), empleado.getNombre(), empleado.getApellidoP(), empleado.getApellidoM(), empleado.getIdPalacio().getIdPalacio(),empleado.getIdPalacio().getIdMunicipio().getNombreMunicipio()));
}
        return (Page<EmpleadoDTO>) lista;
    }
    
    public List<EmpleadoDTO> findAllEmpleados() {
        List<Empleado> listaEmpleados =empleadoDao.findAll();
        List<EmpleadoDTO> lista= new ArrayList<>();
        for(Empleado empleado:listaEmpleados){
            
            lista.add(new EmpleadoDTO(empleado.getCurp(), empleado.getNombre(), empleado.getApellidoP(), empleado.getApellidoM(), empleado.getIdPalacio().getIdPalacio(),empleado.getIdPalacio().getIdMunicipio().getNombreMunicipio()));
        }
        return lista;
    }
    
    
}
