package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class VendedorView {
    @JsonProperty("rubroFantasia")
    private String rubro;
    private String cuit;
    private String nombreFantasia;

    public VendedorView(String rubro, String cuit, String nombreFantasia) {
        this.rubro = rubro;
        this.cuit = cuit;
        this.nombreFantasia = nombreFantasia;
    }

    public String getRubro() {
        return rubro;
    }

    public String getCuit() {
        return cuit;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }
}
