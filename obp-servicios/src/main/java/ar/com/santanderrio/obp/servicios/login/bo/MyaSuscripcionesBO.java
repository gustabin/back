/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ProductoMyAEnum;
import ar.com.santanderrio.obp.servicios.suscripciones.dto.SuscripcionesDTO;

/**
 * The Interface MyaSuscripcionesBO.
 */
public interface MyaSuscripcionesBO {

	/**
	 * Obtener mensajes suscripciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @param credenciales
	 *            the credenciales
	 * @param producto
	 *            the producto
	 * @return the respuesta
	 */
	Respuesta<SuscripcionesDTO> obtenerMensajesSuscripciones(Cliente cliente, CredencialesMya credenciales,
			ProductoMyAEnum producto);

	/**
	 * Update mensajes.
	 *
	 * @param cliente
	 *            the cliente
	 * @param myaUpdateMensajeView
	 *            the mya update mensaje view
	 * @return the respuesta
	 */
	Respuesta<Void> updateMensajes(Cliente cliente, MyaUpdateMensajeView myaUpdateMensajeView);

}
