/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Interface ComprobantesTransferenciasUnificadoBO.
 */
public interface ComprobantesTransferenciasUnificadoBO {

	/**
	 * Obtiene los comprobantes de transferencias de manera asincronica con
	 * fecha.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesTransferenciaAsync(Cliente cliente,
			TransaccionDTO transaccion);

	/**
	 * Obtiene los comprobantes de transferencias de manera asincronica con
	 * filtro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesTransferencia(Cliente cliente, TransaccionDTO transaccion);
	
	Respuesta<ComprobantesDTO> obtenerComprobantesTransferenciaRioRio(Cliente cliente, TransaccionDTO transaccion);

	Respuesta<ComprobantesDTO> obtenerComprobantesTransferenciaOtrosBancos(Cliente cliente, TransaccionDTO transaccion);


}
