/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * The Class MonedaDebinView.
 */
public class MonedaDebinView {

    /** id. */
    private String id;
    
    /** descripcion. */
    private String descripcion;

    /**
	 * Instantiates a new moneda debin view.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
    public MonedaDebinView(String id, String descripcion) {
       this.id = id;
       this.descripcion = descripcion;
    }

    /**
	 * Gets the id.
	 *
	 * @return the id
	 */
    public String getId() {
        return id;
    }

    /**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
    public void setId(String id) {
        this.id = id;
    }

    /**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
    public String getDescripcion() {
        return descripcion;
    }

    /**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
