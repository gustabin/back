/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class ConsultaUnidadControlOutEntity.
 *
 * @author alejandro_leal
 */
@Record
public class ConsultaUnidadControlOutEntity {
	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** *. */
	@Field
	private String sucursalAdministradora;

	/** *. */
	@Field
	private String banca;

	/** *. */
	@Field
	private String division;

	/** *. */
	@Field
	private String teamLeader;

	/** *. */
	@Field
	private String ejecutivoDeCuenta;

	/** *. */
	@Field
	private String jefeDeArea;

	/** *. */
	@Field
	private String gerente;

	/** *. */
	@Field
	private String timestamp;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the sucursal administradora.
	 *
	 * @return the sucursalAdministradora
	 */
	public String getSucursalAdministradora() {
		return sucursalAdministradora;
	}

	/**
	 * Sets the sucursal administradora.
	 *
	 * @param sucursalAdministradora
	 *            the sucursalAdministradora to set
	 */
	public void setSucursalAdministradora(String sucursalAdministradora) {
		this.sucursalAdministradora = sucursalAdministradora;
	}

	/**
	 * Gets the banca.
	 *
	 * @return the banca
	 */
	public String getBanca() {
		return banca;
	}

	/**
	 * Sets the banca.
	 *
	 * @param banca
	 *            the banca to set
	 */
	public void setBanca(String banca) {
		this.banca = banca;
	}

	/**
	 * Gets the division.
	 *
	 * @return the división
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * Sets the division.
	 *
	 * @param division
	 *            the new division
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * Gets the team leader.
	 *
	 * @return the teamLeader
	 */
	public String getTeamLeader() {
		return teamLeader;
	}

	/**
	 * Sets the team leader.
	 *
	 * @param teamLeader
	 *            the teamLeader to set
	 */
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	/**
	 * Gets the ejecutivo de cuenta.
	 *
	 * @return the ejecutivoDeCuenta
	 */
	public String getEjecutivoDeCuenta() {
		return ejecutivoDeCuenta;
	}

	/**
	 * Sets the ejecutivo de cuenta.
	 *
	 * @param ejecutivoDeCuenta
	 *            the ejecutivoDeCuenta to set
	 */
	public void setEjecutivoDeCuenta(String ejecutivoDeCuenta) {
		this.ejecutivoDeCuenta = ejecutivoDeCuenta;
	}

	/**
	 * Gets the jefe de area.
	 *
	 * @return the jefeDeArea
	 */
	public String getJefeDeArea() {
		return jefeDeArea;
	}

	/**
	 * Sets the jefe de area.
	 *
	 * @param jefeDeArea
	 *            the jefeDeArea to set
	 */
	public void setJefeDeArea(String jefeDeArea) {
		this.jefeDeArea = jefeDeArea;
	}

	/**
	 * Gets the gerente.
	 *
	 * @return the gerente
	 */
	public String getGerente() {
		return gerente;
	}

	/**
	 * Sets the gerente.
	 *
	 * @param gerente
	 *            the gerente to set
	 */
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
