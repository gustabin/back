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
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionClienteCompraDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class OperacionClienteCompraDAOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class OperacionClienteCompraDAOImpl extends SimulacionCompraVentaDolaresDAO
		implements OperacionClienteCompraDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionClienteCompraDAOImpl.class);

	/** The servicio operacion compra. */
	@Value("${SERVICIO.PREFIJO.CPVTADOLCN}")
	private String servicioOperacionCompra;

	/** The version 110 operacion compra. */
	@Value("${SERVICIO.VERSION.CPVTADOLCN}")
	private String version110OperacionCompra;

	/**
	 * Invocación al servicio CPVTADOLCN Realiza operación de venta (Banco Vende
	 * / Cliente Compra) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraDatosEntrada
	 *            the operacion compra datos entrada
	 * @return the operacion cliente compra entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public OperacionClienteCompraEntity operacionClienteCompra(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraDatosEntrada, Boolean isCuentaPrivada) throws DAOException {
		LOGGER.info("Invocando Servicio Iatx " + cliente, operacionCompraDatosEntrada);
		IatxResponse iatxResponse = obtenerResponseClienteCompra(cliente, operacionCompraDatosEntrada, isCuentaPrivada);
		return crearResponseEntity(iatxResponse, isCuentaPrivada);
	}

	/**
	 * Genera el request al servicio de Iatx para la venta Invocación al
	 * servicio CPVTADOLCN Realiza operación de venta (Banco Vende / Cliente
	 * Compra) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraVentaEntrada
	 *            the operacion compra venta entrada
	 * @return the iatx response
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxResponse obtenerResponseClienteCompra(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraVentaEntrada, Boolean isCuentaPrivada) throws DAOException {
		IatxRequestData data = crearRequestData(cliente, operacionCompraVentaEntrada);
		if (isCuentaPrivada) {
			data.setCanalTipo(CompraVentaStringUtil.CANAL_BP);
			data.setSubCanalTipo(CompraVentaStringUtil.SUB_CANAL_BP);
		}
		return obtenerRequestIatx(servicioOperacionCompra, version110OperacionCompra, data);
	}

	/**
	 * Construye el objeto respuesta en base a la respuesta de Iatx sin error.
	 * Realiza el manejo de excepciones segun el codigo de error retornado por
	 * el servicio de Iatx CPVTADOLCN Operación de venta (Banco Vende / Cliente
	 * Compra) de dólares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the operacion cliente compra entity
	 */
	private OperacionClienteCompraEntity crearResponseEntity(IatxResponse iatxResponse, Boolean isCuentaPrivada) {
		if (esRespuestaOK(iatxResponse)) {
			return crearRespuestaEntity(iatxResponse);
		}
		return crearRespuestaEntityError(iatxResponse, isCuentaPrivada);
	}

	/**
	 * Genera los datos para realizar el request a Iatx operación de venta dolar
	 * CPVTADOLCN Operación de venta (Banco Vende / Cliente Compra) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraVentaEntrada
	 *            the operacion compra entrada
	 * @return the iatx request data
	 */
	protected IatxRequestData crearRequestData(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraVentaEntrada) {
		IatxRequestData requestData = super.crearRequestData(cliente, operacionCompraVentaEntrada);
		requestData.addBodyValue(operacionCompraVentaEntrada.getMarcaempleado());
		requestData.addBodyValue(operacionCompraVentaEntrada.getPaisemisdoc());
		requestData.addBodyValue(operacionCompraVentaEntrada.getPaisnac());
		requestData.addBodyValue(operacionCompraVentaEntrada.getCodigoconcepto());
		requestData.addBodyValue(operacionCompraVentaEntrada.getTpodocbcra());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNrodocbcra());
		requestData.addBodyValue(operacionCompraVentaEntrada.getTiprefafip());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNiocuentas());
		return requestData;
	}

	/**
	 * Crea un Entity con los datos del response Iatx CPVTADOLCN Operación de
	 * venta (Banco Vende / Cliente Compra) de dólares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the operacion cliente compra entity
	 */
	private OperacionClienteCompraEntity crearRespuestaEntity(IatxResponse iatxResponse) {
		OperacionClienteCompraEntity operacionClienteCompraEntity = new OperacionClienteCompraEntity();
		operacionClienteCompraEntity.setCcccarg(iatxResponse.getNextData());
		operacionClienteCompraEntity.setDivcarg(iatxResponse.getNextData());
		operacionClienteCompraEntity.setCodcarg(iatxResponse.getNextData());
		operacionClienteCompraEntity.setConcarg(iatxResponse.getNextData());
		operacionClienteCompraEntity.setImpcarg(iatxResponse.getNextData());
		operacionClienteCompraEntity.setCccabon(iatxResponse.getNextData());
		operacionClienteCompraEntity.setDivabon(iatxResponse.getNextData());
		operacionClienteCompraEntity.setCodabon(iatxResponse.getNextData());
		operacionClienteCompraEntity.setConabon(iatxResponse.getNextData());
		operacionClienteCompraEntity.setImpabon(iatxResponse.getNextData());
		operacionClienteCompraEntity.setTipcamb(iatxResponse.getNextData());
		operacionClienteCompraEntity.setImpcoti(iatxResponse.getNextData());
		operacionClienteCompraEntity.setFechval(iatxResponse.getNextData());
		operacionClienteCompraEntity.setSdodcar(iatxResponse.getNextData());
		operacionClienteCompraEntity.setSdocarb(iatxResponse.getNextData());
		operacionClienteCompraEntity.setNromcar(iatxResponse.getNextData());
		operacionClienteCompraEntity.setSdodabo(iatxResponse.getNextData());
		operacionClienteCompraEntity.setSdoabob(iatxResponse.getNextData());
		operacionClienteCompraEntity.setNromabo(iatxResponse.getNextData());
		operacionClienteCompraEntity.setCentrl(iatxResponse.getNextData());
		operacionClienteCompraEntity.setCanal(iatxResponse.getNextData());
		operacionClienteCompraEntity.setImplmpu(iatxResponse.getNextData());
		operacionClienteCompraEntity.setConcimp(iatxResponse.getNextData());
		operacionClienteCompraEntity.setImpuest(iatxResponse.getNextData());
		operacionClienteCompraEntity.setRegimen(iatxResponse.getNextData());
		operacionClienteCompraEntity.setCodalta(iatxResponse.getNextData());
		operacionClienteCompraEntity.setNomclie(iatxResponse.getNextData());
		operacionClienteCompraEntity.setPpriape(iatxResponse.getNextData());
		operacionClienteCompraEntity.setPsegape(iatxResponse.getNextData());
		operacionClienteCompraEntity.setTipoid(iatxResponse.getNextData());
		operacionClienteCompraEntity.setNumiden(iatxResponse.getNextData());
		operacionClienteCompraEntity.setNumbole(iatxResponse.getNextData());
		operacionClienteCompraEntity.setPorimpu(iatxResponse.getNextData());
		operacionClienteCompraEntity.setTotcarg(iatxResponse.getNextData());
		operacionClienteCompraEntity.setNiocuentas(iatxResponse.getNextData());
        operacionClienteCompraEntity.setImporteImpuesto2(iatxResponse.getNextData());
        operacionClienteCompraEntity.setConceptoImpuesto2(iatxResponse.getNextData());
        operacionClienteCompraEntity.setImpuesto2(iatxResponse.getNextData());
        operacionClienteCompraEntity.setRegimenImpositivo2(iatxResponse.getNextData());
        operacionClienteCompraEntity.setPorcentajeImpuesto2(iatxResponse.getNextData());
		operacionClienteCompraEntity.setImpuestoBienes(iatxResponse.getNextData());
		// fecha formato AAAAMMDD
		operacionClienteCompraEntity.setFecha(iatxResponse.getFecha());
		// hora formato HH:MM:SS
		String[] horaConSegundos = iatxResponse.getHora().split(":");
		operacionClienteCompraEntity.setHora(horaConSegundos[0] + ":" + horaConSegundos[1]);
		operacionClienteCompraEntity.setNroOperacion(iatxResponse.getNroComprobante());
		operacionClienteCompraEntity.setFechaHoraBody(iatxResponse.getTrama().substring(64, 78));
		return operacionClienteCompraEntity;
	}

	/**
	 * Crea un DTO con el codigo de error de IatxResponse.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the operacion cliente compra DTO
	 */
	private OperacionClienteCompraEntity crearRespuestaEntityError(IatxResponse iatxResponse, Boolean isCuentaPrivada) {
		if(ErrorCompraVentaEnum.CODIGO_BOTON_PANICO.getCodigo().equals(iatxResponse.getErrorCode())){
			return new OperacionClienteCompraEntity(iatxResponse.getErrorCode(), true, iatxResponse.getIatxBody().get(3));
		}
		
		if(ErrorCompraVentaEnum.CODIGO_ONLINE_BCRA.getCodigo().equals(iatxResponse.getErrorCode())){
			if (isCuentaPrivada) {
				return new OperacionClienteCompraEntity(iatxResponse.getErrorCode(), true, iatxResponse.getIatxBody().get(3));
			}else {}
				return new OperacionClienteCompraEntity(iatxResponse.getErrorCode(), true, iatxResponse.getIatxBody().get(5));
		}
		return new OperacionClienteCompraEntity(iatxResponse.getErrorCode(), true);
	}

}
