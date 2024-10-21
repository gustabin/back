/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.SimulacionClienteVendeDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteVendeEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class SimulacionClienteVendeDAOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class SimulacionClienteVendeDAOImpl extends SimulacionCompraVentaDolaresDAO
		implements SimulacionClienteVendeDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionClienteVendeDAOImpl.class);

	/** The servicio simulacion venta. */
	@Value("${SERVICIO.PREFIJO.SIMDOLCNLS}")
	private String servicioSimulacionVenta;

	/** The version 100 simulacion. */
	@Value("${SERVICIO.VERSION.SIMDOLCNLS}")
	private String version100Simulacion;

	/**
	 * Invocación al servicio SIMDOLCNLS100 Realiza simulacion de compra (Banco
	 * Compra / Cliente Vende) de dolares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionCompraVentaDolarData
	 *            the simulacion compra venta dolar data
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the simulacion cliente vende DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public SimulacionClienteVendeEntity obtenerSimulacionClienteVende(Cliente cliente,
			SimulacionDatosEntrada simulacionCompraVentaDolarData, Boolean isBancaPrivada) throws DAOException {
		LOGGER.info("Invocando Servicio Iatx " + cliente, simulacionCompraVentaDolarData);
		IatxResponse iatxResponse = obtenerResponseClienteVende(cliente, simulacionCompraVentaDolarData, isBancaPrivada);
		return crearResponseDTO(iatxResponse);
	}

	/**
	 * Obtener response cliente vende.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionVentaDolarData
	 *            the simulacion venta dolar data
	 * @return the iatx response
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxResponse obtenerResponseClienteVende(Cliente cliente, SimulacionDatosEntrada simulacionVentaDolarData, Boolean isBancaPrivada)
			throws DAOException {
		IatxRequestData data = crearRequestData(cliente, simulacionVentaDolarData);
		if (isBancaPrivada) {
			data.setCanalTipo(CompraVentaStringUtil.CANAL_BP);
			data.setSubCanalTipo(CompraVentaStringUtil.SUB_CANAL_BP);
		}
		return obtenerRequestIatx(servicioSimulacionVenta, version100Simulacion, data);
	}

	/**
	 * Crear response entity.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the simulacion cliente vende DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private SimulacionClienteVendeEntity crearResponseDTO(IatxResponse iatxResponse) throws DAOException {
		if (esRespuestaOK(iatxResponse)) {
			return crearRespuestaEntity(iatxResponse);
		}
		return crearRespuestaEntityError(iatxResponse);
	}

	/**
	 * Crear respuesta DTO error.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the simulacion cliente vende DTO
	 */
	private SimulacionClienteVendeEntity crearRespuestaEntityError(IatxResponse iatxResponse) {
		if (ErrorCompraVentaEnum.CODIGO_BOTON_PANICO.getCodigo().equals(iatxResponse.getErrorCode())) {
			return new SimulacionClienteVendeEntity(iatxResponse.getErrorCode(), iatxResponse.getIatxBody().get(3));
		}
		return new SimulacionClienteVendeEntity(iatxResponse.getErrorCode());
	}

	/**
	 * Genera los datos para realizar el request a Iatx simulador de venta dolar
	 * SIMDOLCNLS100 Simulacion de compra (Banco Compra / Cliente Vende) de
	 * dolares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionVentaData
	 *            the simulacion venta data
	 * @return the iatx request data
	 */
	private IatxRequestData crearRequestData(Cliente cliente, SimulacionDatosEntrada simulacionVentaData) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(simulacionVentaData.getAplicacionPesos());
		requestData.addBodyValue(simulacionVentaData.getSucursalCtaPesos());
		requestData.addBodyValue(simulacionVentaData.getNumeroCtaPesos());
		requestData.addBodyValue(simulacionVentaData.getAplicacionDolar());
		requestData.addBodyValue(simulacionVentaData.getSucursalCtaDolar());
		requestData.addBodyValue(simulacionVentaData.getNuemroCtaDolar());
		requestData.addBodyValue(simulacionVentaData.getIndTuAtesora());
		requestData.addBodyValue(simulacionVentaData.getFechaValor());
		requestData.addBodyValue(simulacionVentaData.getConcepDebito());
		requestData.addBodyValue(simulacionVentaData.getConcepCred());
		requestData.addBodyValue(simulacionVentaData.getCodigoDebi());
		requestData.addBodyValue(simulacionVentaData.getCodigoCre());
		requestData.addBodyValue(simulacionVentaData.getImporteDeb());
		requestData.addBodyValue(simulacionVentaData.getImporteCred().toString());
		requestData.addBodyValue(simulacionVentaData.getImporteCoti());
		requestData.addBodyValue(simulacionVentaData.getIndicCompraVta());
		requestData.addBodyValue(simulacionVentaData.getAutorizaAfip());
		requestData.addBodyValue(simulacionVentaData.getTipoCambio());
		requestData.addBodyValue(simulacionVentaData.getNumBoleCvta());
		requestData.addBodyValue(simulacionVentaData.getCodTributa());
		requestData.addBodyValue(simulacionVentaData.getFecIngrPais().toString());
		requestData.addBodyValue(simulacionVentaData.getNomApell());
		requestData.addBodyValue(simulacionVentaData.getPaisEmisDoc().toString());
		requestData.addBodyValue(simulacionVentaData.getPaisNac().toString());
		requestData.addBodyValue(simulacionVentaData.getCodigoConcepto());
		requestData.addBodyValue(simulacionVentaData.getTpoDocBcra());
		requestData.addBodyValue(simulacionVentaData.getNroDocBcra());
		requestData.addBodyValue(simulacionVentaData.getTipRefAfip());
		return requestData;
	}

	/**
	 * Obtiene Entity con los datos del response Iatx SIMDOLCNLS100 Simulacion
	 * de compra (Banco Compra / Cliente Vende) de dolares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the simulacion cliente vende entity
	 */
	private SimulacionClienteVendeEntity crearRespuestaEntity(IatxResponse iatxResponse) {
		SimulacionClienteVendeEntity simulacionClienteVendeEntity = new SimulacionClienteVendeEntity();
		simulacionClienteVendeEntity.setImporteCotizacion(iatxResponse.getNextData().trim());
		simulacionClienteVendeEntity.setImporteCredito(iatxResponse.getNextData().trim());
		simulacionClienteVendeEntity.setImporteDebito(iatxResponse.getNextData().trim());
		simulacionClienteVendeEntity.setSpred(iatxResponse.getNextData().trim());
		return simulacionClienteVendeEntity;
	}

}