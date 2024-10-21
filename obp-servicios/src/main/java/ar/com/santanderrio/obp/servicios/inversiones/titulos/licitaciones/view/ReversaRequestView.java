/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Request de reversa de orden.
 *
 * @author marcelo.ruiz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReversaRequestView {

	/** The banca. */
	@NotNull
	@Pattern(regexp = "(BP)|(BR)")
	private String banca;

	/** The num orden. */
	@Pattern(regexp = "7([0-9]{9})")
	private String numOrden;

	/** The descripcion. */
	private String descripcion;
	
	private boolean canje;

	/**
	 * Gets the banca.
	 *
	 * @return the banca
	 */
	public String getBanca() {
		return banca;
	}

	/**
	 * Sets the banca.
	 *
	 * @param banca
	 *            the new banca
	 */
	public void setBanca(String banca) {
		this.banca = banca;
	}

	/**
	 * Gets the num orden.
	 *
	 * @return the num orden
	 */
	public String getNumOrden() {
		return numOrden;
	}

	/**
	 * Sets the num orden.
	 *
	 * @param numOrden
	 *            the new num orden
	 */
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
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

	public boolean isCanje() {
		return canje;
	}

	public void setCanje(boolean canje) {
		this.canje = canje;
	}

}
