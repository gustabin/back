/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosSolicitanteTarjetaRecargableView;

/**
 * The Interface TarjetaRecargableManager.
 */
public interface TarjetaRecargableManager {

	/**
	 * Obtener datos iniciales.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosSolicitanteTarjetaRecargableView> obtenerDatosIniciales();

	/**
	 * Alta solicitud tarjeta recargable.
	 *
	 * @param datosSolicitudTarjetaRecargableView
	 *            the datos solicitud tarjeta recargable view
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> altaSolicitudTarjetaRecargable(
			ComprobanteAltaSolicitudTarjetaRecargableView datosSolicitudTarjetaRecargableView);

	/**
	 * Comprobante solicitud tarjeta recargable.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> comprobanteSolicitudTarjetaRecargable();

	/**
	 * Vaciar desafio.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> vaciarDesafio();
}
