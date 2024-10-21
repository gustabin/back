/*
 * editado: 23/08/2016 15:29:19 - b039542 - ignacio_valek@itrsa.com.ar
 *
 */
package ar.com.santanderrio.obp.servicios.pagos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagosPendientesView;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface PagosSEI.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 29/11/2016
 */
@Path("/pagos")
public interface PagosSEI {

	/**
	 * Gets the pagos pendientes.
	 *
	 * @param consultaPagosView
	 *            the consulta pagos view
	 * @return the pagos pendientes
	 */
	@POST
	@Path("/obtenerPagosPendientes")
	@CustomPreAuthorize(AccionController.IR_INICIO_CALENDARIO_DE_PAGOS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagosPendientesView> getPagosPendientes(ConsultaPagosView consultaPagosView);

	/**
	 * Solicitar stop debit.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarStopDebit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> solicitarStopDebit(PagoPendienteView pagoPendienteView);

	/**
	 * Ejecutar stop debit.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarStopDebit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> ejecutarStopDebit(PagoPendienteView pagoPendienteView);

	/**
	 * Informacion adicional para servicios de Pagomiscuentas. Alta,
	 * modificacion de informacion adicional del cliente para servicios que son
	 * devueltos por el servicio de pago mis cuentas.
	 * 
	 * DTF:16416
	 * 
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/altaInformacionAdicional")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Boolean> altaInformacionAdicional(PagoPendienteView pagoPendienteView);

	/**
	 * 
	 * Inicio de modificar adhesion a un pago automatico. Estadisticas y
	 * sesion-contador. DTFs: 14368, 10281
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarModificarAdhesionPagoAuto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> solicitarAdhesionPagoAuto(PagoPendienteView pagoPendienteView);

	/**
	 * 
	 * Modificar adhesion a un pago automatico. cambia monto limite y cuenta de
	 * extraccion. pagoPendienteView representa una adhesion. DTFs: 14368, 10281
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/modificarAdhesionPagoAuto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> modificarAdhesionPagoAuto(PagoPendienteView pagoPendienteView);

	/**
	 * 
	 * Baja adhesion a un pago automatico. pagoPendienteView representa una
	 * adhesion. DTFs: 9802, 10281
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarBajaAdhesionPagoAuto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> solicitarBajaAdhesion(PagoPendienteView pagoPendienteView);

	/**
	 * Ejecutar baja adhesion.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarBajaAdhesionPagoAuto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> ejecutarBajaAdhesion(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar eliminacion pago puntual.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarEliminacionPagoPuntual")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> solicitarEliminacionPago(PagoPendienteView pagoPendienteView);

	/**
	 * Eliminar pago puntual.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/eliminarPago")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackMensajeView> eliminarPago(PagoPendienteView pagoPendienteView);

	/**
	 * Ver detalle de un pago pendiente.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> verDetalle(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar adhesion debito automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarAdhesionDebitoAutomatico")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> solicitarAdhesionDebitoAutomatico(PagoPendienteView pagoPendienteView);

	/**
	 * Solicitar adhesion pago automatico.
	 *
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarAdhesionPagoAutomatico")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoPendienteView> solicitarAdhesionPagoAutomatico(PagoPendienteView pagoPendienteView);

	/**
	 * Baja Tarjeta de Credito de pago Programado. ver: DTF: 10303 iatx:
	 * CNSTJCPAGP
	 *
	 * @author B041299 Manuel.Vargas
	 * @param pagoPendienteView
	 *            the pago pendiente view
	 * @return the respuesta
	 */
	@POST
	@Path("/bajaPagoProgramadoDeTarjetaCredito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackMensajeView> bajaPagoProgramadoDeTarjetaCredito(PagoPendienteView pagoPendienteView);
}
