/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaBOImpl;
import ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilderFactory;

/**
 * La class AgendamientoTransferenciaView solo es usada para encontrar builder
 * especifico en el map de la clase {@link RsaRequestBuilderFactory} y que luego
 * se usara en la construccion del request a RSA.
 * 
 * @author manuel.Vargs B041299
 * @see TransferenciaView
 * @see RsaRequestBuilderFactory
 * @see RsaBOImpl
 */
public class AgendamientoTransferenciaView extends TransferenciaView {

	/** The frecuencias. */
	private List<String> frecuencias;

	/**
	 * Gets the frecuencias.
	 *
	 * @return the frecuencias
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaView#getFrecuencias()
	 */
	public List<String> getFrecuencias() {
		return frecuencias;
	}

	/**
	 * Sets the frecuencias.
	 *
	 * @param frecuencias
	 *            the new frecuencias
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaView#setFrecuencias(java.util.List)
	 */
	public void setFrecuencias(List<String> frecuencias) {
		this.frecuencias = frecuencias;
	}

}
