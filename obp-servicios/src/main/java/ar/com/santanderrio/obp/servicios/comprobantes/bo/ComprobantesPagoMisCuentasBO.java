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
 * BO de comprobantes pago mis cuentas.
 */
public interface ComprobantesPagoMisCuentasBO {

	/**
	 * Obtiene los comprobantes de pago mis cuentas del cliente solicitado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPagoMisCuentas(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * Obtiene los comprobantes de pago mis cuentas de manera asincronica con
	 * fecha.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAsync(Cliente cliente, TransaccionDTO transaccion);

}
