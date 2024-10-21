/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.service;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Interface RespuestaService.
 */
@Deprecated // usar RespuestaFactory.
public interface RespuestaService {

	/**
	 * Armar respuesta error.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 * @param itemMensajeRespuesta
	 *            the item mensaje respuesta
	 * @param codigoMensaje
	 *            the codigo mensaje
	 */
	<E> void armarRespuestaError(Respuesta<E> respuesta, ItemMensajeRespuesta itemMensajeRespuesta,
			String codigoMensaje);

	/**
	 * Armar respuesta ok.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 * @param detalleUltimosMovimientos
	 *            the detalle ultimos movimientos
	 */
	<E> void armarRespuestaOk(Respuesta<E> respuesta, E detalleUltimosMovimientos);

	/**
	 * Armar respuesta error.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param tag
	 *            the tag
	 */
	<E> void armarRespuestaError(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje, String tag);

	/**
	 * Armar respuesta warning.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param tag
	 *            the tag
	 */
	<E> void armarRespuestaWarning(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje, String tag);

}
