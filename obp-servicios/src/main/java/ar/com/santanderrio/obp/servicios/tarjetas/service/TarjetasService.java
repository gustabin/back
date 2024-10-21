/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.Service;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;

/**
 * The Interface TarjetasService.
 */
public interface TarjetasService extends Service {

	/**
	 * Obtener tarjetas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<DisponiblesYConsumoDTO> obtenerTarjetas(Cliente cliente) throws ServiceException;

	/**
	 * Servicio que actualiza la tarjeta favorita.
	 *
	 * @author florencia.n.martinez
	 * @param identificacionCuenta
	 *            the identificacion de cuenta
	 * @param cliente
	 *            the cliente
	 * @return Respuesta<Void>
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Void> actualizarTarjetaFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws ServiceException;

	/**
	 * Obtener tooltip favorito.
	 *
	 * @return the string
	 */
	String obtenerTooltipFavorito();

	/**
	 * Obtener tooltip no favorito.
	 *
	 * @return the string
	 */
	String obtenerTooltipNoFavorito();

	/**
	 * Es marca visa.
	 *
	 * @param marca
	 *            the marca
	 * @return the boolean
	 */
	Boolean esMarcaVisa(String marca);

	/**
	 * Es marca amex.
	 *
	 * @param marca
	 *            the marca
	 * @return the boolean
	 */
	Boolean esMarcaAmex(String marca);

	/**
	 * Corta el numero de tarjeta de la cuenta segun la marca.
	 *
	 * @author florencia.n.martinez
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param marca
	 *            the marca
	 * @return String
	 */
	String cortarNumeroTarjetaComoTarjetaActiva(String numeroTarjeta, String marca);

}
