/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.UsuarioDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IempleadoDao;
import com.recaudacionMunicipio.dao.IrolesDao;
import com.recaudacionMunicipio.dao.IusuarioDao;
import com.recaudacionMunicipio.modelo.Empleado;
import com.recaudacionMunicipio.modelo.Roles;
import com.recaudacionMunicipio.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oscar
 */
@Service
public class UsuarioServicio implements UsuarioServicioImpl,UserDetailsService,Servicios<UsuarioDTO>{

    private Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);
    @Autowired
    private IusuarioDao usuarioDao;
    
    @Autowired
    private IempleadoDao empleadoDao;
    
    @Autowired
    private IrolesDao rolesDao;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario =usuarioDao.findByUsername(username);
        
        if(usuario==null){
            logger.error("error de login");
            throw new  UsernameNotFoundException("error de login");
        }
        List<GrantedAuthority> authorities =usuario.getRolesList1().stream()
                .map(role-> new SimpleGrantedAuthority(role.getDescripcionRol()))
                .collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEstadoUsuario(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario findByTokenPassword(String token) {
        System.out.println("este es mi token: "+token);
        return usuarioDao.findByTokenPassword(token);
    }
    
    @Override
    public UsuarioDTO findById(String id) {
        Usuario usuario=usuarioDao.findById(id).orElse(null);
        int id_rol=0;
        String nombre_rol="sin asignar";
        String estado_texto="";
            if(!usuario.getRolesList1().isEmpty()){
                id_rol=usuario.getRolesList1().get(0).getIdRol();
                nombre_rol=usuario.getRolesList1().get(0).getDescripcionRol();
            }
            if(usuario.getEstadoUsuario())
                estado_texto="ACTIVO";
            else
                estado_texto="DE BAJA";
            
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getPassword(), usuario.getUsername(), usuario.getPuestoUsuario(), usuario.getEstadoUsuario(),estado_texto, usuario.getUrlImagenUsuario(), usuario.getIdEmpleado().getCurp(),usuario.getIdEmpleado().getNombre(),id_rol,nombre_rol);
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario= mapearEntidad(usuarioDTO);
        
        Usuario newUsuario=usuarioDao.save(usuario);
        UsuarioDTO usuarioRespuesta= mapearDTO(newUsuario);
                            
        return usuarioRespuesta;
    }
    
    public Usuario saveCorreo(Usuario user) {
        Usuario newUsuario=usuarioDao.save(user);
        
                            
        return newUsuario;
    }
    
    public Object crear(UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDao.existsById(usuarioDTO.getEmail())+"existeeeeeeeeeeeeee ");
        if((usuarioDao.existsById(usuarioDTO.getEmail())))
            return 0;// significa que usuario ya existe
        if((!usuarioDao.existsById(usuarioDTO.getEmail()))){
            Usuario usuario= mapearEntidad(usuarioDTO);
        
            Usuario newUsuario=usuarioDao.save(usuario);
            UsuarioDTO usuarioRespuesta= mapearDTO(newUsuario);
            return usuarioRespuesta;
        }
        return null;
      
    }

    @Override
    public entidadRespuesta<UsuarioDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Usuario> usuarioP =usuarioDao.findAll(pageable);
        List<Usuario> listaUsuario =usuarioP.getContent();
        List<UsuarioDTO> lista= new ArrayList<>();
        for(Usuario usuario:listaUsuario){
            int id_rol=0;
            String nombre_rol="sin asignar";
            String estado_texto="";
            if(!usuario.getRolesList1().isEmpty()){
                id_rol=usuario.getRolesList1().get(0).getIdRol();
                nombre_rol=usuario.getRolesList1().get(0).getDescripcionRol();
        }
            if(usuario.getEstadoUsuario())
                estado_texto="ACTIVO";
            else
                estado_texto="DE BAJA";
            lista.add(new UsuarioDTO(usuario.getPassword(), usuario.getUsername(), usuario.getPuestoUsuario(), usuario.getEstadoUsuario(), estado_texto,usuario.getUrlImagenUsuario(), usuario.getIdEmpleado().getCurp(),usuario.getIdEmpleado().getNombre(),id_rol,nombre_rol));
    }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(usuarioP.getNumber());
        entidadrespuesta.setMedidaPagina(usuarioP.getSize());
        entidadrespuesta.setTotalElementos(usuarioP.getTotalElements());
        entidadrespuesta.setTotalPaginas(usuarioP.getTotalPages());
        entidadrespuesta.setUltima(usuarioP.isLast());
        entidadrespuesta.setPrimera(usuarioP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        Usuario usuario=usuarioDao 
                .findById(id).orElse(null);
        
        usuarioDao.delete(usuario);
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO, String id) {
        Usuario usuario=usuarioDao 
                .findById(id).orElse(null);

        //usuario.setEmailUsuario(usuarioDTO.getNombre_usuario());
        if(!usuarioDTO.getPassword().isEmpty()){
            System.out.println("entro en ponerle contraseña");
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }
        //usuario.setEmailUsuario(usuarioDTO.getEmail());
        usuario.setPuestoUsuario(usuarioDTO.getPuesto());
        //usuario.setEstadoUsuario(usuarioDTO.isEstado());
        //usuario.setUrlImagenUsuario(usuarioDTO.getUrl_imagen());
        Empleado empleado = empleadoDao.findById(usuarioDTO.getId_empleado()).orElse(null);
        usuario.setIdEmpleado(empleado);
        Roles rol=rolesDao.findById(usuarioDTO.getId_rol()).orElse(null);
        List<Roles>roles = new ArrayList<>();
        roles.add(rol);
        usuario.setRolesList1(roles);
        Usuario usuarioActualizado=usuarioDao.save(usuario);
        return mapearDTO(usuarioActualizado);
    }
    
    public UsuarioDTO updateEstado(boolean estado, String id) {
        Usuario usuario=usuarioDao 
                .findById(id).orElse(null);

        usuario.setEstadoUsuario(estado);
        Usuario usuarioActualizado=usuarioDao.save(usuario);
        return mapearDTO(usuarioActualizado);
    }
    
    public UsuarioDTO updateImagen(String ruta, String id) {
        Usuario usuario=usuarioDao 
                .findById(id).orElse(null);

        usuario.setUrlImagenUsuario(ruta);
        Usuario usuarioActualizado=usuarioDao.save(usuario);
        return mapearDTO(usuarioActualizado);
    }
    
    private UsuarioDTO mapearDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        //usuarioDTO.setNombre_usuario(usuario.getNombreUsuario());
        usuarioDTO.setEmail(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setPuesto(usuario.getPuestoUsuario());
        usuarioDTO.setEstado(usuario.getEstadoUsuario());
        usuarioDTO.setUrl_imagen(usuario.getUrlImagenUsuario());
        usuarioDTO.setId_empleado(usuario.getIdEmpleado().getCurp());
        
        return  usuarioDTO;
    }
    
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        //usuario.setNombreUsuario(usuarioDTO.getNombre_usuario());
        usuario.setUsername(usuarioDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setPuestoUsuario(usuarioDTO.getPuesto());
        usuario.setEstadoUsuario(true);// al crear un nuevo usuario por defecto estara en activo
        //usuario.setUrlImagenUsuario(usuarioDTO.getUrl_imagen());
        Empleado empleado = empleadoDao.findById(usuarioDTO.getId_empleado()).orElse(null);
        usuario.setIdEmpleado(empleado);
        Roles rol=rolesDao.findById(usuarioDTO.getId_rol()).orElse(null);
        List<Roles>roles = new ArrayList<>();
        roles.add(rol);
        usuario.setRolesList1(roles);
       
        return usuario;
    }

    @Override
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> listaUsuario =usuarioDao.findAll(pageable);
        List<UsuarioDTO> lista= new ArrayList<>();
        for(Usuario usuario:listaUsuario){
            int id_rol=0;
            String nombre_rol="sin asignar";
            String estado_texto="";
            if(!usuario.getRolesList1().isEmpty()){
                id_rol=usuario.getRolesList1().get(0).getIdRol();
                nombre_rol=usuario.getRolesList1().get(0).getDescripcionRol();
}
            if(usuario.getEstadoUsuario())
                estado_texto="ACTIVO";
            else
                estado_texto="DE BAJA";
                
            lista.add(new UsuarioDTO(usuario.getPassword(), usuario.getUsername(), usuario.getPuestoUsuario(), usuario.getEstadoUsuario(),estado_texto, usuario.getUrlImagenUsuario(), usuario.getIdEmpleado().getCurp(),usuario.getIdEmpleado().getNombre(),id_rol,nombre_rol));
        }
        return (Page<UsuarioDTO>) lista;
    }
}
