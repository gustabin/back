package ar.com.santanderrio.obp.servicios.perfil.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.perfil.bo.ActualizarNombreBO;
import ar.com.santanderrio.obp.servicios.perfil.dao.ActualizarNombreDAO;

@Component
public class ActualizarNombreBOImpl implements ActualizarNombreBO {

	@Autowired
	private ActualizarNombreDAO actualizarNombreDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActualizarNombreBOImpl.class);
	
	@Override
	public String actualizarNombreCliente(Cliente cliente) {
		try {
			LOGGER.info("Consultando con base de datos para ver si hay que actualizar el nombre del cliente");
			String nombre = cliente.getNombre();
			Long nup = Long.valueOf(cliente.getNup());
			return actualizarNombreDAO.actualizarNombreCliente(nombre, nup);
		} catch (DAOException e) {
			LOGGER.info("El nombre no fue cambiado");
			return "NO SE CAMBIO EL NOMBRE";
		}
	}

}
