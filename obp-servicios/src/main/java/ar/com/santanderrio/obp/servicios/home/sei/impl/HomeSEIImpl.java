/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.home.sei.HomeSEI;
import ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeChanceView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeInversionesView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;

/**
 * The Class HomeSEIImpl.
 */
@Component
public class HomeSEIImpl implements HomeSEI {

	/** The home manager. */
	@Autowired
	private HomeManager homeManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#obtenerTablero()
	 */
	@Override
	public Respuesta<TableroHomeView> obtenerTablero() {
		return homeManager.obtenerTablero();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#obtenerSaldosCuentas()
	 */
	@Override
	public Respuesta<SaldosConsolidadosView> obtenerSaldosCuentas() {

		return homeManager.obtenerSaldosCuentas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#obtenerTopbar()
	 */
	@Override
	public Respuesta<TopbarHomeView> obtenerTopbar() {
		return homeManager.obtenerTopbar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#obtenerSaldoTarjeta(ar
	 * .com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView)
	 */
	@Override
	public Respuesta<CajaHomeTarjetaView> obtenerSaldoTarjeta(CajaHomeTarjetaView cajaTarjetasHomeView) {
		return homeManager.obtenerSaldoTarjeta(cajaTarjetasHomeView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CajaHomeInversionesView> obtenerTenencias() {
		return homeManager.obtenerTenencias();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#
	 * obtenerValidacionAfinidad()
	 */
	@Override
	public void obtenerValidacionAfinidad() {
		homeManager.obtenerValidacionAfinidad();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#obtenerSeguro()
	 */
	@Override
	public Respuesta<CajaHomeSegurosView> obtenerSeguro() {
		return homeManager.obtenerSeguro();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.sei.HomeSEI#grabarEstadisticaAyuda()
	 */
	@Override
	public void grabarEstadisticaAyuda() {
		estadisticaManager.add(CodigoMensajeConstantes.SOLICITAR_AYUDA_HOME,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Obtener premiaciones periodo actual.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CajaHomeChanceView> premiacionesPeriodoActual() {
		return homeManager.premiacionesPeriodoActual();
	}

	@Override
	public void grabarEstadisticaIngresoSuperClub() {
		estadisticaManager.add(EstadisticasConstants.INGRESO_SUPERCLUB,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}
	
}
