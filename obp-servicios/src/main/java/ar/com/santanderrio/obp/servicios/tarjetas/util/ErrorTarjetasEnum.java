/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Enum ErrorTarjetasEnum.
 *
 * @author federico.n.flores
 */
public enum ErrorTarjetasEnum {

	/** The sin ultimos consumos. */
	SIN_CONSUMOS(1197, TagItemMensajeTarjetaEnum.TAG_SIN_CONSUMOS, TipoError.ERROR_SIN_CONSUMOS, CodigoMensajeConstantes.CODIGO_SIN_CONSUMOS),

	/** The error servicio consumos pendientes. */
	ERROR_SERVICIO_CONSUMOS_PENDIENTES(1203, TagItemMensajeTarjetaEnum.CONSUMOS_PENDIENTES, TipoError.ERROR_CONSUMOS_PENDIENTES, CodigoMensajeConstantes.CODIGO_ERROR_WS_CONSUMOS_PENDIENTES),

	/** The error servicio ultimos consumos. */
	ERROR_SERVICIO_ULTIMOS_CONSUMOS(1293, TagItemMensajeTarjetaEnum.ULTIMOS_CONSUMOS, TipoError.ERROR_CARGA_ULTIMOS_CONSUMOS, CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_ULTIMOS_CONSUMOS),

	/** The error generico ultimos consumos. */
    ERROR_GENERICO_ULTIMOS_CONSUMOS(1198, TagItemMensajeTarjetaEnum.ERROR_GENERICO_ULTIMOS_CONSUMOS, TipoError.ERROR_TOTAL_ULTIMOS_CONSUMOS, CodigoMensajeConstantes.CODIGO_ERROR_WS_ULTIMOS_CONSUMOS);

	/** The codigo. */
	private Integer codigo;

	/** The tag. */
	private TagItemMensajeTarjetaEnum tag;

	/** The tipo error. */
	private TipoError tipoError;

	/** The codigo mensaje. */
	private String codigoMensaje;

	/**
	 * Instantiates a new error tarjetas enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 * @return the p
	 */
	ErrorTarjetasEnum(Integer codigo, TagItemMensajeTarjetaEnum tag, TipoError tipoError, String codigoMensaje) {
		this.codigo = codigo;
		this.tag = tag;
		this.tipoError = tipoError;
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public TagItemMensajeTarjetaEnum getTag() {
		return tag;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipoError
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Gets the codigo mensaje.
	 *
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

}
