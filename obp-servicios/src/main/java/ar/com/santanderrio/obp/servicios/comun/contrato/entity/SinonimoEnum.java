/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.entity;

/**
 * Enum. empleado para representar el valor del campo sinonimo. Indica si el DNI
 * del cliente es repetido o no; 0(cero) = no es repetido. Uno(1) = es repetido
 * Utilizado para el SP. de Contrato (HB_PKG_CONTRATOS).
 * 
 * @author pablo.d.gargaglione
 *
 */
public enum SinonimoEnum {

	/** The no repetido. */
	NO_REPETIDO("0", "DNI No Repetido"),
	/** The repetido. */
	REPETIDO("1", "DNI Repetido");

	/** The sinonimo. */
	String valorSinonimo;

	/** The sinonimo. */
	String descripcion;

	/**
	 * Instantiates a new sinonimo enum.
	 *
	 * @param valorSinonimo
	 *            the valor sinonimo
	 * @param descripcion
	 *            the descripcion
	 */
	SinonimoEnum(String valorSinonimo, String descripcion) {
		this.valorSinonimo = valorSinonimo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the valor sinonimo.
	 *
	 * @return the valor sinonimo
	 */
	public String getValorSinonimo() {
		return valorSinonimo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
