/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteBajaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoAutomaticoView;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoManualView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.AdhesionResumenView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.StackSelectorCuentasView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Migracion de LoginController.
 * 
 * @author sergio.e.goldentair
 */
@Path("/cuentas")
public interface CuentasSEI {

	/**
	 * Metodo que obtiene las cuentas.
	 *
	 * @param mc
	 *            the mc
	 * @param cuenta
	 *            the cuenta
	 * @return the cuentas
	 */
	@POST
	@Path("/obtenerCuentas")
	@CustomPreAuthorize({AccionController.IR_INICIO_CUENTAS, AccionController.IR_INICIO_UNA_SOLA_CUENTA})
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<SelectorCuentasView> getCuentas(@Context org.apache.cxf.jaxrs.ext.MessageContext mc,
			ConsultaCuentaView cuenta);
	
	/**
	 * Metodo que obtiene las cuentas.
	 *
	 * @return the list of clients cuentas
	 */
	@POST
	@Path("/obtenerListaCuentasCliente")
	@CustomPreAuthorize({AccionController.IR_INICIO_CUENTAS, AccionController.IR_INICIO_UNA_SOLA_CUENTA})
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<StackSelectorCuentasView> getListaCuentasCliente();
	
	
	/**
	 * Copiar cbu.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/copiarCBU")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CopiarMensajeView> copiarCBU();

	/**
	 * Compartir CBU.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/compartirCBU")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> compartirCBU();

	/**
	 * Imprimir.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@POST
	@Path("/imprimir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> imprimir(ConsultaCuentaView cuenta);

	/**
	 * Metodo que actualiza el alias de una cuenta.
	 *
	 * @param params
	 *            the params
	 * @return the respuesta
	 */
	@POST
	@Path("/actualizarAlias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> actualizarAlias(ConsultaCuentaView params);

	/**
	 * Metodo que pone la marca favorita.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@POST
	@Path("/marcarFavorita")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> marcarFavorita(ConsultaCuentaView cuenta);

	/**
	 * Metodo que obtiene una lisgta con el resument de la cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerListaResumen")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(ConsultaCuentaView cuenta);

	/**
	 * Toma solo la primera fecha|id y retorna el pdf asociado.
	 * 
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerResumenes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> obtenerResumenesPDF(ConsultaCuentaView cuenta);

	/**
	 * Obtener resumenes ZIP.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerResumenesDescargaMultiple")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> obtenerResumenesMultiple(ConsultaCuentaView cuenta);

	/**
	 * Verifica cuenta.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the cuentas
	 */
	@POST
	@Path("/verificarCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Cliente> verificarCuenta(TransferenciaView transferenciaView);

	/**
	 * Metodo que obtiene las cuentas saldo.
	 * 
	 * @return cuentas con saldo
	 */
	@POST
	@Path("/obtenerCuentasSaldo")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<CuentasView> getCuentasSaldo();

	/**
	 * Obtiene detalle de una cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param mc
	 *            the mc
	 * @return detalle de cuenta
	 */
	@POST
	@Path("/obtenerDetalleCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DetalleCBUView> obtenerDetalleCBU(ConsultaCuentaView cuenta,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Obtiene detalle de una cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param mc
	 *            the mc
	 * @return detalle de cuenta
	 */
	@POST
	@Path("/obtenerAliasDeCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DetalleCBUView> obtenerAliasCBU(DetalleCBUView cuenta,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Configuracion alta alias CBU.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@POST
	@Path("/configurarAliasCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DetalleCBUView> configuracionAltaAliasCBU(DetalleCBUView cuenta);

	/**
	 * Confirmacion alias CBU.
	 *
	 * @param view
	 *            the view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmacionAltaAliasCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteAltaDestinatarioView> confirmacionAliasCBU(ComprobanteAltaDestinatarioView view,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Grabar estadistica ayuda alias CBU.
	 *
	 * @param detalle
	 *            the detalle
	 */
	@POST
	@Path("/estadisticaAyudaAliasCBU")
	void grabarEstadisticaAyudaAliasCBU(DetalleCBUView detalle);

	/**
	 * Comprobante alias CBU.
	 *
	 * @param view
	 *            the view
	 */
	@POST
	@Path("/comprobanteAliasCBU")
	void comprobanteAliasCBU(DetalleCBUView view);

	/**
	 * Confirmacion baja alias CBU.
	 *
	 * @param view
	 *            the view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmacionBajaAliasCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteBajaDestinatarioView> confirmacionBajaAliasCBU(ComprobanteBajaDestinatarioView view,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Eliminar alias CBU.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/eliminarAliasCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DetalleCBUView> eliminarAliasCBU(DetalleCBUView view);

	/**
	 * Comprobante eliminar alias CBU.
	 *
	 * @param view
	 *            the view
	 */
	@POST
	@Path("/comprobanteEliminarAliasCBU")
	@Consumes(MediaType.APPLICATION_JSON)
	void comprobanteEliminarAliasCBU(DetalleCBUView view);

	/**
	 * Solicitud traspaso.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitudTraspaso")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TraspasoAutomaticoView> solicitudTraspaso(TraspasoAutomaticoView view);

	/**
	 * Confirmar traspaso automatico.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarSolicitudTraspaso")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TraspasoAutomaticoView> confirmarTraspasoAutomatico(TraspasoAutomaticoView view);

	/**
	 * Solicitud traspaso manual.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitudTraspasoManual")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TraspasoManualView> solicitudTraspasoManual(TraspasoManualView view);

	/**
	 * Configuracion traspaso manual.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/configuracionTraspasoManual")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TraspasoManualView> configuracionTraspasoManual(TraspasoManualView view);

	/**
	 * Confirmacion traspaso manual.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmacionTraspasoManual")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TraspasoManualView> confirmacionTraspasoManual(TraspasoManualView view);

	/**
	 * Comprobante traspaso manual.
	 */
	@POST
	@Path("/comprobanteTraspasoManual")
	void comprobanteTraspasoManual();

	/**
	 * Confirmar adhesion resumen.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarAdhesionResumen")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AdhesionResumenView> confirmarAdhesionResumen(AdhesionResumenView view);

	/**
	 * Comprobante adhesion resumen.
	 *
	 * @param view
	 *            the view
	 */
	@POST
	@Path("/comprobanteAdhesionResumen")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	void comprobanteAdhesionResumen(AdhesionResumenView view);

	/**
	 * Obtener tablero cuentas.
	 *
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerInicioCuentas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<CuentasIntermedioView> obtenerInicioCuentas(@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Obtener tablero cuentas controller.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/notificarAccesoTableroCuentasHome")
	@Consumes(MediaType.APPLICATION_JSON)
	void obtenerTableroCuentasHome();
	
	@POST
    @Path("/obtenerAliasYFavoritos")
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<List<AliasFavoritoProducto>> obtenerAliasYFavoritos();

}
