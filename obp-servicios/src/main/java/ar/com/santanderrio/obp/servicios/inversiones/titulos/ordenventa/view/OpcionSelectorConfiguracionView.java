/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

/**
 * The Class OpcionSelectorConfiguracionView.
 */
public class OpcionSelectorConfiguracionView {

	/** The nombre. */
	private String nombre;
	
	/** The permite pesos. */
	private Boolean permitePesos = false;
	
	/** The permite dolares. */
	private Boolean permiteDolares = false;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the permite pesos.
	 *
	 * @return the permitePesos
	 */
	public Boolean getPermitePesos() {
		return permitePesos;
	}

	/**
	 * Sets the permite pesos.
	 *
	 * @param permitePesos
	 *            the permitePesos to set
	 */
	public void setPermitePesos(Boolean permitePesos) {
		this.permitePesos = permitePesos;
	}

	/**
	 * Gets the permite dolares.
	 *
	 * @return the permiteDolares
	 */
	public Boolean getPermiteDolares() {
		return permiteDolares;
	}

	/**
	 * Sets the permite dolares.
	 *
	 * @param permiteDolares
	 *            the permiteDolares to set
	 */
	public void setPermiteDolares(Boolean permiteDolares) {
		this.permiteDolares = permiteDolares;
	}

}
