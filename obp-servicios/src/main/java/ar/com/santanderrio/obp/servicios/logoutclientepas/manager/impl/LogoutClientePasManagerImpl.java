package ar.com.santanderrio.obp.servicios.logoutclientepas.manager.impl;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.logoutclientepas.manager.LogoutClientePasManager;
import ar.com.santanderrio.obp.servicios.logoutclientepas.view.ResponseLogoutClientePas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutClientePasManagerImpl implements LogoutClientePasManager {

    @Autowired
    RespuestaFactory respuestaFactory;

    @Autowired
    SesionParametros sesionParametros;

    @Override
    public Respuesta<ResponseLogoutClientePas> clienteEsPas() {
        ResponseLogoutClientePas response = new ResponseLogoutClientePas();
        response.setPas(sesionParametros.getPas());
        return respuestaFactory.crearRespuestaOk(ResponseLogoutClientePas.class, response);
    }
}
