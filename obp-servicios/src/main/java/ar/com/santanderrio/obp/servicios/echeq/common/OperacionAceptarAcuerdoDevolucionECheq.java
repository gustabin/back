package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.math.MathContext;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.AceptarRepudiarAcuerdoDevolucionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionAceptarAcuerdoDevolucionECheq extends IatxBaseEcheqOperation {

    public OperacionAceptarAcuerdoDevolucionECheq(ECheqDAO echeqDao) {
        super(echeqDao);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.ACEPTAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public AceptarRepudiarAcuerdoDevolucionECheqInEntity getInEntity() {
        AceptarRepudiarAcuerdoDevolucionECheqInEntity recepcionRepudioEcheqInEntity = new AceptarRepudiarAcuerdoDevolucionECheqInEntity();
        recepcionRepudioEcheqInEntity.setTipoCuenta(StringUtils.leftPad(StringUtils.EMPTY, 2, " ")); 
        recepcionRepudioEcheqInEntity.setSucursalCuenta(StringUtils.leftPad(StringUtils.EMPTY, 3, " "));
        recepcionRepudioEcheqInEntity.setNumeroCuenta(StringUtils.leftPad(StringUtils.EMPTY, 7, " "));
        recepcionRepudioEcheqInEntity.setImporteCuenta(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(getCheque().getMonto(), MathContext.DECIMAL64), 14));
        recepcionRepudioEcheqInEntity.setIdCheque(ISBANStringUtils.fillStr(getCheque().getIntchequeId(), 15));
        recepcionRepudioEcheqInEntity.setNroCheque(ISBANStringUtils.fillNum(getCheque().getChequeNumero(), 9));
        recepcionRepudioEcheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(getCheque().getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
        recepcionRepudioEcheqInEntity.setFechaPago(ECheqUtils.parsearFecha(getCheque().getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
        recepcionRepudioEcheqInEntity.setCuitEmisor(getCheque().getCuentaEmisora().getEmisorCuit());
        recepcionRepudioEcheqInEntity.setRazonSocialEmisor(ISBANStringUtils.fillStr(getCheque().getCuentaEmisora().getEmisorRazonSocial(), 100));
        recepcionRepudioEcheqInEntity.setTipoSolicitud("A");
        recepcionRepudioEcheqInEntity.setMotivoRepudio(StringUtils.leftPad(StringUtils.EMPTY, 500, " "));
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        recepcionRepudioEcheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        recepcionRepudioEcheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        recepcionRepudioEcheqInEntity.setJSessionId(ISBANStringUtils.fillStr(getOperationDetails().getjSessionId(), 50));
        return recepcionRepudioEcheqInEntity;
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.ECHEQ_ACEPTAR_ACUERDO_DEVOLUCION;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
    	return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ACEPTAR_ACUERDO_DEVOLUCION,
                cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ACEPTAR_ACUERDO_DEVOLUCION;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_ACEPTAR_ACUERDO_DEVOLUCION;
    }
}
