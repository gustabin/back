/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeChanceView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeInversionesView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;

/**
 * The Interface HomeSEI.
 */
@Path("/home")
public interface HomeSEI {

	/** The Constant OBTENER_CAJAS. */
	String OBTENER_CAJAS = "/obtenerTablero";

	/** The Constant OBTENER_SALDOS. */
	String OBTENER_SALDOS = "/obtenerSaldosCuentas";

	/** The Constant OBTENER_TOPBAR. */
	String OBTENER_TOPBAR = "/obtenerTopbar";

	/** The Constant OBTENER_TARJETAS. */
	String OBTENER_TARJETAS = "/obtenerSaldoTarjeta";

	/** The Constant OBTENER_INVERSIONES. */
	String OBTENER_INVERSIONES = "/obtenerTenencias";

	/** The obtener afinidad. */
	String OBTENER_AFINIDAD = "/obtenerValidacionAfinidad";
	
	/** grabar estadistica ayuda. */
	String SOLICITAR_AYUDA = "/grabarEstadisticaAyuda";

	/** The obtener seguro. */
	String OBTENER_SEGURO = "/obtenerSeguros";

	/** The premiaciones periodo actual. */
	String PREMIACIONES_PERIODO_ACTUAL = "/premiacionesPeriodoActual";
	
	String ESTADISTICA_INGRESO_SUPERCLUB = "/estadisticaIngresoSuperclub";

	/**
	 * Obtener tablero.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_CAJAS)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TableroHomeView> obtenerTablero();

	/**
	 * Obtener seguro.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_SEGURO)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CajaHomeSegurosView> obtenerSeguro();

	/**
	 * Obtener saldos cuentas.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_SALDOS)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<SaldosConsolidadosView> obtenerSaldosCuentas();

	/**
	 * Obtener topbar.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_TOPBAR)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<TopbarHomeView> obtenerTopbar();

	/**
	 * Obtener saldo tarjeta.
	 *
	 * @param cajaTarjetasHomeView the caja tarjetas home view
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_TARJETAS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<CajaHomeTarjetaView> obtenerSaldoTarjeta(CajaHomeTarjetaView cajaTarjetasHomeView);

	/**
	 * Obtener tenencias inversiones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CajaHomeInversionesView> obtenerTenencias();

	/**
	 * Obtener validacion afinidad.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(OBTENER_AFINIDAD)
	@Produces(MediaType.APPLICATION_JSON)
	void obtenerValidacionAfinidad();
	
	/**
	 * Obtener validacion afinidad.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(SOLICITAR_AYUDA)
	@Produces(MediaType.APPLICATION_JSON)
	void grabarEstadisticaAyuda();

	/**
	 * Obtener premiaciones periodo actual.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(PREMIACIONES_PERIODO_ACTUAL)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CajaHomeChanceView> premiacionesPeriodoActual();
	
	@POST
	@Path(ESTADISTICA_INGRESO_SUPERCLUB)
	@Produces(MediaType.APPLICATION_JSON)
	void grabarEstadisticaIngresoSuperClub();
	
}
