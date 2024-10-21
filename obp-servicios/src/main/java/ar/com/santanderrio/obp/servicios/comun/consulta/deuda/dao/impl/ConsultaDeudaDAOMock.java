/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.ConsultaDeudaDAO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;

/**
 * The Class ConsultaDeudaDAOMock.
 */
public class ConsultaDeudaDAOMock implements ConsultaDeudaDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.
	 * ConsultaDeudaDAO#consultaDeuda(java.lang.String)
	 */
	@Override
	public ClasificacionDeuda consultaDeuda(String nup) throws DAOException {
		ClasificacionDeuda clasificacionDeuda = new ClasificacionDeuda();
		clasificacionDeuda.setDescripcion("Prueba");
		clasificacionDeuda.setSituacionBcra("3");
		return clasificacionDeuda;
	}

}
