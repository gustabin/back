/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaOutDTO;

/**
 * The Interface EstadoYLimitesTarjetaCreditoBO.
 */
public interface EstadoYLimitesTarjetaCreditoBO {

	/**
	 * Obtener detalle datos tarjeta.
	 *
	 * @param consultaDetalleDatosTarjetaInDTO
	 *            the consulta detalle datos tarjeta in DTO
	 * @param cliente
	 *            the cliente
	 * @return the consulta detalle datos tarjeta out DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDetalleDatosTarjetaOutDTO obtenerDetalleDatosTarjeta(
			ConsultaDetalleDatosTarjetaInDTO consultaDetalleDatosTarjetaInDTO, Cliente cliente) throws DAOException;
}
