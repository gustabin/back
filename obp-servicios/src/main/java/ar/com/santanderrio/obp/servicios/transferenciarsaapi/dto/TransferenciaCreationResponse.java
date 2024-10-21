package ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto;

public class TransferenciaCreationResponse {

    private String id;
    private Long nup;
    private String destinationCuit;
    private String currency;

    public TransferenciaCreationResponse() {

    }

    public TransferenciaCreationResponse(String id, Long nup, String destinationCuit, String currency) {
        this.id = id;
        this.nup = nup;
        this.destinationCuit = destinationCuit;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNup() {
        return nup;
    }

    public void setNup(Long nup) {
        this.nup = nup;
    }

    public String getDestinationCuit() {
        return destinationCuit;
    }

    public void setDestinationCuit(String destinationCuit) {
        this.destinationCuit = destinationCuit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
