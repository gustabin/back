/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.AadvantageHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeAadvantageView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class AadvantageHomeManagerImpl.
 */
@Component
public class AadvantageHomeManagerImpl extends AbstractHomeManager implements AadvantageHomeManager {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#aplicaGrupo()
	 */
	@Override
	public Respuesta<Boolean> aplicaGrupo() {
		return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#obtenerAccion()
	 */
	@Override
	public AccionController obtenerAccion() {
		return AccionController.IR_AADVANTAGE;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.AbstractHomeManager#obtenerCajas()
	 */
	@Override
	protected GrupoCajaHomeView obtenerCajas() {
		ArrayList<Caja> cajas = new ArrayList<Caja>();
		GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
		grupoCajaHomeView.setSinError(Boolean.TRUE);
		if (sesionParametros.getNumeroSocioAadvantage() == null) {
			return grupoCajaHomeView;
		}
		cajas.add(new CajaHomeAadvantageView());
		cajas.trimToSize();
		grupoCajaHomeView.setCajas(cajas);
		return grupoCajaHomeView;
	}

}
