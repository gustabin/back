/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.sei.CompraVentaSEI;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.OperacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.SimulacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ConfiguracionView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Class CompraVentaSEIImpl.
 *
 * @author sabrina.cis
 */
@Component("compraVentaSEI")
public class CompraVentaSEIImpl implements CompraVentaSEI {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompraVentaSEIImpl.class);

	/** The MENSAJE_RESPUESTA. */
	private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The manager. */
	@Autowired
	private ConfiguracionCompraVentaManager manager;

	/** The compra manager. */
	@Autowired
	private OperacionCompraVentaManager operacionManager;

	/** The simulacion manager. */
	@Autowired
	private SimulacionCompraVentaManager simulacionManager;

	/**
	 * Obtiene los datos iniciales para la configuracion de compra-venta
	 * dolares.
	 *
	 * @param configuracionView
	 *            the configuracion view
	 * @return the CompraVentaDolarView
	 */
	@Override
	public Respuesta<CompraVentaDolarView> compraVentaDolaresInicio(ConfiguracionView configuracionView) {
		LOGGER.info("Post OK: /getDatosIniciales.");

		Respuesta<CompraVentaDolarView> respuesta = manager.obtenerDatosIniciales(configuracionView.getNumeroCuenta(),
				configuracionView.getOperacion(), configuracionView.getFromCuentas(), configuracionView.getNumeroCuentaDestino());
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;

	}

	/**
	 * Obtiene la cotizacion para la compra de la cuenta activa.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the CompraVentaDolarViewl
	 */
	@Override
	public Respuesta<CompraVentaDolarView> obtenerCotizacionCompra(Cotizacion cotizacion) {
		LOGGER.info("Post OK: /getCotizacionCompra.");

		Respuesta<CompraVentaDolarView> respuesta = manager.obtenerCotizacionParaCompra(cotizacion);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;
	}

	/**
	 * Obtiene la cotizacion para la venta de la cuenta activa.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CompraVentaDolarView> obtenerCotizacionVenta(Cotizacion cotizacion) {
		LOGGER.info("Post OK: /getCotizacionVenta.");

		Respuesta<CompraVentaDolarView> respuesta = manager.obtenerCotizacionParaVenta(cotizacion);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;

	}

	/**
	 * Boton confirmar de la pantalla simulacion, si el cliente compra dolares.
	 *
	 * @param confirmarClienteCompra
	 *            the confirmar cliente compra
	 * @return the ComprobanteCompraVentaView
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaView> confirmarClienteCompra(
			ConfirmarClienteCompraEntity confirmarClienteCompra) {
		LOGGER.info("Post OK: /confirmarClienteCompra.");
		Respuesta<ComprobanteCompraVentaView> respuesta = operacionManager
				.operacionClienteCompra(confirmarClienteCompra);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;

	}

	/**
	 * Boton confirmar de la pantalla simulacion, si el cliente vende dolares.
	 *
	 * @param confirmarClienteVenta
	 *            the confirmar cliente venta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaView> confirmarClienteVenta(
			ConfirmarClienteVentaEntity confirmarClienteVenta) {
		LOGGER.info("Post OK: /confirmarClienteVenta.");

		Respuesta<ComprobanteCompraVentaView> respuesta = operacionManager.operacionClienteVende(confirmarClienteVenta);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;

	}

	/**
	 * Boton continuar del paso de compra venta de dolares para operacion venta,
	 * llamada a servicio de simulacion.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SimulacionCompraVentaDolarView> continuarVentaDolares(
			ContinuarCompraVentaEntity continuarCompraVenta) {
		LOGGER.info("Post OK: /continuarVentaDolares.");

		Respuesta<SimulacionCompraVentaDolarView> respuesta = simulacionManager
				.continuarVentaDolares(continuarCompraVenta);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;

	}

	/**
	 * Continuar compra dolares.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SimulacionCompraVentaDolarView> continuarCompraDolares(
			ContinuarCompraVentaEntity continuarCompraVenta) {
		LOGGER.info("Post OK: /continuarCompraDolares.");
		Respuesta<SimulacionCompraVentaDolarView> respuesta = simulacionManager
				.continuarCompraDolares(continuarCompraVenta);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		return respuesta;

	}

    /*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.compraventa.sei.CompraVentaSEI#
	 * estadisticaComprobanteCompra()
	 */
	@Override
	public void estadisticaComprobanteCompra() {
		LOGGER.info("Post OK: /estadisticaComprobanteCompra.");
		operacionManager.generarEstadisticaComprobanteClienteCompra();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.compraventa.sei.CompraVentaSEI#
	 * estadisticaComprobanteVenta()
	 */
	@Override
	public void estadisticaComprobanteVenta() {
		LOGGER.info("Post OK: /estadisticaComprobanteVenta.");
		operacionManager.generarEstadisticaComprobanteClienteVende();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * descargarComprobantePDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF() {
		return operacionManager.descargarComprobantePDF();
	}

}
