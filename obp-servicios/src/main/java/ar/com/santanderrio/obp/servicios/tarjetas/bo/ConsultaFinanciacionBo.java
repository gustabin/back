/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDTO;

/**
 * The Interface ConsultaFinanciacionBo.
 */
public interface ConsultaFinanciacionBo {

	/**
	 * Obtiene la lista de los planes vigentes, estan en curso y no se
	 * liquidaron aun para la tarjeta seleccionada. Visa o Amex. Recargables no.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ConsultaFinanciacionDTO> obtenerListaFinanciacion(IdentificacionCuenta identificacionCuenta,
			Cliente cliente) throws BusinessException;

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
