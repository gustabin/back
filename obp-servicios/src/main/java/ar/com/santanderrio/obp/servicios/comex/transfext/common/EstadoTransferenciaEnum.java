/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.common;

/**
 * The Enum EstadoTransferenciaEnum.
 */
public enum EstadoTransferenciaEnum {
	
	/** The pendiente tramite. */
	PENDIENTE_TRAMITE("G", "Pendiente"),	

	/** The estado transferencia liquidada. */
	LIQUIDADA("J", "Liquidada"),
	
	/** The estado transferencia rechazada. */
	RECHAZADA("K", "Rechazada"),
	
	/** The estado transferencia guardada. */
	GUARDADA("A", "Borrador"),
	
	/** The pendiente procotizacion. */
	PENDIENTE_PROCOTIZACION("H","Pendiente"),

	EN_ANALISIS_TECNICO_NORMATIVO("M","En analisis tecnico normativo"),
	
	CUSTOMER_COMEX("O","Custormer comex"),
	
	PENDIENTE_AUTORIZACION_BCRA("P","Pendiente autorizacion BCRA"),
	
	A_PROCESAR_CON_FECHA_PROYECTADA("Q","A procesar con fecha proyectada"),
	
	INGRESADO_COMO_TRF("N","Ingresado como TRF");
	
    /** The Constant ESTADO_ANULADA. */
    public static final String ESTADO_ANULADA = "L";
    
    /** The Constant ESTADO_RECHAZADA. */
    public static final String ESTADO_RECHAZADA = "K";
    
    /** The Constant ESTADO_ENTRAMITE. */
    public static final String ESTADO_ENTRAMITE = "G";
    
    /** The Constant ESTADO_LIQUIDADA. */
    public static final String ESTADO_LIQUIDADA = "J";
    
    /** The Constant ESTADO_BORRADOR. */
    public static final String ESTADO_BORRADOR = "A";
    
    /** The Constant ESTADO_PROCOTIZACION. */
    public static final String ESTADO_PROCOTIZACION = "H";
    
    public static final String ESTADO_EN_ANALISIS_TECNICO_NORMATIVO = "M";
    
    public static final String ESTADO_CUSTOMER_COMEX = "O";
    
    public static final String ESTADO_PENDIENTE_AUTORIZACION_BCRA = "P";
    
    public static final String ESTADO_A_PROCESAR_CON_FECHA_PROYECTADA = "Q";
    
    public static final String ESTADO_INGRESADO_COMO_TRF = "N";
    
    
    

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new estado transferencia Enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	EstadoTransferenciaEnum(String codigo, String descripcion) {
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
}
