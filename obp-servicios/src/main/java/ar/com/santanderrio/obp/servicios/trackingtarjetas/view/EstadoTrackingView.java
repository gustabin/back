/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.view;

/**
 * EstadoTrackingView.
 *
 * @author Silvina_Luque
 */

public enum EstadoTrackingView {
    
    /** The pendiente envio. */
    PENDIENTE_ENVIO("1","1", "Pendiente de envío"),
    
    /** The en transito. */
    EN_TRANSITO("2","2","En tránsito"),
    
    /** The entregado. */
    ENTREGADO("3","3","Entregado"),
    
    /** The en sucursal. */
    EN_SUCURSAL("4","3","En Sucursal"),
    
    /** The destruida. */
    DESTRUIDA("5","4","Destruida");


    /** The id estado. */
    private String idEstado;
    
    /** The id estado view. */
    private String idEstadoView;
    
    /** The descripcion estado view. */
    private String descripcionEstadoView;
    
    /**
	 * Instantiates a new estado tracking view.
	 *
	 * @param idEstado
	 *            the id estado
	 * @param idEstadoView
	 *            the id estado view
	 * @param descripcionEstadoView
	 *            the descripcion estado view
	 */
    EstadoTrackingView(String idEstado, String idEstadoView, String descripcionEstadoView) {
        
        this.idEstado = idEstado;
        this.idEstadoView = idEstadoView;
        this.descripcionEstadoView = descripcionEstadoView;
    }

    /**
	 * Gets the id estado.
	 *
	 * @return the id estado
	 */
    public String getIdEstado() {
        return idEstado;
    }

    /**
	 * Sets the id estado.
	 *
	 * @param idEstado
	 *            the new id estado
	 */
    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    /**
	 * Gets the id estado view.
	 *
	 * @return the id estado view
	 */
    public String getIdEstadoView() {
        return idEstadoView;
    }


    /**
	 * Gets the descripcion estado view.
	 *
	 * @return the descripcion estado view
	 */
    public String getDescripcionEstadoView() {
        return descripcionEstadoView;
    }

    /**
	 * Obtener descripcion por id.
	 *
	 * @param idEstado
	 *            the id estado
	 * @return the string
	 */
    public static String obtenerDescripcionPorId(String idEstado) {
        for (EstadoTrackingView estadoEnum : values()) {
            if (estadoEnum.getIdEstado().equals(idEstado)) {
                return estadoEnum.getDescripcionEstadoView();
            }
        }
        return "";
    }
    
    
}
