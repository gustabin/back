package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

public class LegalEntity {
    private String operationTypeName;
    private  String operationSubtypeName;
    private String legalMessage;

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }

    public String getOperationSubtypeName() {
        return operationSubtypeName;
    }

    public void setOperationSubtypeName(String operationSubtypeName) {
        this.operationSubtypeName = operationSubtypeName;
    }

    public String getLegalMessage() {
        return legalMessage;
    }

    public void setLegalMessage(String legalMessage) {
        this.legalMessage = legalMessage;
    }
}
