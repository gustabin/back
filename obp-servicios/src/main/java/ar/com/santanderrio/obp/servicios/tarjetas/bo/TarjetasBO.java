/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * The Interface TarjetasBO.
 *
 * @author sabrina.cis
 */
public interface TarjetasBO {

	/**
	 * Obtener marca de tarjeta.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	String obtenerMarcaDeTarjeta(IdentificacionCuenta identificacionCuenta, Cliente cliente) throws BusinessException;

	/**
	 * Obtener marca de tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	String obtenerMarcaDeTarjeta(Cuenta cuenta);

	/**
	 * Es titular cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	Boolean esTitularCuenta(Cuenta cuenta);

	/**
	 * Es adicional cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	Boolean esAdicionalCuenta(Cuenta cuenta);

	/**
	 * Cortar nro tarjeta desde cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	String cortarNroTarjetaDesdeCuenta(Cuenta cuenta);

	/**
	 * Obtener respuesta visa.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @param operacion
	 *            the operacion
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	RetornoTarjetasEntity obtenerRespuestaVisa(IdentificacionCuenta identificacionCuenta, Cliente cliente,
			OperacionTarjetaWSEnum operacion) throws DAOException, BusinessException;

	/**
	 * Obtener respuesta visa.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param operacion
	 *            the operacion
	 * @return the retorno tarjetas entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	RetornoTarjetasEntity obtenerRespuestaVisa(Cuenta cuenta, OperacionTarjetaWSEnum operacion) throws DAOException;

}