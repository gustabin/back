/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class MontoValorView.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 05/01/2017
 */
@XmlRootElement(name = "montoValorView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MontoValorView {

	/** The id. */
	private BigDecimal id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new monto valor view.
	 */
	public MontoValorView() {
		super();
	}

	/**
	 * Instantiates a new monto valor view.
	 *
	 * @param valor
	 *            the valor
	 * @param descripcion
	 *            the descripcion
	 */
	public MontoValorView(BigDecimal valor, String descripcion) {
		this.setId(valor);
		this.setDescripcion(descripcion);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public BigDecimal getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(BigDecimal id) {
		this.id = id;
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
}
