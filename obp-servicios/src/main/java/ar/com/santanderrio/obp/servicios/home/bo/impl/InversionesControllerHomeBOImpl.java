/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.bo.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO;

/**
 * The Class InversionesControllerHomeBOImpl.
 */
@Component
public class InversionesControllerHomeBOImpl implements InversionesControllerHomeBO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#
	 * aplicaPlazoFijo(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaPlazoFijo(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#
	 * aplicaAccionesBonos(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaAccionesBonos(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#
	 * aplicaFondos(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaFondos(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#
	 * aplicaLicitaciones(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaLicitaciones(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#
	 * aplicaConsolidado(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaConsolidado(Cliente cliente) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#
	 * aplicaTitulos(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public Boolean aplicaTitulos(Cliente cliente) {
		return true;
	}

    @Override
    public Boolean aplicaServiciosInversion(Cliente cliente) {
        return true;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO#aplicaPyl(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Boolean aplicaPyl(Cliente cliente) {
		return true;
	}
	
	@Override
	public Boolean aplicaDinamico(Cliente cliente) {
		return true;
	}
}
