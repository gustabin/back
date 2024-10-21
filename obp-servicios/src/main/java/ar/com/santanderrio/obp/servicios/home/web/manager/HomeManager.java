/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeChanceView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeInversionesView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;

/**
 * The Interface HomeManager.
 */
public interface HomeManager {

	/**
	 * Obtener tablero.
	 *
	 * @return the respuesta
	 */
	Respuesta<TableroHomeView> obtenerTablero();

	/**
	 * Obtener saldos cuentas.
	 *
	 * @return the respuesta
	 */
	Respuesta<SaldosConsolidadosView> obtenerSaldosCuentas();

	/**
	 * Obtener topbar.
	 *
	 * @return the respuesta
	 */
	Respuesta<TopbarHomeView> obtenerTopbar();

	/**
	 * Obtener saldo tarjeta.
	 *
	 * @param cajaTarjetasHomeView the caja tarjetas home view
	 * @return the respuesta
	 */
	Respuesta<CajaHomeTarjetaView> obtenerSaldoTarjeta(CajaHomeTarjetaView cajaTarjetasHomeView);

	/**
	 * Obtener tenencias inversiones.
	 *
	 * @return the respuesta
	 */
	Respuesta<CajaHomeInversionesView> obtenerTenencias();

	/**
	 * Obtener validacion tarjeta debito.
	 *
	 * @return the respuesta
	 */
	void obtenerValidacionAfinidad();

	/**
	 * Obtener seguro.
	 *
	 * @return the respuesta
	 */
	Respuesta<CajaHomeSegurosView> obtenerSeguro();

	/**
	 * Obtener premiaciones periodo actual.
	 *
	 * @return the respuesta
	 */
	Respuesta<CajaHomeChanceView> premiacionesPeriodoActual();
}
