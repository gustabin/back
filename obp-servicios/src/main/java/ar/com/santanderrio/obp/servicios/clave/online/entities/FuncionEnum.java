/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

/**
 * The Enum FuncionEnum.
 */
public enum FuncionEnum {

	/** The enviar sms. */
	ENVIAR_SMS("10"),
	/** The celu. */
	CELU("11"),
	/** The bane. */
	BANE("20"),
	/** The visa amex. */
	VISA_AMEX("30"),
	/** The whatsapp. */
	WHATSAPP("70");
	/** The codigo. */
	private String codigo;

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Instantiates a new funcion enum.
	 *
	 * @param codigo
	 *            the codigo
	 */
	FuncionEnum(String codigo) {
		this.codigo = codigo;
	}

}
