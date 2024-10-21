/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.manager.TenenciaConsolidadaManager;

/**
 * The Class tenenciaConsolidadaSEIImpl.
 */
@Component("tenenciaConsolidada")
public class tenenciaConsolidadaSEIImpl implements TenenciaConsolidadaSEI {

	
	/** The inversiones manager. */
	@Autowired
	private TenenciaConsolidadaManager tenenciaConsolidadaManager;
	
	

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.sei.TenenciaConsolidadaSEI#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {
		return tenenciaConsolidadaManager.obtenerRendimiento();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tenenciaConsolidada.comun.sei.TenenciaConsolidadaSEI#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {
		return tenenciaConsolidadaManager.obtenerRendimientoBPriv();
	}

	
}
