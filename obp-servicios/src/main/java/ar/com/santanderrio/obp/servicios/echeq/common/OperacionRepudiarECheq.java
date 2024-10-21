package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.RecepcionRepudioECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class OperacionRepudiarECheq extends OperacionAceptarECheq {

	public OperacionRepudiarECheq(ECheqDAO eCheqDAO) {
		super(eCheqDAO);
	}

	@Override
	public OperacionEcheqEnum getOperacion() {
		return OperacionEcheqEnum.REPUDIAR;
	}

	@Override
    public RecepcionRepudioECheqInEntity getInEntity() {
        RecepcionRepudioECheqInEntity inEntity = super.getInEntity();
        inEntity.setTipoSolicitud("R");
        inEntity.setMotivoRepudio(operationDetails.getMotivoRepudio());
        return inEntity;
    }

	@Override
	public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
		return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_FEEDBACK_OK_REPUDIAR,
				cheque.getChequeNumero()).getMensaje();
	}

    @Override
    public String getMensajeError(String errorCodeItax) {
        return CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_REPUDIAR;
    }

    @Override
    public String getCodigoEstadistica() {
    	return EstadisticasConstants.CODIGO_REPUDIAR_ECHEQ;
    }
}
    