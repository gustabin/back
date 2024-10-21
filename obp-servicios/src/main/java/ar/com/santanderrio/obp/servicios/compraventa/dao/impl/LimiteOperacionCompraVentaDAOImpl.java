package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.LimiteOperacionCompraVentaDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.LimiteCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * Consulta limites maximos para compra y venta de USD
 * Servicio CVTADOLLIM
 * @author A309331
 *
 */
@Component
public class LimiteOperacionCompraVentaDAOImpl extends IatxBaseDAO  implements LimiteOperacionCompraVentaDAO {

	private static final int OK_CODIGO_RETORNO = 0;

	// CLIENTE COMPRA BANCO VENDE
	private static final String COMPRA = "V";

	// CLIENTE VENDE BANCO COMPRA
	private static final String VENDE = "C";

	private static final String CAUSA_SUPERIOR = "000";

	private static final String MONEDA_USD = "002";

	private static final String NO = "NO";

	private static final String INDICADOR_N = "N";

	private static final String LLAVE_SUPERIOR = "0";

	private static final String CONSULTA = "4 ";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LimiteOperacionCompraVentaDAOImpl.class);

	/** The servicio valida limites maximos de compra y venta. */
	@Value("${SERVICIO.PREFIJO.CVTADOLLIM}")
	private String servicioValidaLimiteCompraVenta;

	/** The version 150 valida limites maximos de compra y venta. */
	@Value("${SERVICIO.VERSION.CVTADOLLIM}")
	private String versionValidaLimiteCompraVenta;
	
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;
	
	@Override
	public LimiteCompraVentaUSDEntity limiteMaximoCompraVentaUSD(Cliente cliente,
			ParametrosOperacion parametrosOperacion, Boolean isBancaPrivada, boolean vende, String conceptoBCRA)
			throws DAOException {
		LOGGER.info("Invocando Servicio Iatx - consulta de límite máximo de compra/venta " + cliente, parametrosOperacion);
		return obtenerResponseLimiteCompraVentaUSD(cliente, parametrosOperacion, isBancaPrivada, vende, conceptoBCRA);
	}

	
	/**
	 * Invoca al servicio IATX CVTADOLLIM
	 * @param cliente
	 * @param operacionVentaDatosEntrada
	 * @param isBancaPrivada
	 * @return
	 * @throws DAOException
	 */
	private LimiteCompraVentaUSDEntity obtenerResponseLimiteCompraVentaUSD(Cliente cliente,
			ParametrosOperacion parametrosOperacion, Boolean isBancaPrivada, boolean vende, String conceptoBCRA) throws DAOException {
		
		LOGGER.debug("obtenerResponseLimiteCompraVentaUSD");
		IatxResponse iatxResponse = null;
		try {
			IatxRequestData data = crearRequestLimiteCompraVentaUSDData(cliente, parametrosOperacion, vende, conceptoBCRA);
			if (isBancaPrivada) {
				data.setCanalTipo(CompraVentaStringUtil.CANAL_BP);
				data.setSubCanalTipo(CompraVentaStringUtil.SUB_CANAL_BP);
			}
			IatxRequest iatxRequest = new IatxRequest(servicioValidaLimiteCompraVenta, versionValidaLimiteCompraVenta);
			iatxRequest.setData(data);
			iatxResponse = iatxComm.exec(iatxRequest);
			if (OK_CODIGO_RETORNO == iatxResponse.getErrorCode()) {
				return processTrama(iatxResponse.getTrama(), LimiteCompraVentaUSDEntity.class);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		}
		return null;
		
	}

	
	/**
	 * Genera el request para invocar al servicio 
	 * @param cliente
	 * @param operacionVentaDatosEntrada
	 * @return
	 */
	private IatxRequestData crearRequestLimiteCompraVentaUSDData(Cliente cliente,
			ParametrosOperacion parametrosOperacion, boolean vende, String conceptoBCRA) {

		LOGGER.debug("crearRequestLimiteCompraVentaUSDData");
		
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(CONSULTA); // operacion A2
		requestData.addBodyValue(StringUtils.leftPad("", 15, "0")); // importe dolar N(13,2)
		requestData.addBodyValue(StringUtils.leftPad("",1)); // audita A1
		requestData.addBodyValue(LLAVE_SUPERIOR); //  llave superior N1
		requestData.addBodyValue(MONEDA_USD); // codigo moneda N3
		requestData.addBodyValue(StringUtils.leftPad("",15, "0")); // importe moneda nac N(13,2)
		requestData.addBodyValue("080"); // pais residencia N3
		requestData.addBodyValue(CAUSA_SUPERIOR); // causa superior N3
		requestData.addBodyValue(INDICADOR_N);	// control efectivo A1
		requestData.addBodyValue(INDICADOR_N);	// control inhabilitados A1
		requestData.addBodyValue(INDICADOR_N);	// fuerza limite A1
		requestData.addBodyValue(StringUtils.leftPad(conceptoBCRA,6));	// codigo concepto N6
		requestData.addBodyValue(parametrosOperacion.getNupTipo());	// tipoDoc BCRA A1 'T' o 'L' o 'D'
		requestData.addBodyValue(StringUtils.leftPad(parametrosOperacion.getNupNumDoc(),11));	// nroDoc BCRA N11
		requestData.addBodyValue(StringUtils.leftPad("",2));	// instrumento moneda extranjera A2
		requestData.addBodyValue(StringUtils.leftPad("",2));	// instrumento moneda nacional A2
		requestData.addBodyValue(StringUtils.leftPad("",1));	// tipo moneda origen A1
		requestData.addBodyValue(StringUtils.leftPad("",1));	// tipo moneda destino A1
		requestData.addBodyValue(StringUtils.leftPad("",2));	// control operaciones clientes A2
		requestData.addBodyValue(StringUtils.leftPad("",2));	// marca excep. control lim A2
		requestData.addBodyValue(StringUtils.leftPad("",2));	// tipo transaccion A2
		requestData.addBodyValue(StringUtils.leftPad("",4));	// sistema invocador A4 
		requestData.addBodyValue(StringUtils.leftPad("",1));	// tipo moneda operacion A1
		requestData.addBodyValue(vende ? VENDE : COMPRA);	// marca compra venta A1  'C' o 'V'
		requestData.addBodyValue(StringUtils.leftPad("",15, "0"));	// imp repa usado N(13,2) + A1
		requestData.addBodyValue(NO);	// control prestamos A2
		requestData.addBodyValue(StringUtils.leftPad("",4));	// sucursal cuenta N4
		requestData.addBodyValue(StringUtils.leftPad("",2));	// producto cuenta A2
		requestData.addBodyValue(StringUtils.leftPad("",4));	// subproducto cuenta A4
		requestData.addBodyValue(StringUtils.leftPad("",12));	// numero cuenta A12
		requestData.addBodyValue(StringUtils.leftPad("",3));	// firmante cuenta A3
		
		return requestData;
		
	}

}
