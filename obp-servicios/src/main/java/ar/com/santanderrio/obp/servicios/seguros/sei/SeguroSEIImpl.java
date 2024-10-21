/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class SeguroSEIImpl.
 */
@Component("seguroSEI")
public class SeguroSEIImpl implements SeguroSEI {

	/** The seguro manager. */
	@Autowired
	@Qualifier("seguroManager")
	private TokenManager seguroManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.seguros.sei.SeguroSEI#
	 * obtenerTokenEncriptado()
	 */
	@Override
	public Respuesta<TokenView> obtenerTokenEncriptado() {
		return seguroManager.obtenerTokenEncriptado();
	}
}
