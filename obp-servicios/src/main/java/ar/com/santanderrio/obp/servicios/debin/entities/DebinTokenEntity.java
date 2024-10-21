package ar.com.santanderrio.obp.servicios.debin.entities;

import java.io.Serializable;

/**
 * The Class DebinTokenEntity.
 *
 * @author sergio.e.goldentair
 */
public class DebinTokenEntity implements Serializable {

	/**
	 * The serial Id.
	 */
	private static final long serialVersionUID = -7905126483249941790L;

	/** The tipo documento. */
	private String tipoDocumento;

	/** The nro documento. */
	private String nroDocumento;

	/** The cuit. */
	private String cuit;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The canal. */
	private String canal;

	/** The ip. */
	private String ip;

	/** The terminal. */
	private String terminal;

	/** The doble factor verificado. */
	private String dobleFactorVerificado;

	/** The permite preautorizado. */
	private String permitePreautorizado;

	/** The categoria limite. */
	private String categoriaLimite;

	/** The mostrar solo transferencias. */
	private String mostrarSoloTransferencias;

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the nro documento.
	 *
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nroTarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta the nroTarjeta to set
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the terminal.
	 *
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Sets the terminal.
	 *
	 * @param terminal the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * Gets the doble factor verificado.
	 *
	 * @return the dobleFactorVerificado
	 */
	public String getDobleFactorVerificado() {
		return dobleFactorVerificado;
	}

	/**
	 * Sets the doble factor verificado.
	 *
	 * @param dobleFactorVerificado the dobleFactorVerificado to set
	 */
	public void setDobleFactorVerificado(String dobleFactorVerificado) {
		this.dobleFactorVerificado = dobleFactorVerificado;
	}

	/**
	 * Gets the permite preautorizado.
	 *
	 * @return the permitePreautorizado
	 */
	public String getPermitePreautorizado() {
		return permitePreautorizado;
	}

	/**
	 * Sets the permite preautorizado.
	 *
	 * @param permitePreautorizado the permitePreautorizado to set
	 */
	public void setPermitePreautorizado(String permitePreautorizado) {
		this.permitePreautorizado = permitePreautorizado;
	}

	/**
	 * Gets the categoria limite.
	 *
	 * @return the categoriaLimite
	 */
	public String getCategoriaLimite() {
		return categoriaLimite;
	}

	/**
	 * Sets the categoria limite.
	 *
	 * @param categoriaLimite the categoriaLimite to set
	 */
	public void setCategoriaLimite(String categoriaLimite) {
		this.categoriaLimite = categoriaLimite;
	}

	/**
	 * @return the mostrarSoloTransferencias
	 */
	public String getMostrarSoloTransferencias() {
		return mostrarSoloTransferencias;
	}

	/**
	 * @param mostrarSoloTransferencias the mostrarSoloTransferencias to set
	 */
	public void setMostrarSoloTransferencias(String mostrarSoloTransferencias) {
		this.mostrarSoloTransferencias = mostrarSoloTransferencias;
	}

}