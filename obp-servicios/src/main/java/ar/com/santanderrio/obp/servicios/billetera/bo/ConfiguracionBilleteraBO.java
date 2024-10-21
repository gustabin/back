/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;

/**
 * The Interface ConfiguracionBilleteraBO.
 */
public interface ConfiguracionBilleteraBO {

	/**
	 * Configurar billetera.
	 *
	 * @param inDTO
	 *            the dto
	 * @return the respuesta
	 */
	Respuesta<BilleteraDTO> configurarBilletera(BilleteraInDTO inDTO);

	/**
	 * Generar comprobante.
	 *
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobante();

}