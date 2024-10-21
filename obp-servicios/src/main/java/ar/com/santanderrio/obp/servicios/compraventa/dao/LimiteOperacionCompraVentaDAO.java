package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.LimiteCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;

public interface LimiteOperacionCompraVentaDAO {
	
	/**
	 * Consulta el limite maximo de compra y venta de USD
	 * @param conceptoBCRA 
	 * @return
	 * @throws DAOException
	 */
	LimiteCompraVentaUSDEntity limiteMaximoCompraVentaUSD(Cliente cliente, ParametrosOperacion parametrosOperacion, Boolean isBancaPrivada, boolean vende, String conceptoBCRA) throws DAOException;

}
