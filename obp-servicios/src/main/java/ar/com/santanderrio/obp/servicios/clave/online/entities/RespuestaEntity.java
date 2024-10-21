/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import org.beanio.annotation.Field;

/**
 * The Class RespuestaEntity.
 */
public class RespuestaEntity {

	/** The opcion 1. */
	@Field
	private String opcion1;

	/** The opcion 2. */
	@Field
	private String opcion2;

	/** The opcion 3. */
	@Field
	private String opcion3;

	/** The opcion 4. */
	@Field
	private String opcion4;

	/** The opcion 5. */
	@Field
	private String opcion5;

	/** The opcion correcta. */
	@Field
	private String opcionCorrecta;

	/**
	 * Gets the opcion 1.
	 *
	 * @return the opcion1
	 */
	public String getOpcion1() {
		return opcion1;
	}

	/**
	 * Sets the opcion 1.
	 *
	 * @param opcion1
	 *            the opcion1 to set
	 */
	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	/**
	 * Gets the opcion 2.
	 *
	 * @return the opcion2
	 */
	public String getOpcion2() {
		return opcion2;
	}

	/**
	 * Sets the opcion 2.
	 *
	 * @param opcion2
	 *            the opcion2 to set
	 */
	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	/**
	 * Gets the opcion 3.
	 *
	 * @return the opcion3
	 */
	public String getOpcion3() {
		return opcion3;
	}

	/**
	 * Sets the opcion 3.
	 *
	 * @param opcion3
	 *            the opcion3 to set
	 */
	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	/**
	 * Gets the opcion 4.
	 *
	 * @return the opcion4
	 */
	public String getOpcion4() {
		return opcion4;
	}

	/**
	 * Sets the opcion 4.
	 *
	 * @param opcion4
	 *            the opcion4 to set
	 */
	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	/**
	 * Gets the opcion 5.
	 *
	 * @return the opcion5
	 */
	public String getOpcion5() {
		return opcion5;
	}

	/**
	 * Sets the opcion 5.
	 *
	 * @param opcion5
	 *            the opcion5 to set
	 */
	public void setOpcion5(String opcion5) {
		this.opcion5 = opcion5;
	}

	/**
	 * Gets the opcion correcta.
	 *
	 * @return the opcionCorrecta
	 */
	public String getOpcionCorrecta() {
		return opcionCorrecta;
	}

	/**
	 * Sets the opcion correcta.
	 *
	 * @param opcionCorrecta
	 *            the opcionCorrecta to set
	 */
	public void setOpcionCorrecta(String opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

}
