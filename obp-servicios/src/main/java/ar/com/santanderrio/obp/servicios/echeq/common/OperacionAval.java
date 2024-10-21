package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.model.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.utils.AvalEcheqUtils;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;
import ar.com.santanderrio.obp.servicios.echeqapi.connector.EcheqApiConnector;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Echeq;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantyClient;
import ar.com.santanderrio.obp.servicios.echeqapi.model.GuarantySigner;

import java.text.ParseException;

public abstract class OperacionAval extends AbstractOperacionECheq {
    protected static final String MOTIVO_NA = "N/A";
    protected final EcheqApiConnector echeqApiConnector;

    protected OperacionAval(EcheqApiConnector echeqApiConnector) {
        this.echeqApiConnector = echeqApiConnector;
    }

    public static Boolean horarioOperacionValida() throws ParseException {
        return ECheqUtils.esHorarioHabilitado();
    }

    protected GuarantySigner mapOperationSigner() {
        return GuarantySigner.builder()
                .clientName(AvalEcheqUtils.mapClientName(cliente))
                .documentNumber(cliente.getNumeroCUILCUIT().replace("-", ""))
                .documentType(TipoDocumentoEnum.CUIL)
                .build();
    }

    protected GuarantyClient mapOperationRequester() {
        return GuarantyClient.builder()
                .name(AvalEcheqUtils.mapClientName(cliente))
                .documentNumber(cliente.getNumeroCUILCUIT().replace("-", ""))
                .documentType(TipoDocumentoEnum.CUIL)
                .build();
    }

    protected GuarantyClient mapHolder() {
        return null;
    }

    protected Echeq mapEcheq() {
        return Echeq.builder()
                .cmc7(cheque.getCmc7())
                .coelsaId(cheque.getIntchequeId())
                .number(cheque.getChequeNumero())
                .build();
    }

    @Override
    public Estadistica getEstadisticaOperacion() {
        final Estadistica estadistica = super.getEstadisticaOperacion();
        Cuenta cuentaOperacion = obtenerCuentaPorCbu(cliente, cheque.getCuentaEmisora().getEmisorCbu());
        if (cuentaOperacion != null) {
            StringBuilder sb = new StringBuilder(cuentaOperacion.getTipoCuentaEnum().getAbreviatura())
                    .append(" ")
                    .append(cuentaOperacion.obtenerNroCuentaFormateado());
            estadistica.setNroCtaOrigen( sb.toString() );
        }
        return estadistica;
    }
}
