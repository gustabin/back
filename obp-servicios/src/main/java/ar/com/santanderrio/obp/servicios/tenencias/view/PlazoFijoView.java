/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class PlazoFijoView.
 *
 * @author desa
 */
public class PlazoFijoView {

	/** The penumcon. */
	private String penumcon;

	/** The anio. */
	private String anio;

	/** The divisa. */
	private String divisa;

	/** The firmantes. */
	private List<FirmanteView> firmantes;

	/** The total devengado. */
	private String totalDevengado;

	/** The total percibido. */
	private String totalPercibido;

	/** The total tenencia. */
	private String totalTenencia;

	/** The total tenencia US. */
	private String totalTenenciaUS;

	/** The total devengado US. */
	private String totalDevengadoUS;

	/** The total percibido US. */
	private String totalPercibidoUS;
	
	private String totalAjustePorCer;
	
	private String totalAjustePorCerUS;
	
	private String totalAjustePorCerDev;
	
	private String totalAjustePorCerDevUS;

	/** The int percibido. */
	private List<TipoSaldoView> intPercibido;

	/** The int devengado. */
	private List<TipoSaldoView> intDevengado;

	/** The tenencia. */
	private List<TipoSaldoView> tenencia;

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the firmantes.
	 *
	 * @return the firmantes
	 */
	public List<FirmanteView> getFirmantes() {
		return firmantes;
	}

	/**
	 * Sets the firmantes.
	 *
	 * @param firmantes
	 *            the firmantes to set
	 */
	public void setFirmantes(List<FirmanteView> firmantes) {
		this.firmantes = firmantes;
	}

	/**
	 * Gets the penumcon.
	 *
	 * @return the penumcon
	 */
	public String getPenumcon() {
		return penumcon;
	}

	/**
	 * Sets the penumcon.
	 *
	 * @param penumcon
	 *            the penumcon to set
	 */
	public void setPenumcon(String penumcon) {
		this.penumcon = penumcon;
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

	/**
	 * Gets the total devengado.
	 *
	 * @return the totalDevengado
	 */
	public String getTotalDevengado() {
		return totalDevengado;
	}

	/**
	 * Sets the total devengado.
	 *
	 * @param totalDevengado
	 *            the totalDevengado to set
	 */
	public void setTotalDevengado(String totalDevengado) {
		this.totalDevengado = totalDevengado;
	}

	/**
	 * Gets the total percibido.
	 *
	 * @return the totalPercibido
	 */
	public String getTotalPercibido() {
		return totalPercibido;
	}

	/**
	 * Sets the total percibido.
	 *
	 * @param totalPercibido
	 *            the totalPercibido to set
	 */
	public void setTotalPercibido(String totalPercibido) {
		this.totalPercibido = totalPercibido;
	}

	/**
	 * Gets the total tenencia.
	 *
	 * @return the totalTenencia
	 */
	public String getTotalTenencia() {
		return totalTenencia;
	}

	/**
	 * Sets the total tenencia.
	 *
	 * @param totalTenencia
	 *            the totalTenencia to set
	 */
	public void setTotalTenencia(String totalTenencia) {
		this.totalTenencia = totalTenencia;
	}

	/**
	 * Gets the int percibido.
	 *
	 * @return the intPercibido
	 */
	public List<TipoSaldoView> getIntPercibido() {
		return intPercibido;
	}

	/**
	 * Sets the int percibido.
	 *
	 * @param intPercibido
	 *            the intPercibido to set
	 */
	public void setIntPercibido(List<TipoSaldoView> intPercibido) {
		this.intPercibido = intPercibido;
	}

	/**
	 * Gets the int devengado.
	 *
	 * @return the intDevengado
	 */
	public List<TipoSaldoView> getIntDevengado() {
		return intDevengado;
	}

	/**
	 * Sets the int devengado.
	 *
	 * @param intDevengado
	 *            the intDevengado to set
	 */
	public void setIntDevengado(List<TipoSaldoView> intDevengado) {
		this.intDevengado = intDevengado;
	}

	/**
	 * Gets the tenencia.
	 *
	 * @return the tenencia
	 */
	public List<TipoSaldoView> getTenencia() {
		return tenencia;
	}

	/**
	 * Sets the tenencia.
	 *
	 * @param tenencia
	 *            the tenencia to set
	 */
	public void setTenencia(List<TipoSaldoView> tenencia) {
		this.tenencia = tenencia;
	}

	/**
	 * Gets the total tenencia US.
	 *
	 * @return the totalTenenciaUS
	 */
	public String getTotalTenenciaUS() {
		return totalTenenciaUS;
	}

	/**
	 * Sets the total tenencia US.
	 *
	 * @param totalTenenciaUS
	 *            the totalTenenciaUS to set
	 */
	public void setTotalTenenciaUS(String totalTenenciaUS) {
		this.totalTenenciaUS = totalTenenciaUS;
	}

	/**
	 * Gets the total devengado US.
	 *
	 * @return the totalDevengadoUS
	 */
	public String getTotalDevengadoUS() {
		return totalDevengadoUS;
	}

	/**
	 * Sets the total devengado US.
	 *
	 * @param totalDevengadoUS
	 *            the totalDevengadoUS to set
	 */
	public void setTotalDevengadoUS(String totalDevengadoUS) {
		this.totalDevengadoUS = totalDevengadoUS;
	}

	/**
	 * Gets the total percibido US.
	 *
	 * @return the totalPercibidoUS
	 */
	public String getTotalPercibidoUS() {
		return totalPercibidoUS;
	}

	/**
	 * Sets the total percibido US.
	 *
	 * @param totalPercibidoUS
	 *            the totalPercibidoUS to set
	 */
	public void setTotalPercibidoUS(String totalPercibidoUS) {
		this.totalPercibidoUS = totalPercibidoUS;
	}

	public String getTotalAjustePorCer() {
		return totalAjustePorCer;
	}

	public void setTotalAjustePorCer(String totalAjustePorCer) {
		this.totalAjustePorCer = totalAjustePorCer;
	}
		
	public String getTotalAjustePorCerUS() {
		return totalAjustePorCerUS;
	}
	
	
	public String getTotalAjustePorCerDev() {
		return totalAjustePorCerDev;
	}
	
	
	public void setTotalAjustePorCerDev(String totalAjustePorCerDev) {
		this.totalAjustePorCerDev = totalAjustePorCerDev;
	}
	
	public String getTotalAjustePorCerDevUs() {
		return totalAjustePorCerDevUS;
	}
	
	public void setTotalAjustePorCerDevUs(String totalAjustePorCerDevUs) {
		this.totalAjustePorCerDevUS = totalAjustePorCerDevUs;
	}


	public void setTotalAjustePorCerUS(String totalAjustePorCerUS) {
		this.totalAjustePorCerUS = totalAjustePorCerUS;
	}
	
}
