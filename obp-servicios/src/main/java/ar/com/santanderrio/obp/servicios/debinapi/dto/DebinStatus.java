package ar.com.santanderrio.obp.servicios.debinapi.dto;

public enum DebinStatus {
    INITIALIZED("INICIADO"),
    ON_GOING("EN CURSO"),

    EXPIRED("VENCIDO"),
    DATA_ERROR("ERROR DATOS"),
    DEBIT_ERROR ("ERROR DEBITO"),
    NO_BALANCE_ERROR(""),
    REJECTED_CLIENT_ERROR("RECHAZO DE CLIENTE"),
    REMOVED("ELIMINADO"),
    CREDIT_ERROR("ERROR ACREDITACION"),
    NO_WARRANTY_ERROR("SIN GARANTIA"),

    CREDITED("ACREDITADO");

    private String paymentProcessorStatus;

    DebinStatus(String paymentProcessorStatus){
        this.paymentProcessorStatus = paymentProcessorStatus;
    }

    public static DebinStatus fromPaymentProcessorStatus(String text) {
        for (DebinStatus r : DebinStatus.values()) {
            if (r.getPaymentProcessorStatus().equalsIgnoreCase(text)) {
                return r;
            }
        }
        return null;
    }

    public String getPaymentProcessorStatus() {
        return paymentProcessorStatus;
    }
}
