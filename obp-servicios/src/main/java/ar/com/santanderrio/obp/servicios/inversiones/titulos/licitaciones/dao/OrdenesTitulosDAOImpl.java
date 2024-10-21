/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FinalizarReversaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RequestConsultarOperacionesTextEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RespuestaDatosOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaResponse;

/**
 * The Class OrdenesTitulosDAOImpl.
 */
@Component
public class OrdenesTitulosDAOImpl implements OrdenesTitulosDAO {

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

    /** The Constant NOMBRE_WS. */
    private static final String NOMBRE_WS = "ORDENES.TENENCIAS";

    /** The inversion WS helper. */
    @Autowired
    private InversionWSHelper inversionWSHelper;

    /** The Constant MENSAJE_ERROR. */
    private static final String MENSAJE_ERROR = "Error llamando al servicio de tenencia valuada";

    /** The dato. */
    @Value("${ORDENES.FIRMA.DATO:Prueba}")
    private String dato;

    /** The firmar. */
    @Value("${ORDENES.FIRMAR:true}")
    private String firmar;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesTitulosDAOImpl.class);

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaCuentaTitulos(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosRequestEntity)
     */
    @Override
    public ConsultaCuentaTitulosResponse consultaCuentaTitulos(ConsultaCuentaTitulosRequestEntity request)
            throws DAOException {

        request.setFirma(generarFirma());
        request.setDato(dato);
        ConsultaCuentaTitulosResponse rta = new ConsultaCuentaTitulosResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaCuentaTitulo/");
            rta = service.post(request, ConsultaCuentaTitulosResponse.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaDeTenencia(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaRequestEntity)
     */
    @Override
    public ConsultaDeTenenciaResponse consultaDeTenencia(ConsultaDeTenenciaRequestEntity request) throws DAOException {

        request.setFirma(generarFirma());
        request.setDato(dato);

        ConsultaDeTenenciaResponse rta = new ConsultaDeTenenciaResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaDeTenencia/");
            rta = service.post(request, ConsultaDeTenenciaResponse.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaAperturaEspecie(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity)
     */
    @Override
    public ConsultaAperturaEspecieResponse consultaAperturaEspecie(ConsultaAperturaEspecieRequestEntity request)
            throws DAOException {

        request.setFirma(generarFirma());
        request.setDato(dato);

        ConsultaAperturaEspecieResponse rta = new ConsultaAperturaEspecieResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaAperturaEspecie/");
            rta = service.post(request, ConsultaAperturaEspecieResponse.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaEspecies(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesRequest)
     */
    @Override
    public ConsultarEspeciesResponse consultaEspecies(ConsultarEspeciesRequest request) throws DAOException {

        request.setFirma(generarFirma());
        request.setDato(dato);

        ConsultarEspeciesResponse rta = new ConsultarEspeciesResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaEspecies/");
            rta = service.post(request, ConsultarEspeciesResponse.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#compraVtaTitulos(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity)
     */
    @Override
    public CompraVtaTitulosResponse compraVtaTitulos(ComptaVtaTitulosRequestEntity request) throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);

        CompraVtaTitulosResponse rta = new CompraVtaTitulosResponse();
        try {
            WebClient service = crearLlamadaAWebService("/CompraVtaTitulosValores/");
            rta = service.post(request, CompraVtaTitulosResponse.class);
            if (rta == null) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultarOrdenes(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOrdenesRequestEntity)
     */
    @Override
    public DatosConsultaOrdenes consultarOrdenes(ConsultaOrdenesRequestEntity request) throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        DatosConsultaOrdenes rta = new DatosConsultaOrdenes();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaDeOrdenes/");
            rta = service.post(request, DatosConsultaOrdenes.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaSuscripcionSaldoPDC(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest)
     */
    @Override
    public ConsultaSuscripcionSaldoPDCResponse consultaSuscripcionSaldoPDC(ConsultaSuscripcionSaldoPDCRequest request)
            throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);

        ConsultaSuscripcionSaldoPDCResponse rta = new ConsultaSuscripcionSaldoPDCResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaSuscripcionSaldoPDC/");
            rta = service.post(request, ConsultaSuscripcionSaldoPDCResponse.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /**
     * genera la firma para llamar a los servicios de P&L.
     *
     * @return the string
     */
    private String generarFirma() {
        String firma = "";
        if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
            try {
                firma = inversionWSHelper.getDatosFirmados(dato);
            } catch (Exception e) {
                LOGGER.error("Error creando firma", e);
            }
        }
        return firma;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultarOperacionesText(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RequestConsultarOperacionesTextEntity)
     */
    @Override
    public ConsultarOperacionesTextResponse consultarOperacionesText(
            RequestConsultarOperacionesTextEntity requestConsultaOperacionesEntity) throws DAOException {
        requestConsultaOperacionesEntity.setFirma(generarFirma());
        requestConsultaOperacionesEntity.setDato(dato);

        ConsultarOperacionesTextResponse rta = new ConsultarOperacionesTextResponse();
        try {
            WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
            service.accept(MediaType.APPLICATION_JSON);
            service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
            service.path("/ConsultaDeOperacionesText/");
            rta = service.post(requestConsultaOperacionesEntity, ConsultarOperacionesTextResponse.class);
            if (rta == null || rta.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error(MENSAJE_ERROR);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /**
	 * Crear llamada A web service.
	 *
	 * @param pathDeConsulta
	 *            the path de consulta
	 * @return the web client
	 * @throws DAOException
	 *             the DAO exception
	 */
    private WebClient crearLlamadaAWebService(String pathDeConsulta) throws DAOException {

        WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
        service.accept(MediaType.APPLICATION_JSON);
        service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        service.path(pathDeConsulta);

        return service;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#finalizarReversarOrdenes(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FinalizarReversaOrdenesRequestEntity)
     */
    @Override
    public RespuestaDatosOrdenResponse finalizarReversarOrdenes(FinalizarReversaOrdenesRequestEntity request)
            throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        RespuestaDatosOrdenResponse rta = new RespuestaDatosOrdenResponse();
        try {
            WebClient service = crearLlamadaAWebService("/SolicitarReversa/");
            rta = service.post(request, RespuestaDatosOrdenResponse.class);
            if (rta == null || rta.getCodigo() != 0 || rta.getDatosOrdenResponse().getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error("Error llamando al servicio reversarOrdenes", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Error llamando al servicio de reversarOrdenes", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            throw new DAOException();
        }
        return rta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaFiltroInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoRequestEntity)
     */
    @Override
    public ConsultaFiltroInformacionMercadoResponse consultaFiltroInfoMercado(
            ConsultaFiltroInformacionMercadoRequestEntity request) throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        ConsultaFiltroInformacionMercadoResponse response = new ConsultaFiltroInformacionMercadoResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaFiltroInformacionMercado/");
            response = service.post(request, ConsultaFiltroInformacionMercadoResponse.class);
            if (response == null || response.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error("Error llamando al servicio ConsultaFiltroInformaciónMercado", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Error llamando al servicio de ConsultaFiltroInformaciónMercado", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error(MENSAJE_ERROR, e);
            throw new DAOException();
        }
        return response;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaIndices(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesRequest)
     */
    @Override
    public ConsultaIndicesResponse consultaIndices(ConsultaIndicesRequest request) throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        ConsultaIndicesResponse response = new ConsultaIndicesResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaIndices/");
            response = service.post(request, ConsultaIndicesResponse.class);
            if (response == null || response.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error("Error llamando al servicio ConsultaIndices", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Error llamando al servicio de ConsultaIndices", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error("Error llamando al servicio de ConsultaIndices", e);
            throw new DAOException();
        }
        return response;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultaInformacionMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoRequest)
     */
    @Override
    public ConsultaInformacionMercadoResponse consultaInformacionMercado(ConsultaInformacionMercadoRequest request)
            throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        ConsultaInformacionMercadoResponse response = new ConsultaInformacionMercadoResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaInformacionMercado/");
            response = service.post(request, ConsultaInformacionMercadoResponse.class);
            if (response == null || response.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error("Error llamando al servicio consultaInformacionMercado", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Error llamando al servicio de consultaInformacionMercado", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error("Error llamando al servicio de consultaInformacionMercado", e);
            throw new DAOException();
        }
        return response;
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#variacionInfomercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoRequest)
	 */
	@Override
	public VariacionInfoMercadoResponse variacionInfomercado(VariacionInfoMercadoRequest request) throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        VariacionInfoMercadoResponse response = new VariacionInfoMercadoResponse();
        try {
            WebClient service = crearLlamadaAWebService("/VariacionInfoMercado/");
            response = service.post(request, VariacionInfoMercadoResponse.class);
            if (response == null || response.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (ResponseProcessingException e) {
            LOGGER.error("Error llamando al servicio VariacionInfoMercado", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Error llamando al servicio de VariacionInfoMercado", e);
            throw new DAOException();
        } catch (RuntimeException e) {
            LOGGER.error("Error llamando al servicio de VariacionInfoMercado", e);
            throw new DAOException();
        }
        return response;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO#consultarOrdenes(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionRequestEntity
	 */
	@Override
	public ConsultaComisionResponse consultaComision(ConsultaComisionRequestEntity request) throws DAOException {
        request.setFirma(generarFirma());
        request.setDato(dato);
        ConsultaComisionResponse response = new ConsultaComisionResponse();
        try {
            WebClient service = crearLlamadaAWebService("/ConsultaComision/");
            response = service.post(request, ConsultaComisionResponse.class);
            if (response == null || response.getCodigo() != 0) {
                throw new DAOException();
            }
        } catch (Exception e) {
            LOGGER.error("Error llamando al servicio ConsultaComisionResponse", e);
            throw new DAOException();
        }
		return response;
	}
	
	

}