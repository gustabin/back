/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface ClienteBO.
 */
public interface ClienteBO {

	/**
	 * Obtener cliente.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Cliente> obtenerCliente(ResumenCliente resumenCliente) throws BusinessException;
	
	/**
	 * Actualiza la fecha del ultimo cambio de clave 
	 * @param nup
	 * @return
	 * @throws BusinessException
	 */
	Respuesta<Boolean> actualizarUltimaFechaCambioClave(Long nup) throws BusinessException;
	
	/**
	 * Obtiene la antiguedad en dias del ultimo cambio de clave y token
	 * @param nup
	 * @return
	 * @throws BusinessException
	 */
	Respuesta<List<BigDecimal>> obtenerAntiguedadDiasUltCambioClaveToken(Long nup) throws BusinessException;

}
