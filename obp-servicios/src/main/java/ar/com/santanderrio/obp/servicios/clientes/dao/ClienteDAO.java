/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.ValidacionSaldo;

/**
 * The Interface ClienteDAO.
 */
public interface ClienteDAO {

    /**
     * Obtener cliente siempre con usuario de session gestionado.
     *
     * @param resumenCliente
     *            the resumen cliente
     * @return the respuesta
     */
    ClienteConSaldoResponse obtenerCliente(ResumenCliente resumenCliente);

    /**
	 * Obtener cliente pero con un nro de session usuario de iatx fijo, no se
	 * gestiona de manera incremental.<br/>
	 * <b>Solo</b> utilizar en las llamadas que se generan desde la <b>api de
	 * status o monitoreo</b> para no genrear session java.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the cliente con saldo response
	 */
    ClienteConSaldoResponse obtenerClienteSinSessionUsuario(ResumenCliente resumenCliente);

    /**
     * Obtener saldo.
     *
     * @param cliente
     *            the cliente
     * @param validacionSaldo
     *            the validacion saldo
     * @return the string
     * @throws DAOException
     *             the DAO exception
     */
    String obtenerSaldo(Cliente cliente, ValidacionSaldo validacionSaldo) throws DAOException;

}
