/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobanteRecargaTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosRecargaTREntity;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;

/**
 * The Interface RecargaTarjetaBO.
 */
public interface RecargaTarjetaBO extends BusinessObject {

	/**
	 * Recargar.
	 *
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	Respuesta<ComprobanteRecargaTarjetaDTO> recargar(DatosRecargaTREntity datosRecargaTR, Cliente cliente)
			throws DAOException;

	/**
	 * Programar recarga.
	 *
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ComprobanteRecargaTarjetaDTO> programarRecarga(DatosRecargaTREntity datosRecargaTR, Cliente cliente)
			throws ServiceException;

	/**
	 * Baja programacion.
	 *
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ComprobanteRecargaTarjetaDTO> bajaProgramacion(DatosRecargaTREntity datosRecargaTR, Cliente cliente)
			throws ServiceException;

	/**
	 * Stop debit.
	 *
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ComprobanteRecargaTarjetaDTO> stopDebit(DatosRecargaTREntity datosRecargaTR, Cliente cliente)
			throws ServiceException;

	/**
	 * Modificar recarga.
	 *
	 * @param datosRecargaTR
	 *            the datos recarga TR
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<ComprobanteRecargaTarjetaDTO> modificarRecarga(DatosRecargaTREntity datosRecargaTR, Cliente cliente)
			throws ServiceException;

	/**
	 * Generar comprobante recarga.
	 *
	 * @param comprobanteRecargaTarjetaView
	 *            the comprobante recarga tarjeta view
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteRecarga(ComprobanteRecargaTarjetaView comprobanteRecargaTarjetaView);

	/**
	 * Obtener tipo cuenta.
	 *
	 * @param tipoCuentaDestinatario
	 *            the tipo cuenta destinatario
	 * @return the string
	 */
	String obtenerTipoCuenta(Integer tipoCuentaDestinatario);

}
