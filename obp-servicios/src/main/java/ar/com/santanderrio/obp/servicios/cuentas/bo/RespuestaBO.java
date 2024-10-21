/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Interface RespuestaBO.
 */
@Deprecated // usar RespuestaFactory
public interface RespuestaBO {

	/**
	 * Armar respuesta warning.
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
	<E> void armarRespuestaWarning(Respuesta<E> respuesta, ItemMensajeRespuesta itemMensajeRespuesta,
			String codigoMensaje);

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

	/**
	 * Armar respuesta warning.
	 *
	 * @param <E>
	 *            the element type
	 * @param respuesta
	 *            the respuesta
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @param codigoMensaje
	 *            the codigo mensaje
	 */
	<E> void armarRespuestaWarning(Respuesta<E> respuesta, List<ItemMensajeRespuesta> itemsMensajeRespuesta,
			String codigoMensaje);

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
	 * Armar respuesta error parametrizado.
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
	 * @param params
	 *            the params
	 */
	<E> void armarRespuestaErrorParametrizado(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje,
			String tag, String[] params);
}
