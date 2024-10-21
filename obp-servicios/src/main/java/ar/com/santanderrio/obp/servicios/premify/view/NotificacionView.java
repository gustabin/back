package ar.com.santanderrio.obp.servicios.premify.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NotificacionView {

    private Integer cantidadNotificaciones;

    private boolean hayNotificaciones;

    public NotificacionView(Integer cantidadNotificaciones, boolean hayNotificaciones){
        this.cantidadNotificaciones = cantidadNotificaciones;
        this.hayNotificaciones = hayNotificaciones;
    }

    public Integer getCantidadNotificaciones() {
        return this.cantidadNotificaciones;
    }

    public void setCantidadNotificaciones(Integer cantidadNotificaciones) {
        this.cantidadNotificaciones = cantidadNotificaciones;
    }

    public boolean getHayNotificaciones() {
        return this.hayNotificaciones;
    }

    public void setHayNotificaciones(boolean hayNotificaciones) {
        this.hayNotificaciones = hayNotificaciones;
    }
}
