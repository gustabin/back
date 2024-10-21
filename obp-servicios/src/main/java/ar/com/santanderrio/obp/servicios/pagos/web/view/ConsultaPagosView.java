/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class ConsultaPagosView.
 *
 * @author B039636
 */
@XmlRootElement(name = "consultaPagosView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaPagosView {

	/** The mes. */
	private String mes;

	/** The anio. */
	private String anio;

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

}
