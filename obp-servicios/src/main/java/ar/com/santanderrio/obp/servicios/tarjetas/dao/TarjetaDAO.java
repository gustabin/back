/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * The Interface TarjetaDAO.
 *
 * @author sergio.e.goldentair
 */
public interface TarjetaDAO {

	/**
	 * Obtener tarjetas de visa WS.
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
	RetornoTarjetasEntity obtenerTarjetasDeVisaWS(Cuenta cuenta, OperacionTarjetaWSEnum operacion, Cliente cliente)
			throws DAOException;
}