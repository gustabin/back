/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenada.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.ValidarCoordenada;

/**
 * Interfaz TarjetaCoordenadaManager.
 */
public interface TarjetaCoordenadaManager {

	/**
	 * Obtiene tarjeta coordenadas mediante PedidoCoordenada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 * @return the respuesta
	 */
	Respuesta<TarjetaCoordenada> solicitarCoordenadas(Cliente cliente, PedidoCoordenada pedidoCoordenada);

	/**
	 * Valida las coordenadas ingresadas desde frontend.
	 *
	 * @param cliente
	 *            the cliente
	 * @param validarCoordenada
	 *            the validar coordenada
	 * @return the respuesta
	 */
	Respuesta<Boolean> validarCoordenadas(Cliente cliente, ValidarCoordenada validarCoordenada);

}
