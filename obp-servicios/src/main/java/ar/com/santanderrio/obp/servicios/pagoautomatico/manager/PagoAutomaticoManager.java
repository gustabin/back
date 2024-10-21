/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnCuentaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;

/**
 * The Interface NuevoPagoAutomaticoManager.
 */
public interface PagoAutomaticoManager {

	/**
	 * Devuelve el archivo de terminos y condiciones.
	 *
	 * @return the respuesta
	 */

	Respuesta<TerminosCondiciones> cargarTerminosCondiciones();

	/**
	 * Obtiene la lista de cuentas del cliente para realizar un pago automatico.
	 *
	 * @param registroSesion
	 *            the registro sesion
	 * @param adhesionDebitoAutomaticoView
	 *            the adhesion debito automatico view
	 * @return the respuesta
	 */
	Respuesta<AdhesionDebitoAutomaticoEnCuentaView> adherirDebitoEnCuenta(RegistroSesion registroSesion,
			AdhesionDebitoAutomaticoEnCuentaView adhesionDebitoAutomaticoView);

	/**
	 * Confirmar adhesion pago automatico.
	 *
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the respuesta
	 */
	Respuesta<AdhesionPagoAutomatico> confirmarAdhesionPagoAutomatico(AdhesionPagoAutomatico adhesionPagoAutomatico);

	/**
	 * Generar comprobante adhesion.
	 *
	 * @param comprobanteDebitoAutomatico
	 *            the comprobante debito automatico
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico);

	/**
	 * obtiene las cuentas del cliente tipo Banelco habilitadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<MedioPagoSelectionView> obtenerCuentas(Cliente cliente);

	/**
	 * Estadistica comprobante nuevo pago automatico.
	 */
	void estadisticaComprobanteNuevoPagoAutomatico();

	/**
	 * Valida que el cliente tenga las tarjetas necesarias para adhesion a
	 * debito automatico en tarjeta y envia a pantalla los datos para la
	 * configuracion de la misma.
	 *
	 * @param nombreFantasia
	 *            the nombre fantasia
	 * @return the respuesta
	 */
	Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(
			String nombreFantasia);

	/**
	 * Generar hash seguridad.
	 *
	 * @param datosParaHash
	 *            the datos para hash
	 * @return the boolean
	 */
	Boolean generarHashSeguridad(HashDebitoAutomaticoTarjetaView datosParaHash);

	/**
	 * Valida que el usuario posea segundo factor de autenticacion antes de
	 * intentar realizar una adhesion a pago automatico.
	 *
	 * @return respuesta
	 */
	Respuesta<Boolean> validarAutenticacionPagoAutomatico();

	
	/**
	 * @param comprobanteAdhesionDebitoAutomatico
	 * @return
	 */
	Respuesta<ReporteView> generarComprobanteAdhesionPagoAutomatico(ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico);


}
