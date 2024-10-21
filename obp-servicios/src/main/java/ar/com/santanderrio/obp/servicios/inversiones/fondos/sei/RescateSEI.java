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
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ComprobanteRescateFondo;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateCitiInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateOutView;

/**
 * The Interface RescateSEI.
 *
 * @author b039920
 */
@Path("/rescate")
public interface RescateSEI {

	/**
	 * Paso 2 de la configuracion de rescate. Aqui se selecciona la cuenta
	 * destino y el importe a rescatar. Se recibe la moneda y se devuelven las
	 * cuentas acordes a la misma.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/configuracionRescate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionRescateOutView> configuracionRescate(ConfiguracionRescateInView viewRequest);

	/**
	 * Comprobante rescate.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarRescate")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<FinalizarRescateView> finalizarRescate(FinalizarRescateInView viewRequest);

	/**
	 * Finalizar rescate citi.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarRescateCiti")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<FinalizarRescateView> finalizarRescateCiti(FinalizarRescateCitiInView viewRequest);
	
	/**
	 * Realiza la confirmación de un rescate de fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularRescateFondo")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<SimulacionRescateOutView> simularRescateFondo(SimulacionRescateInView viewRequest);
	
	/**
	 * Realiza el grabado en sesion de los datos para validar mapa en la
	 * confirmacion y descarga de comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/continuarConfiguracionAConfirmacionRescateExciti")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> continuarConfiguracionAConfirmacionRescateExciti(FinalizarRescateCitiInView viewRequest);

	/**
	 * Realiza la simulacion del rescate y devuelve las cuotas partes.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularRescateFondoBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<SimulacionRescateOutView> simularRescateFondoBPriv(SimulacionRescateInView viewRequest);

	/**
	 * Obtener fondos suscriptos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerFondosPorCuentas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RescateView> obtenerFondosSuscriptos(RescateInView viewRequest);

	/**
	 * Banca privada: Paso 2 de la configuracion de rescate. Aqui se selecciona
	 * la cuenta destino y el importe a rescatar. Se recibe la moneda y se
	 * devuelven la cuenta acorde a la misma.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/configuracionRescateBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionRescateOutView> configuracionRescateBPriv(ConfiguracionRescateBPrivInView viewRequest);

	/**
	 * Obtener fondos suscriptos B priv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerFondosPorCuentasBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RescateView> obtenerFondosSuscriptosBPriv(RescateInView viewRequest);

	/**
	 * Realiza el rescate, retorna datos del comprobante.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarRescateBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<FinalizarRescateView> finalizarRescateBPriv(FinalizarRescateBPrivInView viewRequest);

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
	Respuesta<ComprobanteSuscripcionView> verComprobanteRescate(DatosComprobante viewRequest);

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
	Respuesta<ComprobanteSuscripcionView> verComprobanteRescateBPriv(DatosComprobante viewRequest);

	/**
	 * Acceder al paso 2 de rescate desde la grilla.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/iniciarRescateDesdePaso2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RescateDesdeGrilla> obtenerRescateDesdeGrilla(RescateDesdeGrillaInView viewRequest);

	/**
	 * Descargar comprobante PDF de Rescate de un fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteRescatePDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteRescatePDF(ComprobanteRescateFondo viewRequest);
}
