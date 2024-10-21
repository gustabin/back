package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing;


import java.math.BigDecimal;

public class RefinancingProductDto {

    private String type;
    private String brand;
    private BigDecimal newLimit;
    private String lastDigits;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(BigDecimal newLimit) {
        this.newLimit = newLimit;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }
}

