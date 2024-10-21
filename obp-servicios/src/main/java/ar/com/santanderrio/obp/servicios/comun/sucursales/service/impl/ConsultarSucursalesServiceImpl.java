/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.service.ConsultarSucursalesService;

/**
 * The Class ConsultarSucursalesServiceImpl.
 *
 * @author B039542
 */
@Component
public class ConsultarSucursalesServiceImpl implements ConsultarSucursalesService {

	/** The consultar sucursales bo. */
	@Autowired
	private ConsultarSucursalesBO consultarSucursalesBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.comun.sucursales.service.
	 * ConsultarSucursalesService#consultarSucursalPorId(java.lang.String)
	 */
	@Override
	public Respuesta<Sucursal> consultarSucursalPorId(String id) {
		return consultarSucursalesBO.consultarSucursalPorId(id);
	}
}
