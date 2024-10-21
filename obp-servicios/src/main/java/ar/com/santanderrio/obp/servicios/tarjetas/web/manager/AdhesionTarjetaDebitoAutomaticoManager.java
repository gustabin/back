/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.view.BajaAdhesionTarjView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosAdhesionDebitoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InfoTarjetaAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.NroTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaModificacionAdhesionDebitoView;

/**
 * The Interface AdhesionTarjetaDebitoAutomaticoManager.
 */
public interface AdhesionTarjetaDebitoAutomaticoManager {

	/**
	 * Tarjetas cuentas para debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param reintentar
	 *            the reintentar
	 * @return the respuesta
	 */
	Respuesta<InfoTarjetaAdhesionDebitoView> tarjetasCuentasParaDebito(Cliente cliente, Reintentar reintentar);

	/**
	 * Adherir tarjeta debito automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosAdhesionDebitoView
	 *            the datos adhesion debito view
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> adherirTarjetaDebitoAutomatico(Cliente cliente,
			DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView);

	/**
	 * Estadistica visualizacion comprobante adhesion debito.
	 */
	void estadisticaVisualizacionComprobanteAdhesionDebito();

	/**
	 * Inicio modificacion adhesion debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the respuesta
	 */
	Respuesta<TarjetaModificacionAdhesionDebitoView> inicioModificacionAdhesionDebito(Cliente cliente,
			NroTarjetaView nroTarjeta);

	/**
	 * Modificar adhesion debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosAdhesionDebitoView
	 *            the datos adhesion debito view
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> modificarAdhesionDebito(Cliente cliente,
			DatosAdhesionDebitoTarjetaView datosAdhesionDebitoView);

	/**
	 * Estadistica visualizacion comprobante modificacion debito.
	 */
	void estadisticaVisualizacionComprobanteModificacionDebito();

	/**
	 * Baja adhesion tarjeta.
	 *
	 * @param view
	 *            the view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> bajaAdhesionTarjeta(BajaAdhesionTarjView view, Cliente cliente);

}
