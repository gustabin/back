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
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteTransferenciaFondo;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TransferenciaView;

/**
 * The Interface TransferenciaFondoSEI.
 *
 * @author
 */
@Path("/transferenciaFondos")
public interface TransferenciaFondoSEI {

	/**
	 * Devuelve una lista de cuentas titulo con los fondos que se pueden
	 * transferir y una lista de fondos hacia donde transferir.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/iniciarTransferencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CuentasConsultaFondoView> iniciarTransferencia(CuentasConsultaFondoView viewRequest);

	/**
	 * Iniciar transferencia bpriv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/iniciarTransferenciaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CuentasConsultaFondoView> iniciarTransferenciaBpriv(CuentasConsultaFondoView viewRequest);

	/**
	 * Obtener datos config.
	 *
	 * @param configTransferenciaInView
	 *            the config transferencia in view
	 * @return the respuesta
	 */
	@POST
	@Path("/configurarTransferencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigTransferenciaView> obtenerDatosConfig(ConfigTransferenciaInView configTransferenciaInView);

	/**
	 * Realizar la confirmación de una transferencia para un cliente de Banca
	 * Personal.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarTransferenciaFondos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> finalizarTransferenciaFondos(TransferenciaView viewRequest);

	/**
	 * Simular transferencia.
	 *
	 * @param simulacionTransferenciaInView
	 *            the simulacion transferencia in view
	 * @return the respuesta
	 */
	@POST
	@Path("/simularTransferencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimulacionTransferenciaView> simularTransferencia(
			SimulacionTransferenciaInView simulacionTransferenciaInView);

	/**
	 * Simular transferencia bpriv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularTransferenciaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimulacionTransferenciaView> simularTransferenciaBpriv(SimulacionInView viewRequest);

	/**
	 * Grabar estadistica gastos B priv.
	 *
	 * @param requestView
	 *            the request view
	 */
	@POST
	@Path("/grabarEstadisticaGastosBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaGastosBPriv(ConfigTransferenciaInView requestView);

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
	 * Finalizar transferencia fondos bpriv.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarTransferenciaFondosBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarTransferenciaFondoView> finalizarTransferenciaFondosBpriv(
			FinalizarTransferenciaFondoInView viewRequest);

	/**
	 * Descargar comprobante PDF de Rescate de un fondo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteTransferenciaPDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteTransferenciaPDF(ComprobanteTransferenciaFondo viewRequest);

}
