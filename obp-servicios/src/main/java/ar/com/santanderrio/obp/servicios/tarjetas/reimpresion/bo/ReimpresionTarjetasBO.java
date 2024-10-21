/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ComprobanteAltaReimpresionTarjetas;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetas;

/**
 * The Interface ReimpresionTarjetasBO.
 */
public interface ReimpresionTarjetasBO {

	/**
	 * Gets the tarjetas candidatas reimpresion.
	 *
	 * @return the tarjetas candidatas reimpresion
	 */
	Respuesta<List<TarjetaCandidataDTO>> getTarjetasCandidatasReimpresion();

	/**
	 * Solicitud reimpresion tarjetas.
	 *
	 * @param altaDatosReimpresionTarjetas
	 *            the alta datos reimpresion tarjetas
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaReimpresionTarjetas> solicitudReimpresionTarjetas(
			AltaDatosReimpresionTarjetas altaDatosReimpresionTarjetas);

	/**
	 * Descargar comprobante.
	 *
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	Respuesta<Reporte> descargarComprobante(DatosReimpresionComprobante datos);

}
