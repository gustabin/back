/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.service.RespuestaService;

/**
 * The Class RespuestaServiceImpl.
 */
@Component
public class RespuestaServiceImpl implements RespuestaService {

	/** The respuesta bo. */
	@Autowired
	private RespuestaBO respuestaBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.example.bienvenida.service.RespuestaService#
	 * armarRespuestaError(ar.com.santanderrio.base.respuesta.entities.
	 * Respuesta,
	 * ar.com.santanderrio.base.respuesta.entities.ItemMensajeRespuesta,
	 * java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaError(Respuesta<E> respuesta, ItemMensajeRespuesta itemMensajeRespuesta,
			String codigoMensaje) {
		respuestaBO.armarRespuestaError(respuesta, itemMensajeRespuesta, codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.example.bienvenida.service.RespuestaService#
	 * armarRespuestaOk(ar.com.santanderrio.base.respuesta.entities.Respuesta,
	 * java.lang.Object)
	 */
	@Override
	public <E> void armarRespuestaOk(Respuesta<E> respuesta, E detalleUltimosMovimientos) {
		respuestaBO.armarRespuestaOk(respuesta, detalleUltimosMovimientos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.service.RespuestaService#armarRespuestaError
	 * (ar.com.santanderrio.obp.base.respuesta.entities.Respuesta,
	 * ar.com.santanderrio.obp.base.respuesta.entities.TipoError,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaError(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje, String tag) {
		respuestaBO.armarRespuestaError(respuesta, tipoError, codigoMensaje, tag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.service.RespuestaService#
	 * armarRespuestaWarning(ar.com.santanderrio.obp.base.respuesta.entities.
	 * Respuesta, ar.com.santanderrio.obp.base.respuesta.entities.TipoError,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public <E> void armarRespuestaWarning(Respuesta<E> respuesta, TipoError tipoError, String codigoMensaje,
			String tag) {
		respuestaBO.armarRespuestaWarning(respuesta, tipoError, codigoMensaje, tag);
	}

}
