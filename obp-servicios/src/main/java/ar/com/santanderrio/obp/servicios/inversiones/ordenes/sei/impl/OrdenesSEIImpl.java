/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.OrdenesSEI;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.OrdenesManager;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * The Class OrdenesSEIImpl.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
@Component("ordenesSEI")
public class OrdenesSEIImpl implements OrdenesSEI {

	/** The ordenes manager. */
	@Autowired
	OrdenesManager ordenesManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.OrdenesSEI#
	 * iniciarOrdenesOperaciones()
	 */
	@Override
	public Respuesta<OrdenesView> iniciarOrdenesOperaciones() {
		return ordenesManager.iniciarOrdenes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.OrdenesSEI#
	 * buscarOrdenesOperacionesPorCuenta(java.lang.String)
	 * 
	 * TODO: buscar annotation que cree un objeto clave/valor, ya que no se
	 * admite el paso de parametros primitivos ej: string... si o si debe estar
	 * encapsulado en un objeto.
	 */
	@Override
	public Respuesta<OrdenesView> buscarOrdenesOperacionesPorCuenta(CuentaView cuentaView) {
		return ordenesManager.buscarOrdenesPorCuenta(cuentaView.getNumero());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.OrdenesSEI#
	 * buscarOrdenesOperaciones(ar.com.santanderrio.obp.servicios.inversiones.
	 * ordenes.web.view.FiltrosOrdenesView)
	 */
	@Override
	public Respuesta<OrdenesView> buscarOrdenesOperaciones(FiltrosOrdenesView filtrosOrdenesView) {
		return ordenesManager.buscarOrdenes(filtrosOrdenesView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.OrdenesSEI#
	 * obtenerFiltrosDeBusqueda()
	 */
	@Override
	public Respuesta<FiltrosOrdenesView> obtenerFiltrosDeBusqueda() {
		return ordenesManager.obtenerFiltrosDeBusqueda();
	}

	/**
	 * Grabar estadistica Busqueda.
	 */
	@Override
	public void grabarEstadisticaBusqueda() {
		ordenesManager.grabarEstadisticaBusqueda();

	}

}
