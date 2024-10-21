package ar.com.santanderrio.obp.servicios.logoutclientepas.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.logoutclientepas.view.ResponseLogoutClientePas;

public interface LogoutClientePasManager {

    Respuesta<ResponseLogoutClientePas> clienteEsPas();

}
