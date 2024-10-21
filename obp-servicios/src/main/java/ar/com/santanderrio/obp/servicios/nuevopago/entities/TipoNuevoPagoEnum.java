/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

/**
 * The Enum TipoNuevoPagoEnum.
 */
public enum TipoNuevoPagoEnum {
    
    /** The electronico. */
    ELECTRONICO("0", "ELECTRONICO"),
    
    /** The domestico. */
    DOMESTICO("1", "DOMESTICO"),
    
    /** The afip cat1. */
    AFIP_CAT1("2", "AFIP_CAT1"),
    
    /** The afip cat2. */
    AFIP_CAT2("3", "AFIP_CAT2"),
    
    /** The monotributo. */
    MONOTRIBUTO("4", "MONOTRIBUTO"),
    
    /** The afip cat5. */
    AFIP_CAT5("5", "AFIP_CAT5"),
    
    /** The vep. */
    VEP("6", "VEP"),
    
    /** The custom importe fijo. */
    CUSTOM_IMPORTE_FIJO("7", "CUSTOM_IMPORTE_FIJO"),
    
    /** The custom importe variable. */
    CUSTOM_IMPORTE_VARIABLE("8", "CUSTOM_IMPORTE_VARIABLE"),
    
    /** The celular recarga. */
    CELULAR_RECARGA("9", "RECARGA DE CELULARES"),
    
    /** The sube recarga. */
    SUBE_RECARGA("10", "SUBE"),
      
    /** The pago compras sin deuda. */
    PAGO_COMPRAS_SIN_DEUDA("11", "PAGO_COMPRAS_SIN_DEUDA"),
    
    /** The pago compras importe fijo. */
    PAGO_COMPRAS_IMPORTE_FIJO("12", "PAGO_COMPRAS_IMPORTE_FIJO"),
    
    /** The pago compras importe variable. */
    PAGO_COMPRAS_IMPORTE_VARIABLE("13", "PAGO_COMPRAS_IMPORTE_VARIABLE"),
    
    MOVI_TRANSP_RECARGA("14", "TSCR");
    

    /** The id. */
    private String id;

    /** The descripcion. */
    private String descripcion;
    
    
    /**
     * Instantiates a new tipo nuevo pago enum.
     *
     * @param id
     *            the id
     * @param descripcion
     *            the descripcion
     */
    TipoNuevoPagoEnum(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Obtener por id.
     *
     * @param id
     *            the id
     * @return the tipo nuevo pago enum
     */
    public static TipoNuevoPagoEnum obtener(String id) {
        for (TipoNuevoPagoEnum tipoNuevoPago : TipoNuevoPagoEnum.values()) {
            if (tipoNuevoPago.id.equals(id)) {
                return tipoNuevoPago;
            }
        }
        return null;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
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