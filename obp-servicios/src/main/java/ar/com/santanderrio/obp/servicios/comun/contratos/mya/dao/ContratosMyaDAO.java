/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaCliente;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaClienteParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ContratoParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.RegistroClienteParam;

/**
 * The Interface ContratosMyaDAO.
 */
public interface ContratosMyaDAO {

	/**
	 * Consulta clientes.
	 *
	 * @param consultaCliente
	 *            the consulta cliente
	 * @return the consulta cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaCliente consultaClientes(ConsultaClienteParam consultaCliente) throws DAOException;

	/**
	 * Crear registro.
	 *
	 * @param registroCliente
	 *            the registro cliente
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	boolean crearRegistro(RegistroClienteParam registroCliente) throws DAOException;

	/**
	 * Actualizar contrato.
	 *
	 * @param contrato
	 *            the contrato
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	boolean actualizarContrato(ContratoParam contrato) throws DAOException;

}
