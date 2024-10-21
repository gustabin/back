/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum MovimientosEnum.
 */
public enum MovimientosEnum {

	/** The Constant INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO. */
	MOVIMIENTO_CAJA_DE_AHORRO(0, "01"),

	/** The Constant INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE. */
	MOVIMIENTO_CUENTA_CORRIENTE(1, "00"),

	/** The Constant INDICADOR_MOVIMIENTO_CUENTA_UNICA. */
	MOVIMIENTO_CUENTA_UNICA(2, "02");

	/** The codigo. */
	private int codigo;

	/** The indicador. */
	private String indicador;

	/**
	 * Instantiates a new movimientos enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param indicador
	 *            the indicador
	 */
	private MovimientosEnum(int codigo, String indicador) {
		this.codigo = codigo;
		this.indicador = indicador;
	}

	/**
	 * From codigo string.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the divisa enum
	 */
	public static MovimientosEnum fromCodigoString(String codigo) {
		MovimientosEnum[] values = MovimientosEnum.values();

		MovimientosEnum response = null;

		for (MovimientosEnum movimiento : values) {
			if (Integer.valueOf(movimiento.getCodigo()).equals(Integer.valueOf(codigo))) {
				response = movimiento;
			}
		}
		return response;
	}

	/**
	 * From simbolo string.
	 *
	 * @param indicador
	 *            the indicador
	 * @return the divisa enum
	 */
	public static MovimientosEnum fromIndicadorString(String indicador) {
		MovimientosEnum[] values = MovimientosEnum.values();

		MovimientosEnum response = null;

		for (MovimientosEnum movimientos : values) {
			if (movimientos.getIndicador().equals(indicador)) {
				response = movimientos;
			}
		}
		return response;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Gets the indicador.
	 *
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * Sets the indicador.
	 *
	 * @param indicador
	 *            the new indicador
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
