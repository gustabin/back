/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;

/**
 * The Interface UltimoResumenBo.
 */
public interface UltimoResumenBo {

	/**
	 * Obtener ultimo resumen.
	 *
	 * @param cliente
	 *            the cliente
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<UltimoResumenDTO> obtenerUltimoResumen(Cliente cliente, IdentificacionCuenta identificacionCuenta)
			throws BusinessException;

	/**
	 * Exportar ultimo resumen.
	 *
	 * @param ultimoResumen
	 *            the ultimo resumen
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	// Respuesta<Reporte> exportarUltimoResumen(UltimoResumenDTO ultimoResumen)
	// throws BusinessException
}
