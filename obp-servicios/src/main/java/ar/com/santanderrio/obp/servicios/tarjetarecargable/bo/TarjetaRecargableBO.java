/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableInDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;

/**
 * The Interface TarjetaRecargableBO.
 */
public interface TarjetaRecargableBO {

	/**
	 * Alta solicitud tarjeta recargable.
	 *
	 * @param datosSolicitudTarjetaRecargableInDTO
	 *            the datos solicitud tarjeta recargable in DTO
	 * @return the respuesta
	 */
	Respuesta<DatosSolicitudTarjetaRecargableDTO> altaSolicitudTarjetaRecargable(
			DatosSolicitudTarjetaRecargableInDTO datosSolicitudTarjetaRecargableInDTO);

	/**
	 * Comprobante solicitud tarjeta recargable.
	 *
	 * @param datosComprobante
	 *            the datos comprobante
	 * @return the respuesta
	 */
	Respuesta<Reporte> comprobanteSolicitudTarjetaRecargable(
			DatosComprobanteSolicitudTarjetaRecargableView datosComprobante);
}
