package ar.com.santanderrio.obp.servicios.echeq.enums;

public enum OperacionEcheqEnum {

    ACEPTAR("ACEPTAR"),
    REPUDIAR("REPUDIAR"),
    VER_DETALLE("VER_DETALLE"),
    CUSTODIAR("CUSTODIAR"),
    RESCATAR("RESCATAR"),
    DEPOSITAR("DEPOSITAR"),
    ENDOSAR("ENDOSAR"),
    ANULAR_ENDOSO("ANULAR_ENDOSO"),
    EMITIR_CERTIFICADO("EMITIR_CERTIFICADO"),
    ANULAR("ANULAR"),
    ALTA("ALTA"),
    ACEPTAR_ACUERDO_DEVOLUCION("ACEPTAR_ACUERDO_DEVOLUCION"),
    ANULAR_ACUERDO_DEVOLUCION("ANULAR_ACUERDO_DEVOLUCION"),
    REPUDIAR_ACUERDO_DEVOLUCION("REPUDIAR_ACUERDO_DEVOLUCION"),
    SOLICITAR_ACUERDO_DEVOLUCION("SOLICITAR_ACUERDO_DEVOLUCION"),
    ACEPTAR_PEDIDO_DEVOLUCION("ACEPTAR_PEDIDO_DEVOLUCION"),
    ANULAR_PEDIDO_DEVOLUCION("ANULAR_PEDIDO_DEVOLUCION"),
    REPUDIAR_PEDIDO_DEVOLUCION("REPUDIAR_PEDIDO_DEVOLUCION"),
    SOLICITAR_PEDIDO_DEVOLUCION("SOLICITAR_PEDIDO_DEVOLUCION"),
    ADMITIR_CED("ADMITIR_CED"),
    EMITIR_CED("EMITIR_CED"),
    REPUDIAR_CED("REPUDIAR_CED"),
	ANULAR_CED("ANULAR_CED"),
    SOLICITAR_AVAL("SOLICITAR_AVAL"),
    ANULAR_SOLICITUD_AVAL("ANULAR_SOLICITUD_AVAL"),
    ACEPTAR_AVAL("ACEPTAR_AVAL"),
    REPUDIAR_AVAL("REPUDIAR_AVAL");

    private String accion;

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    OperacionEcheqEnum(String accion) {
        this.accion = accion;
    }

}
