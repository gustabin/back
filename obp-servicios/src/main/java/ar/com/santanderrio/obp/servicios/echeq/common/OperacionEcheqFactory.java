package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeqapi.connector.EcheqApiConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class OperacionEcheqFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperacionEcheqFactory.class);

    private OperacionEcheqFactory() {
        super();
    }

    public static IOperacionECheq getOperacion(OperacionEcheqEnum accion) {
        switch (accion) {
            case ACEPTAR:
                return new OperacionAceptarECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case REPUDIAR:
                return new OperacionRepudiarECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case DEPOSITAR:
                return new OperacionDepositarECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ALTA:
                return new OperacionAltaECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case CUSTODIAR:
                return new OperacionCustodiarECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case RESCATAR:
                return new OperacionRescatarECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ENDOSAR:
                return new OperacionEndosarECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ANULAR_ENDOSO:
                return new OperacionAnularEndosoEcheq(ContextHolder.getContext().getBean(EcheqApiConnector.class));
            case ACEPTAR_ACUERDO_DEVOLUCION:
                return new OperacionAceptarAcuerdoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case SOLICITAR_PEDIDO_DEVOLUCION:
                return new OperacionSolicitarPedidoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case REPUDIAR_PEDIDO_DEVOLUCION:
                return new OperacionRepudiarPedidoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case SOLICITAR_ACUERDO_DEVOLUCION:
                return new OperacionSolicitarAcuerdoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ANULAR_PEDIDO_DEVOLUCION:
                return new OperacionAnularPedidoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case REPUDIAR_ACUERDO_DEVOLUCION:
                return new OperacionRepudiarAcuerdoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ANULAR_ACUERDO_DEVOLUCION:
                return new OperacionAnularSolicitudAcuerdoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case EMITIR_CERTIFICADO:
                return new OperacionEmitirCertificadoECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ACEPTAR_PEDIDO_DEVOLUCION:
                return new OperacionAceptarPedidoDevolucionECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ANULAR:
                return new OperacionAnularECheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case EMITIR_CED:
                return new OperacionAltaCEDEcheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ADMITIR_CED:
                return new OperacionAceptarCEDEcheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case REPUDIAR_CED:
                return new OperacionRepudiarCEDEcheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case ANULAR_CED:
                return new OperacionAnularCEDEcheq(ContextHolder.getContext().getBean(ECheqDAO.class));
            case SOLICITAR_AVAL:
                return new OperacionSolicitudAvalEcheq(ContextHolder.getContext().getBean(EcheqApiConnector.class));
            case ACEPTAR_AVAL:
                return new OperacionAceptarAvalEcheq(ContextHolder.getContext().getBean(EcheqApiConnector.class));
            case ANULAR_SOLICITUD_AVAL:
                return new OperacionAnularSolicitudAvalEcheq(ContextHolder.getContext().getBean(EcheqApiConnector.class));
            case REPUDIAR_AVAL:
                return new OperacionRepudiarAvalEcheq(ContextHolder.getContext().getBean(EcheqApiConnector.class));
            case VER_DETALLE:
                return new OperacionVerDetalleEcheq();
            default:
                LOGGER.error("Operacion desconocida");
                return null;
            }
    }

    /**
     * Mensaje PopUp de la operacion 
     * @param accion
     * @return
     */
    //TODO: REMOVE THIS FROM OPERATION FACTORY
    public static String getCodigoMensajePopUp(OperacionEcheqEnum accion) {
        switch (accion) {
        case ACEPTAR:
        case REPUDIAR:
            return OperacionAceptarECheq.getCodigoMensajePopUp();
        case RESCATAR:
            return OperacionRescatarECheq.getCodigoMensajePopUp();
        case ACEPTAR_PEDIDO_DEVOLUCION:
            return OperacionAceptarPedidoDevolucionECheq.getCodigoMensajePopUp();
        case ACEPTAR_ACUERDO_DEVOLUCION:
            return OperacionAceptarAcuerdoDevolucionECheq.getCodigoMensajePopUp();
        case SOLICITAR_PEDIDO_DEVOLUCION:
            return OperacionSolicitarPedidoDevolucionECheq.getCodigoMensajePopUp();
        case REPUDIAR_PEDIDO_DEVOLUCION:
            return OperacionRepudiarPedidoDevolucionECheq.getCodigoMensajePopUp();
        case SOLICITAR_ACUERDO_DEVOLUCION:
            return OperacionSolicitarAcuerdoDevolucionECheq.getCodigoMensajePopUp();
        case ANULAR_PEDIDO_DEVOLUCION:
            return OperacionAnularPedidoDevolucionECheq.getCodigoMensajePopUp();
        case REPUDIAR_ACUERDO_DEVOLUCION:
            return OperacionRepudiarAcuerdoDevolucionECheq.getCodigoMensajePopUp();
        case ANULAR_ACUERDO_DEVOLUCION:
            return OperacionAnularSolicitudAcuerdoDevolucionECheq.getCodigoMensajePopUp();
        case EMITIR_CERTIFICADO:
            return OperacionEmitirCertificadoECheq.getCodigoMensajePopUp();
        case ADMITIR_CED:
            return OperacionCEDEcheq.getCodigoMensajePopUp();
        case ACEPTAR_AVAL:
            return CodigoMensajeConstantes.ECHEQ_POPUP_ACEPTAR_AVAL;
        case REPUDIAR_AVAL:
            return CodigoMensajeConstantes.ECHEQ_POPUP_REPUDIAR_AVAL;
        case ANULAR_SOLICITUD_AVAL:
            return CodigoMensajeConstantes.ECHEQ_POPUP_ANULAR_SOLICITUD_AVAL;
        default:
            LOGGER.error("Operacion sin mensaje PopUp");
            return null;
        }
    }

    //TODO: REMOVE THIS FROM OPERATION FACTORY
    public static String getLegalesOperacion(OperacionEcheqEnum accion) {
        switch (accion) {
        case CUSTODIAR:
        	return OperacionCustodiarECheq.getCodigoLegal();
        case DEPOSITAR:
        	return OperacionDepositarECheq.getCodigoLegal();
        case EMITIR_CERTIFICADO:
        	return OperacionEmitirCertificadoECheq.getCodigoLegal();
        case EMITIR_CED:
        	return OperacionAltaCEDEcheq.getCodigoLegal();
        default:
            return null;
        }
    }

    //TODO: REMOVE THIS FROM OPERATION FACTORY
    public static String getLegalesAyudaOperacion(OperacionEcheqEnum accion) {
		switch (accion) {
		case ENDOSAR:
			return OperacionEndosarECheq.getCodigoLegalAyuda();
		case ALTA:
			return OperacionAltaECheq.getCodigoLegalAyuda();
        case SOLICITAR_AVAL:
            return OperacionSolicitudAvalEcheq.getCodigoLegalAyuda();
		default:
			return null;
		}
    }

    //TODO: REMOVE THIS FROM OPERATION FACTORY
    public static String getTipoCuentasHabilitadasPrefix(OperacionEcheqEnum accion) { 
        switch (accion) {
        case DEPOSITAR:
            return OperacionDepositarECheq.getTipoCuentasHabilitadasPrefix();
        case ALTA:
            return OperacionAltaECheq.getTipoCuentasHabilitadasPrefix();
        case CUSTODIAR:
            return OperacionCustodiarECheq.getTipoCuentasHabilitadasPrefix();
        default:
            LOGGER.error("Operacion sin cuentas habilitadas");
            return null;
        }
    }

    //TODO: REMOVE THIS FROM OPERATION FACTORY
	public static String getMensajeFueraHorario(OperacionEcheqEnum accion) {
		switch (accion) {
        case DEPOSITAR:
            return OperacionDepositarECheq.getMensajeFueraHorario();
        case ALTA:
            return OperacionAltaECheq.getMensajeFueraHorario();
        case CUSTODIAR:
            return OperacionCustodiarECheq.getMensajeFueraHorario();
        case RESCATAR:
            return OperacionRescatarECheq.getMensajeFueraHorario();
        default:
            LOGGER.error("Operacion desconocida");
            return null;
        }
	}
}
