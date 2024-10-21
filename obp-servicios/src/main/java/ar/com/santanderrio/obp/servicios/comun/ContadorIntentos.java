/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.TitulosManagerImpl;

/**
 * The Class ContadorIntentos.
 */
@Component
@Scope("prototype")
public class ContadorIntentos {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContadorIntentos.class);

	/** The max intentos properties. */
	@Value("${OBP.INTENTOS.MAXIMO}")
	private String maxIntentosProperties;

	/** The Constant MAX_INTENTOS_DEFAULT. */
	private static final int MAX_INTENTOS_DEFAULT = 3;

	/** The Constant ERROR_REINTENTOS. */
	private static final String ERROR_REINTENTOS = "Ha ocurrido un error al calcular los reintentos";

	/** The Constant WARNING_REINTENTOS. */
	private static final String WARNING_REINTENTOS = "El id de sesion almacenado es distinto al id de sesion que desea contabilizar.";

	/** The max intentos. */
	private int maxIntentos;

	/** The contador. */
	private int contador;

	/** The id view. */
	private String idView;

	/** The msg error. */
	private String msgError;

	/** The estado. */
	private boolean estado = true;

	/**
	 * The Contador Intentos Enum Cuando hay mas de un contador en simultaneo,
	 * el enum define de que flujo es cada uno.
	 */
	private ContadorIntentosEnum contadorIntentosEnum;

	/**
	 * The Contador Intentos Se utiliza cuando se necesita mas de un contador en
	 * simultaneo.
	 */
	private ContadorIntentos contadorSiguiente;

	/**
	 * Constructor por default. Obtiene el número máximo de intentos desde el
	 * archivo de configuración.
	 */
	public ContadorIntentos() {
		init();
	}

	/**
	 * Constructor para definir el número máximo de intentos.
	 * 
	 * @param maxIntentos
	 *            numero mayor a 0
	 */
	public ContadorIntentos(int maxIntentos) {
		this.maxIntentos = maxIntentos <= 0 ? MAX_INTENTOS_DEFAULT : maxIntentos;

	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		if (maxIntentosProperties == null || maxIntentosProperties.trim().isEmpty()) {
			this.maxIntentos = MAX_INTENTOS_DEFAULT;
		} else {
			this.maxIntentos = Integer.parseInt(maxIntentosProperties);
		}
	}

	/**
	 * Incrementa el contador en una unidad si no alcanzo el maximo.
	 * 
	 * @return True si permite reintentar, false si alcanzó el maximo de
	 *         intentos
	 */
	public boolean permiteReintentar() {
		contador = contador + 1;
		if (this.excedeReintentos()) {
			return false;
		}
		return true;
	}

	/**
	 * Excede reintentos.
	 *
	 * @return true, if successful
	 */
	public boolean excedeReintentos() {
		return this.contador > maxIntentos;
	}

	/**
	 * Excede reintentos. Valida si excedes los reintentos para el id de sesion
	 * pasado como parametro. Es necesario cada vez que se desee contabilizar un
	 * reintento, resetear antes el contador ContadorIntentos#reset(). Si el
	 * idView almacenado difiere del idSesion validado se almacena el nuevo id y
	 * se resta 1 intento.
	 *
	 * @author emilio.watemberg
	 * @param idSesion
	 *            the id sesion
	 * @return true, if successful
	 */
	private boolean excedeReintentos(String idSesion) {
		if (idSesion.equals(this.idView)) {
			return this.contador > maxIntentos;
		} else {
			idView = idSesion;
			permiteReintentar();
			LOGGER.warn(WARNING_REINTENTOS);
			return this.contador > maxIntentos;
		}
	}

	/**
	 * Excede reintentos.
	 *
	 * @param <T>
	 *            the generic type
	 * @param idSesion
	 *            the id de la vista que representa la sesion
	 * @param respuesta
	 *            obtenida del BO
	 * @return true, if successful
	 */
	public <T> Respuesta<T> excedeReintentos(String idSesion, Respuesta<T> respuesta) {
		if (idSesion != null) {
			if (!excedeReintentos(idSesion)) {
				permiteReintentar();
				return respuesta;
			} else {
				if (respuesta.getItemsMensajeRespuesta() != null) {
					respuesta.getItemsMensajeRespuesta().clear();
				}
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
				respuesta.setRespuestaVacia(true);
				ItemMensajeRespuesta item = new ItemMensajeRespuesta();
				item.setMensaje(msgError);
				item.setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
				item.setTag(null);
				respuesta.add(item);
				return respuesta;
			}
		} else {
			reset();
			LOGGER.debug(ERROR_REINTENTOS);
			throw new RobotException(ERROR_REINTENTOS);
		}

	}

	/**
	 * Reset.
	 */
	public void reset() {
		this.contador = 0;
		this.idView = null;
	}

	/**
	 * Gets the id view.
	 *
	 * @return the id view
	 */
	public String getIdView() {
		return idView;
	}

	/**
	 * Sets the id view.
	 *
	 * @param idView
	 *            the new id view
	 * @param maximoReintentos
	 *            the maximo reintentos
	 * @param msgError
	 *            the msg error
	 */
	public void setIdView(String idView, int maximoReintentos, String msgError) {
		if (this.idView == null || !this.idView.equals(idView)) {
			this.idView = idView;
			this.contador = 1;
			this.maxIntentos = maximoReintentos;
			this.msgError = msgError;
		}
	}

	/**
	 * Sets the id view con un Enum como parametro. Setea el contador en 0
	 * 
	 * ver como se implementa en
	 * {@link TitulosManagerImpl#configuracionOrdenesCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)}
	 *
	 * @param contadorIntentosEnum
	 *            the contador intentos enum
	 * @param maximoReintentos
	 *            the maximo reintentos
	 * @param msgError
	 *            the msg error
	 */
	public void setIdView(ContadorIntentosEnum contadorIntentosEnum, int maximoReintentos, String msgError) {
		this.contadorIntentosEnum = contadorIntentosEnum;
		this.contador = 0;
		this.maxIntentos = maximoReintentos;
		this.msgError = msgError;
	}

	/**
	 * Gets the contador siguiente.
	 *
	 * @return the contador siguiente
	 */
	public ContadorIntentos getContadorSiguiente() {
		return contadorSiguiente;
	}

	/**
	 * Sets the contador siguiente.
	 *
	 * @param contadorSiguiente
	 *            the new contador siguiente
	 */
	public void setContadorSiguiente(ContadorIntentos contadorSiguiente) {
		this.contadorSiguiente = contadorSiguiente;
	}

	/**
	 * Gets the contador.
	 *
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * Sets the contador.
	 *
	 * @param contador
	 *            the new contador
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}

	/**
	 * Metodo que devuelve el contador correpondiente al flujo. En caso de que
	 * no haya un contador para ese flujo, devuelve un contador sin intentos
	 * para que no se pueda continuar
	 *
	 * @param contadorIntentosEnum
	 *            the contador intentos enum
	 * @return the contador intentos
	 */
	public ContadorIntentos gestionarContador(ContadorIntentosEnum contadorIntentosEnum) {
		return gestionarContador(contadorIntentosEnum, Boolean.TRUE);
	}

	/**
	 * Metodo que devuelve el contador correpondiente al flujo. En caso de que
	 * no haya un contador para ese flujo, devuelve un contador sin intentos
	 * para que no se pueda continuar
	 *
	 * @param contadorIntentosEnum
	 *            the contador intentos enum
	 * @param estado
	 *            the estado
	 * @return the contador intentos
	 */
	public ContadorIntentos gestionarContador(ContadorIntentosEnum contadorIntentosEnum, boolean estado) {
		if (contadorIntentosEnum.equals(this.contadorIntentosEnum) && Boolean.valueOf(this.isEstado()).equals(estado)) {
			return this;
		} else if (contadorSiguiente != null) {
			return contadorSiguiente.gestionarContador(contadorIntentosEnum, estado);
		}
		return new ContadorIntentos(0);
	}

	/**
	 * Checks if is estado.
	 *
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
