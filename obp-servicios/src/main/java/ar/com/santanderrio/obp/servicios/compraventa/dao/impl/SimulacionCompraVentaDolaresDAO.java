/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * Clase con metodos comun entre los servicios SIMCPVTACN110 y SIMDOLCNLS100.
 *
 * @author sabrina.cis
 */
@Component
public class SimulacionCompraVentaDolaresDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionCompraVentaDolaresDAO.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/**
	 * Obtiene respuesta IatxRequest.
	 *
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param version
	 *            the version
	 * @param data
	 *            the data
	 * @return the iatx request
	 * @throws DAOException
	 *             the DAO exception
	 */
	public IatxResponse obtenerRequestIatx(String nombreServicio, String version, IatxRequestData data)
			throws DAOException {
		IatxResponse iatxResponse = crearIatxResponse();
		try {
			IatxRequest request = buildIatxRequest(nombreServicio, version, data);
			iatxResponse = ejecutar(request);
			return iatxResponse;
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e, iatxResponse.getErrorMessage());
		}
	}

	/**
	 * Crear request data.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraVentaEntrada
	 *            the operacion compra venta entrada
	 * @return the iatx request data
	 */
	protected IatxRequestData crearRequestData(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraVentaEntrada) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(operacionCompraVentaEntrada.getAplicacionPesos());
		requestData.addBodyValue(operacionCompraVentaEntrada.getSucursalctapesos());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNumeroctapesos());
		requestData.addBodyValue(operacionCompraVentaEntrada.getAplicacionDolar());
		requestData.addBodyValue(operacionCompraVentaEntrada.getSucursalCtaDolar());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNumeroCtaDolar());
		requestData.addBodyValue(operacionCompraVentaEntrada.getIndtuatesora());
		requestData.addBodyValue(operacionCompraVentaEntrada.getFechavalor());
		requestData.addBodyValue(operacionCompraVentaEntrada.getConcepdebito());
		requestData.addBodyValue(operacionCompraVentaEntrada.getConcepcred());
		requestData.addBodyValue(operacionCompraVentaEntrada.getCodigodebi());
		requestData.addBodyValue(operacionCompraVentaEntrada.getCodigocred());
		requestData.addBodyValue(operacionCompraVentaEntrada.getImportedeb());
		requestData.addBodyValue(operacionCompraVentaEntrada.getImportecred());
		requestData.addBodyValue(operacionCompraVentaEntrada.getImportecoti());
		requestData.addBodyValue(operacionCompraVentaEntrada.getIndiccompravta());
		requestData.addBodyValue(operacionCompraVentaEntrada.getAutorizafip());
		requestData.addBodyValue(operacionCompraVentaEntrada.getTipocambio());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNumbolecvta());
		requestData.addBodyValue(operacionCompraVentaEntrada.getCodtributa());
		requestData.addBodyValue(operacionCompraVentaEntrada.getFecingrpais());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNomapell());
		return requestData;
	}

	/**
	 * Genera un objeto IatxRequest a un servicio Iatx ingresado por parametro.
	 *
	 * @param nombreServicio
	 *            the nombre servicio
	 * @param version
	 *            the version
	 * @param data
	 *            the data
	 * @return the iatx request
	 */
	public IatxRequest buildIatxRequest(String nombreServicio, String version, IatxRequestData data) {
		IatxRequest iatxRequest = new IatxRequest(nombreServicio, version);
		iatxRequest.setData(data);
		return iatxRequest;
	}

	/**
	 * Genera un objeto IatxResponse.
	 *
	 * @return the iatx response
	 */
	public IatxResponse crearIatxResponse() {
		return new IatxResponse();
	}

	/**
	 * Ejecuta el llamado al servicio Iatx.
	 *
	 * @param request
	 *            the request
	 * @return the iatx response
	 * @throws IatxException
	 *             the iatx exception
	 */
	public IatxResponse ejecutar(IatxRequest request) throws IatxException {
		return iatxComm.exec(request);
	}

	/**
	 * Es respuesta Estado Error.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esRespuestaEstadoError(IatxResponse iatxResponse) {
		return iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.ERROR);
	}

	/**
	 * Es respuesta Estado OK.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esRespuestaEstadoOK(IatxResponse iatxResponse) {
		return iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esRespuestaOK(IatxResponse iatxResponse) {
		return esCodigoRetornoOK(iatxResponse) && esRespuestaEstadoOK(iatxResponse);
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the boolean
	 */
	public Boolean esCodigoRetornoOK(IatxResponse iatxResponse) {
		return iatxResponse.getErrorCode() == CompraVentaStringUtil.COD_ERROR_CERO;
	}

}
