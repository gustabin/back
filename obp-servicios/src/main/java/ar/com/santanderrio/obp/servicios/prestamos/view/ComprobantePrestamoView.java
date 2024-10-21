/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ComprobantePrestamoView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ComprobantePrestamoView {

	/** The cbu. */
	@NotNull
	@Size(min = 1)
	private String cbu;

	/** The importe seleccionado. */
	@NotNull
	private String importeSeleccionado;

	/** The importe seleccionado. */
	@NotNull
	private boolean isUva;
	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the importe seleccionado.
	 *
	 * @return the importe seleccionado
	 */
	public String getImporteSeleccionado() {
		return importeSeleccionado;
	}

	/**
	 * Sets the importe seleccionado.
	 *
	 * @param importeSeleccionado
	 *            the new importe seleccionado
	 */
	public void setImporteSeleccionado(String importeSeleccionado) {
		this.importeSeleccionado = importeSeleccionado;
	}

	/**
	 * Gets the isUva.
	 *
	 * @return the is Uva
	 */
	public boolean isUva() {
		return isUva;
	}

	/**
	 * Sets the isUva.
	 *
	 * @param isUva
	 *            the new is uva
	 */
	public void setUva(boolean isUva) {
		this.isUva = isUva;
	}
	
}
