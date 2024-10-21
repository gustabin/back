/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;

/**
 * The Interface FondoSEI.
 *
 * @author
 */
@Path("/fondos")
public interface FondoSEI {

	/**
	 * Simula la suscripcion a un fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/simularSuscripcion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FondoView> simularSuscripcion(FondoView viewRequest);

	/**
	 * realiza suscribir en Fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/suscribir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FondoView> suscribir(FondoView viewRequest);

	/**
	 * Banca personal: Devuelve las cuentas titulo con su tenencia y el total de
	 * los fondos disponibles para suscribir.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerFondosPorCuentasBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponiblesBPriv(CuentasConsultaFondoView viewRequest);

	/**
	 * Banca privada: Devuelve las cuentas operativas con su tenencia y con su
	 * cuenta titulo asociada el total de los fondos disponibles para suscribir.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerFondosPorCuentas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CuentasConsultaFondoView> obtenerFondosSuscriptosYDisponibles(CuentasConsultaFondoView viewRequest);

	/**
	 * Configurar suscripcion. obtiene las cuentas operativas para la moneda
	 * seleccionada.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	@POST
	@Path("/configurarSuscripcion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigFondoView> obtenerDatosConfig(ConfigFondoView requestView);

	/**
	 * Aceptar contrato.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	@POST
	@Path("/aceptarContrato")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigFondoView> aceptarContrato(ConfigFondoView requestView);

	/**
	 * Graba estadistica de Gastos de banca personal.
	 *
	 * @param requestView
	 *            the request view
	 */
	@POST
	@Path("/grabarEstadisticaGastos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaLegalBPersonal(ConfigFondoView requestView);

	/**
	 * Graba estadistica de Gastos de banca privada.
	 *
	 * @param requestView
	 *            the request view
	 */
	@POST
	@Path("/grabarEstadisticaGastosBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaLegalBPriv(ConfigFondoView requestView);

	/**
	 * Recupera las tenencias del usuario de acuerdo al tipo de banca
	 * seleccionada PRIVADA/RETAIL.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	@POST
	@Path("/tenencias")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciasFondoView> obtenerTenencias(TipoBancaView requestView);

	/**
	 * Realizar la confirmación de una suscripción para un cliente de Banca
	 * Personal.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarSuscripcionFondos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FondoView> finalizarSuscribirFondos(FondoView viewRequest);

	/**
	 * Configurar suscripcion. obtiene las cuentas operativas para la moneda
	 * seleccionada.
	 *
	 * @param requestView
	 *            the request view
	 * @return the respuesta
	 */
	@POST
	@Path("/configurarSuscripcionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigFondoBPrivView> obtenerDatosConfigBPriv(ConfigFondoBPrivView requestView);

	/**
	 * Simula la suscripcion a un fondo priv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/simularSuscripcionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimulacionSuscripcionOutView> simularSuscripcionBPriv(SimulacionInView viewRequest);

	/**
	 * Simula la suscripcion a un fondo priv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/finalizarSuscripcionFondosBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SuscripcionOutView> finalizarSuscribirFondosBPriv(SuscripcionInView viewRequest);

	/**
	 * Ver comprobante B priv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest);

	/**
	 * Ver comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobante")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest);

	/**
	 * Consultar cotizaciones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarCotizaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CotizacionDeFondosView> consultarCotizaciones();

	/**
	 * Consultar horarios Y honorarios.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarHorariosYHonorarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaHorariosYHonorariosView> consultarHorariosYHonorarios();

	/**
	 * retorna los movimientos de un fondo en particular.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/ultimosMovimientos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientosView> ultimosMovimientos(MovimientosInView viewRequest);

	/**
	 * Obtener detalle de fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/detalleDeFondo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DetalleDeFondoOutView> obtenerDetalleDeFondo(DetalleDeFondoIn viewRequest);

	/**
	 * retorna los movimientos de un fondo en particular.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/busquedaMovimientos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MovimientosView> busquedaMovimientos(MovimientosInView viewRequest);

	/**
	 * retorna los porcentajes para armar graficos de fondos en banca personal.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerGraficosRTL")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<GraficoFondosRTLOutView> obtenerGraficosRTL();

	/**
	 * retorna los porcentajes para armar graficos de fondos en banca privada.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerGraficosBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<GraficoFondosBPrivOutView> obtenerGraficosBPriv();

	/**
	 * Obtiene todos los fondos existente para ver sus ultimos movimientos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerFondosPorCuentasUltimosMovimientos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CuentasConsultaFondoView> obtenerFondosUltimosMovimientos(CuentasConsultaFondoView viewRequest);

	/**
	 * Descargar comprobante PDF de suscripcion de un fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteSuscripcionPDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteSuscripcionPDF(ComprobanteSuscripcionFondo viewRequest);
	
	
	/**
	 * Obtener rendimiento.
	 *
	 * @return the respuesta
	 */
	@POST
    @Path("/obtenerRendimiento")
//    @CustomPreAuthorize(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RendimientoView>obtenerRendimiento();
	
	
	/**
	 * Obtener rendimiento B priv.
	 *
	 * @return the respuesta
	 */
	@POST
    @Path("/obtenerRendimientoBPriv")
//    @CustomPreAuthorize(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RendimientoBPrivView>obtenerRendimientoBPriv();

	@POST
	@Path("/obtenerListaComprobantes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ListaFechasComprobantes> obtenerListaComprobantes();
	
	@POST
	@Path("/descargaComprobantesPDF")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ListadoPDF> descargaComprobantesPDF(ConsultaResumenCuentaBP cuenta);
	
	/**
	 * Grabado de estadisticas para goto de agendar fondos desde paso 1 cuando es fuera de horario rtl.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaGoToAGD")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaGoToAGD();
	
	/**
	 * Grabado de estadisticas para goto de agendar fondos desde paso 1 cuando es fuera de horario bpriv
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaGoToAGDBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaGoToAGDBpriv();
	
}
