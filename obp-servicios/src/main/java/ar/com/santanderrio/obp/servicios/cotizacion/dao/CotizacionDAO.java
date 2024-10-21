/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cotizacion.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionParametrosEntity;
import ar.com.santanderrio.obp.servicios.cotizacion.entities.CotizacionEntity;

/**
 * Interfaz CotizacionDAO.
 */
public interface CotizacionDAO {

	/**
	 * Se comunica con el servicio IATX que obtiene la cotizacion del dolar del
	 * dia.
	 *
	 * @param cliente
	 *            El cliente en cuestion.
	 * @param datosCotizacionEntrada
	 *            Los datos de entrada para llamar al servicio.
	 * @return El objeto CotizacionEntity con los datos solicitados.
	 * @throws DAOException
	 *             the DAO exception
	 */
	CotizacionEntity obtenerDatosCotizacion(Cliente cliente, CotizacionParametrosEntity datosCotizacionEntrada)
			throws DAOException;

}
