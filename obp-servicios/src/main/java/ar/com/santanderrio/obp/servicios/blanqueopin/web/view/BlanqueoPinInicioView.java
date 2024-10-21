package ar.com.santanderrio.obp.servicios.blanqueopin.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

public class BlanqueoPinInicioView {

	private List<TarjetaView> tarjetas;
	private String mensajeInicio;
	private String mensajeClaveNumerica;
	private String mensajeClaveAlfabetica;
	private String mensajeAmbasClaves;

	/**
	 * 
	 */
	public BlanqueoPinInicioView() {
		super();
	}

	/**
	 * @return the tarjetas
	 */
	public List<TarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<TarjetaView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * @return the mensajeInicio
	 */
	public String getMensajeInicio() {
		return mensajeInicio;
	}

	/**
	 * @param mensajeInicio
	 *            the mensajeInicio to set
	 */
	public void setMensajeInicio(String mensajeInicio) {
		this.mensajeInicio = mensajeInicio;
	}

	/**
	 * @return the mensajeClaveNumerica
	 */
	public String getMensajeClaveNumerica() {
		return mensajeClaveNumerica;
	}

	/**
	 * @param mensajeClaveNumerica
	 *            the mensajeClaveNumerica to set
	 */
	public void setMensajeClaveNumerica(String mensajeClaveNumerica) {
		this.mensajeClaveNumerica = mensajeClaveNumerica;
	}

	public String getMensajeClaveAlfabetica() {
		return mensajeClaveAlfabetica;
	}

	public void setMensajeClaveAlfabetica(String mensajeClaveAlfabetica) {
		this.mensajeClaveAlfabetica = mensajeClaveAlfabetica;
	}

	/**
	 * @return the mensajeAmbasClaves
	 */
	public String getMensajeAmbasClaves() {
		return mensajeAmbasClaves;
	}

	/**
	 * @param mensajeAmbasClaves
	 *            the mensajeAmbasClaves to set
	 */
	public void setMensajeAmbasClaves(String mensajeAmbasClaves) {
		this.mensajeAmbasClaves = mensajeAmbasClaves;
	}

}
