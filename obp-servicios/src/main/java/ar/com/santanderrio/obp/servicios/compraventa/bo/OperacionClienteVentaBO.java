/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.LimiteCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;

/**
 * The Interface OperacionClienteVentaBO.
 */
public interface OperacionClienteVentaBO {

	/**
	 * Operacion cliente compra.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ComprobanteCompraVentaDTO> operacionClienteVenta(ParametrosOperacion parametrosOperacion)
			throws BusinessException;

	/**
	 * Consulta el limite maximo de compra y venta de USD
	 * @return
	 * @throws DAOException
	 */
	LimiteCompraVentaUSDEntity limiteMaximoCompraVentaUSD(Cliente cliente, ParametrosOperacion parametrosOperacion, Boolean isBancaPrivada, boolean vende) throws DAOException;
	
}
