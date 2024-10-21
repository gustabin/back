/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;

/**
 * The Interface UltimosConsumosTarjetaBO.
 */
public interface UltimosConsumosTarjetaBO {

	/**
	 * Obtener ultimos consumos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<UltimosConsumosDTO> obtenerUltimosConsumos(Cuenta cuenta);

	/**
	 * Obtener reporte.
	 *
	 * @param body
	 *            the body
	 * @param proceso
	 *            the proceso
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente);

	/**
	 * Obtener marca de tarjeta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	String obtenerMarcaDeTarjeta(IdentificacionCuenta identificacionCuenta, Cliente cliente) throws BusinessException;

	/**
	 * Generar ultimos consumos DTO.
	 *
	 * @param consumosDTO
	 *            the consumos DTO
	 * @param cuenta
	 *            the cuenta
	 * @return the ultimos consumos DTO
	 */
	UltimosConsumosDTO generarUltimosConsumosDTO(List<ConsumoTarjetaDTO> consumosDTO, Cuenta cuenta);

}
