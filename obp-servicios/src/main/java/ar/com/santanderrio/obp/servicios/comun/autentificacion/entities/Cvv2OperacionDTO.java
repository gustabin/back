/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * Esta clase representa el objeto DTO de CVV2 para la autentificacion.
 *
 */
@XmlRootElement(name = "cvv2DTO", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cvv2OperacionDTO extends OperacionDTO {

	/** The ingreso cvv2. */
	private String ingresoCvv2;

	/** The cvv 2 len. */
	private String cvv2Len;

	/**
	 * Instantiates a new cvv2 operacion DTO.
	 */
	public Cvv2OperacionDTO() {
		super(TipoDesafioEnum.CVV2);
	}

	/**
	 * Instantiates a new cvv2 operacion DTO.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public Cvv2OperacionDTO(String mensaje) {
		super(mensaje, TipoDesafioEnum.CVV2);
	}

	/**
	 * Gets the ingreso cvv 2.
	 *
	 * @return the ingreso cvv 2
	 */
	public String getIngresoCvv2() {
		return ingresoCvv2;
	}

	/**
	 * Sets the ingreso cvv 2.
	 *
	 * @param ingresoCvv2
	 *            the new ingreso cvv 2
	 */
	public void setIngresoCvv2(String ingresoCvv2) {
		this.ingresoCvv2 = ingresoCvv2;
	}

	/**
	 * Gets the cvv 2 len.
	 *
	 * @return the cvv2Len
	 */
	public String getCvv2Len() {
		return cvv2Len;
	}

	/**
	 * Sets the cvv 2 len.
	 *
	 * @param cvv2Len
	 *            the cvv2Len to set
	 */
	public void setCvv2Len(String cvv2Len) {
		this.cvv2Len = cvv2Len;
	}

}
