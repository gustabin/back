/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;

/**
 * The Class CompraVentaDolaresManagerImpl.
 *
 * @author sabrina.cis
 */
@Component
public class CompraVentaDolaresManagerImpl {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompraVentaDolaresManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant RESPUESTA_VISTA. */
	public static final String RESPUESTA_VISTA = "Respuesta Para la Vista: {}.";

	/** The Constant CLIENTE_EN_SESION. */
	private static final String CLIENTE_EN_SESION = "Cliente en Sesion {}.";

	/** The CompraVentaDolaresUtil. */
	@Autowired
	protected CompraVentaDolaresUtil util;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion compra venta. */
	@Autowired
	protected SesionCompraVenta sesionCompraVenta;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The casuistica BO. */
	@Autowired
	protected CompraVentaCasuisticaRespuestaBO casuistica;
	
	/** The sesion. */
	@Autowired
	protected SesionParametros sesion;

	/**
	 * Guarda los numeros cuenta origen y destino en la sesion de compra venta.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param nroCuentaOrigen
	 *            the nro cuenta origen
	 * @param nroCuentaDestino
	 *            the nro cuenta destino
	 */
	protected void guardarNumerosCuentaEnSesionCompraVenta(EstadoRespuesta estadoRespuesta, String nroCuentaOrigen,
			String nroCuentaDestino) {
		if (casuistica.esRespuestaOK(estadoRespuesta)) {
			sesionCompraVenta.setNumeroCuentaOrigen(nroCuentaOrigen);
			sesionCompraVenta.setNumeroCuentaDestino(nroCuentaDestino);
		}
	}

	/**
	 * Valida que las cuentas seleccionadas en la configuracion, sean las mismas
	 * a las obtenidas en la operacion.
	 *
	 * @param nroCuentaOrigen
	 *            the nro cuenta origen
	 * @param nroCuentaDestino
	 *            the nro cuenta destino
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	protected void validarCuentasSeleccionadas(String nroCuentaOrigen, String nroCuentaDestino)
			throws CompraVentaDolaresException {
		if (!nroCuentaOrigen.equals(sesionCompraVenta.getNumeroCuentaOrigen())
				&& !nroCuentaDestino.equals(sesionCompraVenta.getNumeroCuentaDestino())) {
			throw new CompraVentaDolaresException();
		}
	}

	/**
	 * Obtener cuenta del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuenta
	 * @throws ServiceException
	 *             the service exception
	 */
	protected Cuenta obtenerCuenta(Cliente cliente, String numeroCuenta) throws ServiceException {
		return (Cuenta) cuentaBO.buscarCuentaPorId(cliente, new IdentificacionCuenta(numeroCuenta));
	}

	/**
	 * La cuenta esta inhabilitada cuando IDECLTSDO.producto_altair=03 y
	 * IDECLTSDO.subproducto_altair=0001 La cuenta en dólares para estar
	 * habilitada no tiene que ser Cuenta Custodia que son las caja de ahorro
	 * u$s Especiales.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	protected boolean estaHabilitadaParaCompraVentaDolar(Cuenta cuenta) throws ServiceException {
		return util.estaHabilitadaParaCompraVentaDolar(cuenta);
	}

	/**
	 * La cuenta esta inhabilitada cuando IDECLTSDO.producto_altair=03 y
	 * IDECLTSDO.subproducto_altair=0001 La cuenta en dólares para estar
	 * habilitada no tiene que ser Cuenta Custodia que son las caja de ahorro
	 * u$s Especiales.
	 *
	 * @param respuestaCuentasView
	 *            the respuesta cuentas view
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	protected boolean tieneCuentasHabilitadas(Respuesta<CuentasView> respuestaCuentasView, Cliente cliente)
			throws ServiceException {
		return util.tieneCuentasHabilitadas(respuestaCuentasView.getRespuesta().getCuentas(), cliente);
	}

	/**
	 * Obtener identificacion cuenta.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the identificacion cuenta
	 */
	protected IdentificacionCuenta obtenerIdentificacionCuenta(String numeroCuenta) {
		if (numeroCuenta != null) {
			return new IdentificacionCuenta(numeroCuenta);
		}
		return null;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	protected Cliente getCliente() {
		LOGGER.info(CLIENTE_EN_SESION, sesionCliente.getCliente().toString());
		return sesionCliente.getCliente();
	}

	/**
	 * Obtener mensaje reintentar.
	 *
	 * @param itemMensajeRespuesta
	 *            the item mensaje respuesta
	 * @param esCompra
	 *            the es compra
	 */
	protected void obtenerMensajeReintentar(ItemMensajeRespuesta itemMensajeRespuesta, boolean esCompra) {
		ContadorIntentos contadorDeOperacion = null;
		if (esCompra) {
			contadorDeOperacion = sesionCompraVenta.getContadorCompra();
		} else {
			contadorDeOperacion = sesionCompraVenta.getContadorVenta();
		}
		if (!contadorDeOperacion.permiteReintentar()) {
			itemMensajeRespuesta.setDetalleTipoError("continuar");
			sesionCompraVenta.setContadorCompra(null);
			sesionCompraVenta.setContadorVenta(null);
		} else {
			itemMensajeRespuesta.setDetalleTipoError("reintentar");
		}
	}

}