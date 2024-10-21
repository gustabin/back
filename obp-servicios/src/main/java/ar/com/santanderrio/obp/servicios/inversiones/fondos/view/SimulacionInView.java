/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class SimulacionInView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulacionInView {

	/** The cuenta titulo. */
	@NotNull
	// @Pattern(regexp = "7([0-9]{9})")
	private String nroCuentaBancaPrivada;

	/** codigo del fondo a suscribir. */
	@Pattern(regexp = "[0-9]{3}")
	private String codigoFondo;

	/** The codigo fondo des. */
	private String codigoFondoDes;

	/** Importe a suscribir. */
	@NotNull
	private BigDecimal importe;

	/**
	 * Gets the nro cuenta banca privada.
	 *
	 * @return the nro cuenta banca privada
	 */
	public String getNroCuentaBancaPrivada() {
		return nroCuentaBancaPrivada;
	}

	/**
	 * Sets the nro cuenta banca privada.
	 *
	 * @param nroCuentaBancaPrivada
	 *            the new nro cuenta banca privada
	 */
	public void setNroCuentaBancaPrivada(String nroCuentaBancaPrivada) {
		this.nroCuentaBancaPrivada = nroCuentaBancaPrivada;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the codigo fondo des.
	 *
	 * @return the codigo fondo des
	 */
	public String getCodigoFondoDes() {
		return codigoFondoDes;
	}

	/**
	 * Sets the codigo fondo des.
	 *
	 * @param codigoFondoDes
	 *            the new codigo fondo des
	 */
	public void setCodigoFondoDes(String codigoFondoDes) {
		this.codigoFondoDes = codigoFondoDes;
	}

}
