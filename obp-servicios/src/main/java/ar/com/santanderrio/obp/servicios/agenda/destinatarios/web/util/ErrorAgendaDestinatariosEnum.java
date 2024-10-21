/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Enum ErrorAgendaDestinatariosEnum.
 */
public enum ErrorAgendaDestinatariosEnum {

    /** The timeout. */
    TIMEOUT("timeOut", TipoError.TIMEOUT, "1435"),

    /** The timeout. */
    TIMEOUTDESTINATARIO("timeOut", TipoError.TIMEOUT, "1523"),

    /** The error servicio edicion. */
    ERROR_SERVICIO_EDICION("errorServicio", TipoError.ERROR_SERVICIO, "1435"),

    /** The unacuentasinagendamientos. */
    UNACUENTASINAGENDAMIENTOS("sinAgendados", TipoError.ERROR_SIN_AGENDADOS, "1392"),

    /** The unacuentaerroragendamientos. */
    UNACUENTAERRORAGENDAMIENTOS("errorServicio", TipoError.ERROR_SERVICIO, "1407"),

    /** The variascuentaserroragendamientos. */
    VARIASCUENTASERRORAGENDAMIENTOS("alertaServicioAgendamiento", TipoError.WARNING_SERVICIO_AGENDAMIENTO, "1408"),

    /** The warningcargaparcial. */
    WARNINGCARGAPARCIAL("alertaDestinatarios", TipoError.WARNING_ERROR_RELLAMADO, "1442"),

    /** The warningcargaparcial. */
    WARNINGERRORCARGAPARCIAL("alertaDestinatarios", TipoError.WARNING_ERROR_RELLAMADO, "1523"),

    /** The sincuentavalida. */
    SINCUENTAVALIDA("sinCuentaValida", TipoError.ERROR_SERVICIO, "1484"),
    
    /** The sincuentavalida. */
    SINCUENTAVALIDATRANSFERENCIA("sinCuentaValida", TipoError.SIN_CUENTAS_VALIDAS, "1668"),

    /** The cuentaingresadapropia. */
    CUENTAINGRESADAPROPIA("esCuentaPropia", TipoError.ERROR_CUENTA_PROPIA, "1453"),

    /** The cuentainexistente. */
    CUENTAINEXISTENTE("cuentaInexistente", TipoError.ERROR_CUENTA_INEXISTENTE, "1490"),

    /** The errorservicioinformacioncuenta. */
    ERRORSERVICIOINFORMACIONCUENTA("errorServicio", TipoError.ERROR_SERVICIO_ALTA_AGENDADOS_CONF, "1461"),

    /** The error servicio alta destinatario feedback rio. */
    ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_RIO("errorServicio", TipoError.ERROR_SERVICIO_ALTA_DESTINATARIO, "1489"),

    /** The cuenta invalida alta destinatario feedback rio. */
    CUENTA_INVALIDA_ALTA_DESTINATARIO_FEEDBACK_RIO("cuentaInvalida", TipoError.CUENTA_INVALIDA_ALTA_DESTINATARIO, "1549"),

    /** The destinatario agendado alta destinatario feedback rio. */
    DESTINATARIO_AGENDADO_ALTA_DESTINATARIO_FEEDBACK("destinatarioAgendado", TipoError.DESTINATARIO_AGENDADO_ALTA_DESTINATARIO, "1455"),

    /** The alta cbu invalido. */
    ALTA_CBU_INVALIDO("cbuInvalido", TipoError.ERROR_CBU_INVALIDO, "1471"),

    /** The error servicio alta destinatario feedback otros bancos. */
    ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_OTROS_BANCOS("errorServicio", TipoError.ERROR_SERVICIO_ALTA_DESTINATARIO, "1435"),

    /** The sin medio de pago. */
    SIN_MEDIO_DE_PAGO("sinMedioDePago", TipoError.ERROR_SERVICIO_MANUAL, "1461"),

    /** The cbu invalido. */
    CBU_INVALIDO("cbuInvalido", TipoError.CBU_INVALIDO, "1461"),

    /** The cbu cuenta propia. */
    CBU_CUENTA_PROPIA("cbuCuentaPropia", TipoError.CBU_CUENTA_PROPIA, "1453"),

    /** The error servicio cbu. */
    ERROR_SERVICIO_CBU("errorServicio", TipoError.ERROR_SERVICIO, "1471"),

    /** The error servicio alta Envio efectivo generico. */
    ERROR_SERVICIO_CBU_ENVIO_EFECTIVO("errorServicio", TipoError.ERROR_SERVICIO, "1435"),

    /** The error servicio alta Envio efectivo generico. */
    ERROR_YA_AGENDADO_ENVIO_EFECTIVO("yaAgendado", TipoError.ERROR_YA_AGENDADO, "1455"),

    /** The error carga manual. */
    ERROR_CARGA_MANUAL("errorCargaManual", TipoError.ERROR_SERVICIO_MANUAL, "1461"),

    /** The error baja destinatario ya eliminado. */
    ERROR_BAJA_DESTINATARIO_YA_ELIMINADO("errorDestinatarioEliminado", TipoError.ERROR_DESTINATARIO_YA_ELIMINADO, "1452"),

    /** The error general eliminacion destinatario. */
    ERROR_GENERAL_ELIMINACION_DESTINATARIO("eliminacionErrorGeneral", TipoError.ERROR_GENERAL_ELIMINACION_DESTINATARIO, ""),

    /** The error editar destinatario invalido. */
    ERROR_EDITAR_DESTINATARIO_INVALIDO("errorDestinatarioInvalidoEditar", TipoError.ERROR_EDITAR_DESTINATARIO_INVALIDO, "1457"),

    /** The error cuenta ingresada inexistente santander. */
    ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER("errorCuentaIngresadaInexistenteSantander", TipoError.ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER, ""),
    
    /** The error cvu dolares no habiltiado. */
    ERROR_CVU_DOLARES_NO_HABILITADO("erroCVUDolaresNoHabilitado", TipoError.ERROR_CVU_DOLARES_NO_HABILITADO, "5080"),
	
    /** The CUENTAMIGRADA. */
	CUENTAMIGRADA("cuentaMigrada", TipoError.ERROR_CUENTA_MIGRADA, "6050"),
	
    /** The CUENTAMIGRADA. */
	CUENTAMIGRADA_AGENDA("cuentaMigradaNuevaTransferenciaAgenda", TipoError.ERROR_CUENTA_MIGRADA, "6051"),
	
    /** The CUENTAMIGRADA. */
	CUENTAMIGRADA_NUEVA_TRANSFERENCIA("cuentaMigradaNuevaTransferencia", TipoError.ERROR_CUENTA_MIGRADA, "6052");
	

    /** The tag. */
    private String tag;

    /** The tipo error. */
    private TipoError tipoError;

    /** The codigo mensaje. */
    private String codigoMensaje;

    /**
     * Instantiates a new error compra venta enum.
     *
     * @param tag
     *            the tag
     * @param tipoError
     *            the tipo error
     * @param codigoMensaje
     *            the codigo mensaje
     */
    ErrorAgendaDestinatariosEnum(String tag, TipoError tipoError, String codigoMensaje) {
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
     * Gets the tipo error.
     *
     * @return the tipo error
     */
    public TipoError getTipoError() {
        return tipoError;
    }

    /**
     * Gets the codigo mensaje.
     *
     * @return the codigo mensaje
     */
    public String getCodigoMensaje() {
        return codigoMensaje;
    }

}
