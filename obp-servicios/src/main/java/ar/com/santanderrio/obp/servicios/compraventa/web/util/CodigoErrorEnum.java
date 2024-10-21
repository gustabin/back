/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.util;

/**
 * The Enum CodigoErrorEnum.
 *
 * @author sabrina.cis
 */
public enum CodigoErrorEnum {

	/** The cod error sucursal fuera horario. */
	COD_ERROR_SUCURSAL_FUERA_HORARIO(10000077),

	/** The cod error cambio cotizacion. */
	COD_ERROR_CAMBIO_COTIZACION(10000006),

	/** The cod error excede limite 1. */
	COD_ERROR_EXCEDE_LIMITE_1(10000004),

	/** The cod error excede limite 2. */
	COD_ERROR_EXCEDE_LIMITE_2(10000002),

	/** The cod error cuenta inhabilitada 1. */
	COD_ERROR_CUENTA_INHABILITADA_1(10002001),

	/** The cod error cuenta inhabilitada 2. */
	COD_ERROR_CUENTA_INHABILITADA_2(10000008),

	/** The cod error cuenta inhabilitada 3. */
	COD_ERROR_CUENTA_INHABILITADA_3(10002065),

	/** The cod error saldo insuficiente 1. */
	COD_ERROR_SALDO_INSUFICIENTE_1(10002122),

	/** The cod error saldo insuficiente 2. */
	COD_ERROR_SALDO_INSUFICIENTE_2(10000515),
	
	/** The codigo error no informado uva calendario. */
	CODIGO_ERROR_NO_INFORMADO_UVA_CALENDARIO(10001918);

	/**
	 * Instantiates a new error compra venta enum.
	 *
	 * @param codigo
	 *            the codigo
	 */
	CodigoErrorEnum(Integer codigo) {
		this.codigo = codigo;
	}

	/** The codigo. */
	private Integer codigo;

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

}
