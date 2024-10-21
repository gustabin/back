package ar.com.santanderrio.obp.servicios.api.transfers.recipients.model;

public class InternalRecipient extends BaseRecipient {

    protected String cbuAlias;

    protected String owner;

    protected String destinationAccountDescription;

    protected String destinationAccountCharacteristic;

    protected String destinationDocumentNumber;

    public String getCbuAlias() {
        return cbuAlias;
    }

    public void setCbuAlias(String cbuAlias) {
        this.cbuAlias = cbuAlias;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDestinationAccountDescription() {
        return destinationAccountDescription;
    }

    public void setDestinationAccountDescription(String destinationAccountDescription) {
        this.destinationAccountDescription = destinationAccountDescription;
    }

    public String getDestinationAccountCharacteristic() {
        return destinationAccountCharacteristic;
    }

    public void setDestinationAccountCharacteristic(String destinationAccountCharacteristic) {
        this.destinationAccountCharacteristic = destinationAccountCharacteristic;
    }

    public String getDestinationDocumentNumber() {
        return destinationDocumentNumber;
    }

    public void setDestinationDocumentNumber(String destinationDocumentNumber) {
        this.destinationDocumentNumber = destinationDocumentNumber;
    }

}
