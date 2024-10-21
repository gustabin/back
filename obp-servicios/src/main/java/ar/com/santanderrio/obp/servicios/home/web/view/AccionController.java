/*
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import org.apache.commons.lang3.StringUtils;


// TODO: Auto-generated Javadoc
/**
 * The Enum AccionController.
 */
public enum AccionController {
    /** The ir consulta. */
    IR_CONSULTA("irConsulta"),
    /** The ir inicio cuentas. */
    IR_INICIO_CUENTAS("irInicioCuentas"),
    /** The ir inicio cuentas banca privada. */
    IR_INICIO_CUENTAS_BANCA_PRIVADA("irInicioBancaPrivada"),
    /** The ir inicio tarjetas. */
    IR_INICIO_TARJETAS("irInicioTarjetas"),
    /** The ir inicio prestamos consultas. */
    IR_INICIO_PRESTAMOS_CONSULTAS("irInicioPrestamosConsultas"),
    /** The ir inicio seguros. */
    IR_INICIO_SEGUROS("irInicioSeguros"),
    /** The ir inicio ahorros y beneficios. */
    IR_INICIO_AHORROS_Y_BENEFICIOS("irInicioAhorrosBeneficios"),
    /** The ir inicio comprobantes. */
    IR_INICIO_COMPROBANTES("irInicioComprobantes"),
    /** The ir inicio tarjeta monedero. */
    IR_INICIO_TARJETA_MONEDERO("irInicioTarjetaMonedero"),
    /** The ir inicio resumen impositivo. */
    IR_INICIO_RESUMEN_IMPOSITIVO("irInicioResumenImpositivo"),

    /** The ir inversiones. */
    IR_INVERSION("irInversion"),
    /** The ir inicio tenencia consolidada. */
    IR_INICIO_TENENCIA_CONSOLIDADA("irInicioTenenciaConsolidada"),
    /** The ir inicio plazos fijos. */
    IR_INICIO_PLAZOS_FIJOS("irInicioPlazosFijos"),
    /** The ir inicio fondos comunes inversion. */
    IR_INICIO_FONDOS_COMUNES_INVERSION("irInicioFondosComunesInversion"),
    /** The ir inicio titulos. */
    IR_INICIO_TITULOS("irInicioTitulos"),
    /** The ir inicio dolares. */
    IR_INICIO_DOLARES("irInicioDolares"),
	/** The ir inicio dolares banca privada. */
    IR_INICIO_DOLARES_BANCA_PRIVADA("irInicioDolaresBPriv"),
    /** The ir inicio licitaciones. */
    IR_INICIO_LICITACIONES("irInicioLicitaciones"),
    /** The ir solicitud. */
    IR_SOLICITUD("irSolicitud"),
    /** The ir solicitud cad. */
    IR_SOLICITUD_CAD("irSolicitudCAD"),
    /** The ir inicio cuenta y paquetes. */
    IR_INICIO_CUENTA_Y_PAQUETES("irInicioCuentasPaquetes"),
    /** The ir inicio prestamos solicitudes. */
    IR_INICIO_PRESTAMOS_SOLICITUDES("irInicioPrestamosSolicitudes"),
    /** The ir inicio tarjetas solicitudes. */
    IR_INICIO_TARJETAS_SOLICITUDES("irInicioTarjetasSolicitudes"),
    /** The ir inicio seguros solicitudes. */
    IR_INICIO_SEGUROS_SOLICITUDES("irInicioSegurosSolicitudes"),
    /** The ir inicio turnos solicitudes. */
    IR_INICIO_TURNOS_SOLICITUDES("irInicioTurnosSolicitudes"),
    /** The ir inicio form cosmos solicitudes. */
    IR_INICIO_FORM_COSMOS_SOLICITUDES("irInicioFormCosmosSolicitudes"),
    /** The ir seguimiento de gestiones. */
    IR_SEGUIMIENTO_DE_GESTIONES("irSeguimientoDeGestiones"),
    /** The ir inicio medios de pagos solicitudes. */
    IR_INICIO_MEDIOS_PAGO_SOLICITUDES("irInicioMediosDePagoSolicitudes"),
    IR_TRACKING_SOLICITUDES("microfront-delivery"),
    /** IR_FINANCIAL_HEALTH */
    IR_FINANCIAL_HEALTH("microfront-financialHealth"),
    /** The ir SuperClub. */
    IR_SUPERCLUB("irSuperClub"),
    IR_SUPERCLUB_PREMIFY("microfront-premify"),
    /** The ir extraccion santander express **/
    IR_EXTRACCION_SANTANDER_EXPRESS("microfront-ponsa-appointments"),
    /** The ir transacciones. */
    IR_TRANSACCION("irTransaccion"),
    /** The ir inicio trasnferencias. */
    IR_INICIO_TRASNFERENCIAS("irInicioTransferencias"),
    /** The ir inicio calendario de pagos. */
    IR_INICIO_CALENDARIO_DE_PAGOS("irInicioCalendarioDePagos"),
    /** The ir inicio pago de tarjeta. */
    IR_INICIO_PAGO_DE_TARJETA("irInicioPagoDeTarjeta"),
    /** The ir inicio envio de efectivo. */
    IR_INICIO_ENVIO_DE_EFECTIVO("irInicioEnvioDeEfectivo"),
    /** The ir inicio billetera virtual. */
    IR_INICIO_BILLETERA_VIRTUAL("irInicioBilleteraVirtual"),
    /** The ir inicio cesion de cheques. */
    IR_INICIO_CESION_DE_CHEQUES("irInicioCesionDeCheques"),
    /** The ir inicio descuento de cheques. */
    IR_INICIO_DESCUENTO_DE_CHEQUES("irInicioDescuentoDeCheques"),
    /** The ir inicio pago de haberes. */
    IR_INICIO_PAGO_DE_HABERES("irInicioPagoDeHaberes"),
    /** The ir inicio pago de haberes. */
    IR_INICIO_DEBIN("irInicioDebin"),
    /** The ir inicio transferencias al exterior. */
    IR_TRANSFERENCIAS_EXTERIOR("irTransferenciasExterior"),
    IR_TRANSFERENCIAS_EXTERIOR_MF("microfront-transferencias-al-exterior"),

    /** The ir Home Cuentas. */
    IR_HOME_CUENTA("irHomeCuenta"),
    /** The ir Home Cuentas. */
    IR_HOME_CALENDARIO("irHomeCalendario"),
    /** The ir Home Cuentas. */
    IR_HOME_PRESTAMO("irHomePrestamo"),
    /** The ir Home Cuentas. */
    IR_HOME_TARJETA("irHomeTarjeta"),

    /** The ir home seguro. */
    IR_HOME_SEGURO("irHomeSeguro"),

    /** The ir home seguro. */
    IR_HOME_INVERSIONES("irHomeInversiones"),

    /** The ir home inversiones solo para banca privada. */
    IR_HOME_INVERSIONES_BP("irHomeInversionesBP"),
    /** The ir inicio solicitudes caja ahorro. */
    IR_INICIO_SOLICITUDES_CAJA_AHORRO("irInicioSolicitudesCajaAhorro"),

    /** The ir inicio solicitudes caja ahorro en dolares. */
    IR_INICIO_SOLICITUDES_CAJA_AHORRO_DOLARES("irInicioSolicitudesCajaAhorroDolares"),

    /** The ir inicio solicitudes caja ahorro. */
    IR_INICIO_SOLICITUDES_CAJA_AHORRO_MICROFRONT("irInicioSolicitudesCajaAhorroMicrofront"),

    /** The ir inicio solicitudes cuenta titulos. */
    IR_INICIO_SOLICITUDES_CUENTA_TITULOS("irInicioSolicitudesCuentaTitulos"),

    /** The ir inicio solicitudes cuenta titulos de Repatriacion. */
    IR_INICIO_SOLICITUDES_CUENTA_TIT_REP("irInicioSolicitudesCuentaTitRep"),
    
    /** The ir inicio solicitudes cuenta titulos de Repatriacion. */
    IR_INICIO_SOLICITUDES_CUENTA_TIT_ENRI("irInicioSolicitudesCuentaTitEnri"),

    /** The ir inicio baja producto cuenta. */
    IR_INICIO_BAJA_PRODUCTO_CUENTA("irBajaProductoCuenta"),

    /** The ir inicio solicitudes chequeras. */
    IR_INICIO_SOLICITUDES_CHEQUERAS("irInicioSolicitudesChequeras"),

    /** The ir inicio solicitudes upgrade. */
    IR_INICIO_SOLICITUDES_UPGRADE("irInicioSolicitudesUpgrade"),

    /** The ir inicio solicitudes limite extraccion. */
    IR_INICIO_SOLICITUDES_LIMITE_EXTRACCION("irInicioSolicitudesLimiteExtraccion"),

    /** The ir inicio solicitudes reimpresion tarjetas. */
    IR_INICIO_SOLICITUDES_REIMPRESION_TARJETAS("irInicioSolicitudesReimpresionTarjetas"),

    /** The ir inicio solicitudes tarjeta visa. */
    IR_INICIO_SOLICITUDES_TARJETA_VISA("irInicioSolicitudesTarjetaVisa"),

    /** The ir inicio solicitudes tarjeta amex. */
    IR_INICIO_SOLICITUDES_TARJETA_AMEX("irInicioSolicitudesTarjetaAmex"),

    /** The ir inicio solicitudes tarjeta master. */
    IR_INICIO_SOLICITUDES_TARJETA_MASTER("irInicioSolicitudesTarjetaMaster"),

    /** The ir inicio solicitudes tarjeta credito. */
    IR_INICIO_SOLICITUDES_TARJETA_CREDITO("irInicioSolicitudesTarjetaCredito"),

    /** The ir inicio solicitudes tarjeta credito adicional. */
    IR_INICIO_SOLICITUDES_TARJETA_CREDITO_ADICIONAL("irInicioSolicitudesTarjetaCreditoAdicional"),

    /** The ir inicio solicitudes tarjeta visa recargable. */
    IR_INICIO_SOLICITUDES_TARJETA_VISA_RECARGABLE("irInicioSolicitudesTarjetaVisaRecargable"),

    /** The ir inicio bajaproducto. */
    IR_INICIO_BAJAPRODUCTO("irBajaProducto"),
    
    /** The ir inicio bajatarjetas. */
    IR_INICIO_BAJATARJETAS("irBajaTarjetas"),

    /** The ir inicio solicitudes tarjeta visa recargable adicional. */
    IR_INICIO_SOLICITUDES_TARJETA_VISA_RECARGABLE_ADICIONAL("irInicioSolicitudesTarjetaVisaRecargableAdicional"),

    /** The ir inicio solicitudes habilitar tarjetas por viaje. */
    IR_INICIO_SOLICITUDES_HABILITAR_TARJETAS_POR_VIAJE("irInicioSolicitudesHabilitarTarjetasPorViaje"),

    /** The ir inicio solicitudes afinidad. */
    IR_INICIO_SOLICITUDES_AFINIDAD("irInicioSolicitudesAfinidad"),

    /** The ir inicio solicitudes pedido chequera. */
    IR_INICIO_SOLICITUDES_PEDIDO_CHEQUERA("irInicioSolicitudesPedidoChequera"),

    /** The ir inicio solicitud de aumento limite transferencia. */
    IR_SOLICITUD_AUMENTO_LIMITE_TRANSFERENCIA("irInicioAumentoLimiteTransferencia"),

    /** The ir inicio solicitudes cuenta exterior. */
    IR_INICIO_SOLICITUDES_CUENTAEXTERIOR("irInicioSolicitudesCuentaExterior"),

    /** The carrusel. */
    CARRUSEL("carrusel"),

    /** The notificaciones. */
    NOTIFICACIONES("notificaciones"),

    /** The chat. */
    CHAT("chat"),

    /** The chatAllClients. */
    CHAT_ALL("chatAllClients"),

    /** The onboarding. */
    ONBOARDING("onboarding"),

    /** Card de millas Aadvantage. */
    IR_AADVANTAGE("irAadvantage"),

    /** The RENDIMIENTO_TENENCIA_INVERSIONES. */
    RENDIMIENTO_TENENCIA_INVERSIONES("rendimientoTenenciaInversiones"),

    /** The banca privada. */
    BANCA_PRIVADA("bancaPrivadaLimitada"),

    /** The ir inicio analisis cartera. */
    IR_INICIO_ANALISIS_CARTERA("irInicioAnalisisCartera"),

    /** The ir inicio servicios de inversion. */
    IR_INICIO_SERVICIOS_DE_INVERSION("irInicioServiciosDeInversion"),
    
    IR_INICIO_BOTON_MEP("irInicioBotonMep"),
    
    IR_INICIO_ITEM_DINAMICO("irInicioBotonDinamico"),

    /** The login hbilitado. */
    LOGIN_HBILITADO("loginHabilitado"),
    /** The ir inicio cuentas. */
    IR_INICIO_UNA_SOLA_CUENTA("irInicioUnaSolaCuenta"),

    /** The ir inicio cuentas banca privada. */
    IR_INICIO_UNA_SOLA_CUENTA_BANCA_PRIVADA("irInicioUnaSolaCuentaBancaPrivada"),

    /** The salto obe tbanco. */
    SALTO_OBE_TBANCO("saltoObeTbanco"),
    
    /** The salto app tbanco. */
    SALTO_APP_TBANCO("saltoAppTbanco"),

    /** The ir transferencias banca privada. */
    IR_TRANSFERENCIAS_BANCA_PRIVADA("irTransferenciaBancaPrivada"),

    /** The ir inicio boton pago. */
    IR_INICIO_BOTON_PAGO("irInicioBotonPago"),

    /** The LOGICA_ENC_LOGIN. */
    LOGICA_ENC_LOGIN("encpines"),

    /** The IR_INICIO_CATALOGO SUPERCLUB. */
    IR_INICIO_CATALOGO("irInicioCatalogo"),

    /** The ir inicio referidos. */
    REFERIDOS_COMPARTIR("referidosCompartir"),

    /** The IR_HOME_TRANFERENCIAS. */
    IR_HOME_TRANFERENCIAS("irHomeTransferencias"),

    /** The IR_INICIO_MILLAS Aadvantage. */
    IR_INICIO_MILLAS("irInicioMillas"),

    /** The IR_CHANCES Chance. */
	IR_CHANCES("irChances"),

    /** The ir a echeq. */
    IR_INICIO_ECHEQ("irInicioECheq"),

    /** The ir orden pago del exterior. */
    IR_ORDEN_PAGO_EXTERIOR("irOrdenPagoDelExterior"),
    IR_ORDEN_PAGO_EXTERIOR_MF("microfront-cobros-del-exterior"),

    /** The transferencias exterior operaciones. */
    TRANSFERENCIAS_EXTERIOR_OPERACIONES("transferenciasExteriorOperaciones"),

   	 /** The ir inicioselectonline. */
 	IR_INICIO_SELECT_ONLINE("irInicioSelectOnline"),

    IR_EJECUTIVO_ONLINE_MICROFRONT("microfront-ejecutivo-online"),

    /** The logout mobile. */
    LOGOUT_MOBILE("logoutMobile"),

    /** The ir inicio resumen anual. */
    IR_INICIO_RESUMEN_ANUAL("irInicioResumenAnual"),
    
    /** The ir cambio grupo afinidad por advantage. */
    IR_CAMBIO_GRUPO_AFINIDAD_POR_ADVANTAGE("irCambioGrupoAfinidadPorAdvantage"),

    /** The ir cambio grupo afinidad por superclub. */
    IR_CAMBIO_GRUPO_AFINIDAD_POR_SUPERCLUB("irCambioGrupoAfinidadPorSuperclub"),
	
    /** The ir referidos. */
    IR_REFERIDOS("irReferidos"),

    /** The ir inicio adhesion women. */
    IR_INICIO_ADHESION_WOMEN("irInicioAdhesionWomen"),

	/** The ir inicio getnet. */
    IR_INICIO_GETNET("irInicioGetnet"),
    
    /** The ir extraccion efectivo. */
    IR_EXTRACCION_EFECTIVO("irExtraccionEfectivo"),

    /** The financiaciones. */
    FINANCIACIONES("financiaciones"),

    /** Solicitud prestamo preaprobado monoproducto*/
	SOLICITUD_PRESTAMO_PREAPROBADO_MONOPRODUCTO("solicitudPrestamoPreaprobadoMonoproducto"),

    /** The queue st compraventa. */
    QUEUE_ST_COMPRAVENTA("queue-st-compraventa"),

    /** The queue st login. */
    QUEUE_ST_LOGIN("queue-st-login"),

    /** The queue st login no wait. */
    QUEUE_ST_LOGIN_NO_WAIT("queue-st-login-no-wait"),
    
    VER_SALDOS_CUENTAS("verSaldosCuentas"),
    
    VER_SALDOS_TARJETAS("verSaldosTarjetas"),

    IR_INICIO_DOCUMENTACION_DOLARES("irInicioDocumentacionDolares"),

    IR_INICIO_CONVENIO_EMPLEADO_PUBLICO("irInicioConvenioEmpleadoPublico"),
    
    IR_CLAVE_BANELCO("irClaveBanelco"),

	IR_DENUNCIA_Y_REPO("irDenunciaYReposicion"),
	
	/** The ir documentacion cuenta. */
    IR_DOCUMENTACION_CUENTA("irDocumentacionCuenta"),

	VER_BUSCADOR_DASHBOARD("verBuscadorDashboard"),

    /** Onboardings de sugerencias al loguearse (sacá tu token, etc.) */
    VER_ONBOARDING("verOnboarding"),

    MICROAPP_ACCOUNTS_FF("microapp-accounts-ff"),

    MICROAPP_ACCOUNTS_ALL("microapp-accounts-all"),

    MICROAPP_TURNOS_FF("microapp-turnos-ff"),

    MICROAPP_TURNOS_ALL("microapp-turnos-all"),

    MICROAPP_CDA_PRIVATE_FF("microapp-cda-private-ff"),

    MICROAPP_CDA_PRIVATE_ALL("microapp-cda-private-all"),

    IR_SORPRESA("irSorpresaMya"),

    /** The proxy login valcred. */
    PROXY_LOGIN_VALCRED("proxyLoginValCred"),

    /** The consumo api accounts. */
    CONSUMO_API_ACCOUNTS("consumoApiAccounts"),

	/** The proxy login. */
	PROXY_LOGIN("proxyLogin"),

    /** Gestion de casos API */
	CONSUMO_API_CASOSF("consumoApiGestionDeCasos"),

    /** The api holding */
    CONSUMO_API_HOLDING("consumoApiHolding"),

	UTILIZAR_API_CUENTAS("usarApiCuentas"),
	
	GRABAR_ESTADISTICAS("grabarEstadisticas"),

	HABILITAR_API_MENU("habilitarApiMenu"),

	APIAUTH_VALIDATION("apiAuthent"),

	UTILIZAR_API_ROUTER_SIMULACION("usarApiRouterSimulacion"), 
	
	UTILIZAR_API_ROUTER_TASAS("usarApiRouterTasas"),

	ACTIVAR_BIOCATCH("activarBiocatch"),
	
	COMPRAS_SIN_VALIDAR_FIRMAS("comprasSinValidarFirmas"),
	
	VENTAS_SIN_VALIDAR_FIRMAS("ventasSinValidarFirmas"),

	IR_CONSENTIMIENTO_BILLETERAS("irConsentimientoBilleteras"),

	MICROAPP_RECUPE_FF("microapp-recupe-ff"),

    MICROAPP_RECUPE_ALL("microapp-recupe-all"),

    MICROAPP_ATYCO_FF("microapp-atyco-ff"),

    MICROAPP_ATYCO_ALL("microapp-atyco-all"),

	IR_BENEFICIO_TRANSFERI_TU_SUELDO("irBeneficioTransferiTuSueldo");

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new accion controller.
     *
     * @param descripcion
     *            the descripcion
     */
    AccionController(String descripcion) {
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
     * Dado una descripcion obtener el enum asociado, si no se encuentra retorna
     * null.
     *
     * @param descripcion
     *            the descripcion
     * @return the accion controller
     */
    public static AccionController obtenerEnumPorDescripcion(String descripcion) {
        if (StringUtils.isNotEmpty(descripcion)) {
            for (AccionController accionController : AccionController.values()) {
                if (accionController.getDescripcion().equals(descripcion)) {
                    return accionController;
                }
            }
        }
        return null;
    }

}
