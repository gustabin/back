/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * Objeto utilizado para el Input del FondoSEI.
 * 
 * @author
 *
 */
public class ConfiguracionRescateOutView {

	/** The cuentas destino. */
	private List<CuentasAdhesionDebitoView> cuentasDestino;

	/** The mensaje limite inferior. */
	private String mensajeLimiteInferior;

	/** The mensaje limite superior. */
	private String mensajeLimiteSuperior;

	/** The legales. */
	private String legales;

	/** The legales especie. */
	private String legalesEspecie;

	/** The tooltip. */
	private String tooltip;
	
	/** The url reglamento */
	private String urlReglamento;

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
	 * Gets the cuentas destino.
	 *
	 * @return the cuentas destino
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDestino() {
		return cuentasDestino;
	}

	/**
	 * Sets the cuentas destino.
	 *
	 * @param cuentasDestino
	 *            the new cuentas destino
	 */
	public void setCuentasDestino(List<CuentasAdhesionDebitoView> cuentasDestino) {
		this.cuentasDestino = cuentasDestino;
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
     * @return the tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * @param tooltip the tooltip to set
     */
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @return the legalesEspecie
     */
    public String getLegalesEspecie() {
        return legalesEspecie;
    }

    /**
     * @param legalesEspecie the legalesEspecie to set
     */
    public void setLegalesEspecie(String legalesEspecie) {
        this.legalesEspecie = legalesEspecie;
    }

}