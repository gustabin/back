package ar.com.santanderrio.obp.servicios.home.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;

public interface MenuHomeManager {
	
	List<SeccionView> construirMenu (Cliente cliente);

	boolean utilizarApiMenu();

}
