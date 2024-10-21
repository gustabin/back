package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.IOperacionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

public class OperacionVerDetalleEcheq extends AbstractOperacionECheq {
    @Override
    public OperacionEcheqEnum getOperacion() {
        return OperacionEcheqEnum.VER_DETALLE;
    }

    @Override
    public void setOperationContext(Cheque echeq, Cliente cliente, ConfirmarOperacionInDTO confirmarOperacionInDTO) {
        //:(
    }

    @Override
    public void execute() {
        //Ver Detalle Implementation Details (not here)
    }

    @Override
    public IOperacionECheqInEntity<?> getInEntity() {
        return null;
    }

    @Override
    public String getCodigoEstadistica() {
        return null;
    }

    @Override
    public String getMensajeOk(ConfirmarOperacionInDTO confirmarOperacionInDTO, MensajeBO mensajeBO) {
        return null;
    }

    @Override
    public String getMensajeError(String errorCodeItax) {
        return null;
    }
}
