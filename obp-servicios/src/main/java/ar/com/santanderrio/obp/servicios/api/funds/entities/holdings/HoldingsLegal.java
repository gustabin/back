package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

public class HoldingsLegal {

    private String operationTypeName;

    private String operationSubtypeName;

    private String legalMessage;

    public String getOperationTypeName() { return operationTypeName; }

    public void setOperationTypeName(String value) { this.operationTypeName = value; }

    public String getOperationSubtypeName() { return operationSubtypeName; }

    public void setOperationSubtypeName(String value) { this.operationSubtypeName = value; }

    public String getLegalMessage() { return legalMessage; }

    public void setLegalMessage(String value) { this.legalMessage = value; }

}
