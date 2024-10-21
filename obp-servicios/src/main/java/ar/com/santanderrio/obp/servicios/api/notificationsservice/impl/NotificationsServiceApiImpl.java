package ar.com.santanderrio.obp.servicios.api.notificationsservice.impl;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.NotificationsServiceApi;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.entities.NotificationsServiceRequest;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.entities.NotificationsServiceResponse;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;

@Component
public class NotificationsServiceApiImpl implements NotificationsServiceApi {
    private String jks = "NOTIFICATIONSSERVICE-API";

    @Autowired
    private RestWebClient restWebClient;

    private WebClient obtenerClienteRest() throws DAOException {
        WebClient clientLogin = restWebClient.obtenerClienteRest(jks);
        clientLogin.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        return clientLogin;
    }

    @Override
    public NotificationsServiceResponse sendMessageToTopic(Object message, String topic) throws DAOException {
        NotificationsServiceRequest request = new NotificationsServiceRequest(topic, message);
        return obtenerClienteRest().post(request, NotificationsServiceResponse.class);
    }
    
}
