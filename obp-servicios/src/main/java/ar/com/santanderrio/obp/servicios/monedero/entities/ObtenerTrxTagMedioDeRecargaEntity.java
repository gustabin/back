/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

/**
 * The Class ObtenerTrxTagMedioDeRecarga.
 */
public class ObtenerTrxTagMedioDeRecargaEntity {

	/** The tipo. */
	private String tipo;

	/** The id cuenta vitual. */
	private String idCuentaVitual;

	/** The id tarjeta. */
	private String idTarjeta;

	/** The id marca tarjeta. */
	private String idMarcaTarjeta;

	/** The ult 4 digitos tarjetas. */
	private String ult4DigitosTarjetas;

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the id cuenta vitual.
	 *
	 * @return the id cuenta vitual
	 */
	public String getIdCuentaVitual() {
		return idCuentaVitual;
	}

	/**
	 * Sets the id cuenta vitual.
	 *
	 * @param idCuentaVitual
	 *            the new id cuenta vitual
	 */
	public void setIdCuentaVitual(String idCuentaVitual) {
		this.idCuentaVitual = idCuentaVitual;
	}

	/**
	 * Gets the id tarjeta.
	 *
	 * @return the id tarjeta
	 */
	public String getIdTarjeta() {
		return idTarjeta;
	}

	/**
	 * Sets the id tarjeta.
	 *
	 * @param idTarjeta
	 *            the new id tarjeta
	 */
	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	/**
	 * Gets the id marca tarjeta.
	 *
	 * @return the id marca tarjeta
	 */
	public String getIdMarcaTarjeta() {
		return idMarcaTarjeta;
	}

	/**
	 * Sets the id marca tarjeta.
	 *
	 * @param idMarcaTarjeta
	 *            the new id marca tarjeta
	 */
	public void setIdMarcaTarjeta(String idMarcaTarjeta) {
		this.idMarcaTarjeta = idMarcaTarjeta;
	}

	/**
	 * Gets the ult 4 digitos tarjetas.
	 *
	 * @return the ult 4 digitos tarjetas
	 */
	public String getUlt4DigitosTarjetas() {
		return ult4DigitosTarjetas;
	}

	/**
	 * Sets the ult 4 digitos tarjetas.
	 *
	 * @param ult4DigitosTarjetas
	 *            the new ult 4 digitos tarjetas
	 */
	public void setUlt4DigitosTarjetas(String ult4DigitosTarjetas) {
		this.ult4DigitosTarjetas = ult4DigitosTarjetas;
	}
}
