/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.ConfirmarOrdenPFView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;

/**
 * The Interface OrdenesBO.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
public interface OrdenesBO {

	/**
	 * Iniciar ordenes operaciones. Carga la vista con los datos de inicio de la
	 * pantalla de busquedas y operaciones de inversiones.
	 * 
	 * Por defecto se cargan los resultados de la primer cuenta obtenida al
	 * consultar las cuentas del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<OrdenesDTO> iniciarOrdenesOperaciones(Cliente cliente);

	/**
	 * Buscar ordenes operaciones.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param codigoSist
	 *            the codigo sist
	 * @return the respuesta
	 */
	Respuesta<OrdenesDTO> buscarOrdenesOperacionesPorCuenta(String numeroCuenta, String codigoSist);

	/**
	 * Buscar ordenes operaciones.
	 *
	 * @param filtrosOrdenesView
	 *            the filtros ordenes view
	 * @param codigoSist
	 *            the codigo sist
	 * @return the respuesta
	 */
	Respuesta<OrdenesDTO> buscarOrdenesOperaciones(FiltrosOrdenesView filtrosOrdenesView, String codigoSist);
	
	
	/**
	 * Confirmar orden.
	 *
	 * @param confirmarOrdenInEntity
	 *            the confirmar orden in entity
	 * @return the respuesta
	 */
	Respuesta<ConfirmarOrdenPFView> confirmarOrden(ConfirmarOrdenInEntity confirmarOrdenInEntity); 
	
	

	
}
