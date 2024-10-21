package ar.com.santanderrio.obp.servicios.debinws.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebinV3;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCBUEntityIn;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinCBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinDestinatarioNoVerificadoException;

/**
 * DebinWSSolicitudesDAO
 * 
 * Consumir ws soap de Debin.
 */
public interface DebinWSSolicitudesDAO {

	/**
	 * Consultar cuentas adheridas.
	 *
	 * @param request
	 *            the request
	 * @return the response vendedor
	 * @throws DAOException
	 *             the DAO exception
	 */
	public abstract ResponseVendedor consultarCuentasAdheridas(RequestConsulta request) throws DAOException;

	/**
	 * Consultar CNSTITCBU.
	 *
	 * @param entityIn
	 *            the entity in
	 * @return the consulta cbu entity out
	 * @throws DebinCBUInvalidoDAOException
	 *             the debin CBU invalido DAO exception
	 * @throws DebinDestinatarioNoVerificadoException
	 *             the debin destinatario no verificado exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	public abstract ConsultaCbuEntityOut consultarCNSTITCBU(ConsultaCBUEntityIn entityIn)
			throws DebinCBUInvalidoDAOException, DebinDestinatarioNoVerificadoException, DAOException;

	/**
	 * Solicitar debin.
	 *
	 * @param request
	 *            the request
	 * @return the response nuevo debin
	 * @throws DAOException
	 *             the DAO exception
	 */
	public abstract ResponseNuevoDebin solicitarDebin(RequestNuevoDebin request) throws DAOException;

	/**
	 * Solicitar debin V3.
	 *
	 * @param request
	 *            the request
	 * @return the response nuevo debin
	 * @throws DAOException
	 *             the DAO exception
	 */
	public abstract ResponseNuevoDebin solicitarDebinV3(RequestNuevoDebinV3 request) throws DAOException;

	/**
	 * Descargar comprobante.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @return the reporte
	 */
	public abstract Reporte descargarComprobante(ComprobanteSolicitudDTO comprobanteDTO);

}