/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.home.entitites.ListaTarjetasDTO;
import ar.com.santanderrio.obp.servicios.home.entitites.TarjetaHomeDTO;

/**
 * BO De tarjetas para la home principal.
 *
 * @author B039543
 */
public interface TarjetasHomeBO {

	/**
	 * Obtengo las tarjetas del cliente </br>
	 * En caso de que sea titular de alguna tarjeta y las adicionales no tengan
	 * consumos, las adicionales no se retornan </br>
	 * En caso de no ser titular de ninguna tarjeta se traen todas las
	 * adicionales.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ListaTarjetasDTO> obtenerTarjetas(Cliente cliente);

	/**
	 * Obtener saldo tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	Respuesta<TarjetaHomeDTO> obtenerSaldoTarjeta(Cuenta cuenta);

}
