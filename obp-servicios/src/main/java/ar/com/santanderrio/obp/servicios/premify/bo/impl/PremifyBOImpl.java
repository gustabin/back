package ar.com.santanderrio.obp.servicios.premify.bo.impl;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.servicios.premify.bo.PremifyBO;
import ar.com.santanderrio.obp.servicios.premify.dao.impl.PremifyDAOImpl;
import ar.com.santanderrio.obp.servicios.premify.entity.NotificacionEntity;
import ar.com.santanderrio.obp.servicios.premify.contracts.NotificacionResponse;

@Component
public class PremifyBOImpl implements PremifyBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremifyBOImpl.class);

    @Autowired
    PremifyDAOImpl premifyDAO;

    @Override
    public NotificacionEntity obtenerNotificaciones(String nup) {
        try {
            List<NotificacionResponse> notificacionesResponse = premifyDAO.getNotifications(nup);
            Integer cantidadNotificaciones = notificacionesResponse == null ? 0 : notificacionesResponse.size();
            return new NotificacionEntity(cantidadNotificaciones);
        } catch (Exception e) {
            LOGGER.info("Ocurrio un error al consultar las notificaciones para el cliente");
            return new NotificacionEntity(0);
        }
    }
}
