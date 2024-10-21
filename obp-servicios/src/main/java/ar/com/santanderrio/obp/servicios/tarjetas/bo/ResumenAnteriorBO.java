/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;

/**
 * Gestiona la logica relacionada a ResumenAnterior.
 * 
 * @author
 *
 */
public interface ResumenAnteriorBO {

	/**
	 * realiza obtenerResumen en ResumenAnterior.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return respuesta con el objeto view response.
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ResumenAnteriorViewResponse> obtenerResumenesAnteriores(IdentificacionCuenta identificacionCuenta,
			Cliente cliente, String numeroCuenta) throws BusinessException;

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
	 * Obtiene la lista de resumenes y genera el pdf para la primer posicion.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerResumenAnterior(IdentificacionCuenta identificacionCuenta, Cliente cliente);
}
