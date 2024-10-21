package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferMetricsConstants;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricTag;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricType;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricWithTags;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferMetricInfoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MetricBuilderImpl implements MetricBuilder {

    @Override
    public MetricWithTags createMetricTransfer(TransferMetricInfoDTO transferInfo) {

        return new MetricWithTags(TransferMetricsConstants.CMDB,
                TransferMetricsConstants.METRIC_NAME,
                TransferMetricsConstants.COUNTER_DEFAULT_VALUE,
                MetricType.COUNTER,
                getTagsFromTransferData(transferInfo));

    }

    private ArrayList<MetricTag> getTagsFromTransferData(TransferMetricInfoDTO transferInfo) {

        ArrayList<MetricTag> tags = new ArrayList<MetricTag>();

        tags.add(new MetricTag(TransferMetricsConstants.TAG_TRANSFER_STATUS, transferInfo.getStatus().getValue()));
        tags.add(new MetricTag(TransferMetricsConstants.TAG_NUP, transferInfo.getNup()));
        tags.add(new MetricTag(TransferMetricsConstants.TAG_TRANSFER_ERROR_CODE, getErrorCodeByStatus(transferInfo)));

        return tags;

    }

    private String getErrorCodeByStatus(TransferMetricInfoDTO transferInfo) {

        if (transferInfo.isStatusFailOrWarning()) {

            return getErrorCode(transferInfo);

        }

        return TransferMetricsConstants.VALUE_NOT_DEFINED;

    }

    private String getErrorCode(TransferMetricInfoDTO transferInfo){

        StringBuilder errorCodeBuilder = new StringBuilder();

        errorCodeBuilder.append(buildTipoBancoDestinoCode(transferInfo));

        errorCodeBuilder.append(TransferMetricsConstants.ERROR_CODE_DELIMITER);

        errorCodeBuilder.append(buildTipoCuentaDestinoCode(transferInfo));

        errorCodeBuilder.append(TransferMetricsConstants.ERROR_CODE_DELIMITER);

        errorCodeBuilder.append(transferInfo.getDetalleError().getCodigo());

        return errorCodeBuilder.toString();

    }

    private String buildTipoBancoDestinoCode(TransferMetricInfoDTO transferInfo) {

        if(!transferInfo.hasDestinationInfo()) {

            return TransferMetricsConstants.VALUE_NO_APLICA;

        }

        if (transferInfo.isRioRio()) {

            return TransferMetricsConstants.TRANSFER_RIO_RIO_CODE;

        } else {

            return TransferMetricsConstants.TRANSFER_RIO_OTRO_CODE;

        }

    }

    private String buildTipoCuentaDestinoCode(TransferMetricInfoDTO transferInfo) {

        if(!transferInfo.hasDestinationInfo()) {

            return TransferMetricsConstants.VALUE_NO_APLICA;

        }

        if (transferInfo.isCuentaPropia()) {

            return TransferMetricsConstants.TRANSFER_CUENTA_PROPIA_CODE;

        } else {

            return TransferMetricsConstants.TRANSFER_CUENTA_TERCERO_CODE;

        }

    }

}
