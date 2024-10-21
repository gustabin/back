/**
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ConfirmarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ConfirmarViajeResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EchoViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EchoViajeResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EliminarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EliminarViajeResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.MarcaViajeroService;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerViajesRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerViajesResponse;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroErrorDesconocidoException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroErrorInternoException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroNoExisteViajeException;

/**
 * Operaciones necesarios para indicar que se va efectuar un viaje a la tarjeta.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component
public class MarcaViajeroDAOImpl implements MarcaViajeroDAO {

    /** The Constant SE_LIBERA_PUERTO. */
    private static final String SE_LIBERA_PUERTO = "Se libero puerto.";
    
    /** The Constant RESPUESTA_WS. */
    private static final String RESPUESTA_WS = "Respuesta ={}";
    /** The Constant ERROR_WS_OBTENER_VIAJES. */
    private static final String ERROR_WS_OBTENER_VIAJES = "Hubo un error al invocar al ws de Obtener Viajes.";
    /** The Constant LOGGER. */
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MarcaViajeroDAOImpl.class);
    
    /** The error 20008. */
    private static final Integer ERROR_VIAJE_REGISTRADO_EN_FECHAS= 20008;

    /** The Constant ERROR_EN_WS. */
    private static final Integer ERROR_EN_WS= 10000;
    
    /** The Constant ERROR_INTERNO_WS. */
    private static final Integer ERROR_INTERNO_WS= 10004;

    /** The no se encontro viaje. */
    private static final  Integer NO_SE_ENCONTRO_VIAJE= 20009;

    /** The error eliminar no existe. */
    private static final  Integer ERROR_ELIMINAR_NO_EXISTE= 1000;
    
    /** The ok. */
    private static final  Integer STATUS_OK= 0;

    /** The Constant ERROR_WS_CONFIRMAR_VIAJE. */
    private static final String ERROR_WS_CONFIRMAR_VIAJE = "Hubo un error al invocar al ws de Confirmar Viaje. Response={}";

    /** Gestionar ws Marca Viajer. */
    @Autowired
    private GestionarWS<MarcaViajeroService> wsMarcaViajeroClient;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.marcaviajero.MarcaViajeroDAO#echoViaje(
     * java .lang.String)
     */
    @Override
    public EchoViajeResponse echoViaje(String message) throws DAOException {
        MarcaViajeroService services = null;
        try {
            services = wsMarcaViajeroClient.obtenerPort();
            EchoViajeRequest request = new EchoViajeRequest();
            request.setMessage(message);
            EchoViajeResponse respuesta = services.echoViaje(request);
            LOGGER.info(RESPUESTA_WS, respuesta);
            return respuesta;
        } finally {
            wsMarcaViajeroClient.liberarPort(services);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO#
     * obtenerViajesDePrismaWS(ar.com.santanderrio.obp.generated.webservices.
     * marcaviajero.ObtenerViajesRequest)
     */
    @Override
    public ObtenerViajesResponse obtenerViajesDePrismaWS(ObtenerViajesRequest request) throws DAOException {
        MarcaViajeroService services = null;
        ObtenerViajesResponse respuesta = null;

        try {
            services = wsMarcaViajeroClient.obtenerPort();
            LOGGER.info("Request : {}", request);
            respuesta = services.obtenerViajes(request);
            LOGGER.info(RESPUESTA_WS, respuesta);

        } catch (RuntimeException e) {
            LOGGER.error(ERROR_WS_OBTENER_VIAJES, e);
            throw new DAOException(e);
        } finally {
            wsMarcaViajeroClient.liberarPort(services);
            LOGGER.info(SE_LIBERA_PUERTO);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO#
     * obtenerTarjetasDelSocio()
     */
    @Override
    public ObtenerTarjetasDelSocioResponse obtenerTarjetasDelSocio(ObtenerTarjetasDelSocioRequest request)
            throws DAOException {

        MarcaViajeroService services = null;
        ObtenerTarjetasDelSocioResponse respuesta = null;

        try {
            services = wsMarcaViajeroClient.obtenerPort();
            LOGGER.info("Request obtenerTarjetasDelSocio={}", request);
            respuesta = services.obtenerTarjetasDelSocio(request);
            LOGGER.info(RESPUESTA_WS, respuesta);

        } catch (RuntimeException e) {
            LOGGER.error(ERROR_WS_OBTENER_VIAJES, e);
            throw new DAOException(e);
        } finally {
            wsMarcaViajeroClient.liberarPort(services);
            LOGGER.info(SE_LIBERA_PUERTO);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO#
     * obtenerPaises()
     */
    @Cacheable(cacheNames = { CacheConstants.Names.CACHE_OBTENER_PAISES_MARCACION })
    @Override
    public ObtenerPaisesResponse obtenerPaises() throws DAOException {
        MarcaViajeroService services = null;
        ObtenerPaisesResponse respuesta = null;

        try {
            services = wsMarcaViajeroClient.obtenerPort();

            respuesta = services.obtenerPaises(new ObtenerPaisesRequest());

            LOGGER.info(RESPUESTA_WS, respuesta);

        } catch (RuntimeException e) {
            LOGGER.error(ERROR_WS_OBTENER_VIAJES, e);
            throw new DAOException(e);
        } finally {
            wsMarcaViajeroClient.liberarPort(services);
            LOGGER.info(SE_LIBERA_PUERTO);
        }
        return respuesta;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO#
     * limpiarCachePaisesMarcacion()
     */
    @CacheEvict(value = CacheConstants.Names.CACHE_OBTENER_PAISES_MARCACION, allEntries = true)
    @Override
    public void limpiarCachePaisesMarcacion() {
        LOGGER.info("Se limpia la cache.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO#
     * confirmarViajeWS(ar.com.santanderrio.obp.generated.webservices.
     * marcaviajero.ConfirmarViajeRequest)
     */
    @Override
    public void confirmarViajeWS(ConfirmarViajeRequest confirmarViajeRequest) throws DAOException {
        MarcaViajeroService services = null;
        ConfirmarViajeResponse respuesta = null;
        services = wsMarcaViajeroClient.obtenerPort();
        try {
            LOGGER.info("Confirmar Viaje WS: {}", confirmarViajeRequest);
            respuesta = services.confirmarViaje(confirmarViajeRequest);
            LOGGER.info(RESPUESTA_WS, respuesta);
        } catch (Exception e) {
            LOGGER.error(ERROR_WS_CONFIRMAR_VIAJE, e);
            throw new DAOException(e);
        } finally {
            wsMarcaViajeroClient.liberarPort(services);
            LOGGER.info(SE_LIBERA_PUERTO);
        }
        if (ERROR_VIAJE_REGISTRADO_EN_FECHAS.equals(respuesta.getCodigoError())) {
            LOGGER.info(ERROR_WS_CONFIRMAR_VIAJE, respuesta);
            throw new MarcaViajeroException();
        }
        if (ERROR_EN_WS.equals(respuesta.getCodigoError())|| 
                ERROR_INTERNO_WS.equals(respuesta.getCodigoError())) {
            LOGGER.info(ERROR_WS_CONFIRMAR_VIAJE, respuesta);
            throw new MarcaViajeroErrorInternoException();
        }
        if (!STATUS_OK.equals(respuesta.getCodigoError())) {
            LOGGER.info(ERROR_WS_CONFIRMAR_VIAJE, respuesta);
            throw new MarcaViajeroErrorDesconocidoException();
        }
    }
    
       /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO#
     * obtenerTarjetasDelSocio()
     */
   @Override
    public void eliminarViaje(EliminarViajeRequest request)
            throws DAOException {

        MarcaViajeroService services = null;
        EliminarViajeResponse respuesta = null;

        try {
            services = wsMarcaViajeroClient.obtenerPort();
            LOGGER.info("Request obtenerTarjetasDelSocio: {}", request);
            respuesta = services.eliminarViaje(request);
            LOGGER.info(RESPUESTA_WS, respuesta);

        } catch (RuntimeException e) { 
            LOGGER.error(ERROR_WS_OBTENER_VIAJES, e);
            throw new DAOException(e);
        } finally {
            wsMarcaViajeroClient.liberarPort(services);
            LOGGER.info(SE_LIBERA_PUERTO);
        }
        if (ERROR_ELIMINAR_NO_EXISTE.equals(respuesta.getCodigoError())|| 
                NO_SE_ENCONTRO_VIAJE.equals(respuesta.getCodigoError())) {
            LOGGER.error(ERROR_WS_CONFIRMAR_VIAJE, respuesta);
            throw new MarcaViajeroNoExisteViajeException();
        }
        if (!STATUS_OK.equals(respuesta.getCodigoError())) {
            LOGGER.error(ERROR_WS_CONFIRMAR_VIAJE, respuesta);
            throw new DAOException();
        }
    }

    
}
