/*
 * 
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DestinoPrestamo.
 */
public class DestinoPrestamo {

	/** The producto UG. */
	private String productoUG;

	/** The subproducto UG. */
	private String subproductoUG;

	/** The divisa producto UG. */
	private String divisaProductoUG;

	/** The destino de fondos UG. */
	private String destinoDeFondosUG;

	/** The descripcion UG. */
	private String descripcionUG;

	/**
	 * Instantiates a new destino prestamo.
	 */
	public DestinoPrestamo() {
		super();
	}

	/**
	 * Instantiates a new destino prestamo.
	 *
	 * @param lineaTexto
	 *            the linea texto
	 */
	public DestinoPrestamo(String lineaTexto) {
		String descripcion = ISBANStringUtils.formatearFraseInicialMayuscula(lineaTexto.substring(14).trim())
				.replace(" / ", " y ");

		this.productoUG = lineaTexto.substring(0, 2);
		this.subproductoUG = lineaTexto.substring(2, 6);
		this.divisaProductoUG = lineaTexto.substring(6, 9);
		this.destinoDeFondosUG = lineaTexto.substring(9, 14);
        if (descripcion.contains("�")) {
            descripcion = descripcion.replaceAll("�", "\u00f3");
        }
		this.descripcionUG = descripcion;
	}

	/**
	 * Instantiates a new destino prestamo.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	public DestinoPrestamo(String id, String descripcion) {
		this.productoUG = id.substring(0, 2);
		this.subproductoUG = id.substring(2, 6);
		this.divisaProductoUG = id.substring(6, 9);
		this.destinoDeFondosUG = id.substring(9, 14);
		this.descripcionUG = descripcion;
	}

	/**
	 * Gets the producto UG.
	 *
	 * @return the producto UG
	 */
	public String getProductoUG() {
		return productoUG;
	}

	/**
	 * Sets the producto UG.
	 *
	 * @param productoUG
	 *            the new producto UG
	 */
	public void setProductoUG(String productoUG) {
		this.productoUG = productoUG;
	}

	/**
	 * Gets the subproducto UG.
	 *
	 * @return the subproducto UG
	 */
	public String getSubproductoUG() {
		return subproductoUG;
	}

	/**
	 * Sets the subproducto UG.
	 *
	 * @param subproductoUG
	 *            the new subproducto UG
	 */
	public void setSubproductoUG(String subproductoUG) {
		this.subproductoUG = subproductoUG;
	}

	/**
	 * Gets the divisa producto UG.
	 *
	 * @return the divisa producto UG
	 */
	public String getDivisaProductoUG() {
		return divisaProductoUG;
	}

	/**
	 * Sets the divisa producto UG.
	 *
	 * @param divisaProductoUG
	 *            the new divisa producto UG
	 */
	public void setDivisaProductoUG(String divisaProductoUG) {
		this.divisaProductoUG = divisaProductoUG;
	}

	/**
	 * Gets the destino de fondos UG.
	 *
	 * @return the destino de fondos UG
	 */
	public String getDestinoDeFondosUG() {
		return destinoDeFondosUG;
	}

	/**
	 * Sets the destino de fondos UG.
	 *
	 * @param destinoDeFondosUG
	 *            the new destino de fondos UG
	 */
	public void setDestinoDeFondosUG(String destinoDeFondosUG) {
		this.destinoDeFondosUG = destinoDeFondosUG;
	}

	/**
	 * Gets the descripcion UG.
	 *
	 * @return the descripcion UG
	 */
	public String getDescripcionUG() {
		return descripcionUG;
	}

	/**
	 * Sets the descripcion UG.
	 *
	 * @param descripcionUG
	 *            the new descripcion UG
	 */
	public void setDescripcionUG(String descripcionUG) {
		this.descripcionUG = descripcionUG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(destinoDeFondosUG);
		hcb.append(divisaProductoUG);
		hcb.append(productoUG);
		hcb.append(subproductoUG);

		return hcb.hashCode();
	}

	/**
	 * Genera el id para la vista.
	 *
	 * @return the string
	 */
	public String obtenerId() {
		return this.productoUG + this.subproductoUG + this.divisaProductoUG + this.destinoDeFondosUG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		DestinoPrestamo other = (DestinoPrestamo) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(destinoDeFondosUG, other.getDestinoDeFondosUG());
		eb.append(divisaProductoUG, other.getDivisaProductoUG());
		eb.append(productoUG, other.getProductoUG());
		eb.append(subproductoUG, other.getSubproductoUG());

		return eb.isEquals();
	}

}
