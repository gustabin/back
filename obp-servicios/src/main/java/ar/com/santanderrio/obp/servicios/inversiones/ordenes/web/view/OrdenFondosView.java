/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.inversiones.comun.OrdenBaseView;

/**
 * The Class OrdenOperacionView.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
@XmlRootElement(name = "ordenOperacionView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenFondosView extends OrdenBaseView{

	/** The tipo. */
	private String tipo;

	/** The fondo. */
	private String fondo;

	/** The importe. */
	private String importe;

	/**
	 * Instantiates a new orden operacion view.
	 */
	public OrdenFondosView() {
		super();
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the fondo.
	 *
	 * @return the fondo
	 */
	public String getFondo() {
		return fondo;
	}

	/**
	 * Sets the fondo.
	 *
	 * @param fondo
	 *            the new fondo
	 */
	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

}
