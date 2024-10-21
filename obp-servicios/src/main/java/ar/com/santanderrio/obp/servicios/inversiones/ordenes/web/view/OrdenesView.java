/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.inversiones.comun.OrdenBaseView;

/**
 * The Class OrdenesView. Representa el dto que transporta los datos al a grilla
 * de busqueda de ordenes y operaciones.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 * 
 */
@XmlRootElement(name = "ordenesView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenesView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;

	/** The filtro. */
	FiltrosOrdenesView filtro = new FiltrosOrdenesView();

	/** The cuentas. */
	List<CuentaView> cuentas = new ArrayList<CuentaView>();

	/** The cuenta seleccionada. */
	private String cuentaSeleccionada;

	/** The ordenes. */
	List<OrdenBaseView> ordenes = new ArrayList<OrdenBaseView>();

	/** The Mensaje. */
	private String mensajeSinOrdenes;

	/**
	 * Instantiates a new ordenes view.
	 */
	public OrdenesView() {
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentaView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the ordenes.
	 *
	 * @return the ordenes
	 */
	public List<OrdenBaseView> getOrdenes() {
		return ordenes;
	}

	/**
	 * Sets the ordenes.
	 *
	 * @param ordenes
	 *            the new ordenes
	 */
	public void setOrdenes(List<OrdenBaseView> ordenes) {
		this.ordenes = ordenes;
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuenta seleccionada
	 */
	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuentaSeleccionada
	 *            the new cuenta seleccionada
	 */
	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * Gets the filtro.
	 *
	 * @return the filtro
	 */
	public FiltrosOrdenesView getFiltro() {
		return filtro;
	}

	/**
	 * Sets the filtro.
	 *
	 * @param filtro
	 *            the new filtro
	 */
	public void setFiltro(FiltrosOrdenesView filtro) {
		this.filtro = filtro;
	}

	/**
	 * Gets the mensaje sin ordenes.
	 *
	 * @return the mensajeSinOrdenes
	 */
	public String getMensajeSinOrdenes() {
		return mensajeSinOrdenes;
	}

	/**
	 * Sets the mensaje sin ordenes.
	 *
	 * @param mensajeSinOrdenes
	 *            the mensajeSinOrdenes to set
	 */
	public void setMensajeSinOrdenes(String mensajeSinOrdenes) {
		this.mensajeSinOrdenes = mensajeSinOrdenes;
	}

}
