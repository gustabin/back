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
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionClienteVendeDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class OperacionClienteVendeDAOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class OperacionClienteVendeDAOImpl extends SimulacionCompraVentaDolaresDAO implements OperacionClienteVendeDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionClienteVendeDAOImpl.class);

	/** The servicio operacion venta. */
	@Value("${SERVICIO.PREFIJO.CPADOLCNLS}")
	private String servicioOperacionVenta;

	/** The version 100 operacion venta. */
	@Value("${SERVICIO.VERSION.CPADOLCNLS}")
	private String version100OperacionVenta;
	
	/**
	 * Invocación al servicio CPADOLCNLS Realiza operación de compra (Banco
	 * Compra / Cliente Vende) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionVentaDatosEntrada
	 *            the operacion venta datos entrada
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the operacion cliente venta entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public OperacionClienteVentaEntity operacionClienteVende(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionVentaDatosEntrada, Boolean isBancaPrivada) throws DAOException {
		LOGGER.info("Invocando Servicio Iatx " + cliente, operacionVentaDatosEntrada);
		IatxResponse iatxResponse = obtenerResponseClienteVende(cliente, operacionVentaDatosEntrada, isBancaPrivada);
		return crearResponseEntity(iatxResponse);
	}

	/**
	 * Genera el request al servicio de Iatx para la compra Invocación al
	 * servicio CPADOLCNLS Realiza operación de compra (Banco Compra/ Cliente
	 * Vende) de dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraVentaEntrada
	 *            the operacion compra venta entrada
	 * @return the iatx response
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxResponse obtenerResponseClienteVende(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraVentaEntrada, Boolean isBancaPrivada) throws DAOException {
		IatxRequestData data = crearRequestData(cliente, operacionCompraVentaEntrada);
		if (isBancaPrivada) {
			data.setCanalTipo(CompraVentaStringUtil.CANAL_BP);
			data.setSubCanalTipo(CompraVentaStringUtil.SUB_CANAL_BP);
		}
		return obtenerRequestIatx(servicioOperacionVenta, version100OperacionVenta, data);
	}

	/**
	 * Construye el objeto respuesta en base a la respuesta de Iatx sin error.
	 * Realiza el manejo de excepciones segun el codigo de error retornado por
	 * el servicio de Iatx CPADOLCNLS Operación de compra (Banco Compra/ Cliente
	 * Vende) de dólares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the operacion cliente venta entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private OperacionClienteVentaEntity crearResponseEntity(IatxResponse iatxResponse) throws DAOException {
		if (esRespuestaOK(iatxResponse)) {
			return crearRespuestaEntity(iatxResponse);
		}
		return crearRespuestaEntityError(iatxResponse);
	}

	/**
	 * Crea un DTO con el codigo de error de IatxResponse.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the operacion cliente compra DTO
	 */
	private OperacionClienteVentaEntity crearRespuestaEntityError(IatxResponse iatxResponse) {
		if(ErrorCompraVentaEnum.CODIGO_BOTON_PANICO.getCodigo().equals(iatxResponse.getErrorCode())){
			return new OperacionClienteVentaEntity(iatxResponse.getErrorCode(), true, iatxResponse.getIatxBody().get(3));
		}
		if(ErrorCompraVentaEnum.CODIGO_ONLINE_BCRA.getCodigo().equals(iatxResponse.getErrorCode())){
			return new OperacionClienteVentaEntity(iatxResponse.getErrorCode(), true, iatxResponse.getIatxBody().get(3));
		}
		return new OperacionClienteVentaEntity(iatxResponse.getErrorCode(), true);
	}

	/**
	 * Genera los datos para realizar el request a Iatx operación de compra
	 * dolar CPADOLCNLS Operación de compra(Banco Compra/ Cliente Vende) de
	 * dólares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param operacionCompraVentaEntrada
	 *            the operacion compra venta entrada
	 * @return the iatx request data
	 */
	@Override
	protected IatxRequestData crearRequestData(Cliente cliente,
			OperacionCompraVentaDatosEntrada operacionCompraVentaEntrada) {
		IatxRequestData requestData = super.crearRequestData(cliente, operacionCompraVentaEntrada);
		requestData.addBodyValue(operacionCompraVentaEntrada.getPaisemisdoc());
		requestData.addBodyValue(operacionCompraVentaEntrada.getPaisnac());
		requestData.addBodyValue(operacionCompraVentaEntrada.getCodigoconcepto());
		requestData.addBodyValue(operacionCompraVentaEntrada.getTpodocbcra());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNrodocbcra());
		requestData.addBodyValue(operacionCompraVentaEntrada.getTiprefafip());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNiocuentas());
		requestData.addBodyValue(operacionCompraVentaEntrada.getNiocuentas());
		return requestData;
	}

	/**
	 * Crea un Entity con los datos del response Iatx CPADOLCNLS Operación de
	 * compra (Banco Compra/ Cliente Vende) de dólares.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the operacion cliente venta entity
	 */
	private OperacionClienteVentaEntity crearRespuestaEntity(IatxResponse iatxResponse) {
		OperacionClienteVentaEntity operacionClienteVentaEntity = new OperacionClienteVentaEntity();
		operacionClienteVentaEntity.setCuentadebito(iatxResponse.getNextData());
		operacionClienteVentaEntity.setDivisacuentadebito(iatxResponse.getNextData());
		operacionClienteVentaEntity.setImportedebitar(iatxResponse.getNextData());
		operacionClienteVentaEntity.setNiodebito(iatxResponse.getNextData());
		operacionClienteVentaEntity.setCuentacredito(iatxResponse.getNextData());
		operacionClienteVentaEntity.setDivisacuentacredito(iatxResponse.getNextData());
		operacionClienteVentaEntity.setImporteacreditar(iatxResponse.getNextData());
		operacionClienteVentaEntity.setNiocredito(iatxResponse.getNextData());
		operacionClienteVentaEntity.setImportecotizacion(iatxResponse.getNextData());
		operacionClienteVentaEntity.setSpredaplicado(iatxResponse.getNextData());
		operacionClienteVentaEntity.setNroBoleto(iatxResponse.getNextData());
		// fecha formato AAAAMMDD
		operacionClienteVentaEntity.setFecha(iatxResponse.getFecha());
		// hora formato HH:MM:SS
		String[] horaConSegundos = iatxResponse.getHora().split(":");
		operacionClienteVentaEntity.setHora(horaConSegundos[0] + ":" + horaConSegundos[1]);
		operacionClienteVentaEntity.setNroOperacion(iatxResponse.getNroComprobante());
		operacionClienteVentaEntity.setFechaHoraBody(iatxResponse.getTrama().substring(64, 78));
		return operacionClienteVentaEntity;
	}
	

}
