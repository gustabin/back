/*
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.dao;

import java.net.SocketTimeoutException;
import java.util.concurrent.Future;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.DetalleComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroDetalleComprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroListaComprobantesOrExt;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Filtros;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ListaComprobantesOrExtRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ListarComprobantesRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Parametros;
import ar.com.santanderrio.obp.servicios.scomp.client.ScompService;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ScomServicioNombreEnum;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class ScompDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("scompDAO")
public class ScompDAOImpl implements ScompDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScompDAOImpl.class);

    /** The Constant COD_RET_OK. */
    private static final String COD_RET_OK = "0";

    /** Gestionar ws SCOMP. */
    @Autowired
    @Qualifier("gestionScomp")
    private GestionarWS<ScompService> wsScompClient;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO#
     * listarComprobantesAsync(ar.com.santanderrio.obp.generated.webservices.
     * scomp.ListarComprobantesRequest)
     */
    @Async
    @Override
    public Future<ComprobanteResponse> listarComprobantesAsync(Filtros filtros) throws DAOException {
        return new AsyncResult<ComprobanteResponse>(listarComprobantes(filtros, "200", "99"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO#listarComprobantes(
     * ar.com.santanderrio.obp.servicios.scomp.client.
     * ListarComprobantesRequest)
     */
    @Override
    public ComprobanteResponse listarComprobantes(Filtros filtros, String version, String subcanal)
            throws DAOException {
        LOGGER.info("Inicio consulta WS Scomp para obtener Listado de comprobantes.");
        ScompService services = null;
        ComprobanteResponse respuesta = null;
        try {
            services = wsScompClient.obtenerPort();
            respuesta = services.listarComprobantes(crearComprobanteRequest(filtros, version, subcanal));
        } catch (WebServiceException e) {
            LOGGER.error("Excepcion al consultar el WS", e);
            throw new DAOException(e);
        } finally {
            wsScompClient.liberarPort(services);
        }
        LOGGER.info("Fin consulta WS Scomp para obtener Listado de comprobantes.");
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO#altaComprobante(ar.
     * com.santanderrio.obp.generated.webservices.scomp.AltaComprobanteRequest)
     */
    @Override
    public ComprobanteResponse altaComprobante(AltaComprobanteRequest altaComprobanteRequest) throws DAOException {
        LOGGER.info("Inicio operacion WS Scomp para dar de alta comprobantes.");
        ScompService services = null;
        ComprobanteResponse respuesta = null;
        try {
            services = wsScompClient.obtenerPort();
            respuesta = services.altaComprobante(altaComprobanteRequest);
        } catch (Exception e) {
            LOGGER.error("Excepcion al consultar el WS", e);
            throw new DAOException(e);
        } finally {
            wsScompClient.liberarPort(services);
        }
        LOGGER.info("Fin operacion WS Scomp para dar de alta comprobantes.");
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO#
     * obtenerComprobanteErroneoAsync()
     */
    @Override
    public Future<ComprobanteResponse> obtenerComprobanteErroneoAsync() {
        return new AsyncResult<ComprobanteResponse>(obtenerComprobanteErroneo());
    }

    /**
     * Obtener comprobante erroneo.
     *
     * @return the comprobante response
     */
    private ComprobanteResponse obtenerComprobanteErroneo() {
        ComprobanteResponse comprobanteErroneo = new ComprobanteResponse();
        comprobanteErroneo.setCodRet(1);
        return comprobanteErroneo;
    }

    /**
     * Crear comprobante request.
     *
     * @param filtros
     *            clase llena con los campos variables del request
     * @param version
     *            the version
     * @param subcanal
     *            the subcanal
     * @return the listar comprobantes request
     */
    private ListarComprobantesRequest crearComprobanteRequest(Filtros filtros, String version, String subcanal) {
        ListarComprobantesRequest request = new ListarComprobantesRequest();
        request.setCanal("04");
        request.setFiltros(filtros);
        request.setNombre(ScomServicioNombreEnum.CNSLISTACOMPROBANTES);
        Parametros parametros = new Parametros();
        parametros.setFormatoRespuesta("XML");
        request.setParametros(parametros);
        request.setSubcanal(subcanal);
        request.setVersion(version);
        return request;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO#listaComprobantesOrExt(ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroListaComprobantesOrExt)
     */
    @Override
    public ComprobanteResponse listaComprobantesOrExt(FiltroListaComprobantesOrExt filtro) throws DAOException {
        LOGGER.info("Inicio consulta WS Scomp para invocar al metodo CNSLISTACOMPROBANTESOREXT.");
        ScompService services = null;
        ComprobanteResponse respuesta = null;
        try {
            services = wsScompClient.obtenerPort();
            respuesta = services.listaComprobantesOrExt(crearRequestComprobantesOrExt(filtro));
        } catch (WebServiceException e) {
            if (e.getCause() instanceof SocketTimeoutException) {
                LOGGER.error("Error de TIMEOUT al invocar al web service Scomp, meotodo: CNSLISTACOMPROBANTESOREXT.",
                        e.getMessage(), e);
                throw new TimeOutException(e.getMessage());
            }
            LOGGER.error("Excepcion al consultar el WS Scomp, metodo: CNSLISTACOMPROBANTESOREXT. ", e);
            throw new DAOException(e);
        } finally {
            wsScompClient.liberarPort(services);
        }
        if (respuesta == null || !COD_RET_OK.equals(Long.toString(respuesta.getCodRet()))) {
            LOGGER.error("Respuesta invalida obtenida del WS Scomp, metodo: CNSLISTACOMPROBANTESOREXT. ");
            throw new DAOException();
        }
        LOGGER.info("Fin consulta WS Scomp para obtener Listado de comprobantes.");
        return respuesta;
    }

    /**
	 * Crear request comprobantes or ext.
	 *
	 * @param filtro
	 *            the filtro
	 * @return the lista comprobantes or ext request
	 */
    private ListaComprobantesOrExtRequest crearRequestComprobantesOrExt(FiltroListaComprobantesOrExt filtro) {
        ListaComprobantesOrExtRequest request = new ListaComprobantesOrExtRequest();
        request.setCanal("04");
        request.setFiltros(filtro);
        request.setNombre(ScomServicioNombreEnum.CNSLISTACOMPROBANTESOREXT);
        Parametros parametros = new Parametros();
        parametros.setFormatoRespuesta("XML");
        request.setParametros(parametros);
        request.setSubcanal("99");
        request.setVersion("1");
        return request;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO#detalleComprobante(ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroDetalleComprobante)
     */
    @Override
    public ComprobanteResponse detalleComprobante(FiltroDetalleComprobante filtro) throws DAOException {
        LOGGER.info("Inicio consulta WS Scomp para invocar al metodo CNSDETALLECOMPROBANTE.");
        ScompService services = null;
        ComprobanteResponse respuesta = null;
        try {
            services = wsScompClient.obtenerPort();
            respuesta = services.detalleComprobante(crearRequestDetalleComprobante(filtro));

        } catch (WebServiceException e) {
            if (e.getCause() instanceof SocketTimeoutException) {
                LOGGER.error("Error de TIMEOUT al invocar al web service Scomp, meotodo: CNSDETALLECOMPROBANTE.",
                        e.getMessage(), e);
                throw new TimeOutException(e.getMessage());
            }
            LOGGER.error("Excepcion al consultar el WS Scomp, metodo: CNSDETALLECOMPROBANTE. ", e);
            throw new DAOException(e);
        } finally {
            wsScompClient.liberarPort(services);
        }
        if (respuesta == null || StringUtils.isEmpty(respuesta.getDetalleHtml())) {
            LOGGER.error("Respuesta invalida obtenida del WS Scomp, metodo: CNSDETALLECOMPROBANTE. ");
            throw new DAOException();
        }
        LOGGER.info("Fin consulta WS Scomp para detalle comprobante.");
        return respuesta;
    }

    /**
	 * Armar el request para obtener el detalle de un comprobante con la version
	 * 100 de la consulta.
	 *
	 * @param filtro
	 *            the filtro
	 * @return the detalle comprobante request
	 */
    private DetalleComprobanteRequest crearRequestDetalleComprobante(FiltroDetalleComprobante filtro) {
        DetalleComprobanteRequest request = new DetalleComprobanteRequest();
        request.setCanal("04");
        request.setFiltros(filtro);
        request.setNombre(ScomServicioNombreEnum.CNSDETALLECOMPROBANTE);
        Parametros parametros = new Parametros();
        parametros.setFormatoRespuesta("HTML");
        request.setParametros(parametros);
        request.setSubcanal("99");
        request.setVersion("100");
        return request;
    }

}
