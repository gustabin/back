/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleHistorialOperacionesDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasDTO;

/**
 * The Interface DescuentoChequesBO.
 */
public interface DescuentoChequesBO {
    
    /**
	 * Obtener monto calificado cesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<DatosCesionDTO> obtenerMontoCalificadoCesion(Cliente cliente);

	/**
	 * Obtener tasas indicativas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TasasIndicativasDTO> obtenerTasasIndicativas(Cliente cliente);

	/**
	 * Obtener operaciones precargadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param ultimoRegistro
	 *            the ultimo registro
	 * @return the respuesta
	 */
	Respuesta<OperacionesPrecargadasDTO> obtenerOperacionesPrecargadas(Cliente cliente, String ultimoRegistro);
	
	/**
	 * Obtener detalle operaciones precargadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTramite
	 *            the nro tramite
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the respuesta
	 */
	Respuesta<DetalleOperacionesPrecargadasDTO> obtenerDetalleOperacionesPrecargadas(Cliente cliente, String nroTramite, String numeroCuenta, Boolean esSimulacion);
	
	/**
	 * Eliminar operacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTramite
	 *            the nro tramite
	 * @return the respuesta
	 */
	Respuesta<Void> eliminarOperacion(Cliente cliente, String nroTramite);
	
	/**
	 * Obtener historial operaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @param ultimoRegistro
	 *            the ultimo registro
	 * @param filtro
	 *            the filtro
	 * @return the respuesta
	 */
	Respuesta<OperacionesPrecargadasDTO> obtenerHistorialOperaciones(Cliente cliente, String ultimoRegistro, String filtro);

	/**
	 * Obtener detalle historial operaciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nroTramite
	 *            the nro tramite
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 */
	Respuesta<DetalleHistorialOperacionesDTO> obtenerDetalleHistorialOperaciones(Cliente cliente, String nroTramite,
			String numeroCuenta);
}
