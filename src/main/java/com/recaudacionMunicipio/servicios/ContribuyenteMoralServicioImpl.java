/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.ContribucionFacturaDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteMoralDTO;
import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcontribuyenteDao;
import com.recaudacionMunicipio.dao.IcontribuyenteMoralDao;
import com.recaudacionMunicipio.modelo.Contribucionfactura;
import com.recaudacionMunicipio.modelo.Contribuyente;
import com.recaudacionMunicipio.modelo.ContribuyenteMoral;
import com.recaudacionMunicipio.modelo.Factura;
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
public class ContribuyenteMoralServicioImpl implements Servicios<ContribuyenteMoralDTO> {

    @Autowired
    private IcontribuyenteMoralDao contribuyenteMoralDao;

    @Autowired
    private IcontribuyenteDao contribuyenteDao;

    @Override
    public ContribuyenteMoralDTO findById(String id) {
        ContribuyenteMoral contribuyenteMoral = contribuyenteMoralDao.findById(id).orElse(null);
        List<Factura> f = contribuyenteMoral.getContribuyente().getFacturaList();
        List<FacturaDTO> facturaDTO = new ArrayList<>();
        List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
        for (Factura factura : f) {
            List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
            for (Contribucionfactura contribucionf : contribucionfactura) {
                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
            }
            facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO,factura.getEstadoPago()));
        }
        ContribuyenteMoralDTO contribuyenteMoralDTO = new ContribuyenteMoralDTO(contribuyenteMoral.getIdContribuyenteMoral(), contribuyenteMoral.getRazonSocialContribuyenteMoral(), contribuyenteMoral.getContribuyente().getRfcContribuyente(), contribuyenteMoral.getContribuyente().getCalle(), contribuyenteMoral.getContribuyente().getNumero(), contribuyenteMoral.getContribuyente().getColonia(), contribuyenteMoral.getContribuyente().getCp(), facturaDTO);
        return contribuyenteMoralDTO;
    }
    
    public List<ContribuyenteMoralDTO> findByTermino(String term) {
        List<ContribuyenteMoral> listaContribuyente = contribuyenteMoralDao.findByIdContribuyenteMoralStartingWithIgnoreCaseOrRazonSocialContribuyenteMoralStartingWithIgnoreCase(term, term);
        List<ContribuyenteMoralDTO> lista = new ArrayList<>();
        for (ContribuyenteMoral contribuyente : listaContribuyente) {
            List<Factura> f = contribuyente.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteMoralDTO(contribuyente.getIdContribuyenteMoral(), contribuyente.getRazonSocialContribuyenteMoral(), contribuyente.getContribuyente().getRfcContribuyente(), contribuyente.getContribuyente().getCalle(), contribuyente.getContribuyente().getNumero(), contribuyente.getContribuyente().getColonia(), contribuyente.getContribuyente().getCp(), facturaDTO));
        }
        return lista;
    }
    
    public List<ContribuyenteMoralDTO> listarContribuentesMorales() {
        List<ContribuyenteMoral> listaContribuyente = contribuyenteMoralDao.findAll();
        List<ContribuyenteMoralDTO> lista = new ArrayList<>();
        for (ContribuyenteMoral contribuyente : listaContribuyente) {
            List<Factura> f = contribuyente.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteMoralDTO(contribuyente.getIdContribuyenteMoral(), contribuyente.getRazonSocialContribuyenteMoral(), contribuyente.getContribuyente().getRfcContribuyente(), contribuyente.getContribuyente().getCalle(), contribuyente.getContribuyente().getNumero(), contribuyente.getContribuyente().getColonia(), contribuyente.getContribuyente().getCp(), facturaDTO));
        }
        return lista;
    }

    @Override
    public ContribuyenteMoralDTO save(ContribuyenteMoralDTO contribuyenteMoralDTO) {
        ContribuyenteMoral contribuyenteMoral = mapearEntidad(contribuyenteMoralDTO);

        Contribuyente contribuyente = new Contribuyente();
        contribuyente.setRfcContribuyente(contribuyenteMoralDTO.getRfc_contribuyente());
        contribuyente.setCalle(contribuyenteMoralDTO.getCalle());
        contribuyente.setNumero(contribuyenteMoralDTO.getNumero());
        contribuyente.setColonia(contribuyenteMoralDTO.getColonia());
        contribuyente.setCp(contribuyenteMoralDTO.getCodigo_postal());

        Contribuyente newContribuyente = contribuyenteDao.save(contribuyente);
        contribuyenteMoral.setContribuyente(newContribuyente);
        contribuyenteMoral.setIdContribuyenteMoral(newContribuyente.getRfcContribuyente());
        contribuyenteMoral.setContribuyente(contribuyente);
        ContribuyenteMoral newContribuyenteMoral = contribuyenteMoralDao.save(contribuyenteMoral);
        ContribuyenteMoralDTO contribuyenteMoralRespuesta = mapearDTO(newContribuyenteMoral);

        return contribuyenteMoralRespuesta;
    }

    public Object crear(ContribuyenteMoralDTO contribuyenteMoralDTO) {
        System.out.println(contribuyenteDao.existsById(contribuyenteMoralDTO.getRfc_contribuyente()) + "existeeeeeeeeeeeeee " + contribuyenteMoralDTO.getRfc_contribuyente());
        if ((contribuyenteDao.existsById(contribuyenteMoralDTO.getRfc_contribuyente()))) {
            return 0;// significa que el rfc existe
        }
        if (contribuyenteMoralDao.findByIdContribuyenteMoral(contribuyenteMoralDTO.getRfc_contribuyente()) != null) {
            return 0;// significa que la curp existe
        }
        if ((!contribuyenteDao.existsById(contribuyenteMoralDTO.getRfc_contribuyente())) && contribuyenteMoralDao.findByIdContribuyenteMoral(contribuyenteMoralDTO.getId_contribuyente_moral()) == null) {
            Contribuyente contribuyente = new Contribuyente();
            contribuyente.setRfcContribuyente(contribuyenteMoralDTO.getRfc_contribuyente());
            contribuyente.setCalle(contribuyenteMoralDTO.getCalle());
            contribuyente.setNumero(contribuyenteMoralDTO.getNumero());
            contribuyente.setColonia(contribuyenteMoralDTO.getColonia());
            contribuyente.setCp(contribuyenteMoralDTO.getCodigo_postal());
            //Contribuyente newContribuyente= 

            contribuyenteDao.save(contribuyente);
            ContribuyenteMoral contribuyenteMoral = mapearEntidad(contribuyenteMoralDTO);
            contribuyenteMoral.setContribuyente(contribuyente);
            //contribuyenteFisica.setIdContribuyenteFisica(contribuyente.getRfcContribuyente());
            ContribuyenteMoral contribuyenteMoralNew = contribuyenteMoralDao.save(contribuyenteMoral);
            ContribuyenteMoralDTO contribuyenteMoralRespuesta = mapearDTO(contribuyenteMoralNew);
            System.out.println("entro en creaaaaaaaar");
            return contribuyenteMoralRespuesta;
        }
        return null;

    }

    @Override
    public entidadRespuesta<ContribuyenteMoralDTO> findAll(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<ContribuyenteMoral> contribuyenteMoralP = contribuyenteMoralDao.findAll(pageable);
        List<ContribuyenteMoral> listaContribuyenteMoral = contribuyenteMoralP.getContent();
        List<ContribuyenteMoralDTO> lista = new ArrayList<>();
        for (ContribuyenteMoral contribuyenteMoral : listaContribuyenteMoral) {
            List<Factura> f = contribuyenteMoral.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO,factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteMoralDTO(contribuyenteMoral.getIdContribuyenteMoral(), contribuyenteMoral.getRazonSocialContribuyenteMoral(), contribuyenteMoral.getContribuyente().getRfcContribuyente(), contribuyenteMoral.getContribuyente().getCalle(), contribuyenteMoral.getContribuyente().getNumero(), contribuyenteMoral.getContribuyente().getColonia(), contribuyenteMoral.getContribuyente().getCp(),facturaDTO));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(contribuyenteMoralP.getNumber());
        entidadrespuesta.setMedidaPagina(contribuyenteMoralP.getSize());
        entidadrespuesta.setTotalElementos(contribuyenteMoralP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribuyenteMoralP.getTotalPages());
        entidadrespuesta.setUltima(contribuyenteMoralP.isLast());
        entidadrespuesta.setPrimera(contribuyenteMoralP.isFirst());

        return entidadrespuesta;
        //return lista;
    } 

    @Override
    public void delete(String id) {
        ContribuyenteMoral contribuyenteMoral = contribuyenteMoralDao
                .findById(id).orElse(null);

        contribuyenteMoralDao.delete(contribuyenteMoral);
    }

    @Override
    public ContribuyenteMoralDTO update(ContribuyenteMoralDTO contribuyenteMoralDTO, String id) {
        ContribuyenteMoral contribuyenteMoral = contribuyenteMoralDao
                .findById(id).orElse(null);

        Contribuyente contribuyente = contribuyenteDao
                .findById(id).orElse(null);

        //contribuyente.setRfcContribuyente(contribuyenteMoralDTO.getRfc_contribuyente());
        contribuyente.setCalle(contribuyenteMoralDTO.getCalle().toUpperCase());
        contribuyente.setNumero(contribuyenteMoralDTO.getNumero());
        contribuyente.setColonia(contribuyenteMoralDTO.getColonia().toUpperCase());
        contribuyente.setCp(contribuyenteMoralDTO.getCodigo_postal());

        contribuyenteMoral.setRazonSocialContribuyenteMoral(contribuyenteMoralDTO.getRazon_social().toUpperCase());
        //Contribuyente contribuyente =contribuyenteDao.findById(contribuyenteMoralDTO.getId_contribuyente_moral()).orElse(null);
        contribuyenteMoral.setContribuyente(contribuyente);

        ContribuyenteMoral contribuyenteMoralActualizado = contribuyenteMoralDao.save(contribuyenteMoral);

        return mapearDTO(contribuyenteMoralActualizado);
    }

    private ContribuyenteMoralDTO mapearDTO(ContribuyenteMoral contribuyenteMoral) {
        ContribuyenteMoralDTO contribuyenteMoralDTO = new ContribuyenteMoralDTO();
        contribuyenteMoralDTO.setId_contribuyente_moral(contribuyenteMoral.getContribuyente().getRfcContribuyente());
        contribuyenteMoralDTO.setRazon_social(contribuyenteMoral.getRazonSocialContribuyenteMoral());

        return contribuyenteMoralDTO;
    }

    private ContribuyenteMoral mapearEntidad(ContribuyenteMoralDTO contribuyenteMoralDTO) {
        ContribuyenteMoral contribuyenteMoral = new ContribuyenteMoral();
        contribuyenteMoral.setIdContribuyenteMoral(contribuyenteMoralDTO.getRfc_contribuyente().toUpperCase());
        contribuyenteMoral.setRazonSocialContribuyenteMoral(contribuyenteMoralDTO.getRazon_social().toUpperCase());
        //Contribuyente contribuyente =contribuyenteDao.findById(contribuyenteMoralDTO.getId_contribuyente_moral()).orElse(null);
        //contribuyenteMoral.setContribuyente(contribuyente);

        return contribuyenteMoral;
    }

    @Override
    public Page<ContribuyenteMoralDTO> findAll(Pageable pageable) {
        Page<ContribuyenteMoral> listaContribuyenteMoral = contribuyenteMoralDao.findAll(pageable);
        List<ContribuyenteMoralDTO> lista = new ArrayList<>();
        for (ContribuyenteMoral contribuyenteMoral : listaContribuyenteMoral) {
            List<Factura> f = contribuyenteMoral.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(), factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO,factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteMoralDTO(contribuyenteMoral.getIdContribuyenteMoral(), contribuyenteMoral.getRazonSocialContribuyenteMoral(), contribuyenteMoral.getContribuyente().getRfcContribuyente(), contribuyenteMoral.getContribuyente().getCalle(), contribuyenteMoral.getContribuyente().getNumero(), contribuyenteMoral.getContribuyente().getColonia(), contribuyenteMoral.getContribuyente().getCp(),facturaDTO));
        }
        return (Page<ContribuyenteMoralDTO>) lista;
    }
}
