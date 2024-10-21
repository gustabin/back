/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Enum ErrorAltaDestinatariosEnum.
 */
public enum ErrorAltaDestinatariosEnum {

	/** The sin medio de pago. */
	SIN_MEDIO_DE_PAGO("sinMedioDePago", TipoError.ERROR_SERVICIO_MANUAL, "1461"),

	/** The cbu invalido. */
	CBU_INVALIDO("cbuInvalido", TipoError.CBU_INVALIDO, "1461"),

	/** The cbu cuenta propia. */
	CBU_CUENTA_PROPIA("cbuCuentaPropia", TipoError.CBU_CUENTA_PROPIA, "1453"),

	/** The error servicio cbu. */
	ERROR_SERVICIO_CBU("errorServicio", TipoError.ERROR_SERVICIO, "1471"),

	/** The error servicio alta Envio efectivo generico. */
	ERROR_SERVICIO_CBU_ENVIO_EFECTIVO("errorServicio", TipoError.ERROR_SERVICIO, "1435"),

	/** The error servicio alta Envio efectivo generico. */
	ERROR_YA_AGENDADO_ENVIO_EFECTIVO("yaAgendado", TipoError.ERROR_YA_AGENDADO, "1455"),

	/** The error carga manual. */
	ERROR_CARGA_MANUAL("errorCargaManual", TipoError.ERROR_SERVICIO_MANUAL, "1461");

	/** The tag. */
	private String tag;

	/** The tipo error. */
	private TipoError tipoError;

	/** The codigo mensaje. */
	private String codigoMensaje;

	/**
	 * Instantiates a new error compra venta enum.
	 *
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 */
	ErrorAltaDestinatariosEnum(String tag, TipoError tipoError, String codigoMensaje) {
		this.tag = tag;
		this.tipoError = tipoError;
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Gets the codigo mensaje.
	 *
	 * @return the codigo mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}
}
