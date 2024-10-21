/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import java.sql.SQLException;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Interface ConfiguracionCompraVentaDolaresBO.
 *
 * @author sabrina.cis
 */
public interface ConfiguracionCompraVentaDolaresBO {

	/**
	 * Obtener cotizacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param fromCuentas
	 *            the from cuentas
	 * @param canalBancaPrivada
	 *            the canal banca privada
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<CompraVentaInicioDTO> obtenerCotizacion(Cliente cliente, Cuenta cuentaOrigen, String tipoOperacion, boolean fromCuentas, boolean canalBancaPrivada)
			throws BusinessException;

	/**
	 * Gets the cuentas saldo ordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param identificacionCuenta2
	 *            the identificacion cuenta 2
	 * @return the cuentas saldo ordenadas
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoOrdenadas(Cliente cliente,
			IdentificacionCuenta identificacionCuenta, IdentificacionCuenta identificacionCuenta2)
			throws BusinessException;

	/**
	 * Gets the cuentas saldo ordenadas BP.
	 *
	 * @param cliente the cliente
	 * @return the cuentas saldo ordenadas BP
	 * @throws BusinessException the business exception
	 * @throws SQLException the SQL exception
	 * @throws ImporteConvertException the importe convert exception
	 */
	Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoOrdenadasBP(Cliente cliente)
			throws BusinessException, SQLException, ImporteConvertException;
	
}