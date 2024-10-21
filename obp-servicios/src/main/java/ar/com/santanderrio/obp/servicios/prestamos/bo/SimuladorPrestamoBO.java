/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.view.DestinoPrestamoSeleccionView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.ConfiguracionPrestamoView;

/**
 * The Interface SimuladorPrestamoBO.
 */
public interface SimuladorPrestamoBO {

	/**
	 * Obtener configuracion simulacion prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the configuracion prestamo DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	ConfiguracionPrestamoDTO obtenerConfiguracionSimulacionPrestamo(Cliente cliente, Cuenta cuenta)
			throws BusinessException;

	/**
	 * Obtener destinos prestamo.
	 *
	 * @param producto
	 *            the producto
	 * @param subProducto
	 *            the sub producto
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	List<DestinoPrestamoSeleccionView> obtenerDestinosPrestamo(String producto, String subProducto)
			throws BusinessException;

	/**
	 * Chequear rangos.
	 *
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @param rangoCuota
	 *            the rango cuota
	 * @return the list
	 */
	List<ItemMensajeRespuesta> chequearRangos(SolicitudSimulacionView solicitudSimulacion,
			PrestamoPermitidoEntity rangoCuota);

	
	/**
	 * Encolar simulacion prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @param configuracionPrestamoDto
	 *            the configuracion prestamo dto
	 * @return the simulador prestamo out entity
	 * @throws BusinessException
	 *             the business exception
	 */
	PrestamoOutEntity obtenerPrestamo(Cliente cliente, SolicitudSimulacionView solicitudSimulacion,
									  ConfiguracionPrestamoDTO configuracionPrestamoDto, String fase) throws BusinessException;


	/**
	 * Obtener PrestamoInEntity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @param configuracionPrestamoDto
	 *            the configuracion prestamo dto
	 * @return the simulador prestamo int entity
	 * @throws BusinessException
	 *             the business exception
	 */
	PrestamoInEntity obtenerPrestamoInEntity(Cliente cliente, SolicitudSimulacionView solicitudSimulacion,
			ConfiguracionPrestamoDTO configuracionPrestamoDto) throws BusinessException;

	/**
	 * Chequear horario operacion.
	 *
	 * @return Boolean con mensaje de error o string vacio, de acuerdo al caso.
	 */
	Boolean chequearSiEstaEnHorarioOperacion();

	/**
	 * Obtiene la configuracion de simulacion de un 
	 * prestamo preaprobado monoproducto
	 * @param cliente
	 * @return
	 * @throws BusinessException 
	 */
	ConfiguracionPrestamoView obtenerConfiguracionSimulacionPrestamoPreaprobado(Cliente cliente) throws BusinessException;

}