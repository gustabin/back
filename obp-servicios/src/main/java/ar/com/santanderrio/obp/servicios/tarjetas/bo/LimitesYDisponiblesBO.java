/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;

/**
 * The Interface LimitesYDisponiblesBO.
 */
public interface LimitesYDisponiblesBO {

	/**
	 * Obtener limite Y disponible DTO.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param retornoWS
	 *            the retorno WS
	 * @return the list
	 */
	LimitesYDisponiblesDTO obtenerLimiteYDisponibleDTO(Cuenta cuenta, RetornoTarjetasEntity retornoWS);

	/**
	 * Buscar limite Y disponible de cuenta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @param listaLimitesYDisponibles
	 *            the lista limites Y disponibles
	 * @return the limites Y disponibles DTO
	 */
	LimitesYDisponiblesDTO buscarLimiteYDisponibleDeCuenta(IdentificacionCuenta identificacionCuenta, Cliente cliente,
			List<LimitesYDisponiblesDTO> listaLimitesYDisponibles);

}
