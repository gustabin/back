/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.bo.AutentificacionBO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AutenticacionBOImpl.
 *
 * @author sabrina.cis
 */
public class AutentificacionBOImpl implements AutentificacionBO {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AutentificacionBOImpl.class);

    /** The estadistica BO. */
    @Autowired
    private EstadisticaBO estadisticaBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The sesion paramatros. */
    @Autowired
    private SesionParametros sesionParamatros;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The Constant ERROR_ESTADISTICAS. */
    private static final String ERROR_ESTADISTICAS = "Error al dar de alta la estadistica {}";

    /** The Constant MSJ_ERROR_EJECUCION_COORDENADAS. */
    public static final String MSJ_ERROR_EJECUCION_COORDENADAS = "Ha ocurrido un error al validar las coordenadas. ";

    /**
     * The Constant
     * LAS_ESTADISTICAS_NO_FUERON_SETEADAS_ANTES_DE_CONSULTAR_EL_DESAFIO.
     */
    public static final String LAS_ESTADISTICAS_NO_FUERON_SETEADAS_ANTES_DE_CONSULTAR_EL_DESAFIO = "Las estadisticas no fueron seteadas antes de consultar el desafio.";

    /**
     * Grabar estadistica.
     *
     * @param codigoEstadistica
     *            the codigo estadistica
     * @param codigoError
     *            the codigo error
     */
    @Override
    public void grabarEstadistica(String codigoEstadistica, String codigoError) {
        if (codigoEstadistica != null) {
            Estadistica estadistica = new Estadistica();
            try {
                estadistica.setCodigoTransaccion(codigoEstadistica);
                estadistica.setCodigoError(codigoError);
                estadisticaBO.add(estadistica, sesionParamatros.getRegistroSession(), sesionCliente.getCliente());
            } catch (BusinessException e) {
                LOGGER.error(ERROR_ESTADISTICAS, estadistica, e);
            }
        } else {
            LOGGER.info(LAS_ESTADISTICAS_NO_FUERON_SETEADAS_ANTES_DE_CONSULTAR_EL_DESAFIO);
        }
    }

    /**
     * Crear respuesta error generico sin registrar estadistica.
     *
     * @return the respuesta
     */
    public Respuesta<AutentificacionDTO> crearRespuestaErrorGenericoSinEstadistica() {
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_GENERICO_RSA);
    }

    /**
     * Crear respuesta error generico.
     *
     * @param codigoEstadistica
     *            the codigo estadistica validacion
     * @return the respuesta
     */
    public Respuesta<AutentificacionDTO> crearRespuestaErrorGenerico(String codigoEstadistica) {
        grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_GENERICO_RSA);
    }

    public Respuesta<AutentificacionDTO> crearRespuestaErrorGenericoToken(String codigoEstadistica) {
        grabarEstadistica(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_GENERICO_TOKEN);
    }

    /**
     * Gets the respuesta factory.
     *
     * @return the respuestaFactory
     */
    public RespuestaFactory getRespuestaFactory() {
        return respuestaFactory;
    }

    /**
     * Sets the respuesta factory.
     *
     * @param respuestaFactory
     *            the respuestaFactory to set
     */
    public void setRespuestaFactory(RespuestaFactory respuestaFactory) {
        this.respuestaFactory = respuestaFactory;
    }

    /**
     * Gets the sesion cliente.
     *
     * @return the sesion cliente
     */
    public SesionCliente getSesionCliente() {
        return sesionCliente;
    }

    /**
     * Sets the sesion cliente.
     *
     * @param sesionCliente
     *            the new sesion cliente
     */
    public void setSesionCliente(SesionCliente sesionCliente) {
        this.sesionCliente = sesionCliente;
    }

    /**
     * Gets the sesion paramatros.
     *
     * @return the sesion paramatros
     */
    public SesionParametros getSesionParamatros() {
        return sesionParamatros;
    }

    /**
     * Sets the sesion paramatros.
     *
     * @param sesionParamatros
     *            the new sesion paramatros
     */
    public void setSesionParamatros(SesionParametros sesionParamatros) {
        this.sesionParamatros = sesionParamatros;
    }

}
