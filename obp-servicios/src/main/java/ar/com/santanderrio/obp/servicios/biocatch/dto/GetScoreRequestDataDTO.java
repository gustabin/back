package ar.com.santanderrio.obp.servicios.biocatch.dto;

import ar.com.santanderrio.obp.servicios.biocatch.model.*;
import ar.com.santanderrio.obp.servicios.transferencias.utils.TransferSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class GetScoreRequestDataDTO extends BaseRequestDataDTO {

    private ActivityName activityName;
    private ActivityType activityType;
    private String originCBU;
    private String recipientCBU;
    private BigDecimal amount;
    private String originCuit;
    private String recipientCuit;
    @JsonSerialize(using = TransferSerializer.class)
    private LocalDate accountOpenDate;
    private BatchFile batchFile;

    public GetScoreRequestDataDTO(String csid, String nup, String ip, String userAgent, PlatformType platformType, Brand brand,
                                  ActivityName activityName, ActivityType activityType) {
        super(csid, nup, ip, userAgent, platformType, brand);
        this.activityName = activityName;
        this.activityType = activityType;
        this.batchFile = null;
    }

    public GetScoreRequestDataDTO(String csid, String nup, String ip, String userAgent, PlatformType platformType, Brand brand,
                                  ActivityName activityName, ActivityType activityType, BiocatchTransferInfoDTO biocatchTransferInfoDTO) {
        super(csid, nup, ip, userAgent, platformType, brand);
        this.activityName = activityName;
        this.activityType = activityType;
        this.originCBU = biocatchTransferInfoDTO.getOrigenCBU();
        this.recipientCBU= biocatchTransferInfoDTO.getDestinoCBU();
        this.amount=biocatchTransferInfoDTO.getImporte();
        this.originCuit= biocatchTransferInfoDTO.getCuitOrigen();
        this.recipientCuit= biocatchTransferInfoDTO.getCuitDestino();
        this.accountOpenDate=biocatchTransferInfoDTO.getFechaCreacionCuenta();
        this.batchFile = null;
    }

    public GetScoreRequestDataDTO(String customerSessionId, String nup, String ip, String userAgent, PlatformType platformType, Brand brand,
                                  ActivityName activityName, ActivityType activityType, BatchFile batchFile) {
        super(customerSessionId, nup, ip, userAgent, platformType, brand);
        this.activityName = activityName;
        this.activityType = activityType;
        this.batchFile = batchFile;
    }

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getOriginCBU() { return originCBU; }

    public void setOriginCBU(String originCBU) { this.originCBU = originCBU;}

    public String getRecipientCBU() {return recipientCBU;}

    public void setRecipientCBU(String recipientCBU) {this.recipientCBU = recipientCBU;}

    public BigDecimal getAmount() {return amount;}

    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public String getOriginCuit() { return originCuit; }

    public void setOriginCuit(String originCuit) { this.originCuit = originCuit;}

    public String getRecipientCuit() {return recipientCuit;}

    public void setRecipientCuit(String recipientCuit) {this.recipientCuit = recipientCuit;}

    public LocalDate getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(LocalDate accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public BatchFile getBatchFile() {
        return batchFile;
    }

    public void setBatchFile(BatchFile batchFile) {
        this.batchFile = batchFile;
    }
}
