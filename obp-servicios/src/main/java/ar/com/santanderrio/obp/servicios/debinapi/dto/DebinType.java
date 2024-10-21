package ar.com.santanderrio.obp.servicios.debinapi.dto;

public enum DebinType {
    SPOT("debin"),
    RECURRENT("recurrente"),
    QR("debinqr"),
    TRANSFER("transferencia"),
    CASHOUT("cashout"),
    CHARGEBACK("contracargo"),
    FIXED_TERM_DEBIN("debinplf"),
    FIXED_TERM_TRANSFER("transferenciaplf");

    private String paymentProcessorType;

    DebinType(String paymentProcessorType){
        this.paymentProcessorType = paymentProcessorType;
    }

    public String getPaymentProcessorType() {
        return paymentProcessorType;
    }

    public static DebinType fromPaymentProcessorType(String text) {
        for (DebinType r : DebinType.values()) {
            if (r.getPaymentProcessorType().equalsIgnoreCase(text)) {
                return r;
            }
        }
        return null;
    }

}
