/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnCuentaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.CodigoEmpresaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;

/**
 * Controller que atiende los request de nuevo pago automatico.
 */

@Path("/nuevoPagoAutomatico")
public interface PagoAutomaticoSEI {

	/**
	 * Obtiene la lista de cuentas para realizar un nuevo pago automatico.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerCuentas")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<MedioPagoSelectionView> obtenerCuentas();

	/**
	 * Confirmar adhesion pago automatico.
	 *
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarAdhesionPagoAutomatico")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionPagoAutomatico> confirmarAdhesionPagoAutomatico(AdhesionPagoAutomatico adhesionPagoAutomatico);

	/**
	 * Cargar terminos condiciones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/terminosCondiciones")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TerminosCondiciones> cargarTerminosCondiciones();

	/**
	 * Adherir debito.
	 *
	 * @param adhesionDebitoAutomaticoView
	 *            the adhesion debito automatico view
	 * @return the respuesta
	 */
	@POST
	@Path("/adherirDebito")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionDebitoAutomaticoEnCuentaView> adherirDebito(
			AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoView);

	/**
	 * Genera un reporte con el comprobante de la adhesion.
	 *
	 * @param comprobanteDebitoAutomatico
	 *            the comprobante debito automatico
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteAdhesion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico);

	/**
	 * Estadistica comprobante nuevo pago automatico.
	 */
	@POST
	@Path("/estadisticaComprobanteNuevoPagoAutomatico")
	void estadisticaComprobanteNuevoPagoAutomatico();

	/**
	 * Obtener datos adhesion debito automatico en tarjeta.
	 *
	 * @param codigoEmpresa
	 *            the codigo empresa
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosAdhesionDebitoAutomaticoEnTarjeta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(
			CodigoEmpresaView codigoEmpresa);

	/**
	 * Generar hash de seguridad.
	 *
	 * @param datosParaHash
	 *            the datos para hash
	 * @return the boolean
	 */
	@POST
	@Path("/confirmacionAdhesionADebitoEnTarjeta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Boolean generarHashDeSeguridad(HashDebitoAutomaticoTarjetaView datosParaHash);

	/**
	 * Valida que el usuario posea segundo factor de autenticacion antes de
	 * intentar realizar una adhesion a pago automatico.
	 *
	 * @return respuesta
	 */
	@POST
	@Path("/validarAutenticacionPagoAutomatico")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Boolean> validarAutenticacionPagoAutomatico();
	
	
	@POST
	@Path("/comprobanteAdhesionPagoAutomatico")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteAdhesionPagoAutomatico(ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico);

}
