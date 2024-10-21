/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.view;

/**
 * The Class TarjetaActivaView.
 */
public class TarjetaActivaView {

	/** The estado. */
	private String estado;

	/** The favorita. */
	private boolean favorita;

	/** The id. */
	private int id;

	/** The vencimiento. */
	private String vencimiento;

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Gets the favorita.
	 *
	 * @return the favorita
	 */
	public boolean getFavorita() {
		return favorita;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Sets the favorita.
	 *
	 * @param favorita
	 *            the favorita to set
	 */
	public void setFavorita(boolean favorita) {
		this.favorita = favorita;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the vencimiento to set
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
}
