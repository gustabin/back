package ar.com.santanderrio.obp.servicios.api.notificationsservice;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.entities.NotificationsServiceResponse;

public interface NotificationsServiceApi {
    NotificationsServiceResponse sendMessageToTopic(Object message, String topic) throws DAOException;
}
