/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Interface ComprobantesPagoTarjetasBO.
 */
public interface ComprobantesPagoTarjetasBO {

	/**
	 * Obtiene comprobantes de pago de tarjetas de manera asincronica.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoTarjetasAsync(Cliente cliente, TransaccionDTO dto,
			Boolean debitoAutomatico);

	/**
	 * Obtiene comprobantes de pago de tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPagoTarjetas(Cliente cliente, TransaccionDTO dto,
			Boolean debitoAutomatico);

	Respuesta<ComprobantesDTO> obtenerComprobantesPagoTarjetasPorCuenta(Cliente cliente, TransaccionDTO dto,
			Boolean debitoAutomatico, Cuenta cuenta);

}
