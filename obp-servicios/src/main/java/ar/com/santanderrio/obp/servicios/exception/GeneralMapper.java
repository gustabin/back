/*
 * 
 */
package ar.com.santanderrio.obp.servicios.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * Todo error no manejado sale por aca.
 * 
 * Clase GeneralMapper.
 */
@Provider
public class GeneralMapper implements ExceptionMapper<Throwable> {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralMapper.class);

	/** The Constant MENSAJE_ERROR. */
	public final static String MENSAJE_ERROR = "Ocurrió un error y no podemos procesar tu solicitud en este momento.";

	/** The Constant MENSAJE_ERROR_VALIDACION. */
	public final static String MENSAJE_ERROR_VALIDACION = "Ah ocurrido un error al validar el request";

	/**
	 * Si llega hasta aca devuelvo un error (en httpcode 200) con un mensaje
	 * indicando que se dio una situacion no deseada.
	 *
	 * @param throwable
	 *            Variable throwable
	 * @return Variable response
	 */
	public Response toResponse(Throwable throwable) {
		LOGGER.error(throwable.getMessage(), throwable);

		Respuesta<String> respuestaError = new Respuesta<String>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(MENSAJE_ERROR);
		item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		respuestaError.add(item);

		// crear respuesta con error
		return Response.status(Response.Status.OK).entity(respuestaError)
				.header("Content-Type", MediaType.APPLICATION_JSON).build();
	}

}
