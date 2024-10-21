/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class OnboardingView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class OnboardingView {

	/** The seccion. */
	private String seccion;

	/** The secciones. */
	private List<String> secciones = new ArrayList<String>();

	/**
	 * Gets the seccion.
	 *
	 * @return the seccion
	 */
	public String getSeccion() {
		return seccion;
	}

	/**
	 * Sets the seccion.
	 *
	 * @param seccion
	 *            the new seccion
	 */
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	/**
	 * Gets the secciones.
	 *
	 * @return the secciones
	 */
	public List<String> getSecciones() {
		return secciones;
	}

	/**
	 * Sets the secciones.
	 *
	 * @param secciones
	 *            the new secciones
	 */
	public void setSecciones(List<String> secciones) {
		this.secciones = secciones;
	}
}
