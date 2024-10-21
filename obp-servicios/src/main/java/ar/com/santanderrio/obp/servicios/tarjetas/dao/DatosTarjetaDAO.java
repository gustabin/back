/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;

/**
 * The Interface DatosTarjetaDAO.
 */
public interface DatosTarjetaDAO {

	/**
	 * Consulta el detalle de una tarjeta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            tarjeta a consultar
	 * @param usarCache
	 * 			  indica si se utiliza cache
	 * @return datos de tarjeta
	 * @throws DAOException
	 *             the DAO exception
	 */
	DatosTarjetaBuilder obtenerDatosTarjeta(Cliente cliente, Cuenta cuenta, boolean usarCache) throws DAOException;

	/**
	 * Obtener pagos programados.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DatosTarjeta> obtenerPagosProgramados(Cliente cliente, Cuenta cuenta) throws DAOException;

	/**
	 * Tiene pagos programados.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosTarjeta
	 *            the datos tarjeta
	 * @param fechaLimite
	 *            the fecha limite
	 * @return the boolean
	 * @throws DAOException
	 *             the DAO exception
	 */
	Boolean tienePagosProgramados(Cliente cliente, DatosTarjeta datosTarjeta, String fechaLimite) throws DAOException;

	/**
	 * Obtener un pago programado para una cuenta y fecha determinada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaTarjeta
	 *            the cuenta tarjeta
	 * @param fecha
	 *            the fecha
	 * @return the datos tarjeta
	 * @throws DAOException
	 *             the DAO exception
	 */
	DatosTarjeta obtenerPagoProgramado(Cliente cliente, Cuenta cuentaTarjeta, Date fecha) throws DAOException;
}
