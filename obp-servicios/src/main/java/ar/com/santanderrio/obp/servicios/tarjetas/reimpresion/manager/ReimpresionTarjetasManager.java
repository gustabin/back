/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.SolicitudReimpresionTarjetasView;

/**
 * The Interface ReimpresionTarjetasManager.
 */
public interface ReimpresionTarjetasManager {

	/**
	 * Gets the datos iniciales.
	 *
	 * @return the datos iniciales
	 */
	public Respuesta<SolicitudReimpresionTarjetasView> getDatosIniciales();

	/**
	 * Gets the datos del solicitante.
	 *
	 * @return lista de tarjetas candidatas
	 */
	public Respuesta<DomiciliosDisponiblesView> getDomiciliosDisponibles();

	/**
	 * Gets the datos del solicitante.
	 *
	 * @param altaDatosReimpresionTarjetasView
	 *            the alta datos reimpresion tarjetas view
	 * @return lista de tarjetas candidatas
	 */
	public Respuesta<AltaDatosReimpresionTarjetasView> altaReimpresionTarjetas(
			AltaDatosReimpresionTarjetasView altaDatosReimpresionTarjetasView);

	/**
	 * Descargar comprobante.
	 *
	 * @return the respuesta
	 */
	public Respuesta<ReporteView> descargarComprobante();

}
