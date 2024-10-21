/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobanteAdhesionEmpleadoEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesPagoSimpleMultipleEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ImporteCuentasView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.AgregarEmpleadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesEliminarView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesRespuestaEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidarPagoSimpleMultipleView;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface PagoHaberesSEI.
 */
@Path("/pagoHaberes")
public interface PagoHaberesSEI {

	/**
	 * Get the PagoHaberes.
	 *
	 * @return PagoHaberes
	 */
	@POST
	@Path("/obtenerEmpleados")
	@CustomPreAuthorize(AccionController.IR_INICIO_PAGO_DE_HABERES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<PagoHaberesView> obtenerPagoHaberes();

	/**
	 * eliminar Empleado.
	 *
	 * @param pagoHaberesEliminarView
	 *            the pago haberes eliminar view
	 * @return PagoHaberes
	 */
	@POST
	@Path("/eliminarEmpleado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackMensajeView> eliminarEmpleado(PagoHaberesEliminarView pagoHaberesEliminarView);

	/**
	 * eliminar Agendamiento.
	 *
	 * @param pagoHaberesEliminarView
	 *            the pago haberes eliminar view
	 * @return PagoHaberes
	 */
	@POST
	@Path("/eliminarAgendamiento")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackMensajeView> eliminarPagoProgramado(PagoHaberesEliminarView pagoHaberesEliminarView);

	/**
	 * ver Detalle Empleado.
	 *
	 * @return PagoHaberes
	 */
	@POST
	@Path("/verDetalleEmpleado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verDetalleEmpleado();

	/**
	 * ver Detalle Pago Agendado.
	 *
	 * @return PagoHaberes
	 */
	@POST
	@Path("/verDetallePagoAgendado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verDetallePagoAgendado();

	/**
	 * validar Empleado.
	 *
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return PagoHaberes
	 */
	@POST
	@Path("/validarEmpleado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoInformadoView> validarEmpleado(PagoInformadoView pagoInformadoView);

	/**
	 * agregar Empleado.
	 *
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return PagoHaberes
	 */
	@POST
	@Path("/agregarEmpleado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteAdhesionEmpleadoEntity> agregarEmpleado(PagoInformadoView pagoInformadoView);

	/**
	 * obtener Cuentas.
	 *
	 * @return AgregarEmpleadoView
	 */
	@POST
	@Path("/obtenerCuentas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AgregarEmpleadoView> obtenerCuentas();

	/**
	 * validacionesPagoPorCBU.
	 *
	 * @param validarCBUView
	 *            the validar CBU view
	 * @return Feedback Mensaje View
	 */
	@POST
	@Path("/validacionesPagoPorCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DatosDestinatarioView> validacionesPagoPorCBU(ValidacionesPagoPorCBUView validarCBUView);

	/**
	 * pago Haberes CBU.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return Feedback Comprobante Pago Haberes CBU
	 */
	@POST
	@Path("/pagoHaberesCBU")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobantePagoHaberesCBUEntity> pagoHaberesCBU(
			ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU);

	/**
	 * Genera un reporte con el comprobante de la adhesion.
	 *
	 * @param comprobanteAdhesionEmpleado
	 *            the comprobante adhesion empleado
	 * @return the respuesta
	 */
	@POST
	@Path("/descargaComprobanteAgregarEmpleado")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobanteAdhesion(ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado);

	/**
	 * Genera un reporte con el comprobante del pago por CBU.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return the respuesta
	 */
	@POST
	@Path("/descargaComprobantePagoPorCBU")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobantePagoPorCBU(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU);

	/**
	 * Realiza todas las validaciones de pago simple-multiple a empleados, antes
	 * de realizar el pago.
	 *
	 * @param validarPagoSimpleMultipleView
	 *            the validar pago simple multiple view
	 * @return the respuesta
	 */
	@POST
	@Path("/validacionesPagoSimpleMultiple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ValidacionesRespuestaEntity> validacionesPagoSimpleMultiple(
			ValidarPagoSimpleMultipleView validarPagoSimpleMultipleView);

	/**
	 * Realiza el pago a uno o multiples empleados.
	 *
	 * @param comprobantePagoHaberesPagoSimpleMultiple
	 *            the comprobante pago haberes pago simple multiple
	 * @return the respuesta
	 */
	@POST
	@Path("/pagoHaberesSimpleMultiple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> realizarPagoHaberes(
			ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple);

	/**
	 * Retorna el importe de las cuentas seleccionadas.
	 *
	 * @param importeCuentasView
	 *            the importe cuentas view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerImportesCuentasDebitar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<ImporteCuentasView>> obtenerImportesCuentasDebitar(List<ImporteCuentasView> importeCuentasView);

	/**
	 * Genera un reporte para un empleado.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	@POST
	@Path("/generarComprobantePagoSimpleMultiple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> generarComprobantePagoSimpleMultiple(ComprobantePagoHaberesCBUEntity comprobante);
	
	/**
	 * Obtener limites horarios.
	 *
	 * @return the respuesta
	 */
	@POST
    @Path("/limitesHorarios")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<String> obtenerLimitesHorarios();
}