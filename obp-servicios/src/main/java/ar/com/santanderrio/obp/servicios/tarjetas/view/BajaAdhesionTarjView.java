/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class BajaAdhesionTarjView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BajaAdhesionTarjView {

	/** The nro tarjeta. */
	public String nroTarjeta;

	/** The nro cuenta producto. */
	public String nroCuentaProducto;

	/** The reintentar. */
	public String reintentar;

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the new nro cuenta producto
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}
}
