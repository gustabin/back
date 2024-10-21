/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Interface ComprobantesDebitoAutomaticoEnCuentaBO.
 */
public interface ComprobantesDebitoAutomaticoEnCuentaBO {

	/**
	 * Obtener comprobantes debito automatico en cuenta async.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesDebitoAutomaticoEnCuentaAsync(Cliente cliente);

	/**
	 * Obtener comprobantes debito automatico en cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesDebitoAutomaticoEnCuenta(Cliente cliente);

	/**
	 * Obtener comprobantes debito automatico en cuenta filtrados async.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(Cliente cliente,
			TransaccionDTO dto);

	Respuesta<ComprobantesDTO> obtenerComprobantesDebitoAutomaticoEnCuentaPorEmpresa(Cliente cliente,
			EmpresaAdheridaEntity empresa, TransaccionDTO dto);

}
