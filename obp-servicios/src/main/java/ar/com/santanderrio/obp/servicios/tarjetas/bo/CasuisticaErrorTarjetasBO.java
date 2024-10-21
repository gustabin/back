/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ErrorTarjetasEnum;

/**
 * The Interface CasuisticaErrorTarjetasBO.
 *
 * @author sabrina.cis
 */
public interface CasuisticaErrorTarjetasBO {

	/**
	 * Es respuesta OK.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta);

	/**
	 * Es respuesta warning.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	Boolean esRespuestaWarning(EstadoRespuesta estadoRespuesta);

	/**
	 * Es respuesta error.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	Boolean esRespuestaError(EstadoRespuesta estadoRespuesta);

	/**
	 * Crear respuesta ok.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaOk(Class<T> respuestaClass, T data);

	/**
	 * Crear respuesta ok.
	 *
	 * @param <T>
	 *            the generic type
	 * @param class1
	 *            the class 1
	 * @return the respuesta
	 */
	<T> Respuesta<Void> crearRespuestaOk(Class<Void> class1);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param error
	 *            the error
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(ErrorTarjetasEnum error);

	/**
	 * Crear respuesta warning.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @param items
	 *            the items
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, T data, List<ItemMensajeRespuesta> items);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param items
	 *            the items
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, List<ItemMensajeRespuesta> items);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param descripcionTag
	 *            the descripcion tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(String descripcionTag, TipoError tipoError, String codigoMensaje);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @param error
	 *            the error
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, T data, ErrorTarjetasEnum error);

}
