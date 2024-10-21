package ar.com.santanderrio.obp.servicios.chances.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chances.manager.ChancesManager;
import ar.com.santanderrio.obp.servicios.chances.sei.ChancesSEI;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceView;

/**
 * The Class ChancesSEIImpl.
 */
@Component("chancesSEI")
public class ChancesSEIImpl implements ChancesSEI{
	
	/** The chances Manager. */
	@Autowired
	private ChancesManager chancesManager;
	
	/**
	 * obtener Chance del Mes seleccionado.
	 *
	 * @param ChanceView
	 *            the ChanceView
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ChanceView> obtenerChancesMes(ChanceView chanceView) {
		return chancesManager.obtenerChancesMes(chanceView);
	}

}
