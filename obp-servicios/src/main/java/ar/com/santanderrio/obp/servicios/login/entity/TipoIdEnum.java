/**
 * 
 */
package ar.com.santanderrio.obp.servicios.login.entity;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoIdEnum;

/**
 * Tipo de Cliente tomado del servicio de identificación del cliente.
 * 
 */

public enum TipoIdEnum {

	/** The dni. */
	DNI("N", MyaTipoIdEnum.DNI),
	/** The libreta civica. */
	LIBRETA_CIVICA("C", MyaTipoIdEnum.LIBRETA_CIVICA),

	/** The libreta enrolamiento. */
	LIBRETA_ENROLAMIENTO("E", MyaTipoIdEnum.LIBRETA_ENROLAMIENTO),

	/** The cedula identidad. */
	CEDULA_IDENTIDAD("I", MyaTipoIdEnum.CEDULA_IDENTIDAD),

	/** The cedula militar. */
	CEDULA_MILITAR("M", MyaTipoIdEnum.CEDULA_MILITAR),

	/** The pasaporte. */
	PASAPORTE("P", MyaTipoIdEnum.PASAPORTE),
	/** The cuit. */
	CUIT("T", MyaTipoIdEnum.CUIT),

	/** The cuil. */
	CUIL("L", MyaTipoIdEnum.CUIL),
	/** The cdi. */
	CDI("D", MyaTipoIdEnum.CDI),

	/** The dni extranjero. */
	DNI_EXTRANJERO("X", MyaTipoIdEnum.DNI_EXTRANJERO),

	/** The certificado int. */
	CERTIFICADO_INT("F", MyaTipoIdEnum.CERTIFICADO_INT);

	/** The mya tipo id enum. */
	private final MyaTipoIdEnum myaTipoIdEnum;

	/** The codigo. */
	private final String codigo;

	/**
	 * Instantiates a new tipo id enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param myaTipoIdEnum
	 *            the mya tipo id enum
	 */
	private TipoIdEnum(String codigo, MyaTipoIdEnum myaTipoIdEnum) {

		this.codigo = codigo;
		this.myaTipoIdEnum = myaTipoIdEnum;
	}

	/**
	 * Gets the mya tipo id enum.
	 *
	 * @return myaTipoIdEnum
	 */
	public MyaTipoIdEnum getMyaTipoIdEnum() {
		return myaTipoIdEnum;
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
	 * Obtener tipo id por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the mya tipo id enum
	 */
	public static MyaTipoIdEnum obtenerTipoIdPorCodigo(String codigo) {

		TipoIdEnum[] values = TipoIdEnum.values();
		MyaTipoIdEnum response = null;

		for (TipoIdEnum tipo : values) {
			if (tipo.getCodigo().equals(codigo)) {
				response = tipo.getMyaTipoIdEnum();
			}
		}
		return response;
	}

}
