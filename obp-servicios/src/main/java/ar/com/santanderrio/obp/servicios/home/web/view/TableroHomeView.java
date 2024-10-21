/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The Class TableroHomeView.
 */
public class TableroHomeView {

	/** The llamada afinidad. */
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	private Boolean llamadaAfinidad;
	
	/** The ultima conexion. */
	private String ultimaConexion;

	/** The grupos. */
	private List<GrupoCajaHomeView> grupos;

	/** Si el módulo buscador está prendido */
	private boolean mostrarBuscador;

	/** Si el módulo onboarding está prendido */
	private boolean mostrarOnboarding;

	/** Si el cliente tiene softToken, para saber si mostrarle o no el onboarding de token */
	private boolean hasTp;

	/**
	 * Gets the grupos.
	 *
	 * @return the grupos
	 */
	public List<GrupoCajaHomeView> getGrupos() {
		return grupos;
	}

	/**
	 * Sets the grupos.
	 *
	 * @param grupos
	 *            the new grupos
	 */
	public void setGrupos(List<GrupoCajaHomeView> grupos) {
		this.grupos = grupos;
	}

	/**
	 * Gets the ultima conexion.
	 *
	 * @return the ultima conexion
	 */
	public String getUltimaConexion() {
		return ultimaConexion;
	}

	/**
	 * Sets the ultima conexion.
	 *
	 * @param ultimaConexion
	 *            the new ultima conexion
	 */
	public void setUltimaConexion(String ultimaConexion) {
		this.ultimaConexion = ultimaConexion;
	}

	/**
	 * Gets the llamada afinidad.
	 *
	 * @return the llamada afinidad
	 */
	public Boolean getLlamadaAfinidad() {
		return llamadaAfinidad;
	}

	/**
	 * Sets the llamada afinidad.
	 *
	 * @param llamadaAfinidad
	 *            the new llamada afinidad
	 */
	public void setLlamadaAfinidad(Boolean llamadaAfinidad) {
		this.llamadaAfinidad = llamadaAfinidad;
	}

	public boolean isMostrarBuscador() {
		return mostrarBuscador;
	}

	public void setMostrarBuscador(boolean mostrarBuscador) {
		this.mostrarBuscador = mostrarBuscador;
	}

	public boolean isMostrarOnboarding() {
		return mostrarOnboarding;
	}

	public void setMostrarOnboarding(boolean mostrarOnboarding) {
		this.mostrarOnboarding = mostrarOnboarding;
	}

	public boolean isHasTp() {
		return hasTp;
	}

	public void setHasTp(boolean hasTp) {
		this.hasTp = hasTp;
	}
}
