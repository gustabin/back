package ar.com.santanderrio.obp.servicios.debinapi.dto;

public enum CurrencyType {
    ARS("032"),
    USD("840");

    private String paymentProcessorCode;

    CurrencyType(String paymentProcessorCode){
        this.paymentProcessorCode = paymentProcessorCode;
    }

    public String getPaymentProcessorCode() {
        return paymentProcessorCode;
    }

    public static CurrencyType fromString(String text) {
        for (CurrencyType actual : CurrencyType.values()){
            if (actual.toString().equals(text) || actual.getPaymentProcessorCode().equals(text)){
                return actual;
            }
        }
        return null;

    }


}
