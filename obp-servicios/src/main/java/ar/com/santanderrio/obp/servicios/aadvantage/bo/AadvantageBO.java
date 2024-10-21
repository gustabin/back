/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageInDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageOutDTO;

/**
 * The Interface AadvantageBO.
 */
public interface AadvantageBO {

	/**
	 * Consultar millas aadvantage.
	 *
	 * @param inDTO
	 *            the in DTO
	 * @return the respuesta
	 */
	Respuesta<SolicitudMillasAadvantageOutDTO> consultarMillasAadvantage(SolicitudMillasAadvantageInDTO inDTO);
	
}
