/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Interface CompraVentaDolaresBO.
 *
 * @author sabrina.cis
 */
public interface CompraVentaDolaresBO {

	/**
	 * Obtener documento valido.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the detalle documento DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleDocumentoDTO obtenerDocumentoValido(Cliente cliente) throws DAOException;

	/**
	 * Obtener cuenta por id.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	Cuenta obtenerCuentaPorId(IdentificacionCuenta identificacionCuenta, Cliente cliente);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param error
	 *            the error
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(ErrorCompraVentaEnum error, String tipoOperacion, Cuenta cuenta, String mensaje);

}
