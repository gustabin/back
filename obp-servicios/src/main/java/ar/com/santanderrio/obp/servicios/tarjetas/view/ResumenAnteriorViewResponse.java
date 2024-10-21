/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenAnterior;

/**
 * Objeto utilizado para el Output del ResumenAnteriorSEI en la operacion
 * obtenerResumen.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResumenAnteriorViewResponse {

	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The resumenes. */
	private List<ResumenAnterior> resumenes;

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the resumenes.
	 *
	 * @return the resumenes
	 */
	public List<ResumenAnterior> getResumenes() {
		return resumenes;
	}

	/**
	 * Sets the resumenes.
	 *
	 * @param resumenes
	 *            the new resumenes
	 */
	public void setResumenes(List<ResumenAnterior> resumenes) {
		this.resumenes = resumenes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenAnteriorViewResponse [marca=" + marca + ", numero=" + numero + ", numeroCuenta=" + numeroCuenta
				+ ", resumenes=" + resumenes + "]";
	}

}
