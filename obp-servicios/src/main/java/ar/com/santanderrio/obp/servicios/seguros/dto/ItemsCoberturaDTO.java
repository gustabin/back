package ar.com.santanderrio.obp.servicios.seguros.dto;

public class ItemsCoberturaDTO {

    private String codTexto;
    private String descripcionTexto;
    private String texto;

    public String getCodTexto() {
        return codTexto;
    }

    public void setCodTexto(String codTexto) {
        this.codTexto = codTexto;
    }

    public String getDescripcionTexto() {
        return descripcionTexto;
    }

    public void setDescripcionTexto(String descripcionTexto) {
        this.descripcionTexto = descripcionTexto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
