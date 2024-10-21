/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Enum FormaDePagoTarjetaEnum.
 */
public enum FormaDePagoTarjetaEnum {

	/** The sin agenda programada. */
	SIN_AGENDA_PROGRAMADA("00", "Sin agenda programada"),
	/** The por caja. */
	POR_CAJA("01", "Por caja"),
	/** The pago minimo con debito en cc. */
	PAGO_MINIMO_CON_DEBITO_EN_CC("02", "Pago mínimo con débito en cta. cte"),
	/** The pago total con debito en cc. */
	PAGO_TOTAL_CON_DEBITO_EN_CC("03", "Pago total con débito en cta. cte"),
	/** The pago minimo con debito en ca. */
	PAGO_MINIMO_CON_DEBITO_EN_CA("04", "Pago mínimo con débito en caja de ahorro"),
	/** The pago total con debito en ca. */
	PAGO_TOTAL_CON_DEBITO_EN_CA("05", "Pago total con débito en caja de ahorro"),

	/** The recarga semanal lunes. */
	// TARJETAS PREPAGAS AGENDADAS
	RECARGA_SEMANAL_LUNES("71", "Recarga semanal - Lunes", "Semanalmente"),
	/** The recarga semanal martes. */
	RECARGA_SEMANAL_MARTES("72", "Recarga semanal - Martes", "Semanalmente"),
	/** The recarga semanal miercoles. */
	RECARGA_SEMANAL_MIERCOLES("73", "Recarga semanal - Miercoles", "Semanalmente"),
	/** The recarga semanal jueves. */
	RECARGA_SEMANAL_JUEVES("74", "Recarga semanal - Jueves", "Semanalmente"),
	/** The recarga semanal viernes. */
	RECARGA_SEMANAL_VIERNES("75", "Recarga semanal - Viernes", "Semanalmente"),
	/** The recarga quincenal lunes. */
	RECARGA_QUINCENAL_LUNES("81", "Recarga quincenal - Lunes", "Quincenalmente"),
	/** The recarga quincenal martes. */
	RECARGA_QUINCENAL_MARTES("82", "Recarga quincenal - Martes", "Quincenalmente"),
	/** The recarga quincenal miercoles. */
	RECARGA_QUINCENAL_MIERCOLES("83", "Recarga quincenal - Miercoles", "Quincenalmente"),
	/** The recarga quincenal jueves. */
	RECARGA_QUINCENAL_JUEVES("84", "Recarga quincenal - Jueves", "Quincenalmente"),
	/** The recarga quincenal viernes. */
	RECARGA_QUINCENAL_VIERNES("85", "Recarga quincenal - Viernes", "Quincenalmente"),
	/** The recarga mensual. */
	RECARGA_MENSUAL("90", "Recarga mensual", "Mensualmente");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/** The descripcion. */
	private String descripcionCorta;

	/**
	 * Instantiates a new forma de pago tarjeta enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	FormaDePagoTarjetaEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcion;
	}

	/**
	 * Instantiates a new forma de pago tarjeta enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 * @param descripcionCorta
	 *            the descripcion corta
	 */
	FormaDePagoTarjetaEnum(String codigo, String descripcion, String descripcionCorta) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
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
	 * Por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the forma de pago tarjeta enum
	 */
	public static FormaDePagoTarjetaEnum porCodigo(String codigo) {
		FormaDePagoTarjetaEnum result = null;
		if (codigo != null && !codigo.trim().isEmpty()) {
			for (FormaDePagoTarjetaEnum item : FormaDePagoTarjetaEnum.values()) {
				if (codigo.equals(item.getCodigo())) {
					result = item;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Gets the descripcion corta.
	 *
	 * @return the descripcion corta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
}
