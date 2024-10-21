/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenada.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo.TarjetaCoordenadaBO;
import ar.com.santanderrio.obp.servicios.tarjetacoordenada.manager.TarjetaCoordenadaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.ValidarCoordenada;

/**
 * Clase TarjetaCoordenadaManagerImpl.
 */
@Component
public class TarjetaCoordenadaManagerImpl implements TarjetaCoordenadaManager {

	/** Constante logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaCoordenadaManagerImpl.class);

	/** Variable tarjeta coordenada service. */
	@Autowired
	private TarjetaCoordenadaBO tarjetaCoordenadaBO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/**
	 * Solicita las coordenadas al servicio IATX para mostrar al usuario.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TarjetaCoordenada> solicitarCoordenadas(Cliente cliente, PedidoCoordenada pedidoCoordenada) {
		return tarjetaCoordenadaBO.solicitarCoordenadas(cliente, pedidoCoordenada);
	}

	/**
	 * Valida las coordenadas ingresadas por el usuario contra el servicio IATX.
	 *
	 * @param cliente
	 *            the cliente
	 * @param validarCoordenada
	 *            the validar coordenada
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Boolean> validarCoordenadas(Cliente cliente, ValidarCoordenada validarCoordenada) {
		return tarjetaCoordenadaBO.validarCoordenadas(cliente, validarCoordenada);
	}

}
