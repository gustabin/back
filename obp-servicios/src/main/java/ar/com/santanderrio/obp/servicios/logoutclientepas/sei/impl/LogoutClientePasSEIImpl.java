package ar.com.santanderrio.obp.servicios.logoutclientepas.sei.impl;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.logoutclientepas.manager.LogoutClientePasManager;
import ar.com.santanderrio.obp.servicios.logoutclientepas.sei.LogoutClientePasSEI;
import ar.com.santanderrio.obp.servicios.logoutclientepas.view.ResponseLogoutClientePas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("logoutClientePasSEI")
public class LogoutClientePasSEIImpl implements LogoutClientePasSEI {

    @Autowired
    private RespuestaFactory respuestaFactory;

    @Autowired
    private SesionParametros sesionParametros;

    @Autowired
    private LogoutClientePasManager logoutClientePasManager;

    @Override
    public Respuesta<ResponseLogoutClientePas> getClietnePas() {
        return logoutClientePasManager.clienteEsPas();
    }
}
