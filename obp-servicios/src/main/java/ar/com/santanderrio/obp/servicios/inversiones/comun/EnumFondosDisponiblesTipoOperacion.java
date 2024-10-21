/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumFondosDisponiblesTipoOperacion.
 */
public enum EnumFondosDisponiblesTipoOperacion {

	/** The su. */
	SU("SU", "Suscripción"),
	/** The re. */
	RE("RE", "Rescate"),
	/** The tr. */
	TR("TR", "Transferencia");

	/** The codigo. */
	private final String codigo;

	/** The descripcion. */
	private final String descripcion;

	/**
	 * Instantiates a new enum fondos disponibles tipo operacion.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	EnumFondosDisponiblesTipoOperacion(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
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

	/** The Constant map. */
	private static final Map<String, EnumFondosDisponiblesTipoOperacion> map;
	static {
		map = new HashMap<String, EnumFondosDisponiblesTipoOperacion>();
		for (EnumFondosDisponiblesTipoOperacion v : EnumFondosDisponiblesTipoOperacion.values()) {
			map.put(v.getCodigo(), v);
		}
	}

	/**
	 * Buscar por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the enum fondos disponibles tipo operacion
	 */
	public static EnumFondosDisponiblesTipoOperacion buscarPorCodigo(String codigo) {
		return map.get(codigo);
	}

}
