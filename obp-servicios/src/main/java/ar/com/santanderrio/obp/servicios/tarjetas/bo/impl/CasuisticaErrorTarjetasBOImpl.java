/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ErrorTarjetasEnum;

/**
 * The Class CasuisticaErrorTarjetasBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class CasuisticaErrorTarjetasBOImpl implements CasuisticaErrorTarjetasBO {

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * Es respuesta OK.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.OK);
	}

	/**
	 * Es respuesta warning.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaWarning(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.WARNING);
	}

	/**
	 * Es respuesta error.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaError(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.ERROR);
	}

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
	@Override
	public <T> Respuesta<T> crearRespuestaOk(Class<T> respuestaClass, T data) {
		return respuestaFactory.crearRespuestaOk(respuestaClass, data);
	}

	/**
	 * Crear respuesta ok.
	 *
	 * @param <T>
	 *            the generic type
	 * @param class1
	 *            the class 1
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<Void> crearRespuestaOk(Class<Void> class1) {
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

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
	@Override
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, T data, ErrorTarjetasEnum error) {
		return respuestaFactory.crearRespuestaError(respuestaClass, data, error.getTag().getDescripcionTag(),
				error.getTipoError(), error.getCodigoMensaje());
	}

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param error
	 *            the error
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaError(ErrorTarjetasEnum error) {
		return respuestaFactory.crearRespuestaError(error.getTag().getDescripcionTag(), error.getTipoError(),
				error.getCodigoMensaje());
	}

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
	@Override
	public <T> Respuesta<T> crearRespuestaError(String descripcionTag, TipoError tipoError, String codigoMensaje) {
		return respuestaFactory.crearRespuestaError(descripcionTag, tipoError, codigoMensaje);
	}

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
	@Override
	public <T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, T data, List<ItemMensajeRespuesta> items) {
		return respuestaFactory.crearRespuestaWarning(respuestaClass, data, items);
	}

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
	@Override
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, List<ItemMensajeRespuesta> items) {
		return respuestaFactory.crearRespuestaError(respuestaClass, items);
	}

}
