/*
 * 
 */
package ar.com.santanderrio.obp.servicios.factory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.bval.jsr303.ApacheValidationProvider;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * A factory for creating Respuesta objects.
 */
@Component
public class RespuestaFactory {

	/** The Mensaje dao. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The validator. */
	private Validator validator;

	/**
	 * Validator init.
	 */
	@PostConstruct
	private void validatorInit() {
		ValidatorFactory validatorFactory = Validation.byProvider(ApacheValidationProvider.class).configure()
				.buildValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	/**
	 * Crear respuesta ok.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaOk(Class<T> respuestaClass) {
		return respuestaOk(null);
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
	public <T> Respuesta<T> crearRespuestaOk(Class<T> respuestaClass, T data) {
		return respuestaOk(data);
	}

	/**
	 * Crear respuesta ok.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaOk(T data) {
		return respuestaOk(data);
	}

	/**
	 * Crear respuesta ok con item de mensaje correspondiente al codigoMensaje.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param tag
	 *            the tag
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaOk(T data, String tag, String codigoMensaje) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.OK);
		rta.setRespuesta(data);
		rta.setRespuestaVacia(false);
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, TipoError.OK, codigoMensaje);
		rta.add(item);
		return rta;
	}

	/**
	 * Crear respuesta error.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param msg
	 *            the msg
	 * @param tag
	 *            the tag
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, String msg, String tag) {
		return generoRespuestaConEstadoTipoError(msg, tag, EstadoRespuesta.ERROR, TipoError.ERROR_GENERICO);
	}

	/**
	 * Crear respuesta error con error personalizado. Igual al anterior, pero
	 * pasandole un tipo de error personalizado (para no seguir engrosando el
	 * enum TipoError)
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param msg
	 *            the msg
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */

	public <T> Respuesta<T> crearRespuestaErrorPersonalizado(Class<T> respuestaClass, String msg, String tipoError) {
		return generoRespuestaConEstadoTipoErrorPersonalizado(msg, EstadoRespuesta.ERROR, tipoError);
	}

	/**
	 * Crear respuesta error personalizado.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaErrorPersonalizado(Class<T> respuestaClass,
			List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
		return generoRespuestaConEstadoTipoErrorPersonalizado(EstadoRespuesta.ERROR, itemsMensajeRespuesta);
	}

	/**
	 * Genero respuesta con estado tipo error personalizado.
	 *
	 * @param <T>
	 *            the generic type
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @return the respuesta
	 */
	private <T> Respuesta<T> generoRespuestaConEstadoTipoErrorPersonalizado(EstadoRespuesta estadoRespuesta,
			List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(estadoRespuesta);
		rta.setRespuestaVacia(true);
		for (ItemMensajeRespuesta item : itemsMensajeRespuesta) {
			item.setMensaje(item.getMensaje());
			item.setTipoError(item.getTipoError());
			item.setTag(null);
			rta.add(item);
		}
		return rta;
	}

	/**
	 * Crear respuesta error con error personalizado. Igual al anterior, pero
	 * pasandole un tipo de error personalizado (para no seguir engrosando el
	 * enum TipoError)
	 * 
	 * @param <T>
	 *            the generic type
	 * @param msg
	 *            the msg
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaErrorPersonalizadoSinClase(String msg, String tipoError) {
		return generoRespuestaConEstadoTipoErrorPersonalizado(msg, EstadoRespuesta.ERROR, tipoError);
	}

	/**
	 * Crear respuesta error personalizado. Pasandole un tipo de error
	 * personalizado.
	 *
	 * @param <T>
	 *            the generic type
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaErrorConTipoErrorPersonalizado(String tipoError, String codigoMensaje) {
		return generoRespuestaConEstadoTipoErrorPersonalizado(buscarMensaje(codigoMensaje), EstadoRespuesta.ERROR,
				tipoError);
	}

	/**
	 * Crear respuesta warning.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param msg
	 *            the msg
	 * @param tag
	 *            the tag
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, String msg, String tag) {
		return generoRespuestaConEstadoTipoError(msg, tag, EstadoRespuesta.WARNING, TipoError.WARNING);
	}

	/**
	 * Crear respuesta warning con error personalizado. Al igual que el
	 * anterior, la idea es no seguir aumentando el enum TipoError
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param msg
	 *            the msg
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarningConTipoErrorPersonalizado(Class<T> respuestaClass, String msg,
			String tipoError) {
		return generoRespuestaConEstadoTipoErrorPersonalizado(msg, EstadoRespuesta.WARNING, tipoError);
	}

	/**
	 * Se genera un rta WARNINGOK, porq UI necesita diferecias de un warning
	 * comun.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param msg
	 *            the msg
	 * @param tag
	 *            the tag
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarningOk(Class<T> respuestaClass, String msg, String tag) {
		return generoRespuestaConEstadoTipoError(msg, tag, EstadoRespuesta.WARNING, TipoError.WARNINGOK);
	}

	/**
	 * Crear respuesta error.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param itemMensajes
	 *            the item mensajes
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, List<ItemMensajeRespuesta> itemMensajes) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.setItemMensajeRespuesta(itemMensajes);
		return rta;
	}

	/**
	 * Valida un bean, si tiene violaciones de restricciones se genera la
	 * respues correspondiente.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param bean
	 *            the bean
	 * @return the respuesta
	 */
	public <T> Respuesta<T> validate(T bean) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
		Respuesta<T> respuesta = new Respuesta<T>();

		if (CollectionUtils.isNotEmpty(constraintViolations)) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);

			for (ConstraintViolation<T> cv : constraintViolations) {
				ItemMensajeRespuesta item = new ItemMensajeRespuesta(cv.getMessage());
				item.setTag(cv.getPropertyPath().toString());
				respuesta.add(item);
			}
		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		}

		return respuesta;
	}

	/**
	 * Metodos Privados.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	private <T> Respuesta<T> respuestaOk(T data) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.OK);
		rta.setRespuesta(data);
		return rta;
	}

	/**
	 * Crea una Respuesta de estado ERROR con item de mensaje correspondiente al
	 * tipoError y codigoMensaje.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(String tag, TipoError tipoError, String codigoMensaje) {
		Respuesta<T> rta = new Respuesta<T>();
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, tipoError, codigoMensaje);
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.add(item);
		return rta;
	}

	/**
	 * Crear respuesta error.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param parametros
	 *            the parametros
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(String tag, TipoError tipoError, String codigoMensaje,
			String... parametros) {
		Respuesta<T> rta = new Respuesta<T>();
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, tipoError, codigoMensaje);
		item.setMensaje(MessageFormat.format(this.buscarMensaje(codigoMensaje), parametros));
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.add(item);
		return rta;
	}

	/**
	 * Crea una Respuesta de estado ERROR con item de mensaje correspondiente al
	 * tipoError y codigoMensaje.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, String tag, TipoError tipoError,
			String codigoMensaje) {
		Respuesta<T> rta = new Respuesta<T>();
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, tipoError, codigoMensaje);
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.add(item);
		return rta;
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
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, T data, String tag, TipoError tipoError,
			String codigoMensaje) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setRespuesta(data);
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, tipoError, codigoMensaje);
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.add(item);
		return rta;
	}

	/**
	 * Crea una Respuesta de estado WARNING con item de mensaje correspondiente
	 * al tipoError y codigoMensaje.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarning(String tag, TipoError tipoError, String codigoMensaje) {
		Respuesta<T> rta = new Respuesta<T>();
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, tipoError, codigoMensaje);
		rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rta.setRespuestaVacia(false);
		rta.add(item);
		return rta;
	}

	/**
	 * Crea una Respuesta de estado WARNING con una respuesta y una lista de
	 * items de mensajes.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 * @return the respuesta
	 */
	//TODO: CHECK IF THIS IS SAFE TO REMOVE ( UNUSED ARGUMENTS)
	public <T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, T data,
			List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setRespuesta(data);
		rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rta.setRespuestaVacia(false);
		rta.setItemMensajeRespuesta(itemsMensajeRespuesta);
		return rta;
	}

	/**
	 * Crea una respuesta warning, a partir de una lista de DatoItemMensaje que
	 * contiene la informacion para obtener los mensajes de base de datos y
	 * armar los items mensaje respuesta.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param datos
	 *            the datos
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarning(List<DatoItemMensaje> datos, T data) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rta.setRespuestaVacia(false);
		rta.setItemMensajeRespuesta(crearItemsMensajeRespuesta(datos));
		rta.setRespuesta(data);
		return rta;
	}

	/**
	 * Crear respuesta warning.
	 *
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param items
	 *            the items
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarning(T data, List<ItemMensajeRespuesta> items) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rta.setRespuestaVacia(false);
		rta.setItemMensajeRespuesta(items);
		rta.setRespuesta(data);
		return rta;
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
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(Class<T> respuestaClass, T data, List<DatoItemMensaje> datos) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setRespuesta(data);
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.setItemMensajeRespuesta(crearItemsMensajeRespuesta(datos));
		rta.setRespuesta(data);
		return rta;
	}

	/**
	 * Crea respuesta warning.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarning(T data, String tag, TipoError tipoError, String codigoMensaje) {
		Respuesta<T> respuesta = new Respuesta<T>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuesta.setRespuestaVacia(false);
		respuesta.setRespuesta(data);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(tag);
		item.setTipoError(tipoError.getDescripcion());
		item.setMensaje(buscarMensaje(codigoMensaje));
		respuesta.add(item);
		return respuesta;

	}

	/**
	 * Crea una respuesta Error, a partir de una lista de DatoItemMensaje que
	 * contiene la informacion para obtener los mensajes de base de datos y
	 * armar los items mensaje respuesta.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(List<DatoItemMensaje> datos) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.setItemMensajeRespuesta(crearItemsMensajeRespuesta(datos));
		return rta;
	}

	/**
	 * Crea una respuesta Error, a partir de una lista de DatoItemMensaje que
	 * contiene la informacion para obtener los mensajes de base de datos y
	 * armar los items mensaje respuesta.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param datos
	 *            the datos
	 * @param data
	 *            the data
	 * @param respuestaVacia
	 *            the respuesta vacia
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(List<DatoItemMensaje> datos, T data, Boolean respuestaVacia) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(respuestaVacia);
		rta.setRespuesta(data);
		rta.setItemMensajeRespuesta(crearItemsMensajeRespuesta(datos));
		return rta;
	}

	/**
	 * Crear items mensaje respuesta.
	 * 
	 * @param datos
	 *            the datos
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> crearItemsMensajeRespuesta(List<DatoItemMensaje> datos) {
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		for (DatoItemMensaje dato : datos) {
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(buscarMensaje(dato.getCodigoMensaje()));
			item.setTag(dato.getTag());
			item.setTipoError(dato.getTipoError().getDescripcion());
			items.add(item);
		}
		return items;
	}
	
	/**
	 * Obtener list item mensaje.
	 *
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param tipoError
	 *            the tipo error
	 * @return the list
	 */
	public List<ItemMensajeRespuesta> obtenerListItemMensaje(String codigoMensaje, String tipoError) {
		Mensaje mensaje = this.mensajeBO.obtenerMensajePorCodigoConErrorGenerico(codigoMensaje);
		List<ItemMensajeRespuesta> mensajesRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
		itemMensaje.setMensaje(mensaje.getMensaje());
		itemMensaje.setTipoError(tipoError);
		mensajesRespuesta.add(itemMensaje);
		return mensajesRespuesta;
	}

	/**
	 * Genero respuesta con estado tipo error.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param msg
	 *            the msg
	 * @param tag
	 *            the tag
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */
	private <T> Respuesta<T> generoRespuestaConEstadoTipoError(String msg, String tag, EstadoRespuesta estadoRespuesta,
			TipoError tipoError) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(estadoRespuesta);
		rta.setRespuestaVacia(true);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(msg);
		item.setTipoError(tipoError.getDescripcion());
		item.setTag(tag);
		rta.add(item);
		return rta;
	}

	/**
	 * Genero respuesta con estado tipo error personalizado. La idea es no
	 * engrosar mas el enum TipoError.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param msg
	 *            the msg
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */
	private <T> Respuesta<T> generoRespuestaConEstadoTipoErrorPersonalizado(String msg, EstadoRespuesta estadoRespuesta,
			String tipoError) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(estadoRespuesta);
		rta.setRespuestaVacia(true);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(msg);
		item.setTipoError(tipoError);
		item.setTag(null);
		rta.add(item);
		return rta;
	}

	/**
	 * Genera ItemMensajeRespuesta, obteniendo desde el mensajeDAO el mensaje de
	 * error que se carga en el item.
	 * 
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the item mensaje respuesta
	 */
	public ItemMensajeRespuesta crearItemMensajeRespuesta(String tag, TipoError tipoError, String codigoMensaje) {

		String mensaje = "";
		if (!StringUtils.isEmpty(codigoMensaje)) {
			mensaje = buscarMensaje(codigoMensaje);
		}
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(tipoError.getDescripcion());
		itemMensajeRespuesta.setTag(tag);
		itemMensajeRespuesta.setMensaje(mensaje);
		return itemMensajeRespuesta;
	}

	/**
	 * Genera ItemMensajeRespuesta, obteniendo desde el mensajeDAO el mensaje de
	 * error que se carga en el item.
	 * 
	 * @param tag
	 *            the tag
	 * @param detalle
	 *            the detalle
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta crearItemMensajeRespuesta(String tag, String detalle, String codigoMensaje) {
		String mensaje = buscarMensaje(codigoMensaje);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setDetalleTipoError(detalle);
		itemMensajeRespuesta.setTag(tag);
		itemMensajeRespuesta.setMensaje(mensaje);
		itemMensajeRespuesta.setTipoError(detalle);
		return itemMensajeRespuesta;
	}

	/**
	 * Crea una Respuesta de estado ERROR con item de mensaje correspondiente al
	 * tipoError y codigoMensaje.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaError(String tag, String tipoError, String codigoMensaje) {
		Respuesta<T> rta = new Respuesta<T>();
		ItemMensajeRespuesta item = crearItemMensajeRespuesta(tag, tipoError, codigoMensaje);
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuestaVacia(true);
		rta.add(item);
		return rta;
	}

	/**
	 * Retorna el mensaje de error desde el dao a partir de un codigo de error.
	 * 
	 * @param codigoError
	 *            the codigo error
	 * @return the string
	 */
	private String buscarMensaje(String codigoError) {
		String mensaje = "";
		if (StringUtils.isNotEmpty(codigoError)) {

			Mensaje msg = mensajeBO.obtenerMensajePorCodigo(codigoError);
			mensaje = msg.getMensaje();

		}
		return mensaje;
	}

	/**
	 * Generar dato item mensaje.
	 * 
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the dato item mensaje
	 */
	public DatoItemMensaje generarDatoItemMensaje(String tag, TipoError tipoError, String codigoMensaje) {
		DatoItemMensaje datoItemMensaje = new DatoItemMensaje();
		datoItemMensaje.setCodigoMensaje(codigoMensaje);
		datoItemMensaje.setTag(tag);
		datoItemMensaje.setTipoError(tipoError);
		return datoItemMensaje;
	}

	/**
	 * Generar item mensaje respuesta.
	 *
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the item mensaje respuesta
	 */
	public ItemMensajeRespuesta generarItemMensajeRespuesta(String tag, TipoError tipoError, String codigoMensaje) {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(tag);
		item.setTipoError(tipoError.getDescripcion());
		item.setMensaje(buscarMensaje(codigoMensaje));
		return item;
	}

	/**
	 * Crear respuesta warning con tipo error personalizado.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param tag
	 *            the tag
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarningConTipoErrorPersonalizado(T data, String tipoError,
			String codigoMensaje, String tag) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rta.setRespuestaVacia(false);
		rta.setRespuesta(data);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(tag);
		item.setTipoError(tipoError);
		item.setMensaje(buscarMensaje(codigoMensaje));
		rta.add(item);
		return rta;
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
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @param tag
	 *            the tag
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaWarning(Class<T> respuestaClass, T data, TipoError tipoError,
			String codigoMensaje, String tag) {
		Respuesta<T> rta = new Respuesta<T>();
		rta.setRespuesta(data);
		rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rta.setRespuestaVacia(false);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(tag);
		item.setTipoError(tipoError.getDescripcion());
		item.setMensaje(buscarMensaje(codigoMensaje));
		rta.add(item);
		if (data == null) {
			rta.setRespuestaVacia(true);
		}
		rta.setRespuesta(data);
		return rta;
	}

	/**
	 * Transforma una respuesta a otra.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param origin
	 *            the origin
	 * @return the respuesta
	 */
	public <T> Respuesta<T> transformar(T data, Respuesta<?> origin) {
		Respuesta<T> respuesta = new Respuesta<T>();

		respuesta.setEstadoRespuesta(origin.getEstadoRespuesta());
		if (CollectionUtils.isNotEmpty(origin.getItemsMensajeRespuesta())) {
			respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>(origin.getItemsMensajeRespuesta()));
		}
		respuesta.setRespuestaVacia(origin.isRespuestaVacia());
		respuesta.setRespuesta(data);
		return respuesta;
	}

}
