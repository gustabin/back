package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing;


public class RefinancingResponseDto {

    private String rateType;

    private String description;

    private String type;

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
