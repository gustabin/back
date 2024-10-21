package ar.com.santanderrio.obp.servicios.premify.bo;

import ar.com.santanderrio.obp.servicios.premify.entity.NotificacionEntity;

public interface PremifyBO {
    NotificacionEntity obtenerNotificaciones(String nup);
}
