/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.bo;

import java.util.Date;
import java.util.List;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobanteAdhesionEmpleadoEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.DatosEmpleadoPagoHaberesSimpleMultipleEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.PagoHaberesEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesEliminarView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesRespuestaEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidarPagoSimpleMultipleView;

/**
 * The Interface PagoHaberesBO.
 */
/**
 * @author Administrator
 *
 */
public interface PagoHaberesBO {

	/**
	 * Obtener consulta agendamiento 7 x 24.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<PagoHaberesEntity> obtenerConsultaAgendamiento7x24(Cliente cliente);

	/**
	 * Checks if is cliente adherido pago haberes.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Boolean> isClienteAdheridoPagoHaberes(Cliente cliente);

	/**
	 * Validar empleado a agregar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return the respuesta
	 */
	Respuesta<PagoInformadoView> validarEmpleado(Cliente cliente, PagoInformadoView pagoInformadoView);

	/**
	 * Agregar empleado 7 x 24.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return the respuesta
	 */

	Respuesta<ComprobanteAdhesionEmpleadoEntity> agregarEmpleado7x24(Cliente cliente,
			PagoInformadoView pagoInformadoView);

	/**
	 * Eliminar empleado 7 x 24.
	 *
	 * @param clienteIngresado
	 *            the cliente ingresado
	 * @param pagoHaberesEliminarView
	 *            the pago haberes eliminar view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> eliminarEmpleado7x24(Cliente clienteIngresado,
			PagoHaberesEliminarView pagoHaberesEliminarView);

	/**
	 * Eliminar pago programado 7 x 24.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoHaberesEliminarView
	 *            the pago haberes eliminar view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> eliminarPagoProgramado7x24(Cliente cliente,
			PagoHaberesEliminarView pagoHaberesEliminarView);

	/**
	 * Validar CBU.
	 *
	 * @param cliente
	 *            the cliente
	 * @param validarCBUView
	 *            the validar CBU view
	 * @return the respuesta
	 */
	Respuesta<DatosDestinatarioView> validarCBU(Cliente cliente, ValidacionesPagoPorCBUView validarCBUView);

	/**
	 * Pago haberes CBU.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosDestinatarioView
	 *            the datos destinatario view
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the respuesta
	 */
	Respuesta<ComprobantePagoHaberesCBUEntity> pagoHaberesCBU(Cliente cliente,
			DatosDestinatarioView datosDestinatarioView, Cuenta cuentaOrigen);

	/**
	 * Generar comprobante adhesion.
	 *
	 * @param comprobanteAdhesionEmpleado
	 *            the comprobante adhesion empleado
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteAdhesion(ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado);

	/**
	 * Validar importe.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoCuentaOrigen
	 *            the tipo cuenta origen
	 * @param nroCuentaOrigen
	 *            the nro cuenta origen
	 * @param importe
	 *            the importe
	 * @return the respuesta
	 */
	Respuesta<DatosDestinatarioView> validarImporte(Cliente cliente, String tipoCuentaOrigen, String nroCuentaOrigen,
			String importe);

	/**
	 * Generar comprobante pago por CBU.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobantePagoPorCBU(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU);

	/**
	 * Validaciones pago simple multiple.
	 *
	 * @param date
	 *            the date
	 * @param today
	 *            the today
	 * @param validarPagoSimpleMultipleView
	 *            the validar pago simple multiple view
	 * @return the respuesta
	 */
	Respuesta<ValidacionesRespuestaEntity> validacionesPagoSimpleMultiple(Date date, Date today,
			ValidarPagoSimpleMultipleView validarPagoSimpleMultipleView);

	/**
	 * Valida que las cuentas y el importe de los empleados sean validos.
	 *
	 * @param pagoHaberesEmpleadosView
	 *            the pago haberes empleados view
	 * @return the respuesta
	 */
	Respuesta<Boolean> validarImporteCuentas(List<PagoInformadoView> pagoHaberesEmpleadosView);

	/**
	 * Realiza el pago puntual a un empleado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @param modoEjecucion
	 *            the modo ejecucion
	 * @param fecha
	 *            the fecha
	 * @return the respuesta
	 */
	Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> realizarPagoSimple(Cliente cliente,
			PagoInformadoView pagoInformadoView, String modoEjecucion, String fecha);

	/**
	 * Obtiene el importe de cada cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<DetalleCuentaEntity> obtenerDetalleCuenta(Cuenta cuenta);

	/**
	 * Genera un reporte de empleado.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobantePagoSimpleMultiple(ComprobantePagoHaberesCBUEntity comprobante);

	Boolean esTipoDeClaveCVU(ValidacionesPagoPorCBUView validacionesPagoPorCBUView);

	Boolean esCBUSantander(ValidacionesPagoPorCBUView validacionesPagoPorCBUView);
}