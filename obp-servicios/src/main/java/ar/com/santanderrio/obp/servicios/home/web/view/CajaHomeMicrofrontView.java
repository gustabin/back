package ar.com.santanderrio.obp.servicios.home.web.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CajaHomeMicrofrontView extends Caja {

    private String titulo;

    private String encabezado;

    private String descripcion;

    private MicrofrontView microfront;

    public CajaHomeMicrofrontView(String titulo, String encabezado, String descripcion, String nombreMicrofront, String urlMicrofront){
        this.titulo = titulo;
        this.encabezado = encabezado;
        this.descripcion = descripcion;
        this.microfront = new MicrofrontView(nombreMicrofront, urlMicrofront);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MicrofrontView getMicrofront() { return microfront; }

    public void setMicrofront(MicrofrontView microfront) {
        this.microfront = microfront;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private class MicrofrontView{

        private String name;

        private String url;

        public MicrofrontView(String nombreMicrofront, String urlMicrofront){
            this.name = nombreMicrofront;
            this.url = urlMicrofront;
        }

        public String getName() { return name; }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() { return url; }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
