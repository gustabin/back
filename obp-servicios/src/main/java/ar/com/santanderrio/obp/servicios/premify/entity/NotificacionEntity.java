package ar.com.santanderrio.obp.servicios.premify.entity;

import org.beanio.annotation.Field;

public class NotificacionEntity {

    @Field
    private Integer cantidadNotificaciones = 0;

    @Field
    private boolean hayNotificaciones = false;

    public NotificacionEntity(Integer cantidadNotificaciones){
        this.cantidadNotificaciones = cantidadNotificaciones;
        this.hayNotificaciones = cantidadNotificaciones > 0;
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
