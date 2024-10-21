package ar.com.santanderrio.obp.servicios.api.transfers.recipients.model;

public class Recipient extends InternalRecipient {

    private String id;

    private String customerId;

    private String destinationCBU;

    private String destinationBank;

    private String destinationBranch;

    private String destinationAccountNumber;

    private String type;

    private String destinationName;

    private String destinationPhone;

    private String destinationDocumentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDestinationCBU() {
        return destinationCBU;
    }

    public void setDestinationCBU(String destinationCBU) {
        this.destinationCBU = destinationCBU;
    }

    public String getDestinationBank() {
        return destinationBank;
    }

    public void setDestinationBank(String destinationBank) {
        this.destinationBank = destinationBank;
    }

    public String getDestinationBranch() {
        return destinationBranch;
    }

    public void setDestinationBranch(String destinationBranch) {
        this.destinationBranch = destinationBranch;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationPhone() {
        return destinationPhone;
    }

    public void setDestinationPhone(String destinationPhone) {
        this.destinationPhone = destinationPhone;
    }

    public String getDestinationDocumentType() {
        return destinationDocumentType;
    }

    public void setDestinationDocumentType(String destinationDocumentType) {
        this.destinationDocumentType = destinationDocumentType;
    }

}
