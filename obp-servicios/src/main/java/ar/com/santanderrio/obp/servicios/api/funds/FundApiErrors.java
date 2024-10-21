package ar.com.santanderrio.obp.servicios.api.funds;

public enum FundApiErrors {
    APF_001("APF_001", "Sale point does not exist"),
    APF_002("APF_002", "Inactive sale point"),
    APF_030("APF_030", "Fund does not exist"),
    APF_033("APF_033", "Inactive fund"),
    APF_032("APF_032", "Fund status not available to operate"),
    APF_050("APF_050", "Asset management does not exist"),
    APF_053("APF_053", "Inactive asset management"),
    APF_052("APF_052", "Asset management status not available to operate"),
    APF_070("APF_070", "Segment does not exist"),
    APF_071("APF_071", "Inactive segment"),
    APF_090("APF_090","Payment method does not exist"),
    APF_092("APF_092", "Non-parameterized payment method for sale point, asset management and segment"),
    APF_093("APF_093", "Inactive payment method for the sale point, asset management and segment"),
    APF_110("APF_110", "Operation not parameterized for the sale point, fund and segment"),
    APF_111("APF_111", "Inactive operation for the sale point, fund and segment"),
    APF_130("APF_130", "Operative / Waiver - Not enabled for the current day"),
    APF_131("APF_131 ", "Operative/ Waiver - Operating hours are not within the enabled range"),
    FCI_174("FCI_174", "La operacion solicitada no puede ser realizada. " +
            "El importe ingresado supera el saldo disponible,SALDO INSUFICIE,174- SALDO INSUFICIENTE"),
    FCI_059("FCI_059", "La operacion solicitada no puede ser realizada. Por favor, " +
            "reintente mas tarde,ARCHIVOS CERRAD,SISTEMA FUERA DE LINEA."),
    APF_150("APF_150", "Operative does not comply with the parameterized rules");

    private String code;
    private String message;

    FundApiErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }
}
