package ar.com.santanderrio.obp.servicios.logoutmobile.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

public interface LogoutMobileBO {

	
	String consumirServicioLogout(Cliente cliente) throws BusinessException;
	
}
