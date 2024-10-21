package ar.com.santanderrio.obp.servicios.getnet.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import ar.com.santanderrio.obp.servicios.comun.view.ComboView;

/**
 * The Class GetNetView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GetnetView {
	
	/** The email. */
	private String email;
	
	/** The SaltoURL. */
	private String url;

	/** The codigo area. */
	private String codigoArea;
	
	/** The celular. */
	private String celular;
			
	/** The cuentas. */
	private List<CuentaView> cuentas;
	
	/** The actividades. */
	private List<ComboView> actividades;
	
	/** The ingresos. */
	private List<ComboView> ingresos;
	
	/** The legal. */
	private String legal;	
	
	/** The esGetnet. */
	private Boolean esGetnet;
	
	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the salto url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the codigo area.
	 *
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}
	
	/**
	 * Sets the codigo area.
	 *
	 * @param codigoArea
	 *            the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}
	
	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
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
	 *            the cuentas to set
	 */
	public void setCuentas(List<CuentaView> cuentas) {
		this.cuentas = cuentas;
	}
	
	/**
	 * Gets the actividades.
	 *
	 * @return the actividades
	 */
	public List<ComboView> getActividades() {
		return actividades;
	}
	
	/**
	 * Sets the actividades.
	 *
	 * @param actividades
	 *            the actividades to set
	 */
	public void setActividades(List<ComboView> actividades) {
		this.actividades = actividades;
	}
	
	/**
	 * Gets the ingresos.
	 *
	 * @return the ingresos
	 */
	public List<ComboView> getIngresos() {
		return ingresos;
	}
	
	/**
	 * Sets the ingresos.
	 *
	 * @param ingresos
	 *            the ingresos to set
	 */
	public void setIngresos(List<ComboView> ingresos) {
		this.ingresos = ingresos;
	}
	
	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}
	
	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the esGetnet.
	 *
	 * @return the esGetnet
	 */
	public Boolean getEsGetnet() {
		return esGetnet;
	}

	/**
	 * Sets the esGetnet.
	 *
	 * @param esGetnet
	 *            the esGetnet to set
	 */
	public void setEsGetnet(Boolean esGetnet) {
		this.esGetnet = esGetnet;
	}
	
}
