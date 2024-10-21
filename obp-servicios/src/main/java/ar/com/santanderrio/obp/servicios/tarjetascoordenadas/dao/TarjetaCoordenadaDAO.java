/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetascoordenadas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.ValidarCoordenada;

/**
 * The Interface TarjetaCoordenadaDAO.
 */
public interface TarjetaCoordenadaDAO {

	/**
	 * Solicitar coordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pedidoCoordenada
	 *            the pedido coordenada
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<TarjetaCoordenada> solicitarCoordenadas(Cliente cliente, PedidoCoordenada pedidoCoordenada)
			throws DAOException;

	/**
	 * Validar coordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param validarCoordenada
	 *            the validar coordenada
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<Boolean> validarCoordenadas(Cliente cliente, ValidarCoordenada validarCoordenada) throws DAOException;

	/**
	 * Solicitar coordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param coordenadasOperacionDTO
	 *            the coordenadas operacion DTO
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException 
	 */
	Respuesta<CoordenadasOperacionDTO> solicitarCoordenadas(Cliente cliente,
			CoordenadasOperacionDTO coordenadasOperacionDTO) throws DAOException;

	/**
	 * Validar coordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param coordenadasOperacionDTO
	 *            the coordenadas operacion DTO
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<CoordenadasOperacionDTO> validarCoordenadas(Cliente cliente,
			CoordenadasOperacionDTO coordenadasOperacionDTO) throws DAOException;

	
	/**
	 * Filtro Nups clientes a los que se les dio de baja la tarjeta de coordenadas
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<String> obtener() throws DAOException;
	
	/**
	 * Filtro Nups para cambiar texto en pantalla confirmacion de operacion TCOORD  
	 * para clientes a los que se les dara de baja la tarjeta de coordenadas proximamente
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<String> obtenerNupsProximaBajaTarjCoord(ExternalPropertyType archivo) throws DAOException;
}
