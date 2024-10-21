/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;

/**
 * The Interface EncabezadoTarjetasBO.
 */
public interface EncabezadoTarjetasBO extends BusinessObject {

	/**
	 * Consulta adhesión de una tarjeta (débito automático - recargas
	 * programadas).
	 *
	 * @param cliente
	 *            the cliente
	 * @param tarjeta
	 *            the tarjeta
	 * @return datos de adhesion de la tarjeta
	 */
	Respuesta<AdhesionTarjeta> obtenerAdhesionTarjeta(Cliente cliente, Cuenta tarjeta);

	/**
	 * Consulta adhesión de las tarjetas recargables de un cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return Datos de adhesión de las tarjetas recargables
	 */
	Respuesta<List<AdhesionTarjeta>> obtenerAdhesionTarjetasRecargables(Cliente cliente);

	/**
	 * Obtiene las tarjetas recargables que posee un cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return Lista de tarjetas recargables
	 */
	Respuesta<List<Cuenta>> obtenerTarjetasRecargables(Cliente cliente);
}
