package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

public class MensajeConfirmacion {

    private String titulo;
    private String descripcion;
    public String getDescripcion() {
        return descripcion;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public MensajeConfirmacion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "MensajeConfirmacion{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
