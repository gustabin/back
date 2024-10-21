/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

/**
 * Tipos de cuenta creditos.
 */
public enum TipoCuentaCreditoEnum {

	/** The tipo cuenta credito void. */
	TIPO_CUENTA_CREDITO_VOID("  ", "VACIO"),

	/** The tipo cuenta credito acte. */
	TIPO_CUENTA_CREDITO_ACTE("00", "ACTE"),

	/** The tipo cuenta credito acah. */
	TIPO_CUENTA_CREDITO_ACAH("01", "ACAH"),

	/** The tipo cuenta credito accd. */
	TIPO_CUENTA_CREDITO_ACCD("03", "ACCD"),

	/** The tipo cuenta credito acad. */
	TIPO_CUENTA_CREDITO_ACAD("04", "ACAD"),

	/** The tipo cuenta credito cru pesos. */
	TIPO_CUENTA_CREDITO_CRU_PESOS("09", "CRU $"),

	/** The tipo cuenta credito acte dolares. */
	TIPO_CUENTA_CREDITO_ACTE_DOLARES("10", "CRU U$S"),

	/** The tipo cuenta unica. */
	TIPO_CUENTA_UNICA("02", "CU");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/**
	 * Instantiates a new campo enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoCuentaCreditoEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
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
	 * Gets the tipo cuenta credito.
	 *
	 * @param campo
	 *            the campo
	 * @return the tipo cuenta credito
	 */
	public static TipoCuentaCreditoEnum getTipoCuentaCredito(String campo) {
		TipoCuentaCreditoEnum[] values = TipoCuentaCreditoEnum.values();
		for (TipoCuentaCreditoEnum tipoIdentificacion : values) {
			if (campo.trim().equalsIgnoreCase(tipoIdentificacion.getCampo())) {
				return tipoIdentificacion;
			}
		}
		return null;
	}
}
