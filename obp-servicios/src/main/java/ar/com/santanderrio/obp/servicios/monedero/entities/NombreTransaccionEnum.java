/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * The Enum NombreTransaccionEnum.
 */
public enum NombreTransaccionEnum {

	/** The anulacion consumo. */
	ANULACION_CONSUMO(3290, "Anulación de consumo"),

	/** The consumo. */
	CONSUMO(3291, "Consumo"),

	/** The recarga efectivo. */
	RECARGA_EFECTIVO(3292, "Recarga en efectivo"),

	/** The anulacion recarga efectivo. */
	ANULACION_RECARGA_EFECTIVO(3294, "Anulación de recarga en efectivo"),

	/** The recarga automatica. */
	RECARGA_AUTOMATICA(3296, "Recarga automática"),

	/** The acreditaciones. */
	ACREDITACIONES(3297, "Acreditaciones"),

	/** The reverso recarga automatica. */
	REVERSO_RECARGA_AUTOMATICA(3530, "Reverso de recarga automática"),

	/** The reverso recarga efectivo. */
	REVERSO_RECARGA_EFECTIVO(3531, "Reverso de recarga en efectivo Tag"),

	/** The reverso anulacion recarga efectivo. */
	REVERSO_ANULACION_RECARGA_EFECTIVO(3532, "Reverso de Anulación de recarga en efectivo tag"),

	/** The reverso anulacion recarga efectivo cta virtual. */
	REVERSO_ANULACION_RECARGA_EFECTIVO_CTA_VIRTUAL(3534, "Reverso de anulación de recarga en efectivo Cuenta virtual"),

	/** The cargos. */
	CARGOS(6838, "Cargos"),

	/** The bonificacion cargos. */
	BONIFICACION_CARGOS(6839, "Bonificación Cargos"),

	/** The reintegro promociones. */
	REINTEGRO_PROMOCIONES(6840, "Reintegro Promociones"),

	/** The recarga electronica. */
	RECARGA_ELECTRONICA(6841, "Recarga Electrónica"),

	/** The reverso consumo off line. */
	REVERSO_CONSUMO_OFF_LINE(6842, "Reverso de Consumo Off Line"),

	/** The reverso anulacion consumo off line. */
	REVERSO_ANULACION_CONSUMO_OFF_LINE(6843, "Reverso de Anulación de Consumo Off Line");

	/** The Constant lookup. */
	private static final Map<Integer, NombreTransaccionEnum> lookup = new HashMap<Integer, NombreTransaccionEnum>();

	static {
		for (NombreTransaccionEnum w : EnumSet.allOf(NombreTransaccionEnum.class)) {
			lookup.put(w.getCode(), w);
		}
	}

	/** The code. */
	private int code;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new nombre transaccion enum.
	 *
	 * @param code
	 *            the code
	 * @param descripcion
	 *            the descripcion
	 */
	NombreTransaccionEnum(int code, String descripcion) {
		this.code = code;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
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
	 * Gets the.
	 *
	 * @param code
	 *            the code
	 * @return the nombre transaccion enum
	 */
	public static NombreTransaccionEnum get(int code) {
		return lookup.get(code);
	}
}