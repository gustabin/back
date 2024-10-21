/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DatosComprobanteTransferencia;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.ComprobanteNuevaTransferencia;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.DetalleCuentaCBUBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.NuevaTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.SelectorCuentasBancaPrivadaView;

/**
 * The Interface CuentasBancaPrivadaManager.
 */
public interface CuentasBancaPrivadaManager {

	/**
	 * Inicio de cuentas banca privada.
	 *
	 * @return the respuesta
	 */
	Respuesta<CuentasIntermedioView> obtenerInicioCuentas();

	/**
	 * Modificacion de apodo cuenta banca privada.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param alias
	 *            the alias
	 * @return the respuesta
	 */
	Respuesta<Void> modificarApodo(String numeroCuenta, String alias);

	/**
	 * Ver detalle de CBU cuenta banca privada.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 */
	Respuesta<DetalleCuentaCBUBancaPrivadaView> verDetalleCBU(String numeroCuenta);

	/**
	 * Obtener cuentas.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<SelectorCuentasBancaPrivadaView> obtenerCuentas(ConsultaCuentaView cuenta);
	
	/**
	 * Descargar comprobante CBU cuenta.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteCBUCuenta();

	/**
	 * Copiar CBU.
	 *
	 * @return the respuesta
	 */
	Respuesta<CopiarMensajeView> copiarCBU();

	/**
	 * Compartir CBU.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> compartirCBU();

	/**
	 * Nueva transferencia.
	 *
	 * @return the respuesta
	 */
	Respuesta<NuevaTransferenciaView> nuevaTransferencia();

	/**
	 * Obtener interviniente.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	Respuesta<IntervinienteView> obtenerInterviniente(IntervinienteinEntity entity);
	
	/**
	 * Ver detalle movimiento.
	 *
	 * @return the respuesta
	 */
	Respuesta<DetalleMovimientosView> verDetalleMovimiento();

	/**
	 * Confirmar transferencia.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	Respuesta<ConfirmarTransferenciaView> confirmarTransferencia(ConfirmarTransferenciaInEntity entity);

	/**
	 * Ver comprobante transferencia.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteTransferenciaView> verComprobanteTransferencia(DatosComprobanteTransferencia viewRequest);

	/**
	 * Descargar comprobante PDF nueva transferencia.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobantePDFNuevaTransferencia(ComprobanteNuevaTransferencia viewRequest);
	
	Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(String nroCuenta);
	
}