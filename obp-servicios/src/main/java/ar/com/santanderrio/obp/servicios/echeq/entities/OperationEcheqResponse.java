package ar.com.santanderrio.obp.servicios.echeq.entities;

public class OperationEcheqResponse {
    private Boolean status = Boolean.FALSE;
    private String numeroComprobante;
    private String iatxErrorCode;

    public OperationEcheqResponse() {
    }

    protected OperationEcheqResponse(OperationEcheqResponseBuilder builder) {
        this.status = builder.status;
        this.numeroComprobante = builder.numeroComprobante;
        this.iatxErrorCode = builder.iatxErrorCode;
    }

    public static OperationEcheqResponseBuilder builder() {
        return new OperationEcheqResponseBuilder();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getIatxErrorCode() {
        return iatxErrorCode;
    }

    public void setIatxErrorCode(String iatxErrorCode) {
        this.iatxErrorCode = iatxErrorCode;
    }

    public static class OperationEcheqResponseBuilder {
        private Boolean status = Boolean.FALSE;
        private String numeroComprobante;
        private String iatxErrorCode;

        public OperationEcheqResponseBuilder status(Boolean status) {
            this.status = status;
            return this;
        }

        public OperationEcheqResponseBuilder numeroComprobante(String numeroComprobante) {
            this.numeroComprobante = numeroComprobante;
            return this;
        }

        public OperationEcheqResponseBuilder iatxErrorCode(String iatxErrorCode) {
            this.iatxErrorCode = iatxErrorCode;
            return this;
        }

        public OperationEcheqResponse build() {
            return new OperationEcheqResponse(this);
        }
    }
}
