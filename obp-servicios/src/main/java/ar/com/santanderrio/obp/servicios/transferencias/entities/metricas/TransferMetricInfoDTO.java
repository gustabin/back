package ar.com.santanderrio.obp.servicios.transferencias.entities.metricas;

public class TransferMetricInfoDTO {

    private String nup;

    private boolean isRioRio;

    private boolean isCuentaPropia;

    private boolean hasDestinationInfo = true;

    private TransferStatus status;

    private DetalleError detalleError;

    public TransferMetricInfoDTO(String nup, TransferStatus status) {

        this.nup = nup;
        this.status = status;

    }

    public TransferMetricInfoDTO(String nup, TransferStatus status, boolean hasDestinationInfo) {

        this.nup = nup;
        this.status = status;
        this.hasDestinationInfo = hasDestinationInfo;

    }

    public boolean isStatusFailOrWarning() {

        return status == TransferStatus.WARNING || status == TransferStatus.FAIL;

    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public boolean isRioRio() {
        return isRioRio;
    }

    public void setRioRio(boolean rioRio) {
        isRioRio = rioRio;
    }

    public boolean isCuentaPropia() {
        return isCuentaPropia;
    }

    public void setCuentaPropia(boolean cuentaPropia) {
        isCuentaPropia = cuentaPropia;
    }

    public boolean hasDestinationInfo() {
        return hasDestinationInfo;
    }

    public void setHasDestinationInfo(boolean hasDestinationInfo) {
        this.hasDestinationInfo = hasDestinationInfo;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public DetalleError getDetalleError() {
        return detalleError;
    }

    public void setDetalleError(DetalleError detalleError) {
        this.detalleError = detalleError;
    }
}
