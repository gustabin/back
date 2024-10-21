/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * The Interface ComprobantesVisaDAO.
 */
public interface ComprobantesVisaDAO {

	/**
	 * Obtener informes debitos automaticos WS.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @param dto
	 *            the dto
	 * @param cliente
	 *            the cliente
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 */

	RetornoTarjetasEntity obtenerInformesDebitosAutomaticosWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			TransaccionDTO dto, Cliente cliente) throws DAOException;

	/**
	 * Obtener informes debitos automaticos WS.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @param cliente
	 *            the cliente
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	RetornoTarjetasEntity obtenerInformesDebitosAutomaticosWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion,
			Cliente cliente) throws DAOException;
}