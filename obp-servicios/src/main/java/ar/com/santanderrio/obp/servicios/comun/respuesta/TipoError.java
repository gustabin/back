/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.respuesta;

/**
 * The Enum TipoError.
 *
 * @author Jonatan_Bocian
 */
public enum TipoError {

    /** The ok. */
    OK("OK"),

    /** The listavacia. */
    LISTAVACIA("LISTAVACIA"),

    /** The sininfodestinatario. */
    SININFODESTINATARIO("SININFODESTINATARIO"),

    /** The listavacia. */
    CUENTA_INACTIVA("CUENTA_INACTIVA"),

    /** The pinvencido. */
    PINVENCIDO("PINVENCIDO"),

    /** The errorcambiousuario. */
    ERRORCAMBIOUSUARIO("ERRORCAMBIOUSUARIO"),

    /** The sinonimo. */
    SINONIMO("SINONIMO"),

    /** The claveinvalida. */
    CLAVEINVALIDA("CLAVEINVALIDA"),

    /** The fueradehorario. */
    FUERADEHORARIO("FUERADEHORARIO"),

    /** The fondo cuenta bloqueada. */
    FONDO_CUENTA_BLOQUEADA("FONDO_CUENTA_BLOQUEADA"),

    /** The usuariobloqueado. */
    USUARIOBLOQUEADO("USUARIOBLOQUEADO"),

    /** The usuarioyadefinido. */
    USUARIOYADEFINIDO("USUARIOYADEFINIDO"),

    /** The nocontesto. */
    NOCONTESTO("NOCONTESTO"),

    /** The warning. */
    WARNING("WARNING"),

    /** The warningok. */
    WARNINGOK("WARNINGOK"),

    /** The vencimientopin. */
    VENCIMIENTOPIN("VENCIMIENTOPIN"),

    /** The error generico. */
    ERROR_GENERICO("ERROR_GENERICO"),

    /** The SUPERA SALDO DISPONIBLE. */
    ERROR_SUPERA_SALDO_DISPONIBLE("ERROR_SUPERA_SALDO_DISPONIBLE"),

    /** The Sin licitaciones. */
    ERROR_SIN_LICITACIONES("ERROR_SIN_LICITACIONES"),

    /** The sin movimientos. */
    SIN_MOVIMIENTOS("SIN_MOVIMIENTOS"),

    /** The sin movimientos. */
    SIN_MOVIMIENTOS_BUSQUEDA("SIN_MOVIMIENTOS_BUSQUEDA"),

    /** The error cuenta cerrada mayor 60 dias. */
    ERROR_CUENTA_CERRADA_MAYOR_60_DIAS("ERROR_CUENTA_CERRADA_MAYOR_60_DIAS"),

    /** The error consulta movimientos. */
    ERROR_CONSULTA_MOVIMIENTOS("ERROR_CONSULTA_MOVIMIENTOS"),

    /** The error actualizar alias. */
    ERROR_ACTUALIZAR_ALIAS("ERROR_ACTUALIZAR_ALIAS"),

    /** The error tarjeta sin vincular. */
    ERROR_TARJETA_SIN_VINCULAR("ERROR_TARJETA_SIN_VINCULAR"),

    /** The error marcar favorita. */
    ERROR_MARCAR_FAVORITA("ERROR_MARCAR_FAVORITA"),

    /** The error cuenta propia. */
    ERROR_CUENTA_PROPIA("ERROR_CUENTA_PROPIA"),

    /** The alias correspondiente a cuenta origen. */
    ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN("ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN"),

    /** The alias correspondiente a cuenta origen con solo una cuenta. */
    ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA(
            "ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA"),

    /** The error cuenta inexistente. */
    ERROR_CUENTA_INEXISTENTE("ERROR_CUENTA_INEXISTENTE"),

    /** The error sin cuentas debito. */
    ERROR_SIN_CUENTAS_DEBITO("ERROR_SIN_CUENTAS_DEBITO"),

    /** The error sin cuentas titulo. */
    ERROR_SIN_CUENTAS_TITULO("ERROR_SIN_CUENTAS_TITULO"),

    /** The error sin cuentas titulo. */
    ERROR_SIN_CUENTAS_OPERATIVAS("ERROR_SIN_CUENTAS_OPERATIVAS"),
    
    /** The error no permite Precancelar. */
    NO_PERMITE_PRECANCELAR("NO_PERMITE_PRECANCELAR"),

    /** The error cuenta inexistente. */
    ERROR_SERVICIO_ALTA_AGENDADOS_CONF("ERROR_SERVICIO_ALTA_AGENDADOS_CONF"),

    /** The error consulta resumenes. */
    ERROR_CONSULTA_RESUMENES("ERROR_CONSULTA_RESUMENES"),

    /** The sin resumenes. */
    SIN_RESUMENES("SIN_RESUMENES"),
    
    /** The sin resumen actual. */
    SIN_RESUMEN_ACTUAL("SIN_RESUMEN_ACTUAL"),

    /** The documento invalido. */
    DOCUMENTO_INVALIDO("DOCUMENTO_INVALIDO"),

    /** The error sin consumos. */
    ERROR_SIN_CONSUMOS("ERROR_SIN_CONSUMOS"),

    /** The error consumos pendientes. */
    ERROR_CONSUMOS_PENDIENTES("ERROR_CONSUMOS_PENDIENTES"),

    /** The error carga ultimos consumos. */
    ERROR_CARGA_ULTIMOS_CONSUMOS("ERROR_CARGA_ULTIMOS_CONSUMOS"),

    /** The error total ultimos consumos. */
    ERROR_TOTAL_ULTIMOS_CONSUMOS("ERROR_TOTAL_ULTIMOS_CONSUMOS"),

    /** The error actualizar favorito tarjeta. */
    ERROR_ACTUALIZAR_FAVORITO_TARJETA("ERROR_ACTUALIZAR_FAVORITO_TARJETA"),
    /** The error descarga resumen. */
    ERROR_DESCARGA_RESUMEN("ERROR_DESCARGA_RESUMEN"),

    /** The error carga ultimo resumen. */
    ERROR_CARGA_ULTIMO_RESUMEN("ERROR_CARGA_ULTIMO_RESUMEN"),
    /** The error sin ultimo resumen. */
    ERROR_SIN_ULTIMO_RESUMEN("ERROR_SIN_ULTIMO_RESUMEN"),
    /** The error carga financiaciones. */
    ERROR_CARGA_FINANCIACIONES("ERROR_CARGA_FINANCIACIONES"),
    /** The error sin financiaciones. */
    ERROR_SIN_FINANCIACIONES("ERROR_SIN_FINANCIACIONES"),

    /** The error sin financiaciones. */
    YA_FINANCIASTE("YA_FINANCIASTE"),

    /** The error financiacion tarjeta saldo insuficiente. */
    ERROR_FINANCIACION_TARJETA_SALDO_INSUFICIENTE("FINANCIACION_SALDO_INSUFICIENTE"),

    /** The error descarga resumen en zip. */
    ERROR_DESCARGA_RESUMEN_EN_ZIP("ERROR_DESCARGA_RESUMEN_EN_ZIP"),

    /** The error item cuenta. */
    ERROR_ITEM_CUENTA("ERROR_ITEM_CUENTA"),

    /** The error item cuenta seleccionada. */
    ERROR_ITEM_CUENTA_SELECCIONADA("ERROR_ITEM_CUENTA_SELECCIONADA"),

    /** The error pago prestamo. */
    ERROR_PAGO_PRESTAMO("ERROR_PAGO_PRESTAMO"),

    /** The error saldos insuficientes. */
    ERROR_SALDOS_INSUFICIENTES("ERROR_SALDOS_INSUFICIENTES"),

    /** The error pago con anterioridad. */
    ERROR_PAGO_CON_ANTERIORIDAD("ERROR_PAGO_CON_ANTERIORIDAD"),

    /** The error descargar comprobante. */
    ERROR_DESCARGAR_COMPROBANTE("ERROR_DESCARGAR_COMPROBANTE"),

    /** The error consulta preformalizacion. */
    ERROR_CONSULTA_PREFORMALIZACION("ERROR_CONSULTA_PREFORMALIZACION"),

    /** Error excedido fecha de comprobantes. */
    EXCEDIDO_FECHA_COMPROBANTES("EXCEDIDO_FECHA_COMPROBANTES"),

    /** Error excedido cantidad de comprobantes. */
    EXCEDIDO_CANTIDAD_COMPROBANTES("EXCEDIDO_CANTIDAD_COMPROBANTES"),

    /** The error descarga comprobante prestamo. */
    ERROR_DESCARGA_COMPROBANTE_PRESTAMO("ERROR_DESCARGA_COMPROBANTE_PRESTAMO"),

    /** The error detalle cuota. */
    ERROR_DETALLE_CUOTA("ERROR_DETALLE_CUOTA"),

    /** The error horario de operaciones. */
    ERROR_HORARIO_DE_OPERACIONES("ERROR_HORARIO_DE_OPERACIONES"),

    /** The error saldos insuficientes varias cuentas. */
    ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS("ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS"),

    /** The error obtener favorito. */
    ERROR_OBTENER_FAVORITO("ERROR_OBTENER_FAVORITO"),

    /** The error limites y disponibles tarjeta. */
    ERROR_LIMITES_Y_DISPONIBLES_TARJETA("ERROR_LIMITES_Y_DISPONIBLES_TARJETA"),

    /** The error agenda transferencias. */
    ERROR_AGENDA_TRANSFERENCIAS("ERROR_AGENDA_TRANSFERENCIAS"),

    /** The error parcial agenda transferencias. */
    ERROR_PARCIAL_AGENDA_TRANSFERENCIAS("ERROR_PARCIAL_AGENDA_TRANSFERENCIAS"),

    /** The error stop debit agenda transferencias. */
    ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS("ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS"),

    /** The error stop debit agenda transferencias con reintento. */
    ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS_CON_REINTENTO("ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS_CON_REINTENTO"),

    /** The sin transferencias agendadas. */
    SIN_TRANSFERENCIAS_AGENDADAS("SIN_TRANSFERENCIAS_AGENDADAS"),

    /** The error sin tarjetas. */
    ERROR_SIN_TARJETAS("ERROR_SIN_TARJETAS"),

    /** The error cuenta dolares pesos inhabilitada. */
    ERROR_CUENTA_DOLARES_PESOS_INHABILITADA("ERROR_CUENTA_DOLARES_PESOS_INHABILITADA"),

    /** The error cuenta origen fuera horario. */
    ERROR_CUENTA_ORIGEN_FUERA_HORARIO("ERROR_CUENTA_ORIGEN_FUERA_HORARIO"),

    /** The error sin acceso a informacion. */
    ERROR_SIN_ACCESO_A_INFORMACION("ERROR_SIN_ACCESO_A_INFORMACION"),

    /** The error solicitar tag titular. */
    ERROR_SOLICITAR_TAG_TITULAR("ERROR_SOLICITAR_TAG_TITULAR"),

    /** The error alta tag titular. */
    ERROR_ALTA_TAG_TITULAR("ERROR_ALTA_TAG_TITULAR"),

    /** The error cambio cotizacion dolar. */
    ERROR_CAMBIO_COTIZACION_DOLAR("ERROR_CAMBIO_COTIZACION_DOLAR"),

    /** The error monto excede transaccion permitida. */
    ERROR_MONTO_EXCEDE_TRANSACCION_PERMITIDA("ERROR_MONTO_EXCEDE_TRANSACCION_PERMITIDA"),

    /** The error actualizacion datos cuenta. */
    ERROR_ACTUALIZACION_DATOS_CUENTA("ERROR_ACTUALIZACION_DATOS_CUENTA"),

    /** The error monto excede maximo transacciona. */
    ERROR_MONTO_EXCEDE_MAXIMO_TRANSACCIONA("ERROR_MONTO_EXCEDE_MAXIMO_TRANSACCIONA"),

    /** The error saldo insuficiente. */
    ERROR_SALDO_INSUFICIENTE("ERROR_SALDO_INSUFICIENTE"),

    /** The error saldo insuficiente para recarga. */
    ERROR_SALDO_INSUFICIENTE_RECARGA("ERROR_SALDO_INSUFICIENTE_RECARGA"),

    /** The error cuenta 180 dias sin operar. */
    ERROR_CUENTA_180_DIAS_SIN_OPERAR("ERROR_CUENTA_180_DIAS_SIN_OPERAR"),

    /** Error de timeout de Banelco. */
    ERROR_BANELCO_TIMEOUT("ERROR_BANELCO_TIMEOUT"),

    /** error q ya fue pagado el servicio. */
    ERROR_PAGO_EFECTUADO("ERROR_PAGO_EFECTUADO"),

    /** The error operacion inhabilitada. */
    ERROR_OPERACION_INHABILITADA("ERROR_OPERACION_INHABILITADA"),

    OPERACION_NO_PERMITIDA_COD_7105("OPERACION_NO_PERMITIDA_COD_7105"),

    /** The error sucursal origen fuera horario. */
    ERROR_SUCURSAL_ORIGEN_FUERA_HORARIO("ERROR_SUCURSAL_ORIGEN_FUERA_HORARIO"),

    /** Warning para error fuera de horario compra venta de dolares. */
    WARNING_SUCURSAL_ORIGEN_FUERA_HORARIO("WARNING_SUCURSAL_ORIGEN_FUERA_HORARIO"),

    /** The error de servicios compra venta. */
    ERROR_DE_SERVICIOS_COMPRA_VENTA("ERROR_DE_SERVICIOS_COMPRA_VENTA"),

    /** The error eliminar transferencia sin reintento. */
    ERROR_ELIMINAR_TRANSFERENCIA_SIN_REINTENTO("ERROR_ELIMINAR_TRANSFERENCIA_SIN_REINTENTO"),

    /** The error eliminar transferencia con reintento. */
    ERROR_ELIMINAR_TRANSFERENCIA_CON_REINTENTO("ERROR_ELIMINAR_TRANSFERENCIA_CON_REINTENTO"),

    /** The error modificar transferencia. */
    ERROR_MODIFICAR_TRANSFERENCIA("ERROR_MODIFICAR_TRANSFERENCIA"),

    /** The sin cuotas pendientes. */
    SIN_CUOTAS_PENDIENTES("SIN_CUOTAS_PENDIENTES"),

    /** The error carga cuotas pediente. */
    ERROR_CARGA_CUOTAS_PEDIENTE("ERROR_CARGA_CUOTAS_PENDIENTE"),

    /** The pago tarjeta ok error stop debit. */
    PAGO_TARJETA_OK_ERROR_STOP_DEBIT("PAGO_TARJETA_OK_ERROR_STOP_DEBIT"),

    /** The error stop debit pago tarjeta. */
    ERROR_STOP_DEBIT_PAGO_TARJETA("ERROR_STOP_DEBIT_PAGO_TARJETA"),

    /** The error stop debit existente pago tarjeta. */
    ERROR_STOP_DEBIT_EXISTENTE_PAGO_TARJETA("ERROR_STOP_DEBIT_EXISTENTE_PAGO_TARJETA"),

    /** The stop debit existente. */
    STOP_DEBIT_EXISTENTE("STOP_DEBIT_EXISTENTE"),

    /** The error reintentar pago tarjeta. */
    ERROR_REINTENTAR_PAGO_TARJETA("ERROR_REINTENTAR_PAGO_TARJETA"),

    /** The error generico recarga. */
    ERROR_GENERICO_RECARGA("ERROR_GENERICO_RECARGA"),

    /** The error reintentar. */
    ERROR_REINTENTAR("ERROR_REINTENTAR"),

    /** The error time out pago tarjeta. */
    ERROR_TIME_OUT_PAGO_TARJETA("ERROR_TIME_OUT_PAGO_TARJETA"),

    /** The error time out recarga tarjeta. */
    ERROR_TIME_OUT_RECARGA_TARJETA("ERROR_TIME_OUT_RECARGA_TARJETA"),

    /** The error pago prestamo con reintento. */
    ERROR_PAGO_PRESTAMO_CON_REINTENTO("ERROR_PAGO_PRESTAMO_CON_REINTENTO"),

    /** The error pago automatico con reintento. */
    ERROR_PAGO_AUTOMATICO_CON_REINTENTO("ERROR_PAGO_AUTOMATICO_CON_REINTENTO"),

    /** The error pago automatico. */
    ERROR_PAGO_AUTOMATICO("ERROR_PAGO_AUTOMATICO"),

    /** The error parcial grilla movimientos. */
    ERROR_PARCIAL_GRILLA_MOVIMIENTOS("ERROR_PARCIAL_GRILLA_MOVIMIENTOS"),

    /** The error parcial grilla movimientos dia. */
    ERROR_PARCIAL_GRILLA_MOVIMIENTOS_DIA("ERROR_PARCIAL_GRILLA_MOVIMIENTOS_DIA"),

    /** The error fecha. */
    ERROR_FECHA("ERROR_FECHA"),

    /** The desafio. */
    DESAFIO("DESAFIO"),

    /** The error desafio. */
    ERROR_DESAFIO("ERROR_DESAFIO"),

    /** The sin metodo desafio. */
    SIN_METODO_DESAFIO("SIN_METODO_DESAFIO"),

    /** The error conocido transferencia. */
    ERROR_CONOCIDO_TRANSFERENCIA("ERROR_CONOCIDO_TRANSFERENCIA"),

    /** The error importe limite transferencia. */
    ERROR_IMPORTE_LIMITE_TRANSFERENCIA("ERROR_IMPORTE_LIMITE_TRANSFERENCIA"),

    /** The error reintentos agotados. */
    ERROR_REINTENTOS_AGOTADOS("ERROR_REINTENTOS_AGOTADOS"),

    /** The error reintentos agotados. */
    ERROR_CON_REINTENTOS("ERROR_CON_REINTENTOS"),

    /** The error solo mensaje. */
    ERROR_SOLO_MENSAJE("ERROR_SOLO_MENSAJE"),

    /** The error descarga resumen multiple. */
    ERROR_DESCARGA_RESUMEN_MULTIPLE("ERROR_DESCARGA_RESUMEN_MULTIPLE"),

    /** The error movimientos pendientes. */
    ERROR_MOVIMIENTOS_PENDIENTES("ERROR_MOVIMIENTOS_PENDIENTES"),

    /** The error pago prestamos config cuentas. */
    ERROR_PAGO_PRESTAMOS_CONFIG_CUENTAS("ERROR_PAGO_PRESTAMOS_CONFIG_CUENTAS"),

    /** The error movimientos valores pendientes. */
    ERROR_MOVIMIENTOS_VALORES_PENDIENTES("ERROR_MOVIMIENTOS_VALORES_PENDIENTES"),

    /** The warning movimientos valores pendientes. */
    WARNING_MOVIMIENTOS_VALORES_PENDIENTES("WARNING_MOVIMIENTOS_VALORES_PENDIENTES"),

    /** The error obtener cuentas prestamo. */
    ERROR_OBTENER_CUENTAS_PRESTAMO("ERROR_OBTENER_CUENTAS_PRESTAMO"),

    /** The error bloqueo token. */
    ERROR_BLOQUEO_TOKEN("ERROR_BLOQUEO_TOKEN"),

    /** The error sync token. */
    ERROR_SYNC_TOKEN("ERROR_SYNC_TOKEN"),

    /** The error otroerror token. */
    ERROR_OTROERROR_TOKEN("ERROR_OTROERROR_TOKEN"),

    /** The error reintentar DEBITO AUTOMATICO. */
    ERROR_REINTENTAR_DEBITO_AUTOMATICO("ERROR_REINTENTAR_DEBITO_AUTOMATICO"),

    /** The destinatario no verificado. */
    DESTINATARIO_NO_VERIFICADO("DESTINATARIO_NO_VERIFICADO"),

    /** The error tarjeta visa mastercard. */
    ERROR_TARJETA_VISA_MASTERCARD("ERROR_TARJETA_VISA_MASTERCARD"),

    /** The error tarjeta banelco. */
    ERROR_TARJETA_BANELCO("ERROR_TARJETA_BANELCO"),
    
    ERROR_SALDO("ERROR_SALDO"),

    /** The error reintentear nuevo pago. */
    ERROR_REINTENTEAR_NUEVO_PAGO("ERROR_REINTENTEAR_NUEVO_PAGO"),

    /** The error time out nuevo pago. */
    ERROR_TIME_OUT_NUEVO_PAGO("ERROR_TIME_OUT_NUEVO_PAGO"),

    /** The error limite mayor nuevo pago. */
    ERROR_LIMITE_MAYOR_NUEVO_PAGO("ERROR_LIMITE_MAYOR_NUEVO_PAGO"),

    /** The error tarjeta mastercard visa banelco. */
    ERROR_TARJETA_MASTERCARD_VISA_BANELCO("ERROR_TARJETA_MASTERCARD_VISA_BANELCO"),

    /** The error obtener tarjetas home. */
    ERROR_TABLERO_HOME("ERROR_TABLERO_HOME"),

    /** The error buscar prestamos. */
    ERROR_BUSCAR_PRESTAMOS("ERROR_BUSCAR_PRESTAMOS"),

    /** The error buscar prestamos oc. */
    ERROR_BUSCAR_PRESTAMOS_OC("ERROR_BUSCAR_PRESTAMOS_OC"),

    /** The error total buscar prestamos. */
    ERROR_TOTAL_BUSCAR_PRESTAMOS("ERROR_TOTAL_BUSCAR_PRESTAMOS"),

    /** The error parcial buscar prestamos. */
    ERROR_PARCIAL_BUSCAR_PRESTAMOS("ERROR_PARCIAL_BUSCAR_PRESTAMOS"),

    /** The error parcial buscar prestamos oc. */
    ERROR_PARCIAL_BUSCAR_PRESTAMOS_OC("ERROR_PARCIAL_BUSCAR_PRESTAMOS_OC"),

    /** The error prestamo vencido. */
    ERROR_PRESTAMO_VENCIDO("ERROR_PRESTAMO_VENCIDO"),

    /** The error prestamos varios vencido. */
    ERROR_PRESTAMOS_VARIOS_VENCIDO("ERROR_PRESTAMOS_VARIOS_VENCIDO"),
    
    /** error busqueda prestamos preaprobados*/
    ERROR_BUSQUEDA_PRESTAMO_PREAPROBADO("ERROR_BUSQUEDA_PRESTAMO_PREAPROBADO"),
    
    /** error simular prestamo preaprobado*/
    ERROR_SIMULACION_PRESTAMO_PREAPROBADO("ERROR_SIMULACION_PRESTAMO_PREAPROBADO"),
    
    /** error alta prestamo preaprobado*/
    ERROR_ALTA_PRESTAMO_PREAPROBADO("ERROR_ALTA_PRESTAMO_PREAPROBADO"),
    
    /** error encolar prestamo preaprobado*/
    ERROR_ENCOLAR_PRESTAMO_PREAPROBADO("ERROR_ENCOLAR_PRESTAMO_PREAPROBADO"),
    
    /** error prestamo preaprobado ya esta encolado*/
    ERROR_PRESTAMO_ENCOLADO_PREAPROBADO("ERROR_PRESTAMO_ENCOLADO_PREAPROBADO"),
    
    /** error al actualizar prestamo preaprobado*/
    ERROR_UPDATE_PRESTAMO_PREAPROBADO("ERROR_UPDATE_PRESTAMO_PREAPROBADO"),

    /** The error reintentos operacion. */
    ERROR_TAG("ERROR_TAG"),

    /** The error sin tags. */
    ERROR_SIN_TAGS("ERROR_SIN_TAGS"),

    /** The fuera de horario. */
    FUERA_DE_HORARIO("FUERA_DE_HORARIO"),

    /** The error solicitar tag adicional. */
    ERROR_SOLICITAR_TAG_ADICIONAL("ERROR_SOLICITAR_TAG_ADICIONAL"),

    /** The error carga saldos y movimientos. */
    ERROR_CARGA_SALDOS_Y_MOVIMIENTOS("ERROR_CARGA_SALDOS_Y_MOVIMIENTOS"),

    /** The monedero sin activar. */
    MONEDERO_SIN_ACTIVAR("MONEDERO_SIN_ACTIVAR"),

    /** The monedero sin datos prisma. */
    MONEDERO_SIN_DATOS_PRISMA("MONEDERO_SIN_DATOS_PRISMA"),

    /** The monedero cliente sin token. */
    MONEDERO_CLIENTE_SIN_TOKEN("MONEDERO_CLIENTE_SIN_TOKEN"),
    /** The timeout prisma. */
    TIMEOUT_PRISMA_PAGOS("TIMEOUT_PRISMA_PAGOS"),

    /** The error activar tag. */
    ERROR_ACTIVAR_TAG("ERROR_ACTIVAR_TAG"),

    /** The fecha nacimiento invalida. */
    FECHA_NACIMIENTO_INVALIDA("FECHA_NACIMIENTO_INVALIDA"),

    /** The error nup identico. */
    ERROR_NUP_IDENTICO("ERROR_NUP_IDENTICO"),

    /** The error generico datos solicitante monedero. */
    ERROR_GENERICO_DATOS_SOLICITANTE_MONEDERO("ERROR_GENERIC_DATOS_SOLICITANTE_TAG"),

    /** The datos invalidos. */
    DATOS_INVALIDOS("DATOS_INVALIDOS"),

    /** The error generico alta tag monedero. */
    ERROR_GENERICO_ALTA_TAG_MONEDERO("ERROR_GENERICO_ALTA_TAG_MONEDERO"),

    /** The error generico activacion tag monedero. */
    ERROR_GENERICO_ACTIVACION_TAG_MONEDERO("ERROR_GENERICO_ACTIVACION_TAG_MONEDERO"),

    /** The error generico alta persona fisica. */
    ERROR_GENERICO_ALTA_PERSONA_FISICA("ERROR_GENERICO_ALTA_PERSONA_FISICA"),

    /** The error reintentos operacion. */
    ERROR_REINTENTOS_OPERACION("ERROR_REINTENTOS_OPERACION"),

    /** The warning reintentos operacion. */
    WARNING_REINTENTOS_OPERACION("WARNING_REINTENTOS_OPERACION"),

    /** The error Inicio Agenda Prestamos. */
    ERROR_INICIO_PRESTAMOS("ERROR_INICIO_PRESTAMOS"),

    /** The error linea disponible zero. */
    ERROR_LINEA_DISPONIBLE_ZERO("ERROR_LINEA_DISPONIBLE_ZERO"),

    /** The error validacion no disponible. */
    ERROR_VALIDACION_NO_DISPONIBLE("ERROR_VALIDACION_NO_DISPONIBLE"),

    /** The error validacion no disponible no total. */
    ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL("ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL"),

    /** The error validacion no disponible no total no registros. */
    ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL_NO_REGISTROS("ERROR_VALIDACION_LINEA_DISPONIBLE_LINEA_TOTAL_NO_REGISTROS"),

    /** The error validacion no registros. */
    ERROR_VALIDACION_NO_REGISTROS("ERROR_VALIDACION_NO_REGISTROS"),

    /** The error no registros con linea crediticia. */
    ERROR_NO_REGISTROS_CON_LINEA_CREDITICIA("ERROR_NO_REGISTROS_CON_LINEA_CREDITICIA"),

    /** The error validacion sin cuentas monetarias activas. */
    ERROR_VALIDACION_SIN_CUENTAS_MONETARIAS_ACTIVAS("ERROR_VALIDACION_SIN_CUENTAS_MONETARIAS_ACTIVAS"),

    /** The error validacion sin cuentas monetarias activas con prestamos. */
    ERROR_VALIDACION_SIN_CUENTAS_MONETARIAS_ACTIVAS_CON_PRESTAMOS(
            "ERROR_VALIDACION_SIN_CUENTAS_MONETARIAS_ACTIVAS_CON_PRESTAMOS"),

    /** The error plazo prestamos. */
    ERROR_PLAZO_PRESTAMOS("ERROR_PLAZO_PRESTAMOS"),

    /** The error sin agendados. */
    ERROR_SIN_AGENDADOS("ERROR_SIN_AGENDADOS"),

    /** The error servicio agendamiento. */
    ERROR_CUENTA_RIO("ERROR_CUENTA_RIO"),

    /** The error servicio agendamiento. */
    ERROR_SERVICIO("ERROR_SERVICIO"),

    /** The error ya agendado. */
    ERROR_YA_AGENDADO("ERROR_YA_AGENDADO"),

    /** The error servicio para carga manual agendamiento. */
    ERROR_SERVICIO_MANUAL("ERROR_SERVICIO_MANUAL"),

    /** The warning servicio agendamiento. */
    WARNING_SERVICIO_AGENDAMIENTO("WARNING_SERVICIO_AGENDAMIENTO"),

    /** The warning agendamiento rellamado. */
    WARNING_ERROR_RELLAMADO("WARNING_ERROR_RELLAMADO"),

    /** The destinatario agendado alta destinatario. */
    DESTINATARIO_AGENDADO_ALTA_DESTINATARIO("DESTINATARIO_AGENDADO_ALTA_DESTINATARIO"),

    /** The riesgo bloqueante. */
    RIESGO_BLOQUEANTE("RIESGO_BLOQUEANTE"),

    /** The riesgo medio. */
    RIESGO_MEDIO("RIESGO_MEDIO"),

    /** The sin cuentas tipo moneda. */
    SIN_CUENTAS_TIPO_MONEDA("SIN_CUENTAS_TIPO_MONEDA"),

    /** The sin cuentas validas. */
    SIN_CUENTAS_VALIDAS("SIN_CUENTAS_VALIDAS"),

    /** The TimeOut. */
    TIMEOUT("ERROR_TIME_OUT"),

    /** The error finalizar suscripcion fondo con reintento. */
    ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO("ERROR_FINALIZAR_SUSCRIPCION_FONDO_CON_REINTENTO"),

    /** The error finalizar suscripcion fondo sin reintento. */
    ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO("ERROR_FINALIZAR_SUSCRIPCION_FONDO_SIN_REINTENTO"),

    /** The error finalizar suscripcion rescate con reintento. */
    ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO("ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_CON_REINTENTO"),

    /** The error finalizar suscripcion rescate fondo sin reintento. */
    ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO("ERROR_FINALIZAR_SUSCRIPCION_RESCATE_FONDO_SIN_REINTENTO"),

    /** The error finalizar transaccion fondo con reintento. */
    ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO("ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO"),

    /** The error finalizar transaccion fondo sin reintento. */
    ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO("ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO"),

    /** The error finalizar suscripcion rescate fondo sin reintento. */
    ERROR_FINALIZAR_ORDEN_COMPRA_CON_REINTENTO("ERROR_FINALIZAR_ORDEN_COMPRA_CON_REINTENTO"),

    /** The error finalizar suscripcion rescate fondo sin reintento. */
    ERROR_FINALIZAR_ORDEN_COMPRA_SIN_REINTENTO("ERROR_FINALIZAR_ORDEN_COMPRA_SIN_REINTENTO"),

    /** Sin fondos a rescatar. */
    SIN_FONDOS_A_RESCATAR("SIN_FONDOS_A_RESCATAR"),

    /** The login clave bloqueada. */
    LOGIN_CLAVE_BLOQUEADA("LOGIN_CLAVE_BLOQUEADA"),

    /** The login documento homonimo. */
    LOGIN_DOCUMENTO_HOMONIMO("LOGIN_DOCUMENTO_HOMONIMO"),

    /** The login documento invalido. */
    LOGIN_DOCUMENTO_INVALIDO("LOGIN_DOCUMENTO_INVALIDO"),

    /** The login datos incorrectos. */
    LOGIN_DATOS_INCORRECTOS("LOGIN_DATOS_INCORRECTOS"),

    /** The error maximo minimo. */
    ERROR_LIMITE_MINIMO("ERROR_LIMITE_MINIMO"),

    /** The error maximo minimo. */
    ERROR_LIMITE_MAXIMO("ERROR_LIMITE_MAXIMO"),

    /** The error sin medio de pago. */
    SIN_MEDIO_DE_PAGO("SIN_MEDIO_DE_PAGO"),

    /** The error sin medio de pago. */
    CBU_INVALIDO("CBU_INVALIDO"),

    /** The error cbu cuenta propia. */
    CBU_CUENTA_PROPIA("CBU_CUENTA_PROPIA"),

    /** The error alias cbu ok pero cuenta inactiva. */
    ALIASCBUOK_CUENTA_INACTIVA("ALIASCBUOK_CUENTA_INACTIVA"),

    /** The error cuendo ya tiene un alias agendado. */
    ERROR_YA_TIENE_ALIAS("YA_TIENE_ALIAS"),

    /** The error cuendo el alias ya fue usado. */
    ERROR_ALIAS_USADO("ALIAS_USADO"),

    /** The error cuendo el alias no fue especificado. */
    ERROR_ALIAS_NO_ESPECIFICADO("ALIAS_NO_ESPECIFICADO"),

    /** The login error session concurrente. */
    LOGIN_ERROR_SESSION_CONCURRENTE("LOGIN_ERROR_SESSION_CONCURRENTE"),

    /** The login error control session. */
    LOGIN_ERROR_CONTROL_SESSION("LOGIN_ERROR_CONTROL_SESSION"),

    /** The login error usuario clave expiradas. */
    LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS("LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS"),

    /** The login error usuario no definido. */
    LOGIN_ERROR_USUARIO_NO_DEFINIDO("LOGIN_ERROR_USUARIO_NO_DEFINIDO"),

    /** The login error total. */
    LOGIN_ERROR_TOTAL("LOGIN_ERROR_TOTAL"),

    /** Quiere limpiar session fuera del flujo pre login ok. */
    PRE_LOGIN_ERROR("PRE_LOGIN_ERROR"),

    /** The login error feedback usuario clave expiradas. */
    LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS("LOGIN_ERROR_FEEDBACK_USUARIO_CLAVE_EXPIRADAS"),

    /** The pago multiple ejecucion error. */
    PAGO_MULTIPLE_EJECUCION_ERROR("PAGO_MULTIPLE_EJECUCION_ERROR"),

    /** The error solicitud monedero dulpicado. */
    ERROR_SOLICITUD_MONEDERO_DUPLICADO("ERROR_SOLICITUD_MONEDERO_DUPLICADO"),

    /** The error validacion datos. */
    ERROR_VALIDACION_DATOS("ERROR_VALIDACION_DATOS"),

    /** The error pago haberes. */
    ERROR_PAGO_HABERES("ERROR_PAGO_HABERES"),

    /** The error eliminar empleado sin reintento. */
    ERROR_ELIMINAR_EMPLEADO_SIN_REINTENTO("ERROR_ELIMINAR_EMPLEADO_SIN_REINTENTO"),

    /** The error eliminar empleado con reintento. */
    ERROR_ELIMINAR_EMPLEADO_CON_REINTENTO("ERROR_ELIMINAR_EMPLEADO_CON_REINTENTO"),

    /** The error agendar empleado con reintento. */
    ERROR_AGENDAR_EMPLEADO_CON_REINTENTO("ERROR_AGENDAR_EMPLEADO_CON_REINTENTO"),

    /** The error agendar empleado sin reintento. */
    ERROR_AGENDAR_EMPLEADO_SIN_REINTENTO("ERROR_AGENDAR_EMPLEADO_SIN_REINTENTO"),

    /** The error eliminar agendamiento sin reintento. */
    ERROR_ELIMINAR_AGENDAMIENTO_SIN_REINTENTO("ERROR_ELIMINAR_AGENDAMIENTO_SIN_REINTENTO"),

    /** The error eliminar agendamiento con reintento. */
    ERROR_ELIMINAR_AGENDAMIENTO_CON_REINTENTO("ERROR_ELIMINAR_AGENDAMIENTO_CON_REINTENTO"),

    /** The cuenta invalida. */
    CUENTA_INVALIDA("CUENTA_INVALIDA"),

    /** The usuario no afiliado. */
    USUARIO_NO_AFILIADO("USUARIO_NO_AFILIADO"),

    /** The usuario sin empleados. */
    USUARIO_SIN_EMPLEADOS("USUARIO_SIN_EMPLEADOS"),

    /** The usuario sin agendamientos. */
    USUARIO_SIN_AGENDAMIENTO("USUARIO_SIN_AGENDAMIENTO"),

    /** The usuario sin pago programado. */
    USUARIO_SIN_PAGO_PROGRAMADO("USUARIO_SIN_AGENDAMIENTO"),

    /** The error servicio alta destinatario. */
    ERROR_SERVICIO_ALTA_DESTINATARIO("ERROR_SERVICIO_ALTA_DESTINATARIO"),

    /** The cuenta invalida alta destinatario. */
    CUENTA_INVALIDA_ALTA_DESTINATARIO("CUENTA_INVALIDA_ALTA_DESTINATARIO"),

    /** The error descarga singular. */
    ERROR_DESCARGA_SINGULAR("ERROR_DESCARGA_SINGULAR"),

    /** The error descarga multiple. */
    ERROR_DESCARGA_MULTIPLE("ERROR_DESCARGA_MULTIPLE"),

    /** The login error de servicios usuario clave expiradas. */
    LOGIN_ERROR_DE_SERVICIOS_USUARIO_CLAVE_EXPIRADAS("LOGIN_ERROR_DE_SERVICIOS_USUARIO_CLAVE_EXPIRADAS"),

    /** The login error usuario cambio pendiente. */
    LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE("LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE"),

    /** The login error feedback usuario cambio pendiente. */
    LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE("LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE"),

    /** The login error de servicios usuario cambio pendiente. */
    LOGIN_ERROR_DE_SERVICIOS_USUARIO_CAMBIO_PENDIENTE("LOGIN_ERROR_DE_SERVICIOS_USUARIO_CAMBIO_PENDIENTE"),

    /** The login error feedback usuario no definido. */
    LOGIN_ERROR_FEEDBACK_USUARIO_NO_DEFINIDO("LOGIN_ERROR_FEEDBACK_USUARIO_NO_DEFINIDO"),

    /** The error cuenta invalida. */
    ERROR_CUENTA_INVALIDA("ERROR_CUENTA_INVALIDA"),

    /** The limite operaciones diario banelco superado. */
    LIMITE_OPERACIONES_DIARIO_BANELCO_SUPERADO("LIMITE_OPERACIONES_DIARIO_BANELCO_SUPERADO"),

    /** The empresa inhabilitada. */
    EMPRESA_INHABILITADA("EMPRESA_INHABILITADA"),

    /** The IMPORTE ERRONEO. */
    IMPORTE_ERRONEO("IMPORTE_ERRONEO"),

    /** The destinatario invalido. */
    DESTINATARIO_INVALIDO("DESTINATARIO_INVALIDO"),

    /** The nuevo pago automatico time out. */
    NUEVO_PAGO_AUTOMATICO_TIME_OUT("NUEVO_PAGO_AUTOMATICO_TIME_OUT"),

    /** The mya error. */
    MYA_ERROR("MYA_ERROR"),

    /** The mya celular ya registrado. */
    MYA_CELULAR_YA_REGISTRADO("MYA_CELULAR_YA_REGISTRADO"),

    /** The mya mail ya registrado. */
    MYA_MAIL_YA_REGISTRADO("MYA_MAIL_YA_REGISTRADO"),

    /** The suscripcion mya. */
    SUSCRIPCION_MYA("SUSCRIPCION_MYA"),

    /** The error cbu invalido. */
    ERROR_CBU_INVALIDO("ERROR_CBU_INVALIDO"),

    /** The warning reintentos. */
    WARNING_REINTENTOS("WARNING_REINTENTOS"),

    /** The error destinatario ya eliminado. */
    ERROR_DESTINATARIO_YA_ELIMINADO("ERROR_DESTINATARIO_YA_ELIMINADO"),

    /** The error general eliminacion destinatario. */
    ERROR_GENERAL_ELIMINACION_DESTINATARIO("ERROR_GENERAL_ELIMINACION_DESTINATARIO"),

    /** The login rsa deny. */
    LOGIN_RSA_DENY("LOGIN_RSA_DENY"),
    
    /** The login rsa user locked. */
    LOGIN_RSA_USUARIO_BLOQUEADO("LOGIN_RSA_USUARIO_BLOQUEADO"),
    
    RSA_BLOQUEAR_USUARIO("RSA_BLOQUEAR_USUARIO"),

    RULE_DERIVACIONAPP("DERIVACIONAPP"),

    /** The documento homonimo. */
    DOCUMENTO_HOMONIMO("DOCUMENTO_HOMONIMO"),
    /** The error no es persona fisica. */
    ERROR_NO_ES_PERSONA_FISICA("ERROR_NO_ES_PERSONA_FISICA"),
    /** The error cte sin medio auth valido. */
    ERROR_CTE_SIN_MEDIO_AUTH_VALIDO("ERROR_CTE_SIN_MEDIO_AUTH_VALIDO"),

    /** The error no hemos podido identificarte. */
    ERROR_NO_HEMOS_PODIDO_IDENTIFICARTE("ERROR_NO_HEMOS_PODIDO_IDENTIFICARTE"),

    /** The error editar destinatario invalido. */
    ERROR_EDITAR_DESTINATARIO_INVALIDO("ERROR_EDITAR_DESTINATARIO_INVALIDO"),

    /** ERROR USUARIO NO POSEE TARJETA BANELCO. */
    ERROR_USUARIO_NO_POSEE_TARJETA_BANELCO("ERROR_USUARIO_NO_POSEE_TARJETA_BANELCO"),

    /** ERROR USUARIO CBU INVALIDO. */
    ERROR_USUARIO_CBU_INVALIDO("ERROR_USUARIO_CBU_INVALIDO"),

    /** ERROR OBTENER DATOS POR CBU. */
    ERROR_OBTENER_DATOS_POR_CBU("ERROR_OBTENER_DATOS_POR_CBU"),

    /** ERROR PAGAR CBU. */
    ERROR_PAGAR_CBU("ERROR_PAGAR_CBU"),

    /** ERROR PAGAR CBU PERMITE REINTENTAR. */
    ERROR_PAGAR_CBU_PERMITE_REINTENTAR("ERROR_PAGAR_CBU_PERMITE_REINTENTAR"),

    /** ERROR PAGAR CBU NO PERMITE REINTENTAR. */
    ERROR_PAGAR_CBU_NO_PERMITE_REINTENTAR("ERROR_PAGAR_CBU_NO_PERMITE_REINTENTAR"),

    /** ERROR ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_CON_REINTENTOS. */
    ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_CON_REINTENTOS("ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_CON_REINTENTOS"),

    /** ERROR ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_SIN_REINTENTOS. */
    ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_SIN_REINTENTOS("ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_SIN_REINTENTOS"),
    
    /** ERROR ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_CON_REINTENTOS. */
    ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_CON_REINTENTOS("ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_CON_REINTENTOS"),

    /** ERROR ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_SIN_REINTENTOS. */
    ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_SIN_REINTENTOS("ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_SIN_REINTENTOS"),

    /** ERROR PREGUNTAS. */
    PREGUNTAS("PREGUNTAS"),

    /** ERROR PREGUNTAS_AUTENTICACION_TIME_OUT. */
    ERROR_SESION_PREGUNTAS_TIME_OUT("ERROR_SESION_TIME_OUT"),

    /** ERROR ERROR_SIN_TRANSFERENCIAS_AGENDADAS. */
    ERROR_SIN_TRANSFERENCIAS_AGENDADAS("ERROR_SIN_TRANSFERENCIAS_AGENDADAS"),

    /** ERROR_GENERICO_ALIAS_CBU. */
    ERROR_GENERICO_ALIAS_CBU("ERROR_GENERICO_ALIAS_CBU"),

    /** METODO_AUTENTICACION. */
    METODO_AUTENTICACION("METODO_AUTENTICACION"),
    
    /** FLUJO_CELULAR. */
    FLUJO_CELULAR("FLUJO_CELULAR"),

    /** ERROR PUEDE_REINTENTAR. */
    PUEDE_REINTENTAR("PUEDE_REINTENTAR"),

    /** ERROR CLAVE_INCORRECTA. */
    CLAVE_INCORRECTA("CLAVE_INCORRECTA"),
    /** ERROR CLAVE_INCORRECTA. */
    CLAVE_ONLINE_CLIENTE_BLOQUEADO("CLAVE_ONLINE_CLIENTE_BLOQUEADO"),  
    /** ERROR ERROR_IP_BLOQUEADA. */
    ERROR_IP_BLOQUEADA("ERROR_IP_BLOQUEADA"), 
    /** ERROR ERROR_SIST_CLAVE_NO_DISPONIBLE. */
    ERROR_SIST_CLAVE_NO_DISPONIBLE("ERROR_SIST_CLAVE_NO_DISPONIBLE"),    
    /** ERROR ERROR_CLIENTE_SIN_CONTRATO. */
    ERROR_CLIENTE_SIN_CONTRATO("ERROR_CLIENTE_SIN_CONTRATO"),
    /** ERROR ERROR_HAY_RESPUESTAS_ERRONEAS. */
    ERROR_HAY_RESPUESTAS_ERRONEAS("ERROR_HAY_RESPUESTAS_ERRONEAS"),
    /** ERROR ERROR_SIN_TARJETA_DEBITO_VALIDA. */
    ERROR_SIN_TARJETA_DEBITO_VALIDA("ERROR_SIN_TARJETA_DEBITO_VALIDA"),
    /** ERROR ERROR_SIN_CEL_REGISTRADO. */
    ERROR_SIN_CEL_REGISTRADO("ERROR_SIN_CEL_REGISTRADO"),
    /** ERROR ERROR_AUTENTICACION_TARJ_DEBITO. */
    ERROR_AUTENTICACION_TARJ_DEBITO("ERROR_AUTENTICACION_TARJ_DEBITO"), 
    /** ERROR ERROR_AUTENTICACION_PIN_INACTIVA. */
    ERROR_AUTENTICACION_PIN_INACTIVA("ERROR_AUTENTICACION_PIN_INACTIVA"),
    /** ERROR ERROR_AUTENTICACION_SMS_OTP_INACTIVA. */
    ERROR_AUTENTICACION_SMS_OTP_INACTIVA("ERROR_AUTENTICACION_SMS_OTP_INACTIVA"),
    /** ERROR ERROR_AUTENTICACION_CVV2_INACTIVA. */
    ERROR_AUTENTICACION_CVV2_INACTIVA("ERROR_AUTENTICACION_CVV2_INACTIVA"),
    /** ERROR ERROR_RUTINA_FECHAS. */
    ERROR_RUTINA_FECHAS("ERROR_RUTINA_FECHAS"),
    /** ERROR ERROR_MODULO. */
    ERROR_MODULO("ERROR_MODULO"),
    /** ERROR ERROR_SQL_CODE_DB2. */
    ERROR_SQL_CODE_DB2("ERROR_SQL_CODE_DB2"),
    /** ERROR ERROR_CICS. */
    ERROR_CICS("ERROR_CICS"),
    /** ERROR ERROR_CLIENTE_BLOQUEADO_3. */
    ERROR_CLIENTE_BLOQUEADO_3("ERROR_CLIENTE_BLOQUEADO_3"),
    /** ERROR ERROR_CLIENTE_BLOQUEADO_4. */
    ERROR_CLIENTE_BLOQUEADO_4("ERROR_CLIENTE_BLOQUEADO_4"),
    /** ERROR ERROR_FUNCION_INVALIDA. */
    ERROR_FUNCION_INVALIDA("ERROR_FUNCION_INVALIDA"),
   
    /** CLAVE ONLINE PUEDE REINTENTAR. */
    CLAVE_ONLINE_PUEDE_REINTENTAR("CLAVE_ONLINE_PUEDE_REINTENTAR"),
    /** ERROR SIN_REINTENTOS_SMS. */
    SIN_REINTENTOS_SMS("SIN_REINTENTOS_SMS"),
    /** CONFIRMACION_CLAVE_TIMEOUT. */
    CONFIRMACION_CLAVE_TIMEOUT("CONFIRMACION_CLAVE_TIMEOUT"),

    /** CLAVE_ONLINE_SESION_EXPIRADA. */
    CLAVE_ONLINE_SESION_EXPIRADA("CLAVE_ONLINE_SESION_EXPIRADA"),

    /** ERROR_HORARIOS_HONORARIOS. */
    ERROR_HORARIOS_HONORARIOS("ERROR_HORARIOS_HONORARIOS"),
    /** The error parcial de transferencia. */
    ERROR_PARCIAL_TRANSFERENCIA("ERROR_PARCIAL_TRANSFERENCIA"),
    /** The debito bloqueada. */
    DEBITO_BLOQUEADA("DEBITO_BLOQUEADA"),
    /** The debito numeros equivocados. */
    DEBITO_NUMEROS_EQUIVOCADOS("DEBITO_NUMEROS_EQUIVOCADOS"),
    /** The error ERROR_DESCARGA_TYC_MYA. */
    ERROR_DESCARGA_TYC_MYA("ERROR_DESCARGA_TYC_MYA"),

    /** The persona no existe en padron. */
    PERSONA_NO_EXISTE_EN_PADRON("PERSONA_NO_EXISTE_EN_PADRON"),

    /** The fecha nacimiento no coincide. */
    FECHA_NACIMIENTO_NO_COINCIDE("FECHA_NACIMIENTO_NO_COINCIDE"),

    /** The persona no existe en altair padron. */
    PERSONA_NO_EXISTE_EN_ALTAIR_PADRON("PERSONA_NO_EXISTE_EN_ALTAIR_PADRON"),

    /** The solicitudes error generico. */
    SOLICITUDES_ERROR_GENERICO("SOLICITUDES_ERROR_GENERICO"),

    /** The persona fecha nacimiento no coincide. */
    PERSONA_FECHA_NACIMIENTO_NO_COINCIDE("PERSONA_FECHA_NACIMIENTO_NO_COINCIDE"),

    /** The persona mismo titular adicional. */
    PERSONA_MISMO_TITULAR_ADICIONAL("PERSONA_MISMO_TITULAR_ADICIONAL"),

    /** The persona inhabilitada. */
    PERSONA_INHABILITADA("PERSONA_INHABILITADA"),

    /** The PERSON A MENO R 16 años. */
    PERSONA_MENOR_16_ANIOS("PERSONA_MENOR_16_AÑOS"),

    /** The persona menor terminos condiciones. */
    PERSONA_MENOR_TERMINOS_CONDICIONES("PERSONA_MENOR_TERMINOS_CONDICIONES"),

    /** The persona adicional coincide con titular. */
    PERSONA_ADICIONAL_COINCIDE_CON_TITULAR("PERSONA_ADICIONAL_COINCIDE_CON_TITULAR"),

    /** The error persona inhabilitada. */
    ERROR_PERSONA_INHABILITADA("ERROR_PERSONA_INHABILITADA"),

    /** The error MYA_CELULAR_ACTUALIZADO. */
    MYA_CELULAR_ACTUALIZADO("MYA_CELULAR_ACTUALIZADO"),

    /** The error CUIT_CUIL_INVALIDO. */
    CUIT_CUIL_INVALIDO("CUIT_CUIL_INVALIDO"),

    /** The detalle deudor error total. */
    DETALLE_DEUDOR_ERROR_TOTAL("DETALLE_DEUDOR_ERROR_TOTAL"),

    /** The transferencia con reintento. */
    TRANSFERENCIA_CON_REINTENTO("TRANSFERENCIA_CON_REINTENTO_MANUAL"),

    /** The transferencia 48 horas. */
    TRANSFERENCIA_48_HORAS("48_HORAS"),

    /** The transferencia sin reintento. */
    TRANSFERENCIA_SIN_REINTENTO("TRANSFERENCIA_SIN_REINTENTO"),

    /** The cuenta propia y unica. */
    CUENTA_PROPIA_Y_UNICA("CUENTA_PROPIA_Y_UNICA"),

    /** The no existe destinatario. */
    NO_EXISTE_DESTINATARIO("NO_EXISTE_DESTINATARIO"),

    /** The error en formato de la cuenta destino ingresada. */
    ERROR_EN_FORMATO_CUENTA("ERROR_EN_FORMATO_CUENTA"),

    /** The cliente sin moneda sin trj banelco habilitada. */
    CLIENTE_SIN_MONEDA_SIN_TRJ_BANELCO_HABILITADA("CLIENTE_SIN_MONEDA_SIN_TRJ_BANELCO_HABILITADA"),

    /** The no hay correspondencia con cbu sesion. */
    NO_HAY_CORRESPONDENCIA_CON_CBU_SESION("NO_HAY_CORRESPONDENCIA_CON_CBU_SESION"),

    /** The cliente no habilitado para transferir. */
    CLIENTE_NO_HABILITADO_PARA_TRANSFERIR("CLIENTE_NO_HABILITADO_PARA_TRANSFERIR"),

    /** The no coinciden monedas. */
    NO_COINCIDEN_MONEDAS("NO_COINCIDEN_MONEDAS"),

    /** The error finalizar plazo fijo sin reintento. */
    ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO("ERROR_FINALIZAR_PLAZO_FIJO_SIN_REINTENTO"),

    /** The error finalizar plazo fijo con reintento. */
    ERROR_FINALIZAR_PLAZO_FIJO_CON_REINTENTO("ERROR_FINALIZAR_PLAZO_FIJO_CON_REINTENTO"),

    /** the ERROR SIN TENENCIA PLAZO FIJO. */
    ERROR_SIN_TENENCIA_PLAZO_FIJO("ERROR_SIN_TENENCIA_PLAZO_FIJO"),

    /** The transferencia con reintento manual. */
    TRANSFERENCIA_CON_REINTENTO_MANUAL("TRANSFERENCIA_CON_REINTENTO_MANUAL"),

    /** The error deny rsa. */
    DENY_RSA("DENY_RSA"),

    RSA_OFFLINE("RSA_OFFLINE"),

    RSA_OFFLINE_VALIDACION_FALLIDA("RSA_OFFLINE_VALIDACION_FALLIDA"),

    BIOCATCH_OFFLINE("BIOCATCH_OFFLINE_VALIDACION_FALLIDA"),
    
    /** The cuenta inexistente. */
    CUENTA_INEXISTENTE("CUENTA_INEXISTENTE"),

    /** The error en servicio timeout cuenta. */
    ERROR_EN_SERVICIO_TIMEOUT_CUENTA("ERROR_EN_SERVICIO_TIMEOUT_CUENTA"),

    /** Error total comprobantes. */
    ERROR_COMPROBANTES("ERROR_COMPROBANTES"),

    /** Error sin comprobantes. */
    SIN_COMPROBANTES("SIN_COMPROBANTES"),

    /** The sin comprobantes en buscador. */
    SIN_COMPROBANTES_EN_BUSCADOR("SIN_COMPROBANTES_EN_BUSCADOR"),

    /** Alerta warning comprobantes. */
    ALERTA_COMPROBANTES("ALERTA_COMPROBANTES"),

    /** The error sin contrato transferencia. */
    ERROR_SIN_CONTRATO_TRANSFERENCIA("ERROR_SIN_CONTRATO_TRANSFERENCIA"),

    /** The error numero identificador. */
    ERROR_NUMERO_IDENTIFICADOR("ERROR_NUMERO_IDENTIFICADOR"),

    /** The error detalle comprobantes. */
    ERROR_DETALLE_COMPROBANTES("ERROR_DETALLE_COMPROBANTES"),

    /** The tarjeta incompatible. */
    TARJETA_INCOMPATIBLE("TARJETA_INCOMPATIBLE"),

    /** The error en servicio consulta cuenta. */
    ERROR_EN_SERVICIO_CONSULTA_CUENTA("ERROR_EN_SERVICIO_CONSULTA_CUENTA"),

    /** ERROR_ADHESION_BILLETERA. */
    ERROR_ADHESION_BILLETERA("ERROR_ADHESION_BILLETERA"),

    /** ERROR_BILLETERA_MENOR_EDAD. */
    ERROR_BILLETERA_MENOR_EDAD("ERROR_BILLETERA_MENOR_EDAD"),

    /** ERROR_SIN_CUENTAS_Y_TARJETAS. */
    ERROR_SIN_CUENTAS_Y_TARJETAS("ERROR_SIN_CUENTAS_Y_TARJETAS"),

    /** ERROR_USUARIO_BLOQUEADO. */
    ERROR_USUARIO_BLOQUEADO("ERROR_USUARIO_BLOQUEADO"),

    /** ERROR_MAIL_NO_CONFIRMADO. */
    ERROR_MAIL_NO_CONFIRMADO("ERROR_MAIL_NO_CONFIRMADO"),

    /** ERROR_MODIFICACION_BILLETERA. */
    ERROR_MODIFICACION_BILLETERA("ERROR_MODIFICACION_BILLETERA"),

    /** ERROR_PRIMER_INGRESO1. */
    ERROR_PRIMER_INGRESO1("ERROR_PRIMER_INGRESO1"),

    /** ERROR_PRIMER_INGRESO2. */
    ERROR_PRIMER_INGRESO2("ERROR_PRIMER_INGRESO2"),

    /** ERROR_PRIMER_INGRESO3. */
    ERROR_PRIMER_INGRESO3("ERROR_PRIMER_INGRESO3"),

    /** * REASIGNACION_ALIAS_CBU **. */

    REASIGNACION_ALIAS_CBU("REASIGNACION_ALIAS_CBU"),

    /** ERROR_ALIAS_CBU_DUPLICADO **. */
    ERROR_ALIAS_CBU_DUPLICADO("ERROR_ALIAS_CBU_DUPLICADO"),

    /** * SIN_OPCIONES_ALIAS_CBU **. */
    SIN_OPCIONES_ALIAS_CBU("SIN_OPCIONES_ALIAS_CBU"),

    /** * SOLO_ALTA_ALIAS_CBU **. */
    SOLO_ALTA_ALIAS_CBU("SOLO_ALTA_ALIAS_CBU"),

    /** * ERROR_SERVICIO_ALIAS_CBU **. */
    ERROR_SERVICIO_ALIAS_CBU("ERROR_SERVICIO_ALIAS_CBU"),

    /** * ERROR_SERVICIO_ALIAS_CBU **. */
    ERROR_ELIMINAR_ALIAS_CBU_INTENTOS("ERROR_ELIMINAR_ALIAS_CBU_INTENTOS"),

    /** * ERROR_ELIMINAR_ALIAS_CBU **. */
    ERROR_ELIMINAR_ALIAS_CBU("ERROR_ELIMINAR_ALIAS_CBU"),

    /** * ERROR_SIN_TENENCIA **. */
    /** The error merlin generico. */
    ERROR_MERLIN_GENERICO("ERROR_MERLIN_GENERICO"),

    /** The error merlin dudcpa ai. */
    ERROR_MERLIN_DUDCPA_AI("ERROR_MERLIN_DUDCPA_AI"),

    /** The error merlin dudcpa li. */
    ERROR_MERLIN_DUDCPA_LI("ERROR_MERLIN_DUDCPA_LI"),

    /** The error merlin dudcpa ca. */
    ERROR_MERLIN_DUDCPA_CA("ERROR_MERLIN_DUDCPA_CA"),
    
    /** The duda domicilio altura. */
    DUDA_DOMICILIO_ALTURA("DUDA_DOMICILIO_ALTURA"),

    /** The duda domicilio localidad. */
    DUDA_DOMICILIO_LOCALIDAD("DUDA_DOMICILIO_LOCALIDAD"),

    /** The duda domicilio calle. */
    DUDA_DOMICILIO_CALLE("DUDA_DOMICILIO_CALLE"),

    /** * ERROR_SIN_TENENCIA **. */
    ERROR_CUENTA_SIN_TENENCIA("ERROR_CUENTA_SIN_TENENCIA"),

    /** The warning timeout. */
    WARNING_TIMEOUT("WARNING_TIMEOUT"),

    /** The error descarga comprobante. */
    ERROR_DESCARGA_COMPROBANTE("ERROR_DESCARGA_COMPROBANTE"),

    /** The error cambio clave usuario. */
    ERROR_CAMBIO_CLAVE_USUARIO("ERROR_CAMBIO_CLAVE_USUARIO"),

    /** The error total. */
    ERROR_TOTAL("ERROR_TOTAL"),

    /** The ERROR_PAGO_HABERES_CONTRATO_NO_ACEPTADO. */
    ERROR_PAGO_HABERES_CONTRATO_NO_ACEPTADO("ERROR_PAGO_HABERES_CONTRATO_NO_ACEPTADO"),

    /** The ERROR_PAGO_HABERES_FUERA_HORARIO. */
    ERROR_PAGO_HABERES_FUERA_HORARIO("ERROR_PAGO_HABERES_FUERA_HORARIO"),

    /** The ERROR_PAGO_HABERES_IMPORTE. */
    ERROR_PAGO_HABERES_IMPORTE("ERROR_PAGO_HABERES_IMPORTE"),

    /** The ERROR_PAGO_HABERES_PAGAR_EMPLEADO. */
    ERROR_PAGO_HABERES_PAGAR_EMPLEADO("ERROR_PAGO_HABERES_PAGAR_EMPLEADO"),

    /** The ERROR_PAGO_HABERES_PAGO_SIMPLE. */
    ERROR_PAGO_HABERES_PAGO_SIMPLE("ERROR_PAGO_HABERES_PAGO_SIMPLE"),

    /** The ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR. */
    ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR("ERROR_PAGO_HABERES_PAGO_MULTIPLE_NO_PERMITE_REINTENTAR"),

    /** The ERROR_PAGO_HABERES_PAGO_MULTIPLE_PERMITE_REINTENTAR. */
    ERROR_PAGO_HABERES_PAGO_MULTIPLE_PERMITE_REINTENTAR("ERROR_PAGO_HABERES_PAGO_MULTIPLE_PERMITE_REINTENTAR"),

    /** The alias inexistente. */
    ALIAS_INEXISTENTE("ALIAS_INEXISTENTE"),

    /** The alias con cta inactiva. */
    ALIAS_CON_CTA_INACTIVA("ALIAS_CON_CTA_INACTIVA"),

    /** The error detalle sin deudas. */
    ERROR_DETALLE_SIN_DEUDAS("ERROR_DETALLE_SIN_DEUDAS"),

    /** The alias eliminado. */
    ALIAS_ELIMINADO("ALIAS_ELIMINADO"),

    /** The error datos modificados del alias consulta. */
    ERROR_DATOS_MODIFICADOS_DEL_ALIAS_CONSULTA("ERROR_DATOS_MODIFICADOS_DEL_ALIAS_CONSULTA"),

    /** The moneda cta no coincide moneda seleccion. */
    MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION("MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION"),

    /** The error cambio de domicilio. */
    CAMBIO_DOMICILIO_ERROR_GENERICO("CAMBIO_DOMICILIO_ERROR_GENERICO"),

    /** The error editar destinatario rio invalido. */
    ERROR_EDITAR_DESTINATARIO_RIO_INVALIDO("ERROR_EDITAR_DESTINATARIO_RIO_INVALIDO"),

    /** The error cuenta ingresada inexistente santander. */
    ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER("ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER"),

    /** The error consulta titular. */
    ERROR_CONSULTA_TITULAR("ERROR_CONSULTA_TITULAR"),

    /** The moneda no coincide con moneda cbu. */
    MONEDA_NO_COINCIDE_CON_MONEDA_CBU("MONEDA_NO_COINCIDE_CON_MONEDA_CBU"),

    /** The error solicitud traspaso. */
    ERROR_SOLICITUD_TRASPASO("ERROR_SOLICITUD_TRASPASO"),

    /** The error solicitud traspaso intentos. */
    ERROR_SOLICITUD_TRASPASO_INTENTOS("ERROR_SOLICITUD_TRASPASO_INTENTOS"),

    /** The warning solicitud traspaso activo. */
    WARNING_SOLICITUD_TRASPASO_ACTIVO("WARNING_SOLICITUD_TRASPASO_ACTIVO"),

    /** The traspaso saldo insuficiente. */
    TRASPASO_SALDO_INSUFICIENTE("TRASPASO_SALDO_INSUFICIENTE"),

    /** The error traspaso manual intentos. */
    ERROR_TRASPASO_MANUAL_INTENTOS("ERROR_TRASPASO_MANUAL_INTENTOS"),

    /** The error traspaso manual. */
    ERROR_TRASPASO_MANUAL("ERROR_TRASPASO_MANUAL"),

    /** WARNING_TOTALES_TENENCIA. */
    WARNING_TOTALES_TENENCIA("WARNING_TOTALES_TENENCIA"),

    /** The adhesion resumen online feedback error. */
    ADHESION_RESUMEN_ONLINE_FEEDBACK_ERROR("ADHESION_RESUMEN_ONLINE_FEEDBACK_ERROR"),

    /** The adhesion resumen fisico feedback error. */
    ADHESION_RESUMEN_FISICO_FEEDBACK_ERROR("ADHESION_RESUMEN_FISICO_FEEDBACK_ERROR"),

    /** The error parcial llamada servicio resumen. */
    ERROR_PARCIAL_LLAMADA_SERVICIO_RESUMEN("ERROR_PARCIAL_LLAMADA_SERVICIO_RESUMEN"),

    /** The ERROR_SOLICITUD_RESUMEN. */
    ERROR_SOLICITUD_RESUMEN("ERROR_SOLICITUD_RESUMEN"),

    /** The ERROR_CONFIRMAR_VIAJE. */
	ERROR_CONFIRMAR_VIAJE("ERROR_CONFIRMAR_VIAJE"),

    /** The ERROR_SOLICITUD_RESUMEN_INTENTOS. */
    ERROR_SOLICITUD_RESUMEN_INTENTOS("ERROR_SOLICITUD_RESUMEN_INTENTOS"),

    /** The error preguntas seguridad generico. */
    PREGUNTAS_SEGURIDAD_ERROR_GENERICO("PREGUNTAS_SEGURIDAD_ERROR_GENERICO"),

    /** The WARNING_GRILLA_TENENCIA. */
    WARNING_GRILLA_TENENCIA("WARNING_GRILLA_TENENCIA"),

    /** The error altair prestamos. */
    ERROR_ALTAIR_PRESTAMOS("ERROR_ALTAIR_PRESTAMOS"),

    /** The WARNING_GRILLA_TENENCIA. */
    CAMBIO_DOMICILIO_ERROR_NO_EXISTE("DOMICILIO_NO_EXISTE"),

    /** Operacion exterior Rio Debito error consulta cuentas. */
    OPERACIONES_EXTERIOR_ERROR_CUENTAS("ERROR_CONSULTANDO_CUENTAS"),

    /** Operacion exterior Rio Debito error consulta tarjetas. */
    OPERACIONES_EXTERIOR_ERROR_TARJETAS("ERROR_CONSULTANDO_TARJETAS"),

    /** Operacion exterior Rio Debito error modifiacion de tarjeta. */
    OPERACIONES_EXTERIOR_ERROR_CMBTARJETA("ERROR_CAMBIO_TARJETA"),

    /** The warning servicio alta reimpresion. */
    WARNING_SERVICIO_ALTA_REIMPRESION("FALLO_SERVICIO_ALTA_REIMPRESION"),

    /** The codigo error lista resumenes error. */
    CODIGO_ERROR_LISTA_RESUMENES_ERROR("CODIGO_ERROR_LISTA_RESUMENES_ERROR"),

    /** The error pago tarjetas general. */
    ERROR_PAGO_TARJETAS_GENERAL("ERROR_PAGO_TARJETAS_GENERAL"),

    /** The alias no coincide moneda. */
    ALIAS_NO_COINCIDE_MONEDA("ALIAS_NO_COINCIDE_MONEDA"),

    /** The alias cuenta inactiva. */
    ALIAS_CUENTA_INACTIVA("ALIAS_CUENTA_INACTIVA"),

    /** The cliente no coincide moneda. */
    CLIENTE_NO_COINCIDE_MONEDA("CLIENTE_NO_COINCIDE_MONEDA"),

    /** The cuenta inexistente en banelco. */
    CUENTA_INEXISTENTE_EN_BANELCO("CUENTA_INEXISTENTE_EN_BANELCO"),

    /** The cuenta inexistente en banelco. */
    CONSULTA_SIN_LICITACIONES("CONSULTA_SIN_LICITACIONES"),

    /** The suscripcion licitacion. */
    ERROR_SUSCRIPCION_LICITACION("ERROR_SUSCRIPCION_LICITACION"),

    /** The suscripcion licitacion. */
    WARNING_SUSCRIPCION_LICITACION("WARNING_SUSCRIPCION_LICITACION"),

    /** The cuenta inexistente en banelco. */
    TRANFERENCIA_CON_RIESGO_ALTO("TRANFERENCIA_CON_RIESGO_ALTO"),

    /** The cuenta inexistente en banelco. */
    ERROR_FECHA_FILTROS_ERRONEA("ERROR_FECHA_FILTROS_ERRONEA"),

    /** The error no contemplado o que no tiene un codigo de error conocido. */
    ERROR_NO_CONTEMPLADO("ERROR_NO_CONTEMPLADO"),

    /** The error inicio mensaje y avisos. */
    ERROR_INICIO_MYA("ERROR_INICIO_MYA"),

    /** ERROR_FINALIZAR_LICITACION_CON_REINTENTO. */
    ERROR_FINALIZAR_LICITACION_CON_REINTENTO("ERROR_FINALIZAR_LICITACION_CON_REINTENTO"),

    /** ERROR_FINALIZAR_LICITACION_SIN_REINTENTO. */
    ERROR_FINALIZAR_LICITACION_SIN_REINTENTO("ERROR_FINALIZAR_LICITACION_SIN_REINTENTO"),

    /** SERVICIO_ERI_CAIDO. */
    SERVICIO_ERI_CAIDO("SERVICIO_ERI_CAIDO"),

    /** WARNING TBANCO a OBP. */
    WARNING_TBANCO_OBP("INTEGRACION_OBP"),

    /** ERROR_DESCARGA_DETALLE_CBU. */
    ERROR_DESCARGA_DETALLE_CBU("ERROR_DESCARGA_DETALLE_CBU"),

    /** The error no contemplado o que no tiene un codigo de error conocido. */
    ERROR_SIN_CUENTA_TITULOS("ERROR_SIN_CUENTA_TITULOS"),

    /** SIN_MENSAJES. */
    SIN_MENSAJES("SIN_MENSAJES"),

    /** SIN_MENSAJES. */
    SIN_DESTINOS("SIN_DESTINOS"),

    /** ERROR MODO NOCTURNO. */
    ERROR_MODO_NOCTURNO("ERROR_MODO_NOCTURNO"),

    /** ERROR_SERVICIO_MYA. */
    ERROR_SERVICIO_MYA("ERROR_SERVICIO_MYA"),

    /** Error generico de tracking de tarjetas. */
    ERROR_GENERICO_TRACKING_TARJETAS("ERROR_GENERICO_TRACKING_TARJETAS"),

    /** Error ERROR_FUERA_HORARIO_TRACKING. */
    ERROR_FUERA_HORARIO_TRACKING("ERROR_FUERA_HORARIO_TRACKING"),

    /** ERROR TRACKING SIN DATOS. */
    WARNING_TRACKING_TARJETAS_SIN_DATOS("WARNING_TRACKING_TARJETAS_SIN_DATOS"),

    /** The sin tarjetas compatibles. */
    SIN_TARJETAS_COMPATIBLES("SIN_TARJETAS_COMPATIBLES"),

    /** The warning sin operaciones. */
    WARNING_SIN_OPERACIONES("WARNING_SIN_OPERACIONES"),

    /** The edad para operar incorrecta. */
    EDAD_PARA_OPERAR_INCORRECTA("EDAD_PARA_OPERAR_INCORRECTA"),

    /**
     * The error mensaje aceptar, y continua por time out exception por BANELCO.
     */
    ERROR_MENSAJE_ACEPTAR("ERROR_MENSAJE_ACEPTAR"),

    /** The sin comprobantes limite. */
    SIN_COMPROBANTES_LIMITE("SIN_COMPROBANTES_LIMITE"),

    /** The error legales licitaciones vigentes. */
    ERROR_LEGALES_LICITACIONES_VIGENTES("ERROR_LEGALES_LICITACIONES_VIGENTES"),

    /** The error legales reversar compra venta. */
    ERROR_LEGALES_REVERSAR_ORDENES_COMPRA_VENTA("ERROR_LEGALES_REVERSAR_ORDENES_COMPRA_VENTA"),

    /** The error finalizar reversar orden compra venta. */
    ERROR_FINALIZAR_REVERSAR_ORDEN_COMPRA_VENTA("ERROR_FINALIZAR_REVERSAR_ORDEN_COMPRA_VENTA"),

    /** The tenencias detalle error. */
    TENENCIAS_DETALLE_ERROR("TENENCIAS_DETALLE_ERROR"),

    /** The error inicio prestamos sin linea disponible. */
    ERROR_INICIO_PRESTAMOS_SIN_LINEA_DISPONIBLE("ERROR_INICIO_PRESTAMOS_SIN_LINEA_DISPONIBLE"),

    /** The error saldo cuenta operativa. */
    ERROR_SALDO_CUENTA_OPERATIVA("ERROR_SALDO_CUENTA_OPERATIVA"),

    /** The limite transferencia diario superado. */
    LIMITE_TRANSFERENCIA_DIARIO_SUPERADO("LIMITE_TRANSFERENCIA_DIARIO_SUPERADO"),

    /** The cuenta bloqueada. */
    CUENTA_BLOQUEADA("CUENTA_BLOQUEADA"),

    /** The error finalizar transferencia banca privada con reintento. */
    ERROR_FINALIZAR_TRANSFERENCIA_BP_CON_REINTENTO("ERROR_FINALIZAR_TRANSFERENCIA_BP_CON_REINTENTO"),

    /** The The error finalizar transferencia banca privada sin reintento. */
    ERROR_FINALIZAR_TRANSFERENCIA_BP_SIN_REINTENTO("ERROR_FINALIZAR_TRANSFERENCIA_BP_SIN_REINTENTO"),

    /** The error confirmar orden con reintento. */
    ERROR_CONFIRMAR_ORDEN_CON_REINTENTO("ERROR_CONFIRMAR_ORDEN_CON_REINTENTO"),

    /** The error confirmar orden sin reintento. */
    ERROR_CONFIRMAR_ORDEN_SIN_REINTENTO("ERROR_CONFIRMAR_ORDEN_SIN_REINTENTO"),

    /** The error cabecera descuentocheques. */
    ERROR_CABECERA_DESCUENTOCHEQUES("ERROR_CABECERA_DESCUENTOCHEQUES"),

    /** The no calificado cabecera descuentocheques. */
    NO_CALIFICADO_CABECERA_DESCUENTOCHEQUES("NO_CALIFICADO_CABECERA_DESCUENTOCHEQUES"),

    /** The error servicio op precargadas con operaciones. */
    ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES("ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES"),

    /** The error op precargadas sin operaciones. */
    ERROR_OP_PRECARGADAS_SIN_OPERACIONES("ERROR_OP_PRECARGADAS_SIN_OPERACIONES"),

    /** The error historial filtro sin operaciones. */
    ERROR_HISTORIAL_FILTRO_SIN_OPERACIONES("ERROR_HISTORIAL_FILTRO_SIN_OPERACIONES"),

    /** The error servicio historico sin operaciones. */
    ERROR_SERVICIO_HISTORICO_SIN_OPERACIONES("ERROR_SERVICIO_HISTORICO_SIN_OPERACIONES"),

    /** The error servicio historico con operaciones. */
    ERROR_SERVICIO_HISTORICO_CON_OPERACIONES("ERROR_SERVICIO_HISTORICO_CON_OPERACIONES"),

    /** The error tasas indicativas. */
    ERROR_TASAS_INDICATIVAS("ERROR_TASAS_INDICATIVAS"),

    /** The error op precargadas no calificado. */
    ERROR_OP_PRECARGADAS_NO_CALIFICADO("ERROR_OP_PRECARGADAS_NO_CALIFICADO"),

    /** The warning poder compra. */
    WARNING_PODER_COMPRA("WARNING_PODER_COMPRA"),

    /** The error moneda liquidacion. */
    ERROR_MONEDA_LIQUIDACION("ERROR_MONEDA_LIQUIDACION"),

    /** The error orden no negociable. */
    ERROR_ORDEN_NO_NEGOCIABLE("ERROR_ORDEN_NO_NEGOCIABLE"),

    /** The error descarga excel. */
    ERROR_DESCARGA_EXCEL("ERROR_DESCARGA_EXCEL"),

    /** The titulo bloqueado. */
    TITULO_BLOQUEADO("TITULO_BLOQUEADO"),

    /** The error parcial consulta resumenes. */
    ERROR_PARCIAL_CONSULTA_RESUMENES("ERROR_PARCIAL_CONSULTA_RESUMENES"),

    /** The Warning parcial inversiones consolidado. */
    WARNING_PARCIAL_INVERSIONES_CONSOLIDADO(" WARNING_PARCIAL_INVERSIONES_CONSOLIDADO"),

    /** The Warning parcial tenencia consolidada. */
    WARNING_PARCIAL_TENENCIA_CONSOLIDADA(" WARNING_PARCIAL_TENENCIA_CONSOLIDADA"),

    /** The Warning parcial plazo fijo. */
    WARNING_PARCIAL_PLAZO_FIJO("WARNING_PARCIAL_PLAZO_FIJO"),

    /** The Warning parcialtitulos valores. */
    WARNING_PARCIAL_TITULOS_VAORES(" WARNING_PARCIAL_TITULOS_VAORES"),

    /** The Warning parcialtitulos valores. */
    WARNING_PARCIAL_FONDOS(" WARNING_PARCIAL_FONDOS"),

    /** DEBINWS_ERROR_GENERICO. */
    DEBINWS_ERROR_GENERICO("DEBINWS_ERROR_GENERICO"),

    /** DEBINWS_ERROR_GENERICO. */
    DEBINWS_ERROR_TIMEOUT("DEBINWS_ERROR_TIMEOUT"),

    /** DEBINWS_WARNING_SIN_DATOS. */
    DEBINWS_WARNING_SIN_DATOS("DEBINWS_WARNING_SIN_DATOS"),

    /** DEBINWS_WARNING_SIN_DATOS_FILTRO. */
    DEBINWS_WARNING_SIN_DATOS_FILTRO("DEBINWS_WARNING_SIN_DATOS_FILTRO"),

    /** DEBINWS_ERROR_CUIT. */
    DEBINWS_ERROR_CUIT("DEBINWS_ERROR_CUIT"),

    /** DEBINWS_ERROR_CONTRACARGO. */
    DEBINWS_ERROR_CONTRACARGO("DEBINWS_ERROR_CONTRACARGO"),

    /** DEBINWS_ERROR_CONTRACARGO. */
    DEBINWS_ERROR_COORDINADOR("DEBINWS_ERROR_COORDINADOR"),

    /** DEBINWS_ERROR_CNSDEBIN. */
    DEBINWS_ERROR_CNSDEBIN("DEBINWS_ERROR_CNSDEBIN"),
    
    /** DEBINWS_ERROR_ADHERIR_RECURRENCIA. */
    DEBINWS_ERROR_ADHERIR_RECURRENCIA("DEBINWS_ERROR_ADHERIR_RECURRENCIA"),

    /** DEBINWS_ERROR_PEDIDO_RECURRENCIA. */
    DEBINWS_ERROR_PEDIDO_RECURRENCIA("DEBINWS_ERROR_PEDIDO_RECURRENCIA"),
    
    /** The error sin reintento interno. */
    ERROR_INTERNO_SIN_REINTENTO("ERROR_INTERNO_SIN_REINTENTO"),

    /** The aviso proxima cuota no disponible. */
    AVISO_PROXIMA_CUOTA_NO_DISPONIBLE("AVISO_PROXIMA_CUOTA_NO_DISPONIBLE"),

	/** The error solicitud en curso. */
	ERROR_SOLICITUD_EN_CURSO("ERROR_SOLICITUD_EN_CURSO"),

    /** DEBINWS_WARNING_LISTA_ERROR_PARCIAL. */
    DEBINWS_WARNING_LISTA_ERROR_PARCIAL("DEBINWS_WARNING_LISTA_ERROR_PARCIAL"),

    /** The error resolucion 30 2017. */
    ERROR_RESOLUCION_30_2017("ERROR_RESOLUCION_30_2017"),

    /** ELIMINAR DEBIN. */
    DEBINWS_ERROR_ELIMINAR("DEBINWS_ERROR_ELIMINAR"),

    /** DEBINWS_ERROR_ADHESION_COMPRADOR. */
    DEBINWS_ERROR_ADHESION_COMPRADOR("DEBINWS_ERROR_ADHESION_COMPRADOR"),

    /** DEBINWS_ERROR_PAGAR. */
    DEBINWS_ERROR_PAGAR("DEBINWS_ERROR_PAGAR"),

    /** DEBINWS_RECHAZAR_DEBIN_ERROR. */
    DEBINWS_RECHAZAR_DEBIN_ERROR("DEBINWS_RECHAZAR_DEBIN_ERROR"),

    /** DEBINWS_ERROR_ACTZKLIMIT. */
    DEBINWS_ERROR_ACTZKLIMIT("DEBINWS_ERROR_ACTZKLIMIT"),

    /** ERROR_GRILLA_INFOMERCADO. */
    ERROR_GRILLA_INFOMERCADO("ERROR_GRILLA_INFOMERCADO"),

    /** The usuario o tarjeta invalida. */
    USUARIO_O_TARJETA_INVALIDA("USUARIO_O_TARJETA_INVALIDA"),

    /** The tarjeta inhabilitada o inexistente. */
    TARJETA_INHABILITADA_O_INEXISTENTE("TARJETA_INHABILITADA_O_INEXISTENTE"),

    /** The sin pagos informados. */
    SIN_PAGOS_INFORMADOS("SIN_PAGOS_INFORMADOS"),

    /** The error servicio calificacion. */
    ERROR_SERVICIO_CALIFICACION("ERROR_SERVICIO_CALIFICACION"),

    /** The descuento cheques eliminacion. */
    DESCUENTO_CHEQUES_ELIMINACION("DESCUENTO_CHEQUES_ELIMINACION"),

    /** The reimpresion tarjetas menor quince dias. */
    REIMPRESION_TARJETAS_MENOR_QUINCE_DIAS("REIMPRESION_TARJETAS_MENOR_QUINCE_DIAS"),

    /** The simulacion alta cheques rechazado. */
    SIMULACION_ALTA_CHEQUES_RECHAZADO("SIMULACION_ALTA_CHEQUES_RECHAZADO"),

    /** The simulacion alta todos rechazados. */
    SIMULACION_ALTA_TODOS_RECHAZADOS("SIMULACION_ALTA_TODOS_RECHAZADOS"),

    /** The simulacion alta algunos rechazados. */
    SIMULACION_ALTA_ALGUNOS_RECHAZADOS("SIMULACION_ALTA_ALGUNOS_RECHAZADOS"),

    /** The saldo insuficiente tarjeta credito. */
    SALDO_INSUFICIENTE_TARJETA_CREDITO("SALDO_INSUFICIENTE_TARJETA_CREDITO"),

    /** The simulacion tasas confirmacion rechazadas. */
    SIMULACION_TASAS_CONFIRMACION_RECHAZADAS("SIMULACION_TASAS_CONFIRMACION_RECHAZADAS"),

    /** The error fuera horario tasas. */
    ERROR_FUERA_HORARIO_TASAS("ERROR_FUERA_HORARIO_TASAS"),

    /** The simulacion tasas algunos rechazados. */
    SIMULACION_TASAS_ALGUNOS_RECHAZADOS("SIMULACION_TASAS_ALGUNOS_RECHAZADOS"),

    /** The cambio domicilio datos contacto. */
    CAMBIO_DOMICILIO_DATOS_CONTACTO("DATOS_DOMICILIO_DATOS_CONTACTO"),

    /** The marca viajero error sin viajes. */
    MARCA_VIAJERO_SIN_VIAJES("ERROR_SIN_VIAJES"),
    
    /** The error total cambio domicilio. */
    ERROR_TOTAL_CAMBIO_DOMICILIO("ERROR_TOTAL_CAMBIO_DOMICILIO"),

    /** The error parcial cambio domicilio. */
    ERROR_PARCIAL_CAMBIO_DOMICILIO("ERROR_PARCIAL_CAMBIO_DOMICILIO"),

    /** The cambio domicilio dom privado. */
    CAMBIO_DOMICILIO_DOM_PRIVADO("CAMBIO_DOMICILIO_DOM_PRIVADO"),

    /** The cambio domicilio dom prendario. */
    CAMBIO_DOMICILIO_DOM_PRENDARIO("CAMBIO_DOMICILIO_DOM_PRENDARIO"),

    /** The grilla inversiones parcial. */
    GRILLA_INVERSIONES_PARCIAL("GRILLA_INVERSIONES_PARCIAL"),

    /** The error generico. */
    ERROR_LEGALES("ERROR_LEGALES"),

    /** DEBINWS_SOLICITUDES_ERROR_TOKEN. */
    DEBINWS_SOLICITUDES_ERROR_TOKEN("DEBINWS_SOLICITUDES_ERROR_TOKEN"), 
    
    /** DEBINWS_ADHESION_ERROR_TOKEN. */
    DEBINWS_ADHESION_ERROR_TOKEN("DEBINWS_ADHESION_ERROR_TOKEN"),     
    
    /** DEBINWS_RECURRENCIA_ERROR_TOKEN. (PREAUTORIZACION) */
    DEBINWS_RECURRENCIA_ERROR_TOKEN("DEBINWS_RECURRENCIA_ERROR_TOKEN"),     
    
    /** VENDEDOR NO ADHERIDO. */
    DEBINWS_NO_ADHERIDO("DEBINWS_NO_ADHERIDO"),
    
    /** ERROR VALIDACION CBU DEBINWS. */
    DEBINWS_ERROR_VALIDACION_ALIAS_CBU("DEBINWS_ERROR_VALIDACION_ALIAS_CBU"), 
    
    /** ERROR SOLICITUD DE DEBIN. */
    DEBINWS_ERROR_SOLICITUD("DEBINWS_ERROR_SOLICITUD"),
    
    /** The error no hay cuentas titulos. */
    ERROR_NO_HAY_CUENTAS_TITULOS("ERROR_NO_HAY_CUENTAS_TITULOS"),
    /** The marca viajero error sin tarjetas. */
    MARCA_VIAJERO_ERROR_SIN_TARJETAS("ERROR_SIN_TARJETAS"),

    /** The marca viajero error sin tarjetas internacionales. */
    MARCA_VIAJERO_ERROR_SIN_TARJETAS_INTERNACIONALES("ERROR_SIN_TARJETAS_INTERNACIONALES"),
    
    /** The marca viajero ERROR_MARCACION_MANUAL. */
    ERROR_MARCACION_MANUAL("ERROR_MARCACION_MANUAL"),
    
    /** The marca viajero ERROR_MARCACION_REINTENTAR. */
    ERROR_MARCACION_REINTENTAR("ERROR_MARCACION_REINTENTAR"),
    
    /** The marca viajero ERROR_MARCACION. */
    ERROR_MARCACION("ERROR_MARCACION"),

    /** The marca viajero ERROR_MARCACION. */
    ERROR_ELIMINACION_REINTENTAR("MARCA_VIAJERO_ERROR_ELIMINACION_REINTENTAR"),
    
    /** The error eliminacion. */
    ERROR_ELIMINACION("MARCA_VIAJERO_ERROR_ELIMINACION"),
    
    /** The prestamo sin cuotas pagas. */
    PRESTAMO_SIN_CUOTAS_PAGAS("PRESTAMO_SIN_CUOTAS_PAGAS"),

	/** The error documentacion respaldatoria insuficiente. */
	ERROR_DOCUMENTACION_RESPALDATORIA_INSUFICIENTE("DOCUMENTACION_RESPALDATORIA_INSUFICIENTE"),
	
	/** The usuario actual incorrecto. */
	USUARIO_ACTUAL_INCORRECTO("USUARIO_ACTUAL_INCORRECTO"),

	/** The transferencia fuera de horario. */
	TRANSFERENCIA_FUERA_DE_HORARIO("TRANSFERENCIA_FUERA_DE_HORARIO"),
	
	/** The sin resultados. */
	SIN_RESULTADOS("SIN_RESULTADOS"),
	
	/** The error time out. */
	ERROR_TIME_OUT("ERROR_TIME_OUT"),
	
	/** The warning rentabilidad total. */
	WARNING_RENTABILIDAD_TOTAL("WARNING_RENTABILIDAD_TOTAL"),

    /** The no existe deuda. */
    NO_EXISTE_DEUDA("NO_EXISTE_DEUDA"),
    
    /** The moneda incorrecta. */
    MONEDA_INCORRECTA("MONEDA_INCORRECTA"),

    /** The error CVU DOLARES NO HABILITADO. */
    ERROR_CVU_DOLARES_NO_HABILITADO("ERROR_CVU_DOLARES_NO_HABILITADO"),

    /** The error tarjetas servicios de inversiones bloqueadas. */
    ERROR_SERVICIOS_BLOQUEADOS("ERROR_SERVICIOS_BLOQUEADOS"),

    /** The error sin tarjetas servicios de inversion. */
    ERROR_SIN_SERVICIOS("ERROR_SIN_SERVICIOS"),
	
	/** The error alta modificacion turno dia con turno. */
	ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO("ERROR_ALTA_MODIFICACION_TURNO_DIA_CON_TURNO"),

    /** The error dest no verificado err. */
    DEST_NO_VERIFICADO_ERR("DEST_NO_VERIFICADO_ERR"),
	
	/** The login deshabilitado. */
    LOGIN_DESHABILITADO("LOGIN_DESHABILITADO"),
    
    SERVICIO_FONDOS_DESHABILITADO("SERVICIO_DESHABILITADO"),
    
    /** The warning rentabilidad total bpriv. */
    WARNING_RENTABILIDAD_TOTAL_BPRIV("WARNING_RENTABILIDAD_TOTAL_BPRIV"),
    
    /** The warning rentabilidad total rtl. */
    WARNING_RENTABILIDAD_TOTAL_RTL("WARNING_RENTABILIDAD_TOTAL_RTL"), 
    
    ALGUN_SERVICIO_ERRONEO ("ALGUN_SERVICIO_ERRONEO"),
     
    /** The warning sin inversiones. */
    WARNING_SIN_INVERSIONES("WARNING_SIN_INVERSIONES"),
    
    /** The warning sin inversiones rtl. */
    WARNING_SIN_INVERSIONES_RTL("WARNING_SIN_INVERSIONES_RTL"),
    
    /** The warning sin inversiones bpriv. */
    WARNING_SIN_INVERSIONES_BPRIV("WARNING_SIN_INVERSIONES_BPRIV"),
    
    /** The warning parcial graficos dashboard. */
    WARNING_PARCIAL_GRAFICOS_DASHBOARD("WARNING_PARCIAL_GRAFICOS_DASHBOARD"),
    
    /** The error CUENTA_MIGRADA. */
    ERROR_CUENTA_MIGRADA("ERROR_CUENTA_MIGRADA"),

	/** The error todopago sin cuentas operativas. */
	ERROR_TODOPAGO_SIN_CUENTAS_OPERATIVAS("ERROR_TODOPAGO_SIN_CUENTAS_OPERATIVAS"),
	
	ERROR_ALTA_ADHESION_MAPS("ERROR_ALTA_ADHESION_MAPS"),
	
	ERROR_BAJA_ADHESION_MAPS("ERROR_BAJA_ADHESION_MAPS"),
	
	WARNING_SIN_SUSCRIPCIONES("WARNING_SIN_SUSCRIPCIONES"),
	CUENTA_SIN_OPERAR_MAS_180_DIAS("CUENTA_SIN_OPERAR_MAS_180_DIAS"),
	
	EXCEDE_LIMITE_DE_CANAL("EXCEDE_LIMITE_DE_CANAL"),
	
	CUENTA_NO_FIRMADA("CUENTA_NO_FIRMADA"),
	
	CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA("CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA"),
	
	SE_ENCUENTRA_FUERA_DEL_TUNEL("SE_ENCUENTRA_FUERA_DEL_TUNEL"),
	
	CUENTA_TITULOS_BLOQUEADA("CUENTA_TITULOS_BLOQUEADA"),
	
	TENENCIA_BLOQUEADA("TENENCIA_BLOQUEADA"),
	
	CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES("CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES"),
	
	SUPERA_TIEMPO_DE_ESPERA("SUPERA_TIEMPO_DE_ESPERA"),
	
	NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS("NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS"),
	
	LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE_TRIVIAL("LOGIN_ERROR_FEEDBACK_USUARIO_CAMBIO_PENDIENTE_TRIVIAL"),

	OTROS_COMPRA_VENTA_CUENTA_TITULOS("OTROS_COMPRA_VENTA_CUENTA_TITULOS"),

	/** The error validacion telefono valsgitel */
	ERROR_VALIDACION_TELEFONO_VALSGITEL("ERROR_VALIDACION_TELEFONO_VALSGITEL"),
	
	WARNING_USUARIO_ADVANCE("WARNING_USUARIO_ADVANCE"),
	
	WARNING_USUARIO_SIN_PRODUCTOS_DISPONIBLES("WARNING_USUARIO_SIN_PRODUCTOS_DISPONIBLES"),
	
	/** The test inversor no realizado. */
	TEST_INVERSOR_NO_REALIZADO("TEST_INVERSOR_NO_REALIZADO"),
	
	/** The error NO_ES_PERSONA_FISICA */
	NO_ES_PERSONA_FISICA("NO_ES_PERSONA_FISICA"),

	/** The error ERROR_PIN_BANELCO */
	ERROR_PIN_BANELCO("ERROR_PIN_BANELCO"),
	
	/** The error ERROR_PIN_OTP */
	ERROR_PIN_OTP("ERROR_PIN_OTP"),

	/** The error ERROR_LOGICO_OTP */
	ERROR_LOGICO_OTP("ERROR_LOGICO_OTP"),
	
	/** The error ERROR_SERVICIO_PREMIACIONES */
	ERROR_SERVICIO_PREMIACIONES("ERROR_SERVICIO_PREMIACIONES"),
	
	/** The error WARNING_SIN_CHANCES */
	WARNING_SIN_CHANCES("WARNING_SIN_CHANCES"),

	WARNING_TENENCIA_MENSAJE_INFO("WARNING_TENENCIA_MENSAJE_INFO"),
	
	WARNING_CAJA_VACIA("WARNING_CAJA_VACIA"),
	
	/** The error ECHEQ_INGRESO. */
	ECHEQ_INGRESO("ECHEQ_INGRESO"),
	
    /** The error ECHEQ_NO_ADHERIDO. */
	ECHEQ_NO_ADHERIDO("ECHEQ_NO_ADHERIDO"),
	
    /** The  error ECHEQ_ERROR_ACEPTACION_CONTRATO. */
	ECHEQ_ERROR_ACEPTACION_CONTRATO("ECHEQ_ERROR_ACEPTACION_CONTRATO"),
	
    /** The warning ECHEQ_WARNING_TOTALES_GRILLA. */
	ECHEQ_WARNING_TOTALES_GRILLA("ECHEQ_WARNING_TOTALES_GRILLA"),

    /** The warning ECHEQ_WARNING_SIN_DATOS_GRILLA. */
	ECHEQ_WARNING_SIN_DATOS_GRILLA("ECHEQ_WARNING_SIN_DATOS_GRILLA"),

	/** The warning ECHEQ_WARNING_SIN_DATOS_GRILLA_ERROR_TOTALES. */
	ECHEQ_WARNING_SIN_DATOS_GRILLA_ERROR_TOTALES("ECHEQ_WARNING_SIN_DATOS_GRILLA_ERROR_TOTALES"),
	
	/** The warning ECHEQ_ERROR_CONFIRMAR_OPERACION. */
	ECHEQ_ERROR_CONFIRMAR_OPERACION("ECHEQ_ERROR_CONFIRMAR_OPERACION"),

	/** The echeq ingreso operacion sin cuentas. */
	ECHEQ_INGRESO_OPERACION_SIN_CUENTAS("ECHEQ_INGRESO_OPERACION_SIN_CUENTAS"),

	/** The echeq warning validar beneficiario. */
	ECHEQ_WARNING_VALIDAR_BENEFICIARIO("ECHEQ_WARNING_VALIDAR_BENEFICIARIO"),
	

	WARNING_NO_HAY_RESUMENES_DISPONIBLES("WARNING_NO_HAY_RESUMENES_DISPONIBLES"),

	WARNING_NO_HAY_COMPROBANTES_TV_DISPONIBLES("WARNING_NO_HAY_COMPROBANTES_TV_DISPONIBLES"),
	
	ERROR_FECHAS_COMPROBANTES_TV_PRIVADA("ERROR_FECHAS_COMPROBANTES_TV_PRIVADA"),
	
	WARNING_DESCARGA_PARCIAL_PDF("WARNING_DESCARGA_PARCIAL_PDF"),
	
	ERROR_DESCARGA_TOTAL_PDF("ERROR_DESCARGA_TOTAL_PDF"),
	
	/** The error ERROR_CAMPANIA_SUSCRIPCION */
	ERROR_CAMPANIA_SUSCRIPCION("ERROR_CAMPANIA_SUSCRIPCION"),

	BLANQUEO_PIN_PUEDE_REINTENTAR("BLANQUEO_PIN_PUEDE_REINTENTAR"),
	
	BLANQUEO_PIN_REINTENTOS_AGOTADOS("BLANQUEO_PIN_REINTENTOS_AGOTADOS"),
	
	BLANQUEO_PIN_TIME_OUT("BLANQUEO_PIN_TIME_OUT"),

	/** The error ERROR_LINEA_CREDITICIA_SIN_MONTO_PERMITIDO */
	ERROR_LINEA_CREDITICIA_SIN_MONTO_PERMITIDO("ERROR_LINEA_CREDITICIA_SIN_MONTO_PERMITIDO"),
	
	ERROR_FUERAHORARIO_ESPECIE("ERROR_FUERAHORARIO_ESPECIE"),
	
	ERROR_SERVICIO_CERRADO_EXCITI("ERROR_SERVICIO_CERRADO_EXCITI"),

    /** The error finalizar promesa de pago sin reintento. */
    ERROR_FINALIZAR_PROMESA_PAGO_SIN_REINTENTO("ERROR_FINALIZAR_PROMESA_PAGO_SIN_REINTENTO"),

    /** The error finalizar promesa de pago con reintento. */
    ERROR_FINALIZAR_PROMESA_PAGO_CON_REINTENTO("ERROR_FINALIZAR_PROMESA_PAGO_CON_REINTENTO"),
	
	ERROR_RESCATE_FONDO_CITI_COMPASS("ERROR_RESCATE_FONDO_CITI_COMPASS"),
	
	TENENCIA_NO_DISPONIBLE_PARA_OPERAR("TENENCIA_NO_DISPONIBLE_PARA_OPERAR"),
	
	NO_CORRESPONDE_REINTENTO("NO_CORRESPONDE_REINTENTO"),
	
	ERROR_BOTON_PANICO("ERROR_BOTON_PANICO"),
	
	CODIGO_ONLINE_BCRA("CODIGO_ONLINE_BCRA"),
	
	ERROR_SIZE_EXCEDIDO("ERROR_SIZE_EXCEDIDO"),
	
	ERROR_EXTENSION_INVALIDA("ERROR_EXTENSION_INVALIDA"),
	
	ERROR_VALIDAR_ARCHIVO_VIRUS("ERROR_VALIDAR_ARCHIVO_VIRUS"),
	
	ERROR_ARCHIVO_VACIO("ERROR_ARCHIVO_VACIO"), 
	
	ERROR_ADJUNTAR_ARCHIVO("ERROR_ADJUNTAR_ARCHIVO"),
	
	ERROR_ELIMINAR_ARCHIVO_ADJUNTO("ERROR_ELIMINAR_ARCHIVO_ADJUNTO"),
	
	ERROR_DESCARGAR_ARCHIVO("ERROR_DESCARGAR_ARCHIVO"),
		
	WARNING_LICITACIONES_CANJE("WARNING_LICITACIONES_CANJE"),

	WARNING_SUPERA_LIMITE_CHEQUES("WARNING_SUPERA_LIMITE_CHEQUES"),
	
	NO_CONTEMPLADO_TJ_ADICIONAL("NO_CONTEMPLADO_TJ_ADICIONAL"),

	ERROR_OPERADOR_TEL_CON_REINTENTOS("ERROR_OPERADOR_TEL_CON_REINTENTOS"),
	
	ERROR_FECHA_MINIMA_PRECANCELACION("ERROR_FECHA_MINIMA_PRECANCELACION"),
	
	ERROR_PRECANCELACION_SOLICITADA_PREVIAMENTE("ERROR_PRECANCELACION_SOLICITADA_PREVIAMENTE"),
	
	ERROR_FECHA_MAXIMA_PRECANCELACION_SUPERADA("ERROR_FECHA_MAXIMA_PRECANCELACION_SUPERADA"),

	ERROR_PRESTAMOS_FOREX("ERROR_PRESTAMOS_FOREX"),
	
	ERROR_OPERADOR_TEL_SIN_REINTENTOS("ERROR_OPERADOR_TEL_CON_REINTENTOS"),
	
	ERROR_CARGAR_NUEVO_CELULAR("ERROR_CARGAR_NUEVO_CELULAR"),

	ERROR_ELIMINAR_CELULAR("ERROR_ELIMINAR_CELULAR"),

	ERROR_ACTUALIZAR_CELULAR("ERROR_ACTUALIZAR_CELULAR"),

	ERROR_OBTENER_CELULARES("ERROR_OBTENER_CELULAR"),
	/** The ERROR_GETNET_COD_RETORNO. */
	ERROR_GETNET_COD_RETORNO("ERROR_GETNET_COD_RETORNO"),
	
	/** The ERROR_GETNET_COD_RETORNO. */
	ERROR_GETNET_SUPERA_REINTENTOS("ERROR_GETNET_SUPERA_REINTENTOS"),
	
	/** The ERROR_GETNET_EMAIL_EXISTENTE. */
	ERROR_GETNET_EMAIL_EXISTENTE("ERROR_GETNET_EMAIL_EXISTENTE"),

    /** The ERROR_GETNET_VALIDATION. */
    ERROR_GETNET_VALIDATION("ERROR_GETNET_VALIDATION"),

    /** The ERROR_GETNET_EMAIL_VALIDATION. */
    ERROR_GETNET_EMAIL_VALIDATION("ERROR_GETNET_EMAIL_VALIDATION"),
	
	EXTRACCION_EFECTIVO_ERROR_REINTENTOS_AGOTADOS("EXTRACCION_EFECTIVO_ERROR_REINTENTOS_AGOTADOS"),

	EXTRACCION_EFECTIVO_ERROR_PERMITE_REINTENTOS("EXTRACCION_EFECTIVO_ERROR_PERMITE_REINTENTOS"),

	/** The error proceso orden venta. */
	ERROR_PROCESO_ORDEN_VENTA("ERROR_PROCESO_ORDEN_VENTA"),

	ERROR_BANELCO("ERROR_BANELCO"),
	
	OPERACION_NO_PERMITIDA("OPERACION_NO_PERMITIDA"),

	/** The error transferencia comex limite. */
	ERROR_TRANSFERENCIA_COMEX_LIMITE("ERROR_TRANSFERENCIA_COMEX_LIMITE"),
	
	/** prestamo ya solicitado. */
	ERROR_PRESTAMO_TASA_SUBSIDIADA_SOLICITADO("ERROR_PRESTAMO_TASA_SUBSIDIADA_SOLICITADO"),
    ERROR_ALTA_PRESTAMO_TASA_SUBSIDIADA("ERROR_ALTA_PRESTAMO_TASA_SUBSIDIADA"),

    PRESTAMO_CANCELACION_ERROR_SERVICIO("ERROR_SERVICIO"),
    PRESTAMO_CANCELACION_ERROR_CUOTA_ADELANTADA("ERROR_CUOTA_ADELANTADA"),
    PRESTAMO_CANCELACION_ERROR_CUOTA_IMPAGA("ERROR_CUOTA_IMPAGA"),
    PRESTAMO_CANCELACION_ERROR_SALDO_INSUFICIENTE("ERROR_SALDO_INSUFICIENTE"),
    PRESTAMO_CANCELACION_ERROR_FUERA_HORARIO("ERROR_FUERA_DE_HORARIO"),

	/** The queue wait. */
	QUEUE_WAIT ("QUEUE_WAIT"),

	/** The queue error turn not found. */
	QUEUE_ERROR_TURN_NOT_FOUND ("QUEUE_ERROR_TURN_NOT_FOUND"),

	ERROR_SOLICITUD_DEVOLUCION_DEBITO_AUTOMATICO_CREADA("ERROR_SOLICITUD_DEVOLUCION_DEBITO_AUTOMATICO_CREADA"), 
	
	ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS("ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS"), 
	ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO("ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO"), 
	ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ("ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ"), 
	ERROR_OPERA_USD_SUB_ANSES("ERROR_OPERA_USD_SUB_ANSES"), 
	ERROR_USD_EXCEDE_TRJ_Y_ATESORAMIENTO("ERROR_USD_EXCEDE_TRJ_Y_ATESORAMIENTO"), 
	ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL("ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL"),
	ERROR_OPERA_USD_VENDE_BONOS_USD("ERROR_OPERA_USD_VENDE_BONOS_USD"),
	ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2("ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO"), 
	ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3("ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3"), 
	ERROR_INHABILITADO_BCRA("ERROR_OPERA_USD_ERROR_INHABILITADO_BCRA"),
	ERROR_OPERA_USD_FUNCIONARIO_PUBLICO("ERROR_OPERA_USD_FUNCIONARIO_PUBLICO"),
	ERROR_OPERA_USD_CERTIFICACION_POSITIVA("ERROR_OPERA_USD_CERTIFICACION_POSITIVA"),
	ERROR_INHABILITADO_BCRA_AMEC_21("ERROR_OPERA_USD_ERROR_INHABILITADO_BCRA_AMEC_21"),
	ERROR_INHABILITADO_BCRA_AMEC_43("ERROR_OPERA_USD_ERROR_INHABILITADO_BCRA_AMEC_43"), 
	ERROR_GENERICO_OPEN_STACK("ERROR_GENERICO_OPEN_STACK"),
	//Censo
	CENSO_ECONOMICO_NO_REALIZADO("ERROR_CENSO_ECONOMICO_NO_REALIZADO"),

	ERROR_INHABILITADO_BCRA_AMEC_42("ERROR_OPERA_USD_ERROR_INHABILITADO_BCRA_AMEC_42"),

	ERROR_DEBIN_YA_EXISTE("ERROR_DEBIN_YA_EXISTE"),
	
	ERROR_EJECUCION_CENSO_ECONOMICO("ERROR_EJECUCION_CENSO_ECONOMICO"),

    SOLICITUD_TOKEN_PRESTAMO("SOLICITUD_TOKEN_PRESTAMO"),

    TOKEN_PRESTAMO_ERROR("TOKEN_PRESTAMO_ERROR"),
    
	//BAJA CUENTA
	ERROR_BAJA_CUENTA("ERROR_BAJA_CUENTA"),
	INVALIDANTES_ONLINE("INVALIDANTES_ONLINE"),
    INVALIDANTES_SALDO_POSITIVO("INVALIDANTES_SALDO_POSITIVO"),
	INVALIDANTES_SUCURSAL("INVALIDANTES_SUCURSAL"),
	BAJA_CUENTAS_ERROR_SIN_PRODUCTOS("BAJA_CUENTAS_ERROR_SIN_PRODUCTOS"),
	OPERACION_DESHABILITADA("OPERACION_DESHABILITADA"),
	NUP_NO_HABILITADO("NUP_NO_HABILITADO"),
	OPERACION_SEGMENTO_NO_HABILITADO("OPERACION_SEGMENTO_NO_HABILITADO"),

    ERROR_SERVICIO_AFIP("ERROR_SERVICIO_AFIP"),
    ERROR_DEUDA_PREVISIONAL("ERROR_DEUDA_PREVISIONAL"),
    ERROR_DEUDA_PREVISIONAL_Y_CENSO("ERROR_DEUDA_PREVISIONAL_Y_CENSO"),
    ERROR_SERVICIO_CENSO("ERROR_SERVICIO_CENSO"),
    ERROR_CVU_PESOS_NO_HABILITADO("ERROR_CVU_PESOS_NO_HABILITADO"),
    LOGIN_USUARIO_CORRECTO_CLAVE_INCORRECTA("LOGIN_USUARIO_CORRECTO_CLAVE_INCORRECTA"),

    //Consent
    CONSENT_USER_NOT_FOUND("CONSENT_USER_NOT_FOUND"),
    ERROR_OTP_VENCIDO("ERROR_OTP_VENCIDO");

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new tipo error.
     *
     * @param descripcion
     *            the descripcion
     */
    TipoError(String descripcion) {
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

}
