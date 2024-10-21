/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO;

/**
 * The Class SolicitudesControllerHomeBOImpl.
 */
@Component
public class SolicitudesControllerHomeBOImpl implements SolicitudesControllerHomeBO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO#
	 * aplicaCuentasPaquetes(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente)
	 */
	@Override
	public Boolean aplicaCuentasPaquetes(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO#
	 * aplicaPrestamos(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaPrestamos(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO#
	 * aplicaTarjetas(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaTarjetas(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO#
	 * aplicaSeguros(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaSeguros(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO#
	 * aplicaOtrosMediosPago(ar.com.santanderrio.obp.servicios.clientes.entities
	 * .Cliente)
	 */
	@Override
	public Boolean aplicaOtrosMediosPago(Cliente cliente) {
		return true;
	}

}
