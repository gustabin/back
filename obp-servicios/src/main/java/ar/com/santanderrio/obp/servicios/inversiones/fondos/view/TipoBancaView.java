/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Encapsula los datos requeridos para la cunsulta de tenencias de un cliente.
 *
 * @author marcelo.ruiz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoBancaView {

	/** el tipo de banca seleccionada. */
	private String tipoBanca;

	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipo banca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}

	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the new tipo banca
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}

}
