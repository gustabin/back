package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

public class OfertaComercialMicrofrontDto {

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OfertaComercialMicrofrontDto(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
