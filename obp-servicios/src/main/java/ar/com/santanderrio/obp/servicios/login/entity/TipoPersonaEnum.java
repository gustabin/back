/**
 * 
 */
package ar.com.santanderrio.obp.servicios.login.entity;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoPersonaEnum;

/**
 * Tipo de Cliente tomado del servicio de identificación del cliente.
 * 
 */
public enum TipoPersonaEnum {

	/** The fisica. */
	FISICA("F", MyaTipoPersonaEnum.FISICA),

	/** The juridica. */
	JURIDICA("J", MyaTipoPersonaEnum.JURIDICA),

	/** The individuo. */
	INDIVIDUO("I", MyaTipoPersonaEnum.INDIVIDUO),

	/** The empresa. */
	EMPRESA("E", MyaTipoPersonaEnum.EMPRESA);

	/** The mya tipo persona enum. */
	private final MyaTipoPersonaEnum myaTipoPersonaEnum;

	/** The codigo. */
	private final String codigo;

	/**
	 * Instantiates a new tipo persona enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param enum2
	 *            the enum 2
	 */
	private TipoPersonaEnum(String codigo, MyaTipoPersonaEnum enum2) {
		this.codigo = codigo;
		this.myaTipoPersonaEnum = enum2;
	}

	/**
	 * Gets the mya tipo persona enum.
	 *
	 * @return the myaTipoPersonaEnum
	 */
	public MyaTipoPersonaEnum getMyaTipoPersonaEnum() {
		return myaTipoPersonaEnum;
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
	 * Obtener tipo persona por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the mya tipo persona enum
	 */
	public static MyaTipoPersonaEnum obtenerTipoPersonaPorCodigo(String codigo) {

		TipoPersonaEnum[] values = TipoPersonaEnum.values();
		MyaTipoPersonaEnum response = null;

		for (TipoPersonaEnum tipo : values) {
			if (tipo.getCodigo().equals(codigo)) {
				response = tipo.getMyaTipoPersonaEnum();
			}
		}
		return response;
	}

}
