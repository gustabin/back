/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.enums;

/**
 * The Enum CanalIngresoEnum.
 */
public enum CanalIngresoEnum {
    
    /** The online banking. */
    ONLINE_BANKING("04", "Online Banking"),
    
    /** The sistemas venta directa. */
    SISTEMAS_VENTA_DIRECTA("12", "Contact Center"),
    
    /** The smcfront. */
    SMCFRONT("99", "SMCFront"),
    
    /** The online banking bp. */
    ONLINE_BANKING_BP("79", "Online Banking BP"),
    
    /** The banca privada. */
    BANCA_PRIVADA("18", "Banca Privada"),
    
    /** The smc. */
    SMC("0", "SMC"),
	
	CANAL_VTA_MONOPRODUCTO("06", "Canal de Venta de Préstamo Monoproducto");
    
    
    /** The codigo. */
    private final String codigo;

    /** The codigo. */
    private final String descripcion;
    
    
    /**
	 * Instantiates a new canal ingreso enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
    CanalIngresoEnum(String codigo, String descripcion) {
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
     * From codigo .
     *
     * @param codigo
     *            the codigo
     * @return the tipo fondo enum
     */
    public static String obtenerDescripcion(String codigo) {
        if(codigo == null){
            return null;
        }
        CanalIngresoEnum[] values = CanalIngresoEnum.values();
        String response = null;
        for (CanalIngresoEnum canalIngreso : values) {
            if (canalIngreso.getCodigo().equalsIgnoreCase(codigo.trim())) {
                response = canalIngreso.getDescripcion();
            }
        }
        return response;
    }

}
