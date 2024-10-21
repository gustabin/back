package ar.com.santanderrio.obp.servicios.debinws.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.debinws.dto.CuentaAdhesionDTO;

public class ConsultarAdhesionDebinesView {

    private String mensajeCuentaEspecial;

    private List<CuentaAdhesionDTO> cuentasPesos;

    private List<CuentaAdhesionDTO> cuentasDolares;

    /** mensajeSinDesafio. */
    private String mensajeSinDesafio;

    /** urlAyuda. */
    private String urlAyuda;    

    /**
     * @return the mensajeCuentaEspecial
     */
    public String getMensajeCuentaEspecial() {
        return mensajeCuentaEspecial;
    }

    /**
     * @param mensajeCuentaEspecial the mensajeCuentaEspecial to set
     */
    public void setMensajeCuentaEspecial(String mensajeCuentaEspecial) {
        this.mensajeCuentaEspecial = mensajeCuentaEspecial;
    }

    /**
     * @return the cuentasPesos
     */
    public List<CuentaAdhesionDTO> getCuentasPesos() {
        return cuentasPesos;
    }

    /**
     * @param cuentasPesos the cuentasPesos to set
     */
    public void setCuentasPesos(List<CuentaAdhesionDTO> cuentasPesos) {
        this.cuentasPesos = cuentasPesos;
    }

    /**
     * @return the cuentasDolares
     */
    public List<CuentaAdhesionDTO> getCuentasDolares() {
        return cuentasDolares;
    }

    /**
     * @param cuentasDolares the cuentasDolares to set
     */
    public void setCuentasDolares(
            List<CuentaAdhesionDTO> cuentasDolares) {
        this.cuentasDolares = cuentasDolares;
    }

	public String getMensajeSinDesafio() {
		return mensajeSinDesafio;
	}

	public void setMensajeSinDesafio(String mensajeSinDesafio) {
		this.mensajeSinDesafio = mensajeSinDesafio;
	}

	public String getUrlAyuda() {
		return urlAyuda;
	}

	public void setUrlAyuda(String urlAyuda) {
		this.urlAyuda = urlAyuda;
	}

}
