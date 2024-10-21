/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Enum TipoCuentaTarjeta.
 */
public enum TipoCuentaTarjetaEnum {

	/** The tipocta master. */
	TIPOCTA_MASTER("06", "AB", "MasterCard"),
	/** The tipocta visa. */
	TIPOCTA_VISA("07", "RV", "VISA"),
	/** The tipocta amex. */
	TIPOCTA_AMEX("42", "RA", "AMEX");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/** The alias. */
	private String abreviatura;

	/**
	 * Instantiates a new tipo cuenta tarjeta.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 * @param abreviatura
	 *            the alias
	 */
	TipoCuentaTarjetaEnum(String codigo, String descripcion, String abreviatura) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.abreviatura = abreviatura;
	}

	/**
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo cuenta tarjeta
	 */
	public static TipoCuentaTarjetaEnum fromCodigo(String codigo) {
		TipoCuentaTarjetaEnum[] values = TipoCuentaTarjetaEnum.values();

		TipoCuentaTarjetaEnum response = null;

		for (TipoCuentaTarjetaEnum tipoCuenta : values) {
			if (tipoCuenta.getCodigo().equals(codigo)) {
				response = tipoCuenta;
			}
		}
		return response;
	}

	/**
	 * Gets the tipo cuenta tarjeta from tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the tipo cuenta tarjeta from tipo cuenta
	 */
	public static TipoCuentaTarjetaEnum getTipoCuentaTarjetaFromTipoCuenta(TipoCuenta tipoCuenta) {
		switch (tipoCuenta.getCodigo()) {
		case 7:
			return TipoCuentaTarjetaEnum.TIPOCTA_VISA;
		case 42:
			return TipoCuentaTarjetaEnum.TIPOCTA_AMEX;
		default:
			return TipoCuentaTarjetaEnum.TIPOCTA_MASTER;
		}
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

}
