/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;

/**
 * The Interface CuotasPendientesBO.
 *
 * @author sabrina.cis
 */
public interface CuotasPendientesBO extends BusinessObject {

	/**
	 * Obtiene las cuotas pendientes.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<CuotasPendientesDTO> obtenerCuotasPendientes(IdentificacionCuenta identificacionCuenta, Cliente cliente)
			throws BusinessException;

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
	 * Obtener reporte.
	 *
	 * @param body
	 *            the body
	 * @param proceso
	 *            the proceso
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente);
}
