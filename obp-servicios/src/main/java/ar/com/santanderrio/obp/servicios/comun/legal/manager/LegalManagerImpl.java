/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;

/**
 * The Class LegalManagerImpl.
 *
 * @author sergio.e.goldentair
 */
@Service
public class LegalManagerImpl implements LegalManager {

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.legal.manager.LegalManager#
	 * vaciarLegales()
	 */
	@Override
	public Respuesta<Boolean> vaciarLegales() {
		return legalBO.limpiarLegales();
	}

}
