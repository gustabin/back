/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cotizacion.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;

/**
 * Interfaz CotizacionBO.
 */
public interface CotizacionBO {

	/**
	 * Obtiene la cotizacion del dolar del dia.
	 *
	 * @param cliente
	 *            El cliente en cuestion
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @param divisa
	 *            the divisa
	 * @return El objeto CotizacionDTO
	 * @throws BusinessException
	 *             the business exception
	 */
	CotizacionDTO obtenerDatosCotizacion(Cliente cliente, DatosTarjeta datoTarjeta, String divisa)
			throws BusinessException;

	/**
	 * Obtener datos cotizacion sin excepcion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @param divisa
	 *            the divisa
	 * @return the cotizacion DTO
	 */
	CotizacionDTO obtenerDatosCotizacionSinExcepcion(Cliente cliente, DatosTarjeta datoTarjeta, String divisa);

}
