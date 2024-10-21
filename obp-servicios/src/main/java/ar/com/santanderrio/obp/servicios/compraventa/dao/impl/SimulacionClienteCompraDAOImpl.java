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
import ar.com.santanderrio.obp.servicios.compraventa.dao.SimulacionClienteCompraDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class SimulacionClienteCompraDAOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class SimulacionClienteCompraDAOImpl extends SimulacionCompraVentaDolaresDAO
		implements SimulacionClienteCompraDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionClienteCompraDAOImpl.class);

	/** The servicio simulacion compra. */
	@Value("${SERVICIO.PREFIJO.SIMCPVTACN}")
	private String servicioSimulacionCompra;

	/** The version 110 simulacion. */
	@Value("${SERVICIO.VERSION.SIMCPVTACN}")
	private String version110Simulacion;

	/**
	 * Invocación al servicio SIMCPVTACN110 Realiza simulación de venta (Banco
	 * Vende / Cliente Compra) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionCompraVentaDolarData
	 *            the simulacion compra venta dolar data
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the simulacion compra venta dolar dato salida
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public SimulacionClienteCompraEntity obtenerSimulacionClienteCompra(Cliente cliente,
			SimulacionDatosEntrada simulacionCompraVentaDolarData, Boolean isBancaPrivada) throws DAOException {
		LOGGER.info("Invocando Servicio Iatx " + cliente, simulacionCompraVentaDolarData);
		IatxResponse iatxResponse = obtenerResponseClienteCompra(cliente, simulacionCompraVentaDolarData, isBancaPrivada);
		return crearResponseDTO(iatxResponse);
	}

	/**
	 * Genera el request al servicio simulacion de Iatx para la venta Invocación
	 * al servicio SIMCPVTACN110 Realiza simulación de venta (Banco Vende /
	 * Cliente Compra) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionCompraVentaDolarData
	 *            the simulacion compra venta dolar data
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the iatx request
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxResponse obtenerResponseClienteCompra(Cliente cliente,
			SimulacionDatosEntrada simulacionCompraVentaDolarData, Boolean isBancaPrivada) throws DAOException {
		IatxRequestData data = crearRequestData(cliente, simulacionCompraVentaDolarData);
		if (isBancaPrivada) {
			data.setCanalTipo(CompraVentaStringUtil.CANAL_BP);
			data.setSubCanalTipo(CompraVentaStringUtil.SUB_CANAL_BP);
		}
		return obtenerRequestIatx(servicioSimulacionCompra, version110Simulacion, data);
	}

	/**
	 * Construye el objeto respuesta en base a la respuesta de Iatx sin error.
	 * Realiza el manejo de excepciones segun el codigo de error retornado por
	 * el servicio de Iatx SIMCPVTACN110 Simulación de venta (Banco Vende /
	 * Cliente Compra) de dólares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the simulacion cliente compra DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private SimulacionClienteCompraEntity crearResponseDTO(IatxResponse iatxResponse) throws DAOException {
		if (esRespuestaOK(iatxResponse)) {
			return crearRespuestaEntity(iatxResponse);
		}
		return crearRespuestaDTOError(iatxResponse);
	}

	/**
	 * Genera los datos para realizar el request a Iatx simulador de compra
	 * dolar SIMCPVTACN110 Simulación de venta (Banco Vende / Cliente Compra) de
	 * dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param simulacionCompraVentaDolarData
	 *            the simulacion compra venta dolar data
	 * @return the iatx request data
	 */
	private IatxRequestData crearRequestData(Cliente cliente, SimulacionDatosEntrada simulacionCompraVentaDolarData) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(simulacionCompraVentaDolarData.getAplicacionPesos());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getSucursalCtaPesos());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getNumeroCtaPesos());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getAplicacionDolar());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getSucursalCtaDolar());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getNuemroCtaDolar());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getIndTuAtesora());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getFechaValor());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getConcepDebito());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getConcepCred());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getCodigoDebi());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getCodigoCre());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getImporteDeb());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getImporteCred().toString());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getImporteCoti());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getIndicCompraVta());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getAutorizaAfip());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getTipoCambio());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getNumBoleCvta());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getFecIngrPais().toString());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getNomApell());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getMarcaEmpleado());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getPaisEmisDoc().toString());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getPaisNac().toString());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getCodigoConcepto());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getTpoDocBcra());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getNroDocBcra());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getCondCliente());
		requestData.addBodyValue(simulacionCompraVentaDolarData.getTipRefAfip());
		return requestData;
	}

	/**
	 * Crea un Entity con los datos del response Iatx SIMCPVTACN110 Simulación
	 * de venta (Banco Vende / Cliente Compra) de dólares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the simulacion cliente compra entity
	 */
	private SimulacionClienteCompraEntity crearRespuestaEntity(IatxResponse iatxResponse) {
		SimulacionClienteCompraEntity simulacionClienteCompraEntity = new SimulacionClienteCompraEntity();
		simulacionClienteCompraEntity.setCccarg(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setDivcarg(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setCodcarg(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setConcarg(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setImpcarg(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setCccabon(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setDivabon(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setCodabon(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setConabon(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setImpabon(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setTipcamb(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setImpcoti(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setFechval(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setSdodcar(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setSdocarb(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setNromcar(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setSdodabo(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setSdoabob(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setNromabo(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setCentori(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setCanal(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setImpimpu(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setConcimp(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setImpuest(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setRegimen(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setCodalta(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setNomclie(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setPpriape(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setPsegape(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setTipoid(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setNumiden(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setNumBole(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setPorImpu(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setTotCarg(iatxResponse.getNextData().trim());
        simulacionClienteCompraEntity.setImporteImpuesto2(iatxResponse.getNextData().trim());
        simulacionClienteCompraEntity.setConceptoImpuesto2(iatxResponse.getNextData().trim());
        simulacionClienteCompraEntity.setImpuesto2(iatxResponse.getNextData().trim());
        simulacionClienteCompraEntity.setRegimenImpositivo2(iatxResponse.getNextData().trim());
        simulacionClienteCompraEntity.setPorcentajeImpuesto2(iatxResponse.getNextData().trim());
		simulacionClienteCompraEntity.setImpuestoBienes(iatxResponse.getNextData().trim());
		return simulacionClienteCompraEntity;
	}

	/**
	 * Crear respuesta DTO error.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the simulacion cliente compra DTO
	 */
	private SimulacionClienteCompraEntity crearRespuestaDTOError(IatxResponse iatxResponse) {
		if(ErrorCompraVentaEnum.CODIGO_BOTON_PANICO.getCodigo().equals(iatxResponse.getErrorCode())){
			return new SimulacionClienteCompraEntity(iatxResponse.getErrorCode(), iatxResponse.getIatxBody().get(3));
		} else if(ErrorCompraVentaEnum.CODIGO_ONLINE_BCRA.getCodigo().equals(iatxResponse.getErrorCode())){
			return new SimulacionClienteCompraEntity(iatxResponse.getErrorCode(), iatxResponse.getIatxBody().get(3));
		} else if(ErrorCompraVentaEnum.ERROR_OPERA_USD_FUNCIONARIO_PUBLICO.getCodigo().equals(iatxResponse.getErrorCode())){
            return new SimulacionClienteCompraEntity(iatxResponse.getErrorCode(), iatxResponse.getIatxBody().get(3));
        } else if(ErrorCompraVentaEnum.ERROR_OPERA_USD_NO_PERMITIDA_CERTIFICACION_POSITIVA.getCodigo().equals(iatxResponse.getErrorCode())){
            return new SimulacionClienteCompraEntity(iatxResponse.getErrorCode(), iatxResponse.getIatxBody().get(3));
        }
		return new SimulacionClienteCompraEntity(iatxResponse.getErrorCode());
	}
}