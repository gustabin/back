package ar.com.santanderrio.obp.servicios.cuentas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;

public class DetalleDocumentoDTO {

    private String tipoDocumento;
    private String numeroDocumento;
    private Boolean isDocumentoPrincipal;

    private ErrorCompraVentaEnum error;
    private Boolean tieneError = Boolean.FALSE;

    public DetalleDocumentoDTO() {
        super();
    }

    public DetalleDocumentoDTO(String tipoDocumento, String numeroDocumento, Boolean isDocumentoPrincipal) {
        super();
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.isDocumentoPrincipal = isDocumentoPrincipal;
    }

    public DetalleDocumentoDTO(ErrorCompraVentaEnum error) {
        super();
        this.error = error;
        this.tieneError = Boolean.TRUE;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return numeroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.numeroDocumento = nroDocumento;
    }

    public Boolean isDocumentoPrincipal() {
        return isDocumentoPrincipal;
    }

    public void setIsDocumentoPrincipal(Boolean documentoPrincipal) {
        this.isDocumentoPrincipal = documentoPrincipal;
    }

    public ErrorCompraVentaEnum getError() {
        return error;
    }

    public void setError(ErrorCompraVentaEnum error) {
        this.error = error;
    }

    public Boolean getTieneError() {
        return tieneError;
    }

    public void setTieneError(Boolean tieneError) {
        this.tieneError = tieneError;
    }

    @Override
    public String toString() {
        return "DetalleDocumentoDTO [tipoDocumento=" + tipoDocumento + ", nroDocumento=" + numeroDocumento
                + ", marcaDocumentoPpal=" + isDocumentoPrincipal + ", error=" + error + ", tieneError=" + tieneError
                + "]";
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder()
                .append(isDocumentoPrincipal).append(numeroDocumento)
                .append(tipoDocumento).append(error).append(tieneError);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleDocumentoDTO)) return false;

        DetalleDocumentoDTO that = (DetalleDocumentoDTO) o;

        return new EqualsBuilder()
                .append(tipoDocumento, that.tipoDocumento)
                .append(numeroDocumento, that.numeroDocumento)
                .append(isDocumentoPrincipal, that.isDocumentoPrincipal)
                .append(error, that.error)
                .append(tieneError, that.tieneError)
                .isEquals();
    }
}
