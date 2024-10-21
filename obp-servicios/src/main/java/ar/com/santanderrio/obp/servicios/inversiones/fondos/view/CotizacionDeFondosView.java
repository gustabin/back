/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class CotizacionDeFondosView.
 */
public class CotizacionDeFondosView {

	/** The fondos. */
	private List<DatosCotizacionFondoView> fondos = new ArrayList<DatosCotizacionFondoView>();

	/** The legales. */
	private String legales;

	/** The legales Super Ahorro Pesos */
	private String legalSuperAhorroPesos;
	
	/** The is ex citi. */
	private Boolean isExCiti;
	
	/** The url delta. */
	private String urlDelta;
	
	/** The url compass. */
	private String urlCompass;

	/**
	 * Gets the fondos.
	 *
	 * @return the fondos
	 */
	public List<DatosCotizacionFondoView> getFondos() {
		return fondos;
	}

	/**
	 * Sets the fondos.
	 *
	 * @param fondos
	 *            the new fondos
	 */
	public void setFondos(List<DatosCotizacionFondoView> fondos) {
		this.fondos = fondos;
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
	 * Gets the legalSuperAhorroPesos.
	 *
	 * @return the legalSuperAhorroPesos
	 */
	public String getLegalSuperAhorroPesos() {
		return legalSuperAhorroPesos;
	}

	/**
	 * Sets the legalSuperAhorroPesos.
	 *
	 * @param legalSuperAhorroPesos
	 *            the new legalSuperAhorroPesos
	 */
	public void setLegalSuperAhorroPesos(String legalSuperAhorroPesos) {
		this.legalSuperAhorroPesos = legalSuperAhorroPesos;
	}

    /**
	 * Gets the url delta.
	 *
	 * @return the url delta
	 */
    public String getUrlDelta() {
        return urlDelta;
    }

    /**
	 * Sets the url delta.
	 *
	 * @param urlDelta
	 *            the new url delta
	 */
    public void setUrlDelta(String urlDelta) {
        this.urlDelta = urlDelta;
    }

    /**
	 * Gets the url compass.
	 *
	 * @return the url compass
	 */
    public String getUrlCompass() {
        return urlCompass;
    }

    /**
	 * Sets the url compass.
	 *
	 * @param urlCompass
	 *            the new url compass
	 */
    public void setUrlCompass(String urlCompass) {
        this.urlCompass = urlCompass;
    }

    /**
	 * Gets the checks if is ex citi.
	 *
	 * @return the checks if is ex citi
	 */
    public Boolean getIsExCiti() {
        return isExCiti;
    }

    /**
	 * Sets the checks if is ex citi.
	 *
	 * @param isExCiti
	 *            the new checks if is ex citi
	 */
    public void setIsExCiti(Boolean isExCiti) {
        this.isExCiti = isExCiti;
    }
}
