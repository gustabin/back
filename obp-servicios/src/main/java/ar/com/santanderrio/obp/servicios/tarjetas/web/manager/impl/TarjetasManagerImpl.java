/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.TarjetasManager;

/**
 * The Class TarjetasManagerImpl.
 *
 * @author sabrina.cis
 */
public class TarjetasManagerImpl implements TarjetasManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetasManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant ERROR_ESTADISTICA. */
	public static final String ERROR_ESTADISTICA = "Error al grabar estadistica ";

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "-";

	/** The Constant LOGGER_ERROR. */
	private static final String LOGGER_ERROR = null;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion tarjetas. */
	@Autowired
	protected SesionTarjetas sesionTarjetas;

	/** The tarjeta BO. */
	@Autowired
	private SelectorYCabeceraBO selectorYCabeceraBO;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The casuistica. */
	@Autowired
	protected CasuisticaErrorTarjetasBO casuistica;

	/**
	 * Obtener numero cuenta.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @return the string
	 */
	public String obtenerNumeroCuenta(String nroSucursal, String nroCuentaProducto) {
		try {
			String sucursal = ISBANStringUtils.formatearSucursal(nroSucursal);
			String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(nroCuentaProducto);
			return sucursal + SEPARATOR + numeroCuenta;
		} catch (NumberFormatException e) {
			LOGGER.error(LOGGER_ERROR, e);
		}
		return null;
	}

	/**
	 * Obtener item mensaje respuesta de cliente.
	 *
	 * @return the list
	 */
	public List<ItemMensajeRespuesta> obtenerItemMensajeRespuestaDeCliente() {
		LOGGER.info("Recuperando Cliente de la Sesion ...");
		return sesionCliente.getItemsRespuesta();
	}

	/**
	 * Dado un nro de cuenta genera un identificador con el nro de sucursal y
	 * nro cuenta producto.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the identificacion cuenta
	 */
	@Override
	public IdentificacionCuenta obtenerIdCuenta(String identificacionCuenta) {
		return new IdentificacionCuenta(identificacionCuenta);
	}

	/**
	 * Obtener identificacion cuenta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the identificacion cuenta
	 * @throws BusinessException
	 *             the business exception
	 */
	public String obtenerMarcaDeTarjeta(IdentificacionCuenta identificacionCuenta) throws BusinessException {
		return selectorYCabeceraBO.obtenerMarcaDeTarjeta(identificacionCuenta, sesionCliente.getCliente());
	}

	/**
	 * Obtener cuentas.
	 *
	 * @return the list
	 */
	public List<Cuenta> obtenerCuentas() {
		return sesionCliente.getCliente().getCuentas();
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	public Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.OK);
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	public <T> Boolean esRespuestaOK(Respuesta<T> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

	/**
	 * Es respuesta WARNING.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	public Boolean esRespuestaWARNING(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.WARNING);
	}

	/**
	 * Es respuesta WARNING.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	public <T> Boolean esRespuestaWARNING(Respuesta<T> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING);
	}

	/**
	 * Es respuesta ERROR.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	public Boolean esRespuestaERROR(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.ERROR);
	}

	/**
	 * Es respuesta ERROR.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	public <T> Boolean esRespuestaERROR(Respuesta<T> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR);
	}

	/**
	 * Armar respuesta ERROR limites Y disponibles.
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
		return casuistica.crearRespuestaError(descripcionTag, tipoError, codigoMensaje);
	}

	/**
	 * Crear log estadistica.
	 *
	 * @param codTransaccion
	 *            the cod transaccion
	 * @param codigoError
	 *            the codigo error
	 */
	@Override
	public void crearLogEstadistica(String codTransaccion, String codigoError) {
		estadisticaManager.add(codTransaccion, codigoError);
	}

	/**
	 * Gets the estadistica manager.
	 *
	 * @return the estadistica manager
	 */
	public EstadisticaManager getEstadisticaManager() {
		return estadisticaManager;
	}

	/**
	 * Sets the estadistica manager.
	 *
	 * @param estadisticaManager
	 *            the new estadistica manager
	 */
	public void setEstadisticaManager(EstadisticaManager estadisticaManager) {
		this.estadisticaManager = estadisticaManager;
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @param <T>
	 *            the generic type
	 * @param codigoError
	 *            the codigo error
	 * @return the respuesta
	 */
	public <T> Respuesta<T> crearRespuestaErrorGenerico(String codigoError) {
		return casuistica.crearRespuestaError(null, TipoError.ERROR_GENERICO, codigoError);
	}

	/**
	 * Gets the selector Y cabecera BO.
	 *
	 * @return the selector Y cabecera BO
	 */
	public SelectorYCabeceraBO getSelectorYCabeceraBO() {
		return selectorYCabeceraBO;
	}

	/**
	 * Sets the selector Y cabecera BO.
	 *
	 * @param selectorYCabeceraBO
	 *            the new selector Y cabecera BO
	 */
	public void setSelectorYCabeceraBO(SelectorYCabeceraBO selectorYCabeceraBO) {
		this.selectorYCabeceraBO = selectorYCabeceraBO;
	}

	/**
	 * Gets the cuenta BO.
	 *
	 * @return the cuenta BO
	 */
	public CuentaBO getCuentaBO() {
		return cuentaBO;
	}

	/**
	 * Sets the cuenta BO.
	 *
	 * @param cuentaBO
	 *            the new cuenta BO
	 */
	public void setCuentaBO(CuentaBO cuentaBO) {
		this.cuentaBO = cuentaBO;
	}

	/**
	 * Gets the sesion tarjetas.
	 *
	 * @return the sesionTarjetas
	 */
	public SesionTarjetas getSesionTarjetas() {
		return sesionTarjetas;
	}

	/**
	 * Sets the sesion tarjetas.
	 *
	 * @param sesionTarjetas
	 *            the sesionTarjetas to set
	 */
	public void setSesionTarjetas(SesionTarjetas sesionTarjetas) {
		this.sesionTarjetas = sesionTarjetas;
	}

	/**
	 * Gets the sesion cliente.
	 *
	 * @return the sesionCliente
	 */
	public SesionCliente getSesionCliente() {
		return sesionCliente;
	}

	/**
	 * Sets the sesion cliente.
	 *
	 * @param sesionCliente
	 *            the sesionCliente to set
	 */
	public void setSesionCliente(SesionCliente sesionCliente) {
		this.sesionCliente = sesionCliente;
	}

}
