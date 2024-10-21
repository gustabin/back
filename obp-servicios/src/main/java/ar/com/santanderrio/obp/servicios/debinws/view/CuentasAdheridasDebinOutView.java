/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class CuentasAdheridasDebinOutView.
 */
public class CuentasAdheridasDebinOutView {
    
    /** cuentas en pesos. */
    /** The cuentas. */
    private List<CuentasAdhesionDebitoView> cuentasPesos = new ArrayList<CuentasAdhesionDebitoView>();
    
    /** cuentas en dolares. */
    /** The cuentas. */
    private List<CuentasAdhesionDebitoView>  cuentasDolares = new ArrayList<CuentasAdhesionDebitoView>();
    
    /** para combo selector de monedas. */
    private List<MonedaDebinView> monedas = new ArrayList<MonedaDebinView>();

    /** mensajeSinDesafio. */
    private String mensajeSinDesafio;

    /** urlAyuda. */
    private String urlAyuda;
    
    


    /**
	 * Gets the monedas.
	 *
	 * @return the monedas
	 */
    public List<MonedaDebinView> getMonedas() {
        return monedas;
    }

    /**
	 * Sets the monedas.
	 *
	 * @param monedas
	 *            the monedas to set
	 */
    public void setMonedas(List<MonedaDebinView> monedas) {
        this.monedas = monedas;
    }

    /**
	 * Gets the mensaje sin desafio.
	 *
	 * @return the mensajeSinDesafio
	 */
    public String getMensajeSinDesafio() {
        return mensajeSinDesafio;
    }

    /**
	 * Sets the mensaje sin desafio.
	 *
	 * @param mensajeSinDesafio
	 *            the mensajeSinDesafio to set
	 */
    public void setMensajeSinDesafio(String mensajeSinDesafio) {
        this.mensajeSinDesafio = mensajeSinDesafio;
    }

    /**
	 * Gets the url ayuda.
	 *
	 * @return the urlAyuda
	 */
    public String getUrlAyuda() {
        return urlAyuda;
    }

    /**
	 * Sets the url ayuda.
	 *
	 * @param urlAyuda
	 *            the urlAyuda to set
	 */
    public void setUrlAyuda(String urlAyuda) {
        this.urlAyuda = urlAyuda;
    }

    /**
	 * Gets the cuentas pesos.
	 *
	 * @return the cuentasPesos
	 */
    public List<CuentasAdhesionDebitoView> getCuentasPesos() {
        return cuentasPesos;
    }

    /**
	 * Sets the cuentas pesos.
	 *
	 * @param cuentasPesos
	 *            the cuentasPesos to set
	 */
    public void setCuentasPesos(List<CuentasAdhesionDebitoView> cuentasPesos) {
        this.cuentasPesos = cuentasPesos;
    }

    /**
	 * Gets the cuentas dolares.
	 *
	 * @return the cuentasDolares
	 */
    public List<CuentasAdhesionDebitoView> getCuentasDolares() {
        return cuentasDolares;
    }

    /**
	 * Sets the cuentas dolares.
	 *
	 * @param cuentasDolares
	 *            the cuentasDolares to set
	 */
    public void setCuentasDolares(List<CuentasAdhesionDebitoView> cuentasDolares) {
        this.cuentasDolares = cuentasDolares;
    }


    

}
