/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;

/**
 * The Interface ConfiguracionCompraVentaManager.
 *
 * @author sabrina.cis
 */
public interface ConfiguracionCompraVentaManager {

	/**
	 * Obtener datos iniciales.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param fromCuentas
	 *            the from cuentas
	 * @param numeroCuentaDestino
	 *            the numero cuenta destino
	 * @return the respuesta
	 */
	Respuesta<CompraVentaDolarView> obtenerDatosIniciales(String numeroCuenta, String tipoOperacion, boolean fromCuentas, String numeroCuentaDestino);

	/**
	 * Obtener cotizacion para compra.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the respuesta
	 */
	Respuesta<CompraVentaDolarView> obtenerCotizacionParaCompra(Cotizacion cotizacion);

	/**
	 * Obtener cotizacion para venta.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the respuesta
	 */
	Respuesta<CompraVentaDolarView> obtenerCotizacionParaVenta(Cotizacion cotizacion);
	
	/**
	 * Consulta si el nup está habilitado para realizar operaciones de compra y venta de USD
	 * @param nup
	 * @return
	 */
	Respuesta<DatosRespuestaHabilitaCompraVentaUSDEntity> cnsHabilitadoCompraVtaUSD(String nup);

}
