/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;

/**
 * The Interface AdhesionTarjetasDAO.
 *
 * @author b039542
 */
public interface AdhesionTarjetasDAO {

	/**
	 * Consulta adhesión tarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the adhesion tarjeta
	 * @throws DAOException
	 *             si hubo en un error al intenar acceder a los datos
	 */
	AdhesionTarjeta consultarAdhesionTarjeta(Cuenta tarjeta, ResumenCliente cliente) throws DAOException;

}
