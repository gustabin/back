package ar.com.santanderrio.obp.servicios.echeq.common;

import java.math.BigDecimal;
import java.math.MathContext;

import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.RecepcionRepudioECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;

public class OperacionAceptarECheq extends IatxBaseEcheqOperation {

    public OperacionAceptarECheq(ECheqDAO eCheqDAO) {
        super(eCheqDAO);
    }

    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.ACEPTAR;
    }
    
    @Override
    public RecepcionRepudioECheqInEntity getInEntity() {
        RecepcionRepudioECheqInEntity recepcionRepudioEcheqInEntity = new RecepcionRepudioECheqInEntity();
        recepcionRepudioEcheqInEntity.setIdCheque(ISBANStringUtils.fillStr(getCheque().getIntchequeId(), 15));
        String tipoDoc = TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion();
        recepcionRepudioEcheqInEntity.setTipoDocumento(ISBANStringUtils.fillStr(tipoDoc, 4));
        recepcionRepudioEcheqInEntity.setNroDocumento(ISBANStringUtils.fillStr(cliente.getNroDocCnsDocXNup(), 11));
        recepcionRepudioEcheqInEntity.setTipoCuenta(StringUtils.EMPTY); 
        recepcionRepudioEcheqInEntity.setSucursalCuenta(StringUtils.EMPTY);
        recepcionRepudioEcheqInEntity.setNumeroCuenta(StringUtils.EMPTY);
        recepcionRepudioEcheqInEntity.setNroCheque(ISBANStringUtils.fillNum(getCheque().getChequeNumero(), 9));
        recepcionRepudioEcheqInEntity.setImporte(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(getCheque().getMonto(), MathContext.DECIMAL64), 14));
        recepcionRepudioEcheqInEntity.setFechaEmision(ECheqUtils.parsearFecha(getCheque().getFechaEmision(), ECheqUtils.MASCARA_FECHA_IATX));
        recepcionRepudioEcheqInEntity.setFechaPago(ECheqUtils.parsearFecha(getCheque().getFechaPago(), ECheqUtils.MASCARA_FECHA_IATX));
        recepcionRepudioEcheqInEntity.setCuitEmisor(getCheque().getCuentaEmisora().getEmisorCuit());
        recepcionRepudioEcheqInEntity.setRazonSocialEmisor(getCheque().getCuentaEmisora().getEmisorRazonSocial());
        recepcionRepudioEcheqInEntity.setTipoSolicitud("A"); // R rechazar
        recepcionRepudioEcheqInEntity.setMotivoRepudio(StringUtils.EMPTY);
        recepcionRepudioEcheqInEntity.setJSessionId(getOperationDetails().getjSessionId());
        recepcionRepudioEcheqInEntity.setAccionEcheq(OperacionEcheqEnum.ACEPTAR);
        return  recepcionRepudioEcheqInEntity;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_ACEPTAR,
                cheque.getChequeNumero()).getMensaje();
    }

    @Override
    public String getCodigoEstadistica() {
        return EstadisticasConstants.CODIGO_ACEPTAR_ECHEQ;
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ACEPTAR;
    }

    public static String getCodigoMensajePopUp() {
        return CodigoMensajeConstantes.ECHEQ_MENSAJE_POP_UP_ACEPTAR;
    }

}
