package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * The class ConceptoRecurrenciaWSView.
 *
 * @author Gastón Capretti
 */
public class EstadoRecurrenciaWSView {

    /** codigo */
    private String id;


    /** descripcion. */
    private String descripcion;

    /**
     *
     */
    public EstadoRecurrenciaWSView() {
    }

    /**
     * @param id
     * @param descripcion
     */
    public EstadoRecurrenciaWSView(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}

