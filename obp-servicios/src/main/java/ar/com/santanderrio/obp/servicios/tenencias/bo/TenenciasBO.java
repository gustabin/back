/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.dto.FirmantesDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TipoClienteCitiDTO;

/**
 * Gestiona la logica de negocio relacionada a Tenencias.
 * 
 * @author
 *
 */
public interface TenenciasBO {

	/**
	 * realiza consultar de Tenencias.
	 *
	 * @param reqTenencias
	 *            the req tenencias
	 * @return respuesta con el objeto dto response.
	 */
	Respuesta<TenenciasDTO> consultarTenencias(TenenciasInDTO reqTenencias);

	/**
	 * realiza consultar de Firmantes.
	 *
	 * @param reqFrimantes
	 *            the req frimantes
	 * @return respuesta con el objeto dto response.
	 */
	Respuesta<FirmantesDTO> consultarFirmantes(TenenciasInDTO reqFrimantes);
	
	/**
	 * realiza consultar de tipo cliente citi Tenencias.
	 *
	 * @param reqCliTenencias
	 *            the req reqCliTenencias
	 * @return respuesta con el objeto dto response.
	 */
	Respuesta<TipoClienteCitiDTO> consultarTipClienteTenencias(TenenciasInDTO reqCliTenencias);
}