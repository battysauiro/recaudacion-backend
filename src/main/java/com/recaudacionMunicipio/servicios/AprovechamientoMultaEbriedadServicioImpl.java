/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaEbriedadDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoMultaEbriedadCompletaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ICatalogoAprovechamientoDao;
import com.recaudacionMunicipio.dao.IaprovechamientoDao;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.ImultaEbriedadDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.Aprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoAprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Multaebriedad;
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
public class AprovechamientoMultaEbriedadServicioImpl implements Servicios<AprovechamientoMultaEbriedadDTO> {

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
    private ImultaEbriedadDao aprovechamientoMultaEbriedadDao;

    @Override
    public AprovechamientoMultaEbriedadDTO findById(String id) {
        Multaebriedad aprovechamientoMultaEbriedad = aprovechamientoMultaEbriedadDao.findById(id).orElse(null);
        AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO = new AprovechamientoMultaEbriedadDTO(aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad(), aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion(),aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getNivelContribucion());
        return aprovechamientoMultaEbriedadDTO;
    }

    public AprovechamientoMultaEbriedadCompletaDTO findByIdCompleto(String id) {
        Multaebriedad aprovechamientoMultaEbriedad = aprovechamientoMultaEbriedadDao.findById(id).orElse(null);
        AprovechamientoMultaEbriedadCompletaDTO aprovechamientoMultaEbriedadCompletaDTO = new AprovechamientoMultaEbriedadCompletaDTO(aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion());
        return aprovechamientoMultaEbriedadCompletaDTO;
    }

    @Override
    public AprovechamientoMultaEbriedadDTO save(AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO) {
        Contribucion contribucion = new Contribucion();
        contribucion.setCodigoContribucion(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(aprovechamientoMultaEbriedadDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(aprovechamientoMultaEbriedadDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(aprovechamientoMultaEbriedadDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Aprovechamiento aprovechamiento = new Aprovechamiento();
        aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion());
        CatalogoAprovechamiento catalogo = catalogoAprovechamientoDao.findById(aprovechamientoMultaEbriedadDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        aprovechamiento.setContribucion(contribucion);
        aprovechamientoDao.save(aprovechamiento);

        Multaebriedad aprovechamientoMultaEbriedad = mapearEntidad(aprovechamientoMultaEbriedadDTO);
        aprovechamientoMultaEbriedad.setAprovechamiento(aprovechamiento);
        Multaebriedad newAprovechamientoMultaEbriedad = aprovechamientoMultaEbriedadDao.save(aprovechamientoMultaEbriedad);
        AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadRespuesta = mapearDTO(newAprovechamientoMultaEbriedad);

        return aprovechamientoMultaEbriedadRespuesta;
    }

    public Object crear(AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO) {
        System.out.println(aprovechamientoMultaEbriedadDao.existsById(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion()) + "existeeeeeeeeeeeeee ");
        if ((aprovechamientoMultaEbriedadDao.existsById(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion()))) {
            return 0;// significa que usuario ya existe
        }
        if ((!aprovechamientoMultaEbriedadDao.existsById(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion()))) {
            Contribucion contribucion = new Contribucion();
            contribucion.setCodigoContribucion(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion());
            contribucion.setConceptoContribucion(aprovechamientoMultaEbriedadDTO.getConcepto_contribucion());
            TipoPago tipoPago = tipoPagoDao.findById(aprovechamientoMultaEbriedadDTO.getId_tipo_pago()).orElse(null);
            contribucion.setIdTipoPago(tipoPago);
            CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(aprovechamientoMultaEbriedadDTO.getId_descripcion()).orElse(null);
            contribucion.setIdDescripcion(catalogoDescripcion);
            contribucionDao.save(contribucion);
            Aprovechamiento aprovechamiento = new Aprovechamiento();
            aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion());
            CatalogoAprovechamiento catalogo = catalogoAprovechamientoDao.findById(aprovechamientoMultaEbriedadDTO.getId_catalogo()).orElse(null);
            aprovechamiento.setIdTipoAprovechamiento(catalogo);
            aprovechamiento.setContribucion(contribucion);
            aprovechamientoDao.save(aprovechamiento);

            Multaebriedad aprovechamientoMultaEbriedad = mapearEntidad(aprovechamientoMultaEbriedadDTO);
            aprovechamientoMultaEbriedad.setAprovechamiento(aprovechamiento);
            Multaebriedad newAprovechamientoMultaEbriedad = aprovechamientoMultaEbriedadDao.save(aprovechamientoMultaEbriedad);
            AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadRespuesta = mapearDTO(newAprovechamientoMultaEbriedad);

            return aprovechamientoMultaEbriedadRespuesta;
        }
        return null;

    }

    @Override
    public entidadRespuesta<AprovechamientoMultaEbriedadDTO> findAll(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Multaebriedad> multaEbriedad = aprovechamientoMultaEbriedadDao.findAll(pageable);
        List<Multaebriedad> listaMultaEbriedad = multaEbriedad.getContent();
        List<AprovechamientoMultaEbriedadDTO> lista = new ArrayList<>();
        for (Multaebriedad aprovechamientoMultaEbriedad : listaMultaEbriedad) {
            lista.add(new AprovechamientoMultaEbriedadDTO(aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad(), aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion(),aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getNivelContribucion()));
        }

        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(multaEbriedad.getNumber());
        entidadrespuesta.setMedidaPagina(multaEbriedad.getSize());
        entidadrespuesta.setTotalElementos(multaEbriedad.getTotalElements());
        entidadrespuesta.setTotalPaginas(multaEbriedad.getTotalPages());
        entidadrespuesta.setUltima(multaEbriedad.isLast());
        entidadrespuesta.setPrimera(multaEbriedad.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<AprovechamientoMultaEbriedadCompletaDTO> findAllC(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Multaebriedad> multaEbriedad = aprovechamientoMultaEbriedadDao.findAll(pageable);
        List<Multaebriedad> listaMultaEbriedad = multaEbriedad.getContent();
        List<AprovechamientoMultaEbriedadCompletaDTO> lista = new ArrayList<>();
        for (Multaebriedad aprovechamientoMultaEbriedad : listaMultaEbriedad) {
            lista.add(new AprovechamientoMultaEbriedadCompletaDTO(aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(multaEbriedad.getNumber());
        entidadrespuesta.setMedidaPagina(multaEbriedad.getSize());
        entidadrespuesta.setTotalElementos(multaEbriedad.getTotalElements());
        entidadrespuesta.setTotalPaginas(multaEbriedad.getTotalPages());
        entidadrespuesta.setUltima(multaEbriedad.isLast());
        entidadrespuesta.setPrimera(multaEbriedad.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        Multaebriedad aprovechamientoMultaEbriedad = aprovechamientoMultaEbriedadDao
                .findById(id).orElse(null);

        aprovechamientoMultaEbriedadDao.delete(aprovechamientoMultaEbriedad);
    }

    @Override
    public AprovechamientoMultaEbriedadDTO update(AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO, String id) {
        Contribucion contribucion = contribucionDao.findById(id).orElse(null);
        contribucion.setConceptoContribucion(aprovechamientoMultaEbriedadDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(aprovechamientoMultaEbriedadDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(aprovechamientoMultaEbriedadDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Aprovechamiento aprovechamiento = aprovechamientoDao.findById(id).orElse(null);
        CatalogoAprovechamiento catalogo = catalogoAprovechamientoDao.findById(aprovechamientoMultaEbriedadDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        aprovechamientoDao.save(aprovechamiento);

        Multaebriedad aprovechamientoMultaEbriedad = aprovechamientoMultaEbriedadDao
                .findById(id).orElse(null);

        aprovechamientoMultaEbriedad.setUmaMin(aprovechamientoMultaEbriedadDTO.getUma_min());
        aprovechamientoMultaEbriedad.setUmaMax(aprovechamientoMultaEbriedadDTO.getUma_max());
        aprovechamientoMultaEbriedad.setCantidadAlcohol(aprovechamientoMultaEbriedadDTO.getCantidad_alcohol());
        //aprovechamientoMultaEbriedad.setAprovechamiento(aprovechamiento);

        Multaebriedad aprovechamientoMultaEbriedadActualizado = aprovechamientoMultaEbriedadDao.save(aprovechamientoMultaEbriedad);

        return mapearDTO(aprovechamientoMultaEbriedadActualizado);
    }

    private AprovechamientoMultaEbriedadDTO mapearDTO(Multaebriedad aprovechamientoMultaEbriedad) {
        AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO = new AprovechamientoMultaEbriedadDTO();
        aprovechamientoMultaEbriedadDTO.setCodigo_contribucion(aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion());
        aprovechamientoMultaEbriedadDTO.setId_apro_ebriedad(aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad());
        aprovechamientoMultaEbriedadDTO.setUma_min(aprovechamientoMultaEbriedad.getUmaMin());
        aprovechamientoMultaEbriedadDTO.setUma_max(aprovechamientoMultaEbriedad.getUmaMax());
        aprovechamientoMultaEbriedadDTO.setCantidad_alcohol(aprovechamientoMultaEbriedad.getCantidadAlcohol());

        return aprovechamientoMultaEbriedadDTO;
    }

    private Multaebriedad mapearEntidad(AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO) {
        Multaebriedad aprovechamientoMultaEbriedad = new Multaebriedad();

        aprovechamientoMultaEbriedad.setIdContribucionMultaEbriedad(aprovechamientoMultaEbriedadDTO.getCodigo_contribucion());
        aprovechamientoMultaEbriedad.setUmaMin(aprovechamientoMultaEbriedadDTO.getUma_min());
        aprovechamientoMultaEbriedad.setUmaMax(aprovechamientoMultaEbriedadDTO.getUma_max());
        aprovechamientoMultaEbriedad.setCantidadAlcohol(aprovechamientoMultaEbriedadDTO.getCantidad_alcohol());

        return aprovechamientoMultaEbriedad;
    }

    @Override
    public Page<AprovechamientoMultaEbriedadDTO> findAll(Pageable pageable) {
        Page<Multaebriedad> listaMultaEbriedad = aprovechamientoMultaEbriedadDao.findAll(pageable);
        List<AprovechamientoMultaEbriedadDTO> lista = new ArrayList<>();
        for (Multaebriedad aprovechamientoMultaEbriedad : listaMultaEbriedad) {
            lista.add(new AprovechamientoMultaEbriedadDTO(aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad(), aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion(),aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getNivelContribucion()));
        }
        return (Page<AprovechamientoMultaEbriedadDTO>) lista;
    }
    
    public List<AprovechamientoMultaEbriedadDTO> listaAEbriedad() {
        List<Multaebriedad> listaMultaEbriedad = aprovechamientoMultaEbriedadDao.findAll();
        List<AprovechamientoMultaEbriedadDTO> lista = new ArrayList<>();
        for (Multaebriedad aprovechamientoMultaEbriedad : listaMultaEbriedad) {
            lista.add(new AprovechamientoMultaEbriedadDTO(aprovechamientoMultaEbriedad.getIdContribucionMultaEbriedad(), aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion(),aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getNivelContribucion()));
        }
        return lista;
    }

    // public Page<AprovechamientoMultaEbriedadDTO> findAllCc(Pageable pageable) {
    //    Page<Multaebriedad> listaMultaEbriedad =aprovechamientoMultaEbriedadDao.findAll(pageable);
    //  return (Page<Multaebriedad>)listaMultaEbriedad;
    //List<AprovechamientoMultaEbriedadCompletaDTO> lista= new ArrayList<>();
    //for(Multaebriedad aprovechamientoMultaEbriedad:listaMultaEbriedad ){
    //  System.out.println(aprovechamientoMultaEbriedad+" ----------------------------------------");
    //lista.add(new AprovechamientoMultaEbriedadCompletaDTO(aprovechamientoMultaEbriedad.getUmaMin(), aprovechamientoMultaEbriedad.getUmaMax(), aprovechamientoMultaEbriedad.getCantidadAlcohol(), aprovechamientoMultaEbriedad.getIdcontribucionmultaEbriedad(), aprovechamientoMultaEbriedad.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaEbriedad.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
    ////}
    //return (Page<AprovechamientoMultaEbriedadCompletaDTO>) lista;
    // }
}
