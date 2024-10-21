/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Interface SimulacionClienteCompraBO.
 *
 * @author sabrina.cis
 */
public interface SimulacionClienteCompraBO {

	/**
	 * Obtener simulacion cliente compra.
	 *
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<SimulacionCompraVentaDTO> obtenerSimulacionClienteCompra(ParametrosSimulacion parametrosSimulacion)
			throws BusinessException;

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