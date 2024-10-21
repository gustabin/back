/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ResumenesCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaCuentaOnDemandOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaOnDemandDTO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.iatx.handler.CodigoRetornoExtendidoHandler;
import ar.com.santanderrio.obp.servicios.iatx.handler.TimestampTypeHandler;
import ar.com.santanderrio.obp.servicios.iatx.handler.ZeroDateTypeHandler;

/**
 * The Class ResumenesCuentaDAOImpl.
 */
@Component
public class ResumenesCuentaDAOImpl implements ResumenesCuentaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenesCuentaDAOImpl.class);

	/** The Constant SERVICIOCNSCTAXNUP. */
	private static final String SERVICIOCNSCTAXNUP = "CNSCTAXNUP";

	/** The Constant VERSIONCNSCTAXNUP. */
	private static final String VERSIONCNSCTAXNUP = "100";

	/** The Constant WARNING_HAY_MAS_DATOS. */
	private static final int WARNING_HAY_MAS_DATOS = 20008001;

	/** caracter delimitador. */
	private static final char DELIMITADOR = 'õ';

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cuentas.dao.ResumenesCuentaDAO#ejecutarCTAXNUP(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public List<CuentaOnDemandDTO> ejecutarCTAXNUP(Cliente cliente) {
		IatxResponse iatxResponse = null;
		ConsultaCuentaOnDemandOutEntity consultaCuentaOutEntity = null;
		List<CuentaOnDemandDTO> cuentas = new ArrayList<CuentaOnDemandDTO>();
		int codigoDeRetorno;
		try {
			do {
				LOGGER.info("Ejecutando servicio " + SERVICIOCNSCTAXNUP);
				iatxResponse = iatxComm.exec(armarRequest(cliente, consultaCuentaOutEntity));
				codigoDeRetorno = iatxResponse.getErrorCode();
				consultaCuentaOutEntity = processTrama(iatxResponse.getTrama(), ConsultaCuentaOnDemandOutEntity.class);
				cuentas.addAll(consultaCuentaOutEntity.getCuentas());
			} while (codigoDeRetorno == WARNING_HAY_MAS_DATOS);
		} catch (IatxException e) {
			LOGGER.error("ERROR al ejecutar el servicio " + SERVICIOCNSCTAXNUP);
			e.printStackTrace();
		}
		return cuentas;
	}

	/**
	 * Process trama.
	 *
	 * @param <T> the generic type
	 * @param trama the trama
	 * @param clazz the clazz
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public <T> T processTrama(String trama, Class<T> clazz) {
		StreamFactory factory;
		StreamBuilder builder = new StreamBuilder(clazz.getName()).format("delimited")
				.addTypeHandler("timestampTypeHandler", new TimestampTypeHandler())
				.addTypeHandler("codigoRetornoExtendidoHandler", new CodigoRetornoExtendidoHandler())
				.addTypeHandler("zeroDateTypeHandler", new ZeroDateTypeHandler()).addRecord(clazz);
		builder.parser(new DelimitedParserBuilder().delimiter(DELIMITADOR));
		factory = StreamFactory.newInstance();
		factory.define(builder);
		Unmarshaller unmarshaller = factory.createUnmarshaller(clazz.getName());
		return (T) unmarshaller.unmarshal(trama);

	}

	/**
	 * Armar request.
	 *
	 * @param cliente the cliente
	 * @param consultaCuentaOutEntity the consulta cuenta out entity
	 * @return the iatx request
	 */
	private IatxRequest armarRequest(Cliente cliente, ConsultaCuentaOnDemandOutEntity consultaCuentaOutEntity) {
		IatxRequest request = new IatxRequest(SERVICIOCNSCTAXNUP, VERSIONCNSCTAXNUP);
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(cliente.getNup());
		if (null != consultaCuentaOutEntity) {
			CuentaOnDemandDTO ultimaCuenta = consultaCuentaOutEntity.getCuentas()
					.get(consultaCuentaOutEntity.getCuentas().size() - 1);
			requestData.addBodyValue(ultimaCuenta.getEntidadCuenta());
			requestData.addBodyValue(ultimaCuenta.getCentroAlta());
			requestData.addBodyValue(ultimaCuenta.getCuenta());
			requestData.addBodyValue(ultimaCuenta.getPecalpar());
			requestData.addBodyValue(ultimaCuenta.getProducto());
			requestData.addBodyValue(ultimaCuenta.getSubProducto());
		}
		request.setData(requestData);
		return request;
	}

}
