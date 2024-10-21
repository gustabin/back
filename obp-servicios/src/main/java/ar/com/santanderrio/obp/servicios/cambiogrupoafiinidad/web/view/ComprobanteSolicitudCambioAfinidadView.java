/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaComprobanteDTO;

/**
 * The Class ComprobanteSolicitudCambioAfinidadView.
 */
public class ComprobanteSolicitudCambioAfinidadView {

	/** The nro socio advantage. */
	private String nroSocioAdvantage;
	
	/** The numero gestion. */
	private String numeroGestion;
	
	/** The tarjetas asociadas. */
	private List<TarjetaAsociadaComprobanteDTO> tarjetasAsociadas;
	
//	private String email;
//	
//	private String nroCelular;
//	
//	private String empresaCelular;
	
	/** The fecha hora. */
private String fechaHora;
	
	/** The info pie. */
	private String infoPie;

	/**
	 * Gets the nro socio advantage.
	 *
	 * @return the nro socio advantage
	 */
	public String getNroSocioAdvantage() {
		return nroSocioAdvantage;
	}

	/**
	 * Sets the nro socio advantage.
	 *
	 * @param nroSocioAdvantage
	 *            the new nro socio advantage
	 */
	public void setNroSocioAdvantage(String nroSocioAdvantage) {
		this.nroSocioAdvantage = nroSocioAdvantage;
	}

	/**
	 * Gets the numero gestion.
	 *
	 * @return the numero gestion
	 */
	public String getNumeroGestion() {
		return numeroGestion;
	}

	/**
	 * Sets the numero gestion.
	 *
	 * @param numeroGestion
	 *            the new numero gestion
	 */
	public void setNumeroGestion(String numeroGestion) {
		this.numeroGestion = numeroGestion;
	}

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getNroCelular() {
//		return nroCelular;
//	}
//
//	public void setNroCelular(String nroCelular) {
//		this.nroCelular = nroCelular;
//	}
//
//	public String getEmpresaCelular() {
//		return empresaCelular;
//	}
//
//	public void setEmpresaCelular(String empresaCelular) {
//		this.empresaCelular = empresaCelular;
//	}

	/**
 * Gets the fecha hora.
 *
 * @return the fecha hora
 */
public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the info pie.
	 *
	 * @return the info pie
	 */
	public String getInfoPie() {
		return infoPie;
	}

	/**
	 * Sets the info pie.
	 *
	 * @param infoPie
	 *            the new info pie
	 */
	public void setInfoPie(String infoPie) {
		this.infoPie = infoPie;
	}

	/**
	 * Gets the tarjetas asociadas.
	 *
	 * @return the tarjetas asociadas
	 */
	public List<TarjetaAsociadaComprobanteDTO> getTarjetasAsociadas() {
		return tarjetasAsociadas;
	}

	/**
	 * Sets the tarjetas asociadas.
	 *
	 * @param tarjetasAsociadas
	 *            the new tarjetas asociadas
	 */
	public void setTarjetasAsociadas(List<TarjetaAsociadaComprobanteDTO> tarjetasAsociadas) {
		this.tarjetasAsociadas = tarjetasAsociadas;
	}
	
}
