/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.sei.AadvantageSEI;
import ar.com.santanderrio.obp.servicios.aadvantage.web.manager.AadvantageManager;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.ConsultaMillasAadvantageView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.GrabadoDeEstadisticaIngresoAadvantageInView;
import ar.com.santanderrio.obp.servicios.aadvantage.web.view.SolicitudMillasAadvantageView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * The Class AadvantageSEIImpl.
 */
@Component("aadvantage")
public class AadvantageSEIImpl implements AadvantageSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AadvantageSEIImpl.class);
	
	/** The aadvantage manager. */
	@Autowired
	private AadvantageManager aadvantageManager;
	
	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.aadvantage.sei.AadvantageSEI#consultarMillas(ar.com.santanderrio.obp.servicios.aadvantage.web.view.SolicitudMillasAadvantageView)
	 */
	public Respuesta<ConsultaMillasAadvantageView> consultarMillas(SolicitudMillasAadvantageView inView){
		LOGGER.info("Post OK: /consultaGrupoAfinidad.");
		return aadvantageManager.consultaMillasAadvantage(inView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.aadvantage.sei.AadvantageSEI#grabarEstadisticaDesdeMenu()
	 */
	@Override
	public void grabarEstadisticaDesdeMenu(GrabadoDeEstadisticaIngresoAadvantageInView request) {
		if(request.getIngresoDesdeHomeController()) {
			estadisticaManager.add(EstadisticasConstants.AADVANTAGE_DESDE_TOPBAR, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);			
		}else {
			estadisticaManager.add(EstadisticasConstants.AADVANTAGE_DESDE_TABLERO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}
		
}
