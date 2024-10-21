/*
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class SeccionView.
 */
public class SeccionMicrofrontView extends SeccionView {

    private MicrofrontView microfront;

    public MicrofrontView getMicrofront() {
        return microfront;
    }

    public void setMicrofront(MicrofrontView microfront) {
        this.microfront = microfront;
    }

    /**
     * To string.
     *
     * @return the string
     */
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Accion", super.getAccion()).append("Titulo", super.getTitulo())
                .append("Microfront name", microfront.getName()).append("Microfront url", microfront.getUrl())
                .append("Mensaje no disponible", super.getMensajeNoDisponible()).append("url", super.getUrl()).toString();
    }
}
