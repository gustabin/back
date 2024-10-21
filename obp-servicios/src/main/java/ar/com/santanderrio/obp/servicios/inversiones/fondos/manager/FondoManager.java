/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionFondo;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConsultaHorariosYHonorariosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CotizacionDeFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.GraficoFondosBPrivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.GraficoFondosRTLOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionSuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TenenciasFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TipoBancaView;

/**
 * Gestiona la logica relacionada a Fondo.
 * 
 * @author
 *
 */
/**
 * @author b039920
 *
 */
public interface FondoManager {

	/**
	 * realiza suscribir en Fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<FondoView> suscribir(FondoView viewRequest);

	/**
	 * Obtener fondos suscriptos Y disponibles.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponibles(CuentasConsultaFondoView viewRequest);

	/**
	 * Banca privada: Devuelve las cuentas operativas con su tenencia y con su
	 * cuenta titulo asociada el total de los fondos disponibles para suscribir.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return {@link Respuesta} {@link CuentasConsultaFondoView}
	 */
	Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponiblesBPriv(CuentasConsultaFondoView viewRequest);

	/**
	 * obtener datos de Configuracion para suscripcion a un fondo.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	Respuesta<ConfigFondoView> obtenerDatosConfig(ConfigFondoView requestView);

	/**
	 * aceptar contrato.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	Respuesta<ConfigFondoView> aceptarContrato(ConfigFondoView requestView);

	/**
	 * Grabar estadistica legales banca personal.
	 *
	 * @param requestView
	 *            the request view
	 */
	void grabarEstadisticaLegalBPersonal(ConfigFondoView requestView);

	/**
	 * Grabar estadistica legales banca privada.
	 *
	 * @param requestView
	 *            the request view
	 */
	void grabarEstadisticaLegalBPriv(ConfigFondoView requestView);

	/**
	 * Recupera las tenenecias del cliente para una banca en particular.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	Respuesta<TenenciasFondoView> obtenerTenencias(TipoBancaView requestView);

	/**
	 * Simular suscripcion.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<FondoView> simularSuscripcion(FondoView viewRequest);

	/**
	 * Realizar la confirmación de una suscripción para un cliente de Banca
	 * Personal.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<FondoView> finalizarSuscribirFondos(FondoView viewRequest);

	/**
	 * obtener datos de Configuracion para suscripcion a un fondo.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	Respuesta<ConfigFondoBPrivView> obtenerDatosConfigBpriv(ConfigFondoBPrivView requestView);

	/**
	 * Realiza la simulacion de una suscripcion a un fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<SimulacionSuscripcionOutView> simularSuscripcionBPriv(SimulacionInView viewRequest);

	/**
	 * Finaliza la suscripcion a un fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<SuscripcionOutView> finalizarSuscribirFondosBPriv(SuscripcionInView viewRequest);

	/**
	 * Ver comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest);

	/**
	 * Ver comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest);

	/**
	 * Limpia la cache de fondos.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarCache();

	/**
	 * Devuelve un listado de fondos con sus cotizaciones y caracteristicas.
	 * Ademas un mensaje de legales a mostrar en la grilla.
	 *
	 * @return the respuesta
	 */
	Respuesta<CotizacionDeFondosView> consultarCotizaciones();

	/**
	 * Devuelve un listado de fondos con horarios y otro con fondos honorarios.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultaHorariosYHonorariosView> consultarHorariosYHonorarios();

	/**
	 * Devuelve lista de los detalles del fondo seleccionado.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<DetalleDeFondoOutView> obtenerDetalleDeFondo(DetalleDeFondoIn viewRequest);

	/**
	 * retorna los movimientos de un fondo en particular.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<MovimientosView> ultimosMovimientos(MovimientosInView viewRequest);

	/**
	 * retorna los movimientos de un fondo en particular.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<MovimientosView> busquedaMovimientos(MovimientosInView viewRequest);

	/**
	 * retorna los porcentajes para armar graficos de fondos en banca personal.
	 *
	 * @return the respuesta
	 */
	Respuesta<GraficoFondosRTLOutView> obtenerGraficosRTL();

	/**
	 * retorna los porcentajes para armar graficos de fondos en banca privada.
	 *
	 * @return the respuesta
	 */
	Respuesta<GraficoFondosBPrivOutView> obtenerGraficosBPriv();

	/**
	 * Devuelve ultimos movimiento de todos los fondos existente.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoView> obtenerFondosUltimosMovimientos(CuentasConsultaFondoView viewRequest);

	/**
	 * Descargar comprobante Suscripcion PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteSuscripcionPDF(ComprobanteSuscripcionFondo viewRequest);

	/**
	 * Obtener rendimiento.
	 *
	 * @return the respuesta
	 */
	Respuesta<RendimientoView> obtenerRendimiento();

	/**
	 * Obtener rendimiento B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv();
	
	Response exportarGrillaFondos(TipoBancaEnum tipoBanca);
	
	Respuesta<Void> grabarEstadisticaGoToAGD();
	
	Respuesta<Void> grabarEstadisticaGoToAGDBpriv();
}
