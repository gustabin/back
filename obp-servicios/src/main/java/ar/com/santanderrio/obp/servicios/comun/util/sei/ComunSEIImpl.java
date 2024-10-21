/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.util.sei;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.util.manager.ComunManager;
import ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView;
import ar.com.santanderrio.obp.servicios.comun.view.FechaView;

/**
 * The Class ComunSEIImpl.
 */
@Component("comunSEI")
public class ComunSEIImpl implements ComunSEI {

	/** The comun manager. */
	@Autowired
	private ComunManager comunManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.util.sei.ComunSEI#getFechaActual(
	 * )
	 */
	@Override
	public FechaView getFechaActual() {
		return comunManager.getFechaActual();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.util.sei.ComunSEI#
	 * cancelarDesafioEnCurso()
	 */
	@Override
	public void cancelarDesafioEnCurso() {
		comunManager.cancelarDesafioEnCurso();
	}

	@Override
	public Respuesta<Void> grabarEstadisticaVisualizacionResumenTyC() {
		return comunManager.grabarEstadisticaVisualizacionResumenTyC();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.util.sei.ComunSEI#
	 * accesoMF(ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView)
	 */
	@Override
	public Respuesta<Void> accesoMF(MicroFEAccessView microFEAccessView) {
		return comunManager.accesoMF(microFEAccessView);
	}

}
