/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenada.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.ValidarCoordenada;

/**
 * Interfaz TarjetaCoordenadaBO.
 */
public interface TarjetaCoordenadaBO extends BusinessObject {

	/**
	 * Solicitar coordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 * @return the respuesta
	 */
	Respuesta<TarjetaCoordenada> solicitarCoordenadas(Cliente cliente, PedidoCoordenada pedidoCoordenada);

	/**
	 * Validar coordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param validarCoordenada
	 *            the validar coordenada
	 * @return the respuesta
	 */
	Respuesta<Boolean> validarCoordenadas(Cliente cliente, ValidarCoordenada validarCoordenada);

	/**
	 * Solicitar coordenadas.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @param codigoEstadisticaSolicitud
	 *            the codigo estadistica solicitud
	 * @param soloEstaVerificandoSiHayDesafios
	 *            the solo esta verificando si hay desafios
	 * @return the respuesta
	 */
	Respuesta<AutentificacionDTO> solicitarCoordenadas(AutentificacionDTO autentificacionDTO,
			String codigoEstadisticaSolicitud, boolean soloEstaVerificandoSiHayDesafios);

	/**
	 * Validar coordenadas.
	 *
	 * @param dto
	 *            the dto
	 * @param codigoEstadisticaValidacion
	 *            the codigo estadistica validacion
	 * @return the respuesta
	 */
	Respuesta<AutentificacionDTO> validarCoordenadas(AutentificacionDTO dto, String codigoEstadisticaValidacion);

}
