/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

import java.util.List;

/**
 * The Class TarjetaView.
 */
public class TarjetaNuevoViajeView {

    /** The nombre. */
    private String nombre;

    /** The is titular. */
    private Boolean isTitular;

    /** The tarjetas asociadas. */
    private List<TarjetaAsociadaView> tarjetasAsociadas;

    /** The nombreWs. */
    private String nombreWs;

    /**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
    public String getNombre() {
        return nombre;
    }

    /**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the checks if is titular.
     *
     * @return the checks if is titular
     */
    public Boolean getIsTitular() {
        return isTitular;
    }

    /**
     * Sets the checks if is titular.
     *
     * @param isTitular
     *            the new checks if is titular
     */
    public void setIsTitular(Boolean isTitular) {
        this.isTitular = isTitular;
    }

    /**
	 * Gets the tarjetas asociadas.
	 *
	 * @return the tarjetasAsociadas
	 */
    public List<TarjetaAsociadaView> getTarjetasAsociadas() {
        return tarjetasAsociadas;
    }

    /**
	 * Sets the tarjetas asociadas.
	 *
	 * @param tarjetasAsociadas
	 *            the tarjetasAsociadas to set
	 */
    public void setTarjetasAsociadas(
            List<TarjetaAsociadaView> tarjetasAsociadas) {
        this.tarjetasAsociadas = tarjetasAsociadas;
    }

    /**
	 * Gets the nombre ws.
	 *
	 * @return the nombreWs
	 */
    public String getNombreWs() {
        return nombreWs;
    }

    /**
	 * Sets the nombre ws.
	 *
	 * @param nombreWs
	 *            the nombreWs to set
	 */
    public void setNombreWs(String nombreWs) {
        this.nombreWs = nombreWs;
    }

}
