/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class ConfigFondoBPrivView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigFondoBPrivView {

	/** The cuentas debito. */
	private List<CuentasAdhesionDebitoView> cuentasDebito;

	/** The cuenta banca privada. */
	private String cuentaBancaPrivada;

	/** The contratoAceptado. */
	private boolean contratoAceptado;

	/** The mensaje limite superior. */
	private String mensajeLimiteSuperior;

	/** The mensaje limite inferior. */
	private String mensajeLimiteInferior;

	/** The mensaje legales 10014. */
	private String legales;

	/** The tiene gastos. */
	private String tieneGastos;
	/** The informacion gastos. */
	private String informacionGastos;

	/** The id mensaje legal. */
	private String idMensajeGastos;
	
	/** The codigo fondo */
	private String codigoFondo;
	
	/** The Url reglament. */
	private String urlReglamento;

	private DatosCotizacionFondoView informacion;
	
	/**
	 * @return the codFondo
	 */
	public String getCodFondo() {
		return codigoFondo;
	}

	/**
	 * @param codFondo the codFondo to set
	 */
	public void setCodFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * @return the urlReglamento
	 */
	public String getUrlReglamento() {
		return urlReglamento;
	}

	/**
	 * @param urlReglamento the urlReglamento to set
	 */
	public void setUrlReglamento(String urlReglamento) {
		this.urlReglamento = urlReglamento;
	}

	/**
	 * Gets the cuenta banca privada.
	 *
	 * @return the cuenta banca privada
	 */
	public String getCuentaBancaPrivada() {
		return cuentaBancaPrivada;
	}

	/**
	 * Sets the cuenta banca privada.
	 *
	 * @param cuentaBancaPrivada
	 *            the new cuenta banca privada
	 */
	public void setCuentaBancaPrivada(String cuentaBancaPrivada) {
		this.cuentaBancaPrivada = cuentaBancaPrivada;
	}

	/**
	 * Checks if is contrato aceptado.
	 *
	 * @return true, if is contrato aceptado
	 */
	public boolean isContratoAceptado() {
		return contratoAceptado;
	}

	/**
	 * Sets the contrato aceptado.
	 *
	 * @param contratoAceptado
	 *            the new contrato aceptado
	 */
	public void setContratoAceptado(boolean contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}

	/**
	 * Gets the cuentas debito.
	 *
	 * @return the cuentas debito
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebito() {
		return cuentasDebito;
	}

	/**
	 * Sets the cuentas debito.
	 *
	 * @param cuentasDebito
	 *            the new cuentas debito
	 */
	public void setCuentasDebito(List<CuentasAdhesionDebitoView> cuentasDebito) {
		this.cuentasDebito = cuentasDebito;
	}

	/**
	 * Gets the mensaje limite superior.
	 *
	 * @return the mensaje limite superior
	 */
	public String getMensajeLimiteSuperior() {
		return mensajeLimiteSuperior;
	}

	/**
	 * Sets the mensaje limite superior.
	 *
	 * @param mensajeLimiteSuperior
	 *            the new mensaje limite superior
	 */
	public void setMensajeLimiteSuperior(String mensajeLimiteSuperior) {
		this.mensajeLimiteSuperior = mensajeLimiteSuperior;
	}

	/**
	 * Gets the mensaje limite inferior.
	 *
	 * @return the mensaje limite inferior
	 */
	public String getMensajeLimiteInferior() {
		return mensajeLimiteInferior;
	}

	/**
	 * Sets the mensaje limite inferior.
	 *
	 * @param mensajeLimiteInferior
	 *            the new mensaje limite inferior
	 */
	public void setMensajeLimiteInferior(String mensajeLimiteInferior) {
		this.mensajeLimiteInferior = mensajeLimiteInferior;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the tiene gastos.
	 *
	 * @return the tiene gastos
	 */
	public String getTieneGastos() {
		return tieneGastos;
	}

	/**
	 * Sets the tiene gastos.
	 *
	 * @param tieneGastos
	 *            the new tiene gastos
	 */
	public void setTieneGastos(String tieneGastos) {
		this.tieneGastos = tieneGastos;
	}

	/**
	 * Gets the id mensaje gastos.
	 *
	 * @return the id mensaje gastos
	 */
	public String getIdMensajeGastos() {
		return idMensajeGastos;
	}

	/**
	 * Sets the id mensaje gastos.
	 *
	 * @param idMensajeGastos
	 *            the new id mensaje gastos
	 */
	public void setIdMensajeGastos(String idMensajeGastos) {
		this.idMensajeGastos = idMensajeGastos;
	}

	/**
	 * Gets the informacion gastos.
	 *
	 * @return the informacion gastos
	 */
	public String getInformacionGastos() {
		return informacionGastos;
	}

	/**
	 * Sets the informacion gastos.
	 *
	 * @param informacionGastos
	 *            the new informacion gastos
	 */
	public void setInformacionGastos(String informacionGastos) {
		this.informacionGastos = informacionGastos;
	}

	public DatosCotizacionFondoView getInformacion() {
		return informacion;
	}

	public void setInformacion(DatosCotizacionFondoView informacion) {
		this.informacion = informacion;
	}
}
