/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;

/**
 * The Class RespuestaBOImpl.
 */
@Component
public class RespuestaBOImpl implements RespuestaBO {

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.bo.RespuestaBO#armarRespuestaWarning(ar.
	 * com.santanderrio.base.respuesta.entities.Respuesta,
	 * ar.com.santanderrio.base.respuesta.entities.ItemMensajeRespuesta,
	 * java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaWarning(Respuesta<E> respuesta, ItemMensajeRespuesta itemMensajeRespuesta,
			String codigoMensaje) {
		Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(codigoMensaje);
		itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		itemMensajeRespuesta.setTipoError(TipoError.SIN_MOVIMIENTOS.getDescripcion());
		respuesta.add(itemMensajeRespuesta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuesta.setRespuestaVacia(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.bo.RespuestaBO#armarRespuestaError(ar.com
	 * .santanderrio.base.respuesta.entities.Respuesta,
	 * ar.com.santanderrio.base.respuesta.entities.ItemMensajeRespuesta,
	 * java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaError(Respuesta<E> respuesta, ItemMensajeRespuesta itemMensajeRespuesta,
			String codigoMensaje) {
		Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(codigoMensaje);
		itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_CONSULTA_MOVIMIENTOS.getDescripcion());
		respuesta.add(itemMensajeRespuesta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.bo.RespuestaBO#armarRespuestaOk(ar.com.
	 * santanderrio.base.respuesta.entities.Respuesta, java.lang.Object)
	 */
	@Override
	public <E> void armarRespuestaOk(Respuesta<E> respuesta, E detalleUltimosMovimientos) {
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(detalleUltimosMovimientos);
		respuesta.setRespuestaVacia(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO#
	 * armarRespuestaWarning(ar.com.santanderrio.obp.base.respuesta.entities.
	 * Respuesta, ar.com.santanderrio.obp.base.respuesta.entities.TipoError,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaWarning(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje,
			String tag) {
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(codigoMensaje);
		itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
		itemMensajeRespuesta.setTag(tag);
		respuesta.add(itemMensajeRespuesta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuesta.setRespuestaVacia(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO#
	 * armarRespuestaError(ar.com.santanderrio.obp.base.respuesta.entities.
	 * Respuesta, ar.com.santanderrio.obp.base.respuesta.entities.TipoError,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaError(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje, String tag) {
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(codigoMensaje);
		itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
		itemMensajeRespuesta.setTag(tag);
		respuesta.add(itemMensajeRespuesta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO#
	 * armarRespuestaWarning(ar.com.santanderrio.obp.base.respuesta.entities.
	 * Respuesta, java.util.List, java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaWarning(Respuesta<E> respuesta, List<ItemMensajeRespuesta> itemsMensajeRespuesta,
			String codigoMensaje) {
		respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuesta.setRespuestaVacia(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO#
	 * armarRespuestaErrorParametrizado(ar.com.santanderrio.obp.base.respuesta.
	 * entities.Respuesta,
	 * ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError,
	 * java.lang.String, java.lang.String, java.lang.String[])
	 */
	@Override
	public <E> void armarRespuestaErrorParametrizado(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje,
			String tag, String[] params) {

		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		Mensaje mensaje = mensajeDAO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_HORARIO_DE_OPERACIONES);
		MessageFormat format = new MessageFormat(mensaje.getMensaje());
		itemMensajeRespuesta.setMensaje(format.format(params));
		itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
		itemMensajeRespuesta.setTag(tag);
		respuesta.add(itemMensajeRespuesta);
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

	}
}
