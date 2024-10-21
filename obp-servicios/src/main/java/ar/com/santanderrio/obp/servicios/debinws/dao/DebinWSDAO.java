package ar.com.santanderrio.obp.servicios.debinws.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebito;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebitoV3;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestContracargo;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseContracargo;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteInDTO;

/**
 * DebinWSDAO
 * 
 * Consumir ws soap de Debin.
 */
public interface DebinWSDAO {

	/**
	 * Consulta Debin New.
	 *
	 * @param request the request
	 * @return the response debin
	 * @throws DAOException the DAO exception
	 */
	ResponseDebin consultaDebinNew(RequestDebin request) throws DAOException;

	/**
	 * Lista Debin New.
	 *
	 * @param request the request
	 * @return the response lista debin
	 * @throws DAOException the DAO exception
	 */
	ResponseListaDebin listaDebinNew(RequestListaDebin request) throws DAOException;

	/**
	 * descargarComprobante.
	 *
	 * @param comprobanteDTO the comprobante DTO
	 * @return Reporte
	 */
	Reporte descargarComprobante(ComprobanteInDTO comprobanteDTO);

	/**
	 * Eliminar debin.
	 *
	 * @param request the request
	 * @return the response
	 * @throws DAOException the DAO exception
	 */
	Response eliminarDebin(RequestDebin request) throws DAOException;

	/**
	 * adhesionComprador
	 * 
	 * @param request
	 * @return
	 */
	Response adhesionComprador(RequestAdhesion request) throws DAOException;

	/**
	 * confirmarDebito
	 * 
	 * @param request
	 * @return
	 */
	Response confirmacionDebito(RequestConfirmacionDebito request) throws DAOException;

	/**
	 * confirmarDebitoV3
	 * 
	 * @param request
	 * @return
	 */
	Response confirmacionDebitoV3(RequestConfirmacionDebitoV3 request) throws DAOException;

	ResponseContracargo solicitarContracargo(RequestContracargo request) throws DAOException;

}