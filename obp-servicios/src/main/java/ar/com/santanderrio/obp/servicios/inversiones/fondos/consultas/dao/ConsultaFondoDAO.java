/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao;

import java.util.List;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;

/**
 * The Interface ConsultaFondoDAO.
 */
public interface ConsultaFondoDAO {

	/**
	 * Obtiene todos los Fondos.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ConsultaFondoOutEntity> obtenerFondos() throws DAOException;

	/**
	 * Obtiene un fondo por ID, si no lo encuentra lanza la excepcion.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the consulta fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaFondoOutEntity obtenerPorCodigo(String codigo) throws DAOException;
	
	/**
	 * Obtiene un fondo por ID, si no lo encuentra lanza la excepcion.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the consulta fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaFondoOutEntity obtenerPorCodigo(int codigo) throws DAOException;

	/**
	 * Obtiene la lista de fondos de la banca.
	 *
	 * @param banca
	 *            the banca
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ConsultaFondoOutEntity> obtenerPorBanca(String banca) throws DAOException;

	/**
	 * Vacia la cache de fondos.
	 */
	void vaciarCache();

	/**
	 * Retorna la lista de fondos con el predicado aplicado.
	 *
	 * @param predicate
	 *            the predicate
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ConsultaFondoOutEntity> obtenerFondos(Predicate predicate) throws DAOException;

}
