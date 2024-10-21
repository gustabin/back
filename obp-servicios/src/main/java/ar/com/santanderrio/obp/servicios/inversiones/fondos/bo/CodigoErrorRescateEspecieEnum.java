package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;

/**
 * Enum para identificar los codigos de mensajes de error para rescates por especie en relacion al codigo devuelto por el store
 * @author B042583
 *
 */
public enum CodigoErrorRescateEspecieEnum {

	FUERA_HORARIO("2", CodigoMensajeConstantes.ERROR_SERVICIO_FONDOS_DESHABILITADO),

	SALDO_INSUFICIENTE("24", CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE);

	/** The codigo. */
	private final String codigo;
	
	private final String mensaje;

	/**
	 * Instantiates a new tipo banca enum.
	 *
	 * @param codigo
	 *            the codigo
	 */
	CodigoErrorRescateEspecieEnum(String codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	/**
	 * BR para banca personal, BP para banca privada, BR para ambas bancas.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}
	/**
	 * From codigo string.
	 *
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the tipo banca enum
	 */
	public static String fromCodigoString(String codigo) {
		CodigoErrorRescateEspecieEnum[] valores = CodigoErrorRescateEspecieEnum.values();

		for (CodigoErrorRescateEspecieEnum valor : valores) {
			if (valor.getCodigo().equals(codigo)) {
				return valor.getMensaje();
			}
		}
		return CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO;
	}
}
