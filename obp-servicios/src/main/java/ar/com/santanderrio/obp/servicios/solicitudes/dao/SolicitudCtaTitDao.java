package ar.com.santanderrio.obp.servicios.solicitudes.dao;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;

public interface SolicitudCtaTitDao {
	
	ValidaAltaView validaAltaProducto(Cliente cliente);
	
	

}
