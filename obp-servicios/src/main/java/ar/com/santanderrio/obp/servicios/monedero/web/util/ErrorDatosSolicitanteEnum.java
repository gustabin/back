/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.util;

import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Enum ErrorCompraVentaEnum.
 *
 * @author sabrina.cis
 */
public enum ErrorDatosSolicitanteEnum {

	/** The operacion fuera horario. */
	OPERACION_FUERA_HORARIO(10000077, CompraVentaStringUtil.SUCURSAL_ORIGEN_FUERA_HORARIO, TipoError.ERROR_SUCURSAL_ORIGEN_FUERA_HORARIO, CodigoMensajeConstantes.CODIGO_ERROR_OPERACION_NO_DISPONIBLE_HORARIO_SUCURSAL),

	/** Fuera de horario warning para cuando tiene cuentas en otra sucursal. */
	OPERACION_FUERA_HORARIO_WARNING(10000077, CompraVentaStringUtil.SUCURSAL_ORIGEN_FUERA_HORARIO, TipoError.WARNING_SUCURSAL_ORIGEN_FUERA_HORARIO, CodigoMensajeConstantes.CODIGO_ERROR_OPERACION_NO_DISPONIBLE_HORARIO_SUCURSAL),
	/** The saldo insuficiente 2. */
	ERROR_DE_SERVICIO(10000515, CompraVentaStringUtil.ERROR_DE_SERVICIO, TipoError.ERROR_DE_SERVICIOS_COMPRA_VENTA, CodigoMensajeConstantes.CODIGO_ERROR_DE_SERVICIOS_COMPRA_VENTA),

	/** The no se puede realizar la operacion. */
	NO_SE_PUEDE_REALIZAR_LA_OPERACION(999, CompraVentaStringUtil.OPERACION_INHABILITADA, TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.CODIGO_ERROR_NO_SE_PUEDE_REALIZAR_LA_OPERACION),

	/** The operacion fuera horario. */
	SIN_ACCESO_A_LA_INFORMACION(1, CompraVentaStringUtil.SIN_INFORMACION, TipoError.ERROR_SIN_ACCESO_A_INFORMACION, CodigoMensajeConstantes.CODIGO_ERROR_NO_SE_PUEDE_ACCEDER_A_LA_INFORMACION),

	/** The cambio la cotizacion. */
	NO_SE_ENCONTRO_DOC_VALIDO(2, CompraVentaStringUtil.DOCUMENTO_INVALIDO, TipoError.DOCUMENTO_INVALIDO, CodigoMensajeConstantes.CODIGO_ERROR_DOCUMENTO_INVALIDO);

	/** The codigo. */
	private Integer codigo;

	/** The tag. */
	private String tag;

	/** The tipo error. */
	private TipoError tipoError;

	/** The codigo mensaje. */
	private String codigoMensaje;

	/**
	 * Instantiates a new error compra venta enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param codigoMensaje
	 *            the codigo mensaje
	 */
	ErrorDatosSolicitanteEnum(Integer codigo, String tag, TipoError tipoError, String codigoMensaje) {
		this.codigo = codigo;
		this.tag = tag;
		this.tipoError = tipoError;
		this.codigoMensaje = codigoMensaje;
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
	 * Sets the tag.
	 *
	 * @param tag
	 *            the new tag
	 */
	void setTag(String tag) {
		this.tag = tag;
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
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the new tipo error
	 */
	void setTipoError(TipoError tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Gets the codigo mensaje.
	 *
	 * @return the codigo mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * Sets the codigo mensaje.
	 *
	 * @param codigoMensaje
	 *            the new codigo mensaje
	 */
	void setCodigoMensaje(String codigoMensaje) {
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
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
