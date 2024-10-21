/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.manager.ChanceHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.CuentaHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.InversionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeguroHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TableroHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TarjetaHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TopbarHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeChanceView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeInversionesView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;

/**
 * The Class HomeManagerImpl.
 */
@Component
public class HomeManagerImpl implements HomeManager {

	/** The tablero home manager. */
	@Autowired
	private TableroHomeManager tableroHomeManager;

	/** The cuenta home manager. */
	@Autowired
	private CuentaHomeManager cuentaHomeManager;

	/** The topbar home manager. */
	@Autowired
	private TopbarHomeManager topbarHomeManager;

	/** The tarjeta home manager. */
	@Autowired
	private TarjetaHomeManager tarjetaHomeManager;

	/** The tarjeta home manager. */
	@Autowired
	@Qualifier("inversionesHomeManagerImpl")
	private InversionesHomeManager inversionesHomeManager;

	/** The seguro home manager. */
	@Autowired
	private SeguroHomeManager seguroHomeManager;

	/** The chance home manager. */
	@Autowired
	private ChanceHomeManager chanceHomeManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager#
	 * obtenerTablero()
	 */
	@Override
	public Respuesta<TableroHomeView> obtenerTablero() {
		return tableroHomeManager.obtenerTablero();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager#
	 * obtenerSaldosCuentas()
	 */
	@Override
	public Respuesta<SaldosConsolidadosView> obtenerSaldosCuentas() {
		return cuentaHomeManager.obtenerSaldosCuentas();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager#
	 * obtenerTopbar()
	 */
	@Override
	public Respuesta<TopbarHomeView> obtenerTopbar() {
		return topbarHomeManager.obtenerTopbar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager#
	 * obtenerSaldoTarjeta(ar.com.santanderrio.obp.servicios.home.web.view.
	 * CajaHomeTarjetaView)
	 */
	@Override
	public Respuesta<CajaHomeTarjetaView> obtenerSaldoTarjeta(CajaHomeTarjetaView cajaTarjetasHomeView) {
		return tarjetaHomeManager.obtenerSaldoTarjeta(cajaTarjetasHomeView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CajaHomeInversionesView> obtenerTenencias() {
		return inversionesHomeManager.obtenerTenencias();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager#
	 * obtenerValidacionAfinidad()
	 */
	@Override
	public void obtenerValidacionAfinidad() {
		tarjetaHomeManager.obtenerValidacionAfinidad();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager#
	 * obtenerSeguro()
	 */
	@Override
	public Respuesta<CajaHomeSegurosView> obtenerSeguro() {
		return seguroHomeManager.obtenerSeguro();
	}

	/**
	 * Obtener premiaciones periodo actual.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CajaHomeChanceView> premiacionesPeriodoActual() {
		return chanceHomeManager.premiacionesPeriodoActual();
	}
}
