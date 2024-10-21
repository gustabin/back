/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.manager;

import java.util.List;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
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

/**
 * The Interface PagoHaberesManager.
 *
 * @author Administrator
 *
 */
public interface PagoHaberesManager {

	/**
	 * Checks if is cliente habilitado.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> isClienteHabilitado();

	/**
	 * Obtener pago haberes.
	 *
	 * @return the respuesta
	 */
	Respuesta<PagoHaberesView> obtenerPagoHaberes();

	/**
	 * Validar empleado.
	 *
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return the respuesta
	 */

	Respuesta<PagoInformadoView> validarEmpleado(PagoInformadoView pagoInformadoView);

	/**
	 * Agregar empleado.
	 *
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return the respuesta
	 */

	Respuesta<ComprobanteAdhesionEmpleadoEntity> agregarEmpleado(PagoInformadoView pagoInformadoView);

	/**
	 * Eliminar empleado.
	 *
	 * @param pagoHaberesEliminarView
	 *            the pago haberes eliminar view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> eliminarEmpleado(PagoHaberesEliminarView pagoHaberesEliminarView);

	/**
	 * Eliminar pago programado.
	 *
	 * @param pagoHaberesEliminarView
	 *            the pago haberes eliminar view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> eliminarPagoProgramado(PagoHaberesEliminarView pagoHaberesEliminarView);

	/**
	 * ver Detalle Empleado.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> verDetalleEmpleado();

	/**
	 * ver detalle pago agendado.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> verDetallePagoAgendado();

	/**
	 * Obtener cuentas.
	 *
	 * @return the AgregarEmpleadoView
	 */
	Respuesta<AgregarEmpleadoView> obtenerCuentas();

	/**
	 * validaciones Pago Por CBU.
	 *
	 * @param validarCBUView
	 *            the validar CBU view
	 * @return the respuesta
	 */
	Respuesta<DatosDestinatarioView> validacionesPagoPorCBU(ValidacionesPagoPorCBUView validarCBUView);

	/**
	 * pago Haberes CBU.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return the respuesta
	 */
	Respuesta<ComprobantePagoHaberesCBUEntity> pagoHaberesCBU(
			ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU);

	/**
	 * Generar comprobante adhesion.
	 *
	 * @param comprobanteAdhesionEmpleado
	 *            the comprobante adhesion empleado
	 * @return the ReporteView
	 */
	Respuesta<ReporteView> generarComprobanteAdhesion(ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado);

	/**
	 * Generar comprobante pago por CBU.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobantePagoPorCBU(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU);

	/**
	 * Realiza todas las validaciones antes de realizar el pago puntual a uno o
	 * varios empleados.
	 *
	 * @param validarPagoSimpleMultipleView
	 *            the validar pago simple multiple view
	 * @return the respuesta
	 */
	Respuesta<ValidacionesRespuestaEntity> validacionesPagoSimpleMultiple(
			ValidarPagoSimpleMultipleView validarPagoSimpleMultipleView);

	/**
	 * Realiza el pago puntual a uno o varios empleados .
	 *
	 * @param comprobantePagoHaberesPagoSimpleMultiple
	 *            the comprobante pago haberes pago simple multiple
	 * @return the respuesta
	 */
	Respuesta<ComprobantePagoHaberesPagoSimpleMultipleEntity> realizarPagoHaberes(
			ComprobantePagoHaberesPagoSimpleMultipleEntity comprobantePagoHaberesPagoSimpleMultiple);

	/**
	 * Retorna el importe para la lista de cuentas selccionada por el usuario.
	 *
	 * @param importeCuentasView
	 *            the importe cuentas view
	 * @return the respuesta
	 */
	Respuesta<List<ImporteCuentasView>> obtenerImportesCuentasDebitar(List<ImporteCuentasView> importeCuentasView);

	/**
	 * Genera un reporte para un empleado.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobantePagoSimpleMultiple(ComprobantePagoHaberesCBUEntity comprobante);

    /**
	 * Obtener limites horarios.
	 *
	 * @return the respuesta
	 */
    Respuesta<String> obtenerLimitesHorarios();
}
