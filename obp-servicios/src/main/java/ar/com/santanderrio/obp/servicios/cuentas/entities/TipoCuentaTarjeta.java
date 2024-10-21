/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoCuentaTarjeta.
 */
public enum TipoCuentaTarjeta {
 
	/** The tipocta master. */
	TIPOCTA_MASTER("06", "MasterCard", "MASTER"),
	/** The tipocta visa. */ 
	TIPOCTA_VISA("07", "VISA", "VISA"),
	/** The tipocta amex. */
	TIPOCTA_AMEX("42", "American Express", "AMEX");
	
	/** The Constant TIPOCTA_VISARECARGABLE. */
    private static final String TIPOCTA_VISARECARGABLE = "77";

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
	TipoCuentaTarjeta(String codigo, String descripcion, String abreviatura) {
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
	public static TipoCuentaTarjeta fromCodigo(String codigo) {
		TipoCuentaTarjeta[] values = TipoCuentaTarjeta.values();

		TipoCuentaTarjeta response = null;

		for (TipoCuentaTarjeta tipoCuenta : values) {
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
	public static TipoCuentaTarjeta getTipoCuentaTarjetaFromTipoCuenta(TipoCuenta tipoCuenta) {
		switch (tipoCuenta.getCodigo()) {
		case 7:
			return TipoCuentaTarjeta.TIPOCTA_VISA;
		case 42:
			return TipoCuentaTarjeta.TIPOCTA_AMEX;
		default:
			return TipoCuentaTarjeta.TIPOCTA_MASTER;
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

	public static String getTipoctaVisarecargable() {
		return TIPOCTA_VISARECARGABLE;
	}

	
	
	
}
