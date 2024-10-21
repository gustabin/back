package ar.com.santanderrio.obp.servicios.echeq.dto;

public class AvalDTO {
    private String nombreAvalista;
    private String documentoAvalista;
    private String tipoDocumentoAvalista;
    private String domicilioAvalista;
    private String estadoAval;
    private String fechaAval;

    public AvalDTO() {}

    public String getNombreAvalista() {
        return nombreAvalista;
    }

    public void setNombreAvalista(String nombreAvalista) {
        this.nombreAvalista = nombreAvalista;
    }

    public String getDocumentoAvalista() {
        return documentoAvalista;
    }

    public void setDocumentoAvalista(String documentoAvalista) {
        this.documentoAvalista = documentoAvalista;
    }

    public String getDomicilioAvalista() {
        return domicilioAvalista;
    }

    public void setDomicilioAvalista(String domicilioAvalista) {
        this.domicilioAvalista = domicilioAvalista;
    }

    public String getTipoDocumentoAvalista() {
        return tipoDocumentoAvalista;
    }

    public void setTipoDocumentoAvalista(String tipoDocumentoAvalista) {
        this.tipoDocumentoAvalista = tipoDocumentoAvalista;
    }

    public String getEstadoAval() {
        return estadoAval;
    }

    public void setEstadoAval(String estadoAval) {
        this.estadoAval = estadoAval;
    }

    public String getFechaAval() {
        return fechaAval;
    }

    public void setFechaAval(String fechaAval) {
        this.fechaAval = fechaAval;
    }
}
