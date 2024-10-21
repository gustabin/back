package ar.com.santanderrio.obp.servicios.compraventa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.HabilitacionCompraVentaDolaresBPEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.RequestBancaPrivadaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

public interface OperacionBancaPrivadaDAO {

	/**
	 * Insertar operacion cambio.
	 *
	 * @param parameter the parameter
	 * @return the string
	 */
	String insertarOperacionCambio(ParametrosOperacion parametrosOperacion,
			OperacionCompraVentaDatosEntrada datosEntrada, RequestBancaPrivadaEntity entity);
	
	
	HabilitacionCompraVentaDolaresBPEntity obtenerHabilitacionCuentaBP(Cliente cliente, Cuenta cuenta) throws IatxException, DAOException;

}
