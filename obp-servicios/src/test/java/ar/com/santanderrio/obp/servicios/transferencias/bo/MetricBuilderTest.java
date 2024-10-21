package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.MetricBuilderImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.DetalleError;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricWithTags;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferMetricInfoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferStatus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MetricBuilderTest {

    private MetricBuilder metricBuilder;

    private final String nup = "0934938493849";
    private final boolean isRioRio = true;
    private final boolean isCuentaPropia = true;
    private final DetalleError detalleError = DetalleError.CUENTA_CERRADA;
    private final String expectedErrorCodeRioRioCuentaPropiaCuentaCerrada = "R:P:CTA_0002";
    private final String expectedErrorCodeRioOtroCuentaTerceroCuentaCerrada = "O:T:CTA_0002";
    private final String expectedErrorCodeNoAplica = "NOT_DEFINED";
    private final int errorCodePositionWithinTags = 2;

    @Before
    public void setup() {

        metricBuilder = new MetricBuilderImpl();

    }

    @Test
    public void createMetricTransferFailRioRioCuentaPropiaReturnCorrectErrorCode() {

        TransferMetricInfoDTO transferMetricInfoFailRioRioCuentaPropia = getTransferMetricInfoFail(isRioRio, isCuentaPropia, detalleError);

        MetricWithTags metric = metricBuilder.createMetricTransfer(transferMetricInfoFailRioRioCuentaPropia);

        assertNotNull(metric);

        String actualErrorCode = metric.getTags().get(errorCodePositionWithinTags).getValue();

        assertNotNull(actualErrorCode);

        assertEquals(expectedErrorCodeRioRioCuentaPropiaCuentaCerrada, actualErrorCode);

    }

    @Test
    public void createMetricTransferFailRioOtroCuentaTerceroReturnCorrectErrorCode() {

        TransferMetricInfoDTO transferMetricInfoFailRioOtroCuentaTercero = getTransferMetricInfoFail(!isRioRio, !isCuentaPropia, detalleError);

        MetricWithTags metric = metricBuilder.createMetricTransfer(transferMetricInfoFailRioOtroCuentaTercero);

        assertNotNull(metric);
        assertNotNull(metric.getTags().get(errorCodePositionWithinTags).getValue());
        assertEquals(expectedErrorCodeRioOtroCuentaTerceroCuentaCerrada, metric.getTags().get(errorCodePositionWithinTags).getValue());

    }

    @Test
    public void createMetricTransferOkReturnNoAplicaErrorCode() {

        TransferMetricInfoDTO transferMetricInfoOk = new TransferMetricInfoDTO(nup, TransferStatus.OK);

        MetricWithTags metric = metricBuilder.createMetricTransfer(transferMetricInfoOk);

        assertNotNull(metric);
        assertNotNull(metric.getTags().get(errorCodePositionWithinTags).getValue());
        assertEquals(expectedErrorCodeNoAplica, metric.getTags().get(errorCodePositionWithinTags).getValue());

    }

    private TransferMetricInfoDTO getTransferMetricInfoFail(boolean isRioRio, boolean isCuentaPropia, DetalleError detalleError) {

        TransferMetricInfoDTO transferInfo = new TransferMetricInfoDTO(nup, TransferStatus.FAIL);
        transferInfo.setRioRio(isRioRio);
        transferInfo.setCuentaPropia(isCuentaPropia);
        transferInfo.setDetalleError(detalleError);

        return transferInfo;

    }

}
