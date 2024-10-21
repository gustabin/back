package ar.com.santanderrio.obp.base.comun;

// TODO: Auto-generated Javadoc
/**
 * The Enum OperacionesRSAEnum.
 */
public enum OperacionesRSAEnum {

    /** The activo. */
    ACTIVO("ACTIVO"),

    /** The log in. */
    LOG_IN("LOG_IN"),

    /** The transferencia. */
    TRANSFERENCIA("TRANSFERENCIA"),

    /** The notificar. */
    NOTIFICAR("NOTIFICAR"),

    /** The nuevo pago. */
    NUEVO_PAGO("NUEVO_PAGO"),

    /** The nuevo pago automatico. */
    NUEVO_PAGO_AUTOMATICO("NUEVO_PAGO_AUTOMATICO"),

    /** The agenda destinatario. */
    AGENDA_DESTINATARIO("AGENDA_DESTINATARIO"),

    /** The agendar transferencia. */
    AGENDAR_TRANSFERENCIA("AGENDAR_TRANSFERENCIA"),

    /** The ALTA_TAG_MONEDERO. */
    ALTA_TAG_MONEDERO("ALTA_TAG_MONEDERO"),

    /** The agenda destinatario. */
    RESCATE_FONDO("RESCATE_FONDO"),

    /** Nueva recarga de tarjeta. */
    RECARGA_TARJETA("RECARGA_TARJETA"),

    /** The billetera. */
    BILLETERA("BILLETERA"),

    /** The cambiousuario. */
    CAMBIOUSUARIO("CAMBIOUSUARIO"),

    /** The cambioclaveyusuario. */
    CAMBIOCLAVEYUSUARIO("CAMBIOCLAVEYUSUARIO"),

    /** The MODIFICACION_LIMITE_DEBITO. */
    MODIFICACION_LIMITE_DEBITO("MODIFICACION_LIMITE_DEBITO"),

    /** The ALTAALIAS. */
    ALTAALIAS("ALTAALIAS"),

    /** The REASIGNAALIAS. */
    REASIGNAALIAS("REASIGNAALIAS"),

    /** The BAJAALIAS. */
    BAJAALIAS("BAJAALIAS"),

    /** The MODIFICAALIAS. */
    MODIFICAALIAS("MODIFICAALIAS"),

    /** The MODIFICAALIAS. */
    GESTION_ALIAS("GESTION_ALIAS"),

    /** The PAGODESUELDOS. */
    PAGODESUELDOS("PAGODESUELDOS"),

    /** The CAMBIODOMICILIO. */
    CAMBIODOMICILIO("CAMBIODOMICILIO"),

    /** The PREGUNTASSEGURIDAD. */
    PREGUNTASSEGURIDAD("PREGUNTASSEGURIDAD"),

    /** The solicitud tarj recargable. */
    SOLICITUD_TARJ_RECARGABLE("SOLICITUD_TARJ_RECARGABLE"),

    /** The solicitud tarj credito adicional. */
    SOLICITUD_TARJ_CREDITO_ADICIONAL("SOLICITUD_TARJ_CREDITO_ADICIONAL"),

    /** EXTRACCION Y COMPRAS EN EL EXTERIOR. */
    EXTCOMPRASEXTERIOR("EXTCOMPRASEXTERIOR"),

    /** The aumento limite transferencia. */
    AUMENTO_LIMITE_TRANSFERENCIA("AUMENTO_LIMITE_TRANSFERENCIA"),

    /** The reimpresion tarjeta. */
    REIMPRESION_TARJETA("REIMPRESION_TARJETA"),

    /** Salto prisma DEBIN. */
    DEBIN("DEBIN"),

    /** PAGO_DEBIN. */
    PAGO_DEBIN("PAGO_DEBIN"),

    /** SOLICITAR DEBIN. */
    SOLICITUD_DEBIN("SOLICITUD_DEBIN"),

    /** ADHESION_DEBIN. */
    ADHESION_DEBIN("ADHESION_DEBIN"),

    /** The reimpresion tarjeta. */
    TENENCIAS("TENENCIAS"),

    /** The transferencia comex. */
    TRANSFERENCIA_COMEX("TRANSFERENCIA_COMEX"),

    /** The pago compras. */
    PAGO_COMPRAS("PAGO_COMPRAS"),

    /** The echeq alta. */
    ECHEQ_ALTA("ECHEQ_ALTA"),
    
    /** The echeq endosar. */
    ECHEQ_ENDOSAR("ECHEQ_ENDOSAR"),

    /** The blanqueo pines. */
    BLANQUEO_PINES("BLANQUEO_PINES"),

    /** The cambioemail. */
    CAMBIOEMAIL("CAMBIOEMAIL"),

    /** The cambiocelular. */
    CAMBIOCELULAR("CAMBIOCELULAR"),
    
    /** The extraccion sin tarjeta. */
    EXTRACCION_SIN_TARJETA("EXTRACCION_SIN_TARJETA"),
	
	/** Emitir CED */
	ECHEQ_EMITIR_CED("ECHEQ_EMITIR_CED"),

	/** Prestamos */
	PRESTAMOS("PRESTAMOS"),
	
	/** Venta USD*/
	VENTA_USD("VENTA_USD"),

    DEBIN_RECURRENTE("DEBIN_RECURRENTE");

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new operaciones RSA enum.
     *
     * @param descripcion
     *            the descripcion
     */
    OperacionesRSAEnum(String descripcion) {
        this.descripcion = descripcion;
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
     * Gets the by descripcion.
     *
     * @param descripcion
     *            the descripcion
     * @return the by descripcion
     */
    public static OperacionesRSAEnum getByDescripcion(String descripcion) {
        for (OperacionesRSAEnum rsa : OperacionesRSAEnum.values()) {
            if (rsa.getDescripcion().equalsIgnoreCase(descripcion)) {
                return rsa;
            }
        }
        return null;
    }
}
