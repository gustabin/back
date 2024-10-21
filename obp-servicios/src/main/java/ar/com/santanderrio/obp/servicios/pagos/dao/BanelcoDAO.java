/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;

/**
 * The Interface BanelcoDAO.
 */
public interface BanelcoDAO {

	/**
	 * devuelve las cuentas q sean de de tipo Banelco(5).
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<CuentaPagoMisCuentas> obtenerCuentasBanelcoHabilitadas(Cliente cliente) throws DAOException;

	/**
	 * Obtiene la lista de facturas para un codigo de pago electronico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pesFiid
	 *            the pes fiid
	 * @param codigoPagoElectronico
	 *            the codigo pago electronico
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Factura> obtenerListaFacturas(Cliente cliente, String pesFiid, String codigoPagoElectronico)
			throws DAOException;

	/**
	 * Valida si los 8 digitos ingrasados por el usuario corresponden a la
	 * tarjeta de debito que tienen asociada.
	 *
	 * @param cliente
	 *            el cliente
	 * @param numTarjeta
	 *            nros. ingresados por el cliente
	 * @return True, si la validacion es correcta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<Boolean> validarTarjetaDebito(Cliente cliente, String numTarjeta) throws DAOException;

	/**
	 * Validar tarjeta banelco.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numTarjeta
	 *            the num tarjeta
	 * @return the integer
	 * @throws DAOException
	 *             the DAO exception
	 */
	Integer validarOchoDigitos(Cliente cliente, String numTarjeta) throws DAOException;
	
	
	
	/**
	 * @param cliente
	 * @return
	 * @throws DAOException
	 */
	List<CuentaPagoMisCuentas> obtenerCuentasBanelcoHabilitadasSinCache(Cliente cliente) throws DAOException;

}
