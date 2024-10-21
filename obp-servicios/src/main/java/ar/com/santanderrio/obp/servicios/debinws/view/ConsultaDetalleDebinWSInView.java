/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

/**
 * The Class ConsultaDetalleDebinWSInView.
 *
 * @author ConsultaDetalleDebinWSInView
 */
public class ConsultaDetalleDebinWSInView {
    
    /** IdDebin. */
    private String debinId;
    
    /** Consulta desde recibidos. */
    private boolean consultaDesdeRecibidos;
    

    /**
	 * Checks if is consulta desde recibidos.
	 *
	 * @return the consultaDesdeRecibidos
	 */
    public boolean isConsultaDesdeRecibidos() {
        return consultaDesdeRecibidos;
    }

    /**
	 * Sets the consulta desde recibidos.
	 *
	 * @param consultaDesdeRecibidos
	 *            the consultaDesdeRecibidos to set
	 */
    public void setConsultaDesdeRecibidos(boolean consultaDesdeRecibidos) {
        this.consultaDesdeRecibidos = consultaDesdeRecibidos;
    }

    /**
	 * Gets the debin id.
	 *
	 * @return the debin id
	 */
    public String getDebinId() {
        return debinId;
    }

    /**
	 * Sets the debin id.
	 *
	 * @param debinId
	 *            the new debin id
	 */
    public void setDebinId(String debinId) {
        this.debinId = debinId;
    }
    


}
