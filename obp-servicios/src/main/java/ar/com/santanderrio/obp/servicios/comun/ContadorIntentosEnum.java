/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

/**
 * The Enum ContadorIntentosEnum.
 * Enum para saber en que flujo se encuentra un contador
 *
 * @author florencia.n.martinez
 */
public enum ContadorIntentosEnum {
	
	/**
	 * Flujo orden de compra.
	 */
	CONFIRMACION_ORDEN_COMPRA("Confirmacion Orden Compra"),
	
	/**
	 * Flujo adhesion poder de compra.
	 */
	ADHESION_PODER_COMPRA("Adhesion Poder Compra");
	
	/** The descripcion. */
	String descripcion;

	/**
	 * Instantiates a new contador intentos enum.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	private ContadorIntentosEnum(String descripcion){
		this.descripcion = descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
