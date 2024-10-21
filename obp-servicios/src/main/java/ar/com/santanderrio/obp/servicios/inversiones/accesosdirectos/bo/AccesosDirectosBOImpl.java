package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.dao.AccesosDirectosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;

@Component
public class AccesosDirectosBOImpl implements AccesosDirectosBO{
	
	@Autowired AccesosDirectosDAO accesosDirectosDAO;
	
	@Autowired
	protected SesionCliente sesionCliente;
	
	@Override 
	public AccesoDirectoProductoEntity obtenerAccesoDirecto() {
		
		Cliente cliente = sesionCliente.getCliente();
		String nup = cliente.getNup();
		
		AccesoDirectoProductoEntity rta = new AccesoDirectoProductoEntity();
		rta = accesosDirectosDAO.obtenerAccesoDirecto(nup);
		
		return rta;
		
	}
	


}
