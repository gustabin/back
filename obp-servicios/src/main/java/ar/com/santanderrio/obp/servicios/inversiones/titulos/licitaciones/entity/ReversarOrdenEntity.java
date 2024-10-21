/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ReversarOrdenEntity.
 */
public class ReversarOrdenEntity extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8092504301769697566L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosReversarOrden datos;

	/**
	 * Constuctor using fields.
	 *
	 * @param datos
	 *            the datos
	 */
	public ReversarOrdenEntity(DatosReversarOrden datos) {
		super();
		this.datos = datos;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosReversarOrden getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosReversarOrden datos) {
		this.datos = datos;
	}

}
