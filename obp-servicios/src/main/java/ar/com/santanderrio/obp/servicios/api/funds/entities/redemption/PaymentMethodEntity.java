package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

public class PaymentMethodEntity {
    private String type;
    private String UBK;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUBK() {
        return UBK;
    }

    public void setUBK(String UBK) {
        this.UBK = UBK;
    }
}
