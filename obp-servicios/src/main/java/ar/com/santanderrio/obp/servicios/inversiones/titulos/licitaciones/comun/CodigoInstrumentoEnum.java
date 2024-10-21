/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.comun;

/**
 * The Enum CodigoInstrumentoEnum.
 */
public enum CodigoInstrumentoEnum {
	
	/** The pub. */
	PUB("PUB", "Títulos Públicos"),
    
    /** The pri. */
    PRI("PRI", "Títulos Privados"),
    
    /** The cdr. */
    CDR("CDR", "Cedears"),
    
    /** The acc. */
    ACC("ACC", "Acciones");
    
	
	/** The codigo. */
	private final String codigo;

	/** The codigo. */
	private final String descripcion;
	
	
	/**
	 * Instantiates a new codigo instrumento enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	CodigoInstrumentoEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
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

	/**
	 * From codigo producto dolares.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo fondo enum
	 */
	public static String obtenerDescripcion(String codigo) {
	    if(codigo == null){
	        return null;
	    }
		CodigoInstrumentoEnum[] values = CodigoInstrumentoEnum.values();
		String response = null;
		for (CodigoInstrumentoEnum codigoInstrumento : values) {
			if (codigoInstrumento.getCodigo().equalsIgnoreCase(codigo.trim())) {
				response = codigoInstrumento.getDescripcion();
			}
		}
		return response;
	}
	
}
